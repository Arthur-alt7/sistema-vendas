package com.projeto.sistemaVendas.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistemaVendas.Models.Venda;
import com.projeto.sistemaVendas.Models.ItemVenda;
import com.projeto.sistemaVendas.Models.Produto;
import com.projeto.sistemaVendas.Repository.VendaRepository;
import com.projeto.sistemaVendas.Repository.ItemVendaRepository;
import com.projeto.sistemaVendas.Services.VendaService;
import com.projeto.sistemaVendas.Services.ClienteService;
import com.projeto.sistemaVendas.Services.FornecedorService;
import com.projeto.sistemaVendas.Services.FuncionarioService;
import com.projeto.sistemaVendas.Services.ItemVendaService;
import com.projeto.sistemaVendas.Services.ProdutoService;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clientService;
    @Autowired
    private ItemVendaService itemVendaService;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;
   
    
    private List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();


    @GetMapping("/cadastroVenda")
    public ModelAndView cadastrarVenda(Venda venda, ItemVenda itemVenda) throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/venda/cadastro");
        mv.addObject("venda", venda);
        mv.addObject("itemVenda", itemVenda);
        mv.addObject("listaItemVenda", this.listaItemVenda);
        mv.addObject("listaFuncionario", funcionarioService.getAllFuncionarios());
        mv.addObject("listaFornecedor", clientService.getAllClientes());
        mv.addObject("listaProduto", produtoService.getAllProdutos());
        return mv; 
    }

    @PostMapping("/salvarVenda")
	public ModelAndView salvar(String acao, Venda venda, ItemVenda itemVenda, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return cadastrarVenda(venda, itemVenda);
		}
		
		if(acao.equals("itens")) {
			this.listaItemVenda.add(itemVenda);
			venda.setValorTotal(venda.getValorTotal() + (itemVenda.getValor() * itemVenda.getQuantidade()));
			venda.setQuantidadeTotal(venda.getQuantidadeTotal() + itemVenda.getQuantidade());
			
		}else if(acao.equals("salvar")) {
            vendaService.createVenda(venda);
			
			for(ItemVenda item: listaItemVenda) {
				item.setVenda(venda);
				itemVendaService.createItemVenda(item);

                Produto prod = produtoService.findProdutoById(item.getProduto().getId());
				//Optional<Produto> prod = produtoRepositorio.findById(item.getProduto().getId());
				//Produto produto = prod.get();
				prod.setEstoque(prod.getEstoque() + item.getQuantidade());
				prod.setPrecoVenda(item.getValor());
				prod.setPrecoCusto(item.getSubTotal());
                produtoService.createProduto(prod);
				//produtoRepositorio.saveAndFlush(produto);
				
				this.listaItemVenda = new ArrayList<>();
			}
			return cadastrarVenda(new Venda(), new ItemVenda());
		}
		return cadastrarVenda(venda, new ItemVenda());
		
	
	}


    @GetMapping("/listarVenda")
    public ModelAndView listar() throws Exception {
        ModelAndView mv = new ModelAndView("administrativo/venda/listaVenda");
        mv.addObject("listaVenda", vendaService.getAllVendas());
        return mv;
    }

 //   @GetMapping("/editarVenda/{id}")
 //   public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
 //       //Optional<Venda> venda = VendaRepository.findById(Id);
 //       Venda venda = vendaService.findVendaById(id);
 //       this.listaItemVenda = itemVendaService.buscarPorVenda(id);
 //       //return cadastrarVenda(venda.get());
 //       return cadastrarVenda(venda, new ItemVenda());
 //   } 

    @GetMapping("/editarVenda/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) throws Exception {
    	Optional<Venda> venda = vendaRepository.findById(id);
 		this.listaItemVenda = itemVendaRepository.findByVendaId(venda.get().getId());
 		
 		return cadastrarVenda(venda.get(), new ItemVenda());
 		
 	}

    @GetMapping("/removerVenda/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) throws Exception {
		//Optional<Venda> Venda = VendaRepository.findById(id);
        Venda venda = vendaService.findVendaById(id);
		vendaService.deleteVenda(venda);
		return listar();
		
	}

    
    
}
