package com.example.heureuxenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class QuestionActivity extends AppCompatActivity {

    TextView questionText,answer_1,answer_2,answer_3,answer_4;
    User user;
    Word word;
    Question question;
    ArrayList<Answer> answers = new ArrayList<Answer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        creat();

        Intent intent = getIntent();
        int count = intent.getIntExtra("count",10);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("word").child(count+"").child("mcQuestion").child("listAnswer")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    answers.clear();

                    for(DataSnapshot postSnapshot : snapshot.getChildren()){
                        Answer answer = postSnapshot.getValue(Answer.class);
                        answers.add(answer);
                    }
                    answer_1.setText(answers.get(0).getContent());
                    answer_2.setText(answers.get(1).getContent());
                    answer_3.setText(answers.get(2).getContent());
                    answer_4.setText(answers.get(3).getContent());
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mDatabase.child("word").child(count+"").child("mcQuestion").child("question")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String question = snapshot.getValue(String.class);
                questionText.setText(question+"...");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void creat() {
        questionText = findViewById(R.id.textView7);
        answer_1 = findViewById(R.id.textView8);
        answer_2 = findViewById(R.id.textView9);
        answer_3 = findViewById(R.id.answer_3);
        answer_4 = findViewById(R.id.answer_4);
    }
}