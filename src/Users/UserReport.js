import React, { useEffect, useState, useRef } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowLeft, faDownload } from '@fortawesome/free-solid-svg-icons';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

export default function UserReport() {
    const { id } = useParams();
    const [testResult, setTestResult] = useState(null);
    const [user, setUserDetail] = useState(null);
    const [formData, setFormData] = useState({
        title: '',
        disc: '',
        maxMarks: '',
        numberOfQuestions: '',
        active: '',
        subject: {
            subId: '',
        },
    });
    const [questions, setQuestions] = useState([]);
    const pdfContentRef = useRef(null);

    const loadTest = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/test/${testResult.testId}`);
            setFormData(response.data);
        } catch (error) {
            console.error('Failed to load test:', error);
        }
    };

    const loadUser = async () => {
        try {
            const info = await axios.get(`http://localhost:8080/Admin/user/${testResult.userId}`);
            setUserDetail(info.data);
        } catch (error) {
            console.log('Failed to load test:', error);
        }
    }

    const fetchQuestion = async (questionId) => {
        try {
            const response = await axios.get(`http://localhost:8080/question/${questionId}`);
            return response.data;
        } catch (error) {
            console.error(`Failed to fetch question with ID ${questionId}:`, error);
            return null;
        }
    };

    const fetchQuestions = async () => {
        const questionIds = testResult.selectedOptions.map((option) => option.questionId);
        const questionPromises = questionIds.map((questionId) => fetchQuestion(questionId));
        const questionData = await Promise.all(questionPromises);
        setQuestions(questionData.filter((question) => question !== null));
    };

    useEffect(() => {
        const fetchTestResult = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/test-results/${id}`);
                setTestResult(response.data);
                console.log(response.data)
            } catch (error) {
                console.error('Failed to fetch test result:', error);
            }
        };
        fetchTestResult();
    }, [id]);

    useEffect(() => {
        if (testResult && testResult.testId) {
            loadTest();
            loadUser();
        }
    }, [testResult]);

    useEffect(() => {
        if (testResult && testResult.selectedOptions) {
            fetchQuestions();
        }
    }, [testResult]);

    const downloadAsPDF = () => {
        html2canvas(pdfContentRef.current).then((canvas) => {
            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF();
            const imgWidth = 190;
            const imgHeight = (canvas.height * imgWidth) / canvas.width;
            pdf.addImage(imgData, 'PNG', 10, 10, imgWidth, imgHeight);
            pdf.save('report.pdf');
        });
    };

    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark " style={{ background: 'purple' }}>
                <div className="container-fluid">
                    <a className="navbar-brand" href='/userIndex' style={{ fontFamily: "times new roman", cursor: 'pointer' }}>
                        <FontAwesomeIcon icon={faArrowLeft} style={{ marginRight: '5px' }} />
                        {formData.title} Test
                    </a>
                    <button className="btn btn-primary  active" onClick={downloadAsPDF}>
                        <FontAwesomeIcon icon={faDownload} style={{ marginRight: '5px' }} />
                        Download PDF
                    </button>
                </div>
            </nav>
            <div>
                <main style={{ marginTop: '-1%' }}>
                    <div id="pdf-content" ref={pdfContentRef}>
                        <div className='bootstrap-wrapper' style={{ padding: 15 }}>
                            <div className='card' style={{ padding: 15, borderRadius: '20px', boxShadow: '3px 3px 3px purple' }}>
                                <div className='row'>
                                    <h3 className='text-center'><strong>{formData.title}</strong></h3>
                                    <span className='text-center' style={{ color: 'gray' }}>-: {formData.subject.title} :-</span>
                                    <hr />
                                    <div className='row'>
                                        <div className='col-md-6'>
                                            <h4>Candidate ID: <span>ST0{user && user.id}</span></h4>
                                        </div>
                                        <div className='col-md-6'>
                                            <h4 style={{ textAlign: 'end' }}>Candidate Name: <span>{user && user.username}</span></h4>
                                        </div>
                                    </div>
                                    <div className='row'>
                                        <div className='col-md-6'>
                                            <h4> Email : <span>{user && user.email}</span></h4>
                                        </div>
                                        <div className='col-md-6'>
                                            <h4 style={{ textAlign: 'end' }}>Phone No: <span>{user && user.phoneNo}</span></h4>
                                        </div>
                                    </div>
                                    <hr />
                                    <div className='row'>
                                        <div className='col-md-6'>
                                            <h4> Total Questions : <span>{formData && formData.numberOFQuestions}</span></h4>
                                        </div>
                                        <div className='col-md-6'>
                                            <h4 style={{ textAlign: 'end' }}>Max Marks: <span>{formData && formData.maxMarks}</span></h4>
                                        </div>
                                    </div>
                                    <br />
                                    <br />
                                    <div className='row'>
                                        <div className='col-md-4'>
                                            <h4 style={{ color: 'darkgreen' }}> Correct Answer : <span>{testResult && testResult.correctAnswers}</span></h4>
                                        </div>
                                        <div className='col-md-4'>
                                            <h4 style={{ textAlign: 'center', color: 'red' }}> Wrong Answer : <span>{testResult && testResult.wrongAnswers}</span></h4>
                                        </div>
                                        <div className='col-md-4'>
                                            <h4 style={{ textAlign: 'end', color: 'royalblue' }}> Unattempted Answer : <span>{testResult && testResult.unansweredQuestions}</span></h4>
                                        </div>
                                    </div>
                                    <hr />
                                    <div className='row'>
                                        <h4 className='text-center'>Score: <span style={{ color: 'darkgreen' }}><b>{(parseInt(formData && formData.maxMarks) / parseInt(testResult && testResult.selectedOptions.length)) * parseInt(testResult && testResult.correctAnswers)}</b></span> </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className='bootstrap-wrapper'>
                            {questions.length === 0 ? (
                                <div className='empty-message text-center' style={{ marginTop: '15%' }}>
                                    <h3>No questions found</h3>
                                    <p>There are no questions available for {formData.title} test</p>
                                </div>
                            ) : (
                                questions.map((question, index) => {
                                    const selectedOption = testResult.selectedOptions.find(
                                        (option) => option.questionId === question.quesId
                                    )?.selectedOption;

                                    const isCorrect = selectedOption === question.answer;
                                    const answerStatus = isCorrect ? 'Correct' : selectedOption ? 'Wrong' : 'Unattempted';

                                    return (
                                        <div
                                            className='card'
                                            style={{
                                                padding: 10,
                                                borderRadius: '20px',
                                                borderColor: 'purple',
                                                margin: '20px',
                                            }}
                                            key={question.quesId}
                                        >
                                            <div className='row'>
                                                <div className='col-md-12'>
                                                    <div className='card-content'>
                                                        <h3>
                                                            <b>QNo:- {index + 1}</b> <span>{question.question}</span>
                                                        </h3>
                                                        <hr />
                                                        <div className='container-fluid'>
                                                            <div className='row'>
                                                                <div className='col-md-6'>
                                                                    <h4
                                                                        style={{
                                                                            background:
                                                                                question.option1 === selectedOption
                                                                                    ? isCorrect
                                                                                        ? '#ACE1AF'
                                                                                        : 'red'
                                                                                    : 'azure',
                                                                            color: question.option1 === selectedOption ? 'black' : 'black',
                                                                            padding: 10,
                                                                            borderRadius: '40px',
                                                                            textAlign: 'center',
                                                                            border:
                                                                                question.option1 === selectedOption
                                                                                    ? '2px solid gold'
                                                                                    : '2px solid purple',
                                                                        }}
                                                                    >
                                                                        {question.option1}
                                                                    </h4>
                                                                </div>
                                                                <div className='col-md-6'>
                                                                    <h4
                                                                        style={{
                                                                            background:
                                                                                question.option2 === selectedOption
                                                                                    ? isCorrect
                                                                                        ? '#ACE1AF'
                                                                                        : 'red'
                                                                                    : 'azure',
                                                                            color: question.option2 === selectedOption ? 'black' : 'black',
                                                                            padding: 10,
                                                                            borderRadius: '40px',
                                                                            textAlign: 'center',
                                                                            border:
                                                                                question.option2 === selectedOption
                                                                                    ? '2px solid gold'
                                                                                    : '2px solid purple',
                                                                        }}
                                                                    >
                                                                        {question.option2}
                                                                    </h4>
                                                                </div>
                                                            </div>
                                                            <div className='row'>
                                                                {question.option3 && (
                                                                    <div className='col-md-6'>
                                                                        <h4
                                                                            style={{
                                                                                background:
                                                                                    question.option3 === selectedOption
                                                                                        ? isCorrect
                                                                                            ? '#ACE1AF'
                                                                                            : 'red'
                                                                                        : 'azure',
                                                                                color: question.option3 === selectedOption ? 'black' : 'black',
                                                                                padding: 10,
                                                                                borderRadius: '40px',
                                                                                textAlign: 'center',
                                                                                border:
                                                                                    question.option3 === selectedOption
                                                                                        ? '2px solid gold'
                                                                                        : '2px solid purple',
                                                                            }}
                                                                        >
                                                                            {question.option3}
                                                                        </h4>
                                                                    </div>
                                                                )}
                                                                {question.option4 && (
                                                                    <div className='col-md-6'>
                                                                        <h4
                                                                            style={{
                                                                                background:
                                                                                    question.option4 === selectedOption
                                                                                        ? isCorrect
                                                                                            ? '#ACE1AF'
                                                                                            : 'red'
                                                                                        : 'azure',
                                                                                color: question.option4 === selectedOption ? 'black' : 'black',
                                                                                padding: 10,
                                                                                borderRadius: '40px',
                                                                                textAlign: 'center',
                                                                                border:
                                                                                    question.option4 === selectedOption
                                                                                        ? '2px solid gold'
                                                                                        : '2px solid purple',
                                                                            }}
                                                                        >
                                                                            {question.option4}
                                                                        </h4>
                                                                    </div>
                                                                )}
                                                            </div>
                                                        </div>
                                                        <hr />
                                                        <div className='row'>
                                                            <div className='col-md-6'>
                                                                <div className='row'>
                                                                        <h4>Selected Answer:  <span>{selectedOption || 'Unattempted'}</span></h4>
                                                                </div>
                                                                <hr />
                                                                <div className='row'>
                                                                     <h4>Correct Answer : <span>{question.answer}</span></h4>
                                                                </div>
                                                            </div>
                                                            <div className='col-md-6 text-center'>
                                                                <h4 style={{ color: isCorrect ? 'green' : 'red' }}><b>{answerStatus}</b></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    );
                                })
                            )}
                        </div>
                    </div>
                </main>
            </div>
        </>
    );
}