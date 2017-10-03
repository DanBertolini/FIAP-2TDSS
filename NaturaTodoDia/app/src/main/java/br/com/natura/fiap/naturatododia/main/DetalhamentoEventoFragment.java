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
import br.com.natura.fiap.naturatododia.dao.EventoDAO;

public class DetalhamentoEventoFragment extends Fragment{

    private String nomeEvento;
    TextView txtDtEvento;
    TextView txtTipoEvento;
    TextView txtEstacao;
    TextView txtPeriodo;
    TextView txtDescricao;

    public DetalhamentoEventoFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.evento_detail_layout, container, false);
        MainActivity mainActivity = ((MainActivity)getActivity());
        int idEvento = 0;
        try {
            nomeEvento = getArguments().getString("evtName");
            idEvento = getArguments().getInt("idEvento");
            mainActivity.getSupportActionBar().setTitle(nomeEvento);
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }

        String evento = getDetalhesEvento(idEvento, v.getContext());
        txtDtEvento = (TextView) v.findViewById(R.id.txtDtEvento);
        txtTipoEvento = (TextView) v.findViewById(R.id.txtTipoEvento);
        txtEstacao = (TextView) v.findViewById(R.id.txtEstacao);
        txtPeriodo = (TextView) v.findViewById(R.id.txtPeriodo);
        txtDescricao = (TextView) v.findViewById(R.id.txtDescricao);



        return v;
    }

    public String getDetalhesEvento(int id, Context ctx){
        EventoDAO dao = new DAO(ctx).getEventoDAO();
        return dao.buscarEvento(id);
    }
}
