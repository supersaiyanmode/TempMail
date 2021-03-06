package com.inkredus.android.tempMail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testmail.R;
import com.inkredus.android.tempMail.tenminutemaildotnet.TenMinuteMailDotNet;

public class MainActivity extends Activity implements EmailListener {
    private Button btnGetNewEmail, btnCopyToClipboard, btnExit;
    private TextView textView;
    private ListView lView;
    private EmailHandler emailHandler;
    private NotificationManager notificationManager;
    private ClipboardManager clipboard;
    
    private static final int NOTIFICATION_ONGOING_MAIL_POLL = 1;
    private static final int NOTIFICATION_NEW_MAIL = 2;
    
    ArrayAdapter<Email> adapter;
    
    private void init() {
        setContentView(R.layout.activity_main);
        lView = (ListView) findViewById(R.id.list);
        btnGetNewEmail = (Button) findViewById(R.id.button1);
        btnCopyToClipboard = (Button) findViewById(R.id.button2);
        btnExit = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView1);
        
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        
        emailHandler = new EmailHandler(new TenMinuteMailDotNet(), this);
        
        btnGetNewEmail.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                generateNewEmail();
            }
        });
        
        btnCopyToClipboard.setEnabled(false);
        btnCopyToClipboard.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String copyText = emailHandler.getEmailID();
                clipboard.setText(copyText);
                Log.i("String copied", copyText);
                Toast.makeText(getApplicationContext(),
                        "Copied to clip board..!", Toast.LENGTH_LONG).show();
                // go to new Activity and pass the clipboard text
                Intent i = new Intent(MainActivity.this, DisplayInfo.class);
                startActivity(i);
            }
        });
        
        btnExit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                exit();
            }
        });
        
        final List<Email> list = new ArrayList<Email>();
        final MainActivity that = this;
        adapter = new ArrayAdapter<Email>(this, R.layout.list_layout, list) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                if (convertView == null) {
                    LayoutInflater inflator = that.getLayoutInflater();
                    view = inflator.inflate(R.layout.list_layout, null);
                    view.setTag(list.get(position));
                } else {
                    view = convertView;
                }
                
                Email email = (Email)view.getTag();
                ((TextView)view.findViewById(R.id.idfrom)).setText(email.getFrom());
                ((TextView)view.findViewById(R.id.idsub)).setText(email.getSubject());
                return view;
            }
        };
        lView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setNotifyOnChange(true);
        lView.refreshDrawableState();
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // arg2 is the listViews Selected index
                Email curEmail = (Email) arg1.getTag();
                //Log.i("email body", "" + emailBody);
                
                Intent i = new Intent(MainActivity.this, DisplayInfo.class);
                i.putExtra("from", curEmail.getFrom());
                i.putExtra("subject", curEmail.getSubject());
                i.putExtra("body", curEmail.getBody());
                startActivity(i);
            }
        });
        
    }
    
    protected void generateNewEmail() {
        if (checkInternetConnection()) {
            btnGetNewEmail.setText("Generating temporary email..");
            btnGetNewEmail.setEnabled(false);
            emailHandler.stopPolling();
            emailHandler.init();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Unable to contact server..", Toast.LENGTH_LONG).show();
            btnGetNewEmail.setText("Get new email");
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    private void exit() {
        emailHandler.stopPolling();
        notificationManager.cancelAll();
        System.exit(0);
    }
    
    public void onNewEmail(Email e) {
        String from, subject, emailBody;
        Log.i("test-debug", "MainActivity: new email..!" + e);
        
        // textView.setText("" + textView.getText() + e);
        
        Notification notification = new Notification(R.drawable.ic_launcher,
                "New Email", System.currentTimeMillis());
        // int icon = R.drawable.notification_icon; //app image icon
        // notification.flags=Notification.FLAG_ONGOING_EVENT;
        from = e.getFrom();
        subject = e.getSubject();
        emailBody = e.getBody();
        
        Log.i("Got the mail from", "" + from);
        Log.i("Got the mail subject", "" + subject);
        Log.i("Got the mail content", "" + emailBody);
        
        adapter.add(e);
        adapter.notifyDataSetChanged();
        lView.refreshDrawableState();
        
        Intent notificationIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(getApplicationContext(),
                e.getSubject(), e.getBody(), contentIntent);
        notificationManager.notify(NOTIFICATION_NEW_MAIL, notification);
        
    }
    
    public void onInitFailed() {
        textView.setText("Unable to initialise. Try again later.");
        btnGetNewEmail.setEnabled(true);
        btnGetNewEmail.setText("Get new email");
    }
    
    public void onInitDone(String s) {
        btnCopyToClipboard.setEnabled(true);
        btnGetNewEmail.setEnabled(true);
        btnGetNewEmail.setText("Get new email");
        textView.setText("Temporary Email: " + s);
    }
    
    public void onStartPolling(EmailAgent e) {
        Log.i("test-debug", "Polling started Main UI notified!");
        String emailID;
        try {
            emailID = e.getEmailID();
        } catch (Exception e1) {
            emailHandler.stopPolling();
            return;
        }
        Notification notification = new Notification(R.drawable.ic_launcher,
                "Waiting for email (" + emailID + ")",
                System.currentTimeMillis());
        // int icon = R.drawable.notification_icon; //app image icon
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        Intent notificationIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(getApplicationContext(),
                "Waiting for email..", emailID, contentIntent);
        notificationManager
                .notify(NOTIFICATION_ONGOING_MAIL_POLL, notification);
    }
    
    public void onEndPolling() {
        Log.i("test-debug", "Polling stopped Main UI notified!");
        notificationManager.cancel(NOTIFICATION_ONGOING_MAIL_POLL);
    }
    
    private boolean checkInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();
        boolean gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        
        if (is3g || isWifi) {
            Log.v("Connect", "3G/WIFI connection is present");
            return true;
        } else if (gprs) {
            Log.v("Connect", "gprs connection is present");
            return true;
        }
        return false;
    }
    
    @Override
    public void onConfigurationChanged(Configuration c) {
        setContentView(R.layout.activity_main);
    }
} // end class
