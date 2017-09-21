package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

import br.com.natura.fiap.naturatododia.R;

public class EventosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listEventos;
    ArrayList<String> eventosPendentes;
    ArrayList<String> eventosConcluidos;
    ArrayAdapter<String> eventosPendentesAdp;
    ArrayAdapter<String> eventosConcluidosAdp;

    TextView evtPendentes;
    TextView evtConcluidos;

    boolean eventoConcluido = false;
    public EventosFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.eventos_layout, container, false);

        eventosPendentes = new ArrayList<String>();
        eventosPendentes.add("Evento Pendente 1");
        eventosConcluidos = new ArrayList<String>();
        eventosConcluidos.add("Evento Concluído 1");

        listEventos = (ListView) v.findViewById(R.id.listEventos);
        eventosPendentesAdp = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,
                eventosPendentes);
        eventosConcluidosAdp = new ArrayAdapter<String>(v.getContext(),
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
            eventoConcluido = false;
        }else if(v.getId() == R.id.evtConcluidos){
            listEventos.setAdapter(eventosConcluidosAdp);
            setSelectedTab(evtPendentes, evtConcluidos);
            eventoConcluido = true;
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

        SugestoesFragment sugestsFragment = new SugestoesFragment();
        Bundle arguments = new Bundle();
        arguments.putString("evtName", ((AppCompatTextView) view).getText().toString());
        arguments.putBoolean("isConcluido", eventoConcluido);
        sugestsFragment.setArguments(arguments);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.frameNavigate, sugestsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}