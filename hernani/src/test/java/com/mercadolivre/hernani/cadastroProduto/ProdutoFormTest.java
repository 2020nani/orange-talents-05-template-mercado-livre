package com.mercadolivre.hernani.cadastroProduto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProdutoFormTest {

	@ParameterizedTest
	@MethodSource("gerador")
	@DisplayName("Cria produto com diversos tipo caracteristicas")
	void test(int esperado, List<NovaCaracteristicaForm> caracteristicas) {
		ProdutoForm produtoform = new ProdutoForm("nome", BigDecimal.TEN, 10, "descricao", 1L, caracteristicas);
		Assertions.assertEquals(esperado, produtoform.buscaCaracteristicasIguais().size());
		
	}
	
	static Stream<Arguments> gerador() {
		return Stream.of(
				Arguments.of(0,List.of()),
				Arguments.of(0,
						List.of(new NovaCaracteristicaForm("key","value"),
								new NovaCaracteristicaForm("key2","value2"),
								new NovaCaracteristicaForm("key3","value3"))),
				Arguments.of(0,
						List.of(new NovaCaracteristicaForm("key","value"),
								new NovaCaracteristicaForm("key2","value2"))),
				Arguments.of(1,
						List.of(new NovaCaracteristicaForm("key","value"),
								new NovaCaracteristicaForm("key","value")
								)));
	}

}
