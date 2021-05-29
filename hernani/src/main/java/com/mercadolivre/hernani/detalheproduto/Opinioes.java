package com.mercadolivre.hernani.detalheproduto;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mercadolivre.hernani.adicionaopiniao.Opiniao;


public class Opinioes {

	private Set<Opiniao> opinioes;

	public Opinioes(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	
	public <T> Set<T> buscaOpiniao(Function<Opiniao, T> funcao) {
		return this.opinioes.stream().map(funcao)
				.collect(Collectors.toSet());
	}

	public double media() {
		Set<Integer> notas = buscaOpiniao(opiniao -> opiniao.getNota());
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
		return possivelMedia.orElse(0.0);
	}	
	
	public int total() {
		return this.opinioes.size();
	}
	
	

}
