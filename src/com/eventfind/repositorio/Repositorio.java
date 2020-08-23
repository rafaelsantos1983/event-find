/*
 * EventFind
 *
 * Tipo: Repositorio
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio;

import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.util.Log;

import com.eventfind.controlador.Controlador;
import com.eventfind.controlador.Protocolos;

/**
 * Classe abstrata que trata a manipulação dos objetos com a base de dados
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Repositorio implements IRepositorio {
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = Repositorio.class.getSimpleName();
	
	/**
	 * Referncia ao controlador
	 */
	private Controlador controlador;
	
	/**
	 * Referencia ao Contexto
	 */
	public Context contexto;
	
	/**
	 * Construtor
	 * @param controlador Controlador
	 */
	public Repositorio(Controlador controlador, Context contexto) {
		this.controlador = controlador;
		this.contexto = contexto;
	}

	/**
	 * @see com.eventfind.repositorio.IRepositorio#inserir(String, ContentValues, SQLiteDatabase)
	 */
	public long inserir(String nomeTabela, ContentValues valores, SQLiteDatabase sqLiteDatabase) {
		long id = sqLiteDatabase.insert(nomeTabela, "", valores);
		Log.i(TAG, "Inseriu na tabela " + nomeTabela + " os registros " + valores.toString());
		return id;
	}

	/**
	 * @see com.eventfind.repositorio.IRepositorio#atualizar(String, ContentValues, String, String[], SQLiteDatabase)
	 */
	public int atualizar(String nomeTabela, ContentValues valores, String where, String[] whereArgs, SQLiteDatabase sqLiteDatabase) {
		int count = sqLiteDatabase.update(nomeTabela, valores, where, whereArgs);
		Log.i(TAG, "Atualizou [" + count + "] registro(s) da tabela: " + nomeTabela);
		return count;
	}

	/**
	 * @see com.eventfind.repositorio.IRepositorio#apagar(String, String, String[], SQLiteDatabase)
	 */
	public int remover(String nomeTabela, String where, String[] whereArgs, SQLiteDatabase sqLiteDatabase) {
		int count = sqLiteDatabase.delete(nomeTabela, where, whereArgs);
		Log.i(TAG, "Removeu [" + count + "] registro(s) da tabela: " + nomeTabela);
		return count;
	}
	
	/**
	 * Retorna um curso na tabela informada como parâmetro, de acordo com as colunas
	 * @param nomeTabela Nome da Tabela
	 * @param colunas Colunas da tabela
	 * @param sqLiteDatabase 
	 */
	public Cursor getCursor(String nomeTabela, String[] colunas, SQLiteDatabase sqLiteDatabase) {
		Cursor curso = null;
		try {
			curso =  sqLiteDatabase.query(nomeTabela,colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(TAG, "Erro ao buscar na tabela: " + nomeTabela + " os registros. \n Erro: " + e.toString());
			throw e;
		}
		
		return curso;
	}	
	
	/**
	 * @see com.eventfind.repositorio.IRepositorio#obterConexaoBancoDados()
	 */
	public SQLiteDatabase obterConexaoBancoDados(){
		return contexto.openOrCreateDatabase(SQLiteHelper.NOME_BANCO_DADOS, Context.MODE_PRIVATE, null);
	}
	
	/**
	 * @see com.eventfind.repositorio.IRepositorio#fecharConexaoBancoDados(SQLiteDatabase)
	 */
	public void fecharConexaoBancoDados(SQLiteDatabase sqLiteDatabase){
		if(sqLiteDatabase != null){
			sqLiteDatabase.close();
		}
	}

//	// SQLiteQueryBuilder
//	// Utilizado pelo Content Provider de carro
//	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection, String selection, String[] selectionArgs,
//			String groupBy, String having, String orderBy) {
//		Cursor c = queryBuilder.query(this.db, projection, selection, selectionArgs, groupBy, having, orderBy);
//		return c;
//	}
	
	/**
	 * @see com.eventfind.repositorio.IRepositorio#criarTabelasECarregar(android.content.Context)
	 */
	public void criarTabelasECarregar(Context contexto){
		boolean bancoCriadoAtualizado = true;
		SQLiteHelper dbHelper = null;
		SQLiteDatabase db = null; 
		try {
			// Criar utilizando um script SQL
			dbHelper = new SQLiteHelper(contexto);
			
			db = dbHelper.getWritableDatabase();
			db.setLocale(Locale.getDefault());
			//TODO Fazer a chamada ao servidor para carrega os eventos
		}catch (Exception e) {//Trata qualquer tipo de exceção, para eviatar que a aplicação seja encerrada
			bancoCriadoAtualizado = false;
			Log.e(TAG, "Erro ao criar e atualizar banco de dados. Log: ");
			e.printStackTrace();
		} finally {
			// Fecha o banco de dados
			if (db != null) {
				db.close();
			}
			if (dbHelper != null) {
				dbHelper.close();
			}
		}
		
		controlador.notitficarMensagemProcesada(Protocolos.BANCO_CRIADO, 0, 0, bancoCriadoAtualizado);
	}
	
	/**
	 * @see com.eventfind.repositorio.IRepositorio#processarMensagem(android.os.Message)
	 */
	public final boolean processarMensagem(Message mensagem) {
		boolean resultado = false;
		switch (mensagem.what) {
		case Protocolos.CRIAR_BANCO:
			criarTabelasECarregar((Context)mensagem.obj);
			resultado = true;
			break;
		default:
			resultado = false;
		}
		return resultado;
	}

	/*
	 * Consulta ao script sql no arquivo "scripts.xml" de acordo com a chave
	 * @param chave Chave para localizar o script SQL no arquivo scripts.xml
	 * @return Script SQL
	 */
	public String obterScriptSQL(int chave){
		String sql = null;
		for (String scriptSQL : contexto.getResources().getStringArray(chave)) {
			sql = scriptSQL;
		}
		return sql;
	}
	
}
