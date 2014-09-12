package br.ufal.ic.FolhaPagamento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.ufal.ic.FolhaPagamento.Interfaces.MetodoPagamento;

public class Empregado {
	private int id;
	private String nome;
	private String endereco;
	private boolean sindicato;
	private int matriculaSindicato;
	private String diaPagamento;
	private MetodoPagamento metodoPagamento;
	
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
	
	
	protected void calcularSalarioLiquido() {
		 if(this.debitoProximoMes) {
			 this.salarioLiquido += this.salarioProximoMes;
		 }
	}
	
	protected void setDiaPagamento(String date) {
		this.diaPagamento = date;
	}
	
	public String getDiaPagamento() {
		return this.diaPagamento;
	}
	
	public boolean isSindicato() {
		return this.sindicato;
	}
	
	public void setSindicato(boolean sindicato) {
		this.sindicato = sindicato;
	}
	
	public int getMatricula() {
		return this.matriculaSindicato;
	}
	
	public void setMatricula(int matricula) {
		this.matriculaSindicato = matricula;
	}
	
	
	
	public void setNome(String nome) {
		
	}
	
	public void setEndereco(String endereco) {
		
	}
	
	public void setMetodoPagamento(MetodoPagamento metodo) {
		
	}
	
	public void AssociarAoSindicato(boolean pertence, double taxaSindical, int matricula) {
		
	}
	
	
	
	public void baterPonto(Date dateInicio, Date dateFim) {
		this.pontoInicio = dateInicio;
		this.pontoFim = dateFim;
	}
}
