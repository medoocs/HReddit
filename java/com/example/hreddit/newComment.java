package com.example.hreddit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hreddit.Data.ForumDao;
import com.example.hreddit.Data.ForumViewModel;
import com.example.hreddit.Data.KomentarDao;
import com.example.hreddit.Data.KomentarViewModel;
import com.example.hreddit.Data.UserDataBase;
import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.Komentar;
import com.example.hreddit.Model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class newComment extends AppCompatActivity {
    public static final String EXTRA_KOMENTAR = "komentar";

    EditText editComm;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editComm = (EditText) findViewById(R.id.editText);

        post = (Button) findViewById(R.id.button);


        //post comment batun
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tekst = editComm.getText().toString().trim();
                Intent replyIntent = new Intent(newComment.this, forumScreen.class);

                if (!tekst.isEmpty()) {
                    replyIntent.putExtra(EXTRA_KOMENTAR, tekst);
                    setResult(RESULT_OK, replyIntent);
                } else{
                    if(tekst.isEmpty())
                        Toast.makeText(newComment.this, "You can't leave comment empty!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //app bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
