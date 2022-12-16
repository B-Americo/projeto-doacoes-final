package com.doacoesfinal.doacoesfinal.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.doacoesfinal.doacoesfinal.model.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String login(@AuthenticationPrincipal User user) {
	/*	if(user != null) {
			return "redirect:/api/donate";
		}*/
		return "login";
	}
	
	 @RequestMapping("/novo")
	    public ModelAndView home(Usuario usuario){ 	
		 	ModelAndView mv = new ModelAndView("/auth/usuario-donate");
	        return mv;
	 }
}
