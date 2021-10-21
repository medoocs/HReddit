package com.example.hreddit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hreddit.Data.ForumStatsViewModel;
import com.example.hreddit.Data.ForumViewModel;
import com.example.hreddit.Data.KomentarAdapter;
import com.example.hreddit.Data.KomentarStatsViewModel;
import com.example.hreddit.Data.KomentarViewModel;
import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.ForumStats;
import com.example.hreddit.Model.Komentar;
import com.example.hreddit.Model.KomentarStats;
import com.example.hreddit.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class forumScreen extends AppCompatActivity {
    public static final int NEW_COMMENT_ACTIVITY_REQUEST_CODE = 1;
    TextView naslov, tekst, upvote, downvote, datum, user;
    ImageView kategorijaImage, upvoteIm, downvoteIm;

    ImageView bronzeFrOff, bronzeFrOn, silverFrOn, goldFrOn;

    private KomentarViewModel komentarViewModel;
    private ForumViewModel forumViewModel;
    private ForumStatsViewModel forumStatsViewModel;
    private KomentarStatsViewModel komentarStatsViewModel;

    private UserViewModel userViewModel;
    private User userIzHome;
    private Forum forum;
    ForumStats forumStats;
    KomentarStats komentarStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_screen);


        forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);
        forumStatsViewModel = new ViewModelProvider(this).get(ForumStatsViewModel.class);
        komentarStatsViewModel = new ViewModelProvider(this).get(KomentarStatsViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");

        if(!userIzHome.getUserName().equals("guest")){
            userIzHome = userViewModel.getUsername(userIzHome.getUserName());
        }else{
            userIzHome = new User("guest", "guest", "guest", "", 0, 0, 0, null, "", "");
        }

        forum = (Forum) getIntent().getSerializableExtra("Forum");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        naslov = (TextView) findViewById(R.id.naslovText);
        tekst = (TextView) findViewById(R.id.sadrzajText);
        upvote = (TextView) findViewById(R.id.upBroj);
        downvote = (TextView) findViewById(R.id.downBroj);
        datum = (TextView) findViewById(R.id.dateTxt);
        user = (TextView) findViewById(R.id.userTxt);

        kategorijaImage = (ImageView) findViewById(R.id.imCat);

        upvoteIm = (ImageView) findViewById(R.id.imUp);
        if(!userIzHome.getUserName().equals("guest")) {
            upvoteIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(forumStatsViewModel.getUserStatsForForum(userIzHome.getUserName(), forum.getId()) == null){
                        ForumStats forumStats = new ForumStats(userIzHome.getUserName(), forum.getId(), 0, 0);
                        forumStatsViewModel.insert(forumStats);
                    }
                    forumStats = forumStatsViewModel.getUserStatsForForum(userIzHome.getUserName(), forum.getId());
                    int upvote = forumStats.getUpvote();
                    int downvote = forumStats.getDownvote();

                    //RADIMO UPVOTE
                    if(upvote == 0 && downvote == 0){
                        forum.setUpvote(forum.getUpvote() + 1);
                        forumStats.setUpvote(1);
                        User userProfile = userViewModel.getUsername(forum.getUser());
                        userProfile.setForumScore(userProfile.getForumScore() + 1);
                        userViewModel.update(userProfile);
                    }
                    else if(upvote == 1 && downvote == 0) Toast.makeText(forumScreen.this, "Already upvoted!", Toast.LENGTH_SHORT).show();
                    else if(upvote == 0 && downvote == 1){
                        forum.setUpvote(forum.getUpvote() + 1);
                        forum.setDownvote(forum.getDownvote() - 1);
                        forumStats.setUpvote(1);
                        forumStats.setDownvote(0);
                        User userProfile = userViewModel.getUsername(forum.getUser());
                        userProfile.setForumScore(userProfile.getForumScore() + 2);
                        userViewModel.update(userProfile);
                    }
                    forumStatsViewModel.update(forumStats);
                    forumViewModel.update(forum);
                    forumScreen.this.onResume();
                }
            });
        }
        downvoteIm = (ImageView) findViewById(R.id.imDown);
        if(!userIzHome.getUserName().equals("guest")) {
            downvoteIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(forumStatsViewModel.getUserStatsForForum(userIzHome.getUserName(), forum.getId()) == null){
                        ForumStats forumStats = new ForumStats(userIzHome.getUserName(), forum.getId(), 0, 0);
                        forumStatsViewModel.insert(forumStats);
                    }
                    forumStats = forumStatsViewModel.getUserStatsForForum(userIzHome.getUserName(), forum.getId());
                    int upvote = forumStats.getUpvote();
                    int downvote = forumStats.getDownvote();

                    //RADIMO DOWNVOTE
                    if(upvote == 0 && downvote == 0){
                        forum.setDownvote(forum.getDownvote() + 1);
                        forumStats.setDownvote(1);
                        User userProfile = userViewModel.getUsername(forum.getUser());
                        userProfile.setForumScore(userProfile.getForumScore() - 1);
                        userViewModel.update(userProfile);
                    }
                    else if(upvote == 0 && downvote == 1) Toast.makeText(forumScreen.this, "Already downvoted!", Toast.LENGTH_SHORT).show();
                    else if(upvote == 1 && downvote == 0){
                        forum.setUpvote(forum.getUpvote() - 1);
                        forum.setDownvote(forum.getDownvote() + 1);
                        forumStats.setUpvote(0);
                        forumStats.setDownvote(1);
                        User userProfile = userViewModel.getUsername(forum.getUser());
                        userProfile.setForumScore(userProfile.getForumScore() - 2);
                        userViewModel.update(userProfile);
                    }
                    forumStatsViewModel.update(forumStats);
                    forumViewModel.update(forum);
                    forumScreen.this.onResume();
                }
            });
        }
        if (forum != null) {
            naslov.setText(forum.getNaslov());
            tekst.setText(forum.getTekst());
            tekst.setMovementMethod(new ScrollingMovementMethod());
            upvote.setText(Integer.toString(forum.getUpvote()));
            downvote.setText(Integer.toString(forum.getDownvote()));
            datum.setText(forum.getDatum());
            user.setText(forum.getUser());
            userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(forum.getUser().equals(userIzHome.getUserName())){
                        Intent intent = new Intent(forumScreen.this, myProfile.class);
                        intent.putExtra("UserIzHome", userIzHome);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(forumScreen.this, userProfile.class);
                        User userProfile = userViewModel.getUsername(forum.getUser());
                        intent.putExtra("UserIzHome", userIzHome);
                        intent.putExtra("UserProfile", userProfile);
                        startActivity(intent);
                    }
                }
            });
            User userProfile = userViewModel.getUsername(forum.getUser());
            bronzeFrOff = findViewById(R.id.bronzeforumoff);
            bronzeFrOn = findViewById(R.id.bronzeforumon);
            silverFrOn = findViewById(R.id.silverforumon);
            goldFrOn = findViewById(R.id.goldforumon);
            if(userProfile.getForumScore() <= 99){
                bronzeFrOff.setVisibility(View.VISIBLE);
                bronzeFrOn.setVisibility(View.GONE);
                silverFrOn.setVisibility(View.GONE);
                goldFrOn.setVisibility(View.GONE);
            }else if(userProfile.getForumScore() >= 100 && userProfile.getForumScore() <= 999){
                bronzeFrOff.setVisibility(View.GONE);
                bronzeFrOn.setVisibility(View.VISIBLE);
                silverFrOn.setVisibility(View.GONE);
                goldFrOn.setVisibility(View.GONE);
            }else if(userProfile.getForumScore() >= 1000 && userProfile.getForumScore() <= 10000){
                bronzeFrOff.setVisibility(View.GONE);
                bronzeFrOn.setVisibility(View.GONE);
                silverFrOn.setVisibility(View.VISIBLE);
                goldFrOn.setVisibility(View.GONE);
            }else{
                bronzeFrOff.setVisibility(View.GONE);
                bronzeFrOn.setVisibility(View.GONE);
                silverFrOn.setVisibility(View.GONE);
                goldFrOn.setVisibility(View.VISIBLE);
            }

            if(forum.getKategorija().equals("Food")) kategorijaImage.setImageResource(R.drawable.ic_foodon);
            else if(forum.getKategorija().equals("Book")) kategorijaImage.setImageResource(R.drawable.ic_bookon);
            else if(forum.getKategorija().equals("Games")) kategorijaImage.setImageResource(R.drawable.ic_gameson);
            else if(forum.getKategorija().equals("Movies")) kategorijaImage.setImageResource(R.drawable.ic_movieson);
            else if(forum.getKategorija().equals("Music")) kategorijaImage.setImageResource(R.drawable.ic_musicon);
            else if(forum.getKategorija().equals("Nature")) kategorijaImage.setImageResource(R.drawable.ic_natureon);
            else if(forum.getKategorija().equals("Places")) kategorijaImage.setImageResource(R.drawable.ic_placeson);
            else if(forum.getKategorija().equals("Technology")) kategorijaImage.setImageResource(R.drawable.ic_techon);
        }
        //add comment
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userIzHome.getUserName().equals("guest")) {
                    Intent intent = new Intent(forumScreen.this, newComment.class);
                    startActivityForResult(intent, NEW_COMMENT_ACTIVITY_REQUEST_CODE);
                }else Toast.makeText(forumScreen.this, "Unregistered user", Toast.LENGTH_SHORT).show();
            }
        });
        //ekran
        RecyclerView recyclerView = findViewById(R.id.listComms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final KomentarAdapter adapter = new KomentarAdapter(userViewModel);
        recyclerView.setAdapter(adapter);
        komentarViewModel = new ViewModelProvider(this).get(KomentarViewModel.class);
        komentarViewModel.getAllComments(forum.getId()).observe(this, new Observer<List<Komentar>>() {
            @Override
            public void onChanged(@Nullable List<Komentar> komentars) {
                adapter.submitList(komentars);
            }
        });

            adapter.setOnItemClickListener(new KomentarAdapter.OnItemClickListener() {
                @Override
                public void onKomentarClick(Komentar komentar) {

                }

                @Override
                public void onUpClick(Komentar komentar) {
                    //UPVOTE KOMENTARA
                    if(!userIzHome.getUserName().equals("guest")) {
                        if (komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId()) == null) {
                            KomentarStats komentarStats = new KomentarStats(userIzHome.getUserName(), komentar.getId(), 0, 0);
                            komentarStatsViewModel.insert(komentarStats);
                        }
                        komentarStats = komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId());
                        int upvote = komentarStats.getUpvote();
                        int downvote = komentarStats.getDownvote();

                        //RADIMO UPVOTE
                        if (upvote == 0 && downvote == 0) {
                            komentar.setUpvote(komentar.getUpvote() + 1);
                            komentarStats.setUpvote(1);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() + 1);
                            userViewModel.update(userProfile);
                        } else if (upvote == 1 && downvote == 0)
                            Toast.makeText(forumScreen.this, "Already upvoted!", Toast.LENGTH_SHORT).show();
                        else if (upvote == 0 && downvote == 1) {
                            komentar.setUpvote(komentar.getUpvote() + 1);
                            komentar.setDownvote(komentar.getDownvote() - 1);
                            komentarStats.setUpvote(1);
                            komentarStats.setDownvote(0);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() + 1);
                            userViewModel.update(userProfile);
                        }
                        komentarViewModel.update(komentar);
                        komentarStatsViewModel.update(komentarStats);
                        forumScreen.this.onResume();
                    }else Toast.makeText(getApplicationContext(), "Unregistered user!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDownClick(Komentar komentar) {
                    //DOWNVOTE KOMENTARA
                    if(!userIzHome.getUserName().equals("guest")) {
                        if (komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId()) == null) {
                            KomentarStats komentarStats = new KomentarStats(userIzHome.getUserName(), komentar.getId(), 0, 0);
                            komentarStatsViewModel.insert(komentarStats);
                        }
                        komentarStats = komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId());
                        int upvote = komentarStats.getUpvote();
                        int downvote = komentarStats.getDownvote();

                        //RADIMO DOWNVOTE
                        if (upvote == 0 && downvote == 0) {
                            komentar.setDownvote(komentar.getDownvote() + 1);
                            komentarStats.setDownvote(1);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() - 1);
                            userViewModel.update(userProfile);
                        } else if (upvote == 0 && downvote == 1)
                            Toast.makeText(forumScreen.this, "Already downvoted!", Toast.LENGTH_SHORT).show();
                        else if (upvote == 1 && downvote == 0) {
                            komentar.setUpvote(komentar.getUpvote() - 1);
                            komentar.setDownvote(komentar.getDownvote() + 1);
                            komentarStats.setUpvote(0);
                            komentarStats.setDownvote(1);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() - 1);
                            userViewModel.update(userProfile);
                        }
                        komentarViewModel.update(komentar);
                        komentarStatsViewModel.update(komentarStats);
                        forumScreen.this.onResume();
                    }else Toast.makeText(getApplicationContext(), "Unregistered user!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onUserClick(Komentar komentar) {
                        if (komentar.getUser().equals(userIzHome.getUserName())) {
                            Intent intent = new Intent(forumScreen.this, myProfile.class);
                            intent.putExtra("UserIzHome", userIzHome);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(forumScreen.this, userProfile.class);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            intent.putExtra("UserIzHome", userIzHome);
                            intent.putExtra("UserProfile", userProfile);
                            startActivity(intent);
                        }

                }
            });

        if(userIzHome.getUserName().equals("admin")) {
            adapter.setOnLongClickListener(new KomentarAdapter.OnLongClickListener() {
                @Override
                public void onLongTouch(Komentar komentar) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(forumScreen.this);
                    alert.setTitle("Alert!!");
                    alert.setMessage("Are you sure to delete record");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            komentarViewModel.delete(komentar);
                            forumScreen.this.onResume();
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    protected void onResume(){
        super.onResume();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if(!userIzHome.getUserName().equals("guest")){
            userIzHome = userViewModel.getUsername(userIzHome.getUserName());
        }else{
            userIzHome = new User("guest", "guest", "guest", "", 0, 0, 0, null, "", "");
        }

        if (forum != null) {
            naslov.setText(forum.getNaslov());
            tekst.setText(forum.getTekst());
            upvote.setText(Integer.toString(forum.getUpvote()));
            downvote.setText(Integer.toString(forum.getDownvote()));
            datum.setText(forum.getDatum());
            user.setText(forum.getUser());
            userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(forum.getUser().equals(userIzHome.getUserName())){
                        Intent intent = new Intent(forumScreen.this, myProfile.class);
                        intent.putExtra("UserIzHome", userIzHome);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(forumScreen.this, userProfile.class);
                        User userProfile = userViewModel.getUsername(forum.getUser());
                        intent.putExtra("UserIzHome", userIzHome);
                        intent.putExtra("UserProfile", userProfile);
                        startActivity(intent);
                    }
                }
            });

            User userProfile = userViewModel.getUsername(forum.getUser());
            bronzeFrOff = findViewById(R.id.bronzeforumoff);
            bronzeFrOn = findViewById(R.id.bronzeforumon);
            silverFrOn = findViewById(R.id.silverforumon);
            goldFrOn = findViewById(R.id.goldforumon);
            if(userProfile.getForumScore() <= 99){
                bronzeFrOff.setVisibility(View.VISIBLE);
                bronzeFrOn.setVisibility(View.GONE);
                silverFrOn.setVisibility(View.GONE);
                goldFrOn.setVisibility(View.GONE);
            }else if(userProfile.getForumScore() >= 100 && userProfile.getForumScore() <= 999){
                bronzeFrOff.setVisibility(View.GONE);
                bronzeFrOn.setVisibility(View.VISIBLE);
                silverFrOn.setVisibility(View.GONE);
                goldFrOn.setVisibility(View.GONE);
            }else if(userProfile.getForumScore() >= 1000 && userProfile.getForumScore() <= 10000){
                bronzeFrOff.setVisibility(View.GONE);
                bronzeFrOn.setVisibility(View.GONE);
                silverFrOn.setVisibility(View.VISIBLE);
                goldFrOn.setVisibility(View.GONE);
            }else{
                bronzeFrOff.setVisibility(View.GONE);
                bronzeFrOn.setVisibility(View.GONE);
                silverFrOn.setVisibility(View.GONE);
                goldFrOn.setVisibility(View.VISIBLE);
            }

            if(forum.getKategorija().equals("Food")) kategorijaImage.setImageResource(R.drawable.ic_foodon);
            else if(forum.getKategorija().equals("Book")) kategorijaImage.setImageResource(R.drawable.ic_bookon);
            else if(forum.getKategorija().equals("Games")) kategorijaImage.setImageResource(R.drawable.ic_gameson);
            else if(forum.getKategorija().equals("Movies")) kategorijaImage.setImageResource(R.drawable.ic_movieson);
            else if(forum.getKategorija().equals("Music")) kategorijaImage.setImageResource(R.drawable.ic_musicon);
            else if(forum.getKategorija().equals("Nature")) kategorijaImage.setImageResource(R.drawable.ic_natureon);
            else if(forum.getKategorija().equals("Places")) kategorijaImage.setImageResource(R.drawable.ic_placeson);
            else if(forum.getKategorija().equals("Technology")) kategorijaImage.setImageResource(R.drawable.ic_techon);
        }

        //ekran
        RecyclerView recyclerView = findViewById(R.id.listComms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final KomentarAdapter adapter = new KomentarAdapter(userViewModel);
        recyclerView.setAdapter(adapter);
        komentarViewModel = new ViewModelProvider(this).get(KomentarViewModel.class);
        komentarViewModel.getAllComments(forum.getId()).observe(this, new Observer<List<Komentar>>() {
            @Override
            public void onChanged(@Nullable List<Komentar> komentars) {
                adapter.submitList(komentars);
            }
        });

        forumStatsViewModel = new ViewModelProvider(this).get(ForumStatsViewModel.class);
        komentarStatsViewModel = new ViewModelProvider(this).get(KomentarStatsViewModel.class);

            adapter.setOnItemClickListener(new KomentarAdapter.OnItemClickListener() {

                @Override
                public void onKomentarClick(Komentar komentar) {

                }

                @Override
                public void onUpClick(Komentar komentar) {
                    //UPVOTE KOMENTARA
                    if(!userIzHome.getUserName().equals("guest")) {
                        if (komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId()) == null) {
                            KomentarStats komentarStats = new KomentarStats(userIzHome.getUserName(), komentar.getId(), 0, 0);
                            komentarStatsViewModel.insert(komentarStats);
                        }
                        komentarStats = komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId());
                        int upvote = komentarStats.getUpvote();
                        int downvote = komentarStats.getDownvote();

                        //RADIMO UPVOTE
                        if (upvote == 0 && downvote == 0) {
                            komentar.setUpvote(komentar.getUpvote() + 1);
                            komentarStats.setUpvote(1);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() + 1);
                            userViewModel.update(userProfile);
                        } else if (upvote == 1 && downvote == 0)
                            Toast.makeText(forumScreen.this, "Already upvoted!", Toast.LENGTH_SHORT).show();
                        else if (upvote == 0 && downvote == 1) {
                            komentar.setUpvote(komentar.getUpvote() + 1);
                            komentar.setDownvote(komentar.getDownvote() - 1);
                            komentarStats.setUpvote(1);
                            komentarStats.setDownvote(0);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() + 1);
                            userViewModel.update(userProfile);
                        }
                        komentarViewModel.update(komentar);
                        komentarStatsViewModel.update(komentarStats);
                        forumScreen.this.onResume();
                    }
                }

                @Override
                public void onDownClick(Komentar komentar) {
                    if(!userIzHome.getUserName().equals("guest")) {
                        if (komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId()) == null) {
                            KomentarStats komentarStats = new KomentarStats(userIzHome.getUserName(), komentar.getId(), 0, 0);
                            komentarStatsViewModel.insert(komentarStats);
                        }
                        komentarStats = komentarStatsViewModel.getUserStatsForKomentar(userIzHome.getUserName(), komentar.getId());
                        int upvote = komentarStats.getUpvote();
                        int downvote = komentarStats.getDownvote();

                        //RADIMO DOWNVOTE
                        if (upvote == 0 && downvote == 0) {
                            komentar.setDownvote(komentar.getDownvote() + 1);
                            komentarStats.setDownvote(1);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() - 1);
                            userViewModel.update(userProfile);
                        } else if (upvote == 0 && downvote == 1)
                            Toast.makeText(forumScreen.this, "Already downvoted!", Toast.LENGTH_SHORT).show();
                        else if (upvote == 1 && downvote == 0) {
                            komentar.setUpvote(komentar.getUpvote() - 1);
                            komentar.setDownvote(komentar.getDownvote() + 1);
                            komentarStats.setUpvote(0);
                            komentarStats.setDownvote(1);
                            User userProfile = userViewModel.getUsername(komentar.getUser());
                            userProfile.setCommentScore(userProfile.getCommentScore() - 1);
                            userViewModel.update(userProfile);
                        }
                        komentarViewModel.update(komentar);
                        komentarStatsViewModel.update(komentarStats);
                        forumScreen.this.onResume();
                    }
                }

                @Override
                public void onUserClick(Komentar komentar) {
                    if(komentar.getUser().equals(userIzHome.getUserName())){
                        Intent intent = new Intent(forumScreen.this, myProfile.class);
                        intent.putExtra("UserIzHome", userIzHome);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(forumScreen.this, userProfile.class);
                        User userProfile = userViewModel.getUsername(komentar.getUser());
                        intent.putExtra("UserIzHome", userIzHome);
                        intent.putExtra("UserProfile", userProfile);
                        startActivity(intent);
                    }
                }
            });

        if(userIzHome.getUserName().equals("admin")) {
            adapter.setOnLongClickListener(new KomentarAdapter.OnLongClickListener() {
                @Override
                public void onLongTouch(Komentar komentar) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(forumScreen.this);
                    alert.setTitle("Alert!!");
                    alert.setMessage("Are you sure to delete record");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            komentarViewModel.delete(komentar);
                            forumScreen.this.onResume();
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
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_COMMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String strDate = dateFormat.format(date);

            Komentar komentar = new Komentar(forum.getId(),
                    data.getStringExtra(newComment.EXTRA_KOMENTAR),
                    userIzHome.getUserName(),
                    strDate,
                    0,
                    0);
            komentarViewModel.insert(komentar);
            forum.setBrojKomentara(forum.getBrojKomentara() + 1);
            forumViewModel.update(forum);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
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
