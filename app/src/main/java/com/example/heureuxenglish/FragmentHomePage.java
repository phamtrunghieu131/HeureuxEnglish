package com.example.heureuxenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class FragmentHomePage extends Fragment {

    ImageView learnWordImg, practiceImg;
    int count;
    Random random = new Random();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);

        learnWordImg = view.findViewById(R.id.hoc_tu_moi);
        practiceImg = view.findViewById(R.id.on_tap);

        mDatabase.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("countLearnedWord")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        count = snapshot.getValue(Integer.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        learnWordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewWordActivity.class);
                startActivity(intent);
            }
        });

        practiceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count != 0) {
                    Intent intent = new Intent(getActivity(), QuestionActivity.class);
                    intent.putExtra("type", 0);
                    intent.putExtra("count", random.nextInt(count));
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"Chưa có từ nào để ôn tập\n       Hãy học từ mới!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}
