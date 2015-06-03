package com.modelodados;

import java.util.Date;

public class Chamado {

	private int		id;
	private String	titulo;
	private String	descricao;
	private Date	dataCriacao;

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
}
