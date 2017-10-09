package br.com.natura.fiap.naturatododia.main;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.PessoaDAO;
import br.com.natura.fiap.naturatododia.dao.PreferenciasDAO;
import br.com.natura.fiap.naturatododia.entity.Evento;
import br.com.natura.fiap.naturatododia.entity.Pessoa;
import br.com.natura.fiap.naturatododia.entity.Preferencia;

public class PerfilFragment extends Fragment implements View.OnClickListener {
    RelativeLayout dadosLayout;
    TextView dadosTab;
    TextView txtNome;
    EditText edTxtNome;
    TextView txtDataNascimento;
    EditText edTxtDataNascimento;
    TextView txtSexo;
    EditText edTxtSexo;
    TextView txtCorPele;
    EditText edTxtCorPele;
    TextView txtTipoPele;
    EditText edTxtTipoPele;
    TextView txtTipoCabelo;
    EditText edTxtTipoCabelo;

    RelativeLayout preferenciasLayout;
    TextView preferenciasTab;
    TextView txtTomFavorito;
    EditText edTxtTomFavorito;
    TextView txtAromaFavorita;
    EditText edTxtAromaFavorita;

    FloatingActionButton btnEdit;
    FloatingActionButton btnSave;
    FloatingActionButton btnCancel;

    Context ctx;

    boolean isMeusDados = true;

    Pessoa pessoa = new Pessoa();

    SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );

    public PerfilFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.perfil_layout, container, false);
        ctx = v.getContext();

        pessoa = getDadosPessoa();
        pessoa.setPreferencia(getPreferenciasPessoa());

        dadosLayout = (RelativeLayout) v.findViewById(R.id.dadosLayout);
        dadosTab = (TextView) v.findViewById(R.id.dadosTab);
        dadosTab.setOnClickListener(this);
        txtNome = (TextView) v.findViewById(R.id.txtNome);
        edTxtNome = (EditText) v.findViewById(R.id.edTxtNome);
        txtDataNascimento = (TextView) v.findViewById(R.id.txtDataNascimento);
        edTxtDataNascimento = (EditText) v.findViewById(R.id.edTxtDataNascimento);
        txtSexo = (TextView) v.findViewById(R.id.txtSexo);
        edTxtSexo = (EditText) v.findViewById(R.id.edTxtSexo);
        txtCorPele = (TextView) v.findViewById(R.id.txtCorPele);
        edTxtCorPele = (EditText) v.findViewById(R.id.edTxtCorPele);
        txtTipoPele = (TextView) v.findViewById(R.id.txtTipoPele);
        edTxtTipoPele = (EditText) v.findViewById(R.id.edTxtTipoPele);
        txtTipoCabelo = (TextView) v.findViewById(R.id.txtTipoCabelo);
        edTxtTipoCabelo = (EditText) v.findViewById(R.id.edTxtTipoCabelo);

        preferenciasLayout = (RelativeLayout) v.findViewById(R.id.preferenciasLayout);
        preferenciasTab = (TextView) v.findViewById(R.id.preferenciasTab);
        preferenciasTab.setOnClickListener(this);
        txtTomFavorito = (TextView) v.findViewById(R.id.txtTomFavorito);
        edTxtTomFavorito = (EditText) v.findViewById(R.id.edTxtTomFavorito);
        txtAromaFavorita = (TextView) v.findViewById(R.id.txtAromaFavorita);
        edTxtAromaFavorita = (EditText) v.findViewById(R.id.edTxtAromaFavorita);

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
        txtNome.setVisibility(View.VISIBLE);
        edTxtNome.setVisibility(View.GONE);
        txtDataNascimento.setVisibility(View.VISIBLE);
        edTxtDataNascimento.setVisibility(View.GONE);
        txtSexo.setVisibility(View.VISIBLE);
        edTxtSexo.setVisibility(View.GONE);
        txtCorPele.setVisibility(View.VISIBLE);
        edTxtCorPele.setVisibility(View.GONE);
        txtTipoPele.setVisibility(View.VISIBLE);
        edTxtTipoPele.setVisibility(View.GONE);
        txtTipoCabelo.setVisibility(View.VISIBLE);
        edTxtTipoCabelo.setVisibility(View.GONE);

        txtTomFavorito.setVisibility(View.VISIBLE);
        edTxtTomFavorito.setVisibility(View.GONE);
        txtAromaFavorita.setVisibility(View.VISIBLE);
        edTxtAromaFavorita.setVisibility(View.GONE);

        btnEdit.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    private void setToEdit(){
        if(isMeusDados){
            edTxtNome.setVisibility(View.VISIBLE);
            edTxtNome.setText(txtNome.getText());
            txtNome.setVisibility(View.GONE);

            edTxtDataNascimento.setVisibility(View.VISIBLE);
            edTxtDataNascimento.setText(txtDataNascimento.getText());
            txtDataNascimento.setVisibility(View.GONE);

            edTxtSexo.setVisibility(View.VISIBLE);
            edTxtSexo.setText(txtSexo.getText());
            txtSexo.setVisibility(View.GONE);

            edTxtCorPele.setVisibility(View.VISIBLE);
            edTxtCorPele.setText(txtCorPele.getText());
            txtCorPele.setVisibility(View.GONE);

            edTxtTipoPele.setVisibility(View.VISIBLE);
            edTxtTipoPele.setText(txtTipoPele.getText());
            txtTipoPele.setVisibility(View.GONE);

            edTxtTipoCabelo.setVisibility(View.VISIBLE);
            edTxtTipoCabelo.setText(txtTipoCabelo.getText());
            txtTipoCabelo.setVisibility(View.GONE);
        }else{
            edTxtTomFavorito.setVisibility(View.VISIBLE);
            edTxtTomFavorito.setText(txtTomFavorito.getText());
            txtTomFavorito.setVisibility(View.GONE);

            edTxtAromaFavorita.setVisibility(View.VISIBLE);
            edTxtAromaFavorita.setText(txtAromaFavorita.getText());
            txtAromaFavorita.setVisibility(View.GONE);
        }

        btnEdit.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    private void saveData(){
        if(isMeusDados) {
            txtNome.setText(edTxtNome.getText());
            txtDataNascimento.setText(edTxtDataNascimento.getText());
            txtSexo.setText(edTxtSexo.getText());
            txtCorPele.setText(edTxtCorPele.getText());
            txtTipoPele.setText(edTxtTipoPele.getText());
            txtTipoCabelo.setText(edTxtTipoCabelo.getText());

            pessoa.setNome(edTxtNome.getText().toString());
            try {
                pessoa.setDtNasci(sdf.parse(edTxtDataNascimento.getText().toString()));
            } catch (ParseException e) {
                Toast.makeText(ctx, "Formato de Data Inválido", Toast.LENGTH_SHORT).show();
            }
            pessoa.setSexo(edTxtSexo.getText().toString());
            pessoa.setCorPele(edTxtCorPele.getText().toString());
            pessoa.setTipoPele(edTxtTipoPele.getText().toString());
            pessoa.setTipoCabelo(edTxtTipoCabelo.getText().toString());

            PessoaDAO dao = new DAO(ctx).getPessoaDAO();
            dao.atualizaCadastro(pessoa);
        }else {
            txtTomFavorito.setText(edTxtTomFavorito.getText());
            txtAromaFavorita.setText(edTxtAromaFavorita.getText());

            pessoa.getPreferencia().setTomFavorito(edTxtTomFavorito.getText().toString());
            pessoa.getPreferencia().setAromaFavorita(edTxtAromaFavorita.getText().toString());
            PreferenciasDAO dao2 = new DAO(ctx).getPreferenciaDAO();
            dao2.atualizaPreferencias(pessoa.getPreferencia());
        }
    }

    private Pessoa getDadosPessoa(){
        PessoaDAO dao = new DAO(ctx).getPessoaDAO();
        return dao.getDadosPessoa();
    }

    private Preferencia getPreferenciasPessoa(){
        PreferenciasDAO dao = new DAO(ctx).getPreferenciaDAO();
        return dao.getPreferenciasPessoa();
    }
}
