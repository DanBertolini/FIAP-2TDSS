package br.com.natura.fiap.naturatododia.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.entity.Produto;

public class AvaliacaoProdutoArrayAdapter extends ArrayAdapter<Produto> implements View.OnClickListener{

    private TextView nomeProduto;
    private ImageButton btnLike;
    private ImageButton btnUnlike;
    private List<Produto> prodList = new ArrayList<Produto>();
    private Context context;

    @Override
    public void add(Produto object) {
        prodList.add(object);
        super.add(object);
    }

    public AvaliacaoProdutoArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.prodList.size();
    }

    public Produto getItem(int index) {
        return this.prodList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Produto prdObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.avaliacao_produto, parent, false);

        nomeProduto = (TextView) row.findViewById(R.id.txtNomePrd);
        nomeProduto.setText(prdObj.getNome());

        btnLike = (ImageButton) row.findViewById(R.id.btnLike);
        btnLike.setOnClickListener(this);
        btnLike.setTag(position);
        btnUnlike = (ImageButton) row.findViewById(R.id.btnUnlike);
        btnUnlike.setOnClickListener(this);
        btnUnlike.setTag(position);

        return row;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Produto prd = prodList.get(position);

        ImageButton like = (ImageButton)((View) v.getParent()).findViewById(R.id.btnLike);
        ImageButton unlike = (ImageButton)((View) v.getParent()).findViewById(R.id.btnUnlike);

        if(v.getId() == R.id.btnLike){
            like.setBackgroundColor(Color.parseColor("#00FF00"));
            unlike.setBackground(null);
        }else if(v.getId() == R.id.btnUnlike){
            unlike.setBackgroundColor(Color.parseColor("#FF0000"));
            like.setBackground(null);
        }


    }
}
