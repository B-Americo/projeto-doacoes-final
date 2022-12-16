package com.doacoesfinal.doacoesfinal.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.doacoesfinal.doacoesfinal.config.UsuarioSistema;
import com.doacoesfinal.doacoesfinal.model.ItensContrato;
import com.doacoesfinal.doacoesfinal.model.Produto;
import com.doacoesfinal.doacoesfinal.model.Usuario;
import com.doacoesfinal.doacoesfinal.model.enums.CategoriaProduto;
import com.doacoesfinal.doacoesfinal.repository.ProdutoRepository;
import com.doacoesfinal.doacoesfinal.repository.UsuarioRepository;

@Controller
@RequestMapping("/api/retirada")
public class DonateController {

	private List<ItensContrato> itensContratos = new ArrayList<ItensContrato>();
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	private Usuario usuario;
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public ModelAndView home(Produto produto, @AuthenticationPrincipal UsuarioSistema usuarioLogado, Model model, BindingResult result) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/aceito-donate");
		mv.addObject("listaItens", itensContratos);
		//model.addAttribute("produtos", new Produto());
		//mv.addObject("produtos", produtoRepository.findAll());
		//mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
		mv.addObject("usuarios", usuario);
		//mv.addObject("categoriaProduto", CategoriaProduto.values());
		return mv;
	}
	
	@GetMapping("/finalizar")
	public ModelAndView finalizarDonate() {
		//buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/finalizar-donate");
		mv.addObject("listaItens", itensContratos);
		//model.addAttribute("produtos", new Produto());
		//mv.addObject("produtos", produtoRepository.findAll());
		//mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
		//mv.addObject("usuarios", usuario);
		//mv.addObject("categoriaProduto", CategoriaProduto.values());
		return mv;
	}
	
	
	
	
	
	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public ModelAndView alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/aceito-donate");
		mv.addObject("listaItens", itensContratos);
		mv.addObject("usuarios", usuario);
		for(ItensContrato it:itensContratos) {
			if(it.getProduto().getId().equals(id)) {
				if(acao.equals(1)) {
				it.setQuantidade(it.getQuantidade()+1);
				}else if(acao == 0)
				it.setQuantidade(it.getQuantidade()-1);				
				break;
			}
		}
		
		
		//model.addAttribute("produtos", new Produto());
		//mv.addObject("produtos", produtoRepository.findAll());
		//mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
		//mv.addObject("usuarios", usuario);
		//mv.addObject("categoriaProduto", CategoriaProduto.values());
		mv.addObject("listaItens", itensContratos);
		return mv;
	}
	
	
	
	@GetMapping("/removerDonate/{id}")
	public ModelAndView removerDonate(@PathVariable Long id) {
		buscarUsuarioLogado();
		System.out.println("Item id: " + id);
		//buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/aceito-donate");
		mv.addObject("listaItens", itensContratos);
		mv.addObject("usuarios", usuario);
		for(ItensContrato it:itensContratos) {
			if(it.getProduto().getId().equals(id)) {
				itensContratos.remove(it);	
				break;
			}
		}
		
		
		//model.addAttribute("produtos", new Produto());
		//mv.addObject("produtos", produtoRepository.findAll());
		//mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
		//mv.addObject("usuarios", usuario);
		//mv.addObject("categoriaProduto", CategoriaProduto.values());
		mv.addObject("listaItens", itensContratos);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/donate/{id}")
	public ModelAndView home(@PathVariable Long id) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/aceito-donate");
		Optional<Produto> prod = produtoRepository.findById(id);
		mv.addObject("usuarios", usuario);
		Produto produto = prod.get();
		
		int controle =0;
		for(ItensContrato it:itensContratos) {
			if(it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade()+1);
				controle = 1;
				break;
			}
		}
		if(controle==0){
		
		
		ItensContrato item = new ItensContrato();
		item.setProduto(produto);
		item.setQuantidade(item.getQuantidade() + 1);
		itensContratos.add(item);
		}
		mv.addObject("listaItens", itensContratos);
		//model.addAttribute("produtos", new Produto());
		//mv.addObject("produtos", produtoRepository.findAll());
		//mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
		//mv.addObject("usuarios", usuario);
		//mv.addObject("categoriaProduto", CategoriaProduto.values());
		return mv;
	}
	
	
	
	private void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String username = autenticado.getName();
			usuario = usuarioRepository.buscarUsuarioUsername(username).get(0);
		}
	}
	
	
	@PostMapping("/finalizar/donate")
	public ModelAndView confirmarDonate() {
		ModelAndView mv = new ModelAndView("/auth/finalizado-donate");
		
		ItensContrato ic = new ItensContrato();
		
		ic.setUsuario(usuario);
		
		return mv;
	}
	
	
	
	
	
}
