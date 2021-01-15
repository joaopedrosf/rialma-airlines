package excecoes;

public class CadastroExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CadastroExcecao(String msg) {
		super(msg);
	}
}
