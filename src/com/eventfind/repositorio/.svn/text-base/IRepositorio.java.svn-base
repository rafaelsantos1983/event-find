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
 * Interface que defini como o repositório irá responder as chamadas do controlador
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface IRepositorio {

	/**
	 * Processar as mensagens do controlado para o repositório
	 * @param mensagem Mensagem
	 * @return <true> se a mensagem for processada, <false> caso contrário
	 */
	public boolean processarMensagem(Message mensagem);

	/**
	 * Fechar conexão com o banco
	 * @param sqLiteDatabase Conexão com banco
	 */
	public abstract void fecharConexaoBancoDados(SQLiteDatabase sqLiteDatabase);

	/**
	 * Abriar conexão com banco
	 */
	public abstract SQLiteDatabase obterConexaoBancoDados();

	/**
	 * Apagar os dados na tabela de acordo com a condição passada como parâmetro 
	 * @param nomeTabela Nome da Tabela
	 * @param where Condição para Apagar
	 * @param sqLiteDatabase Conexão com banco
	 * @param whereArgs Argumento da Condição
	 * @return Quantidade de registro apagados
	 */
	public abstract int remover(String nomeTabela, String where, String[] whereArgs, SQLiteDatabase sqLiteDatabase);

	/**
	 * Atualizar os registro na banco, na tabela informada por parâmetro
	 * @param nameTabela Nome da Tabela
	 * @param valores Valores para serem atualizado
	 * @param where Condição 
	 * @param whereArgs Argumentos 
	 * @param sqLiteDatabase Conexão com banco
	 */
	public abstract int atualizar(String nomeTabela, ContentValues valores, String where,
			String[] whereArgs, SQLiteDatabase sqLiteDatabase);

	/**
	 * Inserir registro no banco de dados, de acordo com a tabela informada como parâmetro
	 * @param nomeTabela Nome da Tabela
	 * @param valores Valores da Tabela
	 * @param sqLiteDatabase Conexão com banco
	 * @return Id do Registro adicionado
	 */
	public abstract long inserir(String nomeTabela, ContentValues valores, SQLiteDatabase sqLiteDatabase);
	
	/**
	 * Criar o banco de dados da aplicação, caso elas não existam
	 * Criação das tabelas:
	 * 	- Evento
	 * 	- Configuração
	 * Atualiza os eventos, realizando conexão com o servidor
	 */
	public void criarTabelasECarregar(Context contexto);
}
