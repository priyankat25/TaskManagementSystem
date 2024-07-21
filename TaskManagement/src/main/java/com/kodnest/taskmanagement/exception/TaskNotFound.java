package com.kodnest.taskmanagement.exception;

@SuppressWarnings("serial")
public class TaskNotFound extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String message;
	
	public TaskNotFound(String message) {
		super(message);
		this.message = message;
	}
}




