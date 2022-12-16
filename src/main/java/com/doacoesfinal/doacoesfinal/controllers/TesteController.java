package com.doacoesfinal.doacoesfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.doacoesfinal.doacoesfinal.config.UsuarioSistema;
import com.doacoesfinal.doacoesfinal.model.Produto;
import com.doacoesfinal.doacoesfinal.model.Usuario;
import com.doacoesfinal.doacoesfinal.model.enums.CategoriaProduto;
import com.doacoesfinal.doacoesfinal.repository.ProdutoRepository;
import com.doacoesfinal.doacoesfinal.repository.UsuarioRepository;

@Controller
@RequestMapping("/api/teste")
public class TesteController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private Usuario usuario;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@GetMapping
    public ModelAndView home(Produto produto,@AuthenticationPrincipal Usuario usuarioLogado, Model model, BindingResult result){
		buscarUsuarioLogado();
	 	ModelAndView mv = new ModelAndView("/auth/PesquisaDonates");	 	
	 	mv.addObject("produtos", produtoRepository.findAll());
	 	mv.addObject("usuarios", usuario);
	 	//mv.addObject("testetestando", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
	 	mv.addObject("categoriaProduto", CategoriaProduto.values());
	 	return mv;
 }	
	
	
	private void buscarUsuarioLogado() {
    	Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
    	if(!(autenticado instanceof AnonymousAuthenticationToken)){
    		String username = autenticado.getName();
    		usuario = usuarioRepository.buscarUsuarioUsername(username).get(0);
    	}
	}
	
	@GetMapping("/filtrar")
    public ModelAndView filtroSelecionado(@RequestParam(required=false, name = "nomepesquisa") String nomepesquisa) {
       	
    	System.out.println("Minhas pesquisa: " + nomepesquisa);
    	
    	
    	buscarUsuarioLogado(); 
    	ModelAndView mv = new ModelAndView(("/auth/PesquisaDonates"));
    	mv.addObject("usuarios", usuario);
   	    mv.addObject("produtos", produtoRepository.findProdutoByNome(nomepesquisa));
    	return mv;
    }
	
	
	
	
}
