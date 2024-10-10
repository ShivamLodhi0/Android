package com.example.personal;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);

        // Example categories
        List<String> categories = Arrays.asList("Movies");

        categoryAdapter = new CategoryAdapter(categories, this::onCategoryClicked);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategories.setAdapter(categoryAdapter);
    }

    private void onCategoryClicked(String category) {
        if (category.equals("Movies")) {
            Intent intent = new Intent(this, MoviesActivity.class);
            startActivity(intent);
        }
    }
}
