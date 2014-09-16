package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


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
	
	public ListAcoes getListAcoes() {
		return this.listAcoes;
	}
	
	public void msgError(String string) {
		System.out.println("Error - " + string);
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
			this.msgError("Empregado já registrado");
		}
	}
	
	public void RemoverEmpregado(Empregado empregado) {
		if(!this.empregados.isEmpty()) {
			if(isRegistrado(empregado)) {
				this.empregados.remove(empregado);
				this.listAcoes.add("DelEmpregados");
			}
			else {
				this.msgError("Empregado não registrado");
			}
		}
		else {
			this.msgError("Nenhum empregado registrado");
		}
	}
	
	public void LancarCartaoPonto(Empregado empregado, Date inicio, Date fim) {
		if(isRegistrado(empregado)) {
			int index = this.empregados.indexOf(empregado);
			this.empregados.get(index).baterPonto(inicio, fim);
			this.listAcoes.add("CartaoPonto");
		}
		else {
			this.msgError("Empregado não registrado");
		}
	}
	
	public void LancarVenda(Empregado empregado, double venda, Date date) {
		if(empregado instanceof Comissionados) {
			if(isRegistrado(empregado)) {
				((Comissionados) empregado).registrarVenda(date, venda);
				this.listAcoes.add("Venda");
			}
			else {
				this.msgError("Empregado não registrado");
			}
		}
		else {
			this.msgError("Este empregado não é comissionado");
		}
	}
	
	public void LancarTaxaExtra(Empregado empregado, double valor) {
		if(empregado.isSindicato()) {
			this.sindicato.cobrarTaxaExtra(valor, empregado.getMatricula());
			this.listAcoes.add("TaxaExtra");
		}
		else {
			this.msgError("Empregado não registrado no sindicato");
		}
	}
	
	public void AlterarEmpregado(String nome, String endereco, 
			Empregado empregado, String metodoPagamento, 
			boolean pertenceSindicato, int matricula, double taxaSindical, 
			Empregado empregadoAntigo) {
		
		if(isRegistrado(empregado)) {
			empregado.setNome(nome);
			empregado.setEndereco(endereco);
			empregado.setMetodoPagamento(metodoPagamento);
			empregado.AssociarAoSindicato(pertenceSindicato, matricula);
			this.sindicato.setTaxa(taxaSindical, matricula);
			
			this.empregados.remove(empregadoAntigo);
			this.empregados.add(empregado);
			
			this.listAcoes.add("AltEmpregado");
		}
		else {
			this.msgError("Empregado não registrado");
		}
	}
	
	private boolean isUltimoDiaUtil(Date date) {
		boolean saida = false;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		
		if(cal.getTime().compareTo(date) == 0) {
			System.out.println("mesmo dia!");
			
			saida = true;
		}
		
		return saida;
	}
	
	public void rodarFolhaPagamento(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		if(this.empregados.isEmpty()) {
			this.msgError("Sem empregados cadastrados");
			
			return ;
		}
		
		if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
			
			for(int i = 0; i < this.empregados.size(); ++i) {
				
				Empregado empregado = this.empregados.get(i);
				
				if(empregado instanceof Horista) {
					((Horista) empregado).setSalarioBruto();
					sindicato.cobrarTaxaFixaIndividual(empregado);
					empregado.calcularSalarioLiquido();
					
					System.out.println("Salario de " + empregado.salarioLiquido + "pago ao funcionario" + empregado.getNome() + 
							"atraves " + empregado.getMetodoPagamento());
				}
				
				if(empregado instanceof Comissionados) {
					
					if( ((Comissionados) empregado).isPrimeiraSemana() ) {
						
						sindicato.cobrarTaxaFixaIndividual(empregado);
						empregado.calcularSalarioLiquido();
						
						System.out.println("Salario de " + empregado.salarioLiquido + "pago ao funcionario" + empregado.getNome() + 
								"atraves " + empregado.getMetodoPagamento());
						
						((Comissionados) empregado).setPrimeiraSemana(false);
					}
					
					else {
						((Comissionados) empregado).setPrimeiraSemana(true);
					}
					
				}
				
			}// end for
			
		} //end if
		
		if(isUltimoDiaUtil(date)) {
			
			for(int i = 0; i < this.empregados.size(); ++i) {
				
				Empregado empregado = this.empregados.get(i);
				
				if(empregado instanceof Assalariado) {
					
					sindicato.cobrarTaxaFixaIndividual(empregado);
					empregado.calcularSalarioLiquido();
					
					System.out.println("Salario de " + empregado.salarioLiquido + " pago ao funcionario " + empregado.getNome() + 
							" atraves " + empregado.getMetodoPagamento());
				}
				
			}
			
		}
		
		
		this.listAcoes.add("FolhaPagamento");
	}
}
