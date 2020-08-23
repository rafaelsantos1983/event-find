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

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;


/**
 * Controlador do negócio Eventfind
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Controlador {

	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = Controlador.class.getSimpleName();
	
	/** Thread responsável pelo processamento das Mensagens que chegam na "Caixa de Entrada" */
	private final HandlerThread executorMensagem;

	/** 
	 * "Agendador de Mensagem" 
	 * Handler responsável por agendar e executar as mensagens solicitadas ao controlador 
	 */
	private final Handler agendadorMensagem;
	
	/** Caixa de Sáida(Lista de Mensagens) com as respostas do controlador */
	private final List<Handler> caixaSaida = new ArrayList<Handler>();
	
	/** Processador das mensagens ao controlador */
	private Processador processador;
	
	/**
	 * Constroe um Controlador
	 */
	public Controlador(Context contexto) {
		this.executorMensagem = new HandlerThread("Executor das Mensagens ao Controlador"); // Inicializa o executor das mensagens ao controlador
		this.executorMensagem.start();

		processador = new Processador(this,contexto);
		
		//Inicializa o "Agendador das Mensagens" no controlador
		//Cada mensagem que chegará ao controlador, será executado pelo agendador
		this.agendadorMensagem = new Handler(executorMensagem.getLooper()) {
			
			/** @see android.os.Handler#handleMessage(android.os.Message) */
			public void handleMessage(Message msg) {
				Controlador.this.processadorMensagem(msg);//metodo implementado no controlador, para processar as mensagens 
			}
		};
	}
	
	/*
	 * Processa as mensagens chamadas ao controlador
	 * @mensagem Mensagem
	 */
	private void processadorMensagem(Message mensagem) {
		Log.d(TAG, "Recebendo a mensagem: " + mensagem);
		
		if (!processador.processarMensagem(mensagem)) {
			Log.w(TAG, "Mensagem : " + mensagem + ", não processada.");
		}
	}
	
	/**
	 * Finaliza todas as chamadas ao controlador, liberado as chamadas ao mesmo
	 */
	public final void finalizarTodasAsChamadas() {
		// ask the inbox thread to exit gracefully
		executorMensagem.getLooper().quit();
	}

	/**
	 * Obter a caixa de entrada das mensagens
	 * @return Caixa de Entrada
	 */
	public final Handler obterCaixaEntrada() {
		return agendadorMensagem;
	}
	
	/**
	 * Adiciona uma mensagem na "Caixa de Saída", 
	 * que será utilizado para retornar está mensagem do controlador
	 * @param handler
	 */
	public final void addOutboxHandler(Handler handler) {
		caixaSaida.add(handler);
	}

	/**
	 * Remove uma mensagem na "Caixa de Saída",
	 * @param handler
	 */
	public final void removeOutboxHandler(Handler handler) {
		caixaSaida.remove(handler);
	}
	
	/**
	 * Notifica ao controlador que a mensagem foi processada
	 * Quando uma mensagem é processada, a resposta e enviada para a caixa de saída
	 * que irá retornar ao quem solicitou a mensagem 
	 * @param what Constante da Mensagem
	 * @param arg1
	 * @param arg2
	 * @param obj
	 */
	public final void notitficarMensagemProcesada(int what, int arg1, int arg2, Object obj) {
		if (caixaSaida.isEmpty()) {
			Log.w(TAG, String.format("Não existem mensagem a mensagem (%d) da caixa de saída", what));
		} else {
			for (Handler handler : caixaSaida) {
				Message msg = Message.obtain(handler, what, arg1, arg2, obj);
				msg.sendToTarget();
			}
		}
	}
}