package com.mercadolivre.hernani.compra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraControllerParte2 {
	
	@Autowired
	private CompraRepository comprarepository;
	
	@Autowired
	private NotaFiscal notaFiscal;
	
	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroForm retornopagseguroform) {
		
		return processa(idCompra, retornopagseguroform);
		
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalForm retornopaypalform) {
		
		return processa(idCompra, retornopaypalform);
	
	}
	
	private String processa(Long idCompra, RetornoGatewayPagamento retornogatewaypagamento) {
	Compra compra = comprarepository.findById(idCompra).get();
		
		compra.tentapagamento(retornogatewaypagamento);
		
		comprarepository.save(compra);
		
		if(compra.processadaComSucesso()) {
			 notaFiscal.criaNotaFiscal(new NotaFiscalForm(compra.getId(), compra.getUsuarioRealizaCompra().getEmail()));
			
		}
		return compra.toString();
	}

}
