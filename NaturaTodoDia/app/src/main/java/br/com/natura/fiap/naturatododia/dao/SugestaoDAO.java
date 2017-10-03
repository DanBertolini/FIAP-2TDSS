package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SugestaoDAO extends SQLiteOpenHelper {

    private static final String TABLE_SUGESTAO = "SUGESTAO";
    private static final String ID = "cd_sugestao";
    private static final String CD_EVENTO = "cd_evento";
    private static final String TABLE_EVENTO = "EVENTO";
    private static final String NM_SUGESTAO = "nm_sugestao";
    private static final String IS_SALVO = "is_salvo";
    private static final String TABLE_SUGESTAO_PRODUTO = "SUGESTAO_PRODUTO";
    private static final String CD_PRODUTO = "cd_produto";
    private static final String TABLE_PRODUTO = "PRODUTO";


    public SugestaoDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =  "CREATE TABLE " + TABLE_SUGESTAO + "( "
                + ID + " INTEGER PRIMARY KEY,   "
                + CD_EVENTO + " INTEGER, "
                + NM_SUGESTAO + " TEXT, "
                + IS_SALVO + " INTEGER DEFAULT 0, "
                + "FOREIGN KEY(" + CD_EVENTO + ") REFERENCES "+ TABLE_EVENTO + "(" + CD_EVENTO + ") )";

        database.execSQL(createTable);

        String createTable2 =  "CREATE TABLE " + TABLE_SUGESTAO_PRODUTO + "( "
                + ID + " INTEGER,   "
                + CD_PRODUTO + " INTEGER, "
                + "PRIMARY KEY ("+ ID +"," + CD_PRODUTO + "), "
                + "FOREIGN KEY(" + ID + ") REFERENCES " + TABLE_SUGESTAO + "(" + ID + "), "
                + "FOREIGN KEY(" + CD_PRODUTO + ") REFERENCES " + TABLE_PRODUTO + "(" + CD_PRODUTO + ") )";

        database.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_SUGESTAO_PRODUTO;
        database.execSQL(drop);
        String drop2 = "DROP TABLE IF EXISTS " + TABLE_SUGESTAO;
        database.execSQL(drop2);

        onCreate(database);
    }

    public List<String> buscarSugestoesPorEvento(int idEvento){
        return new ArrayList<>();
    }

    public List<String> buscarSugestoesSalvas(){
        return new ArrayList<>();
    }

}
