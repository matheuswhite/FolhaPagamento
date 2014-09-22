package br.ufal.ic.FolhaPagamento.Acoes;

import java.util.GregorianCalendar;

import br.ufal.ic.FolhaPagamento.*;

public class Folha implements Acoes{

	private GregorianCalendar cal;
	private Empregado empregado;
	private double salarioBruto = 0.0;
	private double salarioLiquido = 0.0;
	private double salarioFinal = 0.0;
	private double salario2Semanas = 0.0;
	
	public Folha(GregorianCalendar cal, Empregado empregado, double salarioBruto, double salarioLiquido) {
		this.cal = cal;
		this.empregado = empregado;
		
		if(empregado instanceof Horista) {
			this.salarioBruto = salarioBruto;
			this.salarioLiquido = salarioLiquido;
		}
		if(empregado instanceof Comissionados) {
			this.salario2Semanas = salarioBruto;
			this.salarioLiquido = salarioLiquido;
		}
		else if(empregado instanceof Assalariado) {
			this.salarioFinal = salarioBruto;
			this.salarioLiquido = salarioLiquido;
		}
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		// TODO Auto-generated method stub
		
	}

}
