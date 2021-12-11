/**
 * 
 */
package com.orianecare.comingsoonsubscription.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vennelakanti
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {

	private String responseId;
	private String statusCode;
	private String message;
}
