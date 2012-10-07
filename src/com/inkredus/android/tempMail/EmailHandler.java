package com.inkredus.android.tempMail;

import java.net.SocketTimeoutException;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

public class EmailHandler {
	private EmailAgent emailAgent;
	private EmailListener emailListener;
	private boolean initialised, polling;
	AsyncTask<EmailAgent, Void, Void> asyncTask;
	
	public EmailHandler(EmailAgent emailAgent, EmailListener emailListener){
		this.emailAgent = emailAgent;
		this.emailListener = emailListener;
		initialised = false;
		polling = false;
		asyncTask = null;
	}
	
	public void init(){
		(new AsyncTask<EmailAgent, Void, Void>(){
			private EmailAgent agent;
			
			@Override
			protected Void doInBackground(EmailAgent... params) {
				agent = params[0];
				agent.init();
				initialised = true;
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				try {
					Log.i("test-debug","Email generated: " + agent.getEmailID());
					emailListener.onInitDone(agent.getEmailID());
					initialised = true;
					startPolling();
				} catch (EmailManagerNotReadyException e) {
					Log.i("test-debug", "Unable to Initialise Email");
					emailListener.onInitFailed();
					e.printStackTrace();
					initialised = false;
				}
			}
		}).execute(emailAgent);
	}
	
	public void startPolling(){
		if (!initialised)
			return;
		polling = true;
		
		new AsyncTask<EmailAgent, Void, Void>(){
			@Override
			protected void onPreExecute(){
			    asyncTask = this;
				emailListener.onStartPolling(emailAgent);
			}
			@Override
			protected Void doInBackground(EmailAgent... params){
				EmailAgent emailAgent = params[0];
				try {
					while (polling && emailAgent.getUnreadCount() == 0){
						try{
							Log.i("test-debug", "Checking for mails..");
							emailAgent.refresh();
						}catch(SocketTimeoutException e){
							
						}
			 			Thread.sleep(5000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;  //don't interact with the UI
		 	}
			
			@Override
			protected void onPostExecute(Void obj){
				if (emailAgent.getUnreadCount() > 0){
					Log.i("test-debug", "Got " + emailAgent.getUnreadCount() + " emails.");
					for (Email e: emailAgent.getUnreadMails()){
						emailListener.onNewEmail(e);
					}
				}
				startPolling(); // start over!
			}
		}.execute(emailAgent);
	}
	
	public void stopPolling(){
	    if (!polling)
	        return;
		polling = false;
		if(asyncTask != null) {
		    asyncTask.cancel(true);
		    asyncTask = null;
		}
		emailListener.onEndPolling();
	}
	
	public List<Email> getUnreadMails(){
		return emailAgent.getUnreadMails();
	}
	
	public List<Email> getAllMails(){
		return emailAgent.getAllMails();
	}
	
	public int getUnreadCount(){
		return emailAgent.getUnreadCount();
	}
	
	public int getTotalCount(){
		return emailAgent.getTotalCount();
	}

    public String getEmailID()  {
        try {
            return emailAgent.getEmailID();
        } catch (EmailManagerNotReadyException e) {
            e.printStackTrace();
            return null;
        }
    }
}
