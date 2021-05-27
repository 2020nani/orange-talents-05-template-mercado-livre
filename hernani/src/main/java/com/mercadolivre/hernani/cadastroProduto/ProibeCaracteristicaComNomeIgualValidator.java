package com.mercadolivre.hernani.cadastroProduto;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		ProdutoForm request = (ProdutoForm) target;
		Set<String> nomesIguais = request.buscaCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas com nomes iguais "+nomesIguais);
		}
	}
	
}
