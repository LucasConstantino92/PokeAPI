package com.lcons.pokeapi.retrofit;

import com.lcons.pokeapi.model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PokemonDex {

    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();
}
