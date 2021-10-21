package com.example.hreddit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hreddit.Data.ForumDao;
import com.example.hreddit.Data.ForumViewModel;
import com.example.hreddit.Data.UserDataBase;
import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class newPost extends AppCompatActivity {
    public static final String EXTRA_NASLOV_POST = "naslovpost";
    public static final String EXTRA_TEXT_POST = "textpost";
    public static final String EXTRA_KATEGORIJA_POST = "kategorijapost";

    ImageView bookoff, foodoff, gamesoff, moviesoff, musicoff, natureoff, placesoff, techoff,
            bookon, foodon, gameson, movieson, musicon, natureon, placeson, techon;

    String kategorija ="";

    EditText editnaslov, edittekst;

    Button post;

    private User userIzHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        userIzHome = (User) getIntent().getSerializableExtra("UserIzHome>");

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editnaslov = (EditText) findViewById(R.id.editNaslov);
        edittekst = (EditText) findViewById(R.id.editText);

        post = (Button) findViewById(R.id.button);

        //kategorije
        natureoff = (ImageView) findViewById(R.id.btnNatureOff);
        natureon = (ImageView) findViewById(R.id.btnNatureOn);
        natureoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(natureoff, natureon);kategorija = "Nature";
            }
        });
        natureon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(natureoff, natureon);kategorija = "";
            }
        });

        bookoff = (ImageView) findViewById(R.id.btnBookOff);
        bookon = (ImageView) findViewById(R.id.btnBookOn);
        bookoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(bookoff, bookon);kategorija = "Book";
            }
        });
        bookon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(bookoff, bookon);kategorija = "";
            }
        });

        foodoff = (ImageView) findViewById(R.id.btnFoodOff);
        foodon = (ImageView) findViewById(R.id.btnFoodOn);
        foodoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(foodoff, foodon);kategorija = "Food";
            }
        });
        foodon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(foodoff, foodon);kategorija = "";
            }
        });

        gamesoff = (ImageView) findViewById(R.id.btnGamesOff);
        gameson = (ImageView) findViewById(R.id.btnGamesOn);
        gamesoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(gamesoff, gameson);kategorija = "Games";
            }
        });
        gameson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(gamesoff, gameson);kategorija = "";
            }
        });

        moviesoff = (ImageView) findViewById(R.id.btnMoviesOff);
        movieson = (ImageView) findViewById(R.id.btnMoviesOn);
        moviesoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(moviesoff, movieson);kategorija = "Movies";
            }
        });
        movieson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(moviesoff, movieson);kategorija = "";
            }
        });

        musicoff = (ImageView) findViewById(R.id.btnMusicOff);
        musicon = (ImageView) findViewById(R.id.btnMusicOn);
        musicoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(musicoff, musicon);kategorija = "Music";
            }
        });
        musicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(musicoff, musicon);kategorija = "";
            }
        });

        placesoff = (ImageView) findViewById(R.id.btnPlacesOff);
        placeson = (ImageView) findViewById(R.id.btnPlacesOn);
        placesoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(placesoff, placeson);kategorija = "Places";
            }
        });
        placeson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(placesoff, placeson);kategorija = "";
            }
        });

        techoff = (ImageView) findViewById(R.id.btnTechOff);
        techon = (ImageView) findViewById(R.id.btnTechOn);
        techoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(techoff, techon);kategorija = "Technology";
            }
        });
        techon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(techoff, techon);kategorija = "";
            }
        });

        //post batun
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();

                String naslov = editnaslov.getText().toString().trim();
                String tekst = edittekst.getText().toString().trim();

                if (!naslov.isEmpty() && !tekst.isEmpty() && !kategorija.isEmpty()) {
                    replyIntent.putExtra(EXTRA_NASLOV_POST, naslov);
                    replyIntent.putExtra(EXTRA_TEXT_POST, tekst);
                    replyIntent.putExtra(EXTRA_KATEGORIJA_POST, kategorija);
                    replyIntent.putExtra("UserIzMain", userIzHome);
                    setResult(RESULT_OK, replyIntent);
                } else{
                    if(kategorija.isEmpty())
                        Toast.makeText(newPost.this, "You can't leave category empty!", Toast.LENGTH_SHORT).show();
                    else if(naslov.isEmpty())
                        Toast.makeText(newPost.this, "You can't leave title empty!", Toast.LENGTH_SHORT).show();
                    else if(tekst.isEmpty())
                        Toast.makeText(newPost.this, "You can't leave post empty!", Toast.LENGTH_SHORT).show();
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

    private void ugasi(ImageView imageoff, ImageView imageon) {
        bookoff.setVisibility(View.VISIBLE);
        bookon.setVisibility(View.GONE);

        foodoff.setVisibility(View.VISIBLE);
        foodon.setVisibility(View.GONE);

        gamesoff.setVisibility(View.VISIBLE);
        gameson.setVisibility(View.GONE);

        moviesoff.setVisibility(View.VISIBLE);
        movieson.setVisibility(View.GONE);

        musicoff.setVisibility(View.VISIBLE);
        musicon.setVisibility(View.GONE);

        natureoff.setVisibility(View.VISIBLE);
        natureon.setVisibility(View.GONE);

        placesoff.setVisibility(View.VISIBLE);
        placeson.setVisibility(View.GONE);

        techoff.setVisibility(View.VISIBLE);
        techon.setVisibility(View.GONE);

        imageoff.setVisibility(View.VISIBLE);
        imageon.setVisibility(View.GONE);
    }

    private void upali(ImageView imageoff, ImageView imageon) {
        bookoff.setVisibility(View.VISIBLE);
        bookon.setVisibility(View.GONE);

        foodoff.setVisibility(View.VISIBLE);
        foodon.setVisibility(View.GONE);

        gamesoff.setVisibility(View.VISIBLE);
        gameson.setVisibility(View.GONE);

        moviesoff.setVisibility(View.VISIBLE);
        movieson.setVisibility(View.GONE);

        musicoff.setVisibility(View.VISIBLE);
        musicon.setVisibility(View.GONE);

        natureoff.setVisibility(View.VISIBLE);
        natureon.setVisibility(View.GONE);

        placesoff.setVisibility(View.VISIBLE);
        placeson.setVisibility(View.GONE);

        techoff.setVisibility(View.VISIBLE);
        techon.setVisibility(View.GONE);

        imageoff.setVisibility(View.GONE);
        imageon.setVisibility(View.VISIBLE);
    }
}
