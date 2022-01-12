package com.br.mybanc.my_cliente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.br.mybanc.my_cliente.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
