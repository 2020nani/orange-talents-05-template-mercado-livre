package com.mercadolivre.hernani.testecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.hernani.adicionaopiniao.AdicionaOpiniaoRepository;
import com.mercadolivre.hernani.adicionaopiniao.Opiniao;
import com.mercadolivre.hernani.adicionapergunta.Pergunta;
import com.mercadolivre.hernani.adicionapergunta.PerguntaRepository;
import com.mercadolivre.hernani.cadastroProduto.CaracteristicaProduto;
import com.mercadolivre.hernani.cadastroProduto.ImagemProduto;
import com.mercadolivre.hernani.cadastroProduto.ImagemRepository;
import com.mercadolivre.hernani.cadastroProduto.Produto;
import com.mercadolivre.hernani.cadastroProduto.ProdutoRepository;
import com.mercadolivre.hernani.cadastrocategoria.Categoria;
import com.mercadolivre.hernani.cadastrocategoria.CategoriaRepository;
import com.mercadolivre.hernani.cadastrousuario.Usuario;
import com.mercadolivre.hernani.cadastrousuario.UsuarioRepository;

@RestController
public class TesteController {
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private ImagemRepository imagemrepository;
	
	@Autowired
	private AdicionaOpiniaoRepository opiniaorepository;

	@Autowired
	private CategoriaRepository categoriarepository;

	@Autowired
	private PerguntaRepository perguntarepository;

	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private CaracteristicaRepository caracteristicarepository;

	@GetMapping(value = "/usuarios")
	public List<Usuario> listaUsuario() {
		
		List<Usuario> lista = usuariorepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/categorias")
	public List<Categoria> listaCategoria() {
		
		List<Categoria> lista = categoriarepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/produtos")
	public List<Produto> listaProduto() {
		
		List<Produto> lista = produtorepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/caracteristicas")
	public List<CaracteristicaProduto> listaCaracteristica() {
		
		List<CaracteristicaProduto> lista = caracteristicarepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/imagens")
	public List<ImagemProduto> listaImagens() {
		
		List<ImagemProduto> lista = imagemrepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/opiniao")
	public List<Opiniao> listaOpiniao() {
		
		List<Opiniao> lista = opiniaorepository.findAll();
		return lista;
	}
	
	@GetMapping(value = "/perguntas")
	public List<Pergunta> listaPergunta() {
		
		List<Pergunta> lista = perguntarepository.findAll();
		return lista;
	}
	

}
