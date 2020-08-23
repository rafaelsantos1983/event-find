/*
 * EventFind
 *
 * Tipo: RepositoioConfiguracao
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio.configuracao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eventfind.R;
import com.eventfind.controlador.Controlador;
import com.eventfind.modelo.configuracao.Configuracao;
import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.Cidade;
import com.eventfind.modelo.evento.Estado;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.IEstado;
import com.eventfind.modelo.evento.ITipoEvento;
import com.eventfind.modelo.evento.TipoEvento;
import com.eventfind.repositorio.Repositorio;
import com.eventfind.repositorio.SQLiteHelper;

/**
 * Classe que trata a manipulação das CONFIGURAÇÕES com a base de dados
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class RepositorioConfiguracao extends Repositorio implements IRepositorioConfiguracao {

	/**
	 * Construtor
	 * @param controlador Controlador
	 * @param contexto Contexto
	 */
	public RepositorioConfiguracao(Controlador controlador, Context contexto) {
		super(controlador, contexto);
	}
	
	/**
	 * @see com.eventfind.repositorio.configuracao.IRepositorioConfiguracao#inserirOuAtualizarConfiguracao(com.eventfind.modelo.configuracao.IConfiguracao)
	 */
	public void inserirOuAtualizarConfiguracao(IConfiguracao configuracao) {
		Long configuracaoId = configuracao.getId();

		if (configuracaoId != null) {//atualizar 
			SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
			
			try{
				ContentValues valoresConfiguracao = new ContentValues();
				valoresConfiguracao.put("ID_CIDADE", configuracao.getCidade().getId());
				super.atualizar(SQLiteHelper.TABELA_CONFIGURACAO,valoresConfiguracao,"ID_CONFIGURACAO = ?",new String[] {configuracaoId.toString()},sqLiteDatabase);

				//Remover a associação entre a configuração de o tipo do evento
				remover(SQLiteHelper.TABELA_CONFIGURACAO_TIPO_EVENTO, "ID_CONFIGURACAO", new String[] { configuracaoId.toString() }, sqLiteDatabase);

				//Inserir os Tipos de Evento atrelados a configuração
				for (ITipoEvento tipoEvento : configuracao.getTiposEventos()) {
					ContentValues valoresTiposEventos = new ContentValues();
					valoresTiposEventos.put("ID_TIPO_EVENTO", tipoEvento.getId());
					valoresTiposEventos.put("ID_CONFIGURACAO", configuracaoId);
		
					inserir(SQLiteHelper.TABELA_CONFIGURACAO_TIPO_EVENTO,valoresTiposEventos,sqLiteDatabase);
				}
			}finally {
				fecharConexaoBancoDados(sqLiteDatabase);
			}					
		} else { //inserir
			SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
			
			try {
				//Inserindo a Configuração
				ContentValues valoresConfiguracao = new ContentValues();
				valoresConfiguracao.put("ID_CIDADE", configuracao.getCidade().getId()); //Cidade escolhida para a configuração
	
				configuracaoId = inserir(SQLiteHelper.TABELA_CONFIGURACAO,valoresConfiguracao,sqLiteDatabase);
				
				//Inserir os Tipos de Evento atrelados a configuração
				for (ITipoEvento tipoEvento : configuracao.getTiposEventos()) {
					ContentValues valoresTiposEventos = new ContentValues();
					
					Cursor cursorTipoEvento = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_TIPO_EVENTO_PELO_CODIGO),new String[] {tipoEvento.getCodigo().toString()});
					
					if (cursorTipoEvento.getCount() > 0) {
						cursorTipoEvento.moveToFirst();

						valoresTiposEventos.put("ID_TIPO_EVENTO", cursorTipoEvento.getLong(0));
						valoresTiposEventos.put("ID_CONFIGURACAO", configuracaoId);
					}
		
					if(cursorTipoEvento != null){
						cursorTipoEvento.deactivate(); 
						cursorTipoEvento.close();
					}
					
					inserir(SQLiteHelper.TABELA_CONFIGURACAO_TIPO_EVENTO,valoresTiposEventos,sqLiteDatabase);
				}
				
			}finally {
				fecharConexaoBancoDados(sqLiteDatabase);
			}	
		}
	}
	
	/**
	 * @see com.eventfind.repositorio.configuracao.IRepositorioConfiguracao#consultarConfiguracaoPeloId(Long)
	 */
	public IConfiguracao consultarConfiguracaoPeloId(Long id) {
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();

		IConfiguracao configuracao = null;
		
		Cursor cursor = null;
		Cursor cursorTiposEventos = null;
		try{
			sqLiteDatabase.beginTransaction();
		
			cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_CONFIGURACAO_POR_ID),new String[] {id.toString()});
			
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();

				configuracao = 	criarEInicializarConfiguracao(cursor);
				
				//consulta os tipos de eventos que estão associado a configuração
				cursorTiposEventos = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_CONFIGURACAO_TIPO_EVENTO_POR_CONFIGURACAO),new String[] {id.toString()});
				if (cursorTiposEventos.moveToFirst()) {
					do {
						configuracao.addTipoEvento(criarTipoEvento(cursorTiposEventos));
					} while (cursorTiposEventos.moveToNext());
				}
				
			}
		}finally {
			if(cursor != null){
				cursor.deactivate(); 
				cursor.close();
			}

			if(cursorTiposEventos != null){
				cursorTiposEventos.deactivate(); 
				cursorTiposEventos.close();
			}
			
			
			sqLiteDatabase.endTransaction();
			fecharConexaoBancoDados(sqLiteDatabase);
		}	
		
		return configuracao;
	}
	
	/**
	 * @see com.eventfind.repositorio.configuracao.IRepositorioConfiguracao#consultarTodasConfiguracoes()
	 */
	public List<IConfiguracao> consultarTodasConfiguracoes() {
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		List<IConfiguracao> configuracoes = new ArrayList<IConfiguracao>();
		
		try{
			sqLiteDatabase.beginTransaction();
			Cursor cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_TODAS_CONFIGURACOES),null);
			
			if (cursor.moveToFirst()) {
				do {
					IConfiguracao configuracao = criarEInicializarConfiguracao(cursor);
					
					//consulta os tipos de eventos que estão associado a configuração
					Cursor cursorTiposEventos = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_CONFIGURACAO_TIPO_EVENTO_POR_CONFIGURACAO),new String[] {configuracao.getId().toString()});
					if (cursorTiposEventos.moveToFirst()) {
						do {
							configuracao.addTipoEvento(criarTipoEvento(cursorTiposEventos));
						} while (cursorTiposEventos.moveToNext());
					}
					
					configuracoes.add(configuracao);
					
					if(cursorTiposEventos != null){
						cursorTiposEventos.deactivate(); 
						cursorTiposEventos.close();
					}
				} while (cursor.moveToNext());
			}
			if(cursor != null){
				cursor.deactivate(); 
				cursor.close();
			}
		}finally {
			sqLiteDatabase.endTransaction();
			fecharConexaoBancoDados(sqLiteDatabase);
		}

		return configuracoes;
	}
	
	/*
	 * Cria e Inicializa os dados da Configuração de acordo com o Cursor
	 * @param cursor Cursor
	 * @return configuração 
	 */
	private IConfiguracao criarEInicializarConfiguracao(Cursor cursor){
		IConfiguracao configuracao = new Configuracao();
		
		configuracao.setId(cursor.getLong(0));
		IEstado estado = new Estado();
		estado.setId(cursor.getLong(1));
		estado.setSigla(cursor.getString(2));
		estado.setNome(cursor.getString(3));
		
		ICidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setId(cursor.getLong(4));
		cidade.setNome(cursor.getString(5));
		configuracao.setCidade(cidade);
		
		return configuracao;
	}
	
	/*
	 * Cria e Inicializa os dados do Tipo de Evento de acordo com o Cursor
	 * @param cursor Cursor
	 * @return Tipo do Evento 
	 */	
	private ITipoEvento criarTipoEvento(Cursor cursor){
		ITipoEvento tipoEvento = new TipoEvento();
		tipoEvento.setId(cursor.getLong(0));
		tipoEvento.setCodigo(cursor.getInt(1));
		tipoEvento.setDescricao(cursor.getString(2));
		
		return tipoEvento;
	}
	
	/**
	 * @see com.eventfind.repositorio.configuracao.IRepositorioConfiguracao#removerConfiguracao(java.lang.Long)
	 */
	public void removerConfiguracao(Long configuracaoId){
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		try{
			//Remover a associação entre a configuração de o tipo do evento
			remover(SQLiteHelper.TABELA_CONFIGURACAO_TIPO_EVENTO, "ID_CONFIGURACAO=?", new String[] { configuracaoId.toString() }, sqLiteDatabase);
			
			//Remover a configuração
			remover(SQLiteHelper.TABELA_CONFIGURACAO, "ID_CONFIGURACAO=?", new String[] { configuracaoId.toString() }, sqLiteDatabase);
		}finally {
			fecharConexaoBancoDados(sqLiteDatabase);
		}
	}
	
	/**
	 * @see com.eventfind.repositorio.configuracao.IRepositorioConfiguracao#consultarTodosEstados()
	 */
	public List<IEstado> consultarTodosEstados() {
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		List<IEstado> estados = new ArrayList<IEstado>();
		Cursor cursor = null;
		try{
			sqLiteDatabase.beginTransaction();
			cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_TODOS_ESTADOS),null);
			
			if (cursor.moveToFirst()) {
				do {
					IEstado estado = new Estado();
					
					estado.setId(cursor.getLong(0));
					estado.setSigla(cursor.getString(1));
					estado.setNome(cursor.getString(2));
					
					estados.add(estado);
				} while (cursor.moveToNext());
			}
		}finally {
			if(cursor != null){
				cursor.deactivate(); 
				cursor.close();
			}
			
			sqLiteDatabase.endTransaction();
			fecharConexaoBancoDados(sqLiteDatabase);
		}

		return estados;
	}	
	
	/**
	 * @see com.eventfind.repositorio.configuracao.IRepositorioConfiguracao#consultarTodasCidadesPeloEstado(java.lang.Long)
	 */
	public List<ICidade> consultarTodasCidadesPeloEstado(Long estadoId) {
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		List<ICidade> cidades = new ArrayList<ICidade>();
		Cursor cursor = null;
		try{
			sqLiteDatabase.beginTransaction();
			cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_CIDADES_POR_ESTADO),new String[] {estadoId.toString()});
			
			if (cursor.moveToFirst()) {
				do {
					IEstado estado = new Estado();
					estado.setId(cursor.getLong(0));
					estado.setSigla(cursor.getString(1));
					estado.setNome(cursor.getString(2));
					
					ICidade cidade = new Cidade();
					cidade.setEstado(estado);
					cidade.setId(cursor.getLong(3));
					cidade.setNome(cursor.getString(4));					
					
					cidades.add(cidade);
				} while (cursor.moveToNext());
			}
		}finally {
			if(cursor != null){
				cursor.deactivate(); 
				cursor.close();
			}
			
			sqLiteDatabase.endTransaction();
			fecharConexaoBancoDados(sqLiteDatabase);
		}

		return cidades;
	}	
	
}
