package com.testpaper.Exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

/**
 * The UserNotFoundAdvice class is a global exception handler for the UserNotFoundException.
 * It provides a centralized mechanism for handling UserNotFoundExceptions thrown by controllers.
 * 
 * @see UserNotFoundException
 * 
 * @author TPG team
 * @since 21-06-2023
 * @version 17
*/
@ControllerAdvice
public class UserNotFoundAdvice {
	/**
	 * Handles the UserNotFoundException and returns a custom error response.
	 * 
	 * @param exception The UserNotFoundException instance.
	 * @return A map containing the error message.
    */
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String,String> exceptionHandler(UserNotFoundException exception){
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", exception.getMessage());
		
		return errorMap;
	}
}
