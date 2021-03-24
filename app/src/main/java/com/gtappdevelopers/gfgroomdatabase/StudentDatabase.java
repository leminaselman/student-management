package com.gtappdevelopers.gfgroomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {StudentModal.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase{
    private static StudentDatabase instance;
    public abstract StudentDao studentDao();

    public static synchronized StudentDatabase getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase.class, "student_database")
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
        PopulateDbAsyncTask(StudentDatabase instance) {
            StudentDao dao = instance.studentDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
