package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Cliente;
import com.projeto.sistemaVendas.Services.ClienteService;
import com.projeto.sistemaVendas.Services.CidadeService;

@Controller
public class ClienteController {

    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private ClienteService clienteService;
    

    @GetMapping("/cadastroCliente")
    public ModelAndView cadastrarCliente(Cliente cliente) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/cliente/cadastro");
        mv.addObject("cliente", cliente);
        mv.addObject("listaCidades", cidadeService.getAllCidades());
        return mv; 
    }

    @PostMapping("/salvarCliente")
    public ModelAndView salvar(Cliente cliente, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarCliente(cliente);
        }
        clienteService.createCliente(cliente);
        return cadastrarCliente(new Cliente());
    }

    @GetMapping("/listarCliente")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/cliente/listaCliente");
        mv.addObject("listaClientes", clienteService.getAllClientes());
        return mv;
    }

    @GetMapping("/editarCliente/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Cliente> cliente = ClienteRepository.findById(Id);
        Cliente cliente = clienteService.findClienteById(id);
        //return cadastrarCliente(Cliente.get());
        return cadastrarCliente(cliente);
    }

    @GetMapping("/removerCliente/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Cliente> Cliente = clienteRepository.findById(id);
        Cliente cliente = clienteService.findClienteById(id);
		clienteService.deleteCliente(cliente);
		return listar();
		
	}
    
}
