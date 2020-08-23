/*
 * EventFind
 *
 * Tipo: ITipoEvento
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.modelo.evento;

import com.eventfind.modelo.IObjetoGenerico;


/**
 * Interface que representa o TIPO DO EVENTO
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface ITipoEvento extends IObjetoGenerico {

	/**
	 * Constantes dos Tipos dos Eventos 
	 */

	public static final int CULTURAL 		= 1;
	public static final int GASTRONOMICO 	= 2;
	public static final int ESPORTIVO 		= 3;
	public static final int RELIGIOSO 		= 4;
	public static final int POLITICO 		= 5;
	public static final int MUSICAL 		= 6;
	public static final int CARNAVAL 		= 7;
	public static final int OUTROS 			= 8;
	
	/**
	 * Obter o valor de descricao
	 * @return Valor de descricao
	 */
	public String getDescricao();
	
	/**
	 * Seta o valor de descricao em descricao
	 * @param descricao Valor a ser setado
	 */
	public void setDescricao(String descricao);

	/**
	 * Obter o valor de codigo
	 * @return Valor de codigo
	 */
	public Integer getCodigo();

	/**
	 * Seta o valor de codigo em codigo
	 * @param codigo Valor a ser setado
	 */
	public void setCodigo(Integer codigo);
	
	/**
	 * Verifica se o Tipo do Evento � "Cultural" 
	 * @return <true> se o tipo do evento por "Cultural", <false> caso contr�rio
	 */
	public boolean tipoCultural();

	/**
	 * Verifica se o Tipo do Evento � "Gastr�nomico" 
	 * @return <true> se o tipo do evento por "Gastr�nomico", <false> caso contr�rio
	 */
	public boolean tipoGastronomico();

	/**
	 * Verifica se o Tipo do Evento � "Esportivo" 
	 * @return <true> se o tipo do evento por "Esportivo", <false> caso contr�rio
	 */
	public boolean tipoEsportivo();

	/**
	 * Verifica se o Tipo do Evento � "Religioso" 
	 * @return <true> se o tipo do evento por "Religioso", <false> caso contr�rio
	 */
	public boolean tipoReligioso();

	/**
	 * Verifica se o Tipo do Evento � "Pol�tico" 
	 * @return <true> se o tipo do evento por "Pol�tico", <false> caso contr�rio
	 */
	public boolean tipoPolitico();

	/**
	 * Verifica se o Tipo do Evento � "Musical" 
	 * @return <true> se o tipo do evento por "Musical", <false> caso contr�rio
	 */
	public boolean tipoMusical();
	
	/**
	 * Verifica se o Tipo do Evento � "Carnaval" 
	 * @return <true> se o tipo do evento por "Carnaval", <false> caso contr�rio
	 */
	public boolean tipoCarnaval();

	/**
	 * Verifica se o Tipo do Evento � "Outros" 
	 * @return <true> se o tipo do evento por "Outros", <false> caso contr�rio
	 */
	public boolean tipoOutros();	
}
