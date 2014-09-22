package br.ufal.ic.FolhaPagamento.Acoes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufal.ic.FolhaPagamento.*;

public class Folha implements Acoes{

	private GregorianCalendar cal;
	private Empregado empregado;
	private double salarioBruto = 0.0;
	private double salarioLiquido = 0.0;
	private double salarioFinal = 0.0;
	private double salario2Semanas = 0.0;
	private boolean primeiraSemana = false;
	
	public Folha(GregorianCalendar cal, Empregado empregado, double salarioBruto, double salarioLiquido) {
		this.cal = cal;
		this.empregado = empregado;
		
		if(empregado instanceof Horista) {
			this.salarioBruto = salarioBruto;
			this.salarioLiquido = salarioLiquido;
		}
		else if(empregado instanceof Assalariado) {
			this.salarioFinal = salarioBruto;
			this.salarioLiquido = salarioLiquido;
		}
	}
	
	public Folha(GregorianCalendar cal, Empregado empregado, double salarioBruto, double salarioLiquido, boolean primeiraSemana) {
		this.cal = cal;
		this.empregado = empregado;
		
		if(empregado instanceof Comissionados) {
			this.salario2Semanas = salarioBruto;
			this.salarioLiquido = salarioLiquido;
			this.primeiraSemana = primeiraSemana;
		}
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(this.empregado);
		Empregado empregado = pagamento.getEmpregados().get(index);
		
		if(this.empregado instanceof Horista) {
			((Horista) empregado).calcularSalarioLiquido(cal);
		}
		if(this.empregado instanceof Comissionados) {
			((Comissionados) empregado).calcularSalario2Semanas(cal);
			((Comissionados) empregado).setPrimeiraSemana(primeiraSemana);
		}
		else if(this.empregado instanceof Assalariado) {
			((Assalariado) empregado).calcularSalarioFinal(cal);
		}
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		
		System.out.println("Redo: Rodar folha de pagamento");
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(this.empregado);
		Empregado empregado = pagamento.getEmpregados().get(index);
		
		if(this.empregado instanceof Horista) {
			((Horista) empregado).setSalariobruto(salarioBruto);
			((Horista) empregado).setSalarioLiquido(salarioLiquido);
		}
		if(this.empregado instanceof Comissionados) {
			((Comissionados) empregado).setSalarioLiquido(salarioLiquido);
			((Comissionados) empregado).setSalario2Semanas(salario2Semanas);
			((Comissionados) empregado).setPrimeiraSemana(!primeiraSemana);
		}
		else if(this.empregado instanceof Assalariado) {
			((Assalariado) empregado).setSalarioFinal(salarioFinal);
			((Assalariado) empregado).setSalarioLiquido(salarioLiquido);
		}
		
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		System.out.println("Undo: Rodar folha de pagamento");
	}

}
