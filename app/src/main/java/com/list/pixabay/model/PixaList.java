package com.list.pixabay.model;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.list.pixabay.network.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaList extends BaseObservable {

    private MutableLiveData<PixaData> pixaDataMutableList = new MutableLiveData<>();


    public MutableLiveData<PixaData> getPixaDataList() {
        return pixaDataMutableList;
    }

    public void fetchList() {
        Callback<PixaData> callback = new Callback<PixaData>() {
            @Override
            public void onResponse(Call<PixaData> call, Response<PixaData> response) {
                PixaData body = response.body();
                pixaDataMutableList.setValue(body);
            }

            @Override
            public void onFailure(Call<PixaData> call, Throwable t) {
            }
        };

        Api.getApi().getPixaList().enqueue(callback);
    }
}
