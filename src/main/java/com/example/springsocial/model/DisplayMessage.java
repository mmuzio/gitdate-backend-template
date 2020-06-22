package com.example.springsocial.model;

import java.util.Date;

/**
 * DisplayMessage class used for displaying messages to users. <br>
 * Could potentially be the superclass of Message.
 * @author Michael Muzio
 *
 */
public class DisplayMessage {
	
	String messageBody;
	
	String username;
	
	Date submitTime;

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public DisplayMessage(String messageBody, Date submitTime, String username) {
		super();
		this.messageBody = messageBody;
		this.submitTime = submitTime;
		this.username = username;
	}

	public DisplayMessage() {
		super();
	}
	
	

}
