package br.ufal.ic.FolhaPagamento;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Empregado {
	private int id;
	private String nome;
	private String endereco;
	private boolean sindicato;
	private int matriculaSindicato;
	private String metodoPagamento;
	
	protected double salarioBruto;
	protected double salarioLiquido;
	protected double salarioProximoMes;
	protected boolean debitoProximoMes = false;
	
	protected Date pontoInicio;
	protected Date pontoFim;
	
	public Empregado(String nome, String endereco, int id) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.salarioBruto = 0.0;
		this.salarioLiquido = 0.0;
		
		this.pontoInicio = new Date();
		this.pontoFim = new Date();
		
		this.salarioProximoMes = 0;
		
		this.sindicato = false;
	} 
	
	public void setSalarioProximoMes(double valor, boolean mais) {
		if(mais){
			this.salarioProximoMes += valor;
		}
		else {
			this.salarioProximoMes -= valor;
		}
		
	}
	
	public void setDebitoProximoMes(boolean valor) {
		this.debitoProximoMes = valor;
	}
	
	protected void calcularSalarioLiquido() {
		 if(this.debitoProximoMes) {
			 this.salarioLiquido += this.salarioProximoMes;
		 }
	}
	
	public void setSindicato(boolean sin) {
		this.sindicato = sin;
	}
	
	public boolean isSindicato() {
		return this.sindicato;
	}
	
	public int getMatricula() {
		return this.matriculaSindicato;
	}
	
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setMetodoPagamento(String metodo) {
		this.metodoPagamento = metodo;
	}
	
	public void AssociarAoSindicato(boolean pertence, int matricula) {
		this.sindicato = pertence;
		this.matriculaSindicato = matricula;
	}
	
	
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getMetodoPagamento() {
		return this.metodoPagamento;
	}
	
	
	public void baterPonto(Date dateInicio, Date dateFim) {
		this.pontoInicio = dateInicio;
		this.pontoFim = dateFim;
	}
}
