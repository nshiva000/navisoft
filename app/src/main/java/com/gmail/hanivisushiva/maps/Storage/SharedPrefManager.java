package com.gmail.hanivisushiva.maps.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.gmail.hanivisushiva.maps.Models.Login.SignIn;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_name";

    private static SharedPrefManager mInstance;
    private Context mCtx;


    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static  synchronized  SharedPrefManager get_mInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(mCtx);

        }
        return mInstance;
    }

    public void saveUser(SignIn login){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("login_status",true);
        editor.putString("id",login.getData().getId());
        editor.putString("role",login.getData().getRole());
        editor.putString("name",login.getData().getName());
        editor.putString("email",login.getData().getEmail());
        editor.putString("password",login.getData().getPassword());
        editor.putString("phone",login.getData().getPhone());
        editor.putString("d_company",login.getData().getDcompany());
        editor.putString("position",login.getData().getPosition());
        editor.putString("team",login.getData().getTeam());
        editor.putString("project",login.getData().getProject());
        editor.putString("phone_mac",login.getData().getPhonemacaddress().toUpperCase());
        editor.putString("allow_to_book",login.getData().getAllowtobook());
        editor.putString("view_buyer_name",login.getData().getViewbuyername());
        editor.putString("view_buyer_designation",login.getData().getViewbuyerdesignation());
        editor.putString("head_name",login.getData().getBookedteamheadname());
        editor.putString("status",login.getData().getStatus());
        editor.putBoolean("d_status",login.getData().getDStatus());

        editor.apply();
        editor.commit();

    }


    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login_status",false);

    }


    public String getId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("id","");

    }


    public String getName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","");

    }

    public String getMobile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone","");

    }

    public String getPassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("password","");

    }
    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("email","");

    }

    public String getMac(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone_mac","");

    }

    public String getDCompany(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("d_company","");

    }

    public String getProjects(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("project","");

    }

    public String getPosition(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("position","");

    }


  /*  public Login getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
          return new Login(
                  sharedPreferences.getString("email",null),
                  sharedPreferences.getString("password",null),
                  sharedPreferences.getString("uid",null),
                  sharedPreferences.getBoolean("status",false)

          );
    }

    */

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
