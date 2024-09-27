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
    private CidadeRepository CidadeRepository;

    public Cidade createCidade(Cidade Cidade){
        Cidade newCidade = Cidade;
        return CidadeRepository.save(newCidade);
    }

    public List<Cidade> getAllCidades() throws Exception{
        List<Cidade> listaCidades = this.CidadeRepository.findAll();
        if(listaCidades.isEmpty()){
            throw new Exception("Cidades não encontrados");
        }
        return (listaCidades);
    }

    public Cidade findCidadeById(Long id) throws Exception{
        return this.CidadeRepository.findById(id).orElseThrow(() -> new Exception("Cidade não encontrado!"));
    }

    public String deleteCidade(Cidade Cidade) throws Exception{
          try {
            CidadeRepository.delete(Cidade);
            return ("Cidade deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Cidade não encontrado!");
        }
    }


    


}
