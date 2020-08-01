package com.gmail.hanivisushiva.maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Models.Data;
import com.gmail.hanivisushiva.maps.Models.Datum;
import com.gmail.hanivisushiva.maps.Models.Login.SignIn;
import com.gmail.hanivisushiva.maps.Models.Project.Project;
import com.gmail.hanivisushiva.maps.Models.dCompany.DCompany;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sync extends AppCompatActivity {


    DataHelper db;
    ArrayList<String> child_id = new ArrayList<>();
    ArrayList<String> project_list_id = new ArrayList<>();
    ArrayList<String> plots_list_id = new ArrayList<>();
    String user_projects;
    String user_company,password_text,email_text;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        db = new DataHelper(getApplicationContext());


        email_text= SharedPrefManager.get_mInstance(this).getEmail();
        password_text= SharedPrefManager.get_mInstance(this).getPassword();



        getUserData(email_text,password_text);


        user_company= SharedPrefManager.get_mInstance(this).getProjects();
        user_projects= SharedPrefManager.get_mInstance(this).getProjects();


        //getChildData();
    }



    private void getUserData(final String email_text, final String password_text){

        progressDialog =new ProgressDialog(Sync.this);
        progressDialog.setMessage("Synchronizing User Data,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<SignIn> call = RetrofitClient.getmInstance().getApi().user_login(email_text,password_text);

        call.enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {
                SignIn signIn = response.body();

                if (signIn.getStatus()){

                    user_projects= signIn.getData().getProject();
                    user_company= signIn.getData().getDcompany();
                    user_company= SharedPrefManager.get_mInstance(Sync.this).getProjects();
                    user_projects= SharedPrefManager.get_mInstance(Sync.this).getProjects();

                    getChildData();

                    Toast("user Data updated");


                    SharedPrefManager.get_mInstance(Sync.this)
                            .saveUser(signIn);


                }else {
                    Toast("wrong email or Password"+email_text+password_text);
                }
                //Toast(signIn.getMessage());
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t)

            {

                getChildData();
                Log.e("error - login",t.getMessage());
            }
        });
    }


    private void Toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }



    private void getChildId(){


        Cursor cursora = db.getAllChildData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {

                String s = cursora.getString(1);
                child_id.add(s);
                Log.e("child_data_id",s);

            } while (cursora.moveToNext());


            Log.e("child_data_id",child_id.toString());

            Log.e("child_data_id",child_id.size()+"");
        }
    }


    private void getChildData(){


        progressDialog.setMessage("Synchronizing Data,Please Wait");

        Log.e("user_company",user_company+"---"+user_projects);
        //getChildId();
        Call<DCompany> dCompanyCall = RetrofitClient.getmInstance().getApi().get_Child("1");

        dCompanyCall.enqueue(new Callback<DCompany>() {
            @Override
            public void onResponse(Call<DCompany> call, Response<DCompany> response) {
                DCompany body = response.body();



                if (body.getStatus()){
                    progressDialog.setMessage("Synchronizing Child Data,Please Wait");

                    db.delete_child();
                    getChildId();
                    for (int i = 0; i < body.getDData().size(); i++){


                        if (!child_id.contains(body.getDData().get(i).getDid())){
                            if (db.insertChildData(body.getDData().get(i))){
                                Log.e("insering data","data inserted"+body.getDData().get(i).getDname());

                            }
                        }else {
                            Log.e("insering data","child data already contains");
                        }
                    }

                    getAllProjects();
                }







            }

            @Override
            public void onFailure(Call<DCompany> call, Throwable t) {
                progressDialog.setMessage("Synchronizing Child Data Failed,Please Wait");
                Log.e("childCompany","error");
                getAllProjects();
            }
        });
    }



    private void getAllProjects(){
        //get_project_id();


        //Log.e("user_project",user_projects);
        Call<Project> projectCall = RetrofitClient.getmInstance().getApi().get_all_projects(user_projects);

        projectCall.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project project = response.body();




                if (project.getStatus()){

                    progressDialog.setMessage("Synchronizing Project Data,Please Wait");

                    db.delete_project();

                    get_project_id();
                    for (int i = 0; i < project.getProjectsData().size(); i++) {


                        if (!project_list_id.contains(project.getProjectsData().get(i).getPid())) {
                            if (db.insertProject(project.getProjectsData().get(i))) {


                                Log.e("insering data", "project inserted");


                            }

                        }else {
                            Log.e("project_id","already contains project");
                        }

                    }

                }else {
                    Log.e("something","error");
                }



                //Intent intent = new Intent(Login.this,ChildCompany.class);
                //startActivity(intent);
                //get_all_company_data();

                getPlots();



            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.e("cccProject",t.getMessage());
                progressDialog.setMessage("Synchronizing Project Data Failed,Please Wait");

                getPlots();
                //get_all_company_data();
            }
        });

    }



    private void get_project_id() {

        Cursor cursora = db.getProjectData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {

                String s = cursora.getString(1);
                project_list_id.add(s);
                Log.e("project_id",s);

            } while (cursora.moveToNext());

        }

    }


    private void getPlots(){




        Call<Data> call = RetrofitClient.getmInstance().getApi().get_all("1,2");

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                Data data = response.body();


                assert data != null;
                if (data.getStatus()){

                    progressDialog.setMessage("Synchronizing Map,Please Wait");
                    db.delete_plots();
                    get_plot_id();

                    final List<Datum> datumList = data.getData();


                    Log.e("datumlist",datumList.size()+"");





                    for (int i = 0; i < datumList.size(); i++) {



                        ArrayList<String> p = new ArrayList<>();
                        for (int j = 0; j < datumList.get(i).getPoints().size(); j++) {
                            //Log.e("size",.getClass().getName());
                            String s = datumList.get(i).getPoints().get(j);

                            p.add(s);

                        }

                        Gson gson = new Gson();

                        String inputString = gson.toJson(p);

                        datumList.get(i).setString_points(p.toString());




                        if (!plots_list_id.contains(datumList.get(i).getId())) {
                            if (db.insertedData(datumList.get(i))) {
                                Log.e("plots insering data", "data inserted");
                            }

                        }else {
                            Log.e("plots insering data", " contains data inserted");
                        }


                    }








                }

                //DatabaseModel databaseModel = new DatabaseModel();

                progressDialog.dismiss();

                Intent intent = new Intent(Sync.this,ChildCompany.class);
                startActivity(intent);



            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressDialog.setMessage("Synchronizing Map Data Failed,Please Wait");
                progressDialog.dismiss();
                Log.e("server failure",t.getMessage());
                Intent intent = new Intent(Sync.this,ChildCompany.class);
                startActivity(intent);


            }
        });


    }




    private void get_plot_id() {

        Cursor cursora = db.getAllData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {
                String s = cursora.getString(0);


                plots_list_id.add(s);


            } while (cursora.moveToNext());


        }

    }

}
