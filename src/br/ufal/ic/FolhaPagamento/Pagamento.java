package br.ufal.ic.FolhaPagamento;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


//associar empregado a um sindicato (m)
public class Pagamento {
	private List<Empregado> empregados;
	private Sindicato sindicato;
	private ListAcoes listAcoes;
	
	public Pagamento() {
		empregados = new LinkedList<Empregado>();
		sindicato = new Sindicato();
		listAcoes = new ListAcoes();
	}
	
	private boolean isRegistrado(Empregado empregado) {
		boolean retorno = false;
		
		if(this.empregados.contains(empregado)) {
			retorno = true;
		}
		
		return retorno;
	}
	
	public void AdicionarEmpregado(Empregado empregado) {
		if(!isRegistrado(empregado)) {
			this.empregados.add(empregado);
			this.listAcoes.add("AddEmpregado");
		}
		else {
			// erro msg
		}
	}
	
	public void RemoverEmpregado(Empregado empregado) {
		if(!this.empregados.isEmpty()) {
			if(isRegistrado(empregado)) {
				this.empregados.remove(empregado);
				this.listAcoes.add("DelEmpregados");
			}
			else {
				//erro msg
			}
		}
		else {
			//erro msg
		}
	}
	
	public void LancarCartaoPonto(Empregado empregado, Date inicio, Date fim) {
		if(isRegistrado(empregado)) {
			int index = this.empregados.indexOf(empregado);
			this.empregados.get(index).baterPonto(inicio, fim);
		}
		else {
			//erro msg
		}
	}
	
	public void LancarVenda(Empregado empregado, double venda, Date date) {
		if(empregado instanceof Assalariado) {
			if(isRegistrado(empregado)) {
				((Assalariado) empregado).registrarVenda(date, venda);
			}
			else {
				//erro msg
			}
		}
		else {
			//erro msg
		}
	}
	
	public void LancarTaxaExtra(Empregado empregado, double valor) {
		if(empregado.isSindicato()) {
			this.sindicato.cobrarTaxaExtra(valor, empregado.getMatricula());
		}
		else {
			// erro msg
		}
	}
	
	
}
