package com.example.bharatloan;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    final static String url = "Your domain address";
    public static ApiController clientObject;
    public static Retrofit retrofit;

    public ApiController() {

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized ApiController getInstance(){
        if (clientObject==null){
            clientObject = new ApiController();
        }
        return clientObject;
    }
    public ApiSet getApi(){
        return retrofit.create(ApiSet.class);
    }
}
