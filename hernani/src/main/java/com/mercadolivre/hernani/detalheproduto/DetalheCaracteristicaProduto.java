package com.mercadolivre.hernani.detalheproduto;

import com.mercadolivre.hernani.cadastroProduto.CaracteristicaProduto;

public class DetalheCaracteristicaProduto {
	

	private String nome;
	private String descricao;

	public DetalheCaracteristicaProduto(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
