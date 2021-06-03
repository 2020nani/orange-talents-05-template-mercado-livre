package com.mercadolivre.hernani.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroForm implements RetornoGatewayPagamento {
	
	@NotBlank
	private String idTransacao;
	
	@NotNull
	private StatusRetornoPagamento status;

	public RetornoPagseguroForm(@NotBlank String idTransacao, @NotNull StatusRetornoPagamento status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}

	@Override
	public String toString() {
		return "RetornoPagseguroForm [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toTransacao(Compra compra) {
		
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
	
	

}
