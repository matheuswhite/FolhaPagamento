package br.ufal.ic.FolhaPagamento.Acoes;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.ufal.ic.FolhaPagamento.*;

public class Venda implements Acoes{

	private Empregado empregado;
	private double venda;
	private Date date;
	
	public Venda(double venda, Date date, Empregado empregado) {
		this.venda = venda;
		this.date = date;
		this.empregado = empregado;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(empregado);
		Empregado temp = pagamento.getEmpregados().get(index);
		
		Map<Date, List<Double> > vendas = ((Comissionados) temp).getVenda();
		
		vendas.get(date).add(venda);
		
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(empregado);
		Empregado temp = pagamento.getEmpregados().get(index);
		
		Map<Date, List<Double> > vendas = ((Comissionados) temp).getVenda();
		
		vendas.get(date).remove(venda);
	}

}
