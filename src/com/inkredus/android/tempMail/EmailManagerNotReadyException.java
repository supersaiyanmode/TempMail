package com.inkredus.android.tempMail;

public class EmailManagerNotReadyException extends Exception {
    private static final long serialVersionUID = -1000920445795641692L;

    public EmailManagerNotReadyException() {
		// TODO Auto-generated constructor stub
	}

	public EmailManagerNotReadyException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public EmailManagerNotReadyException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	public EmailManagerNotReadyException(String detailMessage,
			Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}
}
