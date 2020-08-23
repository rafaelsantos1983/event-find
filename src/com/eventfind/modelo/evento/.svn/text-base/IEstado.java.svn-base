/*
 * EventFind
 *
 * Tipo: IEstado
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.modelo.evento;

import android.net.Uri;

import com.eventfind.modelo.IObjetoGenerico;


/**
 * Interface que representa o ESTADO
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface IEstado extends IObjetoGenerico {
	
	// content://com.eventfind.modelo.provider.evento/evento
	public static final Uri CONTENT_URI = Uri.parse("content://com.eventfind.modelo.provider.evento/eventos");

	// Mime Type para todos os eventos
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.eventos";

	// Mime Type para um único evento
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.eventos";

	// Ordenação default para inserir no order by
	public static final String DEFAULT_SORT_ORDER = "ID ASC";

	public static final String TITULO = "TITULO";
	public static final String ENDERECO = "ENDERECO";
	public static final String DATA = "DATA";
	public static final String CODIGO_TELEFONE = "CODIGO_TELEFONE";
	public static final String TELEFONE = "TELEFONE";
	public static final String DESCRICAO = "DESCRICAO";
	
	/**
	 * Obter o valor de sigla
	 * @return Valor de sigla
	 */
	public String getSigla();
	
	/**
	 * Seta o valor de sigla em sigla
	 * @param sigla Valor a ser setado
	 */
	public void setSigla(String sigla);
	
	/**
	 * Obter o valor de nome
	 * @return Valor de nome
	 */
	public String getNome();
	
	/**
	 * Seta o valor de nome em nome
	 * @param nome Valor a ser setado
	 */
	public void setNome(String nome);
}
