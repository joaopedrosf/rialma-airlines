package excecoes;

public class VooExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VooExcecao(String msg) {
		super(msg);
	}
}
