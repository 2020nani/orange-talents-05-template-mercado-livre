package com.mercadolivre.hernani.testecontroller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolivre.hernani.cadastroProduto.CaracteristicaProduto;

@Repository
public interface CaracteristicaRepository extends JpaRepository<CaracteristicaProduto, Long> {

}
