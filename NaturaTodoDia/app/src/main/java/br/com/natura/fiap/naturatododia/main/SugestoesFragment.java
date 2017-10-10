package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
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
import java.util.List;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.SugestaoDAO;
import br.com.natura.fiap.naturatododia.entity.Sugestao;

public class SugestoesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listSugestoes;
    List<Sugestao> sugestoes;
    ArrayAdapter<Sugestao>sugestoesAdp;
    ImageButton btnDetalhesEvento;

    String nomeEvento;
    int idEvento;

    public SugestoesFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sugestoes_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());
        idEvento = 0;
        try {
            idEvento = getArguments().getInt("idEvento");
            nomeEvento = getArguments().getString("evtName");
            mainActivity.getSupportActionBar().setTitle(nomeEvento);
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        btnDetalhesEvento = (ImageButton) mainActivity.findViewById(R.id.btnDetalhesEvento);
        btnDetalhesEvento.setVisibility(View.VISIBLE);
        btnDetalhesEvento.setOnClickListener(this);

        sugestoes = getSugestoes(idEvento, v.getContext());
        sugestoesAdp = new ArrayAdapter<>(v.getContext(),
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
            arguments.putInt("idEvento", idEvento);

            detalhamentoEventoFragment.setArguments(arguments);

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.replace(R.id.frameNavigate, detalhamentoEventoFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sugestao sugest = (Sugestao) listSugestoes.getItemAtPosition(position);
        btnDetalhesEvento.setVisibility(View.INVISIBLE);
        ProdutosFragment prodsFragment = new ProdutosFragment();
        Bundle arguments = new Bundle();

        arguments.putBoolean("isSalvo", sugest.isSalvo());
        arguments.putInt("idSugestao", sugest.getId());
        arguments.putString("sugestName", sugest.getNome());
        prodsFragment.setArguments(arguments);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.frameNavigate, prodsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private List<Sugestao> getSugestoes(int idEvento, Context ctx){
        SugestaoDAO dao = new DAO(ctx).getSugestaoDAO();
        return dao.buscarSugestoesPorEvento(idEvento);
    }
}
