package br.ufal.ic.FolhaPagamento;

import java.util.Scanner;

public class Main {

	private Pagamento pagamento;
	private ListAcoes acoes;
	private Scanner scan;
	
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
		print("7- Rodar folha de pagamento");
		print("8- Undo");
		print("9- Redo");
	}
	
	private void addEmpregados() {
		int entrada = 0;
		Empregado empregado;
		
		print("Escolha o tipo de empregado\n");
		print("1- Horista\t2- Assalariado\t3- Comissionado\n");
		entrada = scan.nextInt();
		
		switch(entrada) {
		case 1:
			empregado = new Horista(null, null, entrada, entrada);
			break;
		case 2:
			empregado = new Assalariado();
			break;
		case 3:
			empregado = new Comissionados(null, null, entrada, entrada);
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
