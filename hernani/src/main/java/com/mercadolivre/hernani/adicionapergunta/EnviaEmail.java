package com.mercadolivre.hernani.adicionapergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {
	
	@Autowired
	//1
	private MailerDev mailer;

	//1
	public void PerguntaUsuario(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta de",pergunta.getUsuario().getEmail(),"Qual o valor do PS5" + " para ",pergunta.getProduto().getDono().getEmail());
	}

}
