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
	@PutMapping(value ="/deposito/{id}")
	public Conta deposito  (@PathVariable(value = "id") Long idConta,@RequestBody Conta conta,double valor) {
		Optional<Conta> model = contaRepository.findById(idConta);
		
		if(model.isPresent()) {
			Conta _conta =model.get();
			_conta.setSaldo(_conta.getSaldo()+valor);
			
			transacaoRepository.save(idConta,valor, LocalDate.now());
			return new Conta(contaRepository.save(_conta));
			
		}
		return conta ;
		
	}
	@GetMapping("/saldo/{id}")
	public String saldoConta(@PathVariable(value = "id") Long idConta) {
		Optional<Conta> model = contaRepository.findById(idConta);
		
		if(model.isPresent()) {
			Conta _conta =model.get();
			return String.valueOf(_conta.getSaldo()) ;
		}else {
		
			return "não existe";
		}
	}
	@PutMapping(value ="/saque/{id}")
	public Conta saque  (@PathVariable(value = "id") Long idConta,@RequestBody Conta conta,double valor) {
		Optional<Conta> model = contaRepository.findById(idConta);
		
		if(model.isPresent()) {
			Conta _conta =model.get();
			
			if(_conta.isFlagAtivo()) {
				if(_conta.getSaldo() > valor) {
					_conta.setSaldo(_conta.getSaldo()-valor);
					transacaoRepository.save(idConta,valor, LocalDate.now());
					return new Conta(contaRepository.save(_conta));
				}else {
					return conta ;// sem saldo
				}
			}
			else {
				//bandeira não ativa
			}
				
		}
		return null;
	}
	
	@PutMapping(value ="/bloqueio/{id}")
	public Conta bloqueioConta  (@PathVariable(value = "id") Long idConta,@RequestBody Conta conta,double valor) {
		Optional<Conta> model = contaRepository.findById(idConta);
		
		if(model.isPresent()) {
			Conta _conta =model.get();
			_conta.setFlagAtivo(false);
			return new Conta(contaRepository.save(_conta));
			
		}
		return null;
	}
	@PutMapping(value ="/desbloqueio/{id}")
	public Conta desbloqueioConta  (@PathVariable(value = "id") Long idConta,@RequestBody Conta conta,double valor) {
		Optional<Conta> model = contaRepository.findById(idConta);
		
		if(model.isPresent()) {
			Conta _conta =model.get();
			
			_conta.setFlagAtivo(true);
			return new Conta(contaRepository.save(_conta));
		}
		return null;
	}
	
}
