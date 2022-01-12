package com.br.mybanc.my_conta.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.mybanc.my_conta.Conta;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/conta")

@Api(value="API REST clientes")
@CrossOrigin(origins="*")

public class ContaController {

	

	@Autowired
	com.br.mybanc.my_contarepository.ContaRepository contaRepository;
	
	
	@RequestMapping("/feign/findall")
	@ApiOperation(value="Retorna lista")
	public Model listaConta(Model model) {
		model.addAttribute("contas",contaRepository.findAll());
		return model;
		
	}
	@GetMapping("/add")
	
	public <Cliente> String contaForm(Model model) {
		model.addAttribute("conta", new Conta<Cliente>());
		return "contaForm";
	}
	@PostMapping("/process")
	@ApiOperation(value="salva a conta")
	public String processForm(@Validated Conta conta, BindingResult result) {
		if(result.hasErrors()) {
			return "contaForm";
		}
		contaRepository.save(conta);
		
		return "redirect:/";
	}
	@GetMapping("/conta/{id}")
	@ApiOperation(value="Retorna conta por id")
	public Optional<Conta> conta(@PathVariable(value ="id") Long idConta) {
		Optional<Conta> model = contaRepository.findById(idConta);
		return model;
		
	}
	@RequestMapping("feign/conta/{id}")
	@ApiOperation(value="Retorna conta por id")
	public Optional<Conta> contaFeign(@PathVariable(value ="id") Long idConta) {
		Optional<Conta> model = contaRepository.findById(idConta);
		return model;
		
	}
	
}
