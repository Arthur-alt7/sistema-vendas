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

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrarEstado(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estado/cadastro");
        mv.addObject("estado", estado);
        return mv; 
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrarEstado(estado);
        }
        estadoRepository.saveAndFlush(estado);
        return cadastrarEstado(new Estado());
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return cadastrarEstado(estado.get());
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/estado/lista");
        mv.addObject("listaEstado", estadoRepository.findAll());
        return mv;
    }

    
}
