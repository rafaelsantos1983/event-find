/*
 * EventFind
 *
 * Tipo: Util.java
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.Window;

import com.eventfind.R;

/**
 * Classe utilitária
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Util {

	/**
	 * TAG utilizado nas mensagem do log
	 */
	private static final String TAG = Util.class.getSimpleName();

	/**
	 * Constante da Categoria da Intent do projeto EventFind
	 */
	public static final String CATEGORIA_EVENT_FIND = "CATEGORIA_EVENT_FIND";
	
	/**
	 * Constante que identifica o momento do evento
	 */
	public static final String HOJE = "Hoje";
	public static final String AMANHA = "Amanhã";

	
	/**
	 * Formatao de datra DD/MM/AAAA
	 */
	public static final String DDMMYYYY = "dd/MM/yyyy";

	/**
	 * Formata a data utilizando o padrão "dd/MM/yyyy"
	 * @param data Data
	 * @return Data formatada
	 */
	public static String formatarData(Date data){ 
		return formatarData(data, DDMMYYYY);
	}
	
	/**
	 * Formata a data utilizando o formato enviado por parâmetro
	 * @param data Data
	 * @param formato Formato para formatar a data
	 * @return Data formatada de acordo com o formato
	 * */
	public static String formatarData(Date data, String formato){ 
		SimpleDateFormat formatter = new SimpleDateFormat(formato);  
    	return formatter.format( data );
	}
	
	/**
	 * Cria um degradê de duas cores: azul e cinza
	 * @return
	 */
	public static GradientDrawable DegradeAzulCinza() {
         return  new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.parseColor("#6959CD"), Color.LTGRAY});
	}

	/**
	 * De acordo com a data do evento, verifica em que momento o mesmo irá acontecer, se:
	 * "Hoje" ou 
	 * "Amanhã" ou a
	 * "Data do Evento"
	 * @param dataEvento Data do Evento
	 * @param formato Formato para apresentação da data
	 * @return Descrição para a Data
	 */
	public static String obterDescricaoData(String data, String formato) {
		String retorno = null;
		  
		GregorianCalendar dataCalendar = new GregorianCalendar();
		dataCalendar.set(Calendar.YEAR, Integer.valueOf(data.substring(0,4)));
		dataCalendar.set(Calendar.MONTH, Integer.valueOf(data.substring(5,7)) - 1);
		dataCalendar.set(Calendar.DATE, Integer.valueOf(data.substring(8,10)));
		
		Date date = dataCalendar.getTime();
				
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(Calendar.DATE, 1);
		
		if (formatarData(date,DDMMYYYY).compareTo(formatarData(new Date(),DDMMYYYY)) == 0) {
			retorno = Util.HOJE;
		} else if (formatarData(date,DDMMYYYY).compareTo(formatarData(gregorianCalendar.getTime(),DDMMYYYY)) == 0) {
			retorno = Util.AMANHA;
		}else{
			retorno = formatarData(date, formato);
		}
		
		return retorno;
	}
	
	/**
	 * Seta o cabeçalho da aplicação no cabeçalho da tela
	 * @param activity Atividade
	 */
	public static void setarCabecalho(Activity atividade){
		Window janela = atividade.getWindow();
        
        janela.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.cabecalho);
	}
	
	/**
	 * Verifica se existe conexão com a internet
	 *  
	 * @return <true> caso exista conexão, <false> caso contrário
	 */
	public static boolean existeConexaoInternet(Activity atividade) {     
		boolean existeConexao = false;
		
		try {
			ConnectivityManager cm = (ConnectivityManager) atividade.getSystemService(Context.CONNECTIVITY_SERVICE);     
			if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {         
				existeConexao = true;     
		    }
		}catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG,"Erro ao verificar a conexão com a internet");
		}
		
		return existeConexao;
	}

	/**
	 * Retorna o texto truncado de acordo com os parametros passados
	 *
	 * @param texto texto a ser truncado
	 * @param limite limite para o truncamento do texto
	 * @return retorna o texto passado como parametro truncado no limite
	 */
	public static String truncar(String texto, int limite){
		String textoTruncada = texto;  
		if (texto.length() > limite) {
			textoTruncada = texto.substring(0,limite) + "...";
		}
	    return textoTruncada;
	}	
	
	/**
	 * Valida conteudo do texto passado como parametro
	 *
	 * @param texto texto a ser verificado
	 * @return Retorna o 'true' caso o parametro passado seja NULO ou "" (espaço vazio), caso contrario retorna 'false'
	 */
	public static boolean ehNuloOuBranco(String texto) {
		if (texto == null || texto == "") {
			return true;
		}
		return false;
	}
}
