package com.example.contactsapp2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailInfo extends AppCompatActivity {

    TextView name, namedet, number, numberdet;
    ImageView call, share, more, msg;
    String contactid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail_open);

        name = findViewById(R.id.name_text);
        namedet = findViewById(R.id.contact_name);
        number = findViewById(R.id.contact_number);
        numberdet = findViewById(R.id.number_contact);
        call = findViewById(R.id.imze_call);
        share = findViewById(R.id.share_imz);
        more = findViewById(R.id.more_image);
        msg = findViewById(R.id.imze_msg);

        final String string = getIntent().getStringExtra("contact_name");
        final String number = getIntent().getStringExtra("contact_number");

        contactid = getIntent().getStringExtra("contact_id");


        namedet.setText(string);
        numberdet.setText(number);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, string + ": " + number);
//                sharingIntent.putExtra(Intent.EXTRA_TITLE,string);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", number);
                smsIntent.putExtra("sms_body", "Body of Message");
                startActivity(smsIntent);

            }
        });


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPopup();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    String repphone = null;
                    String Numb = "tel:" + number;// repphone is phonr num
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri
                            .parse(Numb));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                    }

                    startActivity(i);
                } catch (Exception e) {
                    // Modules.showLongMessage(contact.this, e.getMessage());
                }

            }
        });
    }

    private void menuPopup() {
        PopupMenu popupMenu = new PopupMenu(this, more);
        popupMenu.inflate(R.menu.menu_pop_up3);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item2:
                        break;

                    case R.id.item3:
                        Log.e("sss","rrrr");
                        deleteContactById(contactid);
                        break;
                }
                return false;
            }
        });
    }

    private void deleteContactById(String id) {
        Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, ContactsContract.Contacts._ID + "="
                + id, null, null);
        if (cur != null) {
            while (cur.moveToNext()) {
                try {
                    String lookupKey = cur.getString(cur
                            .getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,
                            lookupKey);
                    getContentResolver().delete(uri, ContactsContract.Contacts._ID + "=" + id, null);
                        Log.e("ssss","jjg");
                } catch (Exception e) {
                    Log.e("deleteContactById: ", "e");
                }
            }
            cur.close();
        }
    }

}
