package com.gmail.hanivisushiva.maps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.hanivisushiva.maps.Models.Data;
import com.gmail.hanivisushiva.maps.Models.DatabaseModel;
import com.gmail.hanivisushiva.maps.Models.Datum;
import com.gmail.hanivisushiva.maps.Storage.SharedPrefManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;





public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,EasyPermissions.PermissionCallbacks {

    private DataHelper db;
    private GoogleMap mMap;
    private Marker marker;
    private Context context;
    private SupportMapFragment mapFragment;
    private File file;



    ArrayList<PolygonOptions> arrayList = new ArrayList<>();
    ArrayList<DatabaseModel> databaseModelArrayList = new ArrayList<>();
    ArrayList<String> data_id = new ArrayList<>();


    ArrayList<String> sold_plots = new ArrayList<>();
    ArrayList<String> booked_plots = new ArrayList<>();
    ArrayList<String> available_plots = new ArrayList<>();
    ArrayList<String> roads = new ArrayList<>();


    ArrayList<String> facing_plots_no = new ArrayList<>();
    ArrayList<String> north_west = new ArrayList<>();
    ArrayList<String> south_east = new ArrayList<>();
    ArrayList<String> south_west = new ArrayList<>();
    ArrayList<String> north = new ArrayList<>();
    ArrayList<String> west = new ArrayList<>();
    ArrayList<String> east = new ArrayList<>();
    ArrayList<String> south = new ArrayList<>();


    ArrayList<LatLng> centerPositions = new ArrayList<>();
    ArrayList<String> plot_no = new ArrayList<>();
    HashMap<String, String> click = new HashMap<>();
    HashMap<String, DatabaseModel> all_data = new HashMap<>();

    HashMap<String, Polygon> polygon_id = new HashMap<>();
    ArrayList<Polygon> all_polygon = new ArrayList<>();





    ArrayList<String> plot_id = new ArrayList<>();
    HashMap<String,LatLng> center_hash = new HashMap<>();
    LatLng center_x;
    private TextView textView;
    View main;
    ImageView imageView;
    String pid,cid;


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



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




        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        pid = getIntent().getStringExtra("pid");
        cid = getIntent().getStringExtra("center_map");



        String[] separated = cid.split(",");

        Double part_center1 = (Double)Double.parseDouble( separated[0]); // 004
        Double part_center2 = (Double)Double.parseDouble(separated[1]);

        center_x =  new LatLng(part_center1 ,part_center2);




        Toast(pid);









        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        db = new DataHelper(getApplicationContext());
        get_spots();






        ImageButton search = (ImageButton) findViewById(R.id.search);
        ImageButton center = (ImageButton) findViewById(R.id.center);

        ImageButton share = (ImageButton) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // openCamera();
                openCamera();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogLayout = inflater.inflate(R.layout.search, null);

                final Spinner spinner = dialogLayout.findViewById(R.id.spinner);
                final Spinner spinner_status = dialogLayout.findViewById(R.id.spinner_status);
                final Spinner spinner_facing = dialogLayout.findViewById(R.id.spinner_facing);
                final EditText plot = dialogLayout.findViewById(R.id.search_bar);


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MapsActivity.this,android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.type));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                final ArrayAdapter<String> adapter_status = new ArrayAdapter<String>(MapsActivity.this,android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.fruits));
                adapter_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_status.setAdapter(adapter_status);


                ArrayAdapter<String> adapter_facing = new ArrayAdapter<String>(MapsActivity.this,android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.colours));
                adapter_facing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_facing.setAdapter(adapter_facing);




                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        switch (position){
                            case 0:
                                spinner_status.setVisibility(View.GONE);
                                spinner_facing.setVisibility(View.GONE);
                                plot.setVisibility(View.VISIBLE);
                                break;
                            case 1:

                                spinner_status.setVisibility(View.VISIBLE);
                                spinner_facing.setVisibility(View.VISIBLE);
                                plot.setVisibility(View.GONE);
                                break;
                        }
                        //String s = spinner.getSelectedItem().toString();
                        //Toast(s);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                final Button search_btn = (Button) dialogLayout.findViewById(R.id.search_btn);
                builder.setView(dialogLayout);

                final AlertDialog alertDialog = builder.show();





               search_btn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       no_color();
                       EditText search = (EditText) dialogLayout.findViewById(R.id.search_bar);

                       String type_text =  spinner.getSelectedItem().toString();
                       String status_text =  spinner_status.getSelectedItem().toString();
                       String facing_text =  spinner_facing.getSelectedItem().toString();


                       if (type_text.equals("Status")){

                           if(facing_text.equals("Select-Facing")){



                               switch (status_text){
                                   case "Available":
                                       search(available_plots,"available");
                                       Toast(status_text);
                                       break;
                                   case "Sold":
                                       search(sold_plots,"sold");
                                       Toast(status_text);
                                       break;
                                   case "Booked":
                                       search(booked_plots,"booked");
                                       Toast(status_text);
                                       break;

                               }

                           }else {
                               switch (facing_text){
                                   case "North":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "South":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "East":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "West":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "North-East":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "North-West":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "South-East":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                   case "South-West":
                                       search(get_facing(status_text,facing_text.toLowerCase()),status_text.toLowerCase());
                                       break;
                                       default:
                                          Toast("no plots available");
                                           break;
                               }

                               if (facing_plots_no.size() == 0){
                                   Toast("no Plots Available");
                                   default_color();
                               }
                           }


                       }else {
                           String plot_id = search.getText().toString();
                           if (plot_id.length() == 1){
                               plot_id = 0+plot_id;
                           }


                           if (polygon_id.get(plot_id) != null){

                               polygon_id.get(plot_id).setFillColor(Color.YELLOW);

                           }else {
                               default_color();
                               Toast("this is not a plot Id");
                           }

                       }


                       alertDialog.dismiss();

                   }
               });





            }
        });


        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center_x,18));
                default_color();
               Toast("center clicked");

               Log.e("all",all_data.toString());

            }
        });


    }


    private void search(ArrayList status,String color){
        no_color();

        for (int a=0;a<status.size();a++){


            if (color.equals("sold")){
                polygon_id.get(status.get(a)).setFillColor(getResources().getColor(R.color.sold));
            }else if (color.equals("booked")){
                polygon_id.get(status.get(a)).setFillColor(getResources().getColor(R.color.booked));
            }else {
                polygon_id.get(status.get(a)).setFillColor(getResources().getColor(R.color.available));
            }



        }

    }


    private void no_color(){
        for (int a=0;a<all_polygon.size();a++){

            all_polygon.get(a).setFillColor(getResources().getColor(R.color.dim));

        }

        for (int a=0;a<roads.size();a++){

            polygon_id.get(roads.get(a)).setFillColor(Color.BLACK);

        }


    }


    private void default_color(){

        for (int a=0;a<all_polygon.size();a++){

            all_polygon.get(a).setFillColor(Color.YELLOW);

        }

        for (int a=0;a<roads.size();a++){

            polygon_id.get(roads.get(a)).setFillColor(Color.BLACK);

        }


    }





    public void getAllNews() {
        DatabaseModel data;
        databaseModelArrayList.clear();
        sold_plots.clear();
        available_plots.clear();
        booked_plots.clear();
        arrayList.clear();
        plot_id.clear();

                 Cursor cursora = db.getAllData();
                 if (cursora.getCount() > 0) {
                     cursora.moveToFirst();
                     do {
                                 data = new DatabaseModel(
                                 cursora.getInt(0),
                                 cursora.getString(1),
                                 cursora.getString(2),
                                 cursora.getString(3),
                                 cursora.getString(4),
                                 cursora.getString(5),
                                 cursora.getString(6)
                         );


                         databaseModelArrayList.add(data);

                         all_data.put(cursora.getString(1),data);



                     } while (cursora.moveToNext());

                     for (int i=0;i<databaseModelArrayList.size();i++){
                         ArrayList<LatLng> latLngs = new ArrayList<>();

                         PolygonOptions polygonOptions = new PolygonOptions().clickable(true);

                         String s = databaseModelArrayList.get(i).getPoints();
                         Gson gson = new Gson();
                         Type type = new TypeToken<ArrayList<String>>() {}.getType();
                         ArrayList<String> finalOutputString = gson.fromJson(s, type);
                         for (int j=0;j<finalOutputString.size();j++){

                             Double part1 = (Double)Double.parseDouble(finalOutputString.get(j)); // 004
                             Double part2 = (Double)Double.parseDouble(finalOutputString.get(j+1));
                             j++;
                             LatLng x =  new LatLng(part1 ,part2);
                             polygonOptions.add(x);

                             latLngs.add(x);

                         }

                         Double part01 = (Double)Double.parseDouble(finalOutputString.get(0)); // 004
                         Double part02 = (Double)Double.parseDouble(finalOutputString.get(1)); //
                         //a =  new LatLng(part01 ,part02);


                         if (databaseModelArrayList.get(i).getType().equals("road")){
                             center_hash.put("road",getCenterOfPolygon(latLngs));
                             polygonOptions.strokeWidth(2).fillColor(Color.BLACK).geodesic(true);

                             roads.add(databaseModelArrayList.get(i).getPlot_no());
                         }else {
                             center_hash.put(databaseModelArrayList.get(i).getPlot_no(),getCenterOfPolygon(latLngs));
                             centerPositions.add(getCenterOfPolygon(latLngs));
                             plot_no.add(databaseModelArrayList.get(i).getPlot_no());



                             if(databaseModelArrayList.get(i).getPlot_status().equals("Sold")){

                                 sold_plots.add(databaseModelArrayList.get(i).getPlot_no());


                                 polygonOptions.strokeWidth(2).fillColor(Color.YELLOW).geodesic(true);
                             }else if(databaseModelArrayList.get(i).getPlot_status().equals("Available")){

                                 available_plots.add(databaseModelArrayList.get(i).getPlot_no());
                                 polygonOptions.strokeWidth(2).fillColor(Color.YELLOW).geodesic(true);
                             }else {

                                 booked_plots.add(databaseModelArrayList.get(i).getPlot_no());
                                 polygonOptions.strokeWidth(2).fillColor(Color.YELLOW).geodesic(true);
                             }




                         }

                         Log.e("type",databaseModelArrayList.get(i).getType());

                         if (databaseModelArrayList.get(i).getPlot_no() == null){
                             plot_id.add("its road");
                         }else {
                             plot_id.add(databaseModelArrayList.get(i).getPlot_no());
                         }




                         arrayList.add(polygonOptions);




                     }

                     Log.e("centerPosition" , centerPositions.size()+"");

                     mapFragment.getMapAsync(this);


                 }
             }



    private void get_all_id() {

        Cursor cursora = db.getAllData();
        if (cursora.getCount() > 0) {
            cursora.moveToFirst();
            do {
                String s = cursora.getString(0);


                data_id.add(s);


            } while (cursora.moveToNext());


        }

    }



    private ArrayList get_facing(String status,String facing){
        facing_plots_no.clear();
        Cursor facing_data = db.get_by_facing(facing,status);

        if (facing_data.getCount() > 0) {
            facing_data.moveToFirst();
            do {
                String s = facing_data.getString(1);


                facing_plots_no.add(s);


            } while (facing_data.moveToNext());

            Log.e("northeast",facing_plots_no.toString());
        }else {
            Log.e("northeast","no data to get");
        }

        return facing_plots_no;


    }


    private void get_spots() {

        get_all_id();


        Call<Data> call = RetrofitClient.getmInstance().getApi().get_all(pid);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();

                DatabaseModel databaseModel = new DatabaseModel();
                final List<Datum> data1 = data.getData();

               if (data1 != null) {
                   for (int i = 0; i < data1.size(); i++) {
                       // Log.e("size",data1.get(i).getPoints().size()+"");

                       databaseModel.setPlot_no(data1.get(i).getPlotNo());
                       databaseModel.setPlot_status(data1.get(i).getPlotStatus());
                       databaseModel.setType(data1.get(i).getStatus());
                       databaseModel.setSize(data1.get(i).getSize());
                       databaseModel.setFacing(data1.get(i).getFacing());


                       ArrayList<String> p = new ArrayList<>();
                       for (int j = 0; j < data1.get(i).getPoints().size(); j++) {
                           //Log.e("size",.getClass().getName());
                           String s = data1.get(i).getPoints().get(j);

                           p.add(s);


                       }

                       Gson gson = new Gson();

                       String inputString = gson.toJson(p);

                       if (!data_id.contains(data1.get(i).getId())) {
                           databaseModel.setPoints(p.toString());

                           if (db.insertData(databaseModel)) {
                               Log.e("insering data", "data inserted");
                           }
                       } else {
                           Log.e("contains", "not inserted");
                       }

                   }

               }

            getAllNews();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("server failure",t.getMessage());
                getAllNews();
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Polygon polygon = null;
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
       // mMap.getUiSettings().setZoomControlsEnabled(true);






        Log.e("size",arrayList.size()+"");

        for (int z = 0;z<arrayList.size();z++){
           polygon =  mMap.addPolygon(arrayList.get(z));
           click.put(polygon.getId(),plot_id.get(z));
           polygon_id.put(plot_id.get(z),polygon);
           all_polygon.add(polygon);

        }

        Log.e("a",arrayList.size()+"");
        Log.e("a",roads.size()+"");
        
        polygon.setClickable(true);


        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            public void onPolygonClick(Polygon polygon) {
                TextView facing,plot_no,size;


                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.alert, null);
                plot_no = dialogLayout.findViewById(R.id.plot_name);
                facing = dialogLayout.findViewById(R.id.facing);
                size = dialogLayout.findViewById(R.id.size);




                builder.setPositiveButton("OK", null);
                builder.setView(dialogLayout);
                builder.show();

               // Log.e("aaaa",all_data.get("15").getPlot_no());
                Log.e("ppaa",click.get(polygon.getId()));



                String plot_id = click.get(polygon.getId());

                String facing_text = all_data.get(plot_id).getFacing();
                String size_text = all_data.get(plot_id).getSize();
                String plot_no_text = all_data.get(plot_id).getPlot_no();

                size.setText("feets : "+size_text);
                plot_no.setText("plot no : "+plot_no_text);
                facing.setText("facing : " +facing_text);


            }
        });











        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center_x,18));

       for (int c = 0;c<centerPositions.size();c++){

            LatLng f = centerPositions.get(c);





            String s = plot_no.get(c);
            GroundOverlayOptions iroad = new GroundOverlayOptions();

            if (s.length()>1){
                iroad.image(BitmapDescriptorFactory.fromBitmap(textAsBitmap(s, 10, Color.RED)));
            }else {
                iroad.image(BitmapDescriptorFactory.fromBitmap(textAsBitmap("0"+s, 30, Color.RED)));
            }

            iroad.position(f, 8f);
            iroad.zIndex(4);
            iroad.bearing(0);
            iroad.visible(true);
            iroad.transparency(0);
            mMap.addGroundOverlay(iroad);

        }






