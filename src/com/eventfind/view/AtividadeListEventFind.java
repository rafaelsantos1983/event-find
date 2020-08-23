/*
 * EventFind
 *
 * Tipo: AtividadeListEventFind
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.eventfind.controlador.Controlador;

/**
 * Classe principal das ativdades do tipo lita da aplicação
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public abstract class AtividadeListEventFind extends ListActivity implements Handler.Callback {
	
	/**
	 * Evento do botão para: Atualista a Lista de Eventos
	 */
	public static final int ATUALIZA = 0;

	/**
	 * Evento do botão para: Sair da Aplicação
	 */
	public static final int SAIR = 1;
	
	/**
	 * Evento do botão para: Apresentar Tela de Configurações
	 */	
	public static final int CONFIGURACAO = 2;

	/**
	 * Evento do botão para: Apresentar Tela de Informações do Sistema
	 */	
	public static final int INFORMACAO = 3;
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = AtividadeListEventFind.class.getSimpleName();
	

	/**
	 * Responsável por chamar o método do controlador para finalizar todas as chamadas 
	 * ao controlador quando a tela for finalizada
	 * 
	 */
	public void finalizarTodasAsChamadasControlador(Controlador controlador){
		//Quando finalizar a atividade, encerrar as chamadas em aberta com o controlador
		try {
			if(controlador!=null)
				controlador.finalizarTodasAsChamadas();
		} catch (Throwable t) {
			Log.e(TAG, "Falha as finalizadas todas as chamadas ao controlador", t);
		} 
	}
	
	@Override
	public abstract boolean handleMessage(Message msg);
	
	/**
	 * Retorna uma conexão com o controlador
	 * @return Conexão com o controlador
	 */
	public Controlador obterControlador(){
		Controlador controlador = new Controlador(this);
		controlador.addOutboxHandler(new Handler(this));
		return controlador;
	}
	
	/**
	 * Encerrar a aplicação
	 */
	public void encerrarAplicacao() {
		Log.d(TAG, "Encerra a aplicação");
		finish();
	}
	
}
