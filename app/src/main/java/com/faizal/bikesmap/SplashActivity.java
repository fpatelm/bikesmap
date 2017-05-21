package com.faizal.bikesmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //do rest call to get contract list and save in Preferences
        RestApi.GetContractList(new RestApi.ICallBack() {
            @Override
            public void success() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void error() {
                //show a toast to the suer
            }
        });

  /*      new Handler().postDelayed(new Runnable() {
                *//*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 *//*
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);
*/

    }
}
