package com.example.contactsapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Frag2 extends Fragment {

    TextView addFav;
    FloatingActionButton add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addFav = view.findViewById(R.id.addfav_text);
        add = view.findViewById(R.id.float_add);


        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frag2AddContact listFrag = new Frag2AddContact();
                getFragmentManager().beginTransaction()
                        .add(android.R.id.content, listFrag)
                        .addToBackStack(listFrag.getTag())
                        .commit();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag2AddContact listFrag = new Frag2AddContact();
                getFragmentManager().beginTransaction()
                        .add(android.R.id.content, listFrag)
                        .addToBackStack(listFrag.getTag())
                        .commit();
            }
        });

    }
}
