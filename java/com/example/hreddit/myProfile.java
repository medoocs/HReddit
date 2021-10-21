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

import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class myProfile  extends AppCompatActivity {
    MenuItem menuItem;
    // badge text view
    TextView badgeCounter;
    ImageView bell;
    // change the number to see badge in action
    int pendingNotifications;

    final int REQUEST_CODE_GALLERY = 999;
    CircleImageView profilePicture;
    EditText aboutMe;
    TextView forumScore, commentScore, followers;
    Button edit;

    ImageView bronzeFrOff, bronzeFrOn, silverFrOff, silverFrOn, goldFrOff, goldFrOn;
    ImageView bronzeCOff, bronzeCOn, silverCOff, silverCOn, goldCOff, goldCOn;
    ImageView bronzeFOff, bronzeFOn, silverFOff, silverFOn, goldFOff, goldFOn;


    private User userIzHome;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");
        ArrayList<String> tmp = toArray(userIzHome.getNotificationForums());
        tmp.remove(0);
        pendingNotifications = tmp.size();
        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(userIzHome.getUserName() + "'s profile");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        profilePicture = findViewById(R.id.profilepicture);
        setImageViewWithByteArray(profilePicture, userIzHome.getImage());

        aboutMe = findViewById(R.id.aboutme);
        aboutMe.setText(userIzHome.getAboutMe());

        edit = findViewById(R.id.button);

        forumScore = findViewById(R.id.forums);
        forumScore.setText(Integer.toString(userIzHome.getForumScore()));
        commentScore = findViewById(R.id.comments);
        commentScore.setText(Integer.toString(userIzHome.getCommentScore()));
        followers = findViewById(R.id.followers);
        followers.setText(Integer.toString(userIzHome.getFollowers()));

        bronzeFrOff = findViewById(R.id.bronzeforumoff);
        bronzeFrOn = findViewById(R.id.bronzeforumon);
        silverFrOff = findViewById(R.id.silverforumoff);
        silverFrOn = findViewById(R.id.silverforumon);
        goldFrOff = findViewById(R.id.goldforumoff);
        goldFrOn = findViewById(R.id.goldforumon);
        if(userIzHome.getForumScore() <= 99){
            bronzeFrOff.setVisibility(View.VISIBLE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userIzHome.getForumScore() >= 100 && userIzHome.getForumScore() <= 999){
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.VISIBLE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userIzHome.getForumScore() >= 1000 && userIzHome.getForumScore() <= 10000){
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
        if(userIzHome.getFollowers() <= 99){
            bronzeFOff.setVisibility(View.VISIBLE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userIzHome.getFollowers() >= 100 && userIzHome.getFollowers() <= 999){
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.VISIBLE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userIzHome.getFollowers() >= 1000 && userIzHome.getFollowers() <= 10000){
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
        if(userIzHome.getCommentScore() <= 99){
            bronzeCOff.setVisibility(View.VISIBLE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userIzHome.getCommentScore() >= 100 && userIzHome.getCommentScore() <= 999){
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.VISIBLE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userIzHome.getCommentScore() >= 1000 && userIzHome.getCommentScore() <= 10000){
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

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        myProfile.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    userIzHome.setAboutMe(aboutMe.getText().toString().trim());
                    userIzHome.setImage(imageViewToByte(profilePicture));
                    userViewModel.update(userIzHome);
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();

        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome");
        ArrayList<String> tmp = toArray(userIzHome.getNotificationForums());
        tmp.remove(0);
        pendingNotifications = tmp.size();
        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(userIzHome.getUserName() + "'s profile");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        profilePicture = findViewById(R.id.profilepicture);

        aboutMe = findViewById(R.id.aboutme);
        aboutMe.setText(userIzHome.getAboutMe());

        edit = findViewById(R.id.button);

        forumScore = findViewById(R.id.forums);
        forumScore.setText(Integer.toString(userIzHome.getForumScore()));
        commentScore = findViewById(R.id.comments);
        commentScore.setText(Integer.toString(userIzHome.getCommentScore()));
        followers = findViewById(R.id.followers);
        followers.setText(Integer.toString(userIzHome.getFollowers()));

        bronzeFrOff = findViewById(R.id.bronzeforumoff);
        bronzeFrOn = findViewById(R.id.bronzeforumon);
        silverFrOff = findViewById(R.id.silverforumoff);
        silverFrOn = findViewById(R.id.silverforumon);
        goldFrOff = findViewById(R.id.goldforumoff);
        goldFrOn = findViewById(R.id.goldforumon);
        if(userIzHome.getForumScore() <= 99){
            bronzeFrOff.setVisibility(View.VISIBLE);
            bronzeFrOn.setVisibility(View.GONE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userIzHome.getForumScore() >= 100 && userIzHome.getForumScore() <= 999){
            bronzeFrOff.setVisibility(View.GONE);
            bronzeFrOn.setVisibility(View.VISIBLE);
            silverFrOff.setVisibility(View.GONE);
            silverFrOn.setVisibility(View.GONE);
            goldFrOff.setVisibility(View.GONE);
            goldFrOn.setVisibility(View.GONE);
        }else if(userIzHome.getForumScore() >= 1000 && userIzHome.getForumScore() <= 10000){
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
        if(userIzHome.getFollowers() <= 99){
            bronzeFOff.setVisibility(View.VISIBLE);
            bronzeFOn.setVisibility(View.GONE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userIzHome.getFollowers() >= 100 && userIzHome.getFollowers() <= 999){
            bronzeFOff.setVisibility(View.GONE);
            bronzeFOn.setVisibility(View.VISIBLE);
            silverFOff.setVisibility(View.GONE);
            silverFOn.setVisibility(View.GONE);
            goldFOff.setVisibility(View.GONE);
            goldFOn.setVisibility(View.GONE);
        }else if(userIzHome.getFollowers() >= 1000 && userIzHome.getFollowers() <= 10000){
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
        if(userIzHome.getCommentScore() <= 99){
            bronzeCOff.setVisibility(View.VISIBLE);
            bronzeCOn.setVisibility(View.GONE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userIzHome.getCommentScore() >= 100 && userIzHome.getCommentScore() <= 999){
            bronzeCOff.setVisibility(View.GONE);
            bronzeCOn.setVisibility(View.VISIBLE);
            silverCOff.setVisibility(View.GONE);
            silverCOn.setVisibility(View.GONE);
            goldCOff.setVisibility(View.GONE);
            goldCOn.setVisibility(View.GONE);
        }else if(userIzHome.getCommentScore() >= 1000 && userIzHome.getCommentScore() <= 10000){
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

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        myProfile.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    userIzHome.setAboutMe(aboutMe.getText().toString().trim());
                    byte[] b = imageViewToByte(profilePicture);
                    userIzHome.setImage(imageViewToByte(profilePicture));
                    userViewModel.update(userIzHome);
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                profilePicture.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }

    //app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myprofilemenu, menu);
        menuItem = menu.findItem(R.id.nav_notification);

        // check if any pending notification
        if (pendingNotifications == 0) {
            // if no pending notification remove badge
            menuItem.setActionView(null);
        } else {

            // if notification than set the badge icon layout
            menuItem.setActionView(R.layout.notification_badge);
            // get the view from the nav item
            View view = menuItem.getActionView();
            bell = view.findViewById(R.id.bell);
            bell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(myProfile.this, notificationForums.class);
                    intent.putExtra("UserIzHome", userIzHome);
                    startActivity(intent);
                }
            });
            // get the text view of the action view for the nav item
            badgeCounter = view.findViewById(R.id.badge_counter);
            // set the pending notifications value
            badgeCounter.setText(String.valueOf(pendingNotifications));
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.nav_notification:
                Toast.makeText(getApplicationContext(), "You don't have any new notifications!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.myprofile:
                Intent intent0 = new Intent(myProfile.this, myProfile.class);
                intent0.putExtra("UserIzHome", userIzHome);
                startActivity(intent0);
                return true;
            case R.id.mycomments:
                Intent intent1 = new Intent(myProfile.this, MyComments.class);
                intent1.putExtra("UserIzHome", userIzHome);
                startActivity(intent1);
                return true;
            case R.id.myforums:
                Intent intent2 = new Intent(myProfile.this, MyForums.class);
                intent2.putExtra("UserIzHome", userIzHome);
                startActivity(intent2);
                return true;
            case R.id.password:
                Intent intent3 = new Intent(myProfile.this, PasswordChange.class);
                intent3.putExtra("UserIzHome", userIzHome);
                startActivity(intent3);
                return true;
            case R.id.logout:
                Intent intent4 = new Intent(myProfile.this, MainActivity.class);
                startActivity(intent4);
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

