package br.ufal.ic.FolhaPagamento;

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
	
	
	private void debugEmpregado(Empregado empregado) {
		print("\nNome: " + empregado.getNome());
		print("\nEndereço: " + empregado.getEndereco());
		print("\nId: " + empregado.getId());
	}
	
	private void debug(Horista horista) {
		print("\nHorista");
		debugEmpregado(horista);
		print("\nSalario por Hora: " + horista.getSalarioPorHora());
	}
	
	private void debug(Assalariado assalariado) {
		print("\nAssalariado");
		debugEmpregado(assalariado);
		print("Salario Fixo: " + assalariado.getSalarioFixo());
	}
	
	private void debug(Comissionados comissionado) {
		print("\nComissionado");
		debugEmpregado(comissionado);
		print("Salario Fixo: " + comissionado.getSalarioFixo());
	}
	
	private void debug(Sindicato sindicato) {
		
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
			break;
		case 2:
			Assalariado assalariado = new Assalariado(nome, endereco, id);
			print("Salario Fixo: \n");
			salario = scan.nextDouble();
			assalariado.setSalarioBruto(salario);
			clearBuffer();
			debug(assalariado);
			break;
		case 3:
			Comissionados comissionados = new Comissionados(nome, endereco, id);
			print("Salario Fixo: \n");
			salario = scan.nextDouble();
			comissionados.setSalarioFixo(salario);
			clearBuffer();
			debug(comissionados);
			break;
		}
	}
	
	private void delEmpregados() {
		
	}
	
	private void cartaoPonto() {
		
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
	
	public static void main(String[] args) {
		Main window = new Main();
		int entrada = 0;
		
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
			break;
		}
	}

}
