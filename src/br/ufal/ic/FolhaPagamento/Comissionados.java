package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Comissionados extends Assalariado {
	
	private double comissao;
	private double salario2Semanas;
	private final double TAXA_POR_DIA = 0.1;
	private boolean primeiraSemana = true;
	private Map<GregorianCalendar, List<Double> > vendas;
	
	
	public Comissionados(String nome, String endereco, int id, double salarioFixo) {
		super(nome, endereco, id, salarioFixo);
		
		vendas = new HashMap<GregorianCalendar, List<Double> >();
		comissao = 0.0;
		salario2Semanas = 0.0;
	}
	
	/* Gets e Sets*/
	
	public boolean isPrimeiraSemana() {
		return this.primeiraSemana;
	}
	
	public void setPrimeiraSemana(boolean primeiraSemana) {
		this.primeiraSemana = primeiraSemana;
	}
	
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	
	public double getComissao() {
		return this.comissao;
	}
	
	public List<Double> getVendas(GregorianCalendar cal) {
		cal.set(Calendar.HOUR, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return this.vendas.get(cal.getTime());
	}
	
	public Map<GregorianCalendar, List<Double> > getVendasTotal() {
		return this.vendas;
	}
	
	/* Gets e Sets */
	
	
	
	
	public void registrarVenda(GregorianCalendar cal, double valor) {
		List<Double> aux;
		
		cal.set(Calendar.HOUR, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		try {
			aux = this.vendas.get(cal);
		
			if(aux != null) {
				aux.add(valor);
				vendas.put(cal, aux);
			}
			else {
				aux = new LinkedList<Double>();
				aux.add(valor);
				vendas.put(cal, aux);
			}
		}
		catch (NullPointerException ex) {
			System.out.println("Vendas " + ex);
		}
			 
	}

	public void setSalarioLiquido(double salario) {
		this.salarioLiquido = salario;
	}
	
	public void setSalario2Semanas(double salario) {
		this.salario2Semanas = salario;
	}
	
	public double getSalario2Semanas() {
		return this.salario2Semanas;
	}
	
	@Override
	public void calcularSalarioLiquido(GregorianCalendar cal) {
		
		double valorTotalVendas = 0.0;
		
		try {
			List<Double> temp = this.getVendas(cal);
			
			for(int i = 0; i < temp.size(); ++i) {
				valorTotalVendas += temp.get(i);
				//System.out.println("vendas total: " + valorTotalVendas);
			}
		}
		catch (NullPointerException ex) {
			//System.out.println("Sem vendas!");
		}
		finally {
			this.salarioLiquido = this.taxaFixa + this.salarioBruto;
		
			this.salarioLiquido += this.comissao * valorTotalVendas;
		}
	}
	
	//toda vez que rodar a folha de pagamento executar esse metodo
	public void calcularSalario2Semanas(GregorianCalendar cal) {
		int dia = cal.get(Calendar.DATE);
		
		this.calcularSalarioLiquido(cal);
		
		
		if(this.pontos[dia] >= 8) {
			this.salario2Semanas = (this.salarioLiquido/2) * this.TAXA_POR_DIA;
		}
		else if (this.pontos[dia] != 0){
			this.salario2Semanas = (this.salarioLiquido/2) * this.TAXA_POR_DIA * this.TAXA_POR_HORA * this.pontos[dia];
		}
		
		//debug
		//System.out.println("salario: " + this.salario2Semanas );
	}
	
}
