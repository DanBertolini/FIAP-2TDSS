package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SugestaoDAO extends SQLiteOpenHelper {

    private static final int VERSAO  = 1;
    private static final String TABLE_SUGESTAO = "SUGESTAO";
    private static final  String TABLE_EVENTO = "EVENTO";
    private static final String DATABASE = "NATURA";

    public SugestaoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SugestaoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTableAluno =  "";/*"CREATE TABLE " + TABLE_EVENTO + "( "
                + "cd_evento INTEGER PRIMARY KEY, "
                + " cd_pessoa INTEGER,dt_evento DATETIME, ds_tipo TEXT, "
                + "ds_estacao TEXT, ds_periodo TEXT, ds_evento TEXT" +
                "FOREIGN KEY(cd_pessoa) REFERENCES "+ TABLE_PESSOA +" (cd_pessoa))";*/

        database.execSQL(createTableAluno);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_SUGESTAO;
        database.execSQL(drop);

        onCreate(database);
    }

}
