package com.doacoesfinal.doacoesfinal.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tinylog.Logger;

import com.doacoesfinal.doacoesfinal.config.UsuarioSistema;
import com.doacoesfinal.doacoesfinal.model.Produto;
import com.doacoesfinal.doacoesfinal.model.Usuario;
import com.doacoesfinal.doacoesfinal.model.enums.CategoriaProduto;
import com.doacoesfinal.doacoesfinal.repository.EnderecoRepository;
import com.doacoesfinal.doacoesfinal.repository.ProdutoRepository;
import com.doacoesfinal.doacoesfinal.repository.UsuarioRepository;
import com.doacoesfinal.doacoesfinal.service.ProdutoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/donate")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuario;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@GetMapping
	public ModelAndView home(Produto produto, @AuthenticationPrincipal UsuarioSistema usuarioLogado, Model model, BindingResult result) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/cadastro-donate");
		model.addAttribute("produtos", new Produto());
		//mv.addObject("produtos", produtoRepository.findAll());
		mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogado(usuarioLogado.getUsername()));
		mv.addObject("usuarios", usuario);
		mv.addObject("categoriaProduto", CategoriaProduto.values());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(Produto produto, @AuthenticationPrincipal UsuarioSistema usuarioLogado ,@RequestParam("imagemDonate") MultipartFile file,
			BindingResult result, Model model, RedirectAttributes attributes) {
		
		
		buscarUsuarioLogado();
		if (result.hasErrors()) {
			return home(produto, usuarioLogado, model, result);
		}
		if (!file.isEmpty()) {

		}
		produto.setDataDonate(Calendar.getInstance());
		//produto.setUsuario(); // setando o usuario no
		System.out.println("Usuário logado no sistema: " + usuarioLogado.getUsuario().getUsername());
		produto.setUsuario(usuarioLogado.getUsuario());
		
		try {
			produto.setImagens(file.getBytes());
			System.out.println("Foto encontrada" + file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		

		// salvar produto
		produtoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Donate salvo com sucesso, obrigado pela colaboração!");
		System.out.println(">>>Deu certo: " + produto.getNome() + produto.getCondicao() + produto.getDescricao());
		return new ModelAndView("redirect:/api/donate");
	}

	@RequestMapping("/filtrar")
	public ModelAndView filtroSelecionado(Produto produto, @AuthenticationPrincipal UsuarioSistema usuarioLogado
			,@RequestParam(required = false, name = "nomepesquisa") String nomepesquisa) {

		System.out.println("Minhas pesquisa: " + nomepesquisa);

		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView(("/auth/cadastro-donate"));
		mv.addObject("usuarios", usuario);
		//mv.addObject("produtos", produtoRepository.findProdutoByNome(nomepesquisa));
	    mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogadoFiltroNome(usuarioLogado.getUsername(), nomepesquisa));
		return mv;
	}
	
	
	@RequestMapping("/filtrarAll")
	public ModelAndView filtroSelecionadoAll(Produto produto, @AuthenticationPrincipal UsuarioSistema usuarioLogado
			,@RequestParam(required = false, name = "nomepesquisa") String nomepesquisa) {

		System.out.println("Minhas pesquisa: " + nomepesquisa);

		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView(("/auth/listaall-donate"));
		mv.addObject("usuarios", usuario);
		//mv.addObject("produtos", produtoRepository.findProdutoByNome(nomepesquisa));
		mv.addObject("produtos", produtoRepository.findProdutoPesquisarByNome(nomepesquisa));
	    //mv.addObject("produtos", produtoRepository.findProdutoByUsuarioLogadoFiltroNome(usuarioLogado.getUsername(), nomepesquisa));
		mv.addObject("enderecos", enderecoRepository.findAll());

		return mv;
	}
	
	
	
	
	

	@GetMapping("/imagem/{idprod}")
	@ResponseBody
	public byte[] exibirImagem(@PathVariable("idprod") Long idprod) {

		Produto produto = this.produtoRepository.getReferenceById(idprod);
		return produto.getImagens();
	}

	@GetMapping("/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, Model model) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		if (produtoOptional.isEmpty()) {
			throw new IllegalArgumentException("Donate inválido");
		}
		model.addAttribute("produtos", produtoOptional.get());
		return "/api/donate/form";
	}

	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable("id") Long id, Model model) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		if (produtoOptional.isEmpty()) {
			throw new IllegalArgumentException("Donate inválido");
		}
		produtoRepository.delete(produtoOptional.get());
		return "redirect:/api/donate";
	}
	
	
	@GetMapping("/all")
	public ModelAndView listaAll(Produto produto, Model model, BindingResult result, @AuthenticationPrincipal UsuarioSistema usuarioLogado) {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("/auth/listaall-donate");
		model.addAttribute("produtos", new Produto());


		
		mv.addObject("usuarios", usuario);
		mv.addObject("categoriaProduto", CategoriaProduto.values());
		//mv.addObject("produtos", produtoRepository.findAll());
		mv.addObject("enderecos", enderecoRepository.findAll());
		mv.addObject("produtos", produtoRepository.findProdutoByUsuarioandItem());
		return mv;
	}
	
	
	
	
	

	private void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String username = autenticado.getName();
			usuario = usuarioRepository.buscarUsuarioUsername(username).get(0);
		}
	}

}