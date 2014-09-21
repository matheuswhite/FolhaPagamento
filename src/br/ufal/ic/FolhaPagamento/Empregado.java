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
	protected double salarioProximoMes[];
	protected double taxaFixa;
	protected boolean debitoMes[];
	
	protected int pontos[];
	
	public Empregado(String nome, String endereco, int id) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.salarioBruto = 0.0;
		this.salarioLiquido = 0.0;
		this.taxaFixa = 0.0;
		this.matriculaSindicato = 0;
		this.sindicato = false;
		this.metodoPagamento = null;
		
		//janeiro é 0
		this.salarioProximoMes = new double[11];
		this.debitoMes = new boolean[11];
		
		for(int i= 0; i < 11; ++i) {
			this.debitoMes[i] = false;
		}
		
		this.sindicato = false;
		
		//horas trabalhadas num dia
		this.pontos = new int[31];
		
		try {
			for(int i = 0; i < 31; ++i) {
				this.pontos[i] = 0;
			}
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("indice fora do array. " + ex);
			ex.printStackTrace();
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
	
	public void setDebitoMes(boolean valor, int mes) {
		this.debitoMes[mes] = valor;
	}
	
	public void setSalarioProximoMes(double valor, int mes) {
		this.salarioProximoMes[mes] = valor;
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
	
	public void calcularSalarioLiquido(GregorianCalendar cal) {
		this.salarioLiquido += this.taxaFixa + this.salarioBruto;
		int mes = cal.get(Calendar.MONTH);
		
		if(this.debitoMes[mes]) {
			this.salarioLiquido += this.salarioProximoMes[mes];
		}
	}	
	
	
	
	public void baterPonto(GregorianCalendar cal, GregorianCalendar cal2) {
		
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		int hora2 = cal2.get(Calendar.HOUR_OF_DAY);
		
		int dia = cal.get(Calendar.DATE);
		
		
		this.pontos[dia] = hora2 - hora;
		
	}
	
	public void baterPonto(int horasTrabalhadas, int dia) {
		
		this.pontos[dia] = horasTrabalhadas;
	}
	
}
