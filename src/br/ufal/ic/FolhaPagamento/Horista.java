package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Horista extends Empregado{
	private double salarioPorHora;
	private int horasTabalhadas;
	private int horasExtras;
	private final double TAXA_HORA_EXTRA = 1.5;
	
	public Horista(String nome, String endereco, int id, double salarioPorHora) {
		super(nome, endereco, id);
		
		this.salarioPorHora = salarioPorHora;
	}
	
	
	
	
	public void calcularSalarioBruto(double salarioPorHora, GregorianCalendar cal) {
		
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		
		if(this.pontos[dia] <= 8) {
			this.salarioBruto = this.salarioPorHora * this.pontos[dia];
		}
		else {
			this.salarioBruto = this.salarioPorHora * this.pontos[dia] * this.TAXA_HORA_EXTRA;
		}
	}
	
	public double getSalarioPorHora() {
		return this.salarioPorHora;
	}
	
	//toda vez que rodar a folha de pagamento executar esse metodo
	@Override
	public void calcularSalarioLiquido(GregorianCalendar cal) {
		this.calcularSalarioBruto(this.salarioPorHora, cal);
		
		super.calcularSalarioLiquido(cal);
	}
	
}
