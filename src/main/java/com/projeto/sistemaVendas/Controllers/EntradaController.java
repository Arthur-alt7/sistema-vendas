package com.projeto.sistemaVendas.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Entrada;
import com.projeto.sistemaVendas.Models.ItemEntrada;
import com.projeto.sistemaVendas.Models.Produto;
import com.projeto.sistemaVendas.Services.EntradaService;
import com.projeto.sistemaVendas.Services.EstadoService;
import com.projeto.sistemaVendas.Services.FornecedorService;
import com.projeto.sistemaVendas.Services.FuncionarioService;
import com.projeto.sistemaVendas.Services.ItemEntradaService;
import com.projeto.sistemaVendas.Services.ProdutoService;

@Controller
public class EntradaController {

    @Autowired
    private EntradaService entradaService;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private ItemEntradaService itemEntradaService;
    
    private List<ItemEntrada> listaItemEntrada = new ArrayList<ItemEntrada>();


    @GetMapping("/cadastroEntrada")
    public ModelAndView cadastrarEntrada(Entrada entrada, ItemEntrada itemEntrada) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
        mv.addObject("Entrada", entrada);
        mv.addObject("itemEntrada", itemEntrada);
        mv.addObject("listaItemEntrada", this.listaItemEntrada);
        mv.addObject("listaFuncionario", funcionarioService.getAllFuncionarios());
        mv.addObject("listaFornecedor", fornecedorService.getAllFornecedor());
        mv.addObject("listaProduto", produtoService.getAllProdutos());
        return mv; 
    }

    @PostMapping("/salvarEntrada")
	public ModelAndView salvar(String acao, Entrada entrada, ItemEntrada itemEntrada, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return cadastrarEntrada(entrada, itemEntrada);
		}
		
		if(acao.equals("itens")) {
			this.listaItemEntrada.add(itemEntrada);
			entrada.setValorTotal(entrada.getValorTotal() + (itemEntrada.getValor() * itemEntrada.getQuantidade()));
			entrada.setQuantidadeTotal(entrada.getQuantidadeTotal() + itemEntrada.getQuantidade());
			
		}else if(acao.equals("salvar")) {
            entradaService.createEntrada(entrada);
			
			for(ItemEntrada item: listaItemEntrada) {
				item.setEntrada(entrada);
				itemEntradaService.createItemEntrada(item);

                Produto prod = produtoService.findProdutoById(item.getProduto().getId());
				//Optional<Produto> prod = produtoRepositorio.findById(item.getProduto().getId());
				//Produto produto = prod.get();
				prod.setEstoque(prod.getEstoque() + item.getQuantidade());
				prod.setPrecoVenda(item.getValor());
				prod.setPrecoCusto(item.getValorCusto());
                produtoService.createProduto(prod);
				//produtoRepositorio.saveAndFlush(produto);
				
				this.listaItemEntrada = new ArrayList<>();
			}
			return cadastrarEntrada(new Entrada(), new ItemEntrada());
		}
		return cadastrarEntrada(entrada, new ItemEntrada());
		
	
	}


    @GetMapping("/listarEntrada")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/entrada/listaEntrada");
        mv.addObject("listaEntradas", entradaService.getAllEntradas());
        return mv;
    }

/*     @GetMapping("/editarEntrada/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Entrada> entrada = EntradaRepository.findById(Id);
        Entrada entrada = entradaService.findEntradaById(id);
        //return cadastrarEntrada(entrada.get());
        return cadastrarEntrada(entrada);
    } */

    @GetMapping("/removerEntrada/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Entrada> Entrada = EntradaRepository.findById(id);
        Entrada entrada = entradaService.findEntradaById(id);
		entradaService.deleteEntrada(entrada);
		return listar();
		
	}

    
    
}
