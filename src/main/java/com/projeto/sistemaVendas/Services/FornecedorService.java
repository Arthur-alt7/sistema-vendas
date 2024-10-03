package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Fornecedor;
import com.projeto.sistemaVendas.Repository.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor createFornecedor(Fornecedor fornecedor){
        Fornecedor newFornecedor = fornecedor;
        return fornecedorRepository.save(newFornecedor);
    }

    public List<Fornecedor> getAllFornecedor() throws Exception{
        List<Fornecedor> listaFornecedor = this.fornecedorRepository.findAll();
        if(listaFornecedor.isEmpty()){
            throw new Exception("Fornecedor não encontrados");
        }
        return (listaFornecedor);
    }

    public Fornecedor findFornecedorById(Long id) throws Exception{
        return this.fornecedorRepository.findById(id).orElseThrow(() -> new Exception("Fornecedor não encontrado!"));
    }

    public String deleteFornecedor(Fornecedor fornecedor) throws Exception{
          try {
            fornecedorRepository.delete(fornecedor);
            return ("Fornecedor deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Fornecedor não encontrado!");
        }
    }


    


}
