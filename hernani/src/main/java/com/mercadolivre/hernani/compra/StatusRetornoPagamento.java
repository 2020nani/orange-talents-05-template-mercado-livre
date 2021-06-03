package com.mercadolivre.hernani.compra;

public enum StatusRetornoPagamento {
      SUCESSO,ERRO;

	public StatusTransacao normaliza() {
	
		if(this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.ERRO;
	}
}
