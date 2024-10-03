package com.projeto.sistemaVendas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.sistemaVendas.Models.Cliente;
import com.projeto.sistemaVendas.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente createCliente(Cliente cliente){
        Cliente newCliente = cliente;
        return clienteRepository.save(newCliente);
    }

    public List<Cliente> getAllClientes() throws Exception{
        List<Cliente> listaClientes = this.clienteRepository.findAll();
        if(listaClientes.isEmpty()){
            throw new Exception("Clientes não encontrados");
        }
        return (listaClientes);
    }

    public Cliente findClienteById(Long id) throws Exception{
        return this.clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
    }

    public String deleteCliente(Cliente cliente) throws Exception{
          try {
            clienteRepository.delete(cliente);
            return ("Cliente deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado!");
        }
    }


    


}
