package com.doacoesfinal.doacoesfinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doacoesfinal.doacoesfinal.model.Cidade;
import com.doacoesfinal.doacoesfinal.repository.CidadeRepository;

@Controller
@RequestMapping("/cidade")
public class CidadeController {

	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(@RequestParam(name = "estado", defaultValue = "-1") Long id){
		
		return cidadeRepository.findByEstadoId(id);
	}
}
