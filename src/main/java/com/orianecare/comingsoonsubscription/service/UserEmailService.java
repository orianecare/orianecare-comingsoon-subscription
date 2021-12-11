/**
 * 
 */
package com.orianecare.comingsoonsubscription.service;

import com.orianecare.comingsoonsubscription.request.SubscriptionRequest;
import com.orianecare.comingsoonsubscription.response.SuccessResponse;

/**
 * @author vennelakanti
 *
 */
public interface UserEmailService {

	public SuccessResponse saveEmail(SubscriptionRequest request);
	
}
