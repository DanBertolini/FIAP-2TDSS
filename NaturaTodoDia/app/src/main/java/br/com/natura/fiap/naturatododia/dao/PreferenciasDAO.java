package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.GregorianCalendar;

import br.com.natura.fiap.naturatododia.entity.Preferencia;

public class PreferenciasDAO extends SQLiteOpenHelper {
    private static final  String TABLE_PREFERENCIAS = "PREFERENCIAS";
    private static final  String ID = "cd_preferencias_pessoa";
    private static final  String DS_TOM_FAVORITO = "ds_tom_favorito";
    private static final  String DS_AROMA_FAVORITO = "ds_aroma_favorita";
    private static final String CD_PESSOA = "cd_pessoa";
    private static final String TABLE_PESSOA = "PESSOA";

    public PreferenciasDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){

        String createTable =  "CREATE TABLE " + TABLE_PREFERENCIAS + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CD_PESSOA + " INTEGER, "
                + DS_TOM_FAVORITO + " TEXT, "
                + DS_AROMA_FAVORITO + " TEXT, "
                + "FOREIGN KEY(" + CD_PESSOA + ") REFERENCES "+ TABLE_PESSOA + "(" + CD_PESSOA + "))";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_PREFERENCIAS;
        database.execSQL(drop);

        onCreate(database);
    }

    public Preferencia getPreferenciasPessoa(){
        Preferencia pref = new Preferencia();
        String select =  "SELECT * FROM " + TABLE_PREFERENCIAS;

        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{

            while (cursor.moveToNext()){
                pref.setId(cursor.getInt(0));
                pref.setTomFavorito(cursor.getString(2));
                pref.setAromaFavorita(cursor.getString(3));
            }

        }catch(Exception e){
            pref = null;
        }
        finally{
            cursor.close();
        }
        return pref;
    }

    public void atualizaPreferencias(Preferencia preferencias){
        String update = "UPDATE " + TABLE_PREFERENCIAS + " SET "
                + DS_TOM_FAVORITO + " = '" + preferencias.getTomFavorito() +"', "
                + DS_AROMA_FAVORITO + " = '" + preferencias.getAromaFavorita() + "'"
                + " WHERE " + ID + " = " + preferencias.getId();

        getWritableDatabase().execSQL(update);
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

            String insert = "INSERT INTO  " + TABLE_PREFERENCIAS + " ("
                    + CD_PESSOA + ","
                    + DS_TOM_FAVORITO + ","
                    + DS_AROMA_FAVORITO + ") VALUES ("
                    + codPessoa + ","
                    + "'Citricos'"
                    + "'Claros'" + ")";

            db.execSQL(insert);
        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
    }

}

