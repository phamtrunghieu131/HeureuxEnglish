package com.example.heureuxenglish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.Fragment;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentProfile extends Fragment {
    
    TextView learnedWordText,pointText,levelText,hardDayText,nameText;
    DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        learnedWordText = view.findViewById(R.id.learnedWord);
        pointText = view.findViewById(R.id.point);
        levelText = view.findViewById(R.id.level);
        hardDayText = view.findViewById(R.id.hardDay);
        nameText = view.findViewById(R.id.name);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(User.class);
                nameText.setText(user.getName());
                learnedWordText.setText(user.getLearnedWords().size()+"");
                pointText.setText(user.getPoint()+"");
                levelText.setText(user.getLevel()+"");
                hardDayText.setText(user.getHardDay()+"");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
