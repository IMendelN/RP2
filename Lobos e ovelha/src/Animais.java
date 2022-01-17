import java.util.Random;

public abstract class Animais {
	private static final Random rand = Randomizador.getRandom();

	private int idade;
	private boolean vivo;
	private Campo campo;

	protected Animais(boolean idadeRandomica, Campo campo, int idadeMaxima) {
		idade = 0;
		vivo = true;
		this.campo = campo;
		if (idadeRandomica) {
			idade = rand.nextInt(idadeMaxima);
		}

	}

	/*
	 * Tested ajustado para retornar a localizacao e setar a localizacao do como
	 * null quando o lobo morrer;
	 */
	protected Localizacao incrementaIdade(int idadeMaxima, Localizacao localizacao) {
		idade++;
		if (idade > idadeMaxima) {
			setMorte(localizacao);
			return null;
		}
		return localizacao;
	}

	/*
	 * Tested erro encontrado ajustado, a localizacao nao estava sendo nula,
	 * ajustado tambem a classe loboGuara;
	 */
	protected void setMorte(Localizacao localizacao) {
		vivo = false;
		if (localizacao != null) {
			campo.limpa(localizacao);
			campo = null;
		}
	}

	/* Tested */
	protected int procria(int idadeProcriacao, double probabilidadeProcriacao, int tamanhoNinhada) {
		int nascimentos = 0;
		if (podeProcriar(idadeProcriacao) && rand.nextDouble() <= probabilidadeProcriacao) {
			nascimentos = rand.nextInt(tamanhoNinhada) + 1;
		}
		return nascimentos;
	}

	/* Tested */
	protected boolean podeProcriar(int idadeProcriacao) {
		return idade >= idadeProcriacao && vivo;
	}

	/* Tested */
	public boolean estaVivo() {
		return vivo;
	}

	public Campo getCampo() {
		return campo;
	}

	/* Tested */
	public int getIdade() {
		return idade;
	}
}
