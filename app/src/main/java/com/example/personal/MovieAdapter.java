//package com.example.personal;////package com.example.personal;
//////
//////import android.view.LayoutInflater;
//////import android.view.View;
//////import android.view.ViewGroup;
//////import android.widget.CheckBox;
//////import android.widget.TextView;
//////
//////import androidx.annotation.NonNull;
//////import androidx.recyclerview.widget.RecyclerView;
//////import database.FilmDatabase;
//////import model.Film;
//////
//////
//////import java.util.ArrayList;
//////import java.util.List;
//////
//////public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
//////
//////    private List<Film> movies = new ArrayList<>();
//////    private FilmDatabase filmDatabase;
//////
//////    // Constructor that initializes the movie list and the database
//////    public MovieAdapter(List<Film> movieList, FilmDatabase filmDatabase) {
//////        this.movies = movieList;
//////        this.filmDatabase = filmDatabase;
//////    }
//////
//////    @NonNull
//////    @Override
//////    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
//////        return new MovieViewHolder(view);
//////    }
//////
//////    @Override
//////    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
//////        Film film = movies.get(position);
//////        holder.textView.setText(film.getTitle());
//////        holder.checkBox.setChecked(film.getWatched());
//////
//////        // Set an OnCheckedChangeListener for the CheckBox
//////        holder.checkBox.setOnCheckedChangeListener(null); // Clear previous listener
//////        holder.checkBox.setChecked(film.getWatched()); // Ensure the current state is reflected
//////
//////        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//////            film.setWatched(isChecked); // Update the watched status in the Film object
//////
//////            // Update the film in the database on a background thread
//////            new Thread(() -> {
//////                filmDatabase.filmDao().updateFilm(film.getId(), film.getTitle(), isChecked); // Persist the change
//////            }).start();
//////        });
//////    }
//////
//////
//////    @Override
//////    public int getItemCount() {
//////        return movies.size();
//////    }
//////
//////    public void updateMovies(List<Film> movies) {
//////        this.movies = movies;
//////        notifyDataSetChanged();
//////    }
//////
//////    public static class MovieViewHolder extends RecyclerView.ViewHolder {
//////        TextView textView;
//////        CheckBox checkBox;
//////
//////        public MovieViewHolder(@NonNull View itemView) {
//////            super(itemView);
//////            textView = itemView.findViewById(R.id.tvTitle);
//////            checkBox = itemView.findViewById(R.id.chkWatched);
//////        }
//////    }
//////}
////
////package com.example.personal;
////
////import android.app.Activity;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.CheckBox;
////import android.widget.ImageView;
////import android.widget.TextView;
////import androidx.annotation.NonNull;
////import androidx.recyclerview.widget.RecyclerView;
////import database.FilmDatabase;
////import model.Film;
////
////import java.util.ArrayList;
////import java.util.List;
////
////public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
////
////    private List<Film> movies = new ArrayList<>();
////    private FilmDatabase filmDatabase;
////
////    public MovieAdapter(List<Film> movieList, FilmDatabase filmDatabase) {
////        this.movies = movieList;
////        this.filmDatabase = filmDatabase;
////    }
////
////    @NonNull
////    @Override
////    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
////        return new MovieViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
////        Film film = movies.get(position);
////        holder.textView.setText(film.getTitle());
////        holder.checkBox.setChecked(film.getWatched());
////
////        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
////            film.setWatched(isChecked);
////            // Update the film in the database here
////            new Thread(() -> filmDatabase.filmDao().updateFilm(film.getId(), film.getTitle(), isChecked)).start();
////        });
////
////        holder.deleteButton.setOnClickListener(v -> {
////            // Delete the movie from the database
////            new Thread(() -> {
////                filmDatabase.filmDao().deleteFilm(film.getId());
////
////                // Reload the movie list from the database
////                List<Film> updatedMovies = filmDatabase.filmDao().getAllFilms();
////
////                // Update the movies list and notify the adapter on the UI thread
////                ((Activity) holder.itemView.getContext()).runOnUiThread(() -> {
////                    movies.clear(); // Clear the existing list
////                    movies.addAll(updatedMovies); // Add the updated list
////                    notifyDataSetChanged(); // Notify the adapter of the changes
////                });
////            }).start();
////        });
////
////    }
////
////    @Override
////    public int getItemCount() {
////        return movies.size();
////    }
////
////    public void updateMovies(List<Film> movies) {
////        this.movies = movies;
////        notifyDataSetChanged();
////    }
////
////    public static class MovieViewHolder extends RecyclerView.ViewHolder {
////        TextView textView;
////        CheckBox checkBox;
////        ImageView deleteButton;
////
////        public MovieViewHolder(@NonNull View itemView) {
////            super(itemView);
////            textView = itemView.findViewById(R.id.tvTitle);
////            checkBox = itemView.findViewById(R.id.chkWatched);
////            deleteButton = itemView.findViewById(R.id.btnDelete);
////        }
////    }
////}
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.personal.R;
//import database.FilmDatabase;
//import model.Film;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {
//
//    private List<Film> movies;
//    private List<Film> moviesFull; // List to hold all movies for filtering
//    private FilmDatabase filmDatabase;
//
//    public MovieAdapter(List<Film> movieList, FilmDatabase filmDatabase) {
//        this.movies = movieList;
//        this.moviesFull = new ArrayList<>(movieList); // Copy the original list
//        this.filmDatabase = filmDatabase;
//    }
//
//    @NonNull
//    @Override
//    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
//        return new MovieViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
//        Film film = movies.get(position);
//        holder.textView.setText(film.getTitle());
//        holder.checkBox.setChecked(film.getWatched());
//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            film.setWatched(isChecked);
//            // Update the film in the database here
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return movies.size();
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                List<Film> filteredMovies = new ArrayList<>();
//                if (constraint == null || constraint.length() == 0) {
//                    filteredMovies.addAll(moviesFull); // No filter applied, show all
//                } else {
//                    String filterPattern = constraint.toString().toLowerCase().trim();
//                    for (Film film : moviesFull) {
//                        if (film.getTitle().toLowerCase().contains(filterPattern)) {
//                            filteredMovies.add(film);
//                        }
//                    }
//                }
//
//                FilterResults results = new FilterResults();
//                results.values = filteredMovies;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                movies.clear();
//                movies.addAll((List) results.values);
//                notifyDataSetChanged();
//            }
//        };
//    }
//
//    public static class MovieViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//        CheckBox checkBox;
//
//        public MovieViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.tvTitle);
//            checkBox = itemView.findViewById(R.id.chkWatched);
//        }
//    }
//}


