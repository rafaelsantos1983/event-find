/*
 * EventFind
 *
 * Tipo: ConexaoWebService
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.net.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.IEvento;

/**
 * Classe respons�vel pela conex�o com Web Service
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class ConexaoWebService {
	
	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = ConexaoWebService.class.getSimpleName();

	
	/**
	 * URL do Web Service
	 */
	private static final String URL = "http://www.maniezo.com.br/webservice/soap-server.php";
	
	/**
	 * Servi�o de consulta no Web Service
	 */
	//TODO Definir par�metro do WebService
	private static final String OPERATION = "";
	private static final String NAMESPACE = "";
//	private static final String USERNAME = "";
//	private static final String PASSWORD = "";
	
	
	/**
	 * Consulta os eventos no WebService de acordo com as configura��es pr�-definidas na aplica��o
	 * @param configuracoes Configura��es pr�-definidas
	 * @return Lista com os eventos
	 */
	public static Collection<IEvento> consultarEventos(Collection<IConfiguracao> configuracoes) throws IOException, XmlPullParserException {
		Log.d(TAG, "Consultar os eventos no webservice de acordo com as configura��es");
//		for (IConfiguracao configuracao : configuracoes) {
//			Log.d(TAG,""); //TODO Lista aqui os tipos de eventos que desej-se consultar com as cidades dos eventos
//		}
		
		Collection<IEvento> eventos = new ArrayList<IEvento>();
		
		SoapObject request = new SoapObject(NAMESPACE, OPERATION);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
		
	    //par�metros para realizar a consulta ao web service
	    StringBuffer params = new StringBuffer();
	    //TODO Definir o padr�o para o envios dos par�metros ao servidor
//		params.append(evento.concat("#"));
//		params.append(USERNAME.concat("#"));
//		params.append(PASSWORD.concat("#"));
		request.addProperty("dados_cep", params.toString());
		
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		try {
				httpTransport.call("", envelope);
				String response = (String) envelope.getResponse();
				
				Log.d(TAG,"Reposta do WebService: " + response);
				if (!response.equals("#####")){
					//TODO Fazer a leitura dos eventos obtidos no servi�o e encapisulando numa lista
//					String[] dados = response.split("#");
//				
//					IEvento evento = new Evento();
					return null;
			    }
			} catch (IOException e) {
				Log.e(TAG, "Erro ao buscar os eventos. \n Erro: " + e.toString());
				throw e;
			} catch (XmlPullParserException e) {
				Log.e(TAG, "Erro ao buscar os eventos. \n Erro: " + e.toString());
				throw e;				
			}
			
			return eventos;
	  }
}
