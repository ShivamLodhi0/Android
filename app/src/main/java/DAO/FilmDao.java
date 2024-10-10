package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import model.Film;


import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insert(Film film);

    @Query("SELECT * FROM films")
    List<Film> getAllFilms();

    @Query("DELETE FROM films WHERE id = :filmId")
    void deleteFilm(int filmId);

    @Query("UPDATE films SET title = :title, watched = :watched WHERE id = :id")
    void updateFilm(int id, String title, boolean watched);
}
