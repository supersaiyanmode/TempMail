package com.inkredus.android.tempMail.tenminutemaildotnet;

public class Mail {
    private String  mail_id, //7bsNP1
                    from, //":"Chen Fish",
                    datetime, //2012-10-03 05:36:08",
                    subject; //No subject

    public String getMail_id() {
        return mail_id;
    }

    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
