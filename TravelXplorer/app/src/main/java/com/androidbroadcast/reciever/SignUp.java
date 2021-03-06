package com.androidbroadcast.reciever;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shish on 4/10/2019.
 */

public class SignUp extends Activity {
    DataController mydb;
    EditText email;
    EditText pwd;
    EditText name;
    EditText cpwd;
    TextView msg;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }

    public void saveLogin(View view) {
        String regex="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        EditText newEmail = (EditText) findViewById(R.id.emailView);
        EditText pwd = (EditText) findViewById(R.id.passwordView);
        EditText name = (EditText) findViewById(R.id.nameView);
        EditText cpwd = (EditText) findViewById(R.id.cpasswordView);
        TextView msg = (TextView) findViewById(R.id.msg);
        String email = newEmail.getText().toString();
        String pwdEdit = pwd.getText().toString();
        String cpwdEdit = cpwd.getText().toString();
        String nameEdit = name.getText().toString();
        Log.d("details", "saveLogin: " + email + pwdEdit);

        if (!cpwd.getText().toString().equals(pwd.getText().toString())) {
            //msg.setText("Password does not match");
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();

        }
        if (cpwd.getText().length()>0 && name.getText().length()>0 && pwd.getText().length()>0 && newEmail.getText().length()>0) {
           // if(email.matches("")){
            DataController dataController = new DataController(getBaseContext());
            dataController.open();
            Cursor rs = dataController.retrieve(email);
            if(!rs.moveToFirst()){
            long retValue = dataController.insert(nameEdit, email, pwdEdit);
            dataController.close();
            Log.d("checking..", "saveLogin: " + retValue + msg.getText());
            if (retValue != -1) {
                Context context = getApplicationContext();
                //msg.setText("Registered successfully");
                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUp.this, GooglePlacesExample.class);
                startActivity(intent);
                //Log.d("checing..", "saveLogin: " + retValue + msg.getText());

            }
        }else {
                Toast.makeText(getApplicationContext(), "User already registered.Please Sign In", Toast.LENGTH_LONG).show();
                Intent i = new Intent(SignUp.this,SignInActivity.class);
                startActivity(i);
            }
        }else{
                Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
        }/*} else {
           // msg.setText("Please enter all fields");
            Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
        }*/
    }
    @Override
    protected void onStop() {
        super.onStop();
        SignUp.this.finish();
    }
}





