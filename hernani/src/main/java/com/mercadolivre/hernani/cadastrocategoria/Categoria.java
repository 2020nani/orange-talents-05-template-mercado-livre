package com.mercadolivre.hernani.cadastrocategoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique=true)
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	public Categoria() {
		super();
	}


	public Categoria(@NotBlank String nome, Categoria categoriaMae) {
		super();
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Categoria getCategoriaMae() {
		return categoriaMae;
	}


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoriaMae=" + categoriaMae + "]";
	}
	
	


}
