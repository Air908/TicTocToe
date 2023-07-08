package com.blogspot.examkenotes.timetable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.examkenotes.timetable.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class displayContact extends AppCompatActivity {

    MainActivity main = new MainActivity();
    Mydbhandler db = new Mydbhandler(main);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        Intent intent = getIntent();
        String rname = intent.getStringExtra("Rname"); //if it's a string you stored.
        TextView textView1 = findViewById(R.id.textView5);
        textView1.setText(rname);

        List<Integer> list = new ArrayList<>();
        // add 5 element in ArrayList
        list.add(R.drawable.ta);
        list.add(R.drawable.tb);
        list.add(R.drawable.tc);
        list.add(R.drawable.td);
        list.add(R.drawable.te);
        list.add(R.drawable.tf);
        list.add(R.drawable.tg);
        GFG obj = new GFG();
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(obj.getRandomElement(list));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        ImageButton imageButton1 = findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(main, updateContact.class);
                main.startActivity(myIntent);

                Intent intent = getIntent();
                String rrname = intent.getStringExtra("rrname"); //if it's a string you stored.
                Contact contact = new Contact();
                contact.setClassname(rrname);
                int affectedRows = db.updateContact(contact);

                Log.d("dbrohit", "No. of  affected rows are: " + affectedRows);
            }
        });
    }
}
    class GFG{
        // Function select an element base on index
        // and return an element
        public int getRandomElement(List<Integer> list)
        {
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }
}