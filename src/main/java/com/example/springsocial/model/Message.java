package com.example.springsocial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Message class used for messages between users.
 * @author Michael Muzio
 *
 */
@Entity
@Table(name="message")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Message {

	public Message() {
		super();
	}
	
	public Message(long id, String messageBody,
			User messageSender, Match match, Date submitTime) {
		super();
		this.id = id;
		this.messageBody = messageBody;
		this.messageSender = messageSender;
		this.match = match;
		this.submitTime = submitTime;
	}
	
	@JsonCreator
	public Message(@JsonProperty("id") int id, String messageBody,
		 User messageSender, Match match, Date submitTime) {
	
		super();
		Long newId = Long.valueOf(id);
	    this.id = newId;
		this.messageBody = messageBody;
		this.messageSender = messageSender;
		this.match = match;
		this.submitTime = submitTime;
	}

	public Message(String messageBody,
			User messageSender, Match match, Date submitTime) {
		super();
		this.messageBody = messageBody;
		this.messageSender = messageSender;
		this.match = match;
		this.submitTime = submitTime;
	}

	@Id
	@Column(name="message_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@Column(name = "message_text", length = 100)
	private String messageBody;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User messageSender;
	
	@Column(name="submit_time")
	Date submitTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Match match;

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public User getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(User messageSender) {
		this.messageSender = messageSender;
	}
	
	public Date getSubmitTime() {
		return submitTime;
	}
	
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public long getId() {
		return id;
	}
	
}
