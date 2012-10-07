package com.inkredus.android.tempMail;

public class Email {
	private String from;
	private String date;
	private String subject;
	private String body;
	private String key;
	
	public Email(String key, String from, String date, String subject, String body){
		this.from = from;
		this.date = date;
		this.body = body;
		this.subject = subject;
		this.key = key;
	}

	public String getFrom() {
		return from;
	}

	public String getDate() {
		return date;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
	
	public String getKey() {
	    return key;
	}
	
	public String toString(){
		return "" + from + " | " + subject + " | " + date + " | " + body;
	}
}
