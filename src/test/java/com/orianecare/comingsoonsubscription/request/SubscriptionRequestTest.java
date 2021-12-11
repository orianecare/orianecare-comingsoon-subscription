/**
 * 
 */
package com.orianecare.comingsoonsubscription.request;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vennelakanti
 *
 */
class SubscriptionRequestTest {
	String requestIdMessage="RequestId is mandatory";
	String requestIdNotValidMessage="Request Id is not in valid format";
	String notValidFormat="Not a valid email format";
	String cannotBeBlank = "Email is mandatory";
	
	private SubscriptionRequest subscriptionRequest;
	private Validator validator;
	Set<ConstraintViolation<SubscriptionRequest>> violations; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		subscriptionRequest = new SubscriptionRequest();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        violations = null;
	}

	@Test
	public void testWhenEmailIsNull() {
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
	   
		violations.forEach(constraintViolation->assertEquals(cannotBeBlank, constraintViolation.getMessage()));
		
		
	}
	@Test
	public void testWhenEmailIsEmptyString() {
		subscriptionRequest.setEmail("");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat , constraintViolation.getMessage()));
		
	}
	@Test
	public void testWhenEmailIsBlank() {
		subscriptionRequest.setEmail("  ");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat , constraintViolation.getMessage()));
		//violations.forEach(constraintViolation->System.out.println(constraintViolation.getMessage()));
		
	}
	@Test
	public void testWhenEmailFormatisNotValid_1() {
		subscriptionRequest.setEmail("123");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenEmailFormatisNotValid_2() {
		subscriptionRequest.setEmail("123@");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenEmailFormatisNotValid_3() {
		subscriptionRequest.setEmail("123@.");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenEmailFormatisNotValid_4() {
		subscriptionRequest.setEmail("123@123.");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	@Test
	public void testWhenEmailFormatisNotValid_5() {
		subscriptionRequest.setEmail("@123.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenEmailFormatisNotValid_6() {
		subscriptionRequest.setEmail(" @123.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	@Test
	public void testWhenEmailFormatisNotValid_7() {
		subscriptionRequest.setEmail("#@123.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenEmailFormatisNotValid_8() {
		subscriptionRequest.setEmail("@@123.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenEmailFormatisNotValid_9() {
		subscriptionRequest.setEmail("a|b@example.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		System.out.println(violations);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	@Test
	public void testWhenEmailFormatisNotValid_10() {
		subscriptionRequest.setEmail("a`b@example.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		System.out.println(violations);
		violations.forEach(constraintViolation->assertEquals(notValidFormat, constraintViolation.getMessage()));
		
	}
	@Test
	public void testWhenEmailFormatisValid() {
		subscriptionRequest.setEmail("abc@examplecom");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		assertEquals(1, violations.size());
		
	}
	
	@Test
	public void testWhenRequestIdIsNull() {
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(requestIdMessage, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenRequestIdIsEmpty() {
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId("");
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(requestIdNotValidMessage, constraintViolation.getMessage()));
		
	}
	
	@Test
	public void testWhenRequestIdIsWhitespace() {
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId("  ");
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(requestIdNotValidMessage, constraintViolation.getMessage()));	
	}
	@Test
	public void testWhenRequestIdIs_InvalidFormat() {
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString()+"abc");
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(requestIdNotValidMessage, constraintViolation.getMessage()));	
	}
	@Test
	public void testWhenRequestIdIs_InvalidFormat_1() {
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId("a3deb03g-2e25-48e3-815e-1c40fcd4c8a9");
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(requestIdNotValidMessage, constraintViolation.getMessage()));	
	}
	
	@Test
	public void testWhenRequestIdIs_InvalidFormat_2() {
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId("a3deb03c2e25-48e3-815e-1c40fcd4c8a9");
		violations = validator.validate(subscriptionRequest);
		violations.forEach(constraintViolation->assertEquals(requestIdNotValidMessage, constraintViolation.getMessage()));	
	}
}
