import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnimaisTest {

	@Test
	public void incrementaIdadeTest() {
		Localizacao loc = new Localizacao(1, 1);
		Localizacao loc2 = new Localizacao(2, 2);
		Campo campo = new Campo(50, 50);
		@SuppressWarnings("unused")
		LoboGuara lobo = new LoboGuara(true, campo, loc);
		LoboGuara lobo2 = new LoboGuara(false, campo, new Localizacao(3, 3));
		Ovelha ovelha = new Ovelha(false, campo, loc2);

		assertEquals(ovelha.getIdade(), 0);

		for (int i = 0; i < 151; i++) {
			lobo2.incrementaIdade(150, loc);

		}
		for (int i = 0; i < 41; i++) {
			ovelha.incrementaIdade(40, loc);
		}
		assertFalse(lobo2.estaVivo());
		assertFalse(ovelha.estaVivo());

	}

	@Test
	public void procriaTest() {
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, new Localizacao(1, 1));
		Ovelha ovelha = new Ovelha(false, campo, new Localizacao(2, 1));
		LoboGuara lobo2 = new LoboGuara(false, campo, new Localizacao(1, 2));

		int resultado = ovelha.procria(5, 0.0, 4);
		lobo.procria(10, 0.75, 5);
		lobo2.procria(0, 1.0, 5);

		assertEquals(resultado, 0);

	}

	@Test
	public void podeProcriaTest() {
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, new Localizacao(1, 1));
		Ovelha ovelha = new Ovelha(false, campo, new Localizacao(2, 2));

		lobo.incrementaIdade(16, new Localizacao(1, 1));

		boolean resultado = lobo.podeProcriar(10);
		assertFalse(resultado);

		ovelha.incrementaIdade(10, new Localizacao(2, 2));

		boolean resultado2 = ovelha.podeProcriar(5);
		assertFalse(resultado2);

		for (int i = 0; i < 8; i++) {
			ovelha.incrementaIdade(40, null);
		}
		assertTrue(ovelha.podeProcriar(5));

	}
}