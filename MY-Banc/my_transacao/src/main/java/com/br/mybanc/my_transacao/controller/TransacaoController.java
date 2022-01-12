package com.br.mybanc.my_transacao.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.br.mybanc.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/transacao")

@Api(value="API REST transacao")
@CrossOrigin(origins="*")

public class TransacaoController<Transacao, Conta> {
	@Autowired
	com.br.mybanc.my_transacao.repository.TransacaoRepository transacaoRepository;

	
	@RequestMapping("/feign/findall")//retorna a lista de transaçoes 
	@ApiOperation(value="Retorna lista")
	public Model listaTransacao(Model model) {
		model.addAttribute("transacoes", transacaoRepository.findAll());
		return model;
	}
	@GetMapping("/add")
	public String transacaoForm(Model model) {
		model.addAttribute("transacao", new Object());
		return "transacaoForm";
	}
	@PostMapping("/process")
	@ApiOperation(value="salva a transacao")
	public  String processForm(@Validated Transacao transacao, BindingResult result) {
		if(result.hasErrors()) {
			return "pessoaForm";
		}
		transacaoRepository.save(transacao);
		
		return "redirect:/";
		
	}
	@GetMapping("/transacoes/{id}")
	@ApiOperation(value="Retorna transacao pelo id")
	public List<Transacao> extratoConta(@PathVariable(value = "id") Conta idConta) {
		Iterable<Transacao> model = transacaoRepository.findAll();
		List<Transacao> transacoesDaConta = new ArrayList<Transacao>();
		for (Transacao transacao : model) {
			if(((Transacao) transacao) == idConta) {
				transacoesDaConta.add(transacao);
			}
		}
		
		return transacoesDaConta;
	}
}
