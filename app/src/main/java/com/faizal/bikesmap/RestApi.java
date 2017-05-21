package com.faizal.bikesmap;

import android.util.Log;

import com.faizal.bikesmap.Model.ContractReply;
import com.faizal.bikesmap.Model.StationReply;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fpatel on 21/05/2017.
 */

public class RestApi {

    public static void GetContractList(final ICallBack oCallBack){


        //Now use restadapter to create an instance of your interface
        RestService restService = GetRetrofitService();
        //populate the request parameters
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("apiKey", "38440d36615d46c21c37e5e4cfb487f6a17c9e3c");

        Call<ContractReply> call = restService.getContractList(queryMap);
        call.enqueue(new Callback<ContractReply>() {
            @Override
            public void onResponse(Call<ContractReply> call, Response<ContractReply> response) {
                oCallBack.success();
                Log.d("onResponse: ",response.toString());
            }

            @Override
            public void onFailure(Call<ContractReply> call, Throwable t) {
                oCallBack.error();
            }
        });

    }

    public static void GetStationList(final String contract, final ICallBack oCallBack){
        RestService restService = GetRetrofitService();
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("apiKey", "38440d36615d46c21c37e5e4cfb487f6a17c9e3c");
        queryMap.put("contract", contract);

        Call<StationReply> call = restService.getStationList(queryMap);

        call.enqueue(new Callback<StationReply>() {
            @Override
            public void onResponse(Call<StationReply> call, Response<StationReply> response) {
                oCallBack.success();
            }

            @Override
            public void onFailure(Call<StationReply> call, Throwable t) {
                oCallBack.error();
            }
        });

    }

    public interface ICallBack{
        void success();
        void error();

    }

    private static RestService GetRetrofitService(){
        Retrofit restAdapter =  new Retrofit.Builder()
                .baseUrl("https://api.jcdecaux.com")
                .client(Utils.GetRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return restAdapter.create(RestService.class);
    }
}
