package com.lcons.pokeapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcons.pokeapi.adapter.PokemonListAdapter;
import com.lcons.pokeapi.common.Common;
import com.lcons.pokeapi.common.ItemOffSetDecoration;
import com.lcons.pokeapi.model.Pokedex;
import com.lcons.pokeapi.retrofit.PokemonDex;
import com.lcons.pokeapi.retrofit.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonList extends Fragment {

    PokemonDex pokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recycleView;

    static PokemonList instance;

    public static PokemonList getInstance() {
        if (instance == null)
            instance = new PokemonList();
        return instance;
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstance();
        pokemonDex = retrofit.create(PokemonDex.class);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonList.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonList newInstance(String param1, String param2) {
        PokemonList fragment = new PokemonList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        pokemon_list_recycleView = (RecyclerView) view.findViewById(R.id.pokemon_list_recycleview);
        pokemon_list_recycleView.setHasFixedSize(true);
        pokemon_list_recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemOffSetDecoration itemOffSetDecoration = new ItemOffSetDecoration(getActivity(), R.dimen.spacing);
        pokemon_list_recycleView.addItemDecoration(itemOffSetDecoration);

        fetchData();

        return view;
    }

    private void fetchData() {
        compositeDisposable.add(pokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);

                        pokemon_list_recycleView.setAdapter(adapter);
                    }
                           })
        );
    }
}