package com.faizal.bikesmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.faizal.bikesmap.Model.ContractReply;
import com.faizal.bikesmap.Model.StationReply;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fpatel on 21/05/2017.
 */

public class RestApi {

    public static void GetContractList(final ICallBack oCallBack, Context context){
        Log.d("onResponse: ","GetContractList");
        //Now use restadapter to create an instance of your interface
        RestService restService = GetRetrofitService();
        //populate the request parameters
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("apiKey", "38440d36615d46c21c37e5e4cfb487f6a17c9e3c");

        Call<List<ContractReply>> call = restService.getContractList(queryMap);

        final Set<String> setContract = new HashSet<>();
        SharedPreferences keyValues = context.getSharedPreferences("bikemap", Context.MODE_PRIVATE);
        final SharedPreferences.Editor keyValuesEditor = keyValues.edit();


        call.enqueue(new Callback<List<ContractReply>>() {
            @Override
            public void onResponse(Call<List<ContractReply>> call, Response<List<ContractReply>> response) {

                if(response.body().size() > 0){
                    for(ContractReply item : response.body()){
                        setContract.add(item.getName());
                    }
                }
                keyValuesEditor.putStringSet("ContractList",setContract);
                keyValuesEditor.commit();
                Log.d("onResponse: ",response.body().get(0).getName());
                oCallBack.success();
            }

            @Override
            public void onFailure(Call<List<ContractReply>> call, Throwable t) {
                Log.d("onResponse: ", t.getLocalizedMessage());
                oCallBack.error();
            }
        });

    }

    public static void GetStationList(final String contract, final ICallBackStation oCallBack, Context context){
        RestService restService = GetRetrofitService();
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("apiKey", "38440d36615d46c21c37e5e4cfb487f6a17c9e3c");
        queryMap.put("contract", contract);

        SharedPreferences keyValues = context.getSharedPreferences("bikemap", Context.MODE_PRIVATE);
        final SharedPreferences.Editor keyValuesEditor = keyValues.edit();

        Call<List<StationReply>> call = restService.getStationList(queryMap);

        call.enqueue(new Callback<List<StationReply>>() {
            @Override
            public void onResponse(Call<List<StationReply>> call, Response<List<StationReply>> response) {
                oCallBack.success(response);
            }

            @Override
            public void onFailure(Call<List<StationReply>> call, Throwable t) {
                oCallBack.error();
            }
        });

    }

    public interface ICallBack{
        void success();
        void error();

    }

    public interface ICallBackStation{
        void success(Response<List<StationReply>> response);
        void error();

    }

    private static RestService GetRetrofitService(){
        return new Retrofit.Builder()
                .baseUrl("https://api.jcdecaux.com")
                .client(Utils.GetRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestService.class);
    }
}
