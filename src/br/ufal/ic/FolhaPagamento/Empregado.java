package br.ufal.ic.FolhaPagamento;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Empregado {
	private int id;
	private String nome;
	private String endereco;
	private String metodoPagamento;
	
	private boolean sindicato;
	private int matriculaSindicato;
	
	protected double salarioBruto;
	protected double salarioLiquido;
	protected double salarioProximoMes;
	protected boolean debitoProximoMes = false;
	
	protected int pontos[];
	
	//verificar o for
	public Empregado(String nome, String endereco, int id) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.salarioBruto = 0.0;
		this.salarioLiquido = 0.0;
		
		this.salarioProximoMes = 0.0;
		
		this.sindicato = false;
		
		this.pontos = new int[31];
		
		for(int i : this.pontos) {
			this.pontos[i] = -1;
		}
		
	} 
	
	
	
	
	/* Gets e Sets */
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setMetodoPagamento(String metodo) {
		this.metodoPagamento = metodo;
	}
	
	public String getMetodoPagamento() {
		return this.metodoPagamento;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setSindicato(boolean sin) {
		this.sindicato = sin;
	}
	
	public boolean isSindicato() {
		return this.sindicato;
	}
	
	public void setDebitoProximoMes(boolean valor) {
		this.debitoProximoMes = valor;
	}
	
	public int getMatricula() {
		return this.matriculaSindicato;
	}
	
	/* Gets e Sets */
	
	public void AssociarAoSindicato(boolean pertence, int matricula) {
		this.sindicato = pertence;
		this.matriculaSindicato = matricula;
	}
	
	protected void calcularSalarioBruto(double salario) {
		this.salarioBruto = salario;
	}
	
	protected void calcularSalarioLiquido(double taxafixa) {
		this.salarioLiquido += taxafixa + this.salarioBruto;
		
		if(this.debitoProximoMes) {
			this.salarioLiquido += this.salarioProximoMes;
		}
	}	
	
	// ?!
	public void setSalarioProximoMes(double valor, boolean mais) {
		if(mais){
			this.salarioProximoMes += valor;
		}
		else {
			this.salarioProximoMes -= valor;
		}
	}
	
	
	
	//verificar o for
	public void baterPonto(Date dateInicio, Date dateFim) {
		GregorianCalendar cal = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();
		
		cal.setTime(dateInicio);
		cal2.setTime(dateFim);
		
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int hours2 = cal2.get(Calendar.HOUR_OF_DAY);
		
		for(int x : this.pontos) {
			if(this.pontos[x] == -1) {
				this.pontos[x] = hours - hours2;
				return ;
			}
		}
		
	}
}
