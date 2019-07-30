package com.list.pixabay.network;

import com.google.gson.Gson;
import com.list.pixabay.model.PixaData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Api {

    private static ApiInterface api;
    private static final String BASE_URL = "https://pixabay.com/api/?key=13148476-1f6e7e6df91b8170023dfcfb0&q=yellow+flowers&image_type=photo&pretty=true";

    public static ApiInterface getApi() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {
        @GET(BASE_URL)
        Call<PixaData> getPixaList();
    }
}