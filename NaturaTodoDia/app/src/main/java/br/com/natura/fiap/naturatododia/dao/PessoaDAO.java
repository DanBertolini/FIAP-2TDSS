package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PessoaDAO extends SQLiteOpenHelper {
    private static final  String TABLE_PESSOA = "PESSOA";
    private static final  String ID = "cd_pessoa";
    private static final  String NM_PESSOA = "nm_pessoa";
    private static final  String DT_NASCI = "dt_nasci";
    private static final  String DS_SEXO = "ds_sexo";
    private static final  String DS_COR_PELE = "ds_cor_pele";
    private static final  String DS_TIPO_PELE = "ds_tipo_pele";
    private static final  String DS_TIPO_CABELO = "ds_tipo_cabelo";

    public PessoaDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =  "CREATE TABLE " + TABLE_PESSOA + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NM_PESSOA + " TEXT, "
                + DT_NASCI + " DATETIME, "
                + DS_SEXO + " TEXT, "
                + DS_COR_PELE + " TEXT, "
                + DS_TIPO_PELE + " TEXT, "
                + DS_TIPO_CABELO + " TEXT)";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_PESSOA;
        database.execSQL(drop);

        onCreate(database);
    }

}
