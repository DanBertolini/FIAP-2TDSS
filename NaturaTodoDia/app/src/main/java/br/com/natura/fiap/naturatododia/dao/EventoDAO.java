package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventoDAO extends SQLiteOpenHelper {

    private static final String TABLE_EVENTO = "EVENTO";
    private static final String ID = "cd_evento";
    private static final String NM_EVENTO = "nm_evento";
    private static final String DT_EVENTO = "dt_evento";
    private static final String DS_TIPO = "ds_tipo";
    private static final String DS_ESTACAO = "ds_estacao";
    private static final String DS_PERIODO = "ds_periodo";
    private static final String DS_EVENTO = "ds_evento";

    public EventoDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =  "CREATE TABLE " + TABLE_EVENTO + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NM_EVENTO + " TEXT, "
                + DT_EVENTO + " DATETIME, "
                + DS_TIPO + " TEXT, "
                + DS_ESTACAO + " TEXT, "
                + DS_PERIODO + " TEXT, "
                + DS_EVENTO + " TEXT)";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_EVENTO;
        database.execSQL(drop);

        onCreate(database);
    }

    public String buscarEvento(int id){
        return "";
    }

}
