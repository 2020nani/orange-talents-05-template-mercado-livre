package com.mercadolivre.hernani.detalheproduto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastroProduto.ProdutoRepository;
import com.mercadolivre.hernani.cadastrocategoria.Categoria;

@RestController
public class DetalheProdutoController {
	
	@Autowired
	private ProdutoRepository produtorepository;

	@GetMapping(value = "/produtos/{id}")
	public DetalheProdutoFront listaDetalheProduto(@PathVariable("id") Long id) {
		
		Produto produto = produtorepository.findById(id).get();
		
		return new DetalheProdutoFront(produto);
	}

}
