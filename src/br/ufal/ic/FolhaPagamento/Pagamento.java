package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import br.ufal.ic.FolhaPagamento.Interfaces.MetodoPagamento;


//associar empregado a um sindicato (m) ?!
public class Pagamento {
	private List<Empregado> empregados;
	private Sindicato sindicato;
	private ListAcoes listAcoes;
	
	public Pagamento() {
		empregados = new LinkedList<Empregado>();
		sindicato = new Sindicato();
		listAcoes = new ListAcoes();
	}
	
	public List<Empregado> getEmpregados() {
		return this.empregados;
	}
	
	public Sindicato getSindicato() {
		return this.sindicato;
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
			this.listAcoes.add("CartaoPonto");
		}
		else {
			//erro msg
		}
	}
	
	public void LancarVenda(Empregado empregado, double venda, Date date) {
		if(empregado instanceof Comissionados) {
			if(isRegistrado(empregado)) {
				((Comissionados) empregado).registrarVenda(date, venda);
				this.listAcoes.add("Venda");
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
			this.listAcoes.add("TaxaExtra");
		}
		else {
			// erro msg
		}
	}
	
	//adicionar mudança de matricula
	public void AlterarEmpregado(String nome, String endereco, 
			Empregado empregado, MetodoPagamento metodoPagamento, 
			boolean pertenceSindicato, int matricula, double taxaSindical) {
		
		if(isRegistrado(empregado)) {
			empregado.setNome(nome);
			empregado.setEndereco(endereco);
			empregado.setMetodoPagamento(metodoPagamento);
			empregado.AssociarAoSindicato(pertenceSindicato, matricula);
			this.sindicato.setTaxa(taxaSindical, matricula);
			
			this.listAcoes.add("AltEmpregado");
		}
		else {
			//erro msg
		}
	}
	
	private boolean isBisexto(Calendar cal) {
		boolean saida = false;
		int ano = cal.get(Calendar.YEAR);
		
		if(ano % 4 == 0) {
			if(ano % 100 == 0) {
				if(ano % 400 == 0) {
					saida = true;
				}
			}
			else {
				saida = true;
			}
		}
		
		return saida;
	}
	
	private boolean isUltimoDiaUtil(Date date) {
		boolean saida = false;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH) + 1;
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		
		if(mes % 2 == 0 && mes != 2) {
			if(dia == 30) {
				saida = true;
			}
		}
		
		if(mes % 2 != 0) {
			if(dia == 31) {
				saida = true;
			}
		}
		
		if(mes == 2) {
			if(isBisexto(cal)) {
				if(dia == 29) {
					saida = true;
				}
			}
			else {
				if(dia == 28) {
					saida = true; 
				}
			}
		}
		
		return saida;
	}
	
	public void rodarFolhaPagamento(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
			
			for(int i = 0; i < this.empregados.size(); ++i) {
				
				Empregado empregado = this.empregados.get(i);
				
				if(empregado instanceof Horista) {
					//calcular salario e pagar pelo metodo escolhido
				}
			}
		}
		
		if(isUltimoDiaUtil(date)) {
			for(int i = 0; i < this.empregados.size(); ++i) {
				
				Empregado empregado = this.empregados.get(i);
				
				if(empregado instanceof Assalariado) {
					//calcular salario e pagar pelo metodo escolhido
				}
			}
		}
		
		
		//if(/*cada duas sextas*/) {
			//pagar comissionados
		//}
	}
}
