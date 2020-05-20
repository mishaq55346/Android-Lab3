package com.example.thirdlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {
    DBManager db = new DBManager(this);
    EditText editName, editDate;
    Button btnAddData;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = format.format( new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editName = (EditText)findViewById(R.id.editTextName);
        btnAddData = (Button)findViewById(R.id.AddButton);

        //AddData();
    }

    public void addData(View view) {
        db.addContact(new ChickenContact(editName.getText().toString(), dateString.toString()));
        Toast.makeText(this, "Человек добавлен", Toast.LENGTH_SHORT).show();
    }

}
