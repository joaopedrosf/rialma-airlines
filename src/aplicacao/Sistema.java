package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entidades.Pessoa;
import entidades.Voo;
import excecoes.CadastroExcecao;
import excecoes.VooExcecao;
import utilidades.Util;
import utilidades.funcoesCadastro;
import utilidades.funcoesVoo;

public class Sistema {

	public static List<Voo> listaDeVoos = new ArrayList<>();
	public static List<Pessoa> listaDePessoas = new ArrayList<>();
	public static Scanner input = new Scanner(System.in);
	public static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat sdf3 = new SimpleDateFormat("MM/yyyy");

	public static void main(String[] args) throws ParseException {
		funcoesCadastro.lerCadastros();
		funcoesVoo.criarVoos();
		Integer escolhaMenu = 5;
		Boolean menu = true;
		while (menu) {
			System.out.print("Bem-vindo(a) à companhia aérea Rialma AirLines. Escolha uma opção:\n1 - Fazer cadastro"
					+ "\n2 - Ver vôos\n3 - Comprar passagem\n4 - Sair\nEscolha: ");
			try {
				escolhaMenu = input.nextInt();
			} catch (InputMismatchException e1) {
				System.out.println("\n***Erro: formato inválido. Você será redirecionado ao menu!***");
				input.next();
			}
			if (escolhaMenu < 1 || escolhaMenu > 4) {
				System.out.println("\n***Tente novamente!***\n");
			} else if (escolhaMenu == 1) {
				try {
					funcoesCadastro.fazerCadastro();
				} catch (CadastroExcecao e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("\n**Erro: formato inválido!***\n");
					input.next();
				}
				menu = Util.voltarAoMenu();
			} else if (escolhaMenu == 2) {
				System.out.print("\n1 - Ver todos os vôos\n2 - Ver vôo por localização\nEscolha: ");
				int escolhaOpcao = input.nextInt();
				if (escolhaOpcao == 1) {
					listaDeVoos.forEach(v -> v.imprimeVoo());
				} else if (escolhaOpcao == 2) {
					input.nextLine();
					try {
						System.out.print("\nInsira a cidade de origem: ");
						String cOrigem = input.nextLine();

						System.out.print("Insira a cidade de destino: ");
						String cDestino = input.nextLine();
						
						funcoesVoo.encontrarVoo(cOrigem, cDestino);
					} catch (VooExcecao e0) {
						System.out.println(e0.getMessage());
					}
				}else if(escolhaOpcao > 2 || escolhaOpcao < 1) {
					System.out.println("***Opção não existente!***\n");
					menu = Util.voltarAoMenu();
					if(menu == false) {
						break;
					}else if(menu == true) {
						continue;
					}
				}

				menu = Util.voltarAoMenu();
			} else if (escolhaMenu == 3) {
				String e = null;
				Voo v = null;
				while (!Util.verificaSim.apply(e) && !Util.verificaNao.apply(e)) {
					System.out.print("Sabe o número do voo desejado? (s/n): ");
					e = input.next();
				}
				if (Util.verificaSim.apply(e)) {
					try {
						System.out.print("Insira o número do voo desejado: ");
						int numVoo = input.nextInt();
						v = funcoesVoo.encontrarVoo(numVoo);
					} catch (VooExcecao e2) {
						System.out.println(e2.getMessage());
						menu = Util.voltarAoMenu();
						if (menu == true) {
							continue;
						} else if (menu == false) {
							break;
						}
					}
				} else {
					input.nextLine();
					try {
						System.out.print("\nInsira a cidade de origem: ");
						String cOrigem = input.nextLine();

						System.out.print("Insira a cidade de destino: ");
						String cDestino = input.nextLine();

						System.out.print("Insira a data de partida (dd/mm/yyyy): ");
						Date dataPartida = sdf2.parse(input.next());

						funcoesVoo.encontrarVoo(dataPartida, cOrigem, cDestino);
					} catch (VooExcecao e3) {
						System.out.println(e3.getMessage());
						menu = Util.voltarAoMenu();
						if (menu == true) {
							continue;
						} else if (menu == false) {
							break;
						}
					}

					try {
						System.out.print("Insira o número do voo desejado: ");
						int numVoo = input.nextInt();
						v = funcoesVoo.encontrarVoo(numVoo);
					} catch (VooExcecao e2) {
						System.out.println(e2.getMessage());
						menu = Util.voltarAoMenu();
						if (menu == true) {
							continue;
						} else if (menu == false) {
							break;
						}
					}
				}

				input.nextLine();
				System.out.print("Possui cadastro? (s/n): ");
				e = input.next();
				if (Util.verificaNao.apply(e)) {
					System.out.println("\nPor favor faça seu cadastro antes de fazer a compra.");
					try {
						funcoesCadastro.fazerCadastro();
					} catch (CadastroExcecao e1) {
						System.out.println(e1.getMessage());
					} catch (InputMismatchException e1) {
						System.out.println("\n**Erro: formato inválido!***\n");
						input.next();
					}
				}

				System.out.print("Por favor insira seu CPF para continuar a compra: ");
				String cpf = input.next();
				cpf = Util.formataCpf.apply(cpf);
				Pessoa p = funcoesCadastro.verificarCadastro(cpf);
				if (p == null) {
					System.out.println("CPF não encontrado.\n");
					continue;
				}

				System.out.print("Insira o número de assentos para adultos: ");
				int nAdultos = input.nextInt();
				System.out.println("AVISO: São consideradas crianças até 6 anos de idade!");
				System.out.print("Insira o número de assentos para crianças: ");
				int nCriancas = input.nextInt();

				v.comprarPassagem(nAdultos, nCriancas, p);
				menu = Util.voltarAoMenu();
			} else if (escolhaMenu == 4) {
				System.out.println();
				break;
			}
		}
		input.close();
		System.out.println("Obrigado por utilizar a Rialma Airlines! Nos vemos em breve.");
	}
}
