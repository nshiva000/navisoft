package com.gmail.hanivisushiva.maps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Models.Data;
import com.gmail.hanivisushiva.maps.Models.DatabaseModel;
import com.gmail.hanivisushiva.maps.Models.Datum;
import com.gmail.hanivisushiva.maps.Models.Login.SignIn;
import com.gmail.hanivisushiva.maps.Models.Project.Project;
import com.gmail.hanivisushiva.maps.Models.dCompany.DCompany;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;
import com.google.gson.Gson;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login;
    String email_text,password_text,mac_no;
    DataHelper db;
    ArrayList<String> child_id = new ArrayList<>();
    ArrayList<String> project_list_id = new ArrayList<>();
    ArrayList<String> plots_list_id = new ArrayList<>();
    String user_projects;
    String user_company;
    ProgressDialog progressDialog;




    @Override
    protected void onStart() {

        Toast(SharedPrefManager.get_mInstance(this).isLoggedIn()+"");
        super.onStart();
        if (SharedPrefManager.get_mInstance(this).isLoggedIn()){

            //user_projects= "1";
            user_company= SharedPrefManager.get_mInstance(this).getProjects();
            user_projects= SharedPrefManager.get_mInstance(this).getProjects();


            Log.e("network_status",haveNetworkConnection()+"");

            Intent intent = new Intent(Login.this,ChildCompany.class);
            startActivity(intent);
//            if (checkMac()){
//                Intent intent = new Intent(Login.this,ChildCompany.class);
//                startActivity(intent);
//            }

            //getChildId();
            //checkMac();
            //getChildData();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DataHelper(getApplicationContext());

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);

        WifiManager wiman = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //mac_no = wiman.getConnectionInfo().getMacAddress();
        mac_no = getMacAddr().toUpperCase();

        //Toast(mac_no);






        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text = email.getText().toString().trim();
                password_text = password.getText().toString().trim();
                userLogin(email_text,password_text);

            }
        });



    }

    private void Toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }


    private void userLogin(String e,String p){
        if (TextUtils.isEmpty(e)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(p)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }

        Call<SignIn> call = RetrofitClient.getmInstance().getApi().user_login(email_text,password_text);

        call.enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {
                SignIn signIn = response.body();

                if (signIn.getStatus()){

                    user_projects= signIn.getData().getProject();
                    user_company= signIn.getData().getDcompany();


                    SharedPrefManager.get_mInstance(Login.this)
                           .saveUser(signIn);


                    String user_name_s= SharedPrefManager.get_mInstance(Login.this).getEmail();
                    String user_password_s = SharedPrefManager.get_mInstance(Login.this).getPassword();
                    Toast("login success"+user_name_s+"--"+user_password_s);

                    Intent intent = new Intent(Login.this,Sync.class);
                    startActivity(intent);
//                    if (checkMac()){
//
//                        Intent intent = new Intent(Login.this,Sync.class);
//                        startActivity(intent);
//
//                    }





                }else {
                    Toast("wrong email or Password");
                }
                //Toast(signIn.getMessage());
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t)

            {
                Log.e("error - login",t.getMessage());
            }
        });



    }




    private boolean checkMac(){

        Log.e("mymac",SharedPrefManager.get_mInstance(this).getMac());
        Log.e("mymac",mac_no);

        if (SharedPrefManager.get_mInstance(this).getMac().equals(mac_no)){


              return true;
/*
            if (haveNetworkConnection()){

                progressDialog =new ProgressDialog(Login.this);
                progressDialog.setMessage("Synchronizing Data,Please Wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
               // getChildData();
            }else {
                Intent intent = new Intent(Login.this,ChildCompany.class);
                startActivity(intent);
            }

            //db.delete_db(getApplicationContext());



            */

        }else {


            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.pop_up, null);
            builder.setView(dialogLayout);
            builder.show();

            return false;
            /*
            Toast("wrong mac Address");

            progressDialog =new ProgressDialog(Login.this);
            progressDialog.setMessage("Synchronizing Data,Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
           // getChildData();


            //Intent intent = new Intent(Login.this,ChildCompany.class);
            //startActivity(intent);

            */
        }




    }


    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }






    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }



}
