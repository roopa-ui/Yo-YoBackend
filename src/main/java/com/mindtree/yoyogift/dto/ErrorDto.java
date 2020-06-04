package com.mindtree.yoyogift.dto;

public class ErrorDto {
	
	private String message;
	private Throwable cause;

	public ErrorDto() {
	}

	public ErrorDto(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}


}
