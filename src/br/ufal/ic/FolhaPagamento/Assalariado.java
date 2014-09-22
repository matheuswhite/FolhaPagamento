package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Assalariado extends Empregado{
	private double salarioFinal;
	
	private final double TAXA_POR_DIA = 0.05;
	protected final double TAXA_POR_HORA = 0.125;
	
	public Assalariado(String nome, String endereco, int id, double salarioFixo) {
		super(nome, endereco, id);
		
		super.calcularSalarioBruto(salarioFixo);
		
		this.salarioFinal = 0;
	}
	
	protected double getSalarioFixo() {
		return this.salarioBruto;
	}
	
	public double getSalarioFinal() {
		return this.salarioFinal;
	}
	
	public void setSalarioFinal(double salario) {
		this.salarioFinal = salario;
	}
	
	public void setSalarioLiquido(double salario) {
		this.salarioLiquido = salario;
	}
	
	//toda vez que rodar a folha de pagamento executar esse metodo
	public void calcularSalarioFinal(GregorianCalendar cal) {
		int dia = cal.get(Calendar.DATE);
		
		super.calcularSalarioLiquido(cal);
		
		
		this.salarioFinal += this.salarioLiquido * this.TAXA_POR_DIA * this.pontos[dia] * this.TAXA_POR_HORA;
		
	}
	
}
