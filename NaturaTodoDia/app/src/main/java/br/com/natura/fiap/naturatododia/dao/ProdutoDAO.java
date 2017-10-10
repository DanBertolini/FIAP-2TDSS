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
        String createTable = "CREATE TABLE " + TABLE_PRODUTO + " ( "
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
                + VL_PRECO + " REAL,"
                + IS_ATIVO + " INTEGER DEFAULT 1)";

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
        String select =  "SELECT * FROM " + TABLE_PRODUTO + " WHERE " + ID + " = " + id;
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
                        + NM_PRODUTO + " = '" + prd.getNome() + "', "
                        + DS_PRODUTO + " = '" + prd.getDescricao() + "', "
                        + DS_TIPO + " = '" + prd.getTipo() + "', "
                        + DS_GENERO + " = '" + prd.getGenero() + "', "
                        + DS_COR + " = '" + prd.getCor() + "', "
                        + DS_TOM + " = '" + prd.getTom() + "', "
                        + FL_IMAGEM + " = '" + prd.getImg() + "', "
                        + DS_LINK + " = '" + prd.getLink() + "', "
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
                        + "'" + prd.getNome() + "', "
                        + "'" + prd.getDescricao() + "', "
                        + "'" + prd.getTipo() + "', "
                        + "'" + prd.getGenero() + "', "
                        + "'" + prd.getCor() + "', "
                        + "'" + prd.getTom() + "', "
                        + "'" + prd.getImg() + "', "
                        + "'" + prd.getLink() + "', "
                        + prd.getFps() + ", "
                        + (prd.isBrilho() ? "1" : "0") + ", "
                        + prd.getPreco() + ", "
                        + (prd.isAtivo() ? "1" : "0") + ")";
                database.execSQL(insert);
            }

        }catch (Exception e){
            Log.e("DB-ERRO", "Erro ao incluir/atualizar produto");
        }
    }

    public void createMock(SQLiteDatabase db){
        try {
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
                    + 6 + ", "
                    + "'Batom Extreme Matific', "
                    + "'Com acabamento super matte, o Batom extreme matific traz alta performance e longa duração. " +
                    "Dermatologicamente testado, ele mantém os lábios hidratados e com textura macia. Conteúdo: " +
                    "3,5g. Benefícios: Efeito matte com longa duração.', "
                    + "'Batom', "
                    + "'F', "
                    + "'Rouge 11-M, Violeta 7-M, Nude 7-M, Violeta 4-M', "
                    + "'Escuro', "
                    + "'prd_6.jpg', "
                    + "'http://www.natura.com.br/p/batom-extreme-matific-fps-15-una-64319', "
                    + 15 + ", "
                    + 0 + ", "
                    + 45.9 + ", "
                    + 1 + ")";
            db.execSQL(insert);

            insert = "INSERT INTO " + TABLE_PRODUTO +
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
                    + 14 + ", "
                    + "'Sombra Ultrapigmento Natura Una', "
                    + "'Natura Una traz um novo lançamento: a Sombra Ultrapigmento, que tem cor intensa desde a " +
                    "primeira aplicação, com 12 horas de duração, sem necessitar de retoques ao longo do dia." +
                    " Sua textura cremosa além de garantir a fixação na pele, possibilita melhor aderência e é" +
                    " facilmente aplicável, evitando que a cor acumule nos vincos. As sombras Ultra Pigmento " +
                    "permitem dois tipos de efeitos: uma cor intensa com pincel seco e superintensa com pincel " +
                    "molhado. Natura Una é uma linha que traz a combinação ideal entre alta performance e " +
                    "ingredientes naturais, com tecnologia inovadora e cores intensas. Revele a sua beleza com a nova" +
                    " Sombra ultrapigmento e expresse através do seu olhar. Conteúdo: 3,6g. Benefícios: Maquiagem, com alta " +
                    "fixação e longa duração.', "
                    + "'Sombra', "
                    + "'F', "
                    + "'Várias', "
                    + "'Médio', "
                    + "'prd_14.jpg', "
                    + "'http://www.natura.com.br/p/sombra-ultrapigmento-natura-una-36g-51301', "
                    + 0 + ", "
                    + 0 + ", "
                    + 76.9 + ", "
                    + 1 + ")";
            db.execSQL(insert);
        }catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
    }

}
