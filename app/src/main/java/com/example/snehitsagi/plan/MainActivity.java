package com.example.snehitsagi.plan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        buttonRegister= (Button) findViewById(R.id.buttonRegister);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        textViewSignin= (TextView) findViewById(R.id.link_signup);
        progressBar= new ProgressDialog(this);
        firebaseauth= FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password=editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Email Mandatory", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Password Mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,"PLease wait...","Processing...",true);

                firebaseauth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Intent i = new Intent(MainActivity.this,input.class);
                                    startActivity(i);
                                } else {
                                    Log.e("ERROR",task.getException().toString());
                                    Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password=editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Email Mandatory", Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Password Mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,"Making you part of our family...","Processing...",true);

                firebaseauth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Intent i = new Intent(MainActivity.this,input.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
