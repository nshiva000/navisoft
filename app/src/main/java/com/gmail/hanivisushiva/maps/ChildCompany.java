package com.gmail.hanivisushiva.maps;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Models.Database.DChildCompany;
import com.gmail.hanivisushiva.maps.Models.dCompany.DCompany;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildCompany extends AppCompatActivity {

    String mCompany_id;
    DataHelper db;
    ArrayList<String> data_id = new ArrayList<>();
    ArrayList<DChildCompany> child_company = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_company);
        db = new DataHelper(getApplicationContext());


        mCompany_id  = getIntent().getStringExtra("company_id");
        //Toast(mCompany_id);
        Log.e("companyId",mCompany_id);
    get_id();

     getData();

   get_all_company_data();

    }





    private void getData(){

        Call<DCompany> dCompanyCall = RetrofitClient.getmInstance().getApi().get_Child(mCompany_id);

        dCompanyCall.enqueue(new Callback<DCompany>() {
            @Override
            public void onResponse(Call<DCompany> call, Response<DCompany> response) {
                DCompany body = response.body();



                for (int i = 0; i < body.getDData().size(); i++){

                    if (!data_id.contains(body.getDData().get(i).getDid())){
                        if (db.insertChildData(body.getDData().get(i))){
                            Log.e("insering data","data inserted"+body.getDData().get(i).getDname());

                        }
                    }else {
                        Log.e("insering data","data already contains");
                    }
                }





                get_all_company_data();



            }

            @Override
            public void onFailure(Call<DCompany> call, Throwable t) {
              Log.e("childCompany",t.getMessage());
            }
        });

    }




    private void Toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }



    private void get_all_company_data(){
        DChildCompany dChildCompany;
        child_company.clear();

        Cursor cursora = db.getAllChildData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {

                dChildCompany = new DChildCompany(
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1),
                        cursora.getString(1)
                );



                child_company.add(dChildCompany);


            } while (cursora.moveToNext());

            Log.e("child_company_all", child_company.size()+"");


            //adapter = new CompanyAdapter(getApplicationContext(),q_company);
            //recyclerView.setAdapter(adapter);


        }

    }






    private void get_id() {

        Cursor cursora = db.getAllChildData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {

                String s = cursora.getString(1);
                data_id.add(s);
                Log.e("child_data_id",s);

            } while (cursora.moveToNext());


            Log.e("child_data_id",data_id.toString());

            Log.e("child_data_id",data_id.size()+"");
        }

    }
}
