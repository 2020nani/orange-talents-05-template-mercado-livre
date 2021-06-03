package com.mercadolivre.hernani.compra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;
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

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Compra() {
		// TODO Auto-generated constructor stub
	}

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
				+ quantidadecompra + ", transacoes=" + transacoes + "]";
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
	
	private Set<Transacao> transacoesConcluidasComSucesso(){
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes
				.stream()
				.filter(Transacao :: concluidaComSucesso)
				.collect(Collectors.toSet());
		return transacoesConcluidasComSucesso;
	}


	public void tentapagamento(@Valid RetornoGatewayPagamento retornopagseguroform) {
		Transacao novaTransacao = retornopagseguroform.toTransacao(this); 
		Assert.isTrue(!this.transacoes.contains(novaTransacao),"Ja existe uma transacao igual a essa processada "+novaTransacao);
		
		Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),"Essa compra ja foi concluida com sucesso ");
		if(novaTransacao.getStatus() == StatusTransacao.SUCESSO) {
		this.status = StatusCompra.FINALIZADO;
		}
		
		this.transacoes.add(novaTransacao);
		
		
	}


	public boolean processadaComSucesso() {
		
		return !transacoesConcluidasComSucesso().isEmpty();
	}
	
	

}
