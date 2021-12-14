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
		SimuladorTela SimuladorTela = new SimuladorTela(50, 50);
		SimuladorTela.setCor(getClass(), null);
	} 

	@Test
	public void testMostraStatus() {
		SimuladorTela SimuladorTela = new SimuladorTela(50, 50);
		Campo campo = new Campo (50, 50);
		SimuladorTela.mostraStatus(1, campo); 
	}
	 
	@Test
	public void getCor() {
		simuladorTela = new SimuladorTela(50, 50);
		SimuladorTela.getCor(getClass());
	} 

	@Test
	public void testEhViavel() {
		SimuladorTela SimuladorTela = new SimuladorTela(50, 50);
		Campo Campo;
		Campo = new Campo(50, 50);
		SimuladorTela.ehViavel(Campo);
	}

}

