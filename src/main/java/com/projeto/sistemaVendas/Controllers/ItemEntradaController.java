package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.ItemEntrada;
import com.projeto.sistemaVendas.Services.ItemEntradaService;
import com.projeto.sistemaVendas.Services.EstadoService;

@Controller
public class ItemEntradaController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private ItemEntradaService itemEntradaService;
    

    @GetMapping("/cadastroItemEntrada")
    public ModelAndView cadastrarItemEntrada(ItemEntrada itemEntrada) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/itemEntrada/cadastro");
        mv.addObject("ItemEntrada", itemEntrada);
        mv.addObject("listaEstados", estadoService.getAllEstados());
        return mv; 
    }

    @PostMapping("/salvarItemEntrada")
    public ModelAndView salvar(ItemEntrada itemEntrada, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarItemEntrada(itemEntrada);
        }
        itemEntradaService.createItemEntrada(itemEntrada);
        return cadastrarItemEntrada(new ItemEntrada());
    }

    @GetMapping("/listarItemEntrada")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/itemEntrada/listaItemEntrada");
        mv.addObject("listaItemEntradas", itemEntradaService.getAllItemEntradas());
        return mv;
    }

    @GetMapping("/editarItemEntrada/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<ItemEntrada> itemEntrada = ItemEntradaRepository.findById(Id);
        ItemEntrada itemEntrada = itemEntradaService.findItemEntradaById(id);
        //return cadastrarItemEntrada(itemEntrada.get());
        return cadastrarItemEntrada(itemEntrada);
    }

    @GetMapping("/removerItemEntrada/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<ItemEntrada> ItemEntrada = ItemEntradaRepository.findById(id);
        ItemEntrada itemEntrada = itemEntradaService.findItemEntradaById(id);
		itemEntradaService.deleteItemEntrada(itemEntrada);
		return listar();
		
	}
    
}
