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

        String textoSobre = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce quis scelerisque lorem. Pellentesque commodo vitae nulla at pretium. Quisque porta ac arcu et sollicitudin. Curabitur efficitur cursus ex egestas luctus. Donec condimentum scelerisque quam, quis porttitor velit facilisis non. In hac habitasse platea dictumst. Phasellus ac venenatis nisl. Nunc interdum turpis vitae metus consequat, vitae auctor felis laoreet. Nulla sed felis a libero convallis tincidunt mollis volutpat augue.\n" +
                "\n" +
                "Nulla luctus tellus non neque volutpat posuere. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam quam nibh, tincidunt eu accumsan sit amet, dapibus sed sem. Maecenas eu mi ac tortor congue sodales sed nec felis. Mauris malesuada est eget lectus facilisis tempor. Ut facilisis massa eget eros vehicula convallis sit amet quis lacus. Praesent interdum condimentum efficitur. Integer cursus congue lorem.\n" +
                "\n" +
                "Fusce non ipsum lectus. Pellentesque enim mi, consequat ut mollis vitae, convallis eu neque. Quisque ligula libero, hendrerit ac massa a, porttitor condimentum nunc. Sed tempus quam ut lacus tempus, sed luctus massa laoreet. Quisque faucibus lacus quis metus euismod, nec auctor lorem fermentum. Nullam ac risus nec lorem mattis porta. Sed arcu risus, sagittis non sodales vel, posuere eget sem. Quisque molestie eros ut augue pulvinar interdum. Aenean sagittis lectus non ipsum vestibulum bibendum. Curabitur semper lacinia convallis.";
        txtSobre.setText(textoSobre);
        return v;
    }
}
