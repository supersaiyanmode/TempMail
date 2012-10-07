package com.inkredus.android.tempMail.tenminutemaildotnet;

class MailGet {
    public void setMail_get_user(String mail_get_user) {
        this.mail_get_user = mail_get_user;
    }

    public void setMail_get_mail(String mail_get_mail) {
        this.mail_get_mail = mail_get_mail;
    }

    public void setMail_get_host(String mail_get_host) {
        this.mail_get_host = mail_get_host;
    }

    public void setMail_get_time(String mail_get_time) {
        this.mail_get_time = mail_get_time;
    }

    public void setMail_get_duetime(String mail_get_duetime) {
        this.mail_get_duetime = mail_get_duetime;
    }

    public void setMail_get_key(String mail_get_key) {
        this.mail_get_key = mail_get_key;
    }

    public void setMail_left_time(String mail_left_time) {
        this.mail_left_time = mail_left_time;
    }

    public void setMail_recovering_mail(String mail_recovering_mail) {
        this.mail_recovering_mail = mail_recovering_mail;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    private String  mail_get_user, //mz8102
                    mail_get_mail, //mz8102@a.vztc.com
                    mail_get_host, //a.vztc.com
                    mail_get_time, //1349226216
                    mail_get_duetime, //1349233598,
                    mail_get_key, //foApda
                    mail_left_time, //600,
                    mail_recovering_mail, //null
                    session_id; //db7b59ac289cec4bbf81f161fb4bb2bd

    public String getMail_get_user() {
        return mail_get_user;
    }

    public String getMail_get_mail() {
        return mail_get_mail;
    }

    public String getMail_get_host() {
        return mail_get_host;
    }

    public String getMail_get_time() {
        return mail_get_time;
    }

    public String getMail_get_duetime() {
        return mail_get_duetime;
    }

    public String getMail_get_key() {
        return mail_get_key;
    }

    public String getMail_left_time() {
        return mail_left_time;
    }

    public String getMail_recovering_mail() {
        return mail_recovering_mail;
    }

    public String getSession_id() {
        return session_id;
    } 
}
