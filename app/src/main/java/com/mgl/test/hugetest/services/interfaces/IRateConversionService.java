package com.mgl.test.hugetest.services.interfaces;

import com.mgl.test.hugetest.model.CurrencyConvertModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRateConversionService {

    static final String RATE_API_BASE_URL = "http://api.fixer.io";

    @GET("/latest")
    Call<CurrencyConvertModel> getExchangeRates(@Query("base") String baseCurrency);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RATE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
