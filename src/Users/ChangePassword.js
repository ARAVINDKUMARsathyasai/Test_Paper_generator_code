import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import { useNavigate, useParams, Link } from 'react-router-dom';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';

export default function ChangePassword() {
  const navigate = useNavigate();
  const { id } = useParams();

  const userId = localStorage.getItem('userId');

  const [user, setUser] = useState({
    username: '',
    email: '',
    phoneNo: '',
    password: ''
  });

  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const [showOldPassword, setShowOldPassword] = useState(false);
  const [showNewPassword, setShowNewPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const [passwordError, setPasswordError] = useState('');
  const [confirmPasswordError, setConfirmPasswordError] = useState('');
  const [oldPasswordError, setOldPasswordError] = useState('');

  const loadUser = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/Admin/user/${userId}`);
      setUser(result.data);
      console.log(result);
    } catch (error) {
      console.log(error);
    }
  };
  const { username, email, phoneNo, password } = user;

  useEffect(() => {
    loadUser();
  }, []);

  const handleClick = () => {
    navigate('/');
    localStorage.clear();
    window.location.reload();
  };

  function menuToggle() {
    const toggleMenu = document.querySelector('.menu');
    const toggleDiv = document.querySelector('.info');
    const togglePic = document.querySelector('.ppp');
    toggleMenu.classList.toggle('active');
    toggleDiv.classList.toggle('active');
    togglePic.classList.toggle('active');
  }

  const validatePassword = (password) => {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{8,}$/;
    return passwordRegex.test(password);
  };

  const handleOldPasswordChange = (e) => {
    const oldPasswordValue = e.target.value;
    setOldPassword(oldPasswordValue);

    if (oldPasswordValue !== password) {
      setOldPasswordError('Old password is incorrect');
    } else {
      setOldPasswordError('');
    }
  };

  const handleNewPasswordChange = (e) => {
    const newPasswordValue = e.target.value;
    setNewPassword(newPasswordValue);

    if (!validatePassword(newPasswordValue)) {
      setPasswordError(
        'Password should have at least 1 capital letter, 1 lowercase letter, 1 digit, 1 special character, and minimum 8 characters'
      );
    } else {
      setPasswordError('');
    }
  };

  const handleConfirmPasswordChange = (e) => {
    const confirmPasswordValue = e.target.value;
    setConfirmPassword(confirmPasswordValue);

    if (confirmPasswordValue !== newPassword) {
      setConfirmPasswordError("New password and confirm password don't match");
    } else {
      setConfirmPasswordError('');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (newPassword !== confirmPassword) {
      setConfirmPasswordError("New password and confirm password don't match");
      return;
    } else {
      setConfirmPasswordError('');
    }

    if (!validatePassword(newPassword)) {
      setPasswordError(
        'Password should have at least 1 capital letter, 1 lowercase letter, 1 digit, 1 special character, and minimum 8 characters'
      );
      return;
    } else {
      setPasswordError('');
    }

    try {
      const response = await axios.put(`http://localhost:8080/Admin/user/${userId}`, {
        ...user,
        password: newPassword
      });
      navigate('/userIndex');
      console.log(response);

    } catch (error) {
      console.log(error);
    }
  };

  if (!userId) {
    return (
      <div className='container text-center'>
        <img
          src={require('../img/padlock.jpg')}
          style={{ height: '250px', marginTop: '2%', borderRadius: '50%', background: 'purple', padding: 2 }}
          alt="Padlock"
        />
        <br></br>
        <br></br>
        <h1>Access Denied</h1>
        <h5 style={{ color: '#888888' }}>If you want to access this page login </h5>
        <div className='container ' style={{ display: 'inline' }}>
          <Link to={'/'} className='btn btn-warning' style={{ marginRight: '40px', marginTop: '20px' }}>
            Home
          </Link>
          <Link to={'/userLogin'} className="btn btn-primary" style={{ marginTop: '20px' }}>
            Login
          </Link>
        </div>
      </div>
    );
  }

  const isSubmitDisabled = confirmPasswordError || passwordError || oldPasswordError;

  return (
    <>
      <input type="checkbox" id="nav-toggle" />
      <div className="sidebar">
        <div className="sidebar-brand">
          <h2>
            <span>
              <img src='/capstone.png' alt='Capstone Logo' width='30px' height='30px' style={{ borderRadius: '50%', border: '2px solid gold' }} />
            </span>

            <span>TPG</span>
          </h2>
        </div>
        <div className="sidebar-menu">
          <ul>
            <li>
              <a href="/activeTests">
                <span className="fa-solid fa-file-alt"></span>
                <span>Active Test</span>
              </a>
            </li>
            <li>
              <a href="/userReport">
                <span className="fas fa-book"></span>
                <span>Test Reports</span>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div className="main-content">
        <header>
          <h2>
            <label htmlFor="nav-toggle">
              <span className="las la-bars">
                <span>Change Password</span>
              </span>
            </label>
          </h2>
          <div className="user-wrapper">
            <img onClick={menuToggle} src='/capstone.png' alt='Capstone Logo' width='45px' height='45px' className='ppp' />
            <div onClick={menuToggle} className="info">
              <h6>{user.username.substring(0, user.username.indexOf(' '))}</h6>
              <small>User</small>
            </div>
          </div>
        </header>

        <main>
          <div className='card'  style={{width:'70%',marginLeft:'15%',padding:30,borderRadius:'20px',boxShadow:'5px 5px 5px purple'}}>
            <h4 className='text-center'><strong>Change Password</strong></h4>
          <form onSubmit={handleSubmit}>
            <div className="form-group mb-2">
              <label htmlFor="oldPassword"><strong>Old Password</strong></label>
              <div className="input-group mt-2">
                <input
                  type={showOldPassword ? 'text' : 'password'}
                  id="oldPassword"
                  value={oldPassword}
                  placeholder='Enter Old Password'
                  onChange={handleOldPasswordChange}
                  className="form-control"
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <i
                      className={`fa ${showOldPassword ? 'fa-eye-slash' : 'fa-eye'} form-control`}
                      onClick={() => setShowOldPassword(!showOldPassword)}
                    ></i>
                  </div>
                </div>
              </div>
              {oldPasswordError && <p className="error-message" style={{ color: 'red' }}>{oldPasswordError}</p>}
            </div>
            <div className="form-group">
              <label htmlFor="newPassword"><strong>New Password</strong></label>
              <div className="input-group mt-2">
                <input
                  type={showNewPassword ? 'text' : 'password'}
                  id="newPassword"
                  value={newPassword}
                  onChange={handleNewPasswordChange}
                  className="form-control"
                  placeholder='Enter New Password'
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <i
                      className={`fa ${showNewPassword ? 'fa-eye-slash' : 'fa-eye'} form-control` }
                      onClick={() => setShowNewPassword(!showNewPassword)}
                    ></i>
                  </div>
                </div>
              </div>
              {passwordError && <p className="error-message" style={{ color: 'red' }}>{passwordError}</p>}
              {newPassword && validatePassword(newPassword) && (
                <p style={{ color: 'green' }}>✓ Password requirements fulfilled</p>
              )}
            </div>
            <div className="form-group mb-3">
              <label htmlFor="confirmPassword"><strong>Confirm Password</strong></label>
              <div className="input-group mt-2">
                <input
                  type={showConfirmPassword ? 'text' : 'password'}
                  id="confirmPassword"
                  value={confirmPassword}
                  placeholder='Confirm Password'
                  onChange={handleConfirmPasswordChange}
                  className="form-control"
                  required
                />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <i
                      className={`fa ${showConfirmPassword ? 'fa-eye-slash' : 'fa-eye'} form-control`}
                      onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                    ></i>
                  </div>
                </div>
              </div>
              {confirmPasswordError && <p className="error-message" style={{ color: 'red' }}>{confirmPasswordError}</p>}
              {confirmPassword && confirmPassword === newPassword && (
                <p style={{ color: 'green' }}>✓ Passwords match</p>
              )}
            </div>
            <button type="submit" className='btnn btn-danger' disabled={isSubmitDisabled}>
              Change Password
            </button>
          </form>
          </div>
          <div className="cont">
            <div className="menu">
              <h3>
                USER <br />
                <span>{user.username}</span>
              </h3>
              <ul>
                <li>
                  <img src={require('../img/homee.png')} alt="Home Icon" />
                  <a href="/userIndex">Home</a>
                </li>
                <li>
                  <img src={require('../img/resetPassword.png')} alt="Reset Password Icon" />
                  <Link to={`/changePassword/${userId}`}>Change Password</Link>
                </li>
                <li>
                  <img src={require('../img/SignOut.png')} alt="Sign Out Icon" />
                  <a onClick={handleClick}>Sign Out</a>
                </li>
              </ul>
            </div>
          </div>
        </main>
      </div>
    </>
  );
}