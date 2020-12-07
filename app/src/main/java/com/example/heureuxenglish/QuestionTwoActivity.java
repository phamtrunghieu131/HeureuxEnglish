package com.example.heureuxenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionTwoActivity extends AppCompatActivity {

    TextView englishText,resultText;
    EditText vietnameseText;
    Button mButton;
    boolean pass;
    int count;
    int currentPoint;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_two);
        creat();

        Intent intent = getIntent();
        pass = intent.getBooleanExtra("pass",true);
        count = intent.getIntExtra("count",10);

        mDatabase.child("word").child(count+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Word word = snapshot.getValue(Word.class);
                englishText.setText(word.getExampleInEnglish());
                resultText.setText(word.getExampleInVietnamese());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        currentPoint = snapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = vietnameseText.getText().toString().toLowerCase();
                String result = resultText.getText().toString().toLowerCase();
                if(input.equals(result)){
                    vietnameseText.setTextColor(Color.GREEN);
                    FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("point").setValue(currentPoint+10);
                }
                else{
                    vietnameseText.setTextColor(Color.RED);
                    resultText.setVisibility(View.VISIBLE);
                    pass = false;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(QuestionTwoActivity.this,NotificationActivity.class);
                        intent.putExtra("pass",pass);
                        intent.putExtra("count",count);
                        startActivity(intent);
                    }
                },800);
            }
        });
    }

    private void creat() {
        englishText = findViewById(R.id.textView10);
        resultText = findViewById(R.id.resultText);
        vietnameseText = findViewById(R.id.editText);
        mButton = findViewById(R.id.confirm_questionTwo_dsmButton);
    }
}