package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Entrada;
import com.projeto.sistemaVendas.Repository.EntradaRepository;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public Entrada createEntrada(Entrada entrada){
        Entrada newEntrada = entrada;
        return entradaRepository.save(newEntrada);
    }

    public List<Entrada> getAllEntradas() throws Exception{
        List<Entrada> listaEntradas = this.entradaRepository.findAll();
        if(listaEntradas.isEmpty()){
            throw new Exception("Entradas não encontrados");
        }
        return (listaEntradas);
    }

    public Entrada findEntradaById(Long id) throws Exception{
        return this.entradaRepository.findById(id).orElseThrow(() -> new Exception("Entrada não encontrado!"));
    }

    public String deleteEntrada(Entrada entrada) throws Exception{
          try {
            entradaRepository.delete(entrada);
            return ("Entrada deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Entrada não encontrado!");
        }
    }


    


}
