package com.example.hreddit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyComments extends AppCompatActivity {
    private KomentarViewModel komentarViewModel;
    private ForumViewModel forumViewModel;
    private UserViewModel userViewModel;

    private User userIzHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomments);

        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("My Comments");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //ekran
        RecyclerView recyclerView = findViewById(R.id.listForumaKomentara);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        final KomentarAdapter adapter = new KomentarAdapter(userViewModel);
        recyclerView.setAdapter(adapter);

        komentarViewModel = new ViewModelProvider(this).get(KomentarViewModel.class);
        forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);

        komentarViewModel.getAllCommentsProfile(userIzHome.getUserName()).observe(this, new Observer<List<Komentar>>() {
            @Override
            public void onChanged(@Nullable List<Komentar> komentars) {
                adapter.submitList(komentars);
            }
        });

        adapter.setOnItemClickListener(new KomentarAdapter.OnItemClickListener() {
            @Override
            public void onKomentarClick(Komentar komentar) {
                if(forumViewModel.getForumById(komentar.getIdForuma()) != null) {
                    Intent intent = new Intent(MyComments.this, forumScreen.class);
                    intent.putExtra("UserIzHome", userIzHome);
                    intent.putExtra("Forum", forumViewModel.getForumById(komentar.getIdForuma()));
                    startActivity(intent);
                }else Toast.makeText(MyComments.this, "Post has been deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpClick(Komentar komentar) {

            }

            @Override
            public void onDownClick(Komentar komentar) {

            }

            @Override
            public void onUserClick(Komentar komentar) {

            }
        });
        adapter.setOnLongClickListener(new KomentarAdapter.OnLongClickListener() {
            @Override
            public void onLongTouch(Komentar komentar) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MyComments.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Forum forum = forumViewModel.getForumById(komentar.getIdForuma());
                        forum.setBrojKomentara(forum.getBrojKomentara() - 1);
                        forumViewModel.update(forum);
                        komentarViewModel.delete(komentar);
                        MyComments.this.onResume();
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    protected void onResume(){
        super.onResume();

        //ekran
        RecyclerView recyclerView = findViewById(R.id.listForumaKomentara);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        final KomentarAdapter adapter = new KomentarAdapter(userViewModel);
        recyclerView.setAdapter(adapter);
        komentarViewModel = new ViewModelProvider(this).get(KomentarViewModel.class);
        komentarViewModel = new ViewModelProvider(this).get(KomentarViewModel.class);

        komentarViewModel.getAllCommentsProfile(userIzHome.getUserName()).observe(this, new Observer<List<Komentar>>() {
            @Override
            public void onChanged(@Nullable List<Komentar> komentars) {
                adapter.submitList(komentars);
            }
        });

        adapter.setOnItemClickListener(new KomentarAdapter.OnItemClickListener() {
            @Override
            public void onKomentarClick(Komentar komentar) {
                if(forumViewModel.getForumById(komentar.getIdForuma()) != null) {
                    Intent intent = new Intent(MyComments.this, forumScreen.class);
                    intent.putExtra("UserIzHome", userIzHome);
                    intent.putExtra("Forum", forumViewModel.getForumById(komentar.getIdForuma()));
                    startActivity(intent);
                }else Toast.makeText(MyComments.this, "Post has been deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpClick(Komentar komentar) {

            }

            @Override
            public void onDownClick(Komentar komentar) {

            }

            @Override
            public void onUserClick(Komentar komentar) {

            }
        });
        adapter.setOnLongClickListener(new KomentarAdapter.OnLongClickListener() {
            @Override
            public void onLongTouch(Komentar komentar) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MyComments.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Forum forum = forumViewModel.getForumById(komentar.getIdForuma());
                        forum.setBrojKomentara(forum.getBrojKomentara() - 1);
                        forumViewModel.update(forum);
                        komentarViewModel.delete(komentar);
                        MyComments.this.onResume();
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

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
                Intent intent0 = new Intent(MyComments.this, myProfile.class);
                intent0.putExtra("UserIzHome", userIzHome);
                startActivity(intent0);
                return true;
            case R.id.mycomments:
                Intent intent1 = new Intent(MyComments.this, MyComments.class);
                intent1.putExtra("UserIzHome", userIzHome);
                startActivity(intent1);
                return true;
            case R.id.myforums:
                Intent intent2 = new Intent(MyComments.this, MyForums.class);
                intent2.putExtra("UserIzHome", userIzHome);
                startActivity(intent2);
                return true;
            case R.id.password:
                Intent intent3 = new Intent(MyComments.this, PasswordChange.class);
                intent3.putExtra("UserIzHome", userIzHome);
                startActivity(intent3);
                return true;
            case R.id.logout:
                Intent intent4 = new Intent(MyComments.this, MainActivity.class);
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

