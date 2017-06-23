package com.example.snehitsagi.plan;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;

import static com.example.snehitsagi.plan.R.layout.activity_input;

public class input extends AppCompatActivity implements View.OnClickListener{

    EditText memberName;
    EditText spouseName;
    EditText phoneNo;
    EditText emailid;
    EditText addressLine;
    EditText cityLine;
    EditText stateLine;
    EditText code;
    EditText profession;
    EditText expertise;
    EditText splrec;
    Button addbutton;
    Button dobpick;
    Button dowpick;
    Button dojpick;
    Button imgup;
    TextView dobTextView;
    TextView dowTextView;
    TextView dojTextView;
    String array_country[];
    String selectedCountry;
    DatabaseReference databaseMembers;
    StorageReference storageMembers;
    ProgressDialog progress;
    final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_input);

        databaseMembers= FirebaseDatabase.getInstance().getReference("members");
        storageMembers= FirebaseStorage.getInstance().getReference("members");

        progress= new ProgressDialog(this);

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
            phoneNo.setError( "Mandatory" );
        emailid=(EditText)findViewById(R.id.emailid);
        addressLine=(EditText)findViewById(R.id.address);
        cityLine=(EditText)findViewById(R.id.city);
        stateLine=(EditText)findViewById(R.id.state);
        code=(EditText)findViewById(R.id.postalcode);
        profession=(EditText)findViewById(R.id.prof);
        expertise=(EditText)findViewById(R.id.expert);
        splrec=(EditText)findViewById(R.id.spl);
        imgup=(Button)findViewById(R.id.img);

        array_country=new String[4];
        array_country[0]=" India";
        array_country[1]=" USA";
        array_country[2]=" Canada";
        array_country[3]=" Dubai";
        final Spinner country_spinner= (Spinner)findViewById(R.id.country);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.spinner_text,array_country);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        country_spinner.setAdapter(adapter);
        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
             selectedCountry=array_country[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
             selectedCountry=array_country[0];
            }
        });


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
                String address=addressLine.getText().toString().trim();
                String postal=code.getText().toString().trim();
                String countryString=selectedCountry;
                String city=cityLine.getText().toString().trim();
                String statee=stateLine.getText().toString().trim();
                String proff=profession.getText().toString().trim();
                String expertt=expertise.getText().toString().trim();
                String splr=splrec.getText().toString().trim();
                String id=databaseMembers.push().getKey();
                Members member=new Members(id,name,spouse,mobileno,email,dob,dow,doj,address,city,countryString,postal,proff,expertt,splr,statee);
                databaseMembers.child(id).setValue(member);

                Toast.makeText(input.this, "New member added", Toast.LENGTH_SHORT).show();

                memberName.setText("");
                spouseName.setText("");
                phoneNo.setText("");
                emailid.setText("");
                dobTextView.setText("Not provided");
                dowTextView.setText("Not provided");
                dojTextView.setText("Not provided");
                addressLine.setText("");
                cityLine.setText("");
                stateLine.setText("");
                country_spinner.setSelection(0);
                code.setText("");
                profession.setText("");
                expertise.setText("");
                splrec.setText("");
            }
        });

        imgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,REQUEST_CODE);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            String imgname=memberName.getText().toString().trim();
            progress.setMessage("Uploading");
            progress.show();
            Uri uri=data.getData();
            StorageReference path=storageMembers.child("photos").child("images/"+imgname+".jpg");
            path.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(input.this, "Upload success", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
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