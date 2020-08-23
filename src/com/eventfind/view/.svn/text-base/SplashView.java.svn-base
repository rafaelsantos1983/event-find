/*
 * EventFind
 *
 * Tipo: SplashView
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.eventfind.R;
import com.eventfind.controlador.Controlador;
import com.eventfind.controlador.Protocolos;

/**
 * Classe que representa a tela de abertura do sistema (Splash)
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class SplashView extends AtividadeEventFind {

	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = SplashView.class.getSimpleName();
	
	/**
	 * Controlador
	 */
	private Controlador controlador;
	
	/**
	 * Tempo para a aplicação ser inicializada
	 */
	private final int DELAY = 5000; //3 segundos

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
        requestWindowFeature(Window.FEATURE_NO_TITLE); //removendo a barra de cabeçalho no splash
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
		
		Toast.makeText(this, "Aguarde, carregando a aplicação...", Toast.LENGTH_SHORT).show();
		
    	controlador = obterControlador();
    	//Criar o banco, se necessário
    	Message mensagem = new Message();
    	mensagem.what = Protocolos.CRIAR_BANCO;
    	mensagem.obj = SplashView.this;
    	controlador.obterCaixaEntrada().sendMessage(mensagem);
		
        //o postDelayed faz com que a nossa atividade principal seja startada depois do intervalo especificado na constante (DURACAO_DA_TELA)
        new Handler().postDelayed(new Runnable(){           
            public void run() {
            	
                //Abrir a Lista de Eventos
    			startActivity(new Intent("LISTA_EVENTOS"));
        		
        		//Encerrando a View do Splash
        		finish();
            }
        },DELAY); 
        
        // seta o degradê criado para o layout atual
        this.getWindow().setBackgroundDrawable(Util.DegradeAzulCinza());             
	}

	@Override
	public boolean handleMessage(Message msg) {
		Log.d(TAG, "Recebendo a mensagem: " + msg);
		 
		return false;
	}
	
	
	
	@Override
	protected void onDestroy() {
		Log.d(TAG, "Finalizando a view.");
		//ao finalizar a tela, todas as chamadas são removidas do controlador
		super.finalizarTodasAsChamadasControlador(controlador);
		
		super.onDestroy();
	}

}