/*
 * EventFind
 *
 * Tipo: ConfiguracaoView
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eventfind.R;
import com.eventfind.controlador.Controlador;
import com.eventfind.controlador.Protocolos;
import com.eventfind.modelo.configuracao.Configuracao;
import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.IEstado;
import com.eventfind.modelo.evento.ITipoEvento;
import com.eventfind.modelo.evento.TipoEvento;

/**
 * Classe que representa a tela de configuração do sitema 
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class ConfiguracaoView extends AtividadeListEventFind {

	/**
	 * Spinnner com os estados
	 */
	public Spinner spinnerEstados  = null;
	
	/**
	 * Spinner com as cidades
	 */
	public Spinner spinnerCidades = null;
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = ConfiguracaoView.class.getSimpleName();
	
	/**
	 * Controlador
	 */
	private Controlador controlador;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		controlador = obterControlador();

        // seta o degradê criado para o layout atual
        this.getWindow().setBackgroundDrawable(Util.DegradeAzulCinza());            

		setContentView(R.layout.configuracao);
		
		carregarAsImagensTiposEventos();

		Util.setarCabecalho(this);
		
		Message mensagem = new Message();
    	mensagem.what = Protocolos.CONSULTAR_TODAS_CONFIGURACOES;
    	controlador.obterCaixaEntrada().sendMessage(mensagem);

    	Message mensagem2 = new Message();
    	mensagem2.what = Protocolos.CONSULTAR_TODOS_ESTADOS;
    	controlador.obterCaixaEntrada().sendMessage(mensagem2);
	}

	/**
	 * @see com.eventfind.view.AtividadeListEventFind#handleMessage(android.os.Message)
	 */
	@SuppressWarnings("unchecked")
	public boolean handleMessage(Message msg) {
		boolean resultado = false;
		
		switch (msg.what) {
			case Protocolos.RETORNAR_TODAS_CONFIGURAOES:
				Log.d(TAG, "Apresentar as configurações");
				setListAdapter(new ConfiguracaoListAdapter((List<IConfiguracao>) msg.obj));
				resultado = true;
				break;	
			case Protocolos.RETORNAR_ESTADOS:
				Log.d(TAG, "Carregando todos os estados");
				List<IEstado> estados = (List<IEstado>)msg.obj;
				
				ArrayAdapter<IEstado> arrayEstados = new ArrayAdapter<IEstado>(this,android.R.layout.simple_spinner_item, estados);
				spinnerEstados = (Spinner) findViewById(R.id.estados);
				arrayEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerEstados.setAdapter(arrayEstados);

				spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					/**
					 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
					 */
					public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
						IEstado estado = (IEstado) spinnerEstados.getSelectedItem(); 
						
				    	Message mensagem = new Message();
				    	mensagem.obj = estado.getId();
				    	mensagem.what = Protocolos.CONSULTAR_CIDADES_POR_ESTADO;
				    	controlador.obterCaixaEntrada().sendMessage(mensagem);
					}

					/**
					 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
					 */
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
				resultado = true;
				break;	
			case Protocolos.RETORNAR_CIDADES_POR_ESTADO:
				Log.d(TAG, "Apresentar as cidades de acordo com o estado");

				List<ICidade> cidades = (List<ICidade>)msg.obj;
				ArrayAdapter<ICidade> arrayCidades = new ArrayAdapter<ICidade>(this,android.R.layout.simple_spinner_item, cidades);
				
				spinnerCidades = (Spinner) findViewById(R.id.cidades);
				arrayCidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				spinnerCidades.setAdapter(arrayCidades);

				resultado = true;
				break;					
			default:
				break;
		}
		return resultado;
	}
	
	
	/*
	 * Carrega as imagens dos tipos de eventos
	 */
	private void carregarAsImagensTiposEventos() {
		//botoões
		Button btnIncluirConfiguracao = (Button)findViewById(R.id.butaoIncluir);
		btnIncluirConfiguracao.setOnClickListener(incluirConfiguracao);
		
		//textos
		TextView textInstrucaoConfiguracaoIncluir = (TextView) findViewById(R.id.instrucaoConfiguracaoIncluir);
		TextView textInstrucaoConfiguracaoExcluir = (TextView) findViewById(R.id.instrucaoConfiguracaoExcluir);
		textInstrucaoConfiguracaoIncluir.setText(R.string.instrucaoConfiguracaoIncluir);
		textInstrucaoConfiguracaoExcluir.setText(R.string.instrucaoConfiguracaoExcluir);

		//imagens
		ImageView imgTipoCarnaval = (ImageView) findViewById(R.id.imgTipoCarnaval);
		ImageView imgTipoCultural = (ImageView) findViewById(R.id.imgTipoCultural);
		ImageView imgTipoEsportivo = (ImageView) findViewById(R.id.imgTipoEsportivo);
		ImageView imgTipoGastronomico = (ImageView) findViewById(R.id.imgTipoGastronomico);
		ImageView imgTipoMusical = (ImageView) findViewById(R.id.imgTipoMusical);
		ImageView imgTipoPolitico = (ImageView) findViewById(R.id.imgTipoPolitico);
		ImageView imgTipoReligioso = (ImageView) findViewById(R.id.imgTipoReligioso);
		ImageView imgTipoOutros = (ImageView) findViewById(R.id.imgTipoOutros);
		
		imgTipoCarnaval.setImageResource(R.drawable.carnaval48x48);
		imgTipoCultural.setImageResource(R.drawable.cultural48x48);
		imgTipoEsportivo.setImageResource(R.drawable.esportivo48x48);
		imgTipoGastronomico.setImageResource(R.drawable.gastronomico48x48);
		imgTipoMusical.setImageResource(R.drawable.musical48x48);
		imgTipoPolitico.setImageResource(R.drawable.politico48x48);
		imgTipoReligioso.setImageResource(R.drawable.religioso48x48);
		imgTipoOutros.setImageResource(R.drawable.outros48x48);
	}

	/*
	 * Ação do Botão Incluir Configuração
	 * Salva  configuração, e a incluir na lista
	 */
	private OnClickListener incluirConfiguracao =  new OnClickListener() {
        
        /**
         * @see android.view.View.OnClickListener#onClick(android.view.View)
         */
        public void onClick(View v) {
        	IConfiguracao configuracao = new Configuracao();
        	
        	boolean seleciouAlgumTipoEvento = false;
        	
        	//Tipo Carnaval
        	CheckBox tipoEventoCarnavalCheck = (CheckBox) findViewById(R.id.tipoEventoCarnavalCheck);
        	if(tipoEventoCarnavalCheck.isChecked()){
        		ITipoEvento tipoEventoCarnaval = new TipoEvento();
        		tipoEventoCarnaval.setCodigo(ITipoEvento.CARNAVAL);
            	configuracao.addTipoEvento(tipoEventoCarnaval);
            	
            	seleciouAlgumTipoEvento = true;
        	}

        	//Tipo Cultural
			CheckBox tipoEventoCulturalCheck = (CheckBox) findViewById(R.id.tipoEventoCulturalCheck); 
        	if(tipoEventoCulturalCheck.isChecked()){
        		ITipoEvento tipoEventoCultural = new TipoEvento();
        		tipoEventoCultural.setCodigo(ITipoEvento.CULTURAL);
            	configuracao.addTipoEvento(tipoEventoCultural);
            	
            	seleciouAlgumTipoEvento = true;
        	}

        	//Tipo Esportivo
			CheckBox tipoEventoEsportivoCheck = (CheckBox) findViewById(R.id.tipoEventoEsportivoCheck); 
        	if(tipoEventoEsportivoCheck.isChecked()){
        		ITipoEvento tipoEventoEsportivo = new TipoEvento();
        		tipoEventoEsportivo.setCodigo(ITipoEvento.ESPORTIVO);
            	configuracao.addTipoEvento(tipoEventoEsportivo);
            	
            	seleciouAlgumTipoEvento = true;
        	}

        	//Tipo Gastrônomico
			CheckBox tipoEventoGastronomicoCheck = (CheckBox) findViewById(R.id.tipoEventoGastronomicoCheck); 
        	if(tipoEventoGastronomicoCheck.isChecked()){
        		ITipoEvento tipoEventoGastronomico = new TipoEvento();
        		tipoEventoGastronomico.setCodigo(ITipoEvento.GASTRONOMICO);
            	configuracao.addTipoEvento(tipoEventoGastronomico);
            	
            	seleciouAlgumTipoEvento = true;
        	}

        	//Tipo Musical
			CheckBox tipoEventoMusicalCheck = (CheckBox) findViewById(R.id.tipoEventoMusicalCheck);
        	if(tipoEventoMusicalCheck.isChecked()){
        		ITipoEvento tipoEventoMusical = new TipoEvento();
        		tipoEventoMusical.setCodigo(ITipoEvento.MUSICAL);
            	configuracao.addTipoEvento(tipoEventoMusical);
            	
            	seleciouAlgumTipoEvento = true;
        	}
        	
        	//Tipo Político
	        CheckBox tipoEventoPoliticoCheck = (CheckBox) findViewById(R.id.tipoEventoPoliticoCheck); 
        	if(tipoEventoPoliticoCheck.isChecked()){
        		ITipoEvento tipoEventoPolitico = new TipoEvento();
        		tipoEventoPolitico.setCodigo(ITipoEvento.POLITICO);
            	configuracao.addTipoEvento(tipoEventoPolitico);
            	
            	seleciouAlgumTipoEvento = true;
        	}
        	
        	//Tipo Religioso
	        CheckBox tipoEventoReligiosoCheck = (CheckBox) findViewById(R.id.tipoEventoReligiosoCheck); 
        	if(tipoEventoReligiosoCheck.isChecked()){
        		ITipoEvento tipoEventoReligioso = new TipoEvento();
        		tipoEventoReligioso.setCodigo(ITipoEvento.RELIGIOSO);
            	configuracao.addTipoEvento(tipoEventoReligioso);
            	
            	seleciouAlgumTipoEvento = true;
        	}
			
        	//Tipo Outros
        	CheckBox tipoEventoOutrosCheck = (CheckBox) findViewById(R.id.tipoEventoOutrosCheck);
        	if(tipoEventoOutrosCheck.isChecked()){
        		ITipoEvento tipoEventoOutros = new TipoEvento();
        		tipoEventoOutros.setCodigo(ITipoEvento.OUTROS);
            	configuracao.addTipoEvento(tipoEventoOutros);
            	
            	seleciouAlgumTipoEvento = true;
        	}

        	//Cidade
        	ICidade cidade = (ICidade) spinnerCidades.getSelectedItem();
        	configuracao.setCidade(cidade);
        	if(!seleciouAlgumTipoEvento){
        		Toast.makeText(ConfiguracaoView.this, "Selecione pelo menos um tipo de evento.", Toast.LENGTH_SHORT).show();
        	}else{
		    	Message mensagem = new Message();
		    	mensagem.obj = configuracao;
		    	mensagem.what = Protocolos.INSERIR_CONFIGURACAO;
		    	controlador.obterCaixaEntrada().sendMessage(mensagem);
        	}
        }
    };
    
	/**
	 * @see android.app.Activity#onDestroy()
	 */
	protected void onDestroy() {
		Log.d(TAG, "Finalizando a view.");
		//ao finalizar a tela, todas as chamadas são removidas do controlador
		super.finalizarTodasAsChamadasControlador(controlador);

		super.onDestroy();
	}
	

	/**
	 * Classe que representa o adapter para os dados da congiguração
	 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
	 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
	 */
	public class ConfiguracaoListAdapter extends BaseAdapter {
		/**
		 * LayoutInflater
		 */
		private LayoutInflater layoutInflater;
		
		/**
		 * Lista de Evento
		 */
		private List<IConfiguracao> lista;

		/**
		 * @param context
		 */
		public ConfiguracaoListAdapter(List<IConfiguracao> lista) {
			layoutInflater = LayoutInflater.from(ConfiguracaoView.this);
			this.lista = lista;
		}

		/**
		 * @see android.widget.Adapter#getCount()
		 */
		public int getCount() {
			return lista.size();
		}

		/**
		 * @see android.widget.Adapter#getItem(int)
		 */
		public Object getItem(int posicao) {
			IConfiguracao configuracao = lista.get(posicao);
			return configuracao;
		}

		/**
		 * @see android.widget.Adapter#getItemId(int)
		 */
		public long getItemId(int posicao) {
			return posicao;
		}

		/**
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		public View getView(final int posicao, View convertView, ViewGroup parent) {
			// Recupera o Configuracao da posição atual
			final IConfiguracao configuracao = lista.get(posicao);
			
			ViewConfiguracao viewConfiguracao;

			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.configuracao_lista, null);
			
				viewConfiguracao = new ViewConfiguracao();
				viewConfiguracao.setTxtCidadeEstado((TextView) convertView.findViewById(R.id.textoLinha));
				viewConfiguracao.setImgTipoCarnaval((ImageView) convertView.findViewById(R.id.imgLinhaTipo1));
				viewConfiguracao.setImgTipoCultural((ImageView) convertView.findViewById(R.id.imgLinhaTipo2));
				viewConfiguracao.setImgTipoEsportivo((ImageView) convertView.findViewById(R.id.imgLinhaTipo3));
				viewConfiguracao.setImgTipoGastronomico((ImageView) convertView.findViewById(R.id.imgLinhaTipo4));
				viewConfiguracao.setImgTipoMusical((ImageView) convertView.findViewById(R.id.imgLinhaTipo5));
				viewConfiguracao.setImgTipoPolitico((ImageView) convertView.findViewById(R.id.imgLinhaTipo6));
				viewConfiguracao.setImgTipoReligioso((ImageView) convertView.findViewById(R.id.imgLinhaTipo7));
				viewConfiguracao.setImgTipoOutros((ImageView) convertView.findViewById(R.id.imgLinhaTipo8));
				
				viewConfiguracao.setBtnExcluir((Button) convertView.findViewById(R.id.butaoExcluirLinha));
				viewConfiguracao.getBtnExcluir().setOnClickListener(new OnClickListener() {
			        /**
			         * @see android.view.View.OnClickListener#onClick(android.view.View)
			         */
			        public void onClick(View v) {
				    	Message mensagem = new Message();
				    	mensagem.obj = configuracao.getId();
				    	mensagem.what = Protocolos.EXCLUIR_CONFIGURACAO;
				    	controlador.obterCaixaEntrada().sendMessage(mensagem);
			        }
				});
				
				convertView.setTag(viewConfiguracao);
			} else {
				viewConfiguracao = (ViewConfiguracao) convertView.getTag();
			}
			
			// Atualiza o valor do Text da Cidade para a linha do Configurador
			viewConfiguracao.getTxtCidadeEstado().setText(configuracao.getCidade().getCidadeFormatadaComEstado());

			//inicializa todas as tags de imagens com uma imagem transparente
			viewConfiguracao.getImgTipoCarnaval().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoCultural().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoEsportivo().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoGastronomico().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoMusical().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoPolitico().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoReligioso().setImageResource(R.drawable.transparente32x32);
			viewConfiguracao.getImgTipoOutros().setImageResource(R.drawable.transparente32x32);


			for (ITipoEvento tipoEvento : configuracao.getTiposEventos()) {
				if(tipoEvento.tipoCarnaval()){
					viewConfiguracao.getImgTipoCarnaval().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoCultural()) {
					viewConfiguracao.getImgTipoCultural().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoEsportivo()) {
					viewConfiguracao.getImgTipoEsportivo().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoGastronomico()) {
					viewConfiguracao.getImgTipoGastronomico().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoMusical()) {
					viewConfiguracao.getImgTipoMusical().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoPolitico()) {
					viewConfiguracao.getImgTipoPolitico().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoReligioso()) {
					viewConfiguracao.getImgTipoReligioso().setImageResource(configuracao.getImagem(tipoEvento));
				} else if (tipoEvento.tipoOutros()) {
					viewConfiguracao.getImgTipoOutros().setImageResource(configuracao.getImagem(tipoEvento));
				}
			
			}

			
			return convertView;
		}
		
	}
	
	/**
	 * Classe utilizada para visualizar as configurações na listagem
	 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
	 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
	 */
	private class ViewConfiguracao {
			/**
			 * Nome da Cidade/Estado
			 */
			private TextView txtCidadeEstado;
			/**
			 * Imagem do Tipo Carnaval
			 */
			private ImageView imgTipoCarnaval;
			/**
			 * Imagem do Tipo Cultural
			 */
			private ImageView imgTipoCultural;
			/**
			 * Imagem Tipo Esportivo
			 */
			private ImageView imgTipoEsportivo;
			/**
			 * Imagem Tipo Gastroônomico
			 */
			private ImageView imgTipoGastronomico;
			/**
			 * Imagem Tipo Musical
			 */
			private ImageView imgTipoMusical;
			/**
			 * Imagem Tipo Político
			 */
			private ImageView imgTipoPolitico;
			/**
			 * Imagem Tipo Religioso
			 * 
			 */
			private ImageView imgTipoReligioso;
			/**
			 * Imagem Tipo Outros
			 */
			private ImageView imgTipoOutros;
			/**
			 * Botão de exclusão da configuração
			 */
			private Button btnExcluir;
			/**
			 * Obter o valor de txtCidadeEstado
			 * @return Valor de txtCidadeEstado
			 */
			public TextView getTxtCidadeEstado() {
				return txtCidadeEstado;
			}
			/**
			 * Seta o valor de txtCidadeEstado em txtCidadeEstado
			 * @param txtCidadeEstado Valor a ser setado
			 */
			public void setTxtCidadeEstado(TextView txtCidadeEstado) {
				this.txtCidadeEstado = txtCidadeEstado;
			}
			/**
			 * Obter o valor de imgTipoCarnaval
			 * @return Valor de imgTipoCarnaval
			 */
			public ImageView getImgTipoCarnaval() {
				return imgTipoCarnaval;
			}
			/**
			 * Seta o valor de imgTipoCarnaval em imgTipoCarnaval
			 * @param imgTipoCarnaval Valor a ser setado
			 */
			public void setImgTipoCarnaval(ImageView imgTipoCarnaval) {
				this.imgTipoCarnaval = imgTipoCarnaval;
			}
			/**
			 * Obter o valor de imgTipoCultural
			 * @return Valor de imgTipoCultural
			 */
			public ImageView getImgTipoCultural() {
				return imgTipoCultural;
			}
			/**
			 * Seta o valor de imgTipoCultural em imgTipoCultural
			 * @param imgTipoCultural Valor a ser setado
			 */
			public void setImgTipoCultural(ImageView imgTipoCultural) {
				this.imgTipoCultural = imgTipoCultural;
			}
			/**
			 * Obter o valor de imgTipoEsportivo
			 * @return Valor de imgTipoEsportivo
			 */
			public ImageView getImgTipoEsportivo() {
				return imgTipoEsportivo;
			}
			/**
			 * Seta o valor de imgTipoEsportivo em imgTipoEsportivo
			 * @param imgTipoEsportivo Valor a ser setado
			 */
			public void setImgTipoEsportivo(ImageView imgTipoEsportivo) {
				this.imgTipoEsportivo = imgTipoEsportivo;
			}
			/**
			 * Obter o valor de imgTipoGastronomico
			 * @return Valor de imgTipoGastronomico
			 */
			public ImageView getImgTipoGastronomico() {
				return imgTipoGastronomico;
			}
			/**
			 * Seta o valor de imgTipoGastronomico em imgTipoGastronomico
			 * @param imgTipoGastronomico Valor a ser setado
			 */
			public void setImgTipoGastronomico(ImageView imgTipoGastronomico) {
				this.imgTipoGastronomico = imgTipoGastronomico;
			}
			/**
			 * Obter o valor de imgTipoMusical
			 * @return Valor de imgTipoMusical
			 */
			public ImageView getImgTipoMusical() {
				return imgTipoMusical;
			}
			/**
			 * Seta o valor de imgTipoMusical em imgTipoMusical
			 * @param imgTipoMusical Valor a ser setado
			 */
			public void setImgTipoMusical(ImageView imgTipoMusical) {
				this.imgTipoMusical = imgTipoMusical;
			}
			/**
			 * Obter o valor de imgTipoPolitico
			 * @return Valor de imgTipoPolitico
			 */
			public ImageView getImgTipoPolitico() {
				return imgTipoPolitico;
			}
			/**
			 * Seta o valor de imgTipoPolitico em imgTipoPolitico
			 * @param imgTipoPolitico Valor a ser setado
			 */
			public void setImgTipoPolitico(ImageView imgTipoPolitico) {
				this.imgTipoPolitico = imgTipoPolitico;
			}
			/**
			 * Obter o valor de imgTipoReligioso
			 * @return Valor de imgTipoReligioso
			 */
			public ImageView getImgTipoReligioso() {
				return imgTipoReligioso;
			}
			/**
			 * Seta o valor de imgTipoReligioso em imgTipoReligioso
			 * @param imgTipoReligioso Valor a ser setado
			 */
			public void setImgTipoReligioso(ImageView imgTipoReligioso) {
				this.imgTipoReligioso = imgTipoReligioso;
			}
			/**
			 * Obter o valor de imgTipoOutros
			 * @return Valor de imgTipoOutros
			 */
			public ImageView getImgTipoOutros() {
				return imgTipoOutros;
			}
			/**
			 * Seta o valor de imgTipoOutros em imgTipoOutros
			 * @param imgTipoOutros Valor a ser setado
			 */
			public void setImgTipoOutros(ImageView imgTipoOutros) {
				this.imgTipoOutros = imgTipoOutros;
			}
			/**
			 * Obter o valor de btnExcluir
			 * @return Valor de btnExcluir
			 */
			public Button getBtnExcluir() {
				return btnExcluir;
			}
			/**
			 * Seta o valor de btnExcluir em btnExcluir
			 * @param btnExcluir Valor a ser setado
			 */
			public void setBtnExcluir(Button btnExcluir) {
				this.btnExcluir = btnExcluir;
			}
	}
	
}
