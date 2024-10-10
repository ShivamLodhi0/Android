package com.example.personal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import database.FilmDatabase;
import model.Film;

import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private Button btnAddMovie;
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private FilmDatabase filmDatabase;
    private SearchView searchView; // Declare SearchView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        filmDatabase = FilmDatabase.getDatabase(this);

        btnAddMovie = findViewById(R.id.btnAddMovie);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        searchView = findViewById(R.id.searchView); // Initialize SearchView

        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        // Load initial movies from the database
        loadMovies();

        btnAddMovie.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesActivity.this, AddMovieActivity.class);
            startActivityForResult(intent, 1); // Start AddMovieActivity with request code
        });

        // Set up SearchView functionality
        setupSearchView();
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (movieAdapter != null) {
                    movieAdapter.getFilter().filter(newText); // Filter movies as the user types
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Refresh movie list after returning from AddMovieActivity
            loadMovies();
        }
    }

    private void loadMovies() {
        new Thread(() -> {
            List<Film> movieList = filmDatabase.filmDao().getAllFilms(); // Fetch all films
            runOnUiThread(() -> {
                if (movieAdapter == null) {
                    movieAdapter = new MovieAdapter(movieList, filmDatabase); // Initialize the adapter if null
                    recyclerViewMovies.setAdapter(movieAdapter); // Set the adapter
                } else {
                    movieAdapter.updateMovies(movieList); // Update the adapter if it already exists
                }
            });
        }).start();
    }
}
