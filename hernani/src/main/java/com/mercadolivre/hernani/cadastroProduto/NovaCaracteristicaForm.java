package com.mercadolivre.hernani.cadastroProduto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class NovaCaracteristicaForm {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public NovaCaracteristicaForm(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "NovaCaracteristicaForm [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		
		return new CaracteristicaProduto(nome,descricao,produto);
	}
	
	

}
