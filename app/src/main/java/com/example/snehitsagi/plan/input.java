package com.example.snehitsagi.plan;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.snehitsagi.plan.R.id.picDate;
import static com.example.snehitsagi.plan.R.id.pickDate2;
import static com.example.snehitsagi.plan.R.id.showDate;
import static com.example.snehitsagi.plan.R.id.showDate2;

public class input extends AppCompatActivity{

    EditText memberName;
    EditText spouseName;
    EditText phoneNo;
    EditText emailid;
    Button addbutton;
    Button dobpick;
    Button dowpick;
    TextView dobTextView;
    TextView dowTextView;
    DatabaseReference databaseMembers;
    private DatePickerDialog fromDatePickerDialog;
    private java.text.SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        databaseMembers= FirebaseDatabase.getInstance().getReference("members");

        //date picker begin
        dobpick=(Button) findViewById(picDate);
        dobTextView=(TextView) findViewById(showDate);
        dowpick=(Button) findViewById(pickDate2);
        dowTextView=(TextView) findViewById(showDate2);

        dobpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "DOB Picker");
                Intent in = getIntent();
                String finaldate=in.getStringExtra("finaldate");
                dobTextView.setText(finaldate);
            }
        });

        dowpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "DOW Picker");
                Intent in = getIntent();
                String finaldate=in.getStringExtra("finaldate");
                dowTextView.setText(finaldate);
            }
        });
        //date picker end

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
                String id=databaseMembers.push().getKey();
                Members member=new Members(id,name,spouse,mobileno,email,dob);
                databaseMembers.child(id).setValue(member);

                Toast.makeText(input.this, "New member added", Toast.LENGTH_SHORT).show();

                memberName.setText("");
                spouseName.setText("");
                phoneNo.setText("");
                emailid.setText("");
                dobTextView.setText("Not provided");

            }
        });
    }

}
