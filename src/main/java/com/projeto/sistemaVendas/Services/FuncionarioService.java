package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Funcionario;
import com.projeto.sistemaVendas.Repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario createFuncionario(Funcionario funcionario){
        Funcionario newFuncionario = funcionario;
        return funcionarioRepository.save(newFuncionario);
    }

    public List<Funcionario> getAllFuncionarios() throws Exception{
        List<Funcionario> listaFuncionarios = this.funcionarioRepository.findAll();
        if(listaFuncionarios.isEmpty()){
            throw new Exception("Funcionarios não encontrados");
        }
        return (listaFuncionarios);
    }

    public Funcionario findFuncionarioById(Long id) throws Exception{
        return this.funcionarioRepository.findById(id).orElseThrow(() -> new Exception("Funcionario não encontrado!"));
    }

    public String deleteFuncionario(Funcionario funcionario) throws Exception{
          try {
            funcionarioRepository.delete(funcionario);
            return ("Funcionario deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Funcionario não encontrado!");
        }
    }


    


}
