package com.inkredus.android.tempMail;


import android.app.Activity;
import android.os.Bundle;
import com.example.testmail.R;
import android.widget.TextView;


public class DisplayInfo extends Activity
{
	private TextView from,email,subject;
	String fromcontent,subjectcontent,emailcontent;
	private Bundle b;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        
        b=getIntent().getExtras();
        fromcontent=b.getString("from").toString();
        subjectcontent=b.getString("subject").toString();
        emailcontent=b.getString("body").toString();
        
        from = (TextView)findViewById(R.id.fromLabel);
        email = (TextView)findViewById(R.id.emailLabel);
        subject = (TextView)findViewById(R.id.subjectLabel);
        
        from.setText("From : " +fromcontent);
        email.setText("Email content :" +emailcontent);
        subject.setText("Subject :" +subjectcontent);
       
      
	}
	

	
}
