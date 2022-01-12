package com.br.mybanc.my_contarepository;

import org.springframework.data.repository.CrudRepository;

import com.br.mybanc.my_conta.Conta;

public interface ContaRepository extends CrudRepository<Conta, Long>{

}
