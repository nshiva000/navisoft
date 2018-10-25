package com.gmail.hanivisushiva.maps;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Adapters.CompanyAdapter;
import com.gmail.hanivisushiva.maps.Models.Company.Company;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyPage extends AppCompatActivity {

    String uid;
    DataHelper db;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    ArrayList<String> data_id = new ArrayList<>();
    ArrayList<DCompany> q_company = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);


        uid =  SharedPrefManager.get_mInstance(getApplicationContext()).getId();

        recyclerView = findViewById(R.id.company_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        db = new DataHelper(getApplicationContext());
        get_id();
        Log.e("insering data",data_id.toString());




        get_all_company_data();
        get_data();
    }



    private void get_data(){


        Call<Company> companyCall = RetrofitClient.getmInstance().getApi().get_company(uid);
        companyCall.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                Company company = response.body();


                Log.e("success",company.getMessage());



                for (int i = 0; i < company.getData().size(); i++){

                    if (!data_id.contains(company.getData().get(i).getId())){
                        if (db.insertCompanyData(company.getData().get(i))){
                            Log.e("insering data","data inserted"+company.getData().get(i).getId());

                        }
                    }else {
                        Log.e("insering data","data already contains");
                    }
                }


                //Intent intent = new Intent(CompanyPage.this,MapsActivity.class);
                //startActivity(intent);

                //adapter = new CompanyAdapter(getApplicationContext(),q_list);
                //recyclerView.setAdapter(adapter);


                get_all_company_data();
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                Log.e("error",t.getMessage());
                Log.e("error",t.getCause().toString());
                Log.e("error",t.getLocalizedMessage());
            }
        });

    }

    private void Toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }




    private void get_id() {

        Cursor cursora = db.getAllCompanyData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {
                String s = cursora.getString(1);

                data_id.add(s);

            } while (cursora.moveToNext());
        }

    }






    private void get_all_company_data(){
       DCompany dCompany;
       q_company.clear();

        Cursor cursora = db.getAllCompanyData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {


                //Log.e("string",cursora.getString(2));

               dCompany = new DCompany(
                        cursora.getString(1),
                        cursora.getString(2),
                        cursora.getString(3),
                        cursora.getString(4),
                        cursora.getString(5),
                        cursora.getString(6),
                        cursora.getString(7),
                        cursora.getString(8),
                        cursora.getString(9),
                        cursora.getString(10),
                        cursora.getString(11),
                        cursora.getString(12),
                        cursora.getString(13),
                        cursora.getString(14),
                        cursora.getString(15),
                        cursora.getString(16),
                        cursora.getString(17),
                        cursora.getString(18),
                        cursora.getString(19),
                        cursora.getString(20),
                        cursora.getString(21),
                        cursora.getString(22),
                        cursora.getString(23),
                        cursora.getString(24),
                        cursora.getString(25)
                );



               q_company.add(dCompany);

                Log.e("string",dCompany.toString()+"skbcj");
                Log.e("string",q_company.toString());





            } while (cursora.moveToNext());


            adapter = new CompanyAdapter(getApplicationContext(),q_company);
            recyclerView.setAdapter(adapter);


        }

    }
}
