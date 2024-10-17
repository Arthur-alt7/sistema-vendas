package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.ItemVenda;
import com.projeto.sistemaVendas.Services.ItemVendaService;
import com.projeto.sistemaVendas.Services.EstadoService;

@Controller
public class ItemVendaController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private ItemVendaService itemVendaService;
    

    @GetMapping("/cadastroItemVenda")
    public ModelAndView cadastrarItemVenda(ItemVenda itemVenda) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/itemVenda/cadastro");
        mv.addObject("ItemVenda", itemVenda);
        mv.addObject("listaEstados", estadoService.getAllEstados());
        return mv; 
    }

    @PostMapping("/salvarItemVenda")
    public ModelAndView salvar(ItemVenda itemVenda, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarItemVenda(itemVenda);
        }
        itemVendaService.createItemVenda(itemVenda);
        return cadastrarItemVenda(new ItemVenda());
    }

    @GetMapping("/listarItemVenda")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/itemVenda/listaItemVenda");
        mv.addObject("listaItemVendas", itemVendaService.getAllItemVendas());
        return mv;
    }

    @GetMapping("/editarItemVenda/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<ItemVenda> itemVenda = ItemVendaRepository.findById(Id);
        ItemVenda itemVenda = itemVendaService.findItemVendaById(id);
        //return cadastrarItemVenda(itemVenda.get());
        return cadastrarItemVenda(itemVenda);
    }

    @GetMapping("/removerItemVenda/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<ItemVenda> ItemVenda = ItemVendaRepository.findById(id);
        ItemVenda itemVenda = itemVendaService.findItemVendaById(id);
		itemVendaService.deleteItemVenda(itemVenda);
		return listar();
		
	}
    
}
