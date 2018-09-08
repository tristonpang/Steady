package com.bokbok.tristonpang.steady;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private AutoCompleteTextView mNameView;
    private EditText mContactNumView;
    private DatabaseReference mDatabaseReference;
    private Boolean mIsDeaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mEmailView = findViewById(R.id.emailRegTextView);
        mPasswordView = findViewById(R.id.passwordTextView);
        mConfirmPasswordView = findViewById(R.id.confirmPasswordTextView);
        mNameView = findViewById(R.id.nameTextView);
        mContactNumView = findViewById(R.id.contactNumView);
        mIsDeaf = null;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public void onRadioButtonClicked(View view) {
        // Check if button is checked
        Log.d("Steady", "onRadioButtonClicked()");
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioDeaf:
                if (checked) {
                    mIsDeaf = true;
                    break;
                }
            case R.id.radioCare:
                if (checked) {
                    mIsDeaf = false;
                    break;
                }
        }
    }

    public void registerNewUser(View v) {
        attemptRegistration();
    }

    private void attemptRegistration() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (isEmailValid() && isPasswordValid() && isNumValid() && isRadioButtonValid()) { //begin registration attempt
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("Steady", "attemptRegistration() onComplete Success: + " + task.isSuccessful());
                    if (!task.isSuccessful()) {
                        //error dialog
                        showErrorDialog("Registration failed");
                    } else {
                        //store user info into database
                        addNewUserToDatabase();

                        //switch back to LoginActivity
                        finish();
                    }
                }
            });
        } else { //toast to notify user to fill fields properly
            Toast.makeText(this, R.string.validate_text, Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isEmailValid() {
        boolean result = mEmailView.getText().toString().contains("@");
        Log.d("Steady", "isEmailValid(): " + result);
        return result;
    }

    private boolean isPasswordValid() {
        String password = mPasswordView.getText().toString();
        boolean result = password.length() >= 6
                && mConfirmPasswordView.getText().toString().equals(password);
        Log.d("Steady", "isPasswordValid(): " + result);
        return result;
    }

    private boolean isNumValid() {
        boolean result = mContactNumView.getText().toString().length() == 8;
        Log.d("Steady", "isNumValid(): " + result);
        return result;
    }

    private boolean isRadioButtonValid() {
        Log.d("Steady", "isRadioButtonValid(): " + (mIsDeaf != null));
        return mIsDeaf != null;
    }

    private void showErrorDialog(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("Oh no!")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void addNewUserToDatabase() {
        String id = UUID.randomUUID().toString();
        String name = mNameView.getText().toString();
        String contactNum = mContactNumView.getText().toString();
        String email = mEmailView.getText().toString();

        User newUser = new User(id, name, contactNum, email, null, null, null, null, mIsDeaf);

        //database paths cannot have . so we replace with -
        email = email.replace(".","-");

        //email forms the directory path that store the user data
        mDatabaseReference.child("users").child(id).setValue(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Steady", "New user successfully added to database");
            }
        });
    }

}
