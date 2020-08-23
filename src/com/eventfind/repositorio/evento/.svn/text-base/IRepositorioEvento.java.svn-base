/*
 * EventFind
 *
 * Tipo: IRepositorioEvento
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio.evento;

import java.util.List;

import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.IEvento;
import com.eventfind.modelo.evento.ITipoEvento;
import com.eventfind.repositorio.IRepositorio;

/**
 * Interface que trata a manipulação dos EVENTOS com a base de dados
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface IRepositorioEvento extends IRepositorio{

	/**
	 * Atualiza os eventos do banco de dados, conecta-se ao serbvidor e obtendo os novos eventos disponíveis
	 * @param configuracoes Lista com as configurações para apresentar os eventos
	 */
	public void atualizarEventos(List<IConfiguracao> configuracoes);

	/**
	 * Consulta o evento do Id
	 * @param id Id do Evento
	 */
	public IEvento consultarEventoPeloId(Long id);

	/**
	 * Retorna todos os eventos
	 * @return Todos os eventos
	 */
	public List<IEvento> consultarTodosOsEventos() ;

	/**
	 * Retorna todos os eventos de acordo com as configurações
	 * São consultadas os eventos obdecendo as configurações
	 * @param configuracoes Configuracoes
	 * @return Os eventos
	 */
	public List<IEvento> consultarTodosOsEventosDeAcordoConfiguracao(List<IConfiguracao> configuracoes) ;
	
	/**
	 * Retorna todos os tipos de eventos
	 * @return Todos os tipos de eventos
	 */
	public List<ITipoEvento> consultarTodosOsTiposEventos();
	
	/**
	 * Remover o evento através do ID
	 * @param eventoId ID da Configuração
	 */
	public void removerEvento(Long eventoId);	
	
	/**
	 * Salvar ou Inserir um EVENTO
	 * @param evento Evento
	 */
	public void inserirOuAtualizarEvento(IEvento evento);	
}
