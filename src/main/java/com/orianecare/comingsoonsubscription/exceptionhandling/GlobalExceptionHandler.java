/**
 * 
 */
package com.orianecare.comingsoonsubscription.exceptionhandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.orianecare.comingsoonsubscription.constants.SubscriptionServiceConstants;
import com.orianecare.comingsoonsubscription.response.ErrorResponse;
import com.orianecare.comingsoonsubscription.response.ErrorResponseWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vennelakanti
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	      //Get all errors
	      List<String> message = ex.getBindingResult()
	              .getFieldErrors()
	              .stream()
	              .map(x -> x.getDefaultMessage())
	              .collect(Collectors.toList());
	      List<ErrorResponse> errorResponse = new ArrayList<ErrorResponse>();
	      message.forEach(msg->errorResponse.add(new ErrorResponse(SubscriptionServiceConstants.METHODARGUMENTNOTVALIDEXCEPTIONCODE,msg)));
	    
	      return new ResponseEntity<>(new ErrorResponseWrapper(errorResponse), headers, status);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e){
		ErrorResponse response = new ErrorResponse(SubscriptionServiceConstants.CONSTRAINTVIOLATIONEXCEPTIONCODE, SubscriptionServiceConstants.CONSTRAINTVIOLATIONEXCEPTIONMESSAAGE);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}



	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse response = new ErrorResponse(SubscriptionServiceConstants.HTTPMESSAGENOTREADABLEEXCEPTIONCODE, SubscriptionServiceConstants.HTTPMESSAGENOTREADABLEEXCEPTIONMESSAGE);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse response = new ErrorResponse(SubscriptionServiceConstants.HTTPMEDIATYPENOTSUPPORTEDEXCEPTIONCODE, SubscriptionServiceConstants.HTTPMEDIATYPENOTSUPPORTEDEXCEPTIONMESSAGE);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MessagingException.class)
	public void handleMessagingException(MessagingException ex){
	
		log.error(ex.getMessage());

	}
	@ExceptionHandler(IOException.class)
	public void handleIOException(IOException ex){
		log.error(ex.getMessage());
	}
	
}
