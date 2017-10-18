package com.dzire.mailing.sentmail.model;

import java.util.ArrayList;
import java.util.Map;

public class OpenSentMailMessageBean {
	private long id;
	private String email_id;
	private Map<Long,ArrayList<String>> message;
	
	public OpenSentMailMessageBean(){
		//new SentMessage();
	}
	public Map<Long, ArrayList<String>> getMessage() {
		return message;
	}
	public void setMessage(Map<Long, ArrayList<String>> message) {
		this.message = message;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
}
