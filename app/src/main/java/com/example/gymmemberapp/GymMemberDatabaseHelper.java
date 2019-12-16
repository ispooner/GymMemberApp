package com.example.gymmemberapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GymMemberDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GymMembers.db";
    private static final String TABLE_NAME = "members";
    private static int DATABASE_VERSION = 1;

    private static final String COLUMN_KEY = "member_key";
    private static final String COLUMN_ID = "member_id";
    private static final String COLUMN_NAME = "member_name";
    private static final String COLUMN_PLAN = "member_plan";

    public GymMemberDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement =
                "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_KEY + " INTEGER PRIMARY KEY, " +
                COLUMN_ID + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PLAN + " TEXT)";

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DATABASE_VERSION = newVersion;
        String updataQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(updataQuery);
        onCreate(db);
    }

    public void insertMember(GymMember member) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, member.idString);
        values.put(COLUMN_NAME, member.nameString);
        values.put(COLUMN_PLAN, member.planString);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<GymMember> getMembers(String filter) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_PLAN};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<GymMember> members = new ArrayList<>();

        int idCol = cursor.getColumnIndex(COLUMN_ID);
        int nameCol = cursor.getColumnIndex(COLUMN_NAME);
        int planCol = cursor.getColumnIndex(COLUMN_PLAN);

        while(cursor.moveToNext()) {
            GymMember mem = new GymMember(cursor.getString(nameCol), cursor.getString(idCol), cursor.getString(planCol));
            if(mem.nameString.contains(filter)) {
                members.add(mem);
            }
        }

        members.add(new GymMember("Add new member", "0", ""));
        cursor.close();
        return members;
    }

    public ArrayList<GymMember> getMembers() {
        return getMembers("");
    }
}
