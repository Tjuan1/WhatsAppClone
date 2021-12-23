package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPassword;
    private ShowAlert mShowAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtUsername = findViewById(R.id.edtEnterUsername);
        edtEmail = findViewById(R.id.edtEnterEmail);
        edtPassword = findViewById(R.id.edtEnterPassword);

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public void onSignUpClicked(View view) {
        if(edtEmail.getText().toString().equals("")
                || edtUsername.getText().toString().equals("")
                || edtPassword.getText().toString().equals("")) {
            mShowAlert = new ShowAlert("Oh no!", "Username, Email and password are required", SignupActivity.this);
            mShowAlert.showAlertMessage();
        } else  {
            final ParseUser appUser = new ParseUser();
            appUser.setEmail(edtEmail.getText().toString());
            appUser.setUsername(edtUsername.getText().toString());
            appUser.setPassword(edtPassword.getText().toString());

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
            progressDialog.show();

            appUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null) {
                        mShowAlert = new ShowAlert("Check your email", "To verify this account", SignupActivity.this);
                        mShowAlert.showAlertMessage();
                    } else {
                        ParseUser.logOut();
                        mShowAlert = new ShowAlert("Ooops", e.getMessage(), SignupActivity.this);
                        mShowAlert.showAlertMessage();
                    }
                }
            });

            progressDialog.dismiss();
        }

    }

    public void onLoginClicked(View view) {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //    this hides the keyboard if we tap on the background
    public void onRootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hideBackground(View view) {
        UserInput hideUsrInput = new UserInput(SignupActivity.this);
        hideUsrInput.onRootLayoutTapped(view);
    }
}