package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataEspecialDAO extends SQLiteOpenHelper {

    private static final int VERSAO  = 1;
    private static final String TABLE_DATA_ESPECIAL = "DATA_ESPECIAL";
    private static final  String TABLE_PESSOA = "PESSOA";
    private static final String DATABASE = "NATURA";

    public DataEspecialDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataEspecialDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTableAluno =  "CREATE TABLE " + TABLE_DATA_ESPECIAL + "( "
                + "cd_data_especial INTEGER PRIMARY KEY, "
                + " cd_pessoa INTEGER,dt_especial DATETIME, ds_data_especial, " +
                "FOREIGN KEY(cd_pessoa) REFERENCES "+ TABLE_PESSOA +" (cd_pessoa))";

        database.execSQL(createTableAluno);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_DATA_ESPECIAL;
        database.execSQL(drop);

        onCreate(database);
    }
}
