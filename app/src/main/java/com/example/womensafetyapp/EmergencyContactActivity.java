package com.example.womensafetyapp;

import static com.example.womensafetyapp.MainActivity.PICK_CONTACT;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.womensafetyapp.Adapter.CustomAdapter;
import com.example.womensafetyapp.Model.ContactModel;
import com.example.womensafetyapp.Utils.DbHelper;

import java.util.List;

public class EmergencyContactActivity extends AppCompatActivity {


    // create instances of various classes to be used
    Button button1;
    ListView listView;
    DbHelper db;
    List<ContactModel> list;
    CustomAdapter customAdapter;
    //private static final int PICK_CONTACT = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

       // button1 = findViewById(R.id.button1);
        listView = (ListView) findViewById(R.id.ListView);
        db = new DbHelper(this);
        list = db.getAllContacts();
        customAdapter = new CustomAdapter(this, list);
        listView.setAdapter(customAdapter);

//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // calling of getContacts()
//                if (db.count() != 5) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                    startActivityForResult(intent, PICK_CONTACT);
//                } else {
//                    Toast.makeText(EmergencyContactActivity.this, "Can't Add more than 5 Contacts", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }
}