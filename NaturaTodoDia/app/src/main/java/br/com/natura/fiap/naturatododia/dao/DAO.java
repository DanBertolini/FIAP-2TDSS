package br.com.natura.fiap.naturatododia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {

    private static final int VERSAO  = 8;
    private static final String TABLE_PRODUTO = "PRODUTO";
    private static final String TABLE_PESSOA = "PESSOA";
    private static final String TABLE_PREFERENCIAS = "PREFERENCIAS";
    private static final String TABLE_DATA_ESPECIAL = "DATA_ESPECIAL";
    private static final String TABLE_EVENTO = "EVENTO";
    private static final String TABLE_SUGESTAO = "SUGESTAO";

    private static final String DATABASE = "NATURA";
    private Context context;

    public  DAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, name, factory, version);

    }

    public  DAO(Context context){

        super(context, DATABASE, null, VERSAO);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String drop = "DROP TABLE IF EXISTS " + TABLE_PRODUTO;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_PESSOA;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_PREFERENCIAS;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_DATA_ESPECIAL;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_SUGESTAO;
        database.execSQL(drop);
        drop = "DROP TABLE IF EXISTS " + TABLE_EVENTO;
        database.execSQL(drop);

        CredencialDAO credencialDAO = new CredencialDAO(this.context);
        credencialDAO.onCreate(database);
        PessoaDAO pessoaDAO = new PessoaDAO(this.context);
        pessoaDAO.onCreate(database);
        HistoricoAlunoDAO historicoAlunoDAO = new HistoricoAlunoDAO(this.context);
        historicoAlunoDAO.onCreate(database);
        AlunoDAO alunoDAO = new AlunoDAO(this.context);
        alunoDAO.onCreate(database);
        ProfessorDAO professorDAO = new ProfessorDAO(this.context);
        professorDAO.onCreate(database);
        SerieDAO serieDAO = new SerieDAO(this.context);
        serieDAO.onCreate(database);
        TurmaDAO turmaDAO = new TurmaDAO(this.context);
        turmaDAO.onCreate(database);
        EventoDAO eventoDAO = new EventoDAO(this.context);
        eventoDAO.onCreate(database);
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(this.context);
        disciplinaDAO.onCreate(database);
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(this.context);
        avaliacaoDAO.onCreate(database);
        NotaDAO notaDAO = new NotaDAO(this.context);
        notaDAO.onCreate(database);



    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoantiga, int versaonova) {



    }

    public void iniciaTabelas(){

        SQLiteDatabase database = getReadableDatabase();

        onCreate(database);
    }
}