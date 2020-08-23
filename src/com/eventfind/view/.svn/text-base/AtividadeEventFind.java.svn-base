/*
 * EventFind
 *
 * Tipo: AtividadeEventFind
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.eventfind.controlador.Controlador;

/**
 * Classe principal das ativdades da aplicação
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public abstract class AtividadeEventFind extends Activity implements Handler.Callback {
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = AtividadeEventFind.class.getSimpleName();
	

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
			Log.e(TAG, "Falha ao finalizadar todas as chamadas ao controlador", t);
		} 
	}
	
	/**
	 * Processa as mensagens chamadas ao controlador
	 * @mensagem Mensagem
	 */
	public abstract boolean handleMessage(Message mensagem);
	
	/**
	 * Retorna uma conexão com o controlador
	 * @return Conexão com o controlador
	 */
	public Controlador obterControlador(){
		Controlador controlador = new Controlador(this);
		controlador.addOutboxHandler(new Handler(this));
		return controlador;
	}
	
}
