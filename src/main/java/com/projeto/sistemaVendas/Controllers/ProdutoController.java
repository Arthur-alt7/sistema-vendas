package com.projeto.sistemaVendas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Produto;
import com.projeto.sistemaVendas.Services.ProdutoService;
import com.projeto.sistemaVendas.Services.EstadoService;

@Controller
public class ProdutoController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private ProdutoService produtoService;
    

    @GetMapping("/cadastroProduto")
    public ModelAndView cadastrarProduto(Produto produto) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/produto/cadastro");
        mv.addObject("produto", produto);
        mv.addObject("listaEstados", estadoService.getAllEstados());
        return mv; 
    }

    @PostMapping("/salvarProduto")
    public ModelAndView salvar(Produto produto, BindingResult result) throws Exception {      //BindingResult é uma interface que fornece informações sobre erros e problemas de validação encontrados durante o processo de vinculação de dados. É usada principalmente para verificar e manipular erros de validação em formulários que são enviados para o servidor.
        if(result.hasErrors()) {
            return cadastrarProduto(produto);
        }
        produtoService.createProduto(produto);
        return cadastrarProduto(new Produto());
    }

    @GetMapping("/listarProduto")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/produto/listaProduto");
        mv.addObject("listaProdutos", produtoService.getAllProdutos());
        return mv;
    }

    @GetMapping("/editarProduto/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
        //Optional<Produto> produto = ProdutoRepository.findById(Id);
        Produto produto = produtoService.findProdutoById(id);
        //return cadastrarProduto(produto.get());
        return cadastrarProduto(produto);
    }

    @GetMapping("/removerProduto/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Produto> Produto = ProdutoRepository.findById(id);
        Produto produto = produtoService.findProdutoById(id);
		produtoService.deleteProduto(produto);
		return listar();
		
	}
    
}
