import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CampoTest {

	@Test
	public void testeLimpaCamp() {
		Campo campo = new Campo(50, 50);
		campo.lugar(new Ovelha(false, campo, new Localizacao(2, 2)), 2, 2);
		campo.lugar(new Ovelha(false, campo, new Localizacao(3, 3)), 3, 3);
		campo.lugar(new LoboGuara(false, campo, new Localizacao(22, 22)), 22, 22);
		campo.limpa();
		for (int linha = 0; linha < 50; linha++) {
			for (int coluna = 0; coluna < 50; coluna++) {
				assertNull(campo.getObjectAt(linha, coluna));
			}
		}
	}

	@Test
	public void testeLimpaLoc() {
		Campo campo = new Campo(50, 50);
		campo.lugar(new Ovelha(false, campo, new Localizacao(2, 2)), 2, 2);
		campo.lugar(new Ovelha(false, campo, new Localizacao(3, 3)), 3, 3);
		campo.lugar(new LoboGuara(false, campo, new Localizacao(22, 22)), 22, 22);
		campo.limpa(new Localizacao(2, 2));
		campo.limpa(new Localizacao(3, 3));
		campo.limpa(new Localizacao(22, 22));
		for (int linha = 0; linha < 50; linha++) {
			for (int coluna = 0; coluna < 50; coluna++) {
				assertNull(campo.getObjectAt(linha, coluna));
			}
		}
	}

	@Test
	public void testeLugar() {
		Campo campo = new Campo(50, 50);
		Ovelha ovelha = new Ovelha(false, campo, new Localizacao(2, 2));
		campo.lugar(ovelha, 2, 2);
		assertEquals(campo.getObjectAt(new Localizacao(2, 2)), ovelha.getClasse());
	}

	@Test
	public void testeLocAdLivres() {
		Campo campo = new Campo(50, 50);
		Localizacao localizacao = new Localizacao(0, 0);
		LoboGuara loboGuara = new LoboGuara(true, campo, localizacao);
		Localizacao locAd = campo.localizacaoAdjacenteLivre(localizacao);
		assertTrue(campo.getObjectAt(localizacao) instanceof LoboGuara);
	}

	@Test
	public void testeLocAdjacentes() {
		Campo campo = new Campo(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int i2 = 0; i < 3; i++) {
				campo.lugar(new Ovelha(false, campo, new Localizacao(i, i2)), new Localizacao(i, i2));
			}
		}
		List<Localizacao> locAd = campo.localizacoesAdjacentes(new Localizacao(1, 1));
		for (int i = 0; i < 3; i++) {
			for (int i2 = 0; i < 3; i++) {
				if (i != 1 && i2 != 1) {
					assertTrue(locAd.contains(new Localizacao(i, i2)));
				}
			}
		}
	}

	@Test
	public void testeGetProfundidadeLargura() {
		Campo campo = new Campo(20, 35);
		assertEquals(campo.getLargura(), 35);
		assertEquals(campo.getProfundidade(), 20);
	}

	@Test
	public void testeLocRandom() {
		Campo campo = new Campo(1, 2);
		Campo campo2 = new Campo(3, 3);
		assertTrue(campo2.localizacoesAdjacentes(new Localizacao(1, 1))
				.contains(campo2.localizacaoAdjacenteRandomica(new Localizacao(1, 1))));
		assertEquals(campo.localizacaoAdjacenteRandomica(new Localizacao(0, 0)), new Localizacao(0, 1));
	}

	@Test
	public void testeLocAdLivres2() {
		Campo campo = new Campo(50, 50);
		Localizacao localizacao = new Localizacao(0, 0);
		assertNotEquals(null, campo.localizacaoAdjacenteLivre(localizacao));
	}
}
