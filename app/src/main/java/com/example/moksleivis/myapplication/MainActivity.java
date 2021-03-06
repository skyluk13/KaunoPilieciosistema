package com.example.moksleivis.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button validate = (Button) findViewById(R.id.validate);
        final AutoCompleteTextView emailview = (AutoCompleteTextView) findViewById(R.id.Email);
        final AutoCompleteTextView passworderror = (AutoCompleteTextView) findViewById(R.id.password);


        validate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validateEmail(emailview.getText().toString())) {
                    emailview.setError(getString(R.string.emailas));
                    emailview.requestFocus();
                } else if (!validatePassword(passworderror.getText().toString())) {
                    passworderror.setError(getString(R.string.passwordErroras));
                    passworderror.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, "Sekmingai prisijungete", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, UserAccount.class);
                    startActivity(i);
                }


            }
        });
    }


    // grazina true jei password'as yra teisingas ir grazina false jei paswordas klaidingas.
    protected boolean validatePassword(String password) {
        if(password != null && password.length() > 9) {
            return true;
        } else {
            return false;
        }
    }
    //return true jei emailas teisingas ir false jei neteisingas
    protected boolean validateEmail(String email){
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}
