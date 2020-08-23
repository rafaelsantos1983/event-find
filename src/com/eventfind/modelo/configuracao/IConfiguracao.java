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
	 * Retornar a cole��o de tipos de eventos
	 * @return Cole��o de tipos de eventos
	 */
	public Set<ITipoEvento> getTiposEventos();
	
	/**
	 * Remove o tipo de evento da configura��o
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
	 * Verifica se existe o tipo "Cultural" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Cultural",<false> caso contr�rio
	 */
	public boolean existeTipoCultural();

	/**
	 * Verifica se existe o tipo "Gastr�nomico" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Gastr�nomico",<false> caso contr�rio
	 */
	public boolean existeTipoGastronomico();

	/**
	 * Verifica se existe o tipo "Esportivo" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Esportivo",<false> caso contr�rio
	 */
	public boolean existeTipoEsportivo();

	/**
	 * Verifica se existe o tipo "Religioso" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Religioso",<false> caso contr�rio
	 */
	public boolean existeTipoReligioso();

	/**
	 * Verifica se existe o tipo "Pol�tico" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Pol�tico",<false> caso contr�rio
	 */
	public boolean existeTipoPolitico();

	/**
	 * Verifica se existe o tipo "Musical" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Musical",<false> caso contr�rio
	 */
	public boolean existeTipoMusical();
	
	/**
	 * Verifica se existe o tipo "Carnaval" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Carnaval",<false> caso contr�rio
	 */
	public boolean existeTipoCarnaval();

	/**
	 * Verifica se existe o tipo "Outros" na cole��o de tipos da configura��o
	 * @return <true> se existe o tipo "Outros",<false> caso contr�rio
	 */
	public boolean existeTipoOutros();
}
