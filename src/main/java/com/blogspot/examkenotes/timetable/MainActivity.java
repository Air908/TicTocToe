package com.blogspot.examkenotes.timetable;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.blogspot.examkenotes.timetable.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<String> arrayAdapter;
    List<String> list = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mydbhandler db = new Mydbhandler(MainActivity.this);

        Button button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, addContact.class);
                startActivity(myIntent);
            }
        });
        String [] student= new String[]{ "BCA (Cyber Security)","BCA (Data Science)","BCA (Artificial Intelligence and Machine Learning)"
                ,"BCA (AR and VR)"," BSc (Computer Science)","BSc (Mathematics)","BSc ( Physics )","BSc","MSc (Data Science)","MSc (Cyber Security)"
                ,"MCA (AI and ML)","MCA","B.TECH","B.E","BBA","B.Arch","BJMC","BDS","MBBS","B.Com","CA","BA LLB","LLB"};
        for (String s:student) {
            Contact contacts= new Contact();
            contacts.setClassname(s);
            db.addContact(contacts);
        }



        ArrayList<Contact> contactArrayList = new ArrayList<>();

        //get all contacts
        List<Contact> allContacts = db.getAllClass();
        for(Contact contact: allContacts){
            Log.d("dbrohit","Name: " + contact.getClassname() + "\n");
             list.add( contact.getClassname().toString());
            contactArrayList.add(contact);
        }

        Spinner dropdown = findViewById(R.id.spinner);
        dropdown.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,list);
        dropdown.setAdapter(adapter);

        Log.d("dbrohit", "onCreate: Bro you have " + db.getCount()+ "class in your database");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_LONG).show();
        Intent intent=new Intent(MainActivity.this, displayContact.class);
        intent.putExtra("Rname", list.get(position)); //Optional parameters
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
