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

import com.gmail.hanivisushiva.maps.Adapters.ProjectAdapter;
import com.gmail.hanivisushiva.maps.Models.Database.DChildCompany;
import com.gmail.hanivisushiva.maps.Models.Database.DatabaseProject;
import com.gmail.hanivisushiva.maps.Models.Project.Project;
import com.gmail.hanivisushiva.maps.Models.Project.ProjectsDatum;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DProject extends AppCompatActivity {

    String child_company_id,project_id;

    DataHelper db;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> data_id = new ArrayList<>();
    ArrayList<DatabaseProject> project_list = new ArrayList<>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);


        db = new DataHelper(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        recyclerView = findViewById(R.id.project_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        child_company_id = getIntent().getStringExtra("company_id");
        project_id = SharedPrefManager.get_mInstance(getApplicationContext()).getProjects();

        get_id();

        Log.e("data_id",data_id.toString());
       getData();

      get_all_company_data();

    }


    private void getData(){

        Log.e("data","xxxxxdata already contains pr");
        Call<Project> projectCall = RetrofitClient.getmInstance().getApi().get_projects(child_company_id,project_id);

        projectCall.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project project = response.body();




                if (project.getStatus()){


                    for (int i = 0; i < project.getProjectsData().size(); i++) {


                        if (!data_id.contains(project.getProjectsData().get(i).getPid())) {
                            if (db.insertProject(project.getProjectsData().get(i))) {


                                Log.e("insering data", "project inserted");


                            }

                        }else {
                            Log.e("child_company_id","already contains project");
                        }

                    }

                }else {
                    Log.e("something","error");
                }




            get_all_company_data();



            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.e("cccProject",t.getMessage());
                get_all_company_data();
            }
        });
    }




    private void get_all_company_data(){

        DatabaseProject databaseProject;
        project_list.clear();

        Cursor cursora = db.getProjectData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {

                databaseProject = new DatabaseProject(
                        cursora.getString(0),
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
                        cursora.getString(16)

                );

                Log.e("dddd",databaseProject.toString());


                project_list.add(databaseProject);


            } while (cursora.moveToNext());

            Log.e("project_all", project_list.size()+"aa");


            adapter = new ProjectAdapter(getApplicationContext(),project_list);
            recyclerView.setAdapter(adapter);


        }

    }






    private void get_id() {

        Cursor cursora = db.getProjectData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {

                String s = cursora.getString(1);
                data_id.add(s);
                Log.e("child_data_id",s);

            } while (cursora.moveToNext());

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
            return true;
        }

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    private void logout(){
        SharedPrefManager.get_mInstance(getApplicationContext()).clear();
        Intent intent = new Intent(DProject.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
