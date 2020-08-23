/*
 * EventFind
 *
 * Tipo: Processador
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.controlador;

import java.util.List;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.IEstado;
import com.eventfind.modelo.evento.IEvento;
import com.eventfind.modelo.evento.ITipoEvento;
import com.eventfind.repositorio.configuracao.IRepositorioConfiguracao;
import com.eventfind.repositorio.configuracao.RepositorioConfiguracao;
import com.eventfind.repositorio.evento.IRepositorioEvento;
import com.eventfind.repositorio.evento.RepositorioEvento;

/**
 * Classe que processa as mensagens que chegam ao controlador
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Processador {
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = Processador.class.getSimpleName();
	
	/**
	 * Referncia ao controlador
	 */
	private Controlador controlador;

	/**
	 * Referncia ao contexto
	 */
	private Context contexto;
	
	/**
	 * Repositório do Evento
	 */
	private static IRepositorioEvento repositorioEvento;

	/**
	 * Respositório da Configuracao
	 */
	private static IRepositorioConfiguracao repositorioConfiguracao;
	
	/**
	 * Construtor
	 * @param controlador Controlador
	 */
	public Processador(Controlador controlador, Context contexto) {
		Log.d(TAG, "Inicializa o Processador, Controlador, seus repositórios e o contexto");
		this.inicializarControlador(controlador);
		this.inicializarContexto(contexto);

		//Adicionar aqui abaixo todo os novos repositorio que devem ser inicializados
		this.inicializarRepositorioEvento(controlador);
		this.inicializarRepositorioConfiguracao(controlador);
	}

	/*
	 * Iniciliza o controlador
	 * Caso já esteja incializado, mantem a mesma instância
	 * @param contexto Contexto
	 */
	private void inicializarControlador(Controlador controla){
		if(controlador == null)
			controlador = controla;
	}
	
	/*
	 * Iniciliza o contexto a ser processador
	 * Caso já esteja incializado, mantem a mesma instância
	 * @param contexto Contexto
	 */
	private void inicializarContexto(Context ctx){
			contexto = ctx;
	}
	
	/*
	 * Iniciliza o repositório do evento
	 * Caso já esteja incializado, mantem a mesma instância
	 * @param controlador Controlador
	 */
	private void inicializarRepositorioEvento(Controlador controlador2){
		if(repositorioEvento == null)
			repositorioEvento = new RepositorioEvento(controlador, contexto);
	}

	/*
	 * Iniciliza o repositório do evento
	 * Caso já esteja incializado, mantem a mesma instância
	 * @param controlador Controlador
	 */
	private void inicializarRepositorioConfiguracao(Controlador controlador){
		if(repositorioConfiguracao == null)
			repositorioConfiguracao = new RepositorioConfiguracao(controlador,contexto);
	}
	
	/**
	 * @see com.eventfind.repositorio.IRepositorio#processarMensagem(android.os.Message)
	 */
	public final boolean processarMensagem(Message mensagem) {
		boolean resultado = false;
		Log.d(TAG, "Processar a mensagem: " + mensagem.what);
		switch (mensagem.what) {
		case Protocolos.CRIAR_BANCO:
			repositorioEvento.criarTabelasECarregar((Context)mensagem.obj);
			resultado = true;
			break;
		case Protocolos.ATUALIZAR_EVENTOS:
			List<IConfiguracao> configs = repositorioConfiguracao.consultarTodasConfiguracoes();
			repositorioEvento.atualizarEventos(configs);
			List<IEvento> eventos = repositorioEvento.consultarTodosOsEventosDeAcordoConfiguracao(configs);
			controlador.notitficarMensagemProcesada(Protocolos.ATUALIZAR_LISTA_EVENTOS, 0, 0, eventos); //Retornar os eventos depois de atualizados 
			resultado = true;
			break;
		case Protocolos.CONSULTAR_EVENTO_PELO_ID:
			IEvento evento = repositorioEvento.consultarEventoPeloId((Long)mensagem.obj);
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_EVENTO, 0, 0, evento); //Retornar o evento consultado no banco de dados
			resultado = true;
			break;
		case Protocolos.CONSULTAR_EVENTOS:
			List<IConfiguracao> configuracoes = repositorioConfiguracao.consultarTodasConfiguracoes();
			List<IEvento> listaEventos = repositorioEvento.consultarTodosOsEventosDeAcordoConfiguracao(configuracoes);
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_EVENTOS, 0, 0, listaEventos); //Retornar todos os evento 
			resultado = true;
			break;
		case Protocolos.CONSULTAR_TODAS_CONFIGURACOES:
			List<IConfiguracao> listaConfiguracoes = repositorioConfiguracao.consultarTodasConfiguracoes();
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_TODAS_CONFIGURAOES, 0, 0, listaConfiguracoes); //Retornar todas as configurações
			resultado = true;
			break;

		case Protocolos.CONSULTAR_TODOS_TIPOS_EVENTOS:
			List<ITipoEvento> listaTiposEventos = repositorioEvento.consultarTodosOsTiposEventos();
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_TODOS_TIPOS_EVENTOS, 0, 0, listaTiposEventos); //Retornar todos os tipos de eventos
			resultado = true;
			break;
		case Protocolos.VERIFICAR_EXISTENCIA_CONFIGURACOES:
			List<IConfiguracao> configus = repositorioConfiguracao.consultarTodasConfiguracoes();
			controlador.notitficarMensagemProcesada(Protocolos.ABRIR_AVISO_FALTA_CONFIGURACOES, 0, 0, !configus.isEmpty()); //Retornar se existe ou não configurações	
			resultado = true;
			break;
		case Protocolos.CONSULTAR_TODOS_ESTADOS:
			List<IEstado> estados = repositorioConfiguracao.consultarTodosEstados();
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_ESTADOS, 0, 0, estados); //Retornar todos os estados	
			resultado = true;
			break;

		case Protocolos.CONSULTAR_CIDADES_POR_ESTADO:
			List<ICidade> cidades = repositorioConfiguracao.consultarTodasCidadesPeloEstado((Long)mensagem.obj);
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_CIDADES_POR_ESTADO, 0, 0, cidades); //Retornar todos os estados	
			resultado = true;
			break;
			
		case Protocolos.INSERIR_CONFIGURACAO:
			repositorioConfiguracao.inserirOuAtualizarConfiguracao((IConfiguracao)mensagem.obj);
			List<IConfiguracao> listaConfigs = repositorioConfiguracao.consultarTodasConfiguracoes();
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_TODAS_CONFIGURAOES, 0, 0, listaConfigs); //Retornar todas as configurações
			resultado = true;
			break;

		case Protocolos.EXCLUIR_CONFIGURACAO:
			repositorioConfiguracao.removerConfiguracao((Long)mensagem.obj);
			List<IConfiguracao> configurs = repositorioConfiguracao.consultarTodasConfiguracoes();
			controlador.notitficarMensagemProcesada(Protocolos.RETORNAR_TODAS_CONFIGURAOES, 0, 0, configurs); //Retornar todas as configurações
			resultado = true;
			break;

		default:
			resultado = false;
		}
		
		return resultado;
	}
}
