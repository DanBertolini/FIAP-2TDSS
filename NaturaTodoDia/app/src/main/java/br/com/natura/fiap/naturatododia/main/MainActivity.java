package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.dao.DAO;
import br.com.natura.fiap.naturatododia.dao.ProdutoDAO;
import br.com.natura.fiap.naturatododia.entity.Produto;
import br.com.natura.fiap.naturatododia.login.LoginActivity;
import br.com.natura.fiap.naturatododia.utils.NaturaDialog;
import br.com.natura.fiap.naturatododia.utils.SaveSharedPreference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;

    private ChatFragment chatFragment;
    PerfilFragment perfilFragment;
    private EventosFragment eventosFragment;
    private SalvosFragment salvosFragment;
    private AboutFragment aboutFragment;

    private ImageButton btnDetalhesEvento;
    private ImageButton btnSave;
    private ImageButton btnUnsave;

    private boolean closeChat;

    private interface DialogCallbackInterface {

        void onConfirm();
        void onCancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

                /** Called when a drawer has settled in a completely closed state. */
                public void onDrawerOpened(View view) {
                    super.onDrawerOpened(view);

                    //if there a focus on a textbox hide the keyboard
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

            public void onDrawerSlide(View view, float slideOffset) {
                super.onDrawerSlide(view, slideOffset);

                //if there a focus on a textbox hide the keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView nomeUser = (TextView)headerView.findViewById(R.id.nome_usuario);
        //nomeUser.setText("Daniel");

        navigationView.setNavigationItemSelectedListener(this);

        chatFragment = new ChatFragment();
        perfilFragment = new PerfilFragment();
        eventosFragment = new EventosFragment();
        salvosFragment = new SalvosFragment();
        aboutFragment = new AboutFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameNavigate, chatFragment);
        ft.commit();

        btnDetalhesEvento = (ImageButton) findViewById(R.id.btnDetalhesEvento);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnUnsave = (ImageButton) findViewById(R.id.btnUnsave);
        DAO dao = new DAO(getApplicationContext());

        //atualizaProdutos();
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(closeChat || closeChatFragment(getFragmentManager().findFragmentById(R.id.frameNavigate), new DialogCallbackInterface(){
                @Override
                public void onConfirm() {
                    closeChat = true;
                    onBackPressed();
                }

                @Override
                public void onCancel() {

                }

            })) {
                closeChat = false;
                if (count == 0) {
                    super.onBackPressed();
                } else {
                    onFragmentChange();
                    getFragmentManager().popBackStack();
                    invalidateOptionsMenu();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        //getMenuInflater().inflate(R.menu.evento_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(closeChat || closeChatFragment(fm.findFragmentById(R.id.frameNavigate), new DialogCallbackInterface(){
            @Override
            public void onConfirm() {
                closeChat = true;
                onNavigationItemSelected(item);
            }
            @Override
            public void onCancel() {
                navigationView.getMenu().getItem(1).setChecked(true);
            }
        })) {
            closeChat = false;
            try {
                if (id == R.id.perfil) {
                    ft.replace(R.id.frameNavigate, perfilFragment);
                } else if (id == R.id.conversation) {
                    ft.replace(R.id.frameNavigate, chatFragment);
                } else if (id == R.id.eventos) {
                    ft.replace(R.id.frameNavigate, eventosFragment);
                } else if (id == R.id.salvos) {
                    ft.replace(R.id.frameNavigate, salvosFragment);
                } else if (id == R.id.about) {
                    ft.replace(R.id.frameNavigate, aboutFragment);
                } else if (id == R.id.logout) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    SaveSharedPreference.clearUserName(this);
                    startActivity(intent);
                }

                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                onFragmentChange();

                ft.commit();
            } catch (Exception e) {
                Log.e("WARNING", "Tentativa de abrir a mesma página já aberta");
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onFragmentChange(){
        try{
            btnSave.setVisibility(View.GONE);
            btnUnsave.setVisibility(View.GONE);
            btnDetalhesEvento.setVisibility(View.GONE);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_navigation));
        }catch (Exception e){
            Log.e("ERRO", "Erro ao carregar titulo");
        }
    }

    private boolean closeChatFragment(Fragment fragment, DialogCallbackInterface callback){
        if(!(fragment instanceof ChatFragment)){
            return true;
        }
        ChatFragment chatFragment = (ChatFragment) fragment;
        if(chatFragment.isConversationFinished()){
            return true;
        }
        closeChat = false;
        NaturaDialog dialog = new NaturaDialog();
        dialog.Confirm(this, "Nati", "Você está no meio de uma conversa com a Nati. Deseja realmente sair da e desistir do cadastro desse evento?",
                "Não", "Sim", ok(callback), cancel(callback));

        return false;
    }

    private Runnable ok(final DialogCallbackInterface callback){
        return new Runnable() {
            public void run() {
                callback.onConfirm();
            }
        };
    }

    private Runnable cancel(final DialogCallbackInterface callback){
        return new Runnable() {
            public void run() {
                callback.onCancel();
            }
        };
    }

    private void atualizaProdutos() {

        final String URL = "https://raw.githubusercontent.com/DanBertolini/Fiap-JsonService/master/db.json";

        RequestQueue reqQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyJSONArrayResponse resp = new VolleyJSONArrayResponse();
        VolleyErroRequest fail = new VolleyErroRequest();

        JsonArrayRequest jsonReq = new JsonArrayRequest(URL, resp, fail);
        reqQueue.add(jsonReq);

    }

    private class VolleyErroRequest implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("Error",error.toString());
        }
    }

    private class VolleyJSONArrayResponse implements Response.Listener<JSONArray> {

        @Override
        public void onResponse(JSONArray response) {

                ProdutoDAO dao = new DAO(getApplicationContext()).getProdutoDAO();
                int idProduto = 0;
                for	(int	i	=	0;	i	<	response.length();	i++)	{
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Produto prd = new Produto();
                        idProduto = obj.getInt("id");
                        prd.setId(idProduto);
                        prd.setNome(obj.getString("nome").replace("'", " "));
                        prd.setDescricao(obj.getString("descricao").replace("'", " "));
                        prd.setTipo(obj.getString("tipo"));
                        prd.setGenero(obj.getString("genero"));
                        prd.setCor(obj.getString("cor"));
                        prd.setTom(obj.getString("tonalidade"));
                        prd.setImg(obj.getString("imagem"));
                        prd.setLink(obj.getString("link"));
                        prd.setFps(obj.getInt("fps"));
                        prd.setBrilho(obj.getBoolean("brilho"));
                        prd.setPreco(obj.getDouble("preco"));
                        prd.setAtivo(true);

                        dao.atualizarProd(prd);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("ERRO", "Erro no produto " + idProduto);
                    }
                }

        }
    }
}
