/*
 * EventFind
 *
 * Tipo: IConfiguracao
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.modelo.configuracao;

import java.util.Set;

import com.eventfind.modelo.IObjetoGenerico;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.ITipoEvento;

/**
 * Interface que representa a CONFIGURACAO
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface IConfiguracao extends IObjetoGenerico {
	
	/**
	 * Obter o valor de cidade
	 * @return Valor de cidade
	 */
	public ICidade getCidade();
	
	/**
	 * Seta o valor de cidade em cidade
	 * @param cidade Valor a ser setado
	 */
	public void setCidade(ICidade cidade);

	/**
	 * Adicionar tipo do evento
	 * @param tipoEvento Tipop do Evento
	 */
	public void addTipoEvento(ITipoEvento tipoEvento);

	/**
	 * Retornar a coleção de tipos de eventos
	 * @return Coleção de tipos de eventos
	 */
	public Set<ITipoEvento> getTiposEventos();
	
	/**
	 * Remove o tipo de evento da configuração
	 * @param tipoEvento Tipo do evento para ser removido
	 * @return Tipo do evento removido
	 */
	public ITipoEvento removerTipoEvento(ITipoEvento tipoEvento);

	/**
	 * Retorna a imagem do tipo de evento para o configurador.
	 * 
	 * As imagens foram inseridas no /res/drawable
	 * 
	 * @return
	 */
	public int getImagem(ITipoEvento tipoEvento);
	
	/**
	 * Verifica se existe o tipo "Cultural" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Cultural",<false> caso contrário
	 */
	public boolean existeTipoCultural();

	/**
	 * Verifica se existe o tipo "Gastrônomico" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Gastrônomico",<false> caso contrário
	 */
	public boolean existeTipoGastronomico();

	/**
	 * Verifica se existe o tipo "Esportivo" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Esportivo",<false> caso contrário
	 */
	public boolean existeTipoEsportivo();

	/**
	 * Verifica se existe o tipo "Religioso" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Religioso",<false> caso contrário
	 */
	public boolean existeTipoReligioso();

	/**
	 * Verifica se existe o tipo "Político" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Político",<false> caso contrário
	 */
	public boolean existeTipoPolitico();

	/**
	 * Verifica se existe o tipo "Musical" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Musical",<false> caso contrário
	 */
	public boolean existeTipoMusical();
	
	/**
	 * Verifica se existe o tipo "Carnaval" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Carnaval",<false> caso contrário
	 */
	public boolean existeTipoCarnaval();

	/**
	 * Verifica se existe o tipo "Outros" na coleção de tipos da configuração
	 * @return <true> se existe o tipo "Outros",<false> caso contrário
	 */
	public boolean existeTipoOutros();
}
