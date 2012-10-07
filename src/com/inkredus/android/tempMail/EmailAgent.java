package com.inkredus.android.tempMail;

import java.io.IOException;
import java.util.List;


public interface EmailAgent {
	void init();
	List<Email> getUnreadMails();
	List<Email> getAllMails();
	int getUnreadCount();
	int getTotalCount();
	void refresh() throws IOException;
	String getEmailID() throws EmailManagerNotReadyException;
	boolean ready();
}
