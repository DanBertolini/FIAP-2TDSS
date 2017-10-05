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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.ProdutoDAO;
import br.com.natura.fiap.naturatododia.dao.SugestaoDAO;
import br.com.natura.fiap.naturatododia.entity.Produto;
import br.com.natura.fiap.naturatododia.utils.InputResults;
import br.com.natura.fiap.naturatododia.utils.NaturaDialog;

public class ProdutosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listProdutos;
    List<Produto> produtos;
    ArrayAdapter<Produto> produtosAdp;

    ImageButton btnSave;
    ImageButton btnUnsave;

    private int idSugestao;
    String nomeSugestao;
    private InputResults inputNomeSugestao;

    private Context _ctx;

    public ProdutosFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.produtos_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());
        idSugestao = 0;
        try{
            idSugestao = getArguments().getInt("idSugestao");
            nomeSugestao = getArguments().getString("sugestName");
            mainActivity.getSupportActionBar().setTitle(nomeSugestao);
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        btnSave = (ImageButton) mainActivity.findViewById(R.id.btnSave);
        btnSave.setVisibility(View.VISIBLE);
        btnSave.setOnClickListener(this);
        btnUnsave = (ImageButton) mainActivity.findViewById(R.id.btnUnsave);
        btnUnsave.setOnClickListener(this);

        produtos = listarProdutosSugestao(idSugestao, v.getContext());

        produtosAdp = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_list_item_1,
                produtos);
        listProdutos = (ListView) v.findViewById(R.id.listProdutos);
        listProdutos.setAdapter(produtosAdp);
        listProdutos.setOnItemClickListener(this);

        _ctx = v.getContext();

        return v;
    }

    @Override
    public void onClick(View v) {
        MainActivity mainActivity = ((MainActivity)getActivity());

        if(v.getId() == R.id.btnSave){
            inputNomeSugestao = new InputResults(nomeSugestao);
            btnSave.setVisibility(View.GONE);
            btnUnsave.setVisibility(View.VISIBLE);
            NaturaDialog dialog = new NaturaDialog();
            dialog.InputDialog(mainActivity, "Nati", "Deseja salvar a sugestão com que nome?",
                    "Cancelar", "Salvar", inputNomeSugestao, ok(), cancel());
        } else if(v.getId() == R.id.btnUnsave){
            btnUnsave.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);
            unsaveSugestao(idSugestao, _ctx);
            Toast.makeText(mainActivity, "Sugestão não salva", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Produto prd = (Produto) parent.getSelectedItem();
        ProdutoDetailFragment prodFragment = new ProdutoDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString("prodName", prd.getNome());
        arguments.putInt("idProduto", prd.getId());
        prodFragment.setArguments(arguments);

        FragmentTransaction ft = getFragmentManager().beginTransaction();


        btnUnsave.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        ft.replace(R.id.frameNavigate, prodFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private Runnable ok(){
        return new Runnable() {
            public void run() {
                salvarSugestao(idSugestao, nomeSugestao, _ctx);
                nomeSugestao = inputNomeSugestao.getValue().toString();
                MainActivity mainActivity = ((MainActivity)getActivity());
                Toast.makeText(mainActivity, "Sugestão salva como '" + nomeSugestao +"'", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private Runnable cancel(){
        return new Runnable() {
            public void run() {
                btnUnsave.setVisibility(View.GONE);
                btnSave.setVisibility(View.VISIBLE);
            }
        };
    }

    private List<Produto> listarProdutosSugestao(int idSugestao, Context ctx){
        SugestaoDAO dao = new DAO(ctx).getSugestaoDAO();
        return dao.buscarProdutosPorSugestao(idSugestao);
    }

    private void unsaveSugestao(int idSugestao, Context ctx){
        SugestaoDAO dao = new DAO(ctx).getSugestaoDAO();
        dao.salvarSugestao(idSugestao, nomeSugestao, false);
    }

    private  void salvarSugestao(int idSugestao, String nomeSugestao, Context ctx){
        SugestaoDAO dao = new DAO(ctx).getSugestaoDAO();
        dao.salvarSugestao(idSugestao, nomeSugestao, true);
    }
}
