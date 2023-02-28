package com.rkbansal.videoconferencing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class dashboard_activity extends AppCompatActivity {
   EditText codebox;
   Button joinbutton,sharebutton,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        codebox=findViewById(R.id.codebox);
        joinbutton=findViewById(R.id.joinbutton);
        sharebutton=findViewById(R.id.sharebutton);
        logout=findViewById(R.id.logout);
        URL serverurl;
        try{
            serverurl=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultoptions=new JitsiMeetConferenceOptions.Builder().setServerURL(serverurl).build();
            JitsiMeet.setDefaultConferenceOptions(defaultoptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            JitsiMeetConferenceOptions options =new JitsiMeetConferenceOptions.Builder().setRoom(codebox.getText().toString()).build();
                JitsiMeetActivity.launch(dashboard_activity.this,options);
            }
        });
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=codebox.getText().toString();
                Intent intent =new Intent();
                intent.setAction(intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,string);
                intent.setType("text/plane");
                startActivity(intent);
            }
        });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(dashboard_activity.this,login_activity.class));
                }
            });


    }
}