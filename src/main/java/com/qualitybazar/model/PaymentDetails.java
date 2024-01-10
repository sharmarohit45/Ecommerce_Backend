package com.qualitybazar.model;

public class PaymentDetails {
	
	
	private String paymentMethod;
	private String status;
	private String paymentId;
	private String razorpayPayemntLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorPaymentLinkStatus;
	private String razorpayPaymentId;
	
	
	public PaymentDetails() {
		
		// TODO Auto-generated constructor stub
	}
	
	


	public PaymentDetails(String paymentMethod, String status, String paymentId, String razorpayPayemntLinkId,
			String razorpayPaymentLinkReferenceId, String razorPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentId = paymentId;
		this.razorpayPayemntLinkId = razorpayPayemntLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razorPaymentLinkStatus = razorPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}




	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}


	public String getRazorpayPayemntLinkId() {
		return razorpayPayemntLinkId;
	}


	public void setRazorpayPayemntLinkId(String razorpayPayemntLinkId) {
		this.razorpayPayemntLinkId = razorpayPayemntLinkId;
	}


	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}


	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}


	public String getRazorPaymentLinkStatus() {
		return razorPaymentLinkStatus;
	}


	public void setRazorPaymentLinkStatus(String razorPaymentLinkStatus) {
		this.razorPaymentLinkStatus = razorPaymentLinkStatus;
	}


	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}


	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	
	
	
	

}
