package br.ufal.ic.FolhaPagamento.Acoes;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import br.ufal.ic.FolhaPagamento.*;

public class Venda implements Acoes{

	private Empregado empregado;
	private double venda;
	private GregorianCalendar cal;
	
	public Venda(double venda, GregorianCalendar cal, Empregado empregado) {
		this.venda = venda;
		this.cal = cal;
		this.empregado = empregado;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(empregado);
		Empregado temp = pagamento.getEmpregados().get(index);
		
		Map<GregorianCalendar, List<Double>> vendas = ((Comissionados) temp).getVendasTotal();
		
		vendas.get(cal).add(venda);
		
		System.out.println("Redo: Lançar venda");
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(empregado);
		Empregado temp = pagamento.getEmpregados().get(index);
		
		Map<GregorianCalendar, List<Double>> vendas = ((Comissionados) temp).getVendasTotal();
		
		vendas.get(cal).remove(venda);
		
		System.out.println("Undo: Lançar venda");
	}

}
