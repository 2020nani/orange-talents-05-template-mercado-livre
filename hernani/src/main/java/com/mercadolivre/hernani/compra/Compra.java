package com.mercadolivre.hernani.compra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastrousuario.Usuario;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull 
	@Valid
	private Produto produtoComprado;
	
	@ManyToOne
	@NotNull 
	@Valid
	private Usuario usuarioRealizaCompra;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusCompra status = StatusCompra.INICIADO;
	
	@Positive
	private int quantidadecompra;

	

	public Compra(@NotNull @Valid Produto produtoComprado, @NotNull @Valid Usuario usuarioRealizaCompra,
			@NotNull GatewayPagamento gateway, @Positive int quantidadecompra) {
		super();
		this.produtoComprado = produtoComprado;
		this.usuarioRealizaCompra = usuarioRealizaCompra;
		this.gateway = gateway;
		this.quantidadecompra = quantidadecompra;
	}


	@Override
	public String toString() {
		return "Compra [id=" + id + ", produtoComprado=" + produtoComprado + ", usuarioRealizaCompra="
				+ usuarioRealizaCompra + ", gateway=" + gateway + ", status=" + status + ", quantidadecompra="
				+ quantidadecompra + "]";
	}


	public Long getId() {
		return id;
	}


	public Produto getProdutoComprado() {
		return produtoComprado;
	}


	public Usuario getUsuarioRealizaCompra() {
		return usuarioRealizaCompra;
	}


	public GatewayPagamento getGateway() {
		return gateway;
	}


	public StatusCompra getStatus() {
		return status;
	}


	public int getQuantidadecompra() {
		return quantidadecompra;
	}
	
	

}
