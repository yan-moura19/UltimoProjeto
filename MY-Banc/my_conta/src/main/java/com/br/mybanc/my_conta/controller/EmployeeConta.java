package com.br.mybanc.my_conta.controller;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.mybanc.my_conta.Conta;

@FeignClient (name="conta")
public interface EmployeeConta {
	
	
	@RequestMapping("/conta/find/{id}")
	public Conta findById(@PathVariable(value="id") Long id);
	
	@RequestMapping("conta/findall")
	public Collection<Conta> findAll();

}
