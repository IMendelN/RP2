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

	public LoboGuara(boolean idadeRandomica, Campo campo, Localizacao localizacao) {
		super(idadeRandomica, campo, IDADE_MAXIMA);
		setLocalizacao(localizacao);
		if (idadeRandomica) {
			nivelFome = rand.nextInt(VALOR_FOME_OVELHA);
		} else {
			nivelFome = VALOR_FOME_OVELHA;
		}
	}

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

	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	private void incrementaFome() {
		nivelFome--;
		if (nivelFome == 0) {
			setMorte();
		}
	}

	public void setMorte() {

		super.setMorte(localizacao);
		if (localizacao != null)
			localizacao = null;
	}

	public int getNivelFome() {
		return nivelFome;
	}

	public Object getClasse() {
		return this;
	}
}
