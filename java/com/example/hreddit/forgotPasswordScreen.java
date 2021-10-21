package com.example.hreddit;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.widget.Toast;

import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.User;

public class forgotPasswordScreen extends AppCompatActivity {
    EditText reciep;

    private Button forgotButton;

    private User user;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);

        reciep = (EditText) findViewById(R.id.editText);
        forgotButton = (Button) findViewById(R.id.button);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fromEmail = "";
                String fromPassword = "";
                String toEmail = ((EditText) findViewById(R.id.editText))
                        .getText().toString();

                user = userViewModel.getPass(toEmail);
                if (user != null) {
                    String emailBody = user.getPassword();
                    String emailSubject = "This is your app password!";

                    new SendMailTask(forgotPasswordScreen.this).execute(fromEmail,
                            fromPassword, toEmail, emailSubject, emailBody);

                    Intent i = new Intent(forgotPasswordScreen.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(forgotPasswordScreen.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }

            }

        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
