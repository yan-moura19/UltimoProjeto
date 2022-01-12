package com.br.mybanc.my_cliente.controller;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.mybanc.my_cliente.Cliente;

@FeignClient (name="cliente")
public interface EmployeeCliente {
	
	@RequestMapping("/cliente/find/{id}")
	public Cliente findById(@PathVariable(value="id") Long id);
	
	@RequestMapping("cliente/findall")
	public Collection<Cliente> findAll();
	

}
