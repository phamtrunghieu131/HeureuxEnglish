package com.example.heureuxenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Create();

        getFragmentManager().beginTransaction().replace(R.id.frameLayout_container,new FragmentHomePage()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.item_homepage:
                    selectedFragment = new FragmentHomePage();
                    break;
                case R.id.item_learnedWords:
                    selectedFragment = new FragmentLearnedWords();
                    break;
                case R.id.item_game:
                    selectedFragment = new FragmentGame();
                    break;
                case R.id.item_profile:
                    selectedFragment = new FragmentProfile();
                    break;
            }
            getFragmentManager().beginTransaction().replace(R.id.frameLayout_container,selectedFragment).commit();
            return true;
        }
    };

    private void Create() {
        bottomNavigationView = findViewById(R.id.bottomNavigation_view);
    }
}