package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.natura.fiap.naturatododia.R;

public class EventoDetailFragment extends Fragment{

    public EventoDetailFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.evento_detail_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());
        try{
            mainActivity.getSupportActionBar().setTitle(getArguments().getString("evtName"));
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        return v;
    }
}
