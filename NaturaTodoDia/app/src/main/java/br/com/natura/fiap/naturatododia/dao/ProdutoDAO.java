package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProdutoDAO extends SQLiteOpenHelper {

    private static final String TABLE_PRODUTO = "PRODUTO";
    private static final String ID = "cd_produto";
    private static final String NM_PRODUTO = "nm_produto";
    private static final String DS_PRODUTO = "ds_produto";
    private static final String DS_TIPO = "ds_tipo";
    private static final String DS_GENERO = "ds_genero";
    private static final String DS_TOM = "ds_tom";
    private static final String FL_IMAGEM = "fl_imagem";
    private static final String DS_LINK = "ds_link";
    private static final String VL_FPS = "vl_fps";
    private static final String HAS_BRILHO = "has_brilho";
    private static final String VL_PRECO = "vl_preco";

    public ProdutoDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE " + TABLE_PRODUTO + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NM_PRODUTO + " TEXT, "
                + DS_PRODUTO + " TEXT, "
                + DS_TIPO + " TEXT, "
                + DS_GENERO + " TEXT, "
                + DS_TOM + " TEXT, "
                + FL_IMAGEM + " BLOB, "
                + DS_LINK + " TEXT, "
                + VL_FPS + " INTEGER, "
                + HAS_BRILHO + " INTEGER DEFAULT 0,"
                + VL_PRECO + "REAL)";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;
        database.execSQL(drop);

        onCreate(database);
    }
}
