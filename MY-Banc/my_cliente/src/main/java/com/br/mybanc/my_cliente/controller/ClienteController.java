package com.br.mybanc.my_cliente.controller;

import java.util.Optional;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.mybanc.my_cliente.Cliente;
import com.br.mybanc.my_cliente.repository.ClienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente")
@Api(value="API REST clientes")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class ClienteController {
	@Autowired
	ClienteRepository clienteRepository;
	

	@RequestMapping("/feign/")
	@ApiOperation(value="Retorna lista")
	public Model listaPessoa(Model model) {
		model.addAttribute("contas", clienteRepository.findAll());
		return model;
	}
	@GetMapping("/feign/add")
	@ApiOperation(value="Retorna o formulario")
	public String pessoaForm(Model model) {
		model.addAttribute("pessoa", new Cliente());
		return "pessoaForm";
	}
	@PostMapping("/feign/process")
	@ApiOperation(value="Salva o cliente")
	public String processForm(@Validated Cliente pessoa, BindingResult result) {
		if(result.hasErrors()) {
			return "pessoaForm";
		}
		clienteRepository.save(pessoa);
		
		return "redirect:/";
		
	}
	@GetMapping("/feign/cliente/{id}")
	@ApiOperation(value="busca cliente por id ")
	public Optional<Cliente> conta(@PathVariable(value ="id") Long idConta) {
		Optional<Cliente> model = clienteRepository.findById(idConta);
		return model;
		
	}
}
