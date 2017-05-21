package com.faizal.bikesmap;

import com.faizal.bikesmap.Model.ContractReply;
import com.faizal.bikesmap.Model.StationReply;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fpatel on 21/05/2017.
 */

public interface RestService {
    @GET("/vls/v1/contracts")
    Call<List<ContractReply>> getContractList(@QueryMap Map<String, String> options);
    @GET("/vls/v1/stations")
    Call<StationReply> getStationList(@QueryMap Map<String, String> options);
}

