/**
 * 
 */
package com.orianecare.comingsoonsubscription.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.orianecare.comingsoonsubscription.request.SubscriptionRequest;
import com.orianecare.comingsoonsubscription.response.SuccessResponse;
import com.orianecare.comingsoonsubscription.service.UserEmailService;

/**
 * @author vennelakanti
 *
 */
@ExtendWith(MockitoExtension.class)
class SubscriptionControllerTest {
	
	@InjectMocks
	private SubscriptionController subscriptionController;
	@Mock
	private UserEmailService userEmailService;
	
	private SubscriptionRequest subscriptionRequest;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		this.subscriptionRequest=subscriptionRequest;
	}

	/**
	 * Test method for {@link com.orianecare.comingsoonsubscription.controller.SubscriptionController#saveEmail(com.orianecare.comingsoonsubscription.request.SubscriptionRequest)}.
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@Test
	public void testSaveEmail() throws MessagingException, IOException {
		SuccessResponse successResponse = new SuccessResponse(subscriptionRequest.getRequestId(), "200", "Email Saved Successfully");
		when(userEmailService.saveEmail(subscriptionRequest)).thenReturn(successResponse);
	    ResponseEntity<SuccessResponse>response= subscriptionController.saveEmail(subscriptionRequest);
	    assertEquals(200, response.getStatusCodeValue());
	    assertEquals(subscriptionRequest.getRequestId(),response.getBody().getResponseId());
	    assertEquals("200", response.getBody().getStatusCode());
	    assertEquals("Email Saved Successfully", response.getBody().getMessage());
	}

}
