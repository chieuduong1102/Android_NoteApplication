package com.example.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.modify.NoteModify;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "NoteApp";
    static final int VERSION = 1;

    static DBHelper instance = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ham nay chi goi 1 lan duy nhat -> khi database chua khoi tao.
        //Tao database -> table -> Foods -> Version 1
        db.execSQL(NoteModify.TABLE_NOTE);
        Log.d(DBHelper.class.getName(), "Create food table is success!!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Chi goi 1 lan khi co su thay doi phien ban cua Database
    }
}
