package com.example.thirdlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_Name = "contactManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    public DBManager(Context context) {
        super(context, DB_Name, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    void addContact(ChickenContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_DATE, contact.getDate());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
    ChickenContact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_DATE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ChickenContact contact = new ChickenContact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return  contact;
    }
    public List<ChickenContact> getAllContacts() {
        List<ChickenContact> contactList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {
               ChickenContact contact = new ChickenContact();
               contact.setId(Integer.parseInt(cursor.getString(0)));
               contact.setName(cursor.getString(1));
               contact.setDate(cursor.getString(2));
               contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public int updateContact(ChickenContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_DATE, contact.getDate());

        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }
    public void deleteContact(ChickenContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int contactsCount = cursor.getCount();
        cursor.close();

        return contactsCount;
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_CONTACTS);
        db.close();
    }
}
