package com.mercadolivre.hernani.simulasistema;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.hernani.compra.NotaFiscalForm;

@RestController
public class simulasistemasexternos {
	
	 @PostMapping("/notas-fiscais")
	    public void criaNotaFiscal(@RequestBody @Valid NotaFiscalForm notaFiscalForm) {
	        System.out.println("Criando nota fiscal para a compra: id " + notaFiscalForm.getCompraId() + " do comprador " + notaFiscalForm.getCliente());
	    }

}
