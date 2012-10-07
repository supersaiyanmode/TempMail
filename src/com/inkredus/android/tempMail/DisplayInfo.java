package com.inkredus.android.tempMail;

import com.example.testmail.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayInfo extends Activity
{
	private TextView from,email,subject;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        
        from = (TextView)findViewById(R.id.fromLabel);
        email = (TextView)findViewById(R.id.emailLabel);
        subject = (TextView)findViewById(R.id.subjectLabel);
        
        from.setText("from me");
        email.setText("email is");
        subject.setText("subject is");
      
	}
	
	
}
