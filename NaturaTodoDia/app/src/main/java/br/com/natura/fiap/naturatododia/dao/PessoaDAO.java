package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.natura.fiap.naturatododia.entity.Pessoa;


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

    public Pessoa getDadosPessoa(){
        Pessoa pessoa = new Pessoa();
        String select =  "SELECT * FROM " + TABLE_PESSOA;

        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{

            while (cursor.moveToNext()){
                pessoa.setId(cursor.getInt(0));
                pessoa.setNome(cursor.getString(1));
                pessoa.setDtNasci(new Date(cursor.getLong(2) * 1000));
                pessoa.setSexo(cursor.getString(3));
                pessoa.setCorPele(cursor.getString(4));
                pessoa.setTipoPele(cursor.getString(5));
                pessoa.setTipoCabelo(cursor.getString(6));
            }

        }catch(Exception e){
            pessoa = null;
        }
        finally{
            cursor.close();
        }
        return pessoa;
    }

    public void atualizaCadastro(Pessoa pessoa){
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        String update = "UPDATE " + TABLE_PESSOA + " SET "
                + NM_PESSOA + " = '" + pessoa.getNome() + "', "
                + DT_NASCI + " = '" + sdf.format(pessoa.getDtNasci()) + "', "
                + DS_SEXO + " = '" + pessoa.getSexo() + "', "
                + DS_COR_PELE + " = '" + pessoa.getCorPele() + "', "
                + DS_TIPO_PELE + " = '" + pessoa.getTipoPele() + "', "
                + DS_TIPO_CABELO + " = '" + pessoa.getTipoCabelo() + "'"
                + " WHERE " + ID + " = " + pessoa.getId();

        getWritableDatabase().execSQL(update);
    }

    public void createMock(SQLiteDatabase db){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

            String insert = "INSERT INTO  " + TABLE_PESSOA + " ("
                    + NM_PESSOA + ","
                    + DT_NASCI + ","
                    + DS_SEXO + ","
                    + DS_COR_PELE + ","
                    + DS_TIPO_PELE + ","
                    + DS_TIPO_CABELO +  ") VALUES ("
                    + "'Isabela Tavares',"
                    + "'1984-10-23 12:00:00',"
                    + "'Feminino',"
                    + "'Branca',"
                    + "'Oleosa',"
                    + "'Liso'" + ")";

            db.execSQL(insert);
        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
    }

}
