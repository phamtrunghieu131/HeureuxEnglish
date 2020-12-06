package com.example.heureuxenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.widget.TextView;

public class FragmentHomePage extends Fragment {

    TextView learnWordText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);

        learnWordText = view.findViewById(R.id.hoc_tu_moi_text_view);

        learnWordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewWordActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
}
