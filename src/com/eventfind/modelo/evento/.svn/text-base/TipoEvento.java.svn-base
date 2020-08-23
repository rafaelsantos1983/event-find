/*
 * EventFind
 *
 * Tipo: TipoEvento
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
 * Classe que representa O TIPO DO EVENTO 
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class TipoEvento extends ObjetoGenerico implements Serializable, ITipoEvento,BaseColumns {
	private static final long serialVersionUID = 1L;
	
	/** Código do Tipo */
	private Integer codigo;
	
	/** Descrição */
	private String descricao;
	
	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#getDescricao()
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#setDescricao(java.lang.String)
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#getCodigo()
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#setCodigo(java.lang.Integer)
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoCultural()
	 */
	public boolean tipoCultural(){
		return codigo.equals(CULTURAL);
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoGastronomico()
	 */
	public boolean tipoGastronomico(){
		return codigo.equals(GASTRONOMICO);
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoEsportivo()
	 */
	public boolean tipoEsportivo(){
		return codigo.equals(ESPORTIVO);
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoReligioso()
	 */
	public boolean tipoReligioso(){
		return codigo.equals(RELIGIOSO);
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoPolitico()
	 */
	public boolean tipoPolitico(){
		return codigo.equals(POLITICO);
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoMusical()
	 */
	public boolean tipoMusical(){
		return codigo.equals(MUSICAL);
	}
	
	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoCarnaval()
	 */
	public boolean tipoCarnaval(){
		return codigo.equals(CARNAVAL);
	}

	/**
	 * @see com.eventfind.modelo.evento.ITipoEvento#tipoOutros()
	 */
	public boolean tipoOutros(){
		return codigo.equals(OUTROS);
	}
}

