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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.utils.InputResults;
import br.com.natura.fiap.naturatododia.utils.NaturaDialog;

public class ProdutosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listProdutos;
    ArrayList<String> produtos;
    ArrayAdapter<String> produtosAdp;

    ImageButton btnSave;
    ImageButton btnUnsave;

    String nomeSugestao;
    private InputResults inputNomeSugestao;

    public ProdutosFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.produtos_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());

        try{
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

        produtos = new ArrayList<String>();
        produtos.add("Produto 1");
        produtos.add("Produto 2");
        produtos.add("Produto 3");
        produtos.add("Produto 4");
        produtos.add("Produto 5");
        produtos.add("Produto 6");
        produtos.add("Produto 7");
        produtos.add("Produto 8");
        produtos.add("Produto 9");
        produtos.add("Produto 10");
        produtos.add("Produto 11");
        produtos.add("Produto 12");
        produtosAdp = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,
                produtos);
        listProdutos = (ListView) v.findViewById(R.id.listProdutos);
        listProdutos.setAdapter(produtosAdp);
        listProdutos.setOnItemClickListener(this);

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
            Toast.makeText(mainActivity, "Sugestão não salva", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProdutoDetailFragment prodFragment = new ProdutoDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString("prodName", ((AppCompatTextView) view).getText().toString());
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
}
