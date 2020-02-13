package no.hvl.dat153.namequiz;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Person.class, version = 1, exportSchema = false)
public abstract class RoomDBQuiz extends RoomDatabase {

    public abstract PersonDao personDAO();

    private static volatile RoomDBQuiz personRoomInstance;

    static RoomDBQuiz getDatabase (final Context context) {
        if (personRoomInstance == null) {
            synchronized (RoomDBQuiz.class) {
                if (personRoomInstance == null) {
                    personRoomInstance = Room.databaseBuilder(context.getApplicationContext(), RoomDBQuiz.class, "person_database").build();
                }
            }
        }
        return personRoomInstance;
    }

}
