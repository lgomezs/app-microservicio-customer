package com.example.mongo.exception;

import com.example.mongo.util.ErrorType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends BaseException{

	private final BaseError error;
	
	private static final long serialVersionUID = 1L;
	
	public CustomException(ErrorType errorType) {
		super(errorType.getCode(),errorType.getMessage(),errorType.getTitle());
		error=super.getError();
	}

}
