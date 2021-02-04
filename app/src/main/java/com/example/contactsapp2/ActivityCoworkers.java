package com.example.contactsapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityCoworkers extends AppCompatActivity {

    TextView add;
    FloatingActionButton addbtn;
    ImageView backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coworkers);

        add = findViewById(R.id.text_add);
        addbtn = findViewById(R.id.float_add);
        backbtn = findViewById(R.id.back_btn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FragCoverkers fragCoverkers = new FragCoverkers();
               getSupportFragmentManager().beginTransaction()
                       .add(android.R.id.content,fragCoverkers)
                       .addToBackStack(fragCoverkers.getTag())
                       .commit();
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragCoverkers fragCoverkers = new FragCoverkers();
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content,fragCoverkers)
                        .addToBackStack(fragCoverkers.getTag())
                        .commit();
            }
        });
    }
}
