package com.mercadolivre.hernani.adicionapergunta;

import javax.validation.constraints.NotBlank;

import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastrousuario.Usuario;

public class PerguntaForm {
	
	@NotBlank
	private String titulo;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta converte(Produto produto, Usuario usuario) {
		
		return new Pergunta(titulo, usuario, produto);
	} 

}
