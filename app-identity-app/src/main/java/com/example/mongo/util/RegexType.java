package com.example.mongo.util;

import lombok.Getter;

@Getter
public enum RegexType {

	REGEX_WRONG_PHONE_FORMAT("^[9]\\d{8}(?:[-\\s]\\d{4})?$"),
	REGEX_WRONG_DNI_FORMAT("^\\d{8}(?:[-\\s]\\d{4})?$"),
	REGEX_WRONG_PASSWORD_LENGTH("^[\\s\\S]{8,}$"),
	REGEX_WRONG_PASSWORD_HAS_NUMBER("^(?=.*\\d).{8,}"),
	REGEX_WRONG_PASSWORD_HAS_SPECIAL_CHARACTERS("^[a-zA-Z0-9ñÑ]*$"),
	REGEX_WRONG_PASSWORD_HAS_LETTER("^(?=.*?[a-zA-Z]).{8,}");
	
	private String regex;
	
	private RegexType(String regex) {
		this.regex = regex;
	}
	
}
