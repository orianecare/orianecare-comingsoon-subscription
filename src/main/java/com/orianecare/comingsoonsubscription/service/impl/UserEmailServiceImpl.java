/**
 * 
 */
package com.orianecare.comingsoonsubscription.service.impl;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orianecare.comingsoonsubscription.entity.UserEmailEntity;
import com.orianecare.comingsoonsubscription.repo.UserEmailEntityRepo;
import com.orianecare.comingsoonsubscription.request.SubscriptionRequest;
import com.orianecare.comingsoonsubscription.response.SuccessResponse;
import com.orianecare.comingsoonsubscription.service.EmailSenderSerivce;
import com.orianecare.comingsoonsubscription.service.UserEmailService;

/**
 * @author vennelakanti
 *
 */
@Service
public class UserEmailServiceImpl implements UserEmailService {

	@Autowired
	private UserEmailEntityRepo userEmailEntityRepo; 
	@Autowired
	private EmailSenderSerivce emailSenderService;
	@Override
	public SuccessResponse saveEmail(SubscriptionRequest request) throws MessagingException, IOException {
		
			UserEmailEntity userEmailEntity = new UserEmailEntity();
			userEmailEntity.setEmail(request.getEmail());
			
			userEmailEntityRepo.save(userEmailEntity);
			//emailSenderService.sendSimpleMessage(request.getEmail(), "dummmy email", "dummy text");
			emailSenderService.sendMessageWithAttachment(request.getEmail(), "dummy subject", "dummy body", "dummy.txt");
			return new SuccessResponse(request.getRequestId(), "200", "Email Saved Successfully");
		
	}

}
