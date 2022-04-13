package com.lcons.pokeapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcons.pokeapi.common.Common;
import com.lcons.pokeapi.model.Pokemon;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonDetalhe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonDetalhe extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_nome, pokemon_altura, pokemon_peso;
    RecyclerView recycle_tipo, recycle_fraqueza, recycle_next_evol, recycle_prev_evol;

    static PokemonDetalhe instance;

    public static PokemonDetalhe getInstance() {
        if (instance == null)
            instance = new PokemonDetalhe();
        return instance;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonDetalhe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonDetalhe.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonDetalhe newInstance(String param1, String param2) {
        PokemonDetalhe fragment = new PokemonDetalhe();
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
        View itemView = inflater.inflate(R.layout.fragment_pokemon_detalhe, container, false);

        Pokemon pokemon;
        if (getArguments().get("num") == null)
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));
        else
            pokemon = null;


        pokemon_img = (ImageView) itemView.findViewById(R.id.pokemon_image);
        pokemon_nome = (TextView) itemView.findViewById(R.id.nome);
        pokemon_altura = (TextView) itemView.findViewById(R.id.altura);
        pokemon_peso = (TextView) itemView.findViewById(R.id.peso);

        recycle_tipo = (RecyclerView) itemView.findViewById(R.id.recycle_type);
        recycle_tipo.setHasFixedSize(true);
        recycle_tipo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recycle_fraqueza = (RecyclerView) itemView.findViewById(R.id.recycle_weakness);
        recycle_fraqueza.setHasFixedSize(true);
        recycle_fraqueza.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recycle_prev_evol = (RecyclerView) itemView.findViewById(R.id.recycle_prev_evol);
        recycle_prev_evol.setHasFixedSize(true);
        recycle_prev_evol.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recycle_next_evol = (RecyclerView) itemView.findViewById(R.id.recycle_next_evol);
        recycle_next_evol.setHasFixedSize(true);
        recycle_next_evol.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        setDetailPokemon(pokemon);

        return itemView;
    }

    private void setDetailPokemon(Pokemon pokemon) {
        //Carregar Imagem
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);

        pokemon_nome.setText(pokemon.getName());

        pokemon_peso.setText("Peso " + pokemon.getWeight());
        pokemon_altura.setText("Altura: " + pokemon.getHeight());

    }
}
