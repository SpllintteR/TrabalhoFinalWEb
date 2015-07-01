package com.modelodados;

import java.util.Date;

/**
 * @author Paulo Weber
 *
 */
public class Chamado {

	private int		id;
	private String	titulo;
	private String	descricao;
	private Date	dataCriacao;
	private String anexo;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(final Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getAnexo() {
		return getId() + "_" + "seta.png";
	}

	public void setAnexo(final String anexo) {
		this.anexo = anexo;
	}
}
