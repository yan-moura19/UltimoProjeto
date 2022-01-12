package com.br.mybanc.my_conta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.br.mybanc.*;

@Entity
public class Conta<Cliente> {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idConta;
	
	@NonNull
	private long idPessoa;
	private double saldo = 0;
	private double limiteSaqueDiario= 2000.0;
	private boolean flagAtivo = false;
	@NonNull
	@Column(length = 4)
	private long tipoConta;
	@NonNull
	private LocalDate dataCriacao = LocalDate.now();
	
	public Conta() {
		
	}
	public long getIdConta() {
		return idConta;
	}
	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Cliente getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Cliente idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	

}
