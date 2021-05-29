package com.mercadolivre.hernani.detalheproduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.mercadolivre.hernani.adicionapergunta.Pergunta;
import com.mercadolivre.hernani.cadastroProduto.Produto;

public class DetalheProdutoFront {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<DetalheCaracteristicaProduto> caracteristicas;
	private Set<String> imagens;
	private Set<Map<String,String>> opinioes;
	private Set<String> perguntas;
	private double mediaNotas;
	private int total;

	public DetalheProdutoFront(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getValor();
		this.caracteristicas = produto.buscaCaracteristicas(DetalheCaracteristicaProduto::new);
		//(DetalheCaracteristicaProduto::new) = a (caracteristica -> new DetalheCaracteristicaProduto(caracteristica))
		this.imagens = produto.buscaImagem(imagem -> imagem.getLink());
		this.perguntas = produto.buscaPergunta(pergunta -> pergunta.getTitulo());
		Opinioes opinioes = produto.getOpinioes();		
		this.opinioes = opinioes.buscaOpiniao(opiniao -> {
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
		
		this.mediaNotas = opinioes.media();
		this.total = opinioes.total();
	}
		
		
	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public Set<String> getImagens() {
		return imagens;
	}
	
	public Set<DetalheCaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Set<String> getPerguntas() {
		return perguntas;
	}
	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}
	public double getMediaNotas() {
		return mediaNotas;
	}
	public int getTotal() {
		return total;
	}
	

}
