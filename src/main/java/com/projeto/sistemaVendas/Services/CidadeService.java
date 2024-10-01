package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Cidade;
import com.projeto.sistemaVendas.Repository.CidadeRepository;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade createCidade(Cidade cidade){
        Cidade newCidade = cidade;
        return cidadeRepository.save(newCidade);
    }

    public List<Cidade> getAllCidades() throws Exception{
        List<Cidade> listaCidades = this.cidadeRepository.findAll();
        if(listaCidades.isEmpty()){
            throw new Exception("Cidades não encontrados");
        }
        return (listaCidades);
    }

    public Cidade findCidadeById(Long id) throws Exception{
        return this.cidadeRepository.findById(id).orElseThrow(() -> new Exception("Cidade não encontrado!"));
    }

    public String deleteCidade(Cidade cidade) throws Exception{
          try {
            cidadeRepository.delete(cidade);
            return ("Cidade deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Cidade não encontrado!");
        }
    }


    


}
