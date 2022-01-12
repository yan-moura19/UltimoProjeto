package com.br.mybanc.my_cliente;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.micrometer.core.lang.NonNull;
@Entity
public class Cliente {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idPessoa;
	@NonNull private String nome;
	@NonNull private String cpf;
	@NonNull private Date dataNascimento;
	
	public Cliente(String nome, String cpf, Date date) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = date;
	}
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		
	}
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	public long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
