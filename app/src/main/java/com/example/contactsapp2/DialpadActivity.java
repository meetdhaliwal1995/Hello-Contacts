package com.example.contactsapp2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DialpadActivity extends AppCompatActivity implements View.OnClickListener {

    TextView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnStar, btn0, btnhastag, dialpad;
    ImageView crossBtn, backBtn, plusBtn;
    FloatingActionButton floatBtn;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialpad_activity);

        btn0 = findViewById(R.id.zero_tv);
        btn1 = findViewById(R.id.textView1);
        btn2 = findViewById(R.id.textView2);
        btn3 = findViewById(R.id.textView3);
        btn4 = findViewById(R.id.textView4);
        btn5 = findViewById(R.id.textView5);
        btn6 = findViewById(R.id.textView6);
        btn7 = findViewById(R.id.textView7);
        btn8 = findViewById(R.id.textView8);
        btn9 = findViewById(R.id.textView9);
        btnStar = findViewById(R.id.star_tv);
        btnhastag = findViewById(R.id.hastag_tv);
        crossBtn = findViewById(R.id.cross_btn);
        floatBtn = findViewById(R.id.float_pad);
        dialpad = findViewById(R.id.dialpaad);
        backBtn = findViewById(R.id.back_arrow);
        plusBtn = findViewById(R.id.plus_btn_dialpad);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnStar.setOnClickListener(this);
        btnhastag.setOnClickListener(this);
        crossBtn.setOnClickListener(this);
        floatBtn.setOnClickListener(this);
        dialpad.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);

        crossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialpad.setText(removeLastChar(dialpad.getText().toString()));
            }
        });

        btn0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialpad.setText(dialpad.getText() +"+");
                return true;
            }
        });

        crossBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialpad.setText("");
                return false;
            }
        });


    }

    private static String removeLastChar(String str) {
        if (str.length() > 0) {
            return str.substring(0, str.length() - 1);

        }
        return str;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.cross_btn:
//                String r = dialpad.getText().toString();
//
//                if (!r.isEmpty()) {
//                    dialpad.setText(r.substring(0, r.length() - 1));
//                }
//                break;
            case R.id.textView1:
                dialpad.setText(dialpad.getText() + "1");
                break;

            case R.id.textView2:
                dialpad.setText(dialpad.getText() + "2");
                break;

            case R.id.textView3:
                dialpad.setText(dialpad.getText() + "3");
                break;

            case R.id.textView4:
                dialpad.setText(dialpad.getText() + "4");
                break;

            case R.id.textView6:
                dialpad.setText(dialpad.getText() + "5");
                break;

            case R.id.textView5:
                dialpad.setText(dialpad.getText() + "6");
                break;

            case R.id.textView7:
                dialpad.setText(dialpad.getText() + "7");
                break;

            case R.id.textView8:
                dialpad.setText(dialpad.getText() + "8");
                break;

            case R.id.textView9:
                dialpad.setText(dialpad.getText() + "9");
                break;

            case R.id.zero_tv:
                dialpad.setText(dialpad.getText() + "0");
                break;

            case R.id.star_btn:
                dialpad.setText(dialpad.getText() + "*");
                break;

            case R.id.hastag_tv:
                dialpad.setText(dialpad.getText() + "#");
                break;

            case R.id.float_pad:
                try {
                    String repphone = null;
                    String Numb = "tel:" + dialpad.getText().toString();// repphone is phonr num
                    Intent i = new Intent(Intent.ACTION_CALL, Uri
                            .parse(Numb));
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }

                    startActivity(i);
                } catch (Exception e) {
                    // Modules.showLongMessage(contact.this, e.getMessage());
                }

        }
    }

}
