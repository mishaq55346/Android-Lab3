package com.example.thirdlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = format.format( new Date());
    String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.textView);
        DBManager db = new DBManager(this);
        List<ChickenContact> contacts = db.getAllContacts();
        for (ChickenContact c : contacts) {
            String log = "ID: " + c.getId() + "\nNAME: " + c.getName() + "\nDATE: " + c.getDate() + "\n+--------------------------------------------------------+\n";
            text += log;
        }
        textView.setText(text);
    }
}
