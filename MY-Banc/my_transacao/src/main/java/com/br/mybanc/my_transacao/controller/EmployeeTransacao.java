package com.br.mybanc.my_transacao.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.br.mybanc.my_transacao.Transacao;

@FeignClient (name="transacao")
public interface EmployeeTransacao {
	
	
	@RequestMapping("/transcao/find/{id}")
	public Optional idConta(@PathVariable(value="id") Long id);
	
	@RequestMapping("transacao/findall")
	public Collection<Transacao> findAll();
	

}
