package com.example.hreddit;

/*import com.example.hreddit.databinding.ActivityHomeScreenBinding;
import androidx.databinding.DataBindingUtil;*/

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hreddit.Data.ForumAdapter;
import com.example.hreddit.Data.ForumViewModel;
import com.example.hreddit.Data.UserViewModel;
import com.example.hreddit.Model.Forum;
import com.example.hreddit.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class homeScreen extends AppCompatActivity{
    public static final int NEW_POST_ACTIVITY_REQUEST_CODE = 1;

    String[] kategorija = {"", "", "", "", "", "", "", ""};
    String sort = "asc";
    String search = "%%";
    ImageView bookoff, foodoff, gamesoff, moviesoff, musicoff, natureoff, placesoff, techoff,
              bookon, foodon, gameson, movieson, musicon, natureon, placeson, techon;

    private ForumViewModel forumViewModel;
    private UserViewModel userViewModel;
    private User userIzMain;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userIzMain = (User) getIntent().getSerializableExtra("UserIzMain");
        if(!userIzMain.getUserName().equals("guest")){
            userIzMain = userViewModel.getUsername(userIzMain.getUserName());
            username = userIzMain.getUserName();
        }else{
            userIzMain = new User("guest", "guest", "guest", "", 0, 0, 0, null, "", "");
            username = "guest";
        }

        //ekran
        RecyclerView recyclerView = findViewById(R.id.listForuma);
        final ForumAdapter adapter = new ForumAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        //iz logina user
        if (!username.isEmpty()) {
            setTitle("Welcome " + username);
        }

        //kategorije
        natureoff = (ImageView) findViewById(R.id.btnNatureOff);
        natureon = (ImageView) findViewById(R.id.btnNatureOn);
        natureoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(natureoff, natureon);kategorija[0] = "Nature";
                homeScreen.this.onResume();
            }
        });
        natureon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(natureoff, natureon);kategorija[0] = "";
                homeScreen.this.onResume();
            }
        });

        bookoff = (ImageView) findViewById(R.id.btnBookOff);
        bookon = (ImageView) findViewById(R.id.btnBookOn);
        bookoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(bookoff, bookon);kategorija[1] = "Book";
                homeScreen.this.onResume();
            }
        });
        bookon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(bookoff, bookon);kategorija[1] = "";
                homeScreen.this.onResume();
            }
        });

        foodoff = (ImageView) findViewById(R.id.btnFoodOff);
        foodon = (ImageView) findViewById(R.id.btnFoodOn);
        foodoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(foodoff, foodon);kategorija[2] = "Food";
                homeScreen.this.onResume();
            }
        });
        foodon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(foodoff, foodon);kategorija[2] = "";
                homeScreen.this.onResume();
            }
        });

        gamesoff = (ImageView) findViewById(R.id.btnGamesOff);
        gameson = (ImageView) findViewById(R.id.btnGamesOn);
        gamesoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(gamesoff, gameson);kategorija[3] = "Games";
                homeScreen.this.onResume();
            }
        });
        gameson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(gamesoff, gameson);kategorija[3] = "";
                homeScreen.this.onResume();
            }
        });

        moviesoff = (ImageView) findViewById(R.id.btnMoviesOff);
        movieson = (ImageView) findViewById(R.id.btnMoviesOn);
        moviesoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(moviesoff, movieson);kategorija[4] = "Movies";
                homeScreen.this.onResume();
            }
        });
        movieson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(moviesoff, movieson);kategorija[4] = "";
                homeScreen.this.onResume();
            }
        });

        musicoff = (ImageView) findViewById(R.id.btnMusicOff);
        musicon = (ImageView) findViewById(R.id.btnMusicOn);
        musicoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(musicoff, musicon);kategorija[5] = "Music";
                homeScreen.this.onResume();
            }
        });
        musicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(musicoff, musicon);kategorija[5] = "";
                homeScreen.this.onResume();
            }
        });

        placesoff = (ImageView) findViewById(R.id.btnPlacesOff);
        placeson = (ImageView) findViewById(R.id.btnPlacesOn);
        placesoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(placesoff, placeson);kategorija[6] = "Places";
                homeScreen.this.onResume();
            }
        });
        placeson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(placesoff, placeson);kategorija[6] = "";
                homeScreen.this.onResume();
            }
        });

        techoff = (ImageView) findViewById(R.id.btnTechOff);
        techon = (ImageView) findViewById(R.id.btnTechOn);
        techoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upali(techoff, techon);kategorija[7] = "Technology";
                homeScreen.this.onResume();
            }
        });
        techon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ugasi(techoff, techon);kategorija[7] = "";
                homeScreen.this.onResume();
            }
        });

        //add post
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userIzMain.getUserName().equals("guest")) {
                    Intent intent = new Intent(homeScreen.this, newPost.class);
                    startActivityForResult(intent, NEW_POST_ACTIVITY_REQUEST_CODE);
                }else Toast.makeText(homeScreen.this, "Unregistered user", Toast.LENGTH_SHORT).show();
            }
        });

        forumViewModel.getAllForums(kategorija, sort, search).observe(this, new Observer<List<Forum>>() {
            @Override
            public void onChanged(@Nullable final List<Forum> forums) {
                adapter.submitList(forums);
            }
        });
        adapter.setOnItemClickListener(new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Forum forum) {
                Intent intent = new Intent(homeScreen.this, forumScreen.class);
                intent.putExtra("UserIzHome", userIzMain);
                intent.putExtra("Forum", forum);
                startActivity(intent);
            }
        });

        if(userIzMain.getUserName().equals("admin")) {
            adapter.setOnLongClickListener(new ForumAdapter.OnLongClickListener() {
                @Override
                public void onLongTouch(Forum forum) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(homeScreen.this);
                    alert.setTitle("Alert!");
                    alert.setMessage("Are you sure to delete forum");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            forumViewModel.delete(forum);
                            homeScreen.this.onResume();
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
        userIzMain = (User) getIntent().getSerializableExtra("UserIzMain");
        if(!userIzMain.getUserName().equals("guest")){
            userIzMain = userViewModel.getUsername(userIzMain.getUserName());
            username = userIzMain.getUserName();
        }else{
            userIzMain = new User("guest", "guest", "guest", "", 0, 0, 0, null, "", "");
            username = "guest";
        }
        //ekran
        RecyclerView recyclerView = findViewById(R.id.listForuma);
        final ForumAdapter adapter = new ForumAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);

        forumViewModel.getAllForums(kategorija, sort, search).observe(this, new Observer<List<Forum>>() {
            @Override
            public void onChanged(@Nullable final List<Forum> forums) {
                adapter.submitList(forums);
            }
        });
        adapter.setOnItemClickListener(new ForumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Forum forum) {
                Intent intent = new Intent(homeScreen.this, forumScreen.class);
                intent.putExtra("UserIzHome", userIzMain);
                intent.putExtra("Forum", forum);
                startActivity(intent);
            }
        });
        if(userIzMain.getUserName().equals("admin")) {
            adapter.setOnLongClickListener(new ForumAdapter.OnLongClickListener() {
                @Override
                public void onLongTouch(Forum forum) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(homeScreen.this);
                    alert.setTitle("Alert!");
                    alert.setMessage("Are you sure to delete forum");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            forumViewModel.delete(forum);
                            homeScreen.this.onResume();
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

        if (requestCode == NEW_POST_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String strDate = dateFormat.format(date);


            Forum forum = new Forum(data.getStringExtra(newPost.EXTRA_NASLOV_POST),
                    data.getStringExtra(newPost.EXTRA_TEXT_POST),
                    data.getStringExtra(newPost.EXTRA_KATEGORIJA_POST),
                    username,
                    strDate,
                    0,
                    0,
                    0);
            forumViewModel.insert(forum);
            forum = forumViewModel.getForumByAll(forum.getNaslov(), forum.getTekst(), forum.getUser(), forum.getDatum());
            userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            ArrayList<String> followers = toArray(userIzMain.getFollowing());
            for (String i : followers) {
                if(i.equals("")) continue;
                User pushUser = userViewModel.getUsername(i);
                ArrayList<String> forumsToPush = toArray(pushUser.getNotificationForums());
                forumsToPush.add(Integer.toString(forum.getId()));
                pushUser.setNotificationForums(fromArray(forumsToPush));
                userViewModel.update(pushUser);
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    //app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_search_home, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem profileItem = menu.findItem(R.id.myprofile);
        if(username.equals("guest") || username.equals("admin")){
            profileItem.setVisible(false);
        }
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search By Title...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                search = "%" + newText + "%";
                homeScreen.this.onResume();
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myprofile:
                Intent intent = new Intent(homeScreen.this, myProfile.class);
                intent.putExtra("UserIzHome", userIzMain);
                startActivity(intent);
                return true;
            case R.id.sort:
                return true;
            case R.id.sortAsc:
                sort = "asc";
                homeScreen.this.onResume();
                return true;
            case R.id.sortDsc:
                sort = "desc";
                homeScreen.this.onResume();
                return true;
            case R.id.logout:
                Intent intent4 = new Intent(homeScreen.this, MainActivity.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //ikonice kategorije
    private void ugasi(ImageView imageoff, ImageView imageon) {
        imageoff.setVisibility(View.VISIBLE);
        imageon.setVisibility(View.GONE);
    }

    private void upali(ImageView imageoff, ImageView imageon) {
        imageon.setVisibility(View.VISIBLE);
        imageoff.setVisibility(View.GONE);
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
