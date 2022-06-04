package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categorias")
public class Categorias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank (message = "O tipo da medicação não pode ser nulo ou conter somente espaços em branco!")
	//Ex Tipo: "Antibiótico"
	private String tipo;
	
	@NotBlank (message = "A função da medicação não pode ser nulo ou conter somente espaços em branco!")
	//Ex funcao: Infecção geral
	private String funcao;
	
	@JsonIgnoreProperties("categorias")
	@OneToMany (cascade = CascadeType.REMOVE, mappedBy = "categorias")
	private List<Produtos> produtos;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

	
	
}
