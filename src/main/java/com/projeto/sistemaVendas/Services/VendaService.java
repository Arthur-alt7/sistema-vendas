package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Venda;
import com.projeto.sistemaVendas.Models.ItemVenda;
import com.projeto.sistemaVendas.Repository.VendaRepository;
import com.projeto.sistemaVendas.Repository.ItemVendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
  

    public Venda createVenda(Venda venda){
        Venda newVenda = venda;
        return vendaRepository.save(newVenda);
    }

    public List<Venda> getAllVendas() throws Exception{
        List<Venda> listaVendas = this.vendaRepository.findAll();
        if(listaVendas.isEmpty()){
            throw new Exception("Vendas não encontrados");
        }
        return (listaVendas);
    }

    public Venda findVendaById(Long id) throws Exception{
        return this.vendaRepository.findById(id).orElseThrow(() -> new Exception("Venda não encontrado!"));
    }

    public String deleteVenda(Venda venda) throws Exception{
          try {
            vendaRepository.delete(venda);
            return ("Venda deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Venda não encontrado!");
        }
    }



    


    


}
