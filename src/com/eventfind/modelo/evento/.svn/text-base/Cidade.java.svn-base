/*
 * EventFind
 *
 * Tipo: Cidade
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
 * Classe que representa a CIDADE
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Cidade extends ObjetoGenerico implements Serializable, ICidade,BaseColumns {

	private static final long serialVersionUID = 1L;
	
	/** Nome */
	private String nome;
	
	/** NomeReduzido */
	private String nomeReduzido;

	/** Estado */
	private IEstado estado;
	
	/**
	 * @see com.eventfind.modelo.evento.ICidade#getNome()
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @see com.eventfind.modelo.evento.ICidade#setNome(java.lang.String)
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @see com.eventfind.modelo.evento.ICidade#getNomeReduzido()
	 */
	public String getNomeReduzido() {
		return nomeReduzido;
	}

	/**
	 * @see com.eventfind.modelo.evento.ICidade#setNomeReduzido(java.lang.String)
	 */
	public void setNomeReduzido(String nomeReduzido) {
		this.nomeReduzido = nomeReduzido;
	}

	/**
	 * @see com.eventfind.modelo.evento.ICidade#getEstado()
	 */
	public IEstado getEstado() {
		return estado;
	}

	/**
	 * @see com.eventfind.modelo.evento.ICidade#setEstado(com.eventfind.modelo.evento.IEstado)
	 */
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}
	
	/**
	 * @see com.eventfind.modelo.evento.ICidade#getCidadeFormatadaComEstado()
	 */
	public String getCidadeFormatadaComEstado(){
		return nome + "/" + estado.getSigla();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getNome();
	}
}
