import static org.junit.Assert.*;

import org.junit.Test;

public class SimuladorTest {

	Simulador simulador;

	
	@Test
	public void testSimulador() {
	
		simulador = new Simulador(1 ,1);
		 
	}
	@Test
	public void testSimulado() {
	
		simulador = new Simulador(-1 ,-1);
		
	}
	 


	@Test
	public void testExecutaLongaSimulacao() {
		Simulador simuladorS = new Simulador(100, 100);
		@SuppressWarnings("unused")
		Campo campo = (Campo) simuladorS.getCampo();
		
		simuladorS.executaLongaSimulacao();
	}
 
	@Test
	public void testSimulacao() {

	}

	@Test
	public void testSimulacaoUmaEtapa() {
		Simulador simuladorS = new Simulador();
		simuladorS.executaLongaSimulacao();
	}

	@Test
	public void testRedefine() {
		
		
	}

}
