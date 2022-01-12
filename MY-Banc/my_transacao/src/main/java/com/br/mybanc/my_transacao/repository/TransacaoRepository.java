package com.br.mybanc.my_transacao.repository;

import org.springframework.data.repository.CrudRepository;

public interface TransacaoRepository<Transacao> extends CrudRepository<Transacao, Long>{

}
