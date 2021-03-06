/**
 * 
 */
package com.orianecare.comingsoonsubscription.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.orianecare.comingsoonsubscription.entity.UserEmailEntity;
import com.orianecare.comingsoonsubscription.repo.UserEmailEntityRepo;
import com.orianecare.comingsoonsubscription.request.SubscriptionRequest;
import com.orianecare.comingsoonsubscription.response.SuccessResponse;
import com.orianecare.comingsoonsubscription.service.impl.UserEmailServiceImpl;

/**
 * @author vennelakanti
 *
 */
@ExtendWith(MockitoExtension.class)
class UserEmailServiceImplTest {

	private SubscriptionRequest subscriptionRequest;
	
	private UserEmailEntity userEmailEntity;
	@InjectMocks
	private UserEmailServiceImpl userEmailServiceImpl;
	
	@Mock
	private UserEmailEntityRepo userEmailEntityRepo;
	
	@Mock
	private EmailSenderSerivce emailSenderService;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
		subscriptionRequest.setEmail("abc@example.com");
		subscriptionRequest.setRequestId(UUID.randomUUID().toString());
		this.subscriptionRequest=subscriptionRequest;
		
		UserEmailEntity userEmailEntity = new UserEmailEntity();
		userEmailEntity.setEmail("abc@example.com");
		this.userEmailEntity = userEmailEntity;
		
	}

	/**
	 * Test method for {@link com.orianecare.comingsoonsubscription.service.impl.UserEmailServiceImpl#saveEmail(java.lang.String)}.
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@Test
	void testSaveEmail() throws MessagingException, IOException {
		when(userEmailEntityRepo.save(Mockito.any(UserEmailEntity.class))).thenReturn(userEmailEntity);
		doNothing().when(emailSenderService).sendMessageWithAttachment(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		SuccessResponse response= userEmailServiceImpl.saveEmail(subscriptionRequest);
		verify(userEmailEntityRepo).save(Mockito.any(UserEmailEntity.class));
		verify(emailSenderService).sendMessageWithAttachment(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		assertEquals(subscriptionRequest.getRequestId(), response.getResponseId());
		assertEquals("200", response.getStatusCode());
		assertEquals("Email Saved Successfully", response.getMessage());
	}

}
