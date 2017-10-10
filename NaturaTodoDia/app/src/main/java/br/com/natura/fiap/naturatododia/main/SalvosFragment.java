package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.SugestaoDAO;
import br.com.natura.fiap.naturatododia.entity.Sugestao;

public class SalvosFragment extends Fragment implements AdapterView.OnItemClickListener{
    ListView listFavoritos;
    List<Sugestao> sugestoes;
    ArrayAdapter<Sugestao> sugestoesAdp;

    public SalvosFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favoritos_layout, container, false);

        sugestoes = getSugestoes(v.getContext());
        sugestoesAdp = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_list_item_1,
                sugestoes);
        listFavoritos = (ListView) v.findViewById(R.id.listFavoritos);
        listFavoritos.setAdapter(sugestoesAdp);
        listFavoritos.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sugestao sugest = (Sugestao) parent.getSelectedItem();
        ProdutosFragment prodsFragment = new ProdutosFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("idSugestao", sugest.getId());
        arguments.putString("sugestName", sugest.getNome());
        prodsFragment.setArguments(arguments);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.frameNavigate, prodsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private List<Sugestao> getSugestoes(Context ctx){
        SugestaoDAO dao = new DAO(ctx).getSugestaoDAO();
        return dao.buscarSugestoesSalvas();
    }
}
