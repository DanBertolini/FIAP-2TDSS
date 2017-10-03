package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PreferenciasDAO extends SQLiteOpenHelper {
    private static final  String TABLE_PREFERENCIAS = "PREFERENCIAS";
    private static final  String ID = "cd_preferencias_pessoa";
    private static final  String DS_TOM_FAVORITO = "ds_tom_favorito";
    private static final  String DS_FRAGANCIA_FAVORITO = "ds_fragancia_favorita";
    private static final String CD_PESSOA = "cd_pessoa";
    private static final String TABLE_PESSOA = "PESSOA";

    public PreferenciasDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =  "CREATE TABLE " + TABLE_PREFERENCIAS + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CD_PESSOA + " INTEGER, "
                + DS_TOM_FAVORITO + " TEXT, "
                + DS_FRAGANCIA_FAVORITO + " TEXT, "
                + "FOREIGN KEY(" + CD_PESSOA + ") REFERENCES "+ TABLE_PESSOA + "(" + CD_PESSOA + "))";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_PREFERENCIAS;
        database.execSQL(drop);

        onCreate(database);
    }

    public String getPreferenciasPessoa(){
        return "";
    }

    public void atualizaPreferencias(String preferencias){

    }

}

