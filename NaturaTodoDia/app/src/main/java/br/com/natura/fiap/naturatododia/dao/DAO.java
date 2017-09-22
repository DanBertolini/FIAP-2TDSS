package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {


    private static final String TABLE_SUGESTAO_PRODUTO = "SUGESTAO_PRODUTO";
    private static final String TABLE_SUGESTAO = "SUGESTAO";
    private static final String TABLE_EVENTO = "EVENTO";
    private static final String TABLE_PRODUTO = "PRODUTO";
    private static final String TABLE_DATA_ESPECIAL = "DATA_ESPECIAL";
    private static final String TABLE_PREFERENCIAS = "PREFERENCIAS";
    private static final String TABLE_PESSOA = "PESSOA";

    private static final int VERSAO  = 1;
    private static final String DATABASE = "NATURA";
    private Context context;

    public  DAO(Context context){

        super(context, DATABASE, null, VERSAO);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase database) {


        String drop = "DROP TABLE IF EXISTS " + TABLE_SUGESTAO_PRODUTO;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_SUGESTAO;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_EVENTO;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_PREFERENCIAS;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_DATA_ESPECIAL;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_PESSOA;
        database.execSQL(drop);

        PessoaDAO pessoaDAO = new PessoaDAO(this.context, DATABASE, VERSAO);
        pessoaDAO.onCreate(database);
        DataEspecialDAO dtEspecialDAO = new DataEspecialDAO(this.context, DATABASE, VERSAO);
        dtEspecialDAO.onCreate(database);
        PreferenciasDAO prefDAO = new PreferenciasDAO(this.context, DATABASE, VERSAO);
        prefDAO.onCreate(database);
        ProdutoDAO prodDAO = new ProdutoDAO(this.context, DATABASE, VERSAO);
        prodDAO.onCreate(database);
        EventoDAO eventoDAO = new EventoDAO(this.context, DATABASE, VERSAO);
        eventoDAO.onCreate(database);
        SugestaoDAO sugestDAO = new SugestaoDAO(this.context, DATABASE, VERSAO);
        sugestDAO.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoantiga, int versaonova) {



    }

    public void iniciaTabelas(){

        SQLiteDatabase database = getReadableDatabase();

        onCreate(database);
    }
}