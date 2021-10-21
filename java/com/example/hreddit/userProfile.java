package com.example.hreddit;
import de.hdodenhof.circleimageview.CircleImageView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.hreddit.Data.ForumViewModel;
import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class userProfile  extends AppCompatActivity {
    CircleImageView profilePicture;
    TextView aboutMe;
    TextView forumScore, commentScore, followers;
    Button follow;

    ImageView bronzeFrOff, bronzeFrOn, silverFrOff, silverFrOn, goldFrOff, goldFrOn;
    ImageView bronzeCOff, bronzeCOn, silverCOff, silverCOn, goldCOff, goldCOn;
    ImageView bronzeFOff, bronzeFOn, silverFOff, silverFOn, goldFOff, goldFOn;


    private User userProfile, userIzHome;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userProfile = (User) getIntent().getSerializableExtra("UserProfile");
        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(userProfile.getUserName() + "'s profile");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        profilePicture = findViewById(R.id.profilepicture);
        setImageViewWithByteArray(profilePicture, userProfile.getImage());

        aboutMe = findViewById(R.id.aboutme);
        aboutMe.setText(userProfile.getAboutMe());

        follow = findViewById(R.id.button);

        forumScore = findViewById(R.id.forums);
        forumScore.setText(Integer.toString(userProfile.getForumScore()));
        commentScore = findViewById(R.id.comments);
        commentScore.setText(Integer.toString(userProfile.getCommentScore()));
        followers = findViewById(R.id.followers);
        followers.setText(Integer.toString(userProfile.getFollowers()));

        bronzeFrOff = findViewById(R.id.bronzeforumoff);
        bronzeFrOn = findViewById(R.id.bronzeforumon);
        silverFrOff = findViewById(R.id.silverforumoff);
        silverFrOn = findViewById(R.id.silverforumon);
        goldFrOff = findViewById(R.id.goldforumoff);
        goldFrOn = findViewById(R.id.goldforumon);
        if(userProfile.getForumScore() <= 99){
            bronzeFrOff.setVisibility(View.VISIBLE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userProfile.getForumScore() >= 100 && userProfile.getForumScore() <= 999){
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.VISIBLE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userProfile.getForumScore() >= 1000 && userProfile.getForumScore() <= 10000){
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.VISIBLE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else{
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.VISIBLE);
        }
        bronzeFOff = findViewById(R.id.bronzefollowoff);
        bronzeFOn = findViewById(R.id.bronzefollowon);
        silverFOff = findViewById(R.id.silverfollowoff);
        silverFOn = findViewById(R.id.silverfollowon);
        goldFOff = findViewById(R.id.goldfollowoff);
        goldFOn = findViewById(R.id.goldfollowon);
        if(userProfile.getFollowers() <= 99){
            bronzeFOff.setVisibility(View.VISIBLE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userProfile.getFollowers() >= 100 && userProfile.getFollowers() <= 999){
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.VISIBLE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userProfile.getFollowers() >= 1000 && userProfile.getFollowers() <= 10000){
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.VISIBLE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else{
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.VISIBLE);
        }
        bronzeCOff = findViewById(R.id.bronzecommentoff);
        bronzeCOn = findViewById(R.id.bronzecommenton);
        silverCOff = findViewById(R.id.silvercommentoff);
        silverCOn = findViewById(R.id.silvercommenton);
        goldCOff = findViewById(R.id.goldcommentoff);
        goldCOn = findViewById(R.id.goldcommenton);
        if(userProfile.getCommentScore() <= 99){
            bronzeCOff.setVisibility(View.VISIBLE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userProfile.getCommentScore() >= 100 && userProfile.getCommentScore() <= 999){
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.VISIBLE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userProfile.getCommentScore() >= 1000 && userProfile.getCommentScore() <= 10000){
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.VISIBLE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else{
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.VISIBLE);
        }

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        ArrayList<String> followers = toArray(userProfile.getFollowing());
        boolean flag = false;
        for (String i : followers) {
            if(i.equals(userIzHome.getUserName())) flag = true;
        }
        if(flag){
            follow.setText("Unfollow");
        }else follow.setText("Follow");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!userIzHome.getUserName().equals("guest")) {
                        ArrayList<String> followers = toArray(userProfile.getFollowing());
                        boolean flag = false;
                        for (String i : followers) {
                            if (i.equals(userIzHome.getUserName())) flag = true;
                        }
                        if (!flag) {
                            userProfile.setFollowers(userProfile.getFollowers() + 1);
                            followers.add(userIzHome.getUserName());
                            userProfile.setFollowing(fromArray(followers));
                            userViewModel.update(userProfile);
                            Toast.makeText(getApplicationContext(), "Followed!", Toast.LENGTH_SHORT).show();
                            userProfile.this.onResume();
                        } else {
                            userProfile.setFollowers(userProfile.getFollowers() - 1);
                            followers.remove(userIzHome.getUserName());
                            userProfile.setFollowing(fromArray(followers));
                            userViewModel.update(userProfile);
                            Toast.makeText(getApplicationContext(), "Unfollowed!", Toast.LENGTH_SHORT).show();
                            userProfile.this.onResume();
                        }
                    }else Toast.makeText(getApplicationContext(), "Unregistered user!", Toast.LENGTH_SHORT).show();
                }

            });

    }
    protected void onResume() {
        super.onResume();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userProfile = (User) getIntent().getSerializableExtra("UserProfile");
        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(userProfile.getUserName() + "'s profile");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        profilePicture = findViewById(R.id.profilepicture);
        setImageViewWithByteArray(profilePicture, userProfile.getImage());

        aboutMe = findViewById(R.id.aboutme);
        aboutMe.setText(userProfile.getAboutMe());

        follow = findViewById(R.id.button);

        forumScore = findViewById(R.id.forums);
        forumScore.setText(Integer.toString(userProfile.getForumScore()));
        commentScore = findViewById(R.id.comments);
        commentScore.setText(Integer.toString(userProfile.getCommentScore()));
        followers = findViewById(R.id.followers);
        followers.setText(Integer.toString(userProfile.getFollowers()));

        bronzeFrOff = findViewById(R.id.bronzeforumoff);
        bronzeFrOn = findViewById(R.id.bronzeforumon);
        silverFrOff = findViewById(R.id.silverforumoff);
        silverFrOn = findViewById(R.id.silverforumon);
        goldFrOff = findViewById(R.id.goldforumoff);
        goldFrOn = findViewById(R.id.goldforumon);
        if(userProfile.getForumScore() <= 99){
            bronzeFrOff.setVisibility(View.VISIBLE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userProfile.getForumScore() >= 100 && userProfile.getForumScore() <= 999){
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.VISIBLE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userProfile.getForumScore() >= 1000 && userProfile.getForumScore() <= 10000){
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.VISIBLE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else{
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.VISIBLE);
        }
        bronzeFOff = findViewById(R.id.bronzefollowoff);
        bronzeFOn = findViewById(R.id.bronzefollowon);
        silverFOff = findViewById(R.id.silverfollowoff);
        silverFOn = findViewById(R.id.silverfollowon);
        goldFOff = findViewById(R.id.goldfollowoff);
        goldFOn = findViewById(R.id.goldfollowon);
        if(userProfile.getFollowers() <= 99){
            bronzeFOff.setVisibility(View.VISIBLE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userProfile.getFollowers() >= 100 && userProfile.getFollowers() <= 999){
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.VISIBLE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userProfile.getFollowers() >= 1000 && userProfile.getFollowers() <= 10000){
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.VISIBLE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else{
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.VISIBLE);
        }
        bronzeCOff = findViewById(R.id.bronzecommentoff);
        bronzeCOn = findViewById(R.id.bronzecommenton);
        silverCOff = findViewById(R.id.silvercommentoff);
        silverCOn = findViewById(R.id.silvercommenton);
        goldCOff = findViewById(R.id.goldcommentoff);
        goldCOn = findViewById(R.id.goldcommenton);
        if(userProfile.getCommentScore() <= 99){
            bronzeCOff.setVisibility(View.VISIBLE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userProfile.getCommentScore() >= 100 && userProfile.getCommentScore() <= 999){
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.VISIBLE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userProfile.getCommentScore() >= 1000 && userProfile.getCommentScore() <= 10000){
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.VISIBLE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else{
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.VISIBLE);
        }

        ArrayList<String> followers = toArray(userProfile.getFollowing());
        boolean flag = false;
        for (String i : followers) {
            if(i.equals(userIzHome.getUserName())) flag = true;
        }
        if(flag){
            follow.setText("Unfollow");
        }else follow.setText("Follow");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!userIzHome.getUserName().equals("guest")) {
                        ArrayList<String> followers = toArray(userProfile.getFollowing());
                        boolean flag = false;
                        for (String i : followers) {
                            if (i.equals(userIzHome.getUserName())) flag = true;
                        }
                        if (!flag) {
                            userProfile.setFollowers(userProfile.getFollowers() + 1);
                            followers.add(userIzHome.getUserName());
                            userProfile.setFollowing(fromArray(followers));
                            userViewModel.update(userProfile);
                            Toast.makeText(getApplicationContext(), "Followed!", Toast.LENGTH_SHORT).show();
                            userProfile.this.onResume();
                        } else {
                            userProfile.setFollowers(userProfile.getFollowers() - 1);
                            followers.remove(userIzHome.getUserName());
                            userProfile.setFollowing(fromArray(followers));
                            userViewModel.update(userProfile);
                            Toast.makeText(getApplicationContext(), "Unfollowed!", Toast.LENGTH_SHORT).show();
                            userProfile.this.onResume();
                        }
                    }else Toast.makeText(getApplicationContext(), "Unregistered user!", Toast.LENGTH_SHORT).show();
                }
            });

    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
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


