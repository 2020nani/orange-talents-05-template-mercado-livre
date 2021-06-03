package com.mercadolivre.hernani.compra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@FeignClient(url = "localhost:8080/notas-fiscais", name = "NotaFiscal")
public interface NotaFiscal {

    @PostMapping
    void criaNotaFiscal(@Valid NotaFiscalForm notaFiscalForm);

	

}
