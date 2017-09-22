package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataEspecialDAO extends SQLiteOpenHelper {

    private static final String TABLE_DATA_ESPECIAL = "DATA_ESPECIAL";
    private static final String ID = "cd_data_especial";
    private static final String DT_ESPECIAL = "dt_especial";
    private static final String DS_DATA_ESPECIAL = "ds_data_especial";

    public DataEspecialDAO(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createTable =  "CREATE TABLE " + TABLE_DATA_ESPECIAL + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DT_ESPECIAL + " DATETIME, "
                + DS_DATA_ESPECIAL + " TEXT)";

        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_DATA_ESPECIAL;
        database.execSQL(drop);

        onCreate(database);
    }
}
