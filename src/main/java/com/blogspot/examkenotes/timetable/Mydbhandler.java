package com.blogspot.examkenotes.timetable;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.blogspot.examkenotes.timetable.model.Contact;
import com.blogspot.examkenotes.timetable.params.Param;

import java.util.ArrayList;
import java.util.List;

public class Mydbhandler extends SQLiteOpenHelper {
MainActivity main =new MainActivity();
    public Mydbhandler(Context context){
        super(context, Param.DB_NAME,null,Param.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       try {
           String create = "CREATE TABLE " + Param.TABLE_NAME + " ( "
                   + Param.KEY_ID + " INTEGER PRIMARY KEY, "
                   + Param.KEY_CLASS + " TEXT " + " ) ";
           Log.d("dbrohit", "Query being run is: " + create);
           db.execSQL(create);
       }catch (Exception e){
           Toast.makeText(main,"Something goes wrong in create dbhandler",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact){
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Param.KEY_CLASS, contact.getClassname());
            db.insert(Param.TABLE_NAME, null, values);
            Log.d("dbrohit", "Successfully inserted ");
            db.close();
        }catch (Exception e){
            Toast.makeText(main, "Something goes wrong in add contact ", Toast.LENGTH_SHORT).show();
        }
    }
    public List<Contact> getAllContacts(){
     List<Contact> contactList = new ArrayList<>();
     SQLiteDatabase db =this.getReadableDatabase();
     //Generate the query to read from the database
        String select = "SELECT * FROM " + Param.TABLE_NAME;
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery(select,null);

        //Loop through now
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setClassname(cursor.getString(1));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
                return contactList;
    }
    public List<Contact> getAllClass(){
     List<Contact> contactList = new ArrayList<>();
     SQLiteDatabase db =this.getReadableDatabase();
     //Generate the query to read from the database
        String select = "SELECT * FROM " + Param.TABLE_NAME;
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery(select,null);

        //Loop through now
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setClassname(cursor.getString(1));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
                return contactList;
    }
    public int updateContact(Contact contact){

        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Param.KEY_CLASS,contact.getClassname());


        //Lets update now
        return db.update(Param.TABLE_NAME,values,Param.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

public void deleteContactById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Param.TABLE_NAME ,Param.KEY_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
}
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Param.TABLE_NAME ,Param.KEY_ID + "=?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public int getCount(){
        String query = "SELECT * FROM " + Param.TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
}
