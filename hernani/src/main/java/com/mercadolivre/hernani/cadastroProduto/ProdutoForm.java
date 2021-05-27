package com.mercadolivre.hernani.cadastroProduto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.mercadolivre.hernani.cadastrocategoria.Categoria;
import com.mercadolivre.hernani.cadastrocategoria.CategoriaRepository;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.compartilhado.ExisteObjeto;


public class ProdutoForm {

	@NotBlank
	private String nome;
	
	@NotNull 
	@Positive
	private BigDecimal valor;
	
	@Positive
	private int quantidade;
	
	@NotBlank 
	@Length(max = 1000)
	private  String descricao;
	
	@NotNull
	@ExisteObjeto(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;
	
	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaForm> caracteristicas = new ArrayList<>();

	public ProdutoForm(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Long categoriaId,
			@Size(min = 3) @Valid List<NovaCaracteristicaForm> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	//necessario para que retorne os erros no campo do objeto categoria
	public List<NovaCaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto converte(CategoriaRepository categoriarepository, Usuario dono) {
		Categoria categoria = categoriarepository.findById(categoriaId).get();
		return new Produto(nome, valor, quantidade, descricao, categoria, dono, caracteristicas);
	}

	/*metodo para verificar se caracteristicas possuem nomes iguals*/
	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		// 1
		for (NovaCaracteristicaForm caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			// 1
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}
	

	@Override
	public String toString() {
		return "ProdutoForm [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", categoriaId=" + categoriaId + ", caracteristicas=" + caracteristicas + "]";
	}
	
	
}
