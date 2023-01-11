package com.app.employee.payload.response;

/*Method used to generate the API message response when a user gets registered or gets signed in*/
public class MessageResponse {
	
	private String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
