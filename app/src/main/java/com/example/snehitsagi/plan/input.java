package com.example.snehitsagi.plan;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class input extends AppCompatActivity implements View.OnClickListener{

    EditText memberName;
    EditText spouseName;
    EditText phoneNo;
    EditText emailid;
    Button addbutton;
    Button dobpick;
    Button dowpick;
    Button dojpick;
    TextView dobTextView;
    TextView dowTextView;
    TextView dojTextView;
    DatabaseReference databaseMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        databaseMembers= FirebaseDatabase.getInstance().getReference("members");

        //date picker begin
        dobpick=(Button) findViewById(R.id.pickDOB);
        dobTextView=(TextView) findViewById(R.id.showDOB);
        dowpick=(Button) findViewById(R.id.pickDOW);
        dowTextView=(TextView) findViewById(R.id.showDOW);
        dojpick=(Button) findViewById(R.id.pickDOJ);
        dojTextView=(TextView) findViewById(R.id.showDOJ);

        dobpick.setOnClickListener(this);
        dowpick.setOnClickListener(this);
        dojpick.setOnClickListener(this);

        memberName=(EditText)findViewById(R.id.name);
        if( memberName.getText().toString().length() == 0 )
            memberName.setError( "Mandatory" );
        spouseName=(EditText)findViewById(R.id.SpouseName);
        phoneNo=(EditText)findViewById(R.id.phoneNo);
        if( phoneNo.getText().toString().length() == 0 )
            phoneNo.setError( "Invalid Number" );
        emailid=(EditText)findViewById(R.id.emailid);


        addbutton=(Button) findViewById(R.id.addButton);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=memberName.getText().toString().trim();
                String spouse=spouseName.getText().toString().trim();
                String mobileno=phoneNo.getText().toString().trim();
                String email=emailid.getText().toString().trim();
                String dob=dobTextView.getText().toString().trim();
                String dow=dowTextView.getText().toString().trim();
                String doj=dojTextView.getText().toString().trim();
                String id=databaseMembers.push().getKey();
                Members member=new Members(id,name,spouse,mobileno,email,dob,dow,doj);
                databaseMembers.child(id).setValue(member);

                Toast.makeText(input.this, "New member added", Toast.LENGTH_SHORT).show();

                memberName.setText("");
                spouseName.setText("");
                phoneNo.setText("");
                emailid.setText("");
                dobTextView.setText("Not provided");
                dowTextView.setText("Not provided");
                dojTextView.setText("Not provided");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v==dobpick)
        {
            Calendar c= Calendar.getInstance();
            int $year=c.get(Calendar.YEAR);
            int $month = c.get(Calendar.MONTH);
            int $date = c.get(Calendar.DAY_OF_MONTH);
            c.setTime(new Date());
            c.add(Calendar.YEAR,-27);
            long maxDate=c.getTime().getTime();
            DatePickerDialog dp=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dobTextView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },$date,$month,$year);
            dp.getDatePicker().setMaxDate(maxDate);
            dp.show();
        }
        if(v==dowpick)
        {
            Calendar cd= Calendar.getInstance();
            int $year=cd.get(Calendar.YEAR);
            int $month = cd.get(Calendar.MONTH);
            int $date = cd.get(Calendar.DAY_OF_MONTH);
            cd.setTime(new Date());
            cd.add(Calendar.YEAR,-27);
            long maxDate=cd.getTime().getTime();
            DatePickerDialog dpk=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dowTextView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },$date,$month,$year);
            dpk.getDatePicker().setMaxDate(maxDate);
            dpk.show();
        }
        if(v==dojpick)
        {
            Calendar cdm= Calendar.getInstance();
            int $year=cdm.get(Calendar.YEAR);
            int $month = cdm.get(Calendar.MONTH);
            int $date = cdm.get(Calendar.DAY_OF_MONTH);
            cdm.setTime(new Date());
            cdm.add(Calendar.YEAR,-27);
            long maxDate=cdm.getTime().getTime();
            DatePickerDialog dpm=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dojTextView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },$date,$month,$year);
            dpm.getDatePicker().setMaxDate(maxDate);
            dpm.show();
        }
    }
}