package com.example.validationwithretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    private static final String url = "http://192.168.0.101/apiforAndroid/api/";
    private static ApiController controllerObject;
    private static Retrofit retrofit;


    public ApiController() {

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized ApiController getInstance(){

            if(controllerObject==null){
                controllerObject=new ApiController();

            }

            return controllerObject;

    }


    ApiList getApiList(){

        return  retrofit.create(ApiList.class);

    }



}
