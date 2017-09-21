package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.natura.fiap.naturatododia.R;

public class SugestoesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listSugestoes;
    ArrayList<String> sugestoes;
    ArrayAdapter<String>sugestoesAdp;
    ImageButton btnDetalhesEvento;

    String nomeEvento;
    boolean eventoConcluido;

    public SugestoesFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sugestoes_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());

        try {
            eventoConcluido = getArguments().getBoolean("isConcluido");
            nomeEvento = getArguments().getString("evtName");
            mainActivity.getSupportActionBar().setTitle(nomeEvento);
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        btnDetalhesEvento = (ImageButton) mainActivity.findViewById(R.id.btnDetalhesEvento);
        btnDetalhesEvento.setVisibility(View.VISIBLE);
        btnDetalhesEvento.setOnClickListener(this);

        sugestoes = new ArrayList<String>();
        sugestoes.add("Sugestão 1");
        sugestoesAdp = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,
                sugestoes);
        listSugestoes = (ListView) v.findViewById(R.id.listSugestoes);
        listSugestoes.setAdapter(sugestoesAdp);
        listSugestoes.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnDetalhesEvento){
            btnDetalhesEvento.setVisibility(View.INVISIBLE);

            DetalhamentoEventoFragment detalhamentoEventoFragment = new DetalhamentoEventoFragment();
            Bundle arguments = new Bundle();
            arguments.putString("evtName", nomeEvento);
            detalhamentoEventoFragment.setArguments(arguments);

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.replace(R.id.frameNavigate, detalhamentoEventoFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        btnDetalhesEvento.setVisibility(View.INVISIBLE);
        ProdutosFragment prodsFragment = new ProdutosFragment();
        Bundle arguments = new Bundle();
        arguments.putString("sugestName", ((AppCompatTextView) view).getText().toString());
        arguments.putBoolean("isConcluido", eventoConcluido);
        prodsFragment.setArguments(arguments);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.frameNavigate, prodsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
