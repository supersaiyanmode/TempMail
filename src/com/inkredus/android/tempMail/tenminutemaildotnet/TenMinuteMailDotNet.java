package com.inkredus.android.tempMail.tenminutemaildotnet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import android.util.Log;

import com.google.gson.Gson;
import com.inkredus.android.tempMail.Email;
import com.inkredus.android.tempMail.EmailAgent;
import com.inkredus.android.tempMail.EmailManagerNotReadyException;

public class TenMinuteMailDotNet implements EmailAgent {
    private String emailID;
    private HashMap<String, Email> emails;
    private ArrayList<Email> newEmails;
    private boolean active;
    private Map<String, String> cookies;
    
    public TenMinuteMailDotNet() {
        emails = new HashMap<String, Email>();
        newEmails = new ArrayList<Email>();
        emailID = null;
    }
    
    public void init(){
        try {
            Response r = Jsoup.connect("http://10minutemail.net/address.api.php").method(Method.GET).execute();
            cookies = r.cookies();
            MailGet m = new Gson().fromJson(r.body(), MailGet.class);
            emailID = m.getMail_get_mail();
            active = true;
        }catch (Exception e){
            Log.e("test-debug", "Failed to initialise..",e);
            active = false;
        }
    }
    
    public String getEmailID() throws EmailManagerNotReadyException {
        if (!active || emailID==null)
            throw new EmailManagerNotReadyException();
        return emailID;
    }
    
    public void getMoreMinutes() throws IOException, EmailManagerNotReadyException {
        if (!active)
            throw new EmailManagerNotReadyException();
        Jsoup.connect("http://10minutemail.net/address.api.php?more=1").cookies(cookies).method(Method.GET).execute();
    }
    
    public int unreadCount() throws EmailManagerNotReadyException {
        if (!active)
            throw new EmailManagerNotReadyException();
        return newEmails.size();
    }
    
    public int totalCount() throws EmailManagerNotReadyException{
        if (!active)
            throw new EmailManagerNotReadyException();
        return emails.size();
    }
    
    public void refresh() throws IOException{
        Response r = Jsoup.connect("http://10minutemail.net/mail.api.php")
            .cookies(cookies)
            .method(Method.GET)
            .execute();
        
        Mail[] mails = new Gson().fromJson(r.body(), Mail[].class);
        Log.i("test-debug", "Mail count: " + mails.length);
        for (Mail mail: mails){
            if (!emails.containsKey(mail.getMail_id())){
                Email email = new Email(mail.getMail_id(), mail.getFrom(), mail.getDatetime(), mail.getSubject(), "");
                emails.put(email.getKey(), email);
                newEmails.add(email);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Email> getUnreadMails() {
        ArrayList<Email> ret = null;
        if (newEmails.size() > 0){
            ret = (ArrayList<Email>) newEmails.clone();
            newEmails.clear();
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List<Email> getAllMails() {
        ArrayList<Email> ret = null;
        if (newEmails.size() > 0){
            ret = (ArrayList<Email>) newEmails.clone();
            newEmails.clear();
        }
        return ret;
    }

    public int getUnreadCount() {
        return newEmails.size();
    }

    public int getTotalCount() {
        return emails.size();
    }

    public boolean ready() {
        return active;
    }
}
