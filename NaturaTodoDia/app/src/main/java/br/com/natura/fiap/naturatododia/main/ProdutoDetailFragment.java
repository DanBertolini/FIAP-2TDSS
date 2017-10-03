package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.natura.fiap.naturatododia.R;

public class ProdutoDetailFragment extends Fragment{

    TextView txtNome;
    TextView txtTipoProd;
    TextView txtCores;
    TextView txtTom;
    TextView txtFPS;
    TextView txtBrilho;
    TextView txtLink;

    public ProdutoDetailFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.produto_detail_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());

        try{
            mainActivity.getSupportActionBar().setTitle(getArguments().getString("prodName"));
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        return v;
    }
}
