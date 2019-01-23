package com.example.mongo.exception;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private BaseError error;

	public BaseException(String code, String message, String title) {
		error = new BaseError(code, message, title);
	}
}
