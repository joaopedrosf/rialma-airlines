package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import aplicacao.Sistema;
import entidades.Credito;
import entidades.Debito;
import entidades.Pessoa;
import excecoes.CadastroExcecao;

public class funcoesCadastro {

	public static Pessoa verificarCadastro(String cpf) {
		for (Pessoa p : Sistema.listaDePessoas) {
			if (cpf.equals(p.getCpf())) {
				return p;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static void fazerCadastro() throws ParseException {

		String nome, cpf;
		int idade;
		Sistema.input.nextLine();
		System.out.print("\nInsira o nome: ");
		nome = Sistema.input.nextLine();
		System.out.print("Insira a idade: ");
		idade = Sistema.input.nextInt();
		System.out.print("Insira o CPF: ");
		Sistema.input.nextLine();
		cpf = Sistema.input.nextLine();
		cpf = Util.formataCpf.apply(cpf);
		if(cpf.length() != 11) {
			throw new CadastroExcecao("***CPF inválido. (Tamanho deve conter 11 dígitos).***\n");
		}
		if(!(cpf.matches("[0-9]*"))) {
			throw new CadastroExcecao("***CPF inválido. (Deve conter apenas números)***\n");
		}
		
		if (verificarCadastro(cpf) != null) {
			System.out.println("CPF já cadastrado.");
			return;
		}

		Pessoa p = new Pessoa(nome, idade, cpf);

		System.out.print("Possui cartão? (s/n): ");
		String escolha = Sistema.input.next();
		while (!Util.verificaSim.apply(escolha) && !Util.verificaNao.apply(escolha)) {
			System.out.print("Possui cartão? (s/n): ");
			escolha = Sistema.input.next();
		}

		if (Util.verificaSim.apply(escolha)) {
			String numero;
			String data;

			System.out.print("Crédito ou débito? (c/d): ");
			escolha = Sistema.input.next();
			while (!escolha.equalsIgnoreCase("c") && !escolha.equalsIgnoreCase("d")) {
				System.out.print("Crédito ou débito? (c/d): ");
				escolha = Sistema.input.next();

			}
			if ("c".equalsIgnoreCase(escolha) || "credito".equalsIgnoreCase(escolha)
					|| "crédito".equalsIgnoreCase(escolha)) {
				double limite;

				Sistema.input.nextLine();
				System.out.print("Insira o número do cartão: ");
				numero = Sistema.input.nextLine();
				numero = Util.formataNumCartao.apply(numero);
				
				if(numero.length() != 16) {
					throw new CadastroExcecao("O número do cartão deve conter 16 dígitos.");
				}
				if(!(numero.matches("[0-9]*"))) {
					throw new CadastroExcecao("O cartão deve conter apenas números");
				}

				System.out.print("Insira a validade do cartão (mm/yyyy): ");
				data = Sistema.input.next();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
				Date validade = sdf.parse(data);
				validade.setDate(31);
				Date aux = new Date();
				
				if(validade.before(aux)) {
					throw new CadastroExcecao("O cartão está vencido.");
				}
				
				System.out.print("Insira o limite do cartão: ");
				limite = Sistema.input.nextDouble();
				if(limite < 0) {
					throw new CadastroExcecao("Limite deve ser maior que 0.");
				}

				Credito c = new Credito(numero, validade, limite, p);
				p.setCartao(c);
			} else {
				double saldo;

				Sistema.input.nextLine();
				System.out.print("Insira o número do cartão: ");
				numero = Sistema.input.nextLine();
				numero = Util.formataNumCartao.apply(numero);
				
				if(numero.length() != 16) {
					throw new CadastroExcecao("O número do cartão deve conter 16 dígitos.");
				}
				if(!(numero.matches("[0-9]*"))) {
					throw new CadastroExcecao("O cartão deve conter apenas números");
				}

				System.out.print("Insira a validade do cartão (mm/yyyy): ");
				data = Sistema.input.next();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
				Date validade = sdf.parse(data);
				Date aux = new Date();
				if(validade.before(aux)) {
					throw new CadastroExcecao("O cartão está vencido.");
				}
				System.out.print("Insira o saldo do cartão: ");
				saldo = Sistema.input.nextDouble();
				Debito d = new Debito(numero, validade, saldo, p);
				p.setCartao(d);
			}

		}
		try {
			salvarCadastro(p);
		} catch (IOException e) {
			System.out.println("Falha ao salvar Pessoa");
		}
		System.out.println("***Cadastro realizado com sucesso!***");
		System.out.println();
		Sistema.listaDePessoas.add(p);
	}
	
	public static void salvarCadastro(Pessoa p) throws IOException{
		FileWriter fw = new FileWriter("Cadastros.txt", true);
		if(p.getCartao() != null) {
			if(p.getCartao() instanceof Credito) {
				Credito c = (Credito)p.getCartao();
				fw.write(p.getNome() + ";" + p.getIdade() + ";" + p.getCpf() + ";" + c.isCredito()
						 + ";" + c.getNumero() + ";" + Sistema.sdf3.format(c.getValidade()) 
						 + ";" + c.getLimite() + ";\n");
			}
			else {
				Debito c = (Debito)p.getCartao();
				fw.write(p.getNome() + ";" + p.getIdade() + ";" + p.getCpf() + ";" + c.isCredito()
						 + ";" + c.getNumero() + ";" + Sistema.sdf3.format(c.getValidade()) 
						 + ";" + c.getSaldo() + ";\n");
			}
		}
		else {
			fw.write(p.getNome() + ";" + p.getIdade() + ";" + p.getCpf() + ";\n");
		}
		fw.close();
	}
	
	public static void lerCadastros() {
		try {
			FileReader fr = new FileReader("Cadastros.txt");
			BufferedReader lerArq = new BufferedReader(fr);
			
			String linha = lerArq.readLine();
			
			while(linha != null) {
				String atributosPessoa [] = linha.split(";");
				instanciarPessoa(atributosPessoa);
				linha = lerArq.readLine();
			}
			fr.close();
			lerArq.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo de cadastros não encontrado!");
		} catch (IOException e) {
			System.out.println("Falha ao ler os cadastros!");
		}
	}
	
	public static void instanciarPessoa(String atributos[]){
		Pessoa p = new Pessoa(atributos[0], Integer.parseInt(atributos[1]), atributos[2]);
		try {
			if(atributos[3] != null) {
				if(Boolean.parseBoolean(atributos[3])) {
					try {
						p.setCartao(new Credito(atributos[4], Sistema.sdf3.parse(atributos[5]), Double.parseDouble(atributos[6]), p));
					} catch (NumberFormatException e) {
						System.out.println("NumberFormatException");
					} catch (ParseException e) {
						System.out.println("ParseException");
					}
				}
				else {
					try {
						p.setCartao(new Debito(atributos[4], Sistema.sdf3.parse(atributos[5]), Double.parseDouble(atributos[6]), p));
					} catch (NumberFormatException e) {
						System.out.println("NumberFormatException");
					} catch (ParseException e) {
						System.out.println("ParseException");
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//Caso a pessoa não possua cartão em seu cadastro...
		}
		Sistema.listaDePessoas.add(p);
	}
}
