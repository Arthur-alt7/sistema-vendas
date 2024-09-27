package com.projeto.sistemaVendas.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistemaVendas.Models.Estado;
import com.projeto.sistemaVendas.Repository.EstadoRepository;
import com.projeto.sistemaVendas.Services.EstadoService;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoService estadoService;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrarEstado(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estado/cadastro");
        mv.addObject("estado", estado);
        return mv; 
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result) {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarEstado(estado);
        }
        estadoService.createEstado(estado);
        return cadastrarEstado(new Estado());
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/estado/listaEstado");
        mv.addObject("listaEstados", estadoService.getAllEstados());
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Estado> estado = estadoRepository.findById(Id);
        Estado estado = estadoService.findEstadoById(id);
        //return cadastrarEstado(estado.get());
        return cadastrarEstado(estado);
    }

    @GetMapping("/removerEstado/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Estado> estado = estadoRepository.findById(id);
        Estado estado = estadoService.findEstadoById(id);
		estadoService.deleteEstado(estado);
		return listar();
		
	}
    
}
