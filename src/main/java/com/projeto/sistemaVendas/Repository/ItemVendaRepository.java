package com.projeto.sistemaVendas.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.projeto.sistemaVendas.Models.ItemVenda;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
    @Query("SELECT e FROM ItemVenda e WHERE e.venda.id = ?1") 
    List<ItemVenda>findByVendaId(long id);
}
