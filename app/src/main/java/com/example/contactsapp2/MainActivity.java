package com.example.contactsapp2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView contacts;
    ImageView search, sort, more, backbtn;
    SearchView searchContacts;
    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton floatadd, floatpad;
    public static List<PhnContacts> _list = new ArrayList<>();
    private int traker = 0;
    private Boolean track = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = findViewById(R.id.text_contacts);
        search = findViewById(R.id.search_view);
        sort = findViewById(R.id.sort_image);
        more = findViewById(R.id.more_image);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        floatadd = findViewById(R.id.float_add);
        floatpad = findViewById(R.id.float_pad);
        backbtn = findViewById(R.id.back_arrow);


        getContactList();


        PagerAdapter pager = new PagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pager);
        tabLayout.getTabAt(0).setIcon(getDrawable(R.drawable.ic_person_white));
        tabLayout.getTabAt(1).setIcon(getDrawable(R.drawable.ic_star_white));
        tabLayout.getTabAt(2).setIcon(getDrawable(R.drawable.ic_supervisor_account_white));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        sort.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menuPopup();
                            }
                        });
                        break;
                    case 1:
                        sort.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menuPopup();
                            }
                        });
                        break;
                    case 2:
                        sort.setVisibility(View.GONE);
                        search.setVisibility(View.GONE);
                        more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menuPopup2();
                            }
                        });
                        break;
                }


                tab.getIcon().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                tab.getIcon().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        floatpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DialpadActivity.class);
                startActivity(intent);
            }
        });


        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogSort(getApplicationContext());
            }
        });


    }

    private void menuPopup() {
        PopupMenu popupMenu = new PopupMenu(this, more);
        popupMenu.inflate(R.menu.menu_pop_up);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        createDialogFilter(getApplicationContext());
                        break;
                    case R.id.item2:
//                        FragmentMenuImport fragmentMenuImport = new FragmentMenuImport();
//                        getSupportFragmentManager().beginTransaction()
//                                .add(android.R.id.content, fragmentMenuImport)
//                                .addToBackStack(fragmentMenuImport.getTag())
//                                .commit();
                        createDialogImport(getApplicationContext());
                        break;
                    case R.id.item3:
//                        FragmentMenuExport fragmentMenuExport = new FragmentMenuExport();
//                        getSupportFragmentManager().beginTransaction()
//                                .add(android.R.id.content, fragmentMenuExport)
//                                .addToBackStack(fragmentMenuExport.getTag())
//                                .commit();
                        createDialogImport(getApplicationContext());
                        break;

                    case R.id.item4:
                        Intent intent = new Intent(MainActivity.this, ActivityMenuSetting.class);
                        startActivity(intent);
                        break;

                    case R.id.item5:
                        Intent intent1 = new Intent(MainActivity.this, ActivityMemeAbout.class);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    private void menuPopup2() {
        PopupMenu popupMenu = new PopupMenu(this, more);
        popupMenu.inflate(R.menu.menu_pop_up2);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item2:
//                        FragmentMenuImport fragmentMenuImport = new FragmentMenuImport();
//                        getSupportFragmentManager().beginTransaction()
//                                .add(android.R.id.content, fragmentMenuImport)
//                                .addToBackStack(fragmentMenuImport.getTag())
//                                .commit();
                        createDialogImport(getApplicationContext());
                        break;
                    case R.id.item3:
//                        FragmentMenuExport fragmentMenuExport = new FragmentMenuExport();
//                        getSupportFragmentManager().beginTransaction()
//                                .add(android.R.id.content, fragmentMenuExport)
//                                .addToBackStack(fragmentMenuExport.getTag())
//                                .commit();
                        createDialogImport(getApplicationContext());
                        break;

                    case R.id.item4:
                        Intent intent = new Intent(MainActivity.this, ActivityMenuSetting.class);
                        startActivity(intent);
                        break;

                    case R.id.item5:
                        Intent intent1 = new Intent(MainActivity.this, ActivityMemeAbout.class);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    private void getContactList() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext() && track) {
                        traker = traker + 1;
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));

                        PhnContacts model = new PhnContacts(name, phoneNo,id);

                        _list.add(model);

                        Log.e("hh", "Name: " + name);
                        Log.e("jj", "Phone Number: " + phoneNo);
                        if (traker == 200) {
                            track = false;
                        }
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
    }

    private void createDialogFilter(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.menu_filter_layout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        TextView cancel = dialogView.findViewById(R.id.cancel_tv);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    private void createDialogSort(Context applicationContext) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sort_layout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        TextView cancel = dialogView.findViewById(R.id.cancel_tv);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void createDialogGroup(Context applicationContext) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.group_layout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        TextView cancel = dialogView.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void createDialogImport(Context applicationContext) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.menu_import_layout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        TextView cancel = dialogView.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}
