/**
 * 
 */
package com.orianecare.comingsoonsubscription.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vennelakanti
 *
 */

@Getter
@Setter
public class SubscriptionRequest {

	
            
	@NotNull(message="RequestId is mandatory")
	@Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$",
	message = "Request Id is not in valid format")
	private String requestId;
	
	@NotNull(message = "Email is mandatory")
	@Email(message = "Not a valid email format", regexp = "^[-A-Za-z0-9~!$%^&*_=+}{\\'?]+(\\.[-A-Za-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
	private String email;
}
