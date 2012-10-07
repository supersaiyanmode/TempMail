package com.inkredus.android.tempMail;


public interface EmailListener {
	public void onNewEmail(Email e);
	public void onInitFailed();
	public void onInitDone(String s);
	public void onStartPolling(EmailAgent emailAgent);
	public void onEndPolling();
}
