package com.blogspot.examkenotes.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        MainActivity mainActivity = new MainActivity();
        Mydbhandler db = new Mydbhandler(mainActivity);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView= findViewById(R.id.editTextText);



                String rrname=textView.getText().toString();

                Intent myIntent = new Intent(mainActivity, MainActivity.class);
                myIntent.putExtra("rrname", rrname); //Optional parameters

                mainActivity.startActivity(myIntent);
            }
        });

    }
}