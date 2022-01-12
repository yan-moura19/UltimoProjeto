package com.br.mybanc.my_transacao;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import reactor.util.annotation.NonNull;

@Entity
public class Transacao<Conta> {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idTransacao;
	
	
	@NonNull private double valor;
	@NonNull private LocalDate dataTransacao = LocalDate.now();
	@ManyToOne(optional = true)
	private Conta idConta;
	
	
	
	
	public Transacao(Conta idConta, double valor) {//sub entendido que o id e data serão "automáticos"
		super();
		this.idConta = idConta;
		this.valor = valor;
	}
	public Transacao() {
		
	}
	public long getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(long idTransacao) {
		this.idTransacao = idTransacao;
	}
	public Conta getIdConta() {
		return idConta;
	}
	public void setIdConta(Conta idConta) {
		this.idConta = idConta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDate getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(@NonNull LocalDate dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

}
