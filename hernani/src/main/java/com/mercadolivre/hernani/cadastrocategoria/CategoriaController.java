package com.mercadolivre.hernani.cadastrocategoria;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriarepository;

	@PostMapping(value = "/categorias")
	public void criaCategoria(@RequestBody @Valid CategoriaForm categoriaform) {
		Categoria categoria = categoriaform.converte(categoriarepository);
		categoriarepository.save(categoria);
		
	}

}
