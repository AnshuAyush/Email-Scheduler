package com.example.prombuzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
//import com.creativityapps.gmailbackgroundlibrary.util.GmailSender;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class sendemailandschedular extends AppCompatActivity {
    EditText to , cc , subject;
    Button send;
    RadioButton recurring , weekly , monthly ,yearly;
    GMailSender gmailSender;
    ListView listView;
//    String user = "kevinloftxxx@gmail.com";
//    String password = "xxxkevinloft";
    String user;
    String password;
    String sb;
    String bd;
    String rp;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendemailandschedular);




        // variables
        to = findViewById(R.id.to);
        cc= findViewById(R.id.cc);
        subject = findViewById(R.id.subject);
        send = findViewById(R.id.send);
        recurring = findViewById(R.id.recurring);
        weekly = findViewById(R.id.weekly);
        monthly = findViewById(R.id.Monthly);
        yearly = findViewById(R.id.Yearly);
        EditText body = findViewById(R.id.body);
        TextView body_tittle_small = findViewById(R.id.body_text_small);
        TextView sehedule_small = findViewById(R.id.scheduler_small);
        LottieAnimationView done = findViewById(R.id.done);
        TextView bold_text = findViewById(R.id.bold_upper_text);







        // Select Scheduler
        recurring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 id = R.id.recurring;
            }
        });

        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.weekly;
            }
        });

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.Monthly;
            }
        });

        yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = R.id.Yearly;
            }
        });


        // Schedule email
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Some valid inputs checking
                String a = to.getText().toString();
                if(a.isEmpty()) return;

                if(id == 0) {
                    Toast.makeText(sendemailandschedular.this, "Please Select a Scheduler", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText your_mail = findViewById(R.id.your_mail);
                EditText your_password = findViewById(R.id.your_password);
                String mail = your_mail.getText().toString();
                String password = your_password.getText().toString();
                if(mail.isEmpty()){
                    Toast.makeText(sendemailandschedular.this, "Enter Your mail Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(sendemailandschedular.this, "Enter Your password please", Toast.LENGTH_SHORT).show();
                    return;
                }




                // Visibility gone after click
                to.setVisibility(View.INVISIBLE);
                cc.setVisibility(View.INVISIBLE);
                subject.setVisibility(View.INVISIBLE);
                recurring.setVisibility(View.INVISIBLE);
                weekly.setVisibility(View.INVISIBLE);
                monthly.setVisibility(View.INVISIBLE);
                yearly.setVisibility(View.INVISIBLE);
                body.setVisibility(View.INVISIBLE);
                send.setVisibility(View.INVISIBLE);
                body_tittle_small.setVisibility(View.INVISIBLE);
                sehedule_small.setVisibility(View.INVISIBLE);
                bold_text.setVisibility(View.INVISIBLE);
//                EditText your_mail = findViewById(R.id.your_mail);
//                EditText your_password = findViewById(R.id.your_password);
                your_mail.setVisibility(View.INVISIBLE);
                your_password.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
                done.playAnimation();



                Toast.makeText(sendemailandschedular.this, "ThankYou !! Task has been Scheduled", Toast.LENGTH_SHORT).show();



                // Every 30 seconds

                if(id == R.id.recurring){
                    Handler handler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                                send_email();
                                Toast.makeText(sendemailandschedular.this, "Mail sent", Toast.LENGTH_SHORT).show();
                                handler.postDelayed(this , 30000);

                        }
                    };
                    handler.postDelayed(r, 30000);
                }

                // 7 days
                else if(id == R.id.weekly){
                    Handler handler = new Handler();
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                                send_email();
                                Toast.makeText(sendemailandschedular.this, "Mail sent", Toast.LENGTH_SHORT).show();
                                handler.postDelayed(this, 604800000);
                        }
                    };
                    handler.postDelayed(r , 604800000);
                }


                // Monthly
                else if(id == R.id.Monthly){
                    Handler handler = new Handler();
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {

                                send_email();
                                Toast.makeText(sendemailandschedular.this, "Mail sent", Toast.LENGTH_SHORT).show();
                            handler.postDelayed(this , 604800000);
                            handler.postDelayed(this , 604800000);
                            handler.postDelayed(this , 604800000);
                            handler.postDelayed(this , 604800000);
                            handler.postDelayed(this , 86400000);
                            handler.postDelayed(this , 86400000);

                            }
                    };

                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                }

//                yearly
                else if(id == R.id.Yearly){
                    Handler handler = new Handler();
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                                send_email();
                                Toast.makeText(sendemailandschedular.this, "Mail sent", Toast.LENGTH_SHORT).show();

                                // 1
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 2
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 3
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 4
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 5
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 6
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 7
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 8
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 9
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                // 11
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                                //12
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 604800000);
                                handler.postDelayed(this , 86400000);
                                handler.postDelayed(this , 86400000);

                        }
                    };
                    // 1
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 2
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 3

                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 4
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 5
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 6
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 7
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 8
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 9
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 10
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 11
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);

                    // 12

                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 604800000);
                    handler.postDelayed(r , 86400000);
                    handler.postDelayed(r , 86400000);
                }


            }
        });
    }


    // Back end behind to send email
    public void send_email(){
        EditText your_mail = findViewById(R.id.your_mail);
        EditText your_password = findViewById(R.id.your_password);
        user = your_mail.getText().toString();
        password = your_password.getText().toString();
        gmailSender = new GMailSender(user , password);
        EditText body = findViewById(R.id.body);
        sb = subject.getText().toString();
        bd = body.getText().toString();
        rp = to.getText().toString();
        new MyAsyncClass().execute();

    }


    class MyAsyncClass extends AsyncTask<Void , Void , Void>{

        ProgressDialog pDialog;
//        protected void onPreExecute(){
//            super.onPreExecute();
//            pDialog = new ProgressDialog(sendemailandschedular.this);
//            pDialog.setMessage("Please wait");
//            pDialog.show();
//        }



        @Override
        protected Void doInBackground(Void... mApi) {

            try {
                gmailSender.sendMail(sb , bd  ,user , rp);

            }
            catch (Exception e){
            }
            return null;
        }
    }
}
