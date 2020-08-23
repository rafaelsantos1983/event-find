/*
 * EventFind
 *
 * Tipo: Estado
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.modelo.evento;

import java.io.Serializable;

import android.provider.BaseColumns;

import com.eventfind.modelo.ObjetoGenerico;

/**
 * Classe que representa o ESTADO
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Estado extends ObjetoGenerico implements Serializable, IEstado,BaseColumns {

	private static final long serialVersionUID = 1L;
	
	/** Sigla */
	private String sigla;
	/** Nome */
	private String nome;
	
	/**
	 * @see com.eventfind.modelo.evento.IEstado#getSigla()
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEstado#setSigla(java.lang.String)
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	 
	/**
	 * @see com.eventfind.modelo.evento.IEstado#getNome()
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEstado#setNome(java.lang.String)
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getSigla();
	}
}
