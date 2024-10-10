//package com.example.personal;
//
//import android.annotation.SuppressLint;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import androidx.appcompat.app.AppCompatActivity;
//import database.FilmDatabase;
//import model.Film;
//
//public class AddMovieActivity extends AppCompatActivity {
//
//    private EditText editTextTitle;
//    private CheckBox checkBoxWatched;
//    private Button buttonSave;
//    private FilmDatabase filmDatabase;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_movie);
//
//        editTextTitle = findViewById(R.id.editTextTitle);
//        checkBoxWatched = findViewById(R.id.checkBoxWatched);
//        buttonSave = findViewById(R.id.buttonSave);
//
//        filmDatabase = FilmDatabase.getDatabase(this);
//
//        buttonSave.setOnClickListener(v -> saveMovie());
//    }
//
//    private void saveMovie() {
//        Film film = new Film();
//        film.setTitle(editTextTitle.getText().toString());
//        film.setWatched(checkBoxWatched.isChecked());
//
//        // Use AsyncTask to save movie in the database
//        new SaveMovieTask().execute(film);
//    }
//
//    private class SaveMovieTask extends AsyncTask<Film, Void, Void> {
//        @Override
//        protected Void doInBackground(Film... films) {
//            filmDatabase.filmDao().insert(films[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            finish(); // Finish the activity after saving
//        }
//    }
//}


package com.example.personal;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import database.FilmDatabase;
import model.Film;

public class AddMovieActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private CheckBox checkBoxWatched;
    private Button buttonSave;
    private FilmDatabase filmDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextTitle = findViewById(R.id.editTextTitle);
        checkBoxWatched = findViewById(R.id.checkBoxWatched);
        buttonSave = findViewById(R.id.buttonSave);

        filmDatabase = FilmDatabase.getDatabase(this);

        buttonSave.setOnClickListener(v -> saveMovie());
    }

    private void saveMovie() {
        Film film = new Film();
        film.setTitle(editTextTitle.getText().toString());
        film.setWatched(checkBoxWatched.isChecked());

        // Use AsyncTask to save movie in the database
        new SaveMovieTask().execute(film);
    }

    private class SaveMovieTask extends AsyncTask<Film, Void, Void> {
        @Override
        protected Void doInBackground(Film... films) {
            filmDatabase.filmDao().insert(films[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setResult(RESULT_OK); // Set the result to OK
            finish(); // Finish the activity after saving
        }
    }
}
