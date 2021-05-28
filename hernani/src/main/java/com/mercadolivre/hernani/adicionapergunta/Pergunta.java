package com.mercadolivre.hernani.adicionapergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastrousuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@NotNull
	@ManyToOne
	private Usuario usuarioInteressado;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Future
	@CreationTimestamp
	private LocalDateTime momentoCriacao;
	
	@Deprecated
	public Pergunta() {
		
	}

	
	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuarioInteressado, @NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.usuarioInteressado = usuarioInteressado;
		this.produto = produto;
		
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getUsuario() {
		return usuarioInteressado;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public LocalDateTime getMomentoCriacao() {
		return momentoCriacao;
	}


	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", usuarioInteressado=" + usuarioInteressado + ", produto=" + produto
				+ ", momentoCriacao=" + momentoCriacao + "]";
	}
	
	
	

}
