package com.example.springsocial.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Match class used for matches between users.
 * @author Michael Muzio
 *
 */
@Entity
@Table(name="match")
public class Match {
	
	public Match() {
		super();
	}

	public Match(long id, List<Message> messages, List<User> users) {
	
		super();
		this.id = id;
		this.messages = messages;
		this.users = users;
	}
	
	public Match(long id) {
		super();
		this.id = id;
	}
	
	@JsonCreator
	public Match(@JsonProperty("id") int id) {
		super();
		Long newId = Long.valueOf(id);
	    this.id = newId;
	}
	
	public Match(List<Message> messages, 
			List<User> users) {
	
		super();
		this.messages = messages;
		this.users = users;
	}
	
	public Match(String requester, boolean isAccepted) {
		this.requester = requester;
		this.isAccepted = isAccepted;
	}
	
	@Id
	@Column(name="match_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@Column(name="requester")
	String requester;
	
	@Column(name="isAccepted")
	boolean isAccepted;
	
	@OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Message> messages;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
            name = "Match_Users",
            joinColumns = {@JoinColumn(name = "match_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
	@JsonIgnore
    private List<User> users = new ArrayList<User>();
	
	public List<User> getUsers() {
		
        return users;
        
    }
	
	public void addUser(User user) {
		
		this.users.add(user);
		
	}
	
	public void setUsers(List<User> users) {
		
        this.users = users;
        
    }

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public long getId() {
		return id;
	}
	
	public String getRequester() {
		return requester;
	}
	
	public void setRequester(String requester) {
		this.requester = requester;
	}
	
	public boolean getIsAccepted() {
		return isAccepted;
	}
	
	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

}
