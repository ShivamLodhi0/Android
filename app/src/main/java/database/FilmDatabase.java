package database;

import DAO.FilmDao;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import model.Film;


@Database(entities = {Film.class}, version = 1)
public abstract class FilmDatabase extends RoomDatabase {

    private static volatile FilmDatabase INSTANCE;

    public abstract FilmDao filmDao();

    public static FilmDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FilmDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FilmDatabase.class, "film_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
