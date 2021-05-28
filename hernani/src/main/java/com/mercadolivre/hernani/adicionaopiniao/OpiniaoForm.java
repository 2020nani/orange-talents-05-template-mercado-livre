package com.mercadolivre.hernani.adicionaopiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.config.security.UserDetailsSecurity;

import io.jsonwebtoken.lang.Assert;

public class OpiniaoForm {
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	@Min(1)
	@Max(5)
	private int nota;

	

	public OpiniaoForm(@NotBlank String titulo, @NotBlank @Size(max = 500) String descricao, @Min(1) @Max(5) int nota) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
	}



	@Override
	public String toString() {
		return "OpiniaoForm [titulo=" + titulo + ", descricao=" + descricao + ", nota=" + nota + "]";
	}



	public Opiniao converte(@NotNull @Valid Produto produto,@NotNull @Valid Usuario usuario) {
		Assert.state(usuario!=null,"usuario tem que estar logado");
		Assert.state(produto!=null,"E obrigatorio um produto estar associado com a opiniao");
		return new Opiniao(titulo, descricao, nota, usuario, produto);
	}
	
	

}
