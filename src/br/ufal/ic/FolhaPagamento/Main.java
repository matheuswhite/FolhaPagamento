package br.ufal.ic.FolhaPagamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

	private Pagamento pagamento;
	private ListAcoes acoes;
	private Scanner scan;
	private final int limiteEmpregados = 2000;
	
	public Main() {
		pagamento = new Pagamento();
		acoes = new ListAcoes();
		scan = new Scanner(System.in);
	}
	
	private Date stringToDate(String string) throws ParseException {
		Date dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(string);
		
		print("\ndata: " + dt.toString() + "\n");
		
		return dt;
	}
	
	private Empregado escolhaDeEmpregado(String string) {
		print("Escolha um empregado " + string + ": \n");
		
		for(int i=0; i < pagamento.getEmpregados().size(); ++i) {
			Empregado empregado = pagamento.getEmpregados().get(i);
			
			print(i + "- " + empregado.getNome() + " / Id: " + empregado.getId() + "\n");
		
		}
		
		int entrada = scan.nextInt();
		this.clearBuffer();
		
		Empregado empregado = pagamento.getEmpregados().get(entrada);
		
		this.debugEmpregado(empregado);
		
		return empregado;
	}
	
	private void debugEmpregado(Empregado empregado) {
		print("\nNome: " + empregado.getNome());
		print("\nEndereço: " + empregado.getEndereco());
		print("\nId: " + empregado.getId());
	}
	
	private void debug(Horista horista) {
		print("\nHorista");
		debugEmpregado(horista);
		print("\nSalario por Hora: " + horista.getSalarioPorHora() + "\n");
	}
	
	private void debug(Assalariado assalariado) {
		print("\nAssalariado");
		debugEmpregado(assalariado);
		print("\nSalario Fixo: " + assalariado.getSalarioFixo() + "\n");
	}
	
	private void debug(Comissionados comissionado) {
		print("\nComissionado");
		debugEmpregado(comissionado);
		print("\nSalario Fixo: " + comissionado.getSalarioFixo() + "\n");
	}
	
	private void print(String mensagem) {
		System.out.print(mensagem);
	}
	
	private void clearBuffer() {
		scan.nextLine();
	}
	
	private void menuPrincipal() {
		print("1- Adicionar empregados\n");
		print("2- Remover empregado\n");
		print("3- Lançar cartao de ponto\n");
		print("4- Lançar venda\n");
		print("5- Lançar taxa extra\n");
		print("6- Alterar empregado\n");
		print("7- Rodar folha de pagamento\n");
		print("8- Undo\n");
		print("9- Redo\n");
	}
	
	private void addEmpregados() {
		int entrada = 0;
		String nome, endereco;
		double salario;
		int id;
		
		print("Nome: \n");
		nome = scan.nextLine();
		clearBuffer();
		
		print("Endereço: \n");
		endereco = scan.nextLine();
		clearBuffer();
		
		print("Seu id:");
		id = pagamento.getSindicato().gerarId(this.limiteEmpregados);
		print(" " + id + "\n");
		
		print("Escolha o tipo de empregado\n");
		print("1- Horista\t2- Assalariado\t3- Comissionado\n");
		entrada = scan.nextInt();
		clearBuffer();
		
		switch(entrada) {
		case 1:
			
			Horista horista = new Horista(nome, endereco, id);
			print("Salario por hora: \n");
			salario = scan.nextDouble();
			horista.setSalarioPorHora(salario);
			clearBuffer();
			debug(horista);
			this.pagamento.AdicionarEmpregado(horista);
			
			break;
		case 2:
			
			Assalariado assalariado = new Assalariado(nome, endereco, id);
			print("Salario Fixo: \n");
			salario = scan.nextDouble();
			assalariado.setSalarioBruto(salario);
			clearBuffer();
			debug(assalariado);
			this.pagamento.AdicionarEmpregado(assalariado);
			
			break;
		case 3:
			
			Comissionados comissionados = new Comissionados(nome, endereco, id);
			print("Salario Fixo: \n");
			salario = scan.nextDouble();
			comissionados.setSalarioFixo(salario);
			clearBuffer();
			debug(comissionados);
			this.pagamento.AdicionarEmpregado(comissionados);
			
			break;
		}
	}
	
	private void delEmpregados() {
		Empregado empregado = this.escolhaDeEmpregado("a ser removido");
		int entrada;
		
		print("\nDeseja mesmo remover este Empregado?\n1- Sim\t2- Não\n");
		entrada = scan.nextInt();
		
		if(entrada == 1) {
			pagamento.RemoverEmpregado(empregado);
		}
		else {
			//volta para escolher outro
		}
	}
	
	private void cartaoPonto() throws ParseException {
		Empregado empregado = this.escolhaDeEmpregado("para lancar um ponto");
		String dia, mes, ano, horas, minutos, segundos;
		Date inicio, fim;
		
		//yyyy-MM-dd HH:mm:ss.S
		
		print("Entre com a data inicial: \n");
		
		print("Dia (00):\n");
		dia = scan.nextLine();
		this.clearBuffer();
		
		print("Mes (00):\n");
		mes = scan.nextLine();
		this.clearBuffer();
		
		print("Ano (0000):\n");
		ano = scan.nextLine();
		this.clearBuffer();
		
		print("Hora (00):\n");
		horas = scan.nextLine();
		this.clearBuffer();
		
		print("Minutos (00):\n");
		minutos = scan.nextLine();
		this.clearBuffer();
		
		print("Segundos (00):\n");
		segundos = scan.nextLine();
		this.clearBuffer();
		
		inicio = this.stringToDate(ano + "-" + mes + "-" + dia + " " + horas + ":" + minutos + ":" + segundos + ".0");
		
		print("Entre com a data final: \n");
		
		print("Dia (00):\n");
		dia = scan.nextLine();
		this.clearBuffer();
		
		print("Mes (00):\n");
		mes = scan.nextLine();
		this.clearBuffer();
		
		print("Ano (0000):\n");
		ano = scan.nextLine();
		this.clearBuffer();
		
		print("Hora (00):\n");
		horas = scan.nextLine();
		this.clearBuffer();
		
		print("Minutos (00):\n");
		minutos = scan.nextLine();
		this.clearBuffer();
		
		print("Segundos (00):\n");
		segundos = scan.nextLine();
		this.clearBuffer();
		
		fim = this.stringToDate(ano + "-" + mes + "-" + dia + " " + horas + ":" + minutos + ":" + segundos + ".0");
		
		pagamento.LancarCartaoPonto(empregado, inicio, fim);
	}
	
	private void lancarVenda() {
		
	}
	
	private void taxaExtra() {
		
	}
	
	private void alterarEmpregados() {
		
	}
	
	private void RodarFolha() {
		
	}
	
	private void undo() {
		
	}
	
	private void redo() {
		
	}
	
	public static void main(String[] args) throws ParseException {
		Main window = new Main();
		int entrada = 0;
		boolean exit = false;
		
		while(!exit) {
			window.menuPrincipal();
			entrada = window.scan.nextInt();
			window.clearBuffer();
			
			switch(entrada) {
			case 1:
				window.addEmpregados();
				break;
			case 2:
				window.delEmpregados();
				break;
			case 3:
				window.cartaoPonto();
				break;
			case 4:
				window.lancarVenda();
				break;
			case 5:
				window.taxaExtra();
				break;
			case 6:
				window.alterarEmpregados();
				break;
			case 7:
				window.RodarFolha();
				break;
			case 8:
				window.undo();
				break;
			case 9:
				window.redo();
				break;
			default:
				exit = true;
				break;
			}
			
		} //while end
		
	}
}
