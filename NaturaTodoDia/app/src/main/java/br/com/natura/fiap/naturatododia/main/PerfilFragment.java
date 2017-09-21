package br.com.natura.fiap.naturatododia.main;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.natura.fiap.naturatododia.R;

public class PerfilFragment extends Fragment implements View.OnClickListener {
    RelativeLayout dadosLayout;
    TextView dadosTab;
    TextView dadosInfo1;
    TextView dadosInfo2;
    EditText txtDadosInfo1;
    EditText txtDadosInfo2;

    RelativeLayout preferenciasLayout;
    TextView preferenciasTab;
    TextView preferenciasInfo1;
    TextView preferenciasInfo2;
    EditText txtPreferenciasInfo1;
    EditText txtPreferenciasInfo2;

    FloatingActionButton btnEdit;
    FloatingActionButton btnSave;
    FloatingActionButton btnCancel;

    boolean isMeusDados = true;

    public PerfilFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.perfil_layout, container, false);

        dadosLayout = (RelativeLayout) v.findViewById(R.id.dadosLayout);
        dadosTab = (TextView) v.findViewById(R.id.dadosTab);
        dadosTab.setOnClickListener(this);
        dadosInfo1 = (TextView) v.findViewById(R.id.dadosInfo1);
        dadosInfo2 = (TextView) v.findViewById(R.id.dadosInfo2);
        txtDadosInfo1 = (EditText) v.findViewById(R.id.txtDadosInfo1);
        txtDadosInfo2 = (EditText) v.findViewById(R.id.txtDadosInfo2);

        preferenciasLayout = (RelativeLayout) v.findViewById(R.id.preferenciasLayout);
        preferenciasTab = (TextView) v.findViewById(R.id.preferenciasTab);
        preferenciasTab.setOnClickListener(this);
        preferenciasInfo1 = (TextView) v.findViewById(R.id.preferenciasInfo1);
        preferenciasInfo2 = (TextView) v.findViewById(R.id.preferenciasInfo2);
        txtPreferenciasInfo1 = (EditText) v.findViewById(R.id.txtPreferenciasInfo1);
        txtPreferenciasInfo2 = (EditText) v.findViewById(R.id.txtPreferenciasInfo2);

        btnEdit = (FloatingActionButton) v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);
        btnSave = (FloatingActionButton) v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (FloatingActionButton) v.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dadosTab) {
            setSelectedTab(preferenciasTab, dadosTab);
            isMeusDados = true;
            dadosLayout.setVisibility(View.VISIBLE);
            preferenciasLayout.setVisibility(View.GONE);
            defaultView();
        } else if (v.getId() == R.id.preferenciasTab) {
            setSelectedTab(dadosTab, preferenciasTab);
            isMeusDados = false;
            dadosLayout.setVisibility(View.GONE);
            preferenciasLayout.setVisibility(View.VISIBLE);
            defaultView();
        } else if (v.getId() == R.id.btnEdit) {
            setToEdit();
        } else if (v.getId() == R.id.btnSave) {
            saveData();
            defaultView();
        } else if (v.getId() == R.id.btnCancel) {
            defaultView();
        }
    }

    private void setSelectedTab(TextView old, TextView current) {
        old.setBackground(old.getContext().getDrawable(R.drawable.tab));
        old.setTextColor(Color.parseColor("#d4892d"));

        current.setBackground(current.getContext().getDrawable(R.drawable.tab_active));
        current.setTextColor(Color.parseColor("#ff9105"));
    }

    private void defaultView() {
        dadosInfo1.setVisibility(View.VISIBLE);
        dadosInfo2.setVisibility(View.VISIBLE);
        txtDadosInfo1.setVisibility(View.GONE);
        txtDadosInfo2.setVisibility(View.GONE);

        preferenciasInfo1.setVisibility(View.VISIBLE);
        preferenciasInfo2.setVisibility(View.VISIBLE);
        txtPreferenciasInfo1.setVisibility(View.GONE);
        txtPreferenciasInfo2.setVisibility(View.GONE);

        btnEdit.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    private void setToEdit(){
        if(isMeusDados){
            dadosInfo1.setVisibility(View.GONE);
            dadosInfo2.setVisibility(View.GONE);
            txtDadosInfo1.setVisibility(View.VISIBLE);
            txtDadosInfo1.setText(dadosInfo1.getText());
            txtDadosInfo2.setVisibility(View.VISIBLE);
            txtDadosInfo2.setText(dadosInfo2.getText());
        }else{
            preferenciasInfo1.setVisibility(View.GONE);
            preferenciasInfo2.setVisibility(View.GONE);
            txtPreferenciasInfo1.setVisibility(View.VISIBLE);
            txtPreferenciasInfo1.setText(preferenciasInfo1.getText());
            txtPreferenciasInfo2.setVisibility(View.VISIBLE);
            txtPreferenciasInfo2.setText(preferenciasInfo2.getText());
        }

        btnEdit.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    private void saveData(){
        dadosInfo1.setText(txtDadosInfo1.getText());
        dadosInfo2.setText(txtDadosInfo2.getText());
        preferenciasInfo1.setText(txtPreferenciasInfo1.getText());
        preferenciasInfo2.setText(txtPreferenciasInfo2.getText());
    }
}
