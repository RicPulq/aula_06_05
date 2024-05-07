package br.com.monkey.aula_06_05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"revisao_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Participante (nome TEXT, cpf TEXT PRIMARY KEY, telefone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserDetails");
    }

    public boolean insertUserData(String nome, String cpf, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("cpf", cpf);
        contentValues.put("telefone", telefone);

        long dados = db.insert("UserDetails", null, contentValues);

        return dados != -1;
    }

    public Cursor getUserData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM UserDetails", null);
    }

    public Cursor getUserByName(String nome){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM UserDetails WHERE nome = ?", new String[]{nome});
    }
}
