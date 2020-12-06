package com.example.heureuxenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewWordActivity extends AppCompatActivity {

    TextView newWordText,translateText,englishExText,vietnameseExText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        creat();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .getValue(User.class);
                int size = user.getLearnedWords().size();
                Word word = snapshot.child("word").child(size+"").getValue(Word.class);
                newWordText.setText(word.getWordInEnglish());
                translateText.setText(word.getWordInVietnamese());
                englishExText.setText(word.getExampleInEnglish());
                vietnameseExText.setText(word.getExampleInVietnamese());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void creat() {
        newWordText = findViewById(R.id.textView);
        translateText = findViewById(R.id.textView4);
        englishExText = findViewById(R.id.textView6);
        vietnameseExText = findViewById(R.id.vietnameseExample);
    }
}