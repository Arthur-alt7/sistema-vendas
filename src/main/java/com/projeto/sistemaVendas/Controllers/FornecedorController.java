package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Fornecedor;
import com.projeto.sistemaVendas.Services.FornecedorService;
import com.projeto.sistemaVendas.Services.CidadeService;

@Controller
public class FornecedorController {

    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private FornecedorService fornecedorService;
    

    @GetMapping("/cadastroFornecedor")
    public ModelAndView cadastrarFornecedor(Fornecedor fornecedor) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/fornecedor/cadastro");
        mv.addObject("fornecedor", fornecedor);
        mv.addObject("listaCidades", cidadeService.getAllCidades());
        return mv; 
    }

    @PostMapping("/salvarFornecedor")
    public ModelAndView salvar(Fornecedor fornecedor, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarFornecedor(fornecedor);
        }
        fornecedorService.createFornecedor(fornecedor);
        return cadastrarFornecedor(new Fornecedor());
    }

    @GetMapping("/listarFornecedor")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/fornecedor/listaFornecedor");
        mv.addObject("listaFornecedor", fornecedorService.getAllFornecedor());
        return mv;
    }

    @GetMapping("/editarFornecedor/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Fornecedor> fornecedor = FornecedorRepository.findById(Id);
        Fornecedor fornecedor = fornecedorService.findFornecedorById(id);
        //return cadastrarFornecedor(Fornecedor.get());
        return cadastrarFornecedor(fornecedor);
    }

    @GetMapping("/removerFornecedor/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Fornecedor> Fornecedor = fornecedorRepository.findById(id);
        Fornecedor fornecedor = fornecedorService.findFornecedorById(id);
		fornecedorService.deleteFornecedor(fornecedor);
		return listar();
		
	}
    
}
