package com.gmail.hanivisushiva.maps;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gmail.hanivisushiva.maps.Models.Company.Datum;
import com.gmail.hanivisushiva.maps.Models.DatabaseModel;
import com.gmail.hanivisushiva.maps.Models.dCompany.DDatum;


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




    private static final String TABLE_NAME_COMPANY = "company_table";
    private static final String C_ID = "COMPANY_ID";
    private static final String C_ROLE = "ROLE";
    private static final String C_NAME = "NAME";
    private static final String C_EMAIL = "EMAIL";
    private static final String C_PASSWORD = "PASSWORD";
    private static final String C_PHONE = "PHONE";
    private static final String C_MCOMPANY = "M_COMPANY";
    private static final String C_DCOMPANY = "D_COMPANY";
    private static final String C_POSITION = "POSITION";
    private static final String C_TEAM = "TEAM";
    private static final String C_PROJECT = "PROJECT";
    private static final String C_MACADDRESS = "MAC_ADDRESS";
    private static final String C_ALLOWTOBOOK = "ALLOW_TO_BOOK";
    private static final String C_BUYERNAME = "BUYER_NAME";
    private static final String C_BUYERDESIGNATION = "BUYER_DESIGNATION";
    private static final String C_HEADNAME = "HEAD_NAME";
    private static final String C_STATUS = "STATUS";
    private static final String C_DATE = "DATE";
    private static final String C_CID = "C_ID";
    private static final String C_CNAME = "C_NAME";
    private static final String C_CDESCRIPTION = "C_DESCRIPTION";
    private static final String C_CLOGO = "C_LOGO";
    private static final String C_CLEVEL = "C_LEVEL";
    private static final String C_CTHEME = "C_THEME";
    private static final String C_CSTATUS = "C_STATUS";



    private static final String TABLE_NAME_D_COMPANY = "d_company_table";

    private static final String D_CID = "CID";
    private static final String D_CNAME = "NAME";
    private static final String D_CDESCRIPTION = "CDESCRIPTION";
    private static final String D_CLOGO = "CLOGO";
    private static final String D_CLEVEL = "CLEVEL";
    private static final String D_DCOMPANY = "CDCOMPANY";
    private static final String D_CTHEME = "CTHEME";
    private static final String D_CSTATUS = "CSTATUS";
    private static final String D_DATE = "CDATE";



    private static final String TABLE_NAME_PROJECT = "project";

    private static final String P_ID = "PID";
    private static final String P_NAME = "NAME";
    private static final String P_PGOOGLE = "PGOOGLE";
    private static final String P_PLOGO = "PLOGO";
    private static final String P_PLOCATION = "PLOCATION";
    private static final String P_PSTATUS = "PSTATUS";
    private static final String P_PCOMPANY = "PCOMPANY";
    private static final String P_PLOTS = "PLOTS";
    private static final String P_PLOTTED_AREA = "PLOTTED_AREA";
    private static final String P_DATE = "DATE";
    private static final String P_PROJECT_DES = "PROJECT_DES";
    private static final String P_T_PLOTS = "T_PLOTS";
    private static final String P_T_ALLOTED = "T_ALLOTED";
    private static final String P_T_AVAILABLE = "T_AVAILABLE";
    private static final String P_T_PLOT_AREA = "T_PLOT_AREA";
    private static final String P_T_PLOT_SOLD = "T_PLOT_SOLD";
    private static final String P_T_AREA_AVAILABLE = "T_AREA_AVAILABLE";




    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PLOT_NO TEXT, TYPE TEXT, PLOT_STATUS TEXT, SIZE TEXT, FACING TEXT,POINTS TEXT)");

        db.execSQL("create table " + TABLE_NAME_COMPANY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,COMPANY_ID TEXT, ROLE TEXT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT,PHONE TEXT ,M_COMPANY TEXT, D_COMPANY TEXT, POSITION TEXT, TEAM TEXT, PROJECT TEXT,MAC_ADDRESS TEXT ,ALLOW_TO_BOOK TEXT, BUYER_NAME TEXT, BUYER_DESIGNATION TEXT, HEAD_NAME TEXT, STATUS TEXT,DATE TEXT ,C_ID TEXT, C_NAME TEXT, C_DESCRIPTION TEXT, C_LOGO TEXT, C_LEVEL TEXT,C_THEME TEXT,C_STATUS TEXT)");

        db.execSQL("create table " + TABLE_NAME_D_COMPANY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,CID TEXT, NAME TEXT, CDESCRIPTION TEXT, CLOGO TEXT, CLEVEL TEXT,CDCOMPANY TEXT , CTHEME TEXT, CSTATUS TEXT,CDATE TEXT)");

        db.execSQL("create table " + TABLE_NAME_PROJECT + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PID TEXT, NAME TEXT, PGOOGLE TEXT, PLOGO TEXT,PLOCATION TEXT ,PSTATUS TEXT, PCOMPANY TEXT, PLOTS TEXT, PLOTTED_AREA TEXT, DATE TEXT,PROJECT_DES TEXT ,T_PLOTS TEXT, T_ALLOTED TEXT, T_AVAILABLE TEXT, T_PLOT_AREA TEXT, T_PLOT_SOLD TEXT,T_AREA_AVAILABLE TEXT)");

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








    public boolean insertChildData(DDatum datum){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(D_CID,datum.getDid());
        contentValues.put(D_CNAME,datum.getDname());
        contentValues.put(D_CDESCRIPTION,datum.getDdescription());
        contentValues.put(D_CLOGO,datum.getDfile());
        contentValues.put(D_CTHEME,datum.getDtheme());
        contentValues.put(D_CSTATUS,datum.getDstatus());
        contentValues.put(D_DATE,datum.getDdate());




        Long success = db.insert(TABLE_NAME_D_COMPANY, null,contentValues);

        return success != -1;

    }



    public Cursor getAllData(){
        SQLiteDatabase db =  this.getWritableDatabase();

         return db.rawQuery("select * from "+TABLE_NAME,null);

    }



    public Cursor getAllCompanyData(){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME_COMPANY,null);

    }


    public Cursor getAllChildData(){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME_D_COMPANY,null);

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
