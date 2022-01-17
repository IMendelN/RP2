import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Campo {
	private static final Random rand = Randomizador.getRandom();
//Mudanca de atributo: o campo a partir de agora é um campo de Animais e não de Objects;
	private int profundidade, largura;
	private Animais[][] campo;

//ok
	public Campo(int profundidade, int largura) {
		this.profundidade = profundidade;
		this.largura = largura;
		campo = new Animais[profundidade][largura];
	}

//ok   
	public void limpa() {
		for (int linha = 0; linha < profundidade; linha++) {
			for (int coluna = 0; coluna < largura; coluna++) {
				campo[linha][coluna] = null;
			}
		}
	}

//ok
	public void limpa(Localizacao localizacao) {
		campo[localizacao.getLinha()][localizacao.getColuna()] = null;
	}

//Mudanca de parametro de Object para Animais
	public void lugar(Animais animal, int linha, int coluna) {
		lugar(animal, new Localizacao(linha, coluna));
	}

//ok 
	public void lugar(Animais animal, Localizacao localizacao) {
		campo[localizacao.getLinha()][localizacao.getColuna()] = animal;
	}

//ok   
	public Animais getObjectAt(Localizacao localizacao) {
		return getObjectAt(localizacao.getLinha(), localizacao.getColuna());
	}

//ok 
	public Animais getObjectAt(int linha, int coluna) {
		return campo[linha][coluna];
	}

//ok
	public Localizacao localizacaoAdjacenteRandomica(Localizacao localizacao) {
		List<Localizacao> adjacent = localizacoesAdjacentes(localizacao);
		return adjacent.get(0);
	}

//ok
	public List<Localizacao> localizacoesAdjacentesLivres(Localizacao localizacao) {
		List<Localizacao> livre = new LinkedList<Localizacao>();
		List<Localizacao> adjacente = localizacoesAdjacentes(localizacao);
		for (Localizacao proximo : adjacente) {
			if (getObjectAt(proximo) == null) {
				livre.add(proximo);
			}
		}
		return livre;
	}

//ok   
	public Localizacao localizacaoAdjacenteLivre(Localizacao localizacao) {
		List<Localizacao> livre = localizacoesAdjacentesLivres(localizacao);
		if (!livre.isEmpty()) {
			return livre.get(0);
		} else {
			return null;
		}
	}

//ok
	public List<Localizacao> localizacoesAdjacentes(Localizacao localizacao) {
		assert localizacao != null : "Null localizacao passed to adjacentLocalizacoes";
		List<Localizacao> localizacoes = new LinkedList<Localizacao>();
		if (localizacao != null) {
			int linha = localizacao.getLinha();
			int coluna = localizacao.getColuna();
			for (int roffset = -1; roffset <= 1; roffset++) {
				int proximaLinha = linha + roffset;
				if (proximaLinha >= 0 && proximaLinha < profundidade) {
					for (int coffset = -1; coffset <= 1; coffset++) {
						int proximaColuna = coluna + coffset;
						if (proximaColuna >= 0 && proximaColuna < largura && (roffset != 0 || coffset != 0)) {
							localizacoes.add(new Localizacao(proximaLinha, proximaColuna));
						}
					}
				}
			}
			Collections.shuffle(localizacoes, rand);
		}
		return localizacoes;
	}

//ok
	public int getProfundidade() {
		return profundidade;
	}

//ok   
	public int getLargura() {
		return largura;
	}
}
