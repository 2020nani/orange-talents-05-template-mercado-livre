package com.mercadolivre.hernani.cadastroProduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.util.Assert;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.mercadolivre.hernani.adicionaopiniao.Opiniao;
import com.mercadolivre.hernani.adicionapergunta.Pergunta;
import com.mercadolivre.hernani.cadastrocategoria.Categoria;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.config.security.UserDetailsSecurity;
import com.mercadolivre.hernani.detalheproduto.DetalheCaracteristicaProduto;
import com.mercadolivre.hernani.detalheproduto.Opinioes;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@Positive
	private int quantidade;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario dono;

	@OneToMany(mappedBy = "produto",cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto",cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto",cascade = CascadeType.MERGE)
	private Set<Pergunta> perguntas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@Future
	@CreationTimestamp
	private LocalDateTime momentoCriacao;

	

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario dono, @Size(min = 3) @Valid Collection<NovaCaracteristicaForm> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dono = dono;
		Set<CaracteristicaProduto> novasCaracteristicas = caracteristicas
				.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
		
		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minimo 3 caracteristicas");
	}
	
	/* metodo que associa as imagens e os links gerados com o produto */
	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}
	/*metodo que verifica se usuario que faz upload de imagem e dono do produto*/
	public boolean pertenceAoUsuario(UserDetailsSecurity user) {
		
		return this.dono.getEmail().equals(user.getUsername()) && this.dono.getId().equals(user.getId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getDono() {
		return dono;
	}
	public Opinioes getOpinioes() {
		return new Opinioes(this.opinioes);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dono=" + dono + ", caracteristicas="
				+ caracteristicas + ", imagens=" + imagens + ", momentoCriacao=" + momentoCriacao + "]";
	}

	public Set<DetalheCaracteristicaProduto> buscaCaracteristicas(Function<CaracteristicaProduto, DetalheCaracteristicaProduto > funcao) {
		
		return this.caracteristicas.stream().map(funcao).collect(Collectors.toSet());
	}

	public Set<String> buscaImagem(Function<ImagemProduto, String> funcao) {
		// TODO Auto-generated method stub
		return this.imagens.stream().map(funcao).collect(Collectors.toSet());
	}

	public Set<String> buscaPergunta(Function<Pergunta, String> funcao) {
		// TODO Auto-generated method stub
		return this.perguntas.stream().map(funcao).collect(Collectors.toSet());
	}


	
	
}
