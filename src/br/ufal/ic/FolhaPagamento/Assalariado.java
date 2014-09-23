package br.ufal.ic.FolhaPagamento;

import java.util.Date;
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
	
	//toda vez que rodar a folha de pagamento executar esse metodo
	public void calcularSalarioFinal(int dia) {
		super.calcularSalarioLiquido();
		
		if(this.pontos[dia] >= 8) {
			this.salarioFinal += this.salarioLiquido * this.TAXA_POR_DIA;
		}
		else {
			this.salarioFinal += this.salarioLiquido * this.TAXA_POR_DIA * this.TAXA_POR_HORA * this.pontos[dia];
		}
	}
	
}
