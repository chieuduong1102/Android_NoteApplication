package com.example.modify;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.DBHelper;
import com.example.model.Note;

import java.text.SimpleDateFormat;

public class NoteModify {
    public static final String TABLE_NAME = "note";
    public static final String TABLE_NOTE = "CREATE TABLE note (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tnoidung text,\n" +
            "\tquantrong integer,\n" +
            "\tngaytao Date\n" +
            ")";

    public static void insert(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put("noidung",note.getNoidung());
        contentValues.put("quantrong",note.isQuantrong()?1:0);
        contentValues.put("ngaytao",note.getStringByDate());

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public static void update(Note note){
        ContentValues values = new ContentValues();

        values.put("noidung", note.getNoidung());
        values.put("quantrong", note.isQuantrong()?1:0);
        values.put("ngaytao", note.getStringByDate());

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, values, "_id = " + note.get_id(), null);
    }

    public static void delete(int _id){
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "_id = " + _id, null);
    }

    public static Note find(int _id){
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE _id = " + _id;

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        cursor.moveToNext();

        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("noidung")),
                cursor.getInt(cursor.getColumnIndex("quantrong")),
                cursor.getString(cursor.getColumnIndex("ngaytao"))
        );

        return note;
    }

    public static Note find(Cursor cursor) {
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("noidung")),
                cursor.getInt(cursor.getColumnIndex("quantrong")),
                cursor.getString(cursor.getColumnIndex("ngaytao"))
        );

        return note;
    }

    public static Cursor getNoteCursor(){
        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return cursor;
    }

}
