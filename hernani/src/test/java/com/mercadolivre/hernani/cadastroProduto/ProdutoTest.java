package com.mercadolivre.hernani.cadastroProduto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.mercadolivre.hernani.cadastrocategoria.Categoria;
import com.mercadolivre.hernani.cadastrousuario.Usuario;

class ProdutoTest {
	
	@DisplayName("um produto precisa minimo 3 caracteristicas")
	@ParameterizedTest
	@MethodSource("geradorTeste1")
	void test3(Collection<NovaCaracteristicaForm> caracteristicas) throws Exception {
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("email@email.com", "123456");
		Produto produto = new Produto("teste", BigDecimal.TEN, 10, "descricao", categoria, dono, caracteristicas);
	
	}
	
	static Stream<Arguments> geradorTeste1() {
		return Stream.of(
				Arguments.of(
						List.of(new NovaCaracteristicaForm("key","value"),
								new NovaCaracteristicaForm("key2","value2"),
								new NovaCaracteristicaForm("key3","value3"))),
				Arguments.of(
						List.of(new NovaCaracteristicaForm("key","value"),
								new NovaCaracteristicaForm("key2","value2"),
								new NovaCaracteristicaForm("key3","value3"),
								new NovaCaracteristicaForm("key4","value4")
								)));
	}
	
	@DisplayName("um produto nao pode ser criado com menos de 3 caracteristicas")
	@ParameterizedTest
	@MethodSource("geradorTeste2")
	void test4(Collection<NovaCaracteristicaForm> caracteristicas) throws Exception {
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("email@email.com", "123456");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Produto("teste", BigDecimal.TEN, 10, "descricao", categoria, dono, caracteristicas);
		});
		}
	
	static Stream<Arguments> geradorTeste2() {
		return Stream.of(
				Arguments.of(
						List.of(new NovaCaracteristicaForm("key","value"),
								new NovaCaracteristicaForm("key2","value2"))),
				Arguments.of(
						List.of(new NovaCaracteristicaForm("key","value")
								)));
	}

	@DisplayName("verifica estoque produto")
	@ParameterizedTest
	@CsvSource({"1,1,true", "1,2,true", "1,5,false"})
	void test(int estoque, int quantidadePedida, boolean resultadoEsperado) throws Exception {
		List<NovaCaracteristicaForm> caracteristicas = List.of(
				new NovaCaracteristicaForm("key","value"),
				new NovaCaracteristicaForm("key2","value2"),
				new NovaCaracteristicaForm("key3","value3"));
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("email@email.com", "123456");
		Produto produto = new Produto("teste", BigDecimal.TEN, estoque, "descricao", categoria, dono, caracteristicas);
		
		boolean resultado = produto.atualizaEstoque(quantidadePedida);
	}
	
	@DisplayName("nao aceita abater estoque <= zero")
	@ParameterizedTest
	@CsvSource({"0", "-1", "-100"})
	void test2(int estoque) throws Exception {
		List<NovaCaracteristicaForm> caracteristicas = List.of(
				new NovaCaracteristicaForm("key","value"),
				new NovaCaracteristicaForm("key2","value2"),
				new NovaCaracteristicaForm("key3","value3"));
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("email@email.com", "123456");
		Produto produto = new Produto("teste", BigDecimal.TEN, estoque, "descricao", categoria, dono, caracteristicas);
		
		boolean retorno = produto.atualizaEstoque(estoque);
		
		Assertions.assertEquals(retorno, false);
	}

}
