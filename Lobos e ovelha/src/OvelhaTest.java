import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class OvelhaTest {

	@Test
	public void testePodeProcriarTrue() {
		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), new Localizacao(1, 1));

		for (int i = 0; i < 40; i++)
			ovelha.incrementaIdade(40, new Localizacao(1, 1));

		boolean resultado = ovelha.podeProcriar(5);
		assertTrue(resultado);
	}

	@Test
	public void testePodeProcriarFalse() {
		Localizacao loc = new Localizacao(1, 1);
		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), loc);

		for (int i = 0; i < 4; i++)
			ovelha.incrementaIdade(40, loc);

		boolean resultado = ovelha.podeProcriar(5);
		assertFalse(resultado);
	}

	@Test
	public void testeSetMorte() {
		Localizacao loc = new Localizacao(1, 1);
		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), loc);

		ovelha.setMorte();

		assertFalse(ovelha.estaViva());
		assertEquals(ovelha.getCampo(), null);
		assertEquals(ovelha.getLocalizacao(), null);
	}

	@Test
	public void testeIncementaIdadeVivo() {
		Localizacao loc = new Localizacao(1, 1);
		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), loc);
		Localizacao newLocalizacao = new Localizacao(1, 1);

		for (int i = 0; i < 30; i++)
			newLocalizacao = ovelha.incrementaIdade(40, loc);

		assertEquals(ovelha.getIdade(), 30);
		assertEquals(loc.getClass(), newLocalizacao.getClass());
	}

	@Test
	public void testeIncementaIdadeMorte() {
		Localizacao loc = new Localizacao(1, 1);
		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), loc);
		Localizacao newLocalizacao = new Localizacao(1, 1);

		for (int i = 0; i < 41; i++)
			newLocalizacao = ovelha.incrementaIdade(40, loc);

		assertEquals(ovelha.getIdade(), 41);
		assertFalse(ovelha.estaViva());
		assertNull(newLocalizacao);
	}

	@Test
	public void testeProcria() {
		Localizacao loc = new Localizacao(1, 1);
		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), loc);
		int nacimentos = ovelha.procria(5, 0.15, 4);
		boolean teste = false;
		if (nacimentos >= 0 && nacimentos <= 4)
			teste = true;

		assertTrue(teste);
	}

	@Test
	public void testeGetLocalizacao() {

		Ovelha ovelha = new Ovelha(false, new Campo(50, 50), new Localizacao(1, 1));

		assertTrue(ovelha.getLocalizacao() instanceof Localizacao);

	}

	@Test
	public void testeSetLocalizacao() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Localizacao localizacao = new Localizacao(1, 1);
		Localizacao newLocalizacao = new Localizacao(1, 2);
		Campo campo = new Campo(50, 50);
		Ovelha ovelha = new Ovelha(false, campo, localizacao);
		Method setLoc = ovelha.getClass().getDeclaredMethod("setLocalizacao", localizacao.getClass());
		setLoc.setAccessible(true);

		setLoc.invoke(ovelha, newLocalizacao);

		assertEquals(ovelha.getLocalizacao(), newLocalizacao);

	}

	@Test
	public void testeOvelhaNotRandom() {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		Ovelha ovelha = new Ovelha(false, campo, localizacao);

		assertNotNull(campo.getObjectAt(localizacao));
		assertEquals(ovelha.getClasse(), campo.getObjectAt(localizacao));
		assertEquals(localizacao.getColuna(), 1);
		assertEquals(localizacao.getLinha(), 1);
		assertEquals(ovelha.getLocalizacao(), localizacao);
		assertEquals(ovelha.getIdade(), 0);
		assertEquals(ovelha.getCampo(), campo);
	}

	@Test
	public void testeCorreOvelha() {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		Ovelha ovelha = new Ovelha(false, campo, localizacao);
		List<Ovelha> novasOvelhas = new ArrayList<Ovelha>();
		ovelha.corre(novasOvelhas);
		assertTrue(ovelha.estaViva());
		assertNotNull(ovelha.getLocalizacao());
	}

}
