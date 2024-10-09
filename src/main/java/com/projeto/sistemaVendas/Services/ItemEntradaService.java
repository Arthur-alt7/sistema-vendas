package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.ItemEntrada;
import com.projeto.sistemaVendas.Repository.ItemEntradaRepository;

@Service
public class ItemEntradaService {

    @Autowired
    private ItemEntradaRepository itemEntradaRepository;

    public ItemEntrada createItemEntrada(ItemEntrada itemEntrada){
        ItemEntrada newItemEntrada = itemEntrada;
        return itemEntradaRepository.save(newItemEntrada);
    }

    public List<ItemEntrada> getAllItemEntradas() throws Exception{
        List<ItemEntrada> listaItemEntradas = this.itemEntradaRepository.findAll();
        if(listaItemEntradas.isEmpty()){
            throw new Exception("ItemEntradas não encontrados");
        }
        return (listaItemEntradas);
    }

    public ItemEntrada findItemEntradaById(Long id) throws Exception{
        return this.itemEntradaRepository.findById(id).orElseThrow(() -> new Exception("ItemEntrada não encontrado!"));
    }

    public String deleteItemEntrada(ItemEntrada itemEntrada) throws Exception{
          try {
            itemEntradaRepository.delete(itemEntrada);
            return ("ItemEntrada deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ItemEntrada não encontrado!");
        }
    }


    


}
