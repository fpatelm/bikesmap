package com.faizal.bikesmap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.faizal.bikesmap.Model.BikeInfo;
import com.faizal.bikesmap.Model.StationReply;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<BikeInfo> bikeInfoList = new ArrayList<>();
    Adapter adapter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
    }

    public void submit(View view) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.logo);
        builderSingle.setTitle("Select One City:-");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);

        SharedPreferences prefs = this.getSharedPreferences("bikemap", Context.MODE_PRIVATE);
        Set<String> setContract =  prefs.getStringSet("ContractList",null);

        if(setContract == null){
            return;
        }

        for(String item : setContract){
            arrayAdapter.add(item);
        }

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected City is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        RestApi.GetStationList(strName, new RestApi.ICallBackStation() {
                            @Override
                            public void success(Response<List<StationReply>> response) {
                                //initialise recycleview adapter
                                if (response.body().size() > 0) {
                                    bikeInfoList.clear();
                                    for(StationReply item : response.body()) {
                                        bikeInfoList.add(new BikeInfo().setName(item.getName()).setNumber(item.getNumber()).setAddress(item.getAddress()));
                                        adapter  = new Adapter(bikeInfoList);
                                        rv.setAdapter(adapter);
                                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                    }
                                }
                            }
                            @Override
                            public void error() {

                            }
                        },getBaseContext());
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();


    }
}
