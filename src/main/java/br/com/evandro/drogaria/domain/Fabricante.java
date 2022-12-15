package br.com.evandro.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fabricante extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(length = 50, nullable = false)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
