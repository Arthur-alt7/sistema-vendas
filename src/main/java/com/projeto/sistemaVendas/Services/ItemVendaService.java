package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.ItemVenda;
import com.projeto.sistemaVendas.Repository.ItemVendaRepository;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;
    //@Autowired
    //private ListaItemVenda listaItemVenda;

    public ItemVenda createItemVenda(ItemVenda itemVenda){
        ItemVenda newItemVenda = itemVenda;
        return itemVendaRepository.save(newItemVenda);
    }

    public List<ItemVenda> getAllItemVendas() throws Exception{
        List<ItemVenda> listaItemVendas = this.itemVendaRepository.findAll();
        if(listaItemVendas.isEmpty()){
            throw new Exception("ItemVendas não encontrados");
        }
        return (listaItemVendas);
    }

    public ItemVenda findItemVendaById(Long id) throws Exception{
        return this.itemVendaRepository.findById(id).orElseThrow(() -> new Exception("ItemVenda não encontrado!"));
    }

    public String deleteItemVenda(ItemVenda itemVenda) throws Exception{
          try {
            itemVendaRepository.delete(itemVenda);
            return ("ItemVenda deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ItemVenda não encontrado!");
        }
    }

    public List<ItemVenda> buscarPorEntrada(Long id){
        List<ItemVenda> listaItemVenda = this.itemVendaRepository.findByVendaId(id);
        return (listaItemVenda);
    } 


    


}
