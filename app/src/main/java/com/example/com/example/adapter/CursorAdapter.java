package com.example.com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.model.Note;
import com.example.modify.NoteModify;
import com.example.noteapplication.R;

public class CursorAdapter extends android.widget.CursorAdapter{

    Activity activity;

    public CursorAdapter(Activity context, Cursor c) {
        super(context, c);
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.note_item, null);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        LinearLayout quantrongView = view.findViewById(R.id.ni_isImportant);
        TextView noidungView = view.findViewById(R.id.ni_noteTitle);
        TextView ngaytaoView = view.findViewById(R.id.ni_noteCreatedDate);

        Note note = NoteModify.find(cursor);
        if(note.isQuantrong()){
            quantrongView.setBackgroundResource(R.color.colorImportant);
        } else {
            quantrongView.setBackgroundResource(R.color.colorNormal);
        }

        noidungView.setText(note.getNoidung());
        ngaytaoView.setText(note.getStringByDate());
    }
}
