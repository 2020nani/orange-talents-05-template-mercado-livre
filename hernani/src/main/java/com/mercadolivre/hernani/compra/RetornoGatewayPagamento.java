package com.mercadolivre.hernani.compra;

public interface RetornoGatewayPagamento {
	
	/*
	 * @param compra
	 * @return uma nova transacao em funcao do tipo de gateway especifico
	 * */

	Transacao toTransacao(Compra compra);
}
