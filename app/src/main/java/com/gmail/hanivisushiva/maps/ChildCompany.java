package com.gmail.hanivisushiva.maps;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Adapters.ChildCompanyAdapter;
import com.gmail.hanivisushiva.maps.Models.Database.DChildCompany;
import com.gmail.hanivisushiva.maps.Models.dCompany.DCompany;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildCompany extends AppCompatActivity {

    String mCompany_id,total_projects;
    DataHelper db;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> data_id = new ArrayList<>();
    ArrayList<DChildCompany> child_company = new ArrayList<>();
    ArrayList ans_array = new ArrayList();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_company);
        db = new DataHelper(getApplicationContext());


        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);


        mCompany_id  = SharedPrefManager.get_mInstance(getApplicationContext()).getDCompany();






        recyclerView = findViewById(R.id.company_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



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
                        cursora.getString(2),
                        cursora.getString(3),
                        cursora.getString(4),
                        cursora.getString(5),
                        cursora.getString(5),
                        cursora.getString(6)
                );



                child_company.add(dChildCompany);


            } while (cursora.moveToNext());

            Log.e("child_company_all", child_company.toString());


            adapter = new ChildCompanyAdapter(getApplicationContext(),child_company);
            recyclerView.setAdapter(adapter);


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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            logout();
            Toast("logout");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void logout(){
        SharedPrefManager.get_mInstance(getApplicationContext()).clear();
        Intent intent = new Intent(ChildCompany.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
