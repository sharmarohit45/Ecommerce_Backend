package com.qualitybazar.responce;

public class ApiResponse {
	
	private String jwt;
	private String message;
	private Boolean status;
	public ApiResponse() {
		
	}
	
	
	
	
	public ApiResponse(String jwt, String message, Boolean status) {
		super();
		this.jwt = jwt;
		this.message = message;
		this.status = status;
	}




	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
	

}
