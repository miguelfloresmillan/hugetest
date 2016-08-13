package com.mgl.test.hugetest.services;

import com.mgl.test.hugetest.model.CurrencyConvertModel;
import com.mgl.test.hugetest.services.interfaces.IRateConversionService;
import com.mgl.test.hugetest.services.utils.ServiceCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateConversionService {

    public void getCurrencyExchanges(String baseCurrency, final ServiceCallback<CurrencyConvertModel> callback) {
        IRateConversionService service = IRateConversionService.retrofit.create(IRateConversionService.class);
        Call<CurrencyConvertModel> call = service.getExchangeRates(baseCurrency);
        call.enqueue(new Callback<CurrencyConvertModel>() {
            @Override
            public void onResponse(Call<CurrencyConvertModel> call, Response<CurrencyConvertModel> response) {
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<CurrencyConvertModel> call, Throwable t) {
                callback.onFailure(new Exception(t));

            }
        });
    }

}
