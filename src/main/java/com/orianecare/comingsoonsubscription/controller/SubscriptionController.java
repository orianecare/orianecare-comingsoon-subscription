/**
 * 
 */
package com.orianecare.comingsoonsubscription.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.orianecare.comingsoonsubscription.request.SubscriptionRequest;
import com.orianecare.comingsoonsubscription.response.SuccessResponse;
import com.orianecare.comingsoonsubscription.service.UserEmailService;

/**
 * @author vennelakanti
 *
 */
@CrossOrigin
@RestController
public class SubscriptionController {
	
	@Autowired
	private UserEmailService userEmailService;

	@PostMapping(path = "/subscription")
	public ResponseEntity<SuccessResponse> saveEmail(@Valid @RequestBody SubscriptionRequest request) throws MessagingException, IOException{
		
		return ResponseEntity.ok(userEmailService.saveEmail(request));
	}
}
