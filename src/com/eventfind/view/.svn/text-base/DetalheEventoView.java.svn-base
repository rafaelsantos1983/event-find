/*
R * EventFind
 *
 * Tipo: DetalheEventoView
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eventfind.R;
import com.eventfind.controlador.Controlador;
import com.eventfind.controlador.Protocolos;
import com.eventfind.modelo.evento.IEvento;

/**
 * Classe que representa a tela de detalhamento do evento do sistema
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class DetalheEventoView extends AtividadeEventFind {

	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = DetalheEventoView.class.getSimpleName();
	
	/**
	 * Controlador
	 */
	private Controlador controlador;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.evento_detalhes);
		
		Util.setarCabecalho(this);
		
		controlador = obterControlador();
		
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			Long eventoId = extras.getLong("eventoId");

			//Apartir do eventoId, consulta o evento
			if (eventoId != null) {
		    	
				//solicita ao controlador
		    	Message mensagem = new Message();
		    	mensagem.what = Protocolos.CONSULTAR_EVENTO_PELO_ID;
		    	mensagem.obj = eventoId; //encapsula o id do evento na mensagem para ser processada
		    	controlador.obterCaixaEntrada().sendMessage(mensagem);
			}
		}

        // seta o degradê criado para o layout atual
        this.getWindow().setBackgroundDrawable(Util.DegradeAzulCinza());            
	}

	/**
	 * @see com.eventfind.view.AtividadeListEventFind#handleMessage(android.os.Message)
	 */
	public boolean handleMessage(Message msg) {
		Log.d(TAG, "Recebendo a mensagem: " + msg);
		boolean result = false; 
		switch (msg.what) {
			case Protocolos.RETORNAR_EVENTO:
				IEvento evento = (IEvento) msg.obj; //o evento após ser consultado no banco de dados
			
				//carrega dados do Detalhe
				carregarDetalhesDoEvento(evento);
	
				result =  true;
				break;
			default:
				break;	
		}
		return result;
	}

	/*
	 * Carrega na tela de detalhemento todas as informações para o evento.
	 * @param evento Evento 
	 */
	private void carregarDetalhesDoEvento(final IEvento evento) {
		ImageView tipoImagem = (ImageView) findViewById(R.id.imgTipo_detalhe);
		TextView textTipo = (TextView) findViewById(R.id.tipo_detalhe);
		TextView textTitulo = (TextView) findViewById(R.id.titulo_detalhe);
		TextView textDataHora = (TextView) findViewById(R.id.data_detalhe);
		TextView textDescricao = (TextView) findViewById(R.id.descricao_detalhe);
		TextView textLogradouro = (TextView) findViewById(R.id.logradouro_detalhe);
		TextView textBairro = (TextView) findViewById(R.id.bairro_detalhe);
		TextView textCidade = (TextView) findViewById(R.id.cidade_detalhe);
		TextView textCEP = (TextView) findViewById(R.id.cep_detalhe);
		TextView textTelefone = (TextView) findViewById(R.id.telefone_detalhe);
		TextView textReferencia = (TextView) findViewById(R.id.referencia_detalhe);
		TextView textLocalEvento = (TextView) findViewById(R.id.localEvento_detalhe);
		TextView textNomeEmpresaPromove = (TextView) findViewById(R.id.nomeEmpresaPromove_detalhe);
		TextView textSite = (TextView) findViewById(R.id.site_detalhe);
		TextView textEmail = (TextView) findViewById(R.id.email_detalhe);
//		ImageView imgFacebook = (ImageView) findViewById(R.id.imgFacebook);
//		ImageView imgTwitter = (ImageView) findViewById(R.id.imgTwitter);
//		ImageView imgOrkut = (ImageView) findViewById(R.id.imgOrkut);
		
		
		// Atualiza a imagem para a imagem do Evento
		// A imagem é definda por um recurso no @drawable
		tipoImagem.setImageResource(evento.getImagem());
		textTipo.setText(evento.getTipoEvento().getDescricao());
		textTitulo.setText(evento.getTitulo());
		textDataHora.setText(Util.obterDescricaoData(evento.getData(), Util.DDMMYYYY) + "  " + evento.getHora());
		textDescricao.setText(evento.getDescricao());
		String complemento = Util.ehNuloOuBranco(evento.getComplemento()) ? "" : " - "+evento.getComplemento();
		textLogradouro.setText(evento.getLogradouro() +", "+ evento.getNumero() + complemento);
		textBairro.setText(evento.getBairro());
		textCidade.setText(evento.getCidade().getCidadeFormatadaComEstado());
		textCEP.setText(evento.getCep());
		textTelefone.setText("("+evento.getDdd()+") "+evento.getTelefone());
		textReferencia.setText(evento.getReferencia());
		textLocalEvento.setText(evento.getLocalEvento());
		textNomeEmpresaPromove.setText(evento.getNomeEmpresaPromove());
		textSite.setText(evento.getSite());
		textEmail.setText(evento.getEmail());
//		imgFacebook.setImageResource(R.drawable.facebook);
//		imgTwitter.setImageResource(R.drawable.twitter);
//		imgOrkut.setImageResource(R.drawable.orkut);
		
		ImageButton btnInternet = (ImageButton) findViewById(R.id.btnSite);
		btnInternet.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getSite())));
			}
		});		
		
		ImageButton btnFaceBook = (ImageButton) findViewById(R.id.btnFacebook);
		btnFaceBook.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getFacebook())));
			}
		});
		
		ImageButton btnTwitter = (ImageButton) findViewById(R.id.btnTwitter);
		btnTwitter.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getTwitter())));
			}
		});

		ImageButton btnOrkut = (ImageButton) findViewById(R.id.btnOrkut);
		btnOrkut.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getOrkut())));
			}
		});		
	
		ImageButton btnMap = (ImageButton) findViewById(R.id.btnMap);
		btnMap.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Toast.makeText(DetalheEventoView.this, "Carregando a visualização do evento no mapa ...", Toast.LENGTH_SHORT).show();
//				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getMap())));
			}
		});		
	}
	
	
	
	@Override
	protected void onDestroy() {
		Log.d(TAG, "Finalizando a view.");
		//ao finalizar a tela, todas as chamadas são removidas do controlador
		super.finalizarTodasAsChamadasControlador(controlador);

		super.onDestroy();
	}

}
