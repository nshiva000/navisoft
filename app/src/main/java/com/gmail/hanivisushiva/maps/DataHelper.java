package com.gmail.hanivisushiva.maps;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gmail.hanivisushiva.maps.Models.DatabaseModel;
import com.gmail.hanivisushiva.maps.Models.Datum;
import com.gmail.hanivisushiva.maps.Models.Project.ProjectsDatum;
import com.gmail.hanivisushiva.maps.Models.dCompany.DDatum;


public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "navi_soft";
    private static final String TABLE_NAME = "plot_table";












    private static final String TABLE_NAME_D_COMPANY = "d_company_table";

    private static final String D_CID = "CID";
    private static final String D_CNAME = "NAME";
    private static final String D_CDESCRIPTION = "CDESCRIPTION";
    private static final String D_CLOGO = "CLOGO";
    private static final String D_CTHEME = "CTHEME";
    private static final String D_CSTATUS = "CSTATUS";
    private static final String D_DATE = "CDATE";



    private static final String TABLE_NAME_PROJECT = "project";

    private static final String P_ID = "PID";
    private static final String P_NAME = "NAME";
    private static final String P_CENTER = "PCENTER";
    private static final String P_PGOOGLE = "PGOOGLE";
    private static final String P_PLOGO = "PLOGO";
    private static final String P_PLOCATION = "PLOCATION";
    private static final String P_PSTATUS = "PSTATUS";
    private static final String P_PCOMPANY = "PCOMPANY";
    private static final String P_PLOTS = "PLOTS";
    private static final String P_PLOTTED_AREA = "T_ALLOTED";
    private static final String P_DATE = "DATE";
    private static final String P_PROJECT_DES = "PROJECT_DES";
    private static final String P_T_PLOTS = "T_PLOTS";
    private static final String P_T_AVAILABLE = "T_AVAILABLE";
    private static final String P_T_PLOT_AREA = "T_PLOT_AREA";
    private static final String P_T_PLOT_SOLD = "T_PLOT_SOLD";

    public static final String ID = "ID";
    private static final String PID = "PID";
    private static final String PLOT_NO = "PLOT_NO";
    private static final String PLOT_STATUS = "PLOT_STATUS";
    private static final String SIZE = "SIZE";
    private static final String FACING = "FACING";
    private static final String STATUS = "STATUS";
    private static final String DIM_A = "DIM_A";
    private static final String DIM_B = "DIM_B";
    private static final String DIM_C = "DIM_C";
    private static final String DIM_D = "DIM_D";
    private static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String CUSTOMER_DESIG = "CUSTOMER_DESIG";
    private static final String SOLD_TEAM = "SOLD_TEAM";
    private static final String BOOKED_TEAM = "BOOKED_TEAM";
    private static final String POINTS = "POINTS";




    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //context.deleteDatabase(DATABASE_NAME);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PID TEXT,PLOT_NO TEXT, PLOT_STATUS TEXT, SIZE TEXT, FACING TEXT,STATUS TEXT,DIM_A TEXT,DIM_B TEXT,DIM_C TEXT,DIM_D TEXT,CUSTOMER_NAME TEXT,CUSTOMER_DESIG TEXT,SOLD_TEAM TEXT,BOOKED_TEAM TEXT,POINTS TEXT)");

        db.execSQL("create table " + TABLE_NAME_D_COMPANY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,CID TEXT, NAME TEXT, CDESCRIPTION TEXT, CLOGO TEXT, CTHEME TEXT, CSTATUS TEXT,CDATE TEXT)");

        db.execSQL("create table " + TABLE_NAME_PROJECT + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PID TEXT, NAME TEXT,PCENTER TEXT, PGOOGLE TEXT, PLOGO TEXT,PLOCATION TEXT ,PSTATUS TEXT, PCOMPANY TEXT, PLOTS TEXT, DATE TEXT,PROJECT_DES TEXT ,T_PLOTS TEXT, T_ALLOTED TEXT, T_AVAILABLE TEXT, T_PLOT_AREA TEXT, T_PLOT_SOLD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(db);

    }

  /*  public boolean insertData(DatabaseModel databaseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,databaseModel.getPlot_no());
        contentValues.put(COL_3,databaseModel.getType());
        contentValues.put(COL_4,databaseModel.getPlot_status());
        contentValues.put(COL_5,databaseModel.getSize());
        contentValues.put(COL_6,databaseModel.getFacing());
        contentValues.put(COL_7,databaseModel.getPoints());
        contentValues.put(COL_7,databaseModel.getPoints());

        Long success = db.insert(TABLE_NAME, null,contentValues);

        return success != -1;

    }
    */







  public boolean insertedData(Datum datum){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(PID,datum.getPid());
      contentValues.put(PLOT_NO,datum.getPlotNo());
      contentValues.put(PLOT_STATUS,datum.getPlotStatus());
      contentValues.put(SIZE,datum.getSize());
      contentValues.put(FACING,datum.getFacing());
      contentValues.put(STATUS,datum.getStatus());
      contentValues.put(DIM_A,datum.getDimension1());
      contentValues.put(DIM_B,datum.getDimension2());
      contentValues.put(DIM_C,datum.getDimension3());
      contentValues.put(DIM_D,datum.getDimension4());
      contentValues.put(CUSTOMER_NAME,"datum.getCustomername()");
      contentValues.put(CUSTOMER_DESIG,datum.getCustomerdesi());
      contentValues.put(SOLD_TEAM,datum.getSoldteam());
      contentValues.put(BOOKED_TEAM,datum.getBookedteam());
      contentValues.put(POINTS,datum.getString_points());

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





    public boolean insertProject(ProjectsDatum datum){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(P_ID,datum.getPid());
        contentValues.put(P_NAME,datum.getPname());
        contentValues.put(P_CENTER,datum.getPcenter());
        contentValues.put(P_PGOOGLE,datum.getPgoogle());
        contentValues.put(P_PLOGO,datum.getPlogo());
        contentValues.put(P_PLOCATION,datum.getPlocation());
        contentValues.put(P_PSTATUS,datum.getPstatus());
        contentValues.put(P_PCOMPANY,datum.getDcompany());
        contentValues.put(P_PLOTS,datum.getPdate());
        contentValues.put(P_PLOTTED_AREA,datum.getParea());
        contentValues.put(P_DATE,datum.getPdate());
        contentValues.put(P_PROJECT_DES,datum.getPdescription());
        contentValues.put(P_T_PLOTS,datum.getTotal());
        contentValues.put(P_T_AVAILABLE,datum.getAvailable());
        contentValues.put(P_T_PLOT_AREA,datum.getBooked());
        contentValues.put(P_T_PLOT_SOLD,datum.getSold());

        Long success = db.insert(TABLE_NAME_PROJECT, null,contentValues);

        return success != -1;

    }



    public Cursor getAllData(){
        SQLiteDatabase db =  this.getWritableDatabase();

         return db.rawQuery("select * from "+TABLE_NAME,null);

    }


    public Cursor getAllPlots(String s){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME+" WHERE "+P_ID+" = '"+s+"'",null);

    }






    public Cursor getAllChildData(){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME_D_COMPANY,null);

    }

    public Cursor getProjectData(){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME_PROJECT,null);

    }


    public Cursor getOnlyProjectData(String s){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME_PROJECT+" WHERE "+P_PCOMPANY+" = '"+s+"'",null);

    }


    public Cursor get_by_facing(String facing,String status){
        SQLiteDatabase db =  this.getWritableDatabase();

        return db.rawQuery("select * from "+TABLE_NAME+" WHERE "+PLOT_STATUS+" = '"+status+"'and "+FACING+"='"+facing+"'" ,null);

    }

    public int deletePost(String id){
        SQLiteDatabase db =this.getWritableDatabase();

        return db.delete(TABLE_NAME,PLOT_NO+"="+id, null);
    }


    public void delete_db(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

    public void delete_child(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(TABLE_NAME_D_COMPANY, null, null);
    }

    public void delete_project(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(TABLE_NAME_PROJECT, null, null);
    }

    public void delete_plots(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }






}
