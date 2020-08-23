/*
 * EventFind
 *
 * Tipo: Controlador
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.controlador;


/**
 * Interface com as mensagens(protocolos) utilizado para a comunicação das camadas
 * Entre a Visão, Controlador e Modelo
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public interface Protocolos {
	
	/*
	 * ################# Constantes com os protocolos de comunicação entre a camada de Modelo e Controle
	 * 
	 */
	
	/** Protocolo para solicitar a criação do banco */ 
	public static final int CRIAR_BANCO = 1;
	
	/** Protocolo para o consultar o evento */ 
	public static final int CONSULTAR_EVENTO_PELO_ID = 2;
	
	/** Protocolo para atualizar os eventos(conectar-se ao servidor) */ 
	public static final int ATUALIZAR_EVENTOS = 3;
	
	/** Protocolo para consultar todos os eventos na base do eventfind */ 
	public static final int CONSULTAR_EVENTOS = 4;
	
	/** Protocolo para consultar todos os tipos de eventos na base do eventfind */
	public static final int CONSULTAR_TODOS_TIPOS_EVENTOS = 5;
	
	/** Protocolo para verificar se existem configurações na base de dados */
	public static final int VERIFICAR_EXISTENCIA_CONFIGURACOES = 6;
	
	/** Protocolo para consultar todos os estados */
	public static final int CONSULTAR_TODOS_ESTADOS = 7;

	/** Protocolo para consultar as cidades de acordo com o estado */
	public static final int CONSULTAR_CIDADES_POR_ESTADO = 8;
	
	/** Protocolo para consultar todas as configuracoes */
	public static final int CONSULTAR_TODAS_CONFIGURACOES = 9;
	
	/** Protocolo para inserir a configuração */
	public static final int INSERIR_CONFIGURACAO = 10;
	
	/** Protocolo para remover a configuração */
	public static final int EXCLUIR_CONFIGURACAO = 11;	
	
	/*
	 * Constantes com os protocolos de comunicação entre a camada de Controle e Visão
	 * 
	 */
	/** Protocolo para indicar o encerramento da aplicação */
	public static final int ENCERRAR_APLICACAO = 101;
	
	/** Protocolo para indicar a finalização da criação do banco */ 
	public static final int BANCO_CRIADO = 102;	
	
	/** Protocolo para indicar que a lista de eventos será atualizada */
	public static final int ATUALIZAR_LISTA_EVENTOS = 103;
	
	/** Protocolo para enviar o evento para a tela de detalhamento */
	public static final int RETORNAR_EVENTO = 104;
	
	/** Protocolo para enviar todos os eventos para a lista de eventos */
	public static final int RETORNAR_EVENTOS = 104;	
	
	/** Protocolo para enviar todos os tipos de eventos para a lista de eventos */
	public static final int RETORNAR_TODOS_TIPOS_EVENTOS = 105;	

	/** Protocolo para abrir um aviso sobre as configurações  */
	public static final int ABRIR_AVISO_FALTA_CONFIGURACOES = 106;	

	/** Protocolo para retornar todos os estados  */
	public static final int RETORNAR_ESTADOS = 107;

	/** Protocolo para retornar as cidades do estado */
	public static final int RETORNAR_CIDADES_POR_ESTADO = 108;
	
	/** Protocolo para retornar as cidades do estado */
	public static final int RETORNAR_TODAS_CONFIGURAOES = 109;
}

