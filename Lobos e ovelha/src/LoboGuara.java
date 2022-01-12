import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class LoboGuara extends Animais {
	private static final int IDADE_PROCRIACAO = 10;
	private static final int IDADE_MAXIMA = 150;
	private static final double PROBABILIDADE_PROCRIACAO = 0.75;
	private static final int TAMANHO_MAXIMO_NINHADA = 5;
	private static final int VALOR_FOME_OVELHA = 7;
	private static final Random rand = Randomizador.getRandom();
	private Localizacao localizacao;
	private int nivelFome;

//ok
	public LoboGuara(boolean idadeRandomica, Campo campo, Localizacao localizacao) {
		super(idadeRandomica, campo, IDADE_MAXIMA);
		setLocalizacao(localizacao);
		if (idadeRandomica) {
			nivelFome = rand.nextInt(VALOR_FOME_OVELHA);
		} else {
			nivelFome = VALOR_FOME_OVELHA;
		}
	}

	/*
	 * ok // adicionei o incrementa fome aqui; // ajuste, agora o metodo incrementa
	 * idade esta retornando uma localizacao, entao //fez-se necessario uma vaiavel
	 * para armazenar o resultado.
	 */
	public void caca(List<LoboGuara> novosLobos) {
		incrementaFome();
		localizacao = incrementaIdade(IDADE_MAXIMA, localizacao);
		if (estaVivo()) {
			daALuz(novosLobos);
			Localizacao newLocalizacao = procuraComida(getLocalizacao());
			if (newLocalizacao == null) {
				newLocalizacao = getCampo().localizacaoAdjacenteLivre(getLocalizacao());
			}
			if (newLocalizacao != null) {
				setLocalizacao(newLocalizacao);
			} else {
				setMorte();
			}
		}
	}

	// ok
	// Add ifs para testar se o animal e uma ovelha e se a ovelha esta viva;
	// mudanca de Object para Animais
	private Localizacao procuraComida(Localizacao localizacao) {

		List<Localizacao> adjacente = getCampo().localizacoesAdjacentes(localizacao);
		Iterator<Localizacao> it = adjacente.iterator();
		while (it.hasNext()) {
			Localizacao onde = it.next();
			Animais animal = getCampo().getObjectAt(onde);
			if (animal instanceof Ovelha) {
				Ovelha ovelha = (Ovelha) animal;
				if (ovelha.estaViva()) {
					ovelha.setMorte();
					nivelFome = VALOR_FOME_OVELHA;
					return onde;
				} else {
					return null;
				}
			}

		}
		return null;
	}

	/*
	 * ok Correcao no for pois ele nao estava testando se haviam localizacoes
	 * adjacentes livres para o filhote nascer;
	 */
	private void daALuz(List<LoboGuara> novosLobos) {
		List<Localizacao> livre = getCampo().localizacoesAdjacentesLivres(getLocalizacao());
		int nascimentos = procria(IDADE_PROCRIACAO, PROBABILIDADE_PROCRIACAO, TAMANHO_MAXIMO_NINHADA);
		for (int b = 0; (b < nascimentos) && (livre.size() > 0); b++) {
			Localizacao loc = livre.remove(0);
			LoboGuara jovem = new LoboGuara(false, getCampo(), loc);
			novosLobos.add(jovem);
		}
	}

	private void setLocalizacao(Localizacao newLocalizacao) {
		if (localizacao != null) {
			getCampo().limpa(localizacao);
		}
		localizacao = newLocalizacao;
		getCampo().lugar(this, newLocalizacao);
	}

	/* Tested */
	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	/* tested */
	private void incrementaFome() {
		nivelFome--;
		if (nivelFome == 0) {
			setMorte();
		}
	}

	/*
	 * ok Tested necessário criar pois a posicao nao estava sendo nula apos a morte.
	 */
	public void setMorte() {

		super.setMorte(localizacao);
		if (localizacao != null)
			localizacao = null;
	}

	/* Add metodo para facilitar testes */
	public int getNivelFome() {
		return nivelFome;
	}

	public Object getClasse() {
		return this;
	}
}
