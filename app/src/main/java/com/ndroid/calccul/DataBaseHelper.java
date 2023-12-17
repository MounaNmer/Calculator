package com.ndroid.calccul;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_Name="Users.db";
    private static final int DATABASE_Version=1;
    private static final String TABLE_Name="User";
    private static final String TABLE_Name2="Admin";
    private static final String COLUMN_FIRSTNAME="FirstName";
    private static final String COLUMN_LASTNAME="LastName";
    private static final String COLUMN_PASSWORD="Password";
    private static final String COLUMN_EMAIL="Email";

    public DataBaseHelper(Context context){
        super(context,DATABASE_Name,null, DATABASE_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String createUserTableQuery="CREATE TABLE "+TABLE_Name+"("+COLUMN_EMAIL+" TEXT PRIMARY KEY,"+COLUMN_PASSWORD+" TEXT, "+COLUMN_FIRSTNAME +" TEXT, "+COLUMN_LASTNAME +" TEXT)";
        db.execSQL(createUserTableQuery);
        String createAdminTableQuery="CREATE TABLE "+TABLE_Name2+"("+COLUMN_EMAIL+" TEXT PRIMARY KEY,"+COLUMN_PASSWORD+" TEXT)";
        db.execSQL(createAdminTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        String dropUserTableQuery="Drop TABLE IF EXISTS "+TABLE_Name;
        db.execSQL(dropUserTableQuery);
        String dropAdminTableQuery="Drop TABLE IF EXISTS "+TABLE_Name2;
        db.execSQL(dropAdminTableQuery);

    }

    public long insertUser(String email,String password,String nom,String prenom){
        ContentValues Values=new ContentValues();
        Values.put(COLUMN_EMAIL,email);
        Values.put(COLUMN_PASSWORD,password);
        Values.put(COLUMN_LASTNAME,nom);
        Values.put(COLUMN_FIRSTNAME,prenom);
        SQLiteDatabase db=this.getWritableDatabase();
        return db.insert(TABLE_Name,null,Values);
    }
   /* public long insertAdmin(String email,String password){
        ContentValues Values=new ContentValues();
        Values.put(COLUMN_EMAIL,email);
        Values.put(COLUMN_PASSWORD,password);
        SQLiteDatabase db=this.getWritableDatabase();
        return db.insert(TABLE_Name2,null,Values);
    }*/
    public long dropUser(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        String selection=COLUMN_EMAIL+" = ? ";
        String[] selectionArgs={email};
        return db.delete(TABLE_Name,selection,selectionArgs);
    }

    public boolean readUser(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=COLUMN_EMAIL+" = ? And "+COLUMN_PASSWORD+" = ? ";
        String[] selectionArgs={email,password};
        Cursor cursor=db.query(TABLE_Name,null,selection,selectionArgs,null,null,null);
        boolean userExists=cursor.getCount()>0;
        cursor.close();
        return userExists;
    }
    public boolean readAdmin(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=COLUMN_EMAIL+" = ? And "+COLUMN_PASSWORD+" = ? ";
        String[] selectionArgs={email,password};
        Cursor cursor=db.query(TABLE_Name2,null,selection,selectionArgs,null,null,null);
        boolean adminExists=cursor.getCount()>0;
        cursor.close();
        return adminExists;
    }


}
