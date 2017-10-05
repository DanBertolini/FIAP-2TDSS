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

public class ProdutoDAO extends SQLiteOpenHelper {

    private static final String TABLE_PRODUTO = "PRODUTO";
    private static final String ID = "cd_produto";
    private static final String NM_PRODUTO = "nm_produto";
    private static final String DS_PRODUTO = "ds_produto";
    private static final String DS_TIPO = "ds_tipo";
    private static final String DS_GENERO = "ds_genero";
    private static final String DS_COR = "ds_cor";
    private static final String DS_TOM = "ds_tom";
    private static final String FL_IMAGEM = "fl_imagem";
    private static final String DS_LINK = "ds_link";
    private static final String VL_FPS = "vl_fps";
    private static final String HAS_BRILHO = "has_brilho";
    private static final String VL_PRECO = "vl_preco";
    private static final String IS_ATIVO = "is_ativo";

    public ProdutoDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE " + TABLE_PRODUTO + "( "
                + ID + " INTEGER PRIMARY KEY, "
                + NM_PRODUTO + " TEXT, "
                + DS_PRODUTO + " TEXT, "
                + DS_TIPO + " TEXT, "
                + DS_GENERO + " TEXT, "
                + DS_COR + " TEXT, "
                + DS_TOM + " TEXT, "
                + FL_IMAGEM + " TEXT, "
                + DS_LINK + " TEXT, "
                + VL_FPS + " INTEGER, "
                + HAS_BRILHO + " INTEGER DEFAULT 0,"
                + VL_PRECO + "REAL,"
                + IS_ATIVO + "INTEGER DEFAULT 1)";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;
        database.execSQL(drop);

        onCreate(database);
    }

    public Produto buscarProduto(int id){
        Produto prod = new Produto();
        String select =  "SELECT * FROM " + TABLE_PRODUTO + "WHERE " + ID + " = " + id;
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{

            while (cursor.moveToNext()) {
                prod.setId(cursor.getInt(0));
                prod.setNome(cursor.getString(1));
                prod.setDescricao(cursor.getString(2));
                prod.setTipo(cursor.getString(3));
                prod.setGenero(cursor.getString(4));
                prod.setCor(cursor.getString(5));
                prod.setTom(cursor.getString(6));
                prod.setLink(cursor.getString(8));
                prod.setFps(cursor.getInt(9));
                prod.setBrilho(cursor.getInt(10) == 1);
                prod.setPreco(cursor.getFloat(11));
            }
        }catch(Exception e){
            prod = null;
        }
        finally{
            cursor.close();
        }

        return prod;
    }

    public void atualizarProd(Produto prd){
        String select =  "SELECT * FROM " + TABLE_PRODUTO + "WHERE " + ID + " = " + prd.getId();
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        try{
            SQLiteDatabase database = getWritableDatabase();
            if(cursor.moveToNext()){
                String update = "UPDATE " + TABLE_PRODUTO +
                        "SET "
                        + NM_PRODUTO + " = " + prd.getNome() + ", "
                        + DS_PRODUTO + " = " + prd.getDescricao() + ", "
                        + DS_TIPO + " = " + prd.getTipo() + ", "
                        + DS_GENERO + " = " + prd.getGenero() + ", "
                        + DS_COR + " = " + prd.getCor() + ", "
                        + DS_TOM + " = " + prd.getTom() + ", "
                        + FL_IMAGEM + " = " + prd.getImg() + ", "
                        + DS_LINK + " = " + prd.getLink() + ", "
                        + VL_FPS + " = " + prd.getFps() + ", "
                        + HAS_BRILHO + " = " + (prd.isBrilho() ? "1" : "0") + ", "
                        + VL_PRECO + " = " + prd.getPreco() + ", "
                        + IS_ATIVO + " = " + (prd.isAtivo() ? "1" : "0")
                        + " WHERE " + ID + " = " + prd.getId();
                database.execSQL(update);
            } else {
                String insert = "INSERT INTO " + TABLE_PRODUTO +
                        "("
                        + ID + ", "
                        + NM_PRODUTO + ", "
                        + DS_PRODUTO + ", "
                        + DS_TIPO + ", "
                        + DS_GENERO + ", "
                        + DS_COR + ", "
                        + DS_TOM + ", "
                        + FL_IMAGEM + ", "
                        + DS_LINK + ", "
                        + VL_FPS + ", "
                        + HAS_BRILHO + ", "
                        + VL_PRECO + ", "
                        + IS_ATIVO + ") VALUES ("
                        + prd.getId() + ", "
                        + prd.getNome() + ", "
                        + prd.getDescricao() + ", "
                        + prd.getTipo() + ", "
                        + prd.getGenero() + ", "
                        + prd.getCor() + ", "
                        + prd.getTom() + ", "
                        + prd.getImg() + ", "
                        + prd.getLink() + ", "
                        + prd.getFps() + ", "
                        + (prd.isBrilho() ? "1" : "0") + ", "
                        + prd.getPreco() + ", "
                        + (prd.isAtivo() ? "1" : "0");
                database.execSQL(insert);
            }

        }catch (Exception e){
            Log.e("DB-ERRO", "Erro ao incluir/atualizar produto");
        }
    }

}
