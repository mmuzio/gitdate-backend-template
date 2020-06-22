package com.example.springsocial.model;

import java.util.Date;

/**
 * RequestMessage class used when user makes a request to add a new message.
 * @author Michael Muzio
 *
 */
public class RequestMessage {
	
	public RequestMessage() {
		super();
	}
	
	public RequestMessage(long message_id, String messageBody,
			String username, long match_id, Date submitTime) {
		super();
		this.message_id = message_id;
		this.messageBody = messageBody;
		this.username = username;
		this.match_id = match_id;
		this.submitTime = submitTime;
	}

    private long message_id;
	
	private String messageBody;
	
	private String username;
	
	Date submitTime;
	
	private long match_id;

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getSubmitTime() {
		return submitTime;
	}
	
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public long getMessage_id() {
		return message_id;
	}
	
	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
	
	public long getMatch_id() {
		return match_id;
	}
	
	public void setMatch_id(long match_id) {
		this.match_id = match_id;
	}
	

}
