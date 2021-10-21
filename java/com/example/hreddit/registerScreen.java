package com.example.hreddit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hreddit.Data.UserDao;
import com.example.hreddit.Data.UserDataBase;
import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.User;

import java.io.ByteArrayOutputStream;

public class registerScreen extends AppCompatActivity {
    public static final String EXTRA_USERNAME_REGISTER = "usernameregister";
    public static final String EXTRA_EMAIL_REGISTER = "emailregister";
    public static final String EXTRA_PASSWORD_REGISTER = "passwordregister";

    EditText editUsername, editEmail, editPassword, editConfirmPassword;
    Button buttonRegister;
    CheckBox checkBoxRobot;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        editUsername = findViewById(R.id.inputUsername);
        editEmail = findViewById(R.id.inputEmail);
        editPassword = findViewById(R.id.inputPassTxt);
        editConfirmPassword = findViewById(R.id.repeatPassword);
        buttonRegister = findViewById(R.id.button);
        checkBoxRobot = findViewById(R.id.checkBox);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        //register batun
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();

                String userName = editUsername.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String passwordConf = editConfirmPassword.getText().toString().trim();

                User usernameCheck = userViewModel.getUsername(userName);
                User emailCheck = userViewModel.getEmail(email);

                if (emailCheck == null && usernameCheck == null && password.equals(passwordConf) && checkBoxRobot.isChecked()) {
                    replyIntent.putExtra(EXTRA_USERNAME_REGISTER, userName);
                    replyIntent.putExtra(EXTRA_EMAIL_REGISTER, email);
                    replyIntent.putExtra(EXTRA_PASSWORD_REGISTER, password);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                } else{
                    if(usernameCheck != null)
                        Toast.makeText(registerScreen.this, "Username already in use!", Toast.LENGTH_SHORT).show();
                    else if(emailCheck != null)
                        Toast.makeText(registerScreen.this, "Email already in use!", Toast.LENGTH_SHORT).show();
                    else if(!password.equals(passwordConf))
                        Toast.makeText(registerScreen.this, "Password is not matching!", Toast.LENGTH_SHORT).show();
                    else if(!checkBoxRobot.isChecked())
                        Toast.makeText(registerScreen.this, "You're a robot!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
