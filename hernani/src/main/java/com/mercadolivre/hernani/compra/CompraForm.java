package com.mercadolivre.hernani.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {
	@Positive
	private int quantidadecompra;
	
	@NotNull
	private GatewayPagamento gateway;
	
	
  
	public CompraForm(@Positive int quantidadecompra, GatewayPagamento gateway) {
		super();
		this.quantidadecompra = quantidadecompra;
		this.gateway = gateway;
	}

	public int getQuantidadecompra() {
		return quantidadecompra;
	}
	
	public GatewayPagamento getGateway() {
		return gateway;
	}
}
