package com.lcons.pokeapi.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance(){
        if (instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return instance;
        }
}