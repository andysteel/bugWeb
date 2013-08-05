package br.com.k19.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bug {

	@Id @GeneratedValue
	private Long b_id;
	private String descricao;
	private String gravidade;
	@ManyToOne
	private Projeto projeto;
	
	
	public Long getB_id() {
		return b_id;
	}
	public void setB_id(Long b_id) {
		this.b_id = b_id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGravidade() {
		return gravidade;
	}
	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
}
