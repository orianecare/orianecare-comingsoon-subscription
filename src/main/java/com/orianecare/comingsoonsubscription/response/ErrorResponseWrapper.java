package com.orianecare.comingsoonsubscription.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseWrapper {

	List<ErrorResponse> errors;
}
