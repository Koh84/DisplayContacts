package com.kelvin.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnContactListener {

    RecyclerView recyclerView;

    private static final int REQUEST_RUNTIME_PERMISSION = 123;
    String[] permissons = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (CheckPermission(MainActivity.this, permissons[0]))
        {
            recyclerView = findViewById(R.id.recyclerView);
            MyAdapter myAdapter = new MyAdapter(this, getContacts(), this);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            // you do not have permission go request runtime permissions
            RequestPermission(MainActivity.this, permissons, REQUEST_RUNTIME_PERMISSION);
        }
    }

    private void RequestPermission(Activity thisActivity, String[] Permission, int Code) {
        if (ContextCompat.checkSelfPermission(thisActivity,
                Permission[0])
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Permission[0])) {
            } else {
                ActivityCompat.requestPermissions(thisActivity, Permission,
                        Code);
            }
        }
    }

    private boolean CheckPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private List<MyContacts> getContacts()
    {
        List<MyContacts> list = new ArrayList<>();

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                null, null, ContactsContract.Contacts.DISPLAY_NAME+ " ASC");

        cursor.moveToFirst();

        while(cursor.moveToNext())
        {
            list.add(new MyContacts(
            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            , cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            , cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
            , cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
            ));
        }
        cursor.close();

        return list;
    }

    @Override
    public void onContactClick(String contact_id) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contact_id));
        intent.setData(uri);
        startActivity(intent);

        Log.d("Main","contact_id : "+ contact_id);
    }

}
