package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Funcionario;
import com.projeto.sistemaVendas.Services.FuncionarioService;
import com.projeto.sistemaVendas.Services.CidadeService;
import com.projeto.sistemaVendas.Services.EstadoService;

@Controller
public class FuncionarioController {

    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private FuncionarioService funcionarioService;
    

    @GetMapping("/cadastroFuncionario")
    public ModelAndView cadastrarFuncionario(Funcionario funcionario) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/funcionario/cadastro");
        mv.addObject("Funcionario", funcionario);
        mv.addObject("listaCidades", cidadeService.getAllCidades());
        return mv; 
    }

    @PostMapping("/salvarFuncionario")
    public ModelAndView salvar(Funcionario funcionario, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarFuncionario(funcionario);
        }
        funcionarioService.createFuncionario(funcionario);
        return cadastrarFuncionario(new Funcionario());
    }

    @GetMapping("/listarFuncionario")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/funcionario/listaFuncionario");
        mv.addObject("listaFuncionarios", funcionarioService.getAllFuncionarios());
        return mv;
    }

    @GetMapping("/editarFuncionario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Funcionario> funcionario = FuncionarioRepository.findById(Id);
        Funcionario funcionario = funcionarioService.findFuncionarioById(id);
        //return cadastrarFuncionario(Funcionario.get());
        return cadastrarFuncionario(funcionario);
    }

    @GetMapping("/removerFuncionario/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Funcionario> Funcionario = funcionarioRepository.findById(id);
        Funcionario funcionario = funcionarioService.findFuncionarioById(id);
		funcionarioService.deleteFuncionario(funcionario);
		return listar();
		
	}
    
}
