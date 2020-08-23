/*
 * EventFind
 *
 * Tipo: Evento
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eventfind.R;

/**
 * Classe Helper para manipulação com os dados do banco
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class SQLiteHelper extends SQLiteOpenHelper{
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = SQLiteHelper.class.getSimpleName();
	
	/** Nome do banco de dados */
	public final static String NOME_BANCO_DADOS = "EventFindBD.db";
	
	/** Número da Versão do BAnco de Dados */
	private static final int VERSAO_BANCO = 65;

	/** Nome da Tabela EVENTO */
	public static final String TABELA_EVENTO = "EVENTO";
	
	/** Nome da Tabela CONFIGURACAO */
	public static final String TABELA_CONFIGURACAO = "CONFIGURACAO";
	
	/** Nome da Tabela CONFIGURACAO_TIPO_EVENTO */
	public static final String TABELA_CONFIGURACAO_TIPO_EVENTO = "CONFIGURACAO_TIPO_EVENTO";	
	
	/**
	 * Referncia ao contexto
	 */
	private Context contexto;
	
	/**
	 * Cria uma instância de BancoHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versão do banco de dados (se for diferente é para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	public SQLiteHelper(Context context) {
		super(context,NOME_BANCO_DADOS, null, VERSAO_BANCO);
		contexto = context;
	}

	/**
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "Criando banco do EventFind com os sqls");

		// Executa cada sql 
		for (String scriptSQL : contexto.getResources().getStringArray(R.array.ScriptCriacaoBanco)) {
			Log.i(TAG, scriptSQL);
			// Cria o banco de dados executando o script de criação
			db.execSQL(scriptSQL);
		}
		
		//TODO Ao finalizar o projeto, antes de colocar no Market, retirar esta leitura
		//Script para dar Carga no Banco para dados para TESTE
		// Executa cada sql 
		for (String scriptSQL : contexto.getResources().getStringArray(R.array.ScriptCargaBanco)) {
			Log.i(TAG, scriptSQL);
			db.execSQL(scriptSQL);
		}		
		Log.i(TAG, "Banco de Dados do EventFind criado");
	}

	/**
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		Log.w(TAG, "Atualizando da versão do Banco de Dados do EventFind da versão antiga: " + versaoAntiga + " para a nova versão: " + novaVersao + ". Todos os registros serão apagados.");
		// Apagar as tabelas...
		for (String scriptSQL : contexto.getResources().getStringArray(R.array.ScriptApagarBanco)) {
			Log.i(TAG, scriptSQL);
			db.execSQL(scriptSQL);	
		}
		// Cria novamente...
		onCreate(db);
	}
}
