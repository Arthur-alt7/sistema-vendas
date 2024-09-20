package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Estado;
import com.projeto.sistemaVendas.Repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado createEstado(Estado estado){
        Estado newEstado = estado;
        return estadoRepository.save(newEstado);
    }

    public List<Estado> getAllEstados() throws Exception{
        List<Estado> listaEstados = this.estadoRepository.findAll();
        if(listaEstados.isEmpty()){
            throw new Exception("Estados não encontrados");
        }
        return (listaEstados);
    }

    public Estado findEstadoById(Long Id) throws Exception{
        return this.estadoRepository.findById(Id).orElseThrow(() -> new Exception("Estado não encontrado!"));
    }

    public String deleteEstado(Estado estado) throws Exception{
          try {
            estadoRepository.delete(estado);
            return ("Estado deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Estado não encontrado!");
        }
    }


    


}
