package com.example.hreddit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;

    private ImageView image;
    private Button loginButton, guestButton;
    private TextView registerButton, passwordButton;
    private EditText emailEdit, passwordEdit;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.logoImage2);
        emailEdit = (EditText) findViewById(R.id.usernameTxt);
        passwordEdit = (EditText) findViewById(R.id.passwordTxt);
        loginButton = (Button) findViewById(R.id.loginButton);


        registerButton = (TextView) findViewById(R.id.registerButton);
        guestButton = (Button) findViewById(R.id.guestButton);
        passwordButton = (TextView) findViewById(R.id.forgotPasswordButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        //register batun
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registerScreen.class);
                startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);            }
        });

        //login batun
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, homeScreen.class);

                String email = emailEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();

                User user = userViewModel.getUser(email, password);

                if (user != null) {
                    intent.putExtra("UserIzMain", user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //login as guest batun
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, homeScreen.class);

                User user = new User("guest", "guest", "guest", "", 0, 0, 0, imageViewToByte(image), "", "");

                if (user != null) {
                    intent.putExtra("UserIzMain", user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //forgot pass batun
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, forgotPasswordScreen.class);
                startActivity(intent);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User user = new User(data.getStringExtra(registerScreen.EXTRA_USERNAME_REGISTER),
                    data.getStringExtra(registerScreen.EXTRA_PASSWORD_REGISTER),
                    data.getStringExtra(registerScreen.EXTRA_EMAIL_REGISTER),
                    "About me...",
                    0,
                    0,
                    0,
                    imageViewToByte(image),
                    "",
                    "");
            userViewModel.insert(user);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
