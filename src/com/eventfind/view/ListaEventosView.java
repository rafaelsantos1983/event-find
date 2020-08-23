/*
 * EventFind
 *
 * Tipo: ListaEventosView
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.eventfind.R;
import com.eventfind.controlador.Controlador;
import com.eventfind.controlador.Protocolos;
import com.eventfind.modelo.evento.IEvento;
import com.eventfind.view.adapter.EventoListAdapter;

/**
 * Classe que representa a tela da lista de eventos do sistema
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class ListaEventosView extends AtividadeListEventFind{
	
	/**
	 * Barra de atualiza��o dos eventos no servidor
	 */
	private ProgressDialog progressDialog;
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = ListaEventosView.class.getSimpleName();
	
	/**
	 * Controlador
	 */
	private Controlador controlador;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		super.onCreate(savedInstanceState);
		
		controlador = obterControlador();
		
		//Verificar se existe confgura��es cadastradas
    	Message mensagem = new Message();
    	mensagem.what = Protocolos.VERIFICAR_EXISTENCIA_CONFIGURACOES;
    	controlador.obterCaixaEntrada().sendMessage(mensagem);
	}


	/**
	 * @see com.eventfind.view.AtividadeListEventFind#handleMessage(android.os.Message)
	 */
	@SuppressWarnings("unchecked")
	public boolean handleMessage(final Message msg) {
		Log.d(TAG, "Recebendo a mensagem: " + msg);
		
		boolean resultado = false;
		
		switch (msg.what) {
			case Protocolos.ABRIR_AVISO_FALTA_CONFIGURACOES:
				Boolean existeConfiguracoes = (Boolean)msg.obj;
				Log.d(TAG, "Verificar se existem configura��es cadastradas");
				
				//Se existir configura��es, consultar os eventos no banco de acordo com as confogura��es
				System.out.println("existeConfiguracoes="+existeConfiguracoes);
				if(existeConfiguracoes){
					Log.d(TAG, "Existem cconfigura��es, consultar os eventos");
					controlador = obterControlador();
					//solicita ao controlador
					Message mensagem = new Message();
					mensagem.what = Protocolos.CONSULTAR_EVENTOS;
					controlador.obterCaixaEntrada().sendMessage(mensagem);
					
				}else{//Se n�o existir as configura��es, sugeir se deseja ir para a tela de configura��es	
					Log.d(TAG, "N�o existem configura��es cadastradas");
					AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEventosView.this);
					//alerta.setIcon(R.drawable.interrogacao);
					alerta.setTitle("N�o existem configura��es");
					alerta.setMessage("Deseja cadastrar agora?");

					// M�todo executado se escolher Sim
					alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							controlador = obterControlador();
							//solicita ao controlador
							Message mensagem = new Message();
							mensagem.what = Protocolos.CONSULTAR_EVENTOS;
							controlador.obterCaixaEntrada().sendMessage(mensagem);
							
							startActivity(new Intent("CONFIGURACAO"));
						}
					});
					// M�todo executado se escolher N�o
					alerta.setNegativeButton("N�o", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							controlador = obterControlador();
							//solicita ao controlador
							Message mensagem = new Message();
							mensagem.what = Protocolos.CONSULTAR_EVENTOS;
							controlador.obterCaixaEntrada().sendMessage(mensagem);	
						}
					});
					
					// Exibe o alerta de confirma��o
					alerta.show();
				}
				
				resultado = true;
				break;
			case Protocolos.ATUALIZAR_LISTA_EVENTOS:
				Log.d(TAG, "Atualizar a lista de eventos");
				
				//Encerrado o Progress Dialog
				progressDialog.dismiss();
				setListAdapter(new EventoListAdapter(this, (List<IEvento>) msg.obj));
				resultado = true;
				break;
			case Protocolos.RETORNAR_EVENTOS:
				Log.d(TAG, "Aprensentar os eventos");

				// seta o degrad� criado para o layout atual
				this.getWindow().setBackgroundDrawable(Util.DegradeAzulCinza());            
		        
				// Adaptador de lista customizado para cada linha
				setListAdapter(new EventoListAdapter(this, (List<IEvento>) msg.obj));
				
				Util.setarCabecalho(this);
				
				resultado = true;
				break;			
			default:
				break;
		}
		return resultado;
	}

	/**
	 * @see android.app.Activity#onDestroy()
	 */
	protected void onDestroy() {
		Log.d(TAG, "Finalizando a view.");
		//ao finalizar a tela, todas as chamadas s�o removidas do controlador
		super.finalizarTodasAsChamadasControlador(controlador);

		super.onDestroy();
	}
	
	/**
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		IEvento evento =  (IEvento) this.getListAdapter().getItem(position);

		//Abrir a tela de detalhemnto, enviando o Id do Evento
		Intent it = new Intent("DETALHE_EVENTO");
		it.putExtra("eventoId", evento.getId());
		
		startActivityForResult(it, 0);
	}
	
	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		//Op��o Menu - Atualizar Lista de Eventos
		MenuItem item = menu.add(0, ATUALIZA, 0, "Atualizar");
		item.setIcon(R.drawable.refresh);

		//Op��o Menu - Abrir Tela Configura��o
		item = menu.add(0, CONFIGURACAO, 0, "Configura��o");
		item.setIcon(R.drawable.configuracao);

		//Op��o Menu - Informa��es
		item = menu.add(0, INFORMACAO, 0, "Informa��o");
		item.setIcon(R.drawable.informacao);

		//Op��o Menu - Sair da Aplica��o
		item = menu.add(0, SAIR, 0, "Sair");
		item.setIcon(R.drawable.sair);

		return true;
	}
	
	/**
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	public boolean onMenuItemSelected(int featuredId, MenuItem item) {
		switch (item.getItemId()) {
			case ATUALIZA:
				if (!Util.existeConexaoInternet(this)){
					Log.d(TAG,"Tentado atualizar os eventos junto ao servidor, por�m sem conex�o com rede de dados");
					Toast.makeText(ListaEventosView.this, "N�o h� conex�o.", Toast.LENGTH_SHORT).show();
  			    }else{
  			    	progressDialog = ProgressDialog.show(this, "Event Find", "Atualizando...", true,false);

  			    	controlador = obterControlador();
  			    	Message mensagem = new Message();
  			    	mensagem.what = Protocolos.ATUALIZAR_EVENTOS;
  			    	controlador.obterCaixaEntrada().sendMessage(mensagem);
  			    }	
		    	return true;
			case CONFIGURACAO:
				startActivity(new Intent("CONFIGURACAO"));
				
				return true;
			case INFORMACAO:
				startActivity(new Intent("INFORMACAO"));
				
				return true;				
			case SAIR:
				super.encerrarAplicacao(); //encerrar a aplica��o
		}
		
		return false;
	}
	
	/**
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	protected void onActivityResult(int codigo, int codigoRetorno, Intent it) {
		super.onActivityResult(codigo, codigoRetorno, it);
	}
}
