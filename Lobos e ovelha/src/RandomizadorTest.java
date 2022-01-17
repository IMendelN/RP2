import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class RandomizadorTest {

	@Test
	public void testGetRandom() {
		Random rand = Randomizador.getRandom();
		rand.nextDouble();
		rand.nextInt(50);
		rand.nextFloat(5000);
		rand.nextLong(1000);
		Randomizador.reset();
		assertEquals(rand, Randomizador.getRandom());
	}
}
