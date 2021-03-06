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

    private static final int VERSAO  = 6;
    private static final String DATABASE = "NATURA";
    private Context context;

    private SQLiteDatabase db;

    public  DAO(Context context){

        super(context, DATABASE, null, VERSAO);
        this.context = context;
        db = getReadableDatabase();
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
        db = database;
        createMock();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoantiga, int versaonova) {
        onCreate(database);
    }

    public EventoDAO getEventoDAO(){
        return new EventoDAO(this.context, DATABASE, VERSAO);
    }

    public PessoaDAO getPessoaDAO(){
        return new PessoaDAO(this.context, DATABASE, VERSAO);
    }

    public PreferenciasDAO getPreferenciaDAO(){
        return new PreferenciasDAO(this.context, DATABASE, VERSAO);
    }

    public ProdutoDAO getProdutoDAO(){
        return new ProdutoDAO(this.context, DATABASE, VERSAO);
    }

    public SugestaoDAO getSugestaoDAO(){
        return new SugestaoDAO(this.context, DATABASE, VERSAO);
    }

    private void createMock(){

        db.beginTransaction();
        getProdutoDAO().createMock(db);
        getPessoaDAO().createMock(db);
        getPreferenciaDAO().createMock(db);
        getEventoDAO().createMock(db);
        getSugestaoDAO().createMock(db);
        getSugestaoDAO().createMock2(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}