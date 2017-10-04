package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.ProdutoDAO;
import br.com.natura.fiap.naturatododia.entity.Produto;

public class ProdutoDetailFragment extends Fragment{

    TextView txtNome;
    TextView txtTipoProd;
    TextView txtCores;
    TextView txtTom;
    TextView txtFPS;
    TextView txtBrilho;
    TextView txtLink;
    TextView txtPreco;
    TextView txtDescricao;
    public ProdutoDetailFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.produto_detail_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());

        int idProduto = 0;
        try{
            idProduto = getArguments().getInt("idProduto");
            mainActivity.getSupportActionBar().setTitle(getArguments().getString("prodName"));
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        Produto produto = getDetalhesProduto(idProduto, v.getContext());
        txtNome = (TextView) v.findViewById(R.id.txtNome);
        txtTipoProd = (TextView) v.findViewById(R.id.txtTipoProd);
        txtCores = (TextView) v.findViewById(R.id.txtCores);
        txtTom = (TextView) v.findViewById(R.id.txtTom);
        txtFPS = (TextView) v.findViewById(R.id.txtFPS);
        txtBrilho = (TextView) v.findViewById(R.id.txtBrilho);
        txtLink = (TextView) v.findViewById(R.id.txtLink);
        txtPreco = (TextView) v.findViewById(R.id.txtPreco);
        txtDescricao = (TextView) v.findViewById(R.id.txtDescricao);

        if(produto != null){
            txtNome.setText(produto.getNome());
            txtTipoProd.setText(produto.getTipo());
            txtCores.setText(produto.getCor());
            txtTom.setText(produto.getTom());
            txtFPS.setText(produto.getFps());
            txtBrilho.setText(produto.isBrilho() ? "Sim": "Não");
            txtLink.setText(produto.getLink());
            txtPreco.setText("R$ " + produto.getPreco());
            txtDescricao.setText(produto.getDescricao());
        }

        return v;
    }

    public Produto getDetalhesProduto(int id, Context ctx){
        ProdutoDAO dao = new DAO(ctx).getProdutoDAO();
        return dao.buscarProduto(id);
    }
}
