package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.natura.fiap.naturatododia.entity.Produto;
import br.com.natura.fiap.naturatododia.entity.Sugestao;

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
    private static final String NM_PRODUTO = "nm_produto";


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
                + "PRIMARY KEY ("+ ID +", " + CD_PRODUTO + "), "
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

    public List<Sugestao> buscarSugestoesPorEvento(int idEvento){
        List<Sugestao> listSugest = new ArrayList<>();
        String select =  "SELECT * FROM " + TABLE_SUGESTAO + " WHERE " + CD_EVENTO + " = " + idEvento;
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{

            while (cursor.moveToNext()) {
                Sugestao sugestao = new Sugestao();
                sugestao.setId(cursor.getInt(0));
                sugestao.setNome(cursor.getString(2));
                listSugest.add(sugestao);
            }
        }catch(Exception e){
            listSugest = null;
        }
        finally{
            cursor.close();
        }

        return listSugest;
    }

    public List<Sugestao> buscarSugestoesSalvas(){
        List<Sugestao> listSugest = new ArrayList<>();
        String select =  "SELECT * FROM " + TABLE_SUGESTAO + " WHERE " + IS_SALVO + " = " + 1;
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{
            while (cursor.moveToNext()) {
                Sugestao sugestao = new Sugestao();
                sugestao.setId(cursor.getInt(0));
                sugestao.setNome(cursor.getString(2));
                sugestao.setSalvo(cursor.getInt(3) == 1);
                listSugest.add(sugestao);;
            }
        }catch(Exception e){
            listSugest = null;
        }
        finally{
            cursor.close();
        }

        return listSugest;
    }

    public List<Produto> buscarProdutosPorSugestao(int idSugestao){
        List<Produto> prds = new ArrayList<>();
        String select =  "SELECT P." + CD_PRODUTO + ", P." + NM_PRODUTO + " FROM " + TABLE_SUGESTAO_PRODUTO +
                " SG INNER JOIN " + TABLE_PRODUTO + " P " +
                "ON SG." + CD_PRODUTO + " = P." + CD_PRODUTO + " WHERE " + ID + " = " + idSugestao;
        Cursor cursor = null;
        try{
            cursor = getReadableDatabase().rawQuery(select, null);

            while (cursor.moveToNext()) {
                Produto prod = new Produto();
                prod.setId(cursor.getInt(0));
                prod.setNome(cursor.getString(1));
                prds.add(prod);
            }
        }catch(Exception e){
            prds = null;
        }
        finally{
            cursor.close();
        }

        return prds;
    }

    public  void salvarSugestao(int idSugestao, String nomeSugestao, boolean salvo){
        String salva = "UPDATE " + TABLE_SUGESTAO + " SET "
                + IS_SALVO + " = " + (salvo ? "1" : "0") + ", "
                + NM_SUGESTAO + " = '" + nomeSugestao + "'"
                + " WHERE " + ID + " = " + idSugestao;

        getWritableDatabase().execSQL(salva);
    }

    public void createMock(SQLiteDatabase db){
        try{
            int codEvento = 0;
            String select =  "SELECT " + CD_EVENTO + " FROM " + TABLE_EVENTO;
            Cursor cursor = db.rawQuery(select, null);
            try{

                if (cursor.moveToNext()){
                    codEvento = cursor.getInt(0);
                }

            }catch(Exception e){
            }
            finally{
                cursor.close();
            }

            String insert = "INSERT INTO  " + TABLE_SUGESTAO + " ("
                    + CD_EVENTO + ","
                    + NM_SUGESTAO + ","
                    + IS_SALVO + ") VALUES ("
                    +  codEvento + ","
                    + "'Sugestão 1',"
                    + 0 + ")";

            db.execSQL(insert);
        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
    }


    public void createMock2(SQLiteDatabase db){
        try{
            int codSugestao = 0;
            String select =  "SELECT " + ID + " FROM " + TABLE_SUGESTAO;
            Cursor cursor = db.rawQuery(select, null);
            try{

                if (cursor.moveToNext()){
                    codSugestao = cursor.getInt(0);
                }

            }catch(Exception e){
            }
            finally{
                cursor.close();
            }

            String insert = "INSERT INTO  " + TABLE_SUGESTAO_PRODUTO + " ("
                    + ID + ","
                    + CD_PRODUTO + ") VALUES ("
                    +  codSugestao + ","
                    + 6 + ")";

            db.execSQL(insert);

            insert = "INSERT INTO  " + TABLE_SUGESTAO_PRODUTO + " ("
                    + ID + ","
                    + CD_PRODUTO + ") VALUES ("
                    +  codSugestao + ","
                    + 14 + ")";

            db.execSQL(insert);
        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
    }


}
