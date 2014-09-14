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
	
	private void print(String mensagem) {
		System.out.print(mensagem);
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
		
		print("Endereço: \n");
		endereco = scan.nextLine();
		
		print("Seu id:");
		id = pagamento.getSindicato().gerarId(this.limiteEmpregados);
		print(" " + id + "\n");
		
		print("Escolha o tipo de empregado\n");
		print("1- Horista\t2- Assalariado\t3- Comissionado\n");
		entrada = scan.nextInt();
		
		switch(entrada) {
		case 1:
			Horista horista = new Horista(nome, endereco, id);
			print("Salario por hora: \n");
			salario = scan.nextDouble();
			horista.setSalarioPorHora(salario);
			break;
		case 2:
			Assalariado assalariado = new Assalariado(nome, endereco, id);
			print("Salario Fixo: \n");
			salario = scan.nextDouble();
			assalariado.setSalarioBruto(salario);
			break;
		case 3:
			Comissionados comissionados = new Comissionados(nome, endereco, id);
			print("Salario Fixo: \n");
			salario = scan.nextDouble();
			comissionados.setSalarioFixo(salario);
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
			window.lancarVenda();
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
