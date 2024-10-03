package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Produto;
import com.projeto.sistemaVendas.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto createProduto(Produto produto){
        Produto newProduto = produto;
        return produtoRepository.save(newProduto);
    }

    public List<Produto> getAllProdutos() throws Exception{
        List<Produto> listaProdutos = this.produtoRepository.findAll();
        if(listaProdutos.isEmpty()){
            throw new Exception("Produtos não encontrados");
        }
        return (listaProdutos);
    }

    public Produto findProdutoById(Long id) throws Exception{
        return this.produtoRepository.findById(id).orElseThrow(() -> new Exception("Produto não encontrado!"));
    }

    public String deleteProduto(Produto produto) throws Exception{
          try {
            produtoRepository.delete(produto);
            return ("Produto deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Produto não encontrado!");
        }
    }


    


}
