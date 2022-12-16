package com.doacoesfinal.doacoesfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doacoesfinal.doacoesfinal.model.Cidade;
import com.doacoesfinal.doacoesfinal.model.Endereco;
import com.doacoesfinal.doacoesfinal.model.Estado;
import com.doacoesfinal.doacoesfinal.model.Grupo;
import com.doacoesfinal.doacoesfinal.model.Usuario;
import com.doacoesfinal.doacoesfinal.repository.CidadeRepository;
import com.doacoesfinal.doacoesfinal.repository.EnderecoRepository;
import com.doacoesfinal.doacoesfinal.repository.EstadoRepository;
import com.doacoesfinal.doacoesfinal.repository.GrupoRepository;
import com.doacoesfinal.doacoesfinal.service.UsuarioService;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired PasswordEncoder passwordEncoder;
	

	
	@GetMapping
    public ModelAndView home(Usuario usuario, Model model, BindingResult result){ 	
	 	ModelAndView mv = new ModelAndView("/auth/usuario-donate");
	 	model.addAttribute("usuarios", new Usuario());
	 	//model.addAttribute("cidades", new Cidade());
	 	//mv.addObject("usuario", new Usuario());
	 	mv.addObject("estados" , estadoRepository.findAll());
	 	mv.addObject("cidades" , cidadeRepository.findAll());
	 	mv.addObject("grupos" , grupoRepository.findAll());
	 	mv.addObject("enderecos", enderecoRepository.findAll());
        return mv;
 }
	
	
	
	
	
	
	
	
	
	 @RequestMapping(value = "/novo", method = RequestMethod.POST)
	    public ModelAndView cadastrar(Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes){		 
		 
		 if (result.hasErrors()){ 
	            return home(usuario, model, result);
	        }
	        //salvar produto
		 	
		 	 //cidade.setNome(cidade);
		 
		    usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
	      	//estadoRepository.save(estado);
	        usuarioService.salvar(usuario);
	        attributes.addFlashAttribute("mensagem", "Donate salvo com sucesso, obrigado pela colaboração!");
	        System.out.println(">>>Deu certo: " + usuario.getNome());
	        System.out.println(">>>>>>>CEP cadastrado: " + usuario.getEndereco().getCep());
	        System.out.println(">>>>>>>Cidade cadastrado: " +  usuario.getEndereco().getCidade().getNome());
	        System.out.println(">>>>>>>Cidade cadastrado: " +  usuario.getEndereco().getCidade().getEstado().getNome());
	        System.out.println(">>>>>>>Cidade cadastrado: " +  usuario.getGrupo().getNome());
	        return new ModelAndView("redirect:/login");
	    }

}
