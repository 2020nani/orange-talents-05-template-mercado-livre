package com.mercadolivre.hernani.cadastrocategoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.hernani.compartilhado.UniqueValue;

public class CategoriaForm {
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@Positive
	private Long categoriaId;
	public CategoriaForm(@NotBlank String nome, @Positive Long categoriaId) {
		super();
		this.nome = nome;
		this.categoriaId = categoriaId;
	}
	
	public Categoria converte(CategoriaRepository categoriarepository) {
		Categoria categoria = null;
		if(categoriaId != null) {
		 categoria = categoriarepository.findById(categoriaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nao existe categoria o Id passado"));
		 Assert.notNull(categoria,"O objeto com id passado nao pode ser nulo");
		}
		return new Categoria(nome, categoria);
	}
	

}
