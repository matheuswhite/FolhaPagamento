package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import br.ufal.ic.FolhaPagamento.Acoes.*;


public class Pagamento {
	private List<Empregado> empregados;
	private Sindicato sindicato;
	private ListAcoes listAcoes;
	
	private Adicionar adicionar;
	private Remover remover;
	private Cartao cartao;
	private Cartao cartaoAux;
	private Cartao cartaoAntigo;
	private Venda venda;
	private Taxa taxa;
	private Alterar alterar;
	private Folha folha;
	
	public Pagamento() {
		empregados = new LinkedList<Empregado>();
		sindicato = new Sindicato();
		listAcoes = new ListAcoes();
	}
	
	
	/* Gets */
	
	public List<Empregado> getEmpregados() {
		return this.empregados;
	}
	
	public Sindicato getSindicato() {
		return this.sindicato;
	}
	
	public ListAcoes getListAcoes() {
		return this.listAcoes;
	}
	
	/* Gets */
	
	
	
	private boolean isRegistrado(Empregado empregado) {
		
		for(Empregado e : empregados) {
			if(e.getId() == empregado.getId()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void AdicionarEmpregado(Empregado empregado) {
		if(!isRegistrado(empregado)) {
			this.empregados.add(empregado);
			
			this.adicionar = new Adicionar(empregado);
			this.listAcoes.add(adicionar);
		}
		else {
			System.out.println("Empregado ja cadastrado");
		}
	}
	
	public void RemoverEmpregado(Empregado empregado) {
		try {
			this.empregados.remove(empregado);
				
			this.remover = new Remover(empregado);
			this.listAcoes.add(remover);
		}
		catch (NullPointerException e) {
			System.out.println("Empregado ainda n�o cadastrado" + e.getMessage());
		}
		
	}
	
	public void LancarCartaoPonto(Empregado empregado, GregorianCalendar inicio, GregorianCalendar fim) {
		int dia = inicio.get(Calendar.DATE);
		
		try {
			int index = this.empregados.indexOf(empregado);
			
			cartaoAntigo = new Cartao(empregado, empregado.pontos[dia], dia);
			
			this.empregados.get(index).baterPonto(inicio, fim);
			
			cartao = new Cartao(empregado, empregado.pontos[dia], dia);
			
			cartaoAux = new Cartao(cartaoAntigo, cartao);
			
			this.listAcoes.add(cartaoAux);
		}
		catch (Exception e) {
			System.out.println("Empregado n�o registrado" + e.getMessage());
		}
		
	}
	
	public void LancarVenda(Empregado empregado, double venda, GregorianCalendar cal) {
		try {
			((Comissionados) empregado).registrarVenda(cal, venda);
			
			this.venda = new Venda(venda, cal, empregado);
			
			this.listAcoes.add(this.venda);
		}
		catch ( NullPointerException e){
			System.out.println("Empregado n�o registrado" + e.getMessage());
		}
		
	}
	
	public void LancarTaxaExtra(Empregado empregado, double valor, GregorianCalendar cal) {
		int mes = cal.get(Calendar.MONTH);
		
		this.sindicato.cobrarTaxaExtra(valor, empregado.getMatricula(), mes);
		
		this.taxa = new Taxa(empregado, valor, mes);
			
		this.listAcoes.add(this.taxa);
	}
	
	public void AlterarEmpregado(String nome, String endereco, Empregado empregado, String metodoPagamento, 
			boolean pertenceSindicato, int matricula, double taxaSindical, Empregado empregadoAntigo) {
		
		
		try {
			empregado.setNome(nome);
			empregado.setEndereco(endereco);
			empregado.setMetodoPagamento(metodoPagamento);
			empregado.setSindicato(pertenceSindicato);
			
			if(empregado.isSindicato()) {
				this.sindicato.addAssociado(empregado, taxaSindical);
			}
			
			else {
				this.sindicato.delAssociado(empregadoAntigo.getMatricula());
			}
			
			this.empregados.remove(empregadoAntigo);
			this.empregados.add(empregado);
			
			//this.listAcoes.add("ALT");
		}
		catch (NullPointerException e) {
			System.out.println("Empregado " + empregadoAntigo.getNome() + " n�o encontrado" + e.getMessage());
		}
		
	}
	
	//espero que d� certo
	private boolean isUltimoDiaUtil(GregorianCalendar cal) {
		boolean saida = false;
		
		int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		int ultimoDia = Calendar.getInstance().getActualMaximum(mes);
		
		if(dia == ultimoDia) {
			saida = true;
		}
		
		return saida;
	}
	
	public void rodarFolhaPagamento(GregorianCalendar cal) {
		
		try {
			
			for(Empregado empregado : this.empregados) {
					
				if(empregado instanceof Horista) {
					
					((Horista) empregado).calcularSalarioLiquido(cal);
						
					if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
						System.out.println("Salario de " + empregado.salarioLiquido + " pago ao funcionario " + empregado.getNome() + 
							" atraves " + empregado.getMetodoPagamento());
					}
						
				}
						
				if(empregado instanceof Comissionados) {
					((Comissionados) empregado).calcularSalario2Semanas(cal);	
					
					if(cal.get(Calendar.DAY_OF_WEEK) == 6) {
					
						if( ((Comissionados) empregado).isPrimeiraSemana() ) {
							
							System.out.println("Salario de " + empregado.salarioLiquido + " pago ao funcionario " + empregado.getNome() + 
								" atraves " + empregado.getMetodoPagamento());
							
							((Comissionados) empregado).setPrimeiraSemana(false);
						}
						
						else {
							((Comissionados) empregado).setPrimeiraSemana(true);
						}	
					}
				}
				
				if(empregado instanceof Assalariado) {
					((Assalariado) empregado).calcularSalarioFinal(cal);
					
					if(isUltimoDiaUtil(cal)) {
										
						System.out.println("Salario de " + empregado.salarioLiquido + " pago ao funcionario " + empregado.getNome() + 
								" atraves " + empregado.getMetodoPagamento());
					}
					
				}
				
			}
			
		}
		catch ( NullPointerException e ) {
			System.out.println("Empregado n�o encontrado! " + e.getMessage());
		}
		
		//this.listAcoes.add("FOL");
	}
}