//        IconGenerator factory = new IconGenerator(this);
//        Bitmap icon = factory.makeIcon("11:15 1.08.2013");
//
//        mMap.addMarker(new MarkerOptions().position(pos).
//        icon(BitmapDescriptorFactory.fromBitmap(icon)));
//



        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }






             private static LatLng getCenterOfPolygon(ArrayList<LatLng> latLngList) {
                 double[] centroid = {0.0, 0.0};
                 for (int i = 0; i < latLngList.size(); i++) {
                     centroid[0] += latLngList.get(i).latitude;
                     centroid[1] += latLngList.get(i).longitude;
                 }
                 int totalPoints = latLngList.size();
                 return new LatLng(centroid[0] / totalPoints, centroid[1] / totalPoints);
             }









             public Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setStrokeWidth(5);
        paint.setShadowLayer(0, 0, 0, 0xff000000);
        paint.setTextAlign(Paint.Align.LEFT);

        int width = (int) (paint.measureText(text) + 0.5f); // round
        float baseline = (int) (-paint.ascent() + 0.5f); // ascent() is negative
        int height = (int) (baseline + paint.descent() + 0.5f);

        Bitmap image = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);

        Drawable drawable2 = new BitmapDrawable(getResources(), image);
        Drawable drawable1 = getResources().getDrawable(R.drawable.back);

        Drawable[] layers = new Drawable[2];
        layers[0] = drawable1;
        layers[1] = drawable2;

        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(1, 0, 0, 0, 0);

        Bitmap bitmap = Bitmap.createBitmap((width + 5), (height + 5),
                Bitmap.Config.ARGB_8888);

        Canvas can = new Canvas(bitmap);
        layerDrawable.setBounds(0, 0, width, height);
        layerDrawable.draw(can);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.getResources(),
                bitmap);
        bitmapDrawable.setBounds(0, 0, (width + 5), (height + 5));

        return bitmapDrawable.getBitmap().copy(
                Bitmap.Config.ARGB_8888, true);
    }






    private void Toast(String s){
        Toast.makeText(getApplicationContext(), s,Toast.LENGTH_SHORT).show();
    }



    public void snapShot(){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center_x,18));
        GoogleMap.SnapshotReadyCallback callback=new GoogleMap.SnapshotReadyCallback () {
            Bitmap bitmap;
            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                bitmap=snapshot;

                try{
                    file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"map.png");
                    FileOutputStream fout=new FileOutputStream(file);
                    bitmap.compress (Bitmap.CompressFormat.PNG,90,fout);
                    Toast.makeText (MapsActivity.this, "Capture"+bitmap.getGenerationId(), Toast.LENGTH_SHORT).show ();


                    Uri uri = getImageUri(getApplicationContext(),bitmap);

                    String link = "http://maps.google.com/maps?q=loc:" + center_x.latitude +","+ center_x.longitude;


//            Toast.makeText(getApplicationContext(),b.toString(),Toast.LENGTH_SHORT).show();


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,"click the link: \n "+link );
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, "Share image via:"));



                }catch (Exception e){
                    e.printStackTrace ();
                    Toast.makeText (MapsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show ();
                }


            }
        };
        mMap.snapshot (callback);
    }



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

        }
    }




    @AfterPermissionGranted(123)
    private void openCamera() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

            snapShot();

        } else {
            EasyPermissions.requestPermissions(this, "We need permissions because this and that",
                    123, perms);
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

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void logout(){
        SharedPrefManager.get_mInstance(getApplicationContext()).clear();
        Intent intent = new Intent(MapsActivity.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

















}
