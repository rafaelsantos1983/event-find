/*
 * EventFind
 *
 * Tipo: IRepositorioConfiguracao
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio.configuracao;

import java.util.List;

import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.IEstado;
import com.eventfind.repositorio.IRepositorio;

/**
 * Interface que trata a manipulação das CONFIGURAÇÕES com a base de dados
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface IRepositorioConfiguracao extends IRepositorio{

	/**
	 * Consulta uma configuração através do ID
	 * @param id Id da Configuração
	 */
	public IConfiguracao consultarConfiguracaoPeloId(Long id);

	/**
	 * Retorna todos as configurações
	 * @return Todos as configurações
	 */
	public List<IConfiguracao> consultarTodasConfiguracoes();
	
	/**
	 * Remover a configuração através do ID
	 * @param configuracaoId ID da Configuração
	 */
	public void removerConfiguracao(Long configuracaoId);	
	
	/**
	 * Salvar ou Inserir uma Configuracao
	 * @param evento Evento
	 */
	public void inserirOuAtualizarConfiguracao(IConfiguracao configuracao);
	
	/**
	 * Consultar todas as cidades apartir do estado
	 * @param estadoId ID do estado 
	 * @return Todas as cidades
	 */
	public List<ICidade> consultarTodasCidadesPeloEstado(Long estadoId);
	
	/**
	 * Consultar todos os estados do banco 
	 * @return Todos os estados
	 */
	public List<IEstado> consultarTodosEstados();	
}
