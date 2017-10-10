package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.natura.fiap.naturatododia.entity.Evento;

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
        String createTable =  "CREATE TABLE " + TABLE_EVENTO + " ( "
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

    public Evento buscarEvento(int id){
        Evento ev = new Evento();
        String select =  "SELECT * FROM " + TABLE_EVENTO + " WHERE " + ID + " = " + id;
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{

            while (cursor.moveToNext()) {
                ev.setId(cursor.getInt(0));
                ev.setNome(cursor.getString(2));
                ev.setDtEvento(new Date(cursor.getLong(3) * 1000));
                ev.setTipo(cursor.getString(4));
                ev.setEstacao(cursor.getString(5));
                ev.setPeriodo(cursor.getString(6));
                ev.setDescricao(cursor.getString(7));

            }
        }catch(Exception e){
            ev = null;
        }
        finally{
            cursor.close();
        }

        return ev;
    }

    public List<Evento> buscarEventosPendentes(){
        String select =  "SELECT * FROM " + TABLE_EVENTO + " WHERE "+ DT_EVENTO + " >= date('now') ORDER BY " + ID;
        Cursor cursor = getReadableDatabase().rawQuery(select, null);
        List<Evento> listaEventos = new ArrayList<>();
        try{

            while (cursor.moveToNext()) {

                Evento evento = new Evento();
                evento.setId(cursor.getInt(0));
                evento.setNome(cursor.getString(2));
                listaEventos.add(evento);

            }
        }catch(Exception e){
            throw e;
        }
        finally{

            cursor.close();
        }

        return listaEventos;
    }

    public List<Evento> buscarEventosConcluidos(){
        String select =  "SELECT * FROM " + TABLE_EVENTO + " WHERE " + DT_EVENTO + " < date('now') ORDER BY " + ID;
        Cursor cursor = getReadableDatabase().rawQuery(select, null);
        List<Evento> listaEventos = new ArrayList<>();
        try{

            while (cursor.moveToNext()) {

                Evento evento = new Evento();
                evento.setId(cursor.getInt(0));
                evento.setNome(cursor.getString(2));
                listaEventos.add(evento);

            }
        }catch(Exception e){
            throw e;
        }
        finally{

            cursor.close();
        }

        return listaEventos;
    }

    public void createMock(SQLiteDatabase db){
        try{
            int codPessoa = 0;
            String select =  "SELECT " + CD_PESSOA + " FROM " + TABLE_PESSOA;
            Cursor cursor = db.rawQuery(select, null);
            try{

                if (cursor.moveToNext()){
                    codPessoa = cursor.getInt(0);
                }

            }catch(Exception e){
            }
            finally{
                cursor.close();
            }

            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

            String insert = "INSERT INTO  " + TABLE_EVENTO + " ("
                    + CD_PESSOA + ","
                    + NM_EVENTO + ","
                    + DT_EVENTO + ","
                    + DS_TIPO + ","
                    + DS_ESTACAO + ","
                    + DS_PERIODO + ","
                    + DS_EVENTO +  ") VALUES ("
                    + codPessoa +","
                    + "'Aniversario Parducci',"
                    + "'2018-07-19 12:00:00',"
                    + "'Aniversário',"
                    + "'Primavera',"
                    + "'Tarde',"
                    + "'Teste de aniversário'" + ")";

            db.execSQL(insert);

            insert = "INSERT INTO  " + TABLE_EVENTO + " ("
                    + CD_PESSOA + ","
                    + NM_EVENTO + ","
                    + DT_EVENTO + ","
                    + DS_TIPO + ","
                    + DS_ESTACAO + ","
                    + DS_PERIODO + ","
                    + DS_EVENTO +  ") VALUES ("
                    + codPessoa +","
                    + "'Aniversario FIAP',"
                    + "'2017-08-23 12:00:00',"
                    + "'Aniversário',"
                    + "'Outono',"
                    + "'Tarde',"
                    + "'Aniversário da faculdade'" + ")";

            db.execSQL(insert);

        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
    }

}