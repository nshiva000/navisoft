package com.gmail.hanivisushiva.maps;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Models.Login.SignIn;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login;
    String email_text,password_text,mac_no;




    @Override
    protected void onStart() {

        Toast(SharedPrefManager.get_mInstance(this).isLoggedIn()+"");
        super.onStart();
        if (SharedPrefManager.get_mInstance(this).isLoggedIn()){
            checkMac();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                    SharedPrefManager.get_mInstance(Login.this)
                           .saveUser(signIn);
                    Toast("login success");
                    checkMac();


                }else {
                    Toast("wrong email or Password");
                }
                //Toast(signIn.getMessage());
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });



    }




    private void checkMac(){

        Log.e("mymac",SharedPrefManager.get_mInstance(this).getMac());
        Log.e("mymac",mac_no);

        if (SharedPrefManager.get_mInstance(this).getMac().equals(mac_no)){
            DataHelper db = new DataHelper(getApplicationContext());

            db.delete_db(getApplicationContext());
            Intent intent = new Intent(Login.this,ChildCompany.class);
            startActivity(intent);
        }else {
            //Toast("Something went wrong please contact Administrator");


            Intent intent = new Intent(Login.this,ChildCompany.class);
            startActivity(intent);
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
}
