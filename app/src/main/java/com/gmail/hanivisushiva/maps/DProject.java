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

import com.gmail.hanivisushiva.maps.Adapters.ProjectAdapter;
import com.gmail.hanivisushiva.maps.Models.Database.DChildCompany;
import com.gmail.hanivisushiva.maps.Models.Database.DatabaseProject;
import com.gmail.hanivisushiva.maps.Models.Project.Project;
import com.gmail.hanivisushiva.maps.Models.Project.ProjectsDatum;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;

import java.util.ArrayList;


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
Toast(project_id);


      get_all_company_data();

    }

    private void Toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }





    private void get_all_company_data(){

        DatabaseProject databaseProject;
        project_list.clear();

        Cursor cursora = db.getOnlyProjectData(child_company_id);
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


                if (project_id.contains(databaseProject.getPID())){
                    project_list.add(databaseProject);
                }





            } while (cursora.moveToNext());

            Log.e("project_all", project_list.size()+"aa");


            adapter = new ProjectAdapter(getApplicationContext(),project_list);
            recyclerView.setAdapter(adapter);


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
        }else if (id == R.id.action_sync){
            Sync_data();
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


    private void Sync_data(){

        Intent intent = new Intent(DProject.this,Sync.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
