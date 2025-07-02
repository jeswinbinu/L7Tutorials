package com.litmus7.rental.dto;

public enum ResponseStatus {
	SUCCESS_STATUS_CODE(200),
	ERROR_STATUS_CODE(500);

	private final int code;

	ResponseStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}