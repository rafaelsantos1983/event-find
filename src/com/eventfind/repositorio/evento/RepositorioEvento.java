/*
 * EventFind
 *
 * Tipo: RepositoioEvento
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.repositorio.evento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eventfind.R;
import com.eventfind.controlador.Controlador;
import com.eventfind.modelo.configuracao.IConfiguracao;
import com.eventfind.modelo.evento.Cidade;
import com.eventfind.modelo.evento.Estado;
import com.eventfind.modelo.evento.Evento;
import com.eventfind.modelo.evento.ICidade;
import com.eventfind.modelo.evento.IEstado;
import com.eventfind.modelo.evento.IEvento;
import com.eventfind.modelo.evento.ITipoEvento;
import com.eventfind.modelo.evento.TipoEvento;
import com.eventfind.repositorio.Repositorio;
import com.eventfind.repositorio.SQLiteHelper;
import com.eventfind.view.Util;

/**
 * Classe que trata a manipulação dos EVENTOS com a base de dados
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto~
 * 
 * :romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class RepositorioEvento extends Repositorio implements IRepositorioEvento {
	
	/**
	 * Construtor
	 * @param controlador Controlador
	 * @param contexto Contextod
	 */
	public RepositorioEvento(Controlador controlador, Context contexto) {
		super(controlador, contexto);
	}	
	
	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#inserirOuAtualizarEvento(com.eventfind.modelo.evento.IEvento)
	 */
	public void inserirOuAtualizarEvento(IEvento evento) {
//		long id = evento.getId();

//		if (id != 0) {//atualizar 
//			
//			
//			ContentValues valores = new ContentValues();
//			
////			values.put(Carros.NOME, carro.nome);
//
//			String _id = String.valueOf(configuracao.getId());
//
//			String where = Carros._ID + "=?";
//			String[] whereArgs = new String[] { _id };
//
//			int count = atualizar(values, where, whereArgs);
//
//			return this.atualizar(BancoHelper.TABELA_EVENTO, valores, where, whereArgs);;
//		} else { //inserir
//			id = this.inserir(configuracao);
//			
//			ContentValues values = new ContentValues();
//			values.put(Carros.NOME, carro.nome);
//			values.put(Carros.PLACA, carro.placa);
//			values.put(Carros.ANO, carro.ano);
//
//			long id = inserir(BancoHelper.TABELA_EVENTO, values);
//		}
	}
	
	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#consultarEventoPeloId(java.lang.Long)
	 */
	public IEvento consultarEventoPeloId(Long id) {
		
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();

		IEvento evento = null;
		
		Cursor cursor = null;
		try{
			sqLiteDatabase.beginTransaction();
			
			cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_EVENTO_POR_ID),new String[] {id.toString()} );
			
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				evento = criarEInicializarEvento(cursor);
			}
		}finally {
			if(cursor != null){
				cursor.deactivate(); 
				cursor.close();
			}
			
			sqLiteDatabase.endTransaction();
			fecharConexaoBancoDados(sqLiteDatabase);
		}
		
		return evento;
	}
	
	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#consultarTodosOsEventos()
	 */
	public List<IEvento> consultarTodosOsEventos() {
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		List<IEvento> eventos = new ArrayList<IEvento>();
		Cursor cursor = null;
		try{
			sqLiteDatabase.beginTransaction();
			
			cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_TODOS_EVENTOS),null );

			if (cursor.moveToFirst()) {
				do {
					eventos.add(criarEInicializarEvento(cursor));
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

		return eventos;
	}	
	
	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#consultarTodosOsEventosDeAcordoConfiguracao(List)
	 */
	public List<IEvento> consultarTodosOsEventosDeAcordoConfiguracao(List<IConfiguracao> configuracoes) {
		List<IEvento> eventos = new ArrayList<IEvento>();
		
		//verifica-se se existe alguma configuração que deve ser utilizada para listar os eventos
		//caso não exista nenhum configuração, deve-se lista todos os eventos disponíveis
		if(!configuracoes.isEmpty()){
			SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
			
			try{
				
				sqLiteDatabase.beginTransaction();
			
				for (IConfiguracao configuracao : configuracoes) {
					
					Cursor cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_TODOS_EVENTOS_POR_CONFIGURACAO),
							new String[] {configuracao.getId().toString(), configuracao.getId().toString()} );
					
					if (cursor.moveToFirst()) {
						do {
							eventos.add(criarEInicializarEvento(cursor));
						} while (cursor.moveToNext());
					}
					
					if(cursor != null){
						cursor.close();
					}
				}	
				
		        // Em ordem crescente do início do mandato  
		        Collections.sort (eventos, new Comparator<IEvento>() {  
		            public int compare(IEvento e1, IEvento e2) {  
		                return e1.getData().compareTo(e2.getData());  
		            }  
		        });  
			}finally {
				
				sqLiteDatabase.endTransaction();
				fecharConexaoBancoDados(sqLiteDatabase);
			}
		}

		return eventos;
	}

	/*
	 * Cria e Inicializa os dados do Evento de acordo com o Cursor
	 * @param cursor Cursor
	 * @return Evento 
	 */
	private IEvento criarEInicializarEvento(Cursor cursor){
		IEvento evento = new Evento();
		
		evento.setId(cursor.getLong(0));
		evento.setTitulo(cursor.getString(1));
		evento.setData(cursor.getString(2));
		evento.setHora(cursor.getString(3));
		evento.setTelefone(cursor.getString(4));
		evento.setDdd(cursor.getString(5));
		evento.setDescricao(cursor.getString(6));
		evento.setLogradouro(cursor.getString(7));
		evento.setNumero(cursor.getInt(8));
		evento.setComplemento(cursor.getString(9));
		evento.setBairro(cursor.getString(10));
		evento.setCep(cursor.getString(11));
		evento.setReferencia(cursor.getString(12));
		
		//tipo do evento
		ITipoEvento tipoEvento = new TipoEvento();
		tipoEvento.setId(cursor.getLong(13));
		tipoEvento.setCodigo(cursor.getInt(14));
		tipoEvento.setDescricao(cursor.getString(15));
		evento.setTipoEvento(tipoEvento);

		//estado
		IEstado estado = new Estado();
		estado.setId(cursor.getLong(16));
		estado.setSigla(cursor.getString(17));
		estado.setNome(cursor.getString(18));
		
		
		evento.setNomeEmpresaPromove(cursor.getString(21));
		evento.setLocalEvento(cursor.getString(22));
		evento.setSite(cursor.getString(23));
		evento.setEmail(cursor.getString(24));
		
		evento.setFacebook(cursor.getString(25));
		evento.setTwitter(cursor.getString(26));
		evento.setOrkut(cursor.getString(27));

		//cidade
		ICidade cidade = new Cidade();
		cidade.setId(cursor.getLong(19));
		cidade.setNome(cursor.getString(20));
		cidade.setNomeReduzido(cursor.getString(28));
		cidade.setEstado(estado);
		evento.setCidade(cidade);

		return evento;
	}

	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#consultarTodosOsTiposEventos()
	 */
	public List<ITipoEvento> consultarTodosOsTiposEventos() {
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		List<ITipoEvento> tiposEventos = new ArrayList<ITipoEvento>();
		Cursor cursor = null;
		try{
			sqLiteDatabase.beginTransaction();
		
		    cursor = sqLiteDatabase.rawQuery(obterScriptSQL(R.array.QUERY_TODOS_TIPOS_EVENTOS),null );

			if (cursor.moveToFirst()) {
				do {
					ITipoEvento tipoEvento = new TipoEvento();
					tipoEvento.setId(cursor.getLong(0));
					tipoEvento.setCodigo(cursor.getInt(1));
					tipoEvento.setDescricao(cursor.getString(2));
					
					tiposEventos.add(tipoEvento);
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

		return tiposEventos;
	}
	
	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#atualizarEventos(List)
	 */
	public void atualizarEventos(List<IConfiguracao> configuracoes) {
		SQLiteDatabase sqLiteDatabase = null;
		SQLiteHelper dbHelper = null;
		
		try{
			//TODO Tempo para simular uma conexão com o servidor e obtendo os dados, remover este sleep quando fazer a conexao com o servidor
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			dbHelper = new SQLiteHelper(contexto);
				
			sqLiteDatabase = dbHelper.getWritableDatabase();

			sqLiteDatabase.execSQL(obterScriptSQL(R.array.REMOVER_EVENTOS_COM_DATA_MENOR_ATUAL));

			//TODO Código para simular a obtenção de dados do servidor, remover este código depois de criado do servidor de dados
			for (String scriptSQL : contexto.getResources().getStringArray(R.array.ScriptCargaBancoTesteEvento)) {
				sqLiteDatabase.execSQL(scriptSQL);
			}
			
		}finally {
			// Fecha o banco de dados
			if (sqLiteDatabase != null) {
				sqLiteDatabase.close();
			}
			if (dbHelper != null) {
				dbHelper.close();
			}
		}			
		
	}

	/**
	 * @see com.eventfind.repositorio.evento.IRepositorioEvento#removerEvento(java.lang.Long)
	 */
	public void removerEvento(Long eventoId){
		SQLiteDatabase sqLiteDatabase = obterConexaoBancoDados();
		
		try{
			sqLiteDatabase.beginTransaction();
		
			//Remover o evento
			remover(SQLiteHelper.TABELA_EVENTO, "ID_EVENTO", new String[] { eventoId.toString() }, sqLiteDatabase);

		}finally {
			sqLiteDatabase.endTransaction();
			fecharConexaoBancoDados(sqLiteDatabase);
		}
	}
}
