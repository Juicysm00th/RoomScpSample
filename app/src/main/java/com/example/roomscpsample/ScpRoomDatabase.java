package com.example.roomscpsample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Scp.class}, version = 1, exportSchema = false)
public abstract class ScpRoomDatabase extends RoomDatabase {
    public abstract ScpDao scpDao();
    private static ScpRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ScpDao mDao;
        String[] scps = {"Indestructable Reptile", "The Statue", "The Old Man"};

        PopulateDbAsync(ScpRoomDatabase db) {
            mDao = db.scpDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // If we have no words, then create the initial list of words
            if (mDao.getAnyScp().length < 1) {
                for (int i = 0; i <= scps.length - 1; i++) {
                    Scp scp = new Scp(scps[i]);
                    mDao.insert(scp);
                }
            }
            return null;
        }
    }

    public static ScpRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ScpRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScpRoomDatabase.class, "scp_database")
                            // Wipes and rebuilds instead of migratong
                            // if no Migration Object
                            // Migration is not part of this practical
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
