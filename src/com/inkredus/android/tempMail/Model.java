package com.inkredus.android.tempMail;

public class Model 
{
	 
    private String from;
	private String subject;
  
    public Model(String subject , String from) 
    {
        this.from = from;
        this.subject = subject;
    }
 
    public String getEmailFrom() 
    {
        return from;
    }
 
    public String getEmailSubject() 
    {
        return subject;
    }
    
    public void setEmailFrom(String from) 
    {
        this.from = from;
    }
 
    public void setEmailSubject(String subject)
    {
        this.subject = subject;
    }
 
}
