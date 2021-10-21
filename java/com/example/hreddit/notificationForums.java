package com.example.hreddit;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hreddit.Data.ForumAdapter;
import com.example.hreddit.Data.ForumViewModel;
import com.example.hreddit.Data.KomentarAdapter;
import com.example.hreddit.Data.KomentarViewModel;
import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.Komentar;
import com.example.hreddit.Model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class notificationForums extends AppCompatActivity {
    private ForumViewModel forumViewModel;
    private UserViewModel userViewModel;
    private User userIzHome;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationforums);

        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Notifications");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //ekran
        RecyclerView recyclerView = findViewById(R.id.listForumaKomentara);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ForumAdapter adapter = new ForumAdapter();
        recyclerView.setAdapter(adapter);
        forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        ArrayList<String> listForumIds = toArray(userIzHome.getNotificationForums());
        listForumIds.remove(0);
        String[] forumIds = listForumIds.toArray(new String[0]);
        int[] array = Arrays.asList(forumIds).stream().mapToInt(Integer::parseInt).toArray();
        forumViewModel.getForumsById(array).observe(this, new Observer<List<Forum>>() {
            @Override
            public void onChanged(@Nullable List<Forum> forums) {
                adapter.submitList(forums);
            }
        });

        adapter.setOnItemClickListener(new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Forum forum) {
                Intent intent = new Intent(notificationForums.this, forumScreen.class);
                intent.putExtra("UserIzHome", userIzHome);
                intent.putExtra("Forum", forum);
                startActivity(intent);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onResume(){
        super.onResume();

        //ekran
        RecyclerView recyclerView = findViewById(R.id.listForumaKomentara);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ForumAdapter adapter = new ForumAdapter();
        recyclerView.setAdapter(adapter);
        forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        ArrayList<String> listForumIds = toArray(userIzHome.getNotificationForums());
        listForumIds.remove(0);
        String[] forumIds = listForumIds.toArray(new String[0]);
        int[] array = Arrays.asList(forumIds).stream().mapToInt(Integer::parseInt).toArray();
        forumViewModel.getForumsById(array).observe(this, new Observer<List<Forum>>() {
            @Override
            public void onChanged(@Nullable List<Forum> forums) {
                adapter.submitList(forums);
            }
        });

        adapter.setOnItemClickListener(new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Forum forum) {
                Intent intent = new Intent(notificationForums.this, forumScreen.class);
                intent.putExtra("UserIzHome", userIzHome);
                intent.putExtra("Forum", forum);
                startActivity(intent);
            }
        });
    }




    //app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notificationsmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.removeall:
                Intent intent0 = new Intent(notificationForums.this, homeScreen.class);
                intent0.putExtra("UserIzMain", userIzHome);
                userIzHome.setNotificationForums("");
                userViewModel.update(userIzHome);
                startActivity(intent0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String fromArray(ArrayList<String> strings) {
        String string = "";
        for(String s : strings) string += (s + ",");

        return string;
    }


    public ArrayList<String> toArray(String concatenatedStrings) {
        ArrayList<String> myStrings = new ArrayList<>();
        for(String s : concatenatedStrings.split(",")) myStrings.add(s);

        return myStrings;
    }
}

