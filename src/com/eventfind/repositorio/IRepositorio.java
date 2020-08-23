/*
 * EventFind
 *
 * Tipo: IRepositorio
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;

/**
 * Interface que defini como o reposit�rio ir� responder as chamadas do controlador
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface IRepositorio {

	/**
	 * Processar as mensagens do controlado para o reposit�rio
	 * @param mensagem Mensagem
	 * @return <true> se a mensagem for processada, <false> caso contr�rio
	 */
	public boolean processarMensagem(Message mensagem);

	/**
	 * Fechar conex�o com o banco
	 * @param sqLiteDatabase Conex�o com banco
	 */
	public abstract void fecharConexaoBancoDados(SQLiteDatabase sqLiteDatabase);

	/**
	 * Abriar conex�o com banco
	 */
	public abstract SQLiteDatabase obterConexaoBancoDados();

	/**
	 * Apagar os dados na tabela de acordo com a condi��o passada como par�metro 
	 * @param nomeTabela Nome da Tabela
	 * @param where Condi��o para Apagar
	 * @param sqLiteDatabase Conex�o com banco
	 * @param whereArgs Argumento da Condi��o
	 * @return Quantidade de registro apagados
	 */
	public abstract int remover(String nomeTabela, String where, String[] whereArgs, SQLiteDatabase sqLiteDatabase);

	/**
	 * Atualizar os registro na banco, na tabela informada por par�metro
	 * @param nameTabela Nome da Tabela
	 * @param valores Valores para serem atualizado
	 * @param where Condi��o 
	 * @param whereArgs Argumentos 
	 * @param sqLiteDatabase Conex�o com banco
	 */
	public abstract int atualizar(String nomeTabela, ContentValues valores, String where,
			String[] whereArgs, SQLiteDatabase sqLiteDatabase);

	/**
	 * Inserir registro no banco de dados, de acordo com a tabela informada como par�metro
	 * @param nomeTabela Nome da Tabela
	 * @param valores Valores da Tabela
	 * @param sqLiteDatabase Conex�o com banco
	 * @return Id do Registro adicionado
	 */
	public abstract long inserir(String nomeTabela, ContentValues valores, SQLiteDatabase sqLiteDatabase);
	
	/**
	 * Criar o banco de dados da aplica��o, caso elas n�o existam
	 * Cria��o das tabelas:
	 * 	- Evento
	 * 	- Configura��o
	 * Atualiza os eventos, realizando conex�o com o servidor
	 */
	public void criarTabelasECarregar(Context contexto);
}
