package utilidades;

import java.text.ParseException;
import java.util.Date;
import java.util.function.Function;

import aplicacao.Sistema;
import entidades.Voo;
import excecoes.VooExcecao;

public class funcoesVoo {

public static void criarVoos() throws ParseException {
		
		Date d1 = Sistema.sdf1.parse("15/12/2020 17:30");
		Date d2 = Sistema.sdf1.parse("17/12/2020 15:45");
		Date d3 = Sistema.sdf1.parse("19/12/2020 10:00");
		Date d4 = Sistema.sdf1.parse("21/12/2020 09:15");
		Date d5 = Sistema.sdf1.parse("23/12/2020 05:00");
		Date d6 = Sistema.sdf1.parse("25/12/2020 07:00");
		
		Voo voo1 = new Voo("Goiânia", "São Paulo", d1, 5, 1050, 600);
		Voo voo2 = new Voo("Goiânia", "Belo Horizonte", d2, 7, 1052, 800);
		Voo voo3 = new Voo("Goiânia", "Florianópolis", d3, 9, 1054, 1000);
		Voo voo4 = new Voo("Goiânia", "Manaus", d4, 11, 1056, 1200);
		Voo voo5 = new Voo("Goiânia", "Rialma", d5, 13, 1058, 1400);
		Voo voo6 = new Voo("Goiânia", "Rialma", d6, 15, 1060, 1600);
		Sistema.listaDeVoos.add(voo1);
		Sistema.listaDeVoos.add(voo2);
		Sistema.listaDeVoos.add(voo3);
		Sistema.listaDeVoos.add(voo4);
		Sistema.listaDeVoos.add(voo5);
		Sistema.listaDeVoos.add(voo6);
	}
	
	public static Voo encontrarVoo(int numVoo) {
		int aux = 0;
		for (Voo v : Sistema.listaDeVoos) {
			if (v.getNumeroVoo() == numVoo) {
				aux++;
				return v;
			}
		}
		if(aux == 0) {
			throw new VooExcecao("***Não existe vôo com esse número!***\n");
		}
		return null;
	}

	public static void encontrarVoo(String cidadeOrigem, String cidadeDestino) {
		int aux = 0;
		for (Voo v : Sistema.listaDeVoos) {
			if (Util.removerAcentos(v.getCidadeOrigem()).equalsIgnoreCase(cidadeOrigem.trim())
					&& Util.removerAcentos(v.getCidadeDestino()).equalsIgnoreCase(cidadeDestino.trim())) {
				aux++;
				v.imprimeVoo();
			}
		}
		if(aux == 0) {
			throw new VooExcecao("***Não existe vôo nessas condições!***\n");
		}
	}

	public static void encontrarVoo(Date d, String cidadeOrigem, String cidadeDestino) {
		int aux = 0;
		
		Function<Date, String> formatarData = data -> {
			return Sistema.sdf2.format(data);
		};

		for (Voo v : Sistema.listaDeVoos) {
			if (Util.removerAcentos(v.getCidadeOrigem()).equalsIgnoreCase(cidadeOrigem.trim())
					&& Util.removerAcentos(v.getCidadeDestino()).equalsIgnoreCase(cidadeDestino.trim())
					&& formatarData.apply(v.getDataPartida()).equalsIgnoreCase(formatarData.apply(d))) {
				aux++;
				v.imprimeVoo();
			}
		}
		if(aux == 0) {
			throw new VooExcecao("***Não existe vôo nessas condições!***\n");
		}
	}
}
