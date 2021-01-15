package utilidades;

import java.text.Normalizer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import aplicacao.Sistema;

public class Util {
	
	public static UnaryOperator<String> formataCpf = cpf -> {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		return cpf;
	};
	
	public static UnaryOperator<String> formataNumCartao = numero -> {
		numero = numero.replace("-", "");
		numero = numero.replace(" ", "");
		numero = numero.replace(".", "");
		return numero;
	};

	public static Function<String, Boolean> verificaSim = s -> {
		if ("s".equalsIgnoreCase(s) || "sim".equalsIgnoreCase(s)) {
			return true;
		}
		return false;
	};

	public static Function<String, Boolean> verificaNao = n -> {
		if ("n".equalsIgnoreCase(n) || "nao".equalsIgnoreCase(n) || "não".equalsIgnoreCase(n)) {
			return true;
		}
		return false;
	};
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static Boolean voltarAoMenu() {
		String m = null;
		Boolean menu;
		
		while(!verificaSim.apply(m) && !verificaNao.apply(m)) {
			System.out.print("Deseja voltar ao menu (s/n)? ");
			m = Sistema.input.next();
		}
		if(verificaSim.apply(m)) {
			menu = true;
			System.out.println();
		}else {
			menu = false;
		}
		return menu;
	}
}
