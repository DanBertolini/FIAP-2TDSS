package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.natura.fiap.naturatododia.R;

public class SalvosFragment extends Fragment{

    public SalvosFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favoritos_layout, container, false);


        return v;
    }
}
