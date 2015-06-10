package com.modelodados;

import java.util.Date;
import java.util.List;

/**
 * @author Paulo Weber
 *
 */
public class Chamado {

	private int		id;
	private String	titulo;
	private String	descricao;
	private Date	dataCriacao;
	private List<Instrucao> instrucoes;

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

	public List<Instrucao> getInstrucoes() {
		return instrucoes;
	}

	public void addInstrucao(final Instrucao instrucao){
		instrucoes.add(instrucao);
	}
}
