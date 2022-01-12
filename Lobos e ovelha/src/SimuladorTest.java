import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class SimuladorTest {

	Simulador simulador;

	@Test
	public void testSimulador() {
		simulador = new Simulador(1, 1);
	}

	@Test
	public void testSimuladorValorNegativo() {
		simulador = new Simulador(6, -7);
		simulador = new Simulador(-5, 4);
		simulador = new Simulador(-8, -7);
	}

	@Test
	public void testExecutaLongaSimulacao() {
		simulador = new Simulador(100, 100);
		simulador.executaLongaSimulacao();
	}

	@Test
	public void testSimulacaoComDiferentesEtapas() {
		simulador = new Simulador(100, 100);
		simulador.simulacao(10);
		simulador.simulacao(5000);
		simulador.simulacao(450);
	}

	@Test
	public void testSimulacaoUmaEtapa() {
		simulador = new Simulador();
		simulador.simulacaoUmaEtapa();
		assertEquals(simulador.getEtapa(), 1);
	}

	@Test
	public void testRedefine() {
		simulador = new Simulador();
		simulador.executaLongaSimulacao();
		simulador.redefine();
		assertEquals(simulador.getEtapa(), 0);
	}

	@Test
	public void testPovoa() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		simulador = new Simulador();
		Simulador simulador1_1 = new Simulador(1, 1);
		Simulador simulador2_3 = new Simulador(2, 3);
		Method povoa = simulador.getClass().getDeclaredMethod("povoa");
		povoa.setAccessible(true);
		povoa.invoke(simulador);
		povoa.invoke(simulador1_1);
		povoa.invoke(simulador2_3);
	}

	@Test
	public void testgetCampo() {
		simulador = new Simulador();
		assertNotNull(simulador.getCampo());
	}

}
