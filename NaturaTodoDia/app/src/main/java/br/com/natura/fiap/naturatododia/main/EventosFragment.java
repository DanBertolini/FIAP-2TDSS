package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.EventoDAO;
import br.com.natura.fiap.naturatododia.entity.Evento;

public class EventosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listEventos;
    List<Evento> eventosPendentes;
    List<Evento> eventosConcluidos;
    ArrayAdapter<Evento> eventosPendentesAdp;
    ArrayAdapter<Evento> eventosConcluidosAdp;

    TextView evtPendentes;
    TextView evtConcluidos;

    public EventosFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.eventos_layout, container, false);

        eventosPendentes = getEventos(true, v.getContext());
        eventosConcluidos = getEventos(false, v.getContext());

        listEventos = (ListView) v.findViewById(R.id.listEventos);
        eventosPendentesAdp = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_list_item_1,
                eventosPendentes);
        eventosConcluidosAdp = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_list_item_1,
                eventosConcluidos);
        listEventos.setAdapter(eventosPendentesAdp);
        listEventos.setOnItemClickListener(this);

        evtPendentes = (TextView) v.findViewById(R.id.evtPendentes);
        evtPendentes.setOnClickListener(this);
        evtConcluidos = (TextView) v.findViewById(R.id.evtConcluidos);
        evtConcluidos.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.evtPendentes){
            listEventos.setAdapter(eventosPendentesAdp);
            setSelectedTab(evtConcluidos, evtPendentes);
        }else if(v.getId() == R.id.evtConcluidos){
            listEventos.setAdapter(eventosConcluidosAdp);
            setSelectedTab(evtPendentes, evtConcluidos);
        }
    }

    private void setSelectedTab(TextView old, TextView current){
        old.setBackground(old.getContext().getDrawable(R.drawable.tab));
        old.setTextColor(Color.parseColor("#d4892d"));

        current.setBackground(current.getContext().getDrawable(R.drawable.tab_active));
        current.setTextColor(Color.parseColor("#ff9105"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Evento ev = (Evento) listEventos.getItemAtPosition(position);

        SugestoesFragment sugestsFragment = new SugestoesFragment();
        Bundle arguments = new Bundle();
        arguments.putString("evtName", ev.getNome());
        arguments.putInt("idEvento", ev.getId());
        sugestsFragment.setArguments(arguments);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.frameNavigate, sugestsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private List<Evento> getEventos(boolean pendente, Context ctx){
        EventoDAO dao = new DAO(ctx).getEventoDAO();
        if(pendente){
            return dao.buscarEventosPendentes();
        }else{
            return dao.buscarEventosConcluidos();
        }
    }
}