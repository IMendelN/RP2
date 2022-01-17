import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

class CampoEstatisticaTest {

	@Test
	void testCampoEstatistica() {
		@SuppressWarnings("unused")
		CampoEstatistica estatisticas = new CampoEstatistica();
	}

	@Test
	void testGetPopulationDetails() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		Campo campo = new Campo(1, 2);
		Ovelha ovelha = new Ovelha(true, campo, new Localizacao(0, 1));
		campo.lugar(new LoboGuara(true, campo, new Localizacao(0, 0)), new Localizacao(0, 0));
		campo.lugar(ovelha, new Localizacao(0, 1));
		String saida = estatisticas.getPopulationDetails(campo);
		assertTrue(saida.contains("Ovelha: 1 "));
		assertTrue(saida.contains("LoboGuara: 1 "));
	}

	@Test
	void testGetPopulationDetailsCampoVazio() {
		Campo campo = new Campo(1, 1);
		CampoEstatistica estatisticas = new CampoEstatistica();
		String saida = estatisticas.getPopulationDetails(campo);
		assertEquals(saida, "");
	}

	@Test
	void testRedefine() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		Campo campo = new Campo(1, 1);
		LoboGuara lobo = new LoboGuara(true, campo, new Localizacao(0, 0));
		campo.lugar(lobo, new Localizacao(0, 0));
		estatisticas.incrementaContador(lobo.getClass());
		estatisticas.redefine();
	}

	@Test
	void testIncrementaContador() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		Campo campo = new Campo(1, 1);
		LoboGuara lobo = new LoboGuara(true, campo, new Localizacao(0, 0));
		campo.lugar(lobo, new Localizacao(0, 0));
		estatisticas.incrementaContador(lobo.getClass());
	}

	@Test
	void testIncrementaContadorCampoVazio() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		Campo campo = new Campo(1, 1);
		LoboGuara lobo = new LoboGuara(true, campo, new Localizacao(0, 0));
		estatisticas.incrementaContador(lobo.getClass());
	}

	@Test
	void testContadorFinalizado() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		estatisticas.contadorFinalizado();

	}

	@Test
	void testEhViavel() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		Campo campo = new Campo(1, 2);
		LoboGuara lobo = new LoboGuara(true, campo, new Localizacao(0, 0));
		Ovelha ovelha = new Ovelha(true, campo, new Localizacao(0, 1));
		campo.lugar(lobo, new Localizacao(0, 0));
		campo.lugar(ovelha, new Localizacao(0, 1));
		estatisticas.incrementaContador(lobo.getClass());

		assertTrue(estatisticas.ehViavel(campo));
	}

	@Test
	void testEhViavelfalse() {
		CampoEstatistica estatisticas = new CampoEstatistica();
		Campo campo = new Campo(1, 2);
		LoboGuara lobo = new LoboGuara(true, campo, new Localizacao(0, 0));
		campo.lugar(lobo, new Localizacao(0, 0));
		estatisticas.incrementaContador(lobo.getClass());

		assertFalse(estatisticas.ehViavel(campo));
	}

}
