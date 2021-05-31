package com.mercadolivre.hernani.adicionapergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadolivre.hernani.compra.Compra;

@Component
public class EnviaEmail {
	
	@Autowired
	//1
	private MailerDev mailer;

	//1
	public void PerguntaUsuario(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta de",pergunta.getUsuario().getEmail(),"Qual o valor do PS5" + " para ",pergunta.getProduto().getDono().getEmail());
	}
	
	public void CompraRealizada(@NotNull @Valid Compra compra) {
		mailer.send("<html>...</html>", "Nova compra realizada por" +compra.getUsuarioRealizaCompra().getEmail() + "do produto" +compra.getProdutoComprado().getNome(), "lojinhadoze@mercadolivreprivado.com", compra.getUsuarioRealizaCompra().getEmail(), compra.getProdutoComprado().getDono().getEmail());
	}

}
