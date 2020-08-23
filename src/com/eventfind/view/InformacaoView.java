/*
R * EventFind
 *
 * Tipo: InformacaoView
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eventfind.R;

/**
 * Classe que representa a tela de informações do sitema
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class InformacaoView extends AtividadeEventFind {

	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = InformacaoView.class.getSimpleName();
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.informacao);
		
		carregarAbout();
		
		Util.setarCabecalho(this);

        // seta o degradê criado para o layout atual
        this.getWindow().setBackgroundDrawable(Util.DegradeAzulCinza());            
	}

	/**
	 * @see com.eventfind.view.AtividadeListEventFind#handleMessage(android.os.Message)
	 */
	public boolean handleMessage(Message msg) {
		Log.d(TAG, "Recebendo a mensagem: " + msg);
		return true;
	}
	
	/*
	 * Carrega as imagens dos tipos de eventos
	 */
	private void carregarAbout() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);     
        builder.setIcon(R.drawable.ladybug48x48); // ícone de alerta
        builder.setTitle(R.string.nomeAplicacao); //título do caixa de diálogo
        builder.setMessage(R.string.aboutEventFind);            
        builder.setCancelable(false);    
        builder.setView(getCurrentFocus());
        
        //Evento disparado se clicar no botão Sim
        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {               
            public void onClick(DialogInterface dialog, int id) {                     
            	InformacaoView.this.finish(); //Fecha o Activity     
            }            
        });     
        AlertDialog alert = builder.create();     
        alert.show(); //Chama a caixa de diálogo		
	}

}
