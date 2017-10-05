package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.natura.fiap.naturatododia.R;

public class AboutFragment extends Fragment{
    private TextView txtSobre;
    public AboutFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_layout, container, false);
        txtSobre = (TextView) v.findViewById(R.id.txtSobre);

        String textoSobre = "Olá, sou a Nati, sua amiga virtual, e estou aqui para dar as melhores sugestões e dicas para " +
                "seus eventos, sejam eles uma balada, uma festa, um casamento, um aniversário ou uma simples saída casual." +
                "\n \n" +
                "Conte comigo para arrasar no visual";
        txtSobre.setText(textoSobre);
        return v;
    }
}