package com.example.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import database.FilmDatabase;
import model.Film;
import android.widget.Filter;
import android.widget.Filterable;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {

    private List<Film> movies; // Original list of movies
    private List<Film> filteredMovies; // List for filtered results
    private FilmDatabase filmDatabase;

    // Constructor
    public MovieAdapter(List<Film> movies, FilmDatabase filmDatabase) {
        this.movies = movies;
        this.filteredMovies = new ArrayList<>(movies); // Copy of the original list for filtering
        this.filmDatabase = filmDatabase;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Film film = filteredMovies.get(position); // Use the filtered list
        holder.tvTitle.setText(film.getTitle());
        holder.chkWatched.setChecked(film.getWatched());

        // Handle delete button click
        holder.btnDelete.setOnClickListener(v -> {
            new Thread(() -> {
                filmDatabase.filmDao().deleteFilm(film.getId()); // Delete movie from database
                movies.remove(film); // Remove movie from the original list
                filteredMovies.remove(position); // Remove movie from the filtered list
                notifyItemRemoved(position); // Notify the adapter about the item removal
            }).start();
        });
    }

    @Override
    public int getItemCount() {
        return filteredMovies.size(); // Return the size of the filtered movie list
    }

    // Method to update movies
    public void updateMovies(List<Film> newMovies) {
        this.movies.clear(); // Clear the current list
        this.movies.addAll(newMovies); // Add the new movies
        this.filteredMovies.clear(); // Also clear the filtered list
        this.filteredMovies.addAll(newMovies); // Add new movies to the filtered list
        notifyDataSetChanged(); // Notify the adapter that data has changed
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Film> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(movies); // No filter applied
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Film film : movies) {
                        if (film.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredList.add(film); // Add matching film to the filtered list
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList; // Set the filtered list as the result
                return results;
            }

            @Override
            @SuppressWarnings("unchecked")
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredMovies.clear(); // Clear the current filtered list
                filteredMovies.addAll((List<Film>) results.values); // Add the new filtered results
                notifyDataSetChanged(); // Notify the adapter of the changes
            }
        };
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CheckBox chkWatched;
        ImageView btnDelete;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            chkWatched = itemView.findViewById(R.id.chkWatched);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
