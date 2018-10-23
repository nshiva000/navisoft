package com.gmail.hanivisushiva.maps;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gmail.hanivisushiva.maps.Models.DatabaseModel;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "navi_soft";
    private static final String TABLE_NAME = "plot_table";
    public static final String COL_1 = "ID";
    private static final String COL_2 = "PLOT_NO";
    private static final String COL_3 = "TYPE";
    private static final String COL_4 = "PLOT_STATUS";
    private static final String COL_5 = "SIZE";
    private static final String COL_6 = "FACING";
    private static final String COL_7 = "POINTS";






    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PLOT_NO TEXT, TYPE TEXT, PLOT_STATUS TEXT, SIZE TEXT, FACING TEXT,POINTS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(db);

    }

    public boolean insertData(DatabaseModel databaseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,databaseModel.getPlot_no());
        contentValues.put(COL_3,databaseModel.getType());
        contentValues.put(COL_4,databaseModel.getPlot_status());
        contentValues.put(COL_5,databaseModel.getSize());
        contentValues.put(COL_6,databaseModel.getFacing());
        contentValues.put(COL_7,databaseModel.getPoints());




        Long success = db.insert(TABLE_NAME, null,contentValues);

        return success != -1;

    }


    public Cursor getAllData(){
        SQLiteDatabase db =  this.getWritableDatabase();

         return db.rawQuery("select * from "+TABLE_NAME,null);

    }


    public Cursor get_by_facing(String facing,String status){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME+" WHERE "+COL_4+" = '"+status+"'and "+COL_6+"='"+facing+"'" ,null);

    }

    public int deletePost(String id){
        SQLiteDatabase db =this.getWritableDatabase();

        return db.delete(TABLE_NAME,COL_2+"="+id, null);
    }





}
