/**
 * 
 */
package com.orianecare.comingsoonsubscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orianecare.comingsoonsubscription.entity.UserEmailEntity;
import com.orianecare.comingsoonsubscription.repo.UserEmailEntityRepo;
import com.orianecare.comingsoonsubscription.request.SubscriptionRequest;
import com.orianecare.comingsoonsubscription.response.SuccessResponse;

/**
 * @author vennelakanti
 *
 */
@Service
public class UserEmailServiceImpl implements UserEmailService {

	@Autowired
	private UserEmailEntityRepo userEmailEntityRepo; 
	
	@Override
	public SuccessResponse saveEmail(SubscriptionRequest request)  {
		
			UserEmailEntity userEmailEntity = new UserEmailEntity();
			userEmailEntity.setEmail(request.getEmail());
			
			userEmailEntityRepo.save(userEmailEntity);
		
			 return new SuccessResponse(request.getRequestId(), "200", "Email Saved Successfully");
		
	}

}
