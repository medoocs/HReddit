package com.example.hreddit.Data;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.os.AsyncTask;
        import android.widget.ImageView;

        import androidx.annotation.NonNull;
        import androidx.room.Database;
        import androidx.room.Room;
        import androidx.room.RoomDatabase;
        import androidx.sqlite.db.SupportSQLiteDatabase;

        import com.example.hreddit.MainActivity;
        import com.example.hreddit.Model.Forum;
        import com.example.hreddit.Model.ForumStats;
        import com.example.hreddit.Model.Komentar;
        import com.example.hreddit.Model.KomentarStats;
        import com.example.hreddit.Model.User;

        import java.io.ByteArrayOutputStream;


@Database(entities = {User.class, Forum.class, Komentar.class, KomentarStats.class, ForumStats.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {
    private static UserDataBase instance;
    public abstract UserDao getUserDao();
    public abstract ForumDao getForumDao();
    public abstract ForumStatsDao getForumStatsDao();
    public abstract KomentarStatsDao getKomentarStatsDao();

    public static synchronized UserDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDataBase.class, "HReddit-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ForumDao forumDao;
        private UserDao userDao;
        private KomentarDao komentarDao;

        private PopulateDbAsyncTask(UserDataBase db) {
            forumDao = db.getForumDao();
            userDao = db.getUserDao();
            komentarDao = db.getKomentarDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("admin", "admin", "admin@admin.com", "admin", 0, 0, 0, null, "", ""));

            return null;
        }
    }

    public abstract KomentarDao getKomentarDao();

}
