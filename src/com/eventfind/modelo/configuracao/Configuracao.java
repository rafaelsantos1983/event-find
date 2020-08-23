/*
 * EventFind
 *
 * Tipo: Configuracao
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.modelo.configuracao;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.provider.BaseColumns;

import com.eventfind.R;
import com.eventfind.modelo.ObjetoGenerico;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.ITipoEvento;

/**
 * Classe que representa a CONFIGURACAO
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Configuracao extends ObjetoGenerico implements Serializable, BaseColumns, IConfiguracao {

	private static final long serialVersionUID = 1L;
	
	/** Cidade */
	private ICidade cidade;

	/** Tipos de Eventos associados a configuração */
	private Set<ITipoEvento> tiposEventos;
	
	/**
	 * Construtor
	 */
	public Configuracao(){
		tiposEventos = new HashSet<ITipoEvento>();
	}
	
	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#getCidade()
	 */
	public ICidade getCidade() {
		return cidade;
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#setCidade(com.eventfind.modelo.evento.ICidade)
	 */
	public void setCidade(ICidade cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#addTipoEvento(com.eventfind.modelo.evento.ITipoEvento)
	 */
	public void addTipoEvento(ITipoEvento tipoEvento){
		this.tiposEventos.add(tipoEvento);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#getTiposEventos()
	 */
	public Set<ITipoEvento> getTiposEventos(){
		return Collections.unmodifiableSet(this.tiposEventos);
	}
	
	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#removerTipoEvento(com.eventfind.modelo.evento.ITipoEvento)
	 */
	public ITipoEvento removerTipoEvento(ITipoEvento tipoEvento){
		ITipoEvento tipoEventoRemovido = null;
		for (Iterator<ITipoEvento> iterator = this.tiposEventos.iterator(); iterator.hasNext();) {
			ITipoEvento tipo = iterator.next();
			if(tipo.getId().equals(tipoEvento.getId())){
				iterator.remove();
			}
		}
		
		return tipoEventoRemovido;
	}

	/*
	 * Verifica se dentre os tipos de eventos que existem na configuração, existe o informado no parâmetro
	 * @param codigoTipoEvento Código do Tipo do Evento
	 * @return <true> se existir o tipo, <false> caso contrário
	 */
	private boolean existeTipoEvento(int codigoTipoEvento){
		boolean existe = false;
		for (Iterator<ITipoEvento> iterator = tiposEventos.iterator(); (iterator.hasNext() && !existe);) {
			ITipoEvento tipoEvento = (ITipoEvento) iterator.next();
			if(tipoEvento.getCodigo().equals(codigoTipoEvento)){
				existe = true;
			}
		}
		
		return existe;
	}
	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#getImagem(ITipoEvento)
	 */
	public int getImagem(ITipoEvento tipoEvento) {
		switch (tipoEvento.getCodigo()) {
			case ITipoEvento.CULTURAL:
				return R.drawable.cultural32x32;
			case ITipoEvento.GASTRONOMICO:
				return R.drawable.gastronomico32x32;
			case ITipoEvento.ESPORTIVO:
				return R.drawable.esportivo32x32;
			case ITipoEvento.RELIGIOSO:
				return R.drawable.religioso32x32;
			case ITipoEvento.POLITICO:
				return R.drawable.politico32x32;
			case ITipoEvento.MUSICAL:
				return R.drawable.musical32x32;
			case ITipoEvento.CARNAVAL:
				return R.drawable.carnaval32x32;
		}
		return R.drawable.outros32x32;
	}
	
	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoCultural()
	 */
	public boolean existeTipoCultural(){
		return existeTipoEvento(ITipoEvento.CULTURAL);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoGastronomico()
	 */
	public boolean existeTipoGastronomico(){
		return existeTipoEvento(ITipoEvento.GASTRONOMICO);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoEsportivo()
	 */
	public boolean existeTipoEsportivo(){
		return existeTipoEvento(ITipoEvento.ESPORTIVO);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoReligioso()
	 */
	public boolean existeTipoReligioso(){
		return existeTipoEvento(ITipoEvento.RELIGIOSO);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoPolitico()
	 */
	public boolean existeTipoPolitico(){
		return existeTipoEvento(ITipoEvento.POLITICO);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoMusical()
	 */
	public boolean existeTipoMusical(){
		return existeTipoEvento(ITipoEvento.MUSICAL);
	}
	
	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoCarnaval()
	 */
	public boolean existeTipoCarnaval(){
		return existeTipoEvento(ITipoEvento.CARNAVAL);
	}

	/**
	 * @see com.eventfind.modelo.configuracao.IConfiguracao#existeTipoOutros()
	 */
	public boolean existeTipoOutros(){
		return existeTipoEvento(ITipoEvento.OUTROS);
	}
}