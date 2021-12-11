package com.orianecare.comingsoonsubscription.exceptionhandling;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
/**
 * @author vennelakanti
 *
 */
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;
	
	@Test
	public void testHandleMethodArgumentNotValidMethodArgumentNotValidExceptionHttpHeadersHttpStatusWebRequest() {
		MockBindingResult bindingResult = new MockBindingResult();
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST,globalExceptionHandler.handleMethodArgumentNotValid(ex, null,HttpStatus.BAD_REQUEST,null).getStatusCode());
	}
	
	@Test
	public void testHandleConstraintViolationException() {
		ConstraintViolationException ex = new ConstraintViolationException(null, null, null);
		ResponseEntity<Object>response= globalExceptionHandler.handleConstraintViolationException(ex);
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode() );
		
	}
	
	@Test
	public void testHandleHttpMessageNotReadable() {
		HttpMessageNotReadableException ex = new HttpMessageNotReadableException(null);
		assertEquals(HttpStatus.BAD_REQUEST, globalExceptionHandler.handleHttpMessageNotReadable(ex, null, null, null).getStatusCode());
	}
	
	@Test
	public void testHandleHttpMediaTypeNotSupported() {
		HttpMediaTypeNotSupportedException ex = new HttpMediaTypeNotSupportedException(null);
		assertEquals(HttpStatus.BAD_REQUEST, globalExceptionHandler.handleHttpMediaTypeNotSupported(ex, null, null, null).getStatusCode());
	}

}
