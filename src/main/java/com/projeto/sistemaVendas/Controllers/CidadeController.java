package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Cidade;
import com.projeto.sistemaVendas.Services.CidadeService;
import com.projeto.sistemaVendas.Services.EstadoService;

@Controller
public class CidadeController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;
    

    @GetMapping("/cadastroCidade")
    public ModelAndView cadastrarCidade(Cidade Cidade) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/cidade/cadastro");
        mv.addObject("Cidade", Cidade);
        mv.addObject("listaEstados", estadoService.getAllEstados());
        return mv; 
    }

    @PostMapping("/salvarCidade")
    public ModelAndView salvar(Cidade cidade, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarCidade(cidade);
        }
        cidadeService.createCidade(cidade);
        return cadastrarCidade(new Cidade());
    }

    @GetMapping("/listarCidade")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/cidade/listaCidade");
        mv.addObject("listaCidades", cidadeService.getAllCidades());
        return mv;
    }

    @GetMapping("/editarCidade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Cidade> cidade = CidadeRepository.findById(Id);
        Cidade cidade = cidadeService.findCidadeById(id);
        //return cadastrarCidade(cidade.get());
        return cadastrarCidade(Cidade);
    }

    @GetMapping("/removerCidade/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Cidade> Cidade = CidadeRepository.findById(id);
        Cidade cidade = cidadeService.findCidadeById(id);
		cidadeService.deleteCidade(cidade);
		return listar();
		
	}
    
}
