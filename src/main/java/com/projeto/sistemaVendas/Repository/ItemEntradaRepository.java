package com.projeto.sistemaVendas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.sistemaVendas.Models.ItemEntrada;

@Repository
public interface ItemEntradaRepository extends JpaRepository<ItemEntrada, Long>{
    
}
