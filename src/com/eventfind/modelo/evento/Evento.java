/*
 * EventFind
 *
 * Tipo: Evento
 *
 * Desenvolvido por:
 *    Rafael Santos (rafaelsantos1983@gmail.com)
 *    Romero Barbosa (romerobarbosa@gmail.com)
 * Todos os direitos reservados.
 */
package com.eventfind.modelo.evento;

import java.io.Serializable;

import android.provider.BaseColumns;

import com.eventfind.R;
import com.eventfind.modelo.ObjetoGenerico;

/**
 * Classe que representa um EVENTO
 * @author Rafael Santos <a href="mailto:rafaelsantos1983@gmail.com">rafaelsantos1983@gmail.com</a>
 * @author Romero Barbosa <a href="mailto:romerobarbosa@gmail.com">romerobarbosa@gmail.com</a>
 */
public class Evento extends ObjetoGenerico implements Serializable, IEvento, BaseColumns {

	private static final long serialVersionUID = 1L;
	
	/** Título */
	private String titulo;
	/** Data em que será realizado o evento*/
	private String data;
	/** Hora em que será realizado o evento*/
	private String hora;
	/** Descrição */
	private String descricao;
	/** Telefone para contato com o evento */
	private String telefone;
	/** DDD do Telefone para contato com o evento */
	private String ddd;
	/** Tipo do Evento */
	private ITipoEvento tipoEvento;
	/** Nome da Empresa que Promove o Evento */
	private String nomeEmpresaPromove;
	/** Local do Evento */
	private String localEvento;
	/** Site do Evento */
	private String site;
	/** E-mail do Evento */
	private String email;
	/** Facebook do Evento */
	private String facebook;
	/** Twitter do Evento */
	private String twitter;
	/** Orkut do Evento */
	private String orkut;
	/** Map do Evento */
	private String map;

	
	//Endereço do Evento
	/** Logradouro */
	private String logradouro;
	/** Número */
	private Integer numero;
	/** Complemento */
	private String complemento;
	/** Bairro */
	private String bairro;
	/** CEP */
	private String cep;
	/** Referência de como chegar ao local */
	private String referencia;
	/** Cidade */
	private ICidade cidade;	

//	Mapa (Com rota)
//	Comentários 
//	Postas comentários no twitter com uma tag personalizada;
//	Filtrar os comentários da tag do evento no twitter;

	/**
	 * Constroe um Evento
	 */
	public Evento(){
		
	}
	
	/**
	 * Constroe um Evento
	 */
	public Evento(Long id, String titulo, String descricao, ICidade cidade,
			String data, String ddd, String telefone, ITipoEvento tipoEvento) {
		setId(id);
		setTitulo(titulo);
		setDescricao(descricao);
		setCidade(cidade);
		setData(data);
		setDdd(ddd);
		setTelefone(telefone);
		setTipoEvento(tipoEvento);
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getTitulo()
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setTitulo(java.lang.String)
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setData(java.util.Date)
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getDescricao()
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setDescricao(java.lang.String)
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	/**
	 * @see com.eventfind.modelo.evento.IEvento#getTipoEvento()
	 */
	public ITipoEvento getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setTipoEvento(com.eventfind.modelo.evento.ITipoEvento)
	 */
	public void setTipoEvento(ITipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	/**
	 * @see com.eventfind.modelo.evento.IEvento#getImagem()
	 */
	public int getImagem() {
		switch (tipoEvento.getCodigo()) {
			case TipoEvento.CULTURAL:
				return R.drawable.cultural48x48;
			case TipoEvento.GASTRONOMICO:
				return R.drawable.gastronomico48x48;
			case TipoEvento.ESPORTIVO:
				return R.drawable.esportivo48x48;
			case TipoEvento.RELIGIOSO:
				return R.drawable.religioso48x48;
			case TipoEvento.POLITICO:
				return R.drawable.politico48x48;
			case TipoEvento.MUSICAL:
				return R.drawable.musical48x48;
			case TipoEvento.CARNAVAL:
				return R.drawable.carnaval48x48;
		}
		return R.drawable.outros48x48;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getTelefone()
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setTelefone(java.lang.String)
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getDdd()
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setDdd(java.lang.String)
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getData()
	 */
	public String getData() {
		return data;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getLogradouro()
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setLogradouro(java.lang.String)
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getNumero()
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setNumero(java.lang.Integer)
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getComplemento()
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setComplemento(java.lang.String)
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getBairro()
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setBairro(java.lang.String)
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getCep()
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setCep(java.lang.String)
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getReferencia()
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setReferencia(java.lang.String)
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#getCidade()
	 */
	public ICidade getCidade() {
		return cidade;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEndereco#setCidade(com.eventfind.modelo.evento.ICidade)
	 */
	public void setCidade(ICidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getHora()
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setHora(java.lang.String)
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getNomeEmpresaPromove()
	 */
	public String getNomeEmpresaPromove() {
		return nomeEmpresaPromove;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setNomeEmpresaPromove(java.lang.String)
	 */
	public void setNomeEmpresaPromove(String nomeEmpresaPromove) {
		this.nomeEmpresaPromove = nomeEmpresaPromove;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getNomeEmpresaPromove()
	 */
	public String getLocalEvento() {
		return localEvento;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setNomeEmpresaPromove(java.lang.String)
	 */
	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getNomeEmpresaPromove()
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setNomeEmpresaPromove(java.lang.String)
	 */
	public void setSite(String site) {
		this.site = site;
	}
	
	/**
	 * @see com.eventfind.modelo.evento.IEvento#getNomeEmpresaPromove()
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setNomeEmpresaPromove(java.lang.String)
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obter o valor de facebook
	 * @return Valor de facebook
	 */
	public String getFacebook() {
		return facebook;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setFacebook(java.lang.String)
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getTwitter()
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setTwitter(java.lang.String)
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getOrkut()
	 */
	public String getOrkut() {
		return orkut;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setOrkut(java.lang.String)
	 */
	public void setOrkut(String orkut) {
		this.orkut = orkut;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#getMap()
	 */
	public String getMap() {
		return map;
	}

	/**
	 * @see com.eventfind.modelo.evento.IEvento#setMap(java.lang.String)
	 */
	public void setMap(String map) {
		this.map = map;
	}
	
}