import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class SimuladorTelaTest {

	Simulador simulador;
	private SimuladorTela simuladorTela;

	@Test
	public void testSimuladorTela() {

		simuladorTela = new SimuladorTela(50, 50);
	}

	@Test
	public void testSetCor() {
		simuladorTela = new SimuladorTela(50, 50);
		simuladorTela.setCor(getClass(), null);
	}

	@Test
	public void getCor() {
		simuladorTela = new SimuladorTela(50, 50);
		LoboGuara loboGuara = new LoboGuara(true, new Campo(1, 1), new Localizacao(0, 0));
		simuladorTela.setCor(loboGuara.getClass(), Color.blue);
		assertEquals(SimuladorTela.getCor(loboGuara.getClass()), Color.blue);
		assertEquals(SimuladorTela.getCor(getClass()), Color.gray);
	}

	@Test
	public void testMostraStatus() {
		simuladorTela = new SimuladorTela(50, 50);
		Campo campo = new Campo(50, 50);
		campo.lugar(new LoboGuara(true, campo, new Localizacao(0, 0)), 0, 0);
		simuladorTela.mostraStatus(1, campo);
	}

	@Test
	public void testMostraStatusNaoVisivel() {
		simuladorTela = new SimuladorTela(50, 50);
		Campo campo = new Campo(50, 50);
		simuladorTela.setVisible(false);
		simuladorTela.mostraStatus(1, campo);
	}

	@Test
	public void testEhViavel() {
		simuladorTela = new SimuladorTela(50, 50);
		Campo Campo;
		Campo = new Campo(50, 50);
		simuladorTela.ehViavel(Campo);
	}

	@Test
	public void testTelaSize0() {
		simuladorTela = new SimuladorTela(0, 0);
	}

}
