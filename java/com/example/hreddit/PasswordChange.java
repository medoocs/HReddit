package com.example.hreddit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.widget.Toast;

import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.User;

public class PasswordChange extends AppCompatActivity {
    EditText oldPass, newPass;

    private Button change;

    private User userIzHome;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Change Password");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");

        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        change = (Button) findViewById(R.id.button);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = ((EditText) findViewById(R.id.oldPass))
                        .getText().toString();
                String newPass = ((EditText) findViewById(R.id.newPass))
                        .getText().toString();

                User user = userViewModel.getUsername(userIzHome.getUserName());
                if (user != null && oldPass.equals(user.getPassword())) {
                    user.setPassword(newPass);
                    userViewModel.update(user);
                    Intent i = new Intent(PasswordChange.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(PasswordChange.this, "Unregistered user, or incorrect old password", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myprofilemenu, menu);
        MenuItem notifications = menu.findItem(R.id.nav_notification);
        notifications.setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.myprofile:
                Intent intent0 = new Intent(PasswordChange.this, myProfile.class);
                intent0.putExtra("UserIzHome", userIzHome);
                startActivity(intent0);
                return true;
            case R.id.mycomments:
                Intent intent = new Intent(PasswordChange.this, MyComments.class);
                intent.putExtra("UserIzHome", userIzHome);
                startActivity(intent);
                return true;
            case R.id.myforums:
                Intent intent2 = new Intent(PasswordChange.this, MyForums.class);
                intent2.putExtra("UserIzHome", userIzHome);
                startActivity(intent2);
                return true;
            case R.id.password:
                Intent intent3 = new Intent(PasswordChange.this, PasswordChange.class);
                intent3.putExtra("UserIzHome", userIzHome);
                startActivity(intent3);
                return true;
            case R.id.logout:
                Intent intent4 = new Intent(PasswordChange.this, MainActivity.class);
                startActivity(intent4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
