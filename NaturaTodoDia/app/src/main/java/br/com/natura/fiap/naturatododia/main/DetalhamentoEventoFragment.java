package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.natura.fiap.naturatododia.R;

public class DetalhamentoEventoFragment extends Fragment{

    private String nomeEvento;

    public DetalhamentoEventoFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.evento_detail_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());
        try {
            nomeEvento = getArguments().getString("evtName");
            mainActivity.getSupportActionBar().setTitle(nomeEvento);
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        return v;
    }
}
