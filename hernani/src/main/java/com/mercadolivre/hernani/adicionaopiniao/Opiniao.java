package com.mercadolivre.hernani.adicionaopiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastrousuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	@Min(1)
	@Max(5)
	private int nota;
	
	@ManyToOne
	@NotNull
	private Usuario usuario;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	@Deprecated
	public Opiniao() {
		
	}

	public Opiniao(@NotBlank String titulo, @NotBlank @Size(max = 500) String descricao, @Min(1) @Max(5) int nota,
			@NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getNota() {
		return nota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", nota=" + nota + ", usuario="
				+ usuario + ", produto=" + produto + "]";
	}
	
	
	

}
