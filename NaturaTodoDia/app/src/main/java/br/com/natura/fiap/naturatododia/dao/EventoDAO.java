package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO extends SQLiteOpenHelper {

    private static final String TABLE_EVENTO = "EVENTO";
    private static final String ID = "cd_evento";
    private static final String NM_EVENTO = "nm_evento";
    private static final String DT_EVENTO = "dt_evento";
    private static final String DS_TIPO = "ds_tipo";
    private static final String DS_ESTACAO = "ds_estacao";
    private static final String DS_PERIODO = "ds_periodo";
    private static final String DS_EVENTO = "ds_evento";
    private static final String CD_PESSOA = "cd_pessoa";
    private static final String TABLE_PESSOA = "PESSOA";

    public EventoDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =  "CREATE TABLE " + TABLE_EVENTO + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CD_PESSOA + " INTEGER, "
                + NM_EVENTO + " TEXT, "
                + DT_EVENTO + " DATETIME, "
                + DS_TIPO + " TEXT, "
                + DS_ESTACAO + " TEXT, "
                + DS_PERIODO + " TEXT, "
                + DS_EVENTO + " TEXT, "
                + "FOREIGN KEY(" + CD_PESSOA + ") REFERENCES "+ TABLE_PESSOA + "(" + CD_PESSOA + "))";

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

    public List<String> buscarEventosPendentes(){
        /*
        * String select =  "SELECT * FROM EVENTO WHERE data = '" +data +"' AND codTurma = "+ turma +" ORDER BY codEvento";
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{

            while (cursor.moveToNext()) {

                Evento evento = new Evento();
                evento.setCd_evento(cursor.getInt(0));
                evento.setNm_evento(cursor.getString(1));
                evento.setDs_evento(cursor.getString(2));
                evento.setDt_evento(cursor.getString(3));
                listaEventos.add(evento);

            }
        }catch(Exception e){
            throw e;
        }
        finally{

            cursor.close();
        }
        *
        *
        * */

        return new ArrayList<String>();
    }

    public List<String> buscarEventosConcluidos(){
        return new ArrayList<String>();
    }

}