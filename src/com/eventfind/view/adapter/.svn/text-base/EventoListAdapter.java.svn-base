/*
 * EventFind
 *
 * Tipo: EventoListAdapter
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventfind.R;
import com.eventfind.modelo.evento.IEvento;
import com.eventfind.view.Util;

/**
 * Classe que representa uma Lista de EVENTO utilizado para lista os eventos
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class EventoListAdapter extends BaseAdapter {
	
	/**
	 * Contexto
	 */
	private Context context;
	
	/**
	 * Lista de Evento
	 */
	private List<IEvento> lista;

	/**
	 * @param context
	 * @param lista
	 */
	public EventoListAdapter(Context context, List<IEvento> lista) {
		this.context = context;
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
		IEvento evento = lista.get(posicao);
		return evento;
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
	public View getView(int posicao, View convertView, ViewGroup parent) {
		// Recupera o Evento da posição atual
		IEvento evento = lista.get(posicao);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.evento_lista, null);
		
		// Atualiza o valor do Text para o titulo do Evento
		TextView textTitulo = (TextView) view.findViewById(R.id.titulo);
		textTitulo.setText(Util.truncar(evento.getTitulo(), 23));
		
		// Atualiza o valor do Text para a descrição do Evento
		TextView textLocalEvento = (TextView) view.findViewById(R.id.localEvento);
		textLocalEvento.setText(Util.truncar(evento.getLocalEvento(), 20));

		// Atualiza o valor do Text para o cidade do Evento
		TextView textCidade = (TextView) view.findViewById(R.id.cidade);
		textCidade.setText(evento.getCidade().getNomeReduzido());

		// Atualiza a imagem para a imagem do Evento
		// A imagem é definda por um recurso no @drawable
		ImageView img = (ImageView) view.findViewById(R.id.imgTipo);
		img.setImageResource(evento.getImagem());

		// Atualiza o valor do Text para a data do Evento
		TextView textData = (TextView) view.findViewById(R.id.data);
		textData.setText(Util.obterDescricaoData(evento.getData(), Util.DDMMYYYY));

		return view;
	}
}