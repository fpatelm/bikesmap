package com.faizal.bikesmap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected City is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();


    }
}
