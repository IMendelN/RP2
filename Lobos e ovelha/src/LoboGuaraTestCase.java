import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LoboGuaraTestCase {

	@Before
	public void setUp() throws Exception {

	}

//--------------------------------------------------------------
	@Test
	public void testeIncrementaFomeMata() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), new Localizacao(1, 1));
		Method incrementaFome = lobo.getClass().getDeclaredMethod("incrementaFome");
		incrementaFome.setAccessible(true);
		for (int i = 0; i < 7; i++) {
			incrementaFome.invoke(lobo);
		}
		assertFalse(lobo.estaVivo());

	}

//-------------------------------------------------------------------
	@Test
	public void testeIncrementaFomeVivo() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), new Localizacao(1, 1));
		Method incrementaFome = lobo.getClass().getDeclaredMethod("incrementaFome");
		incrementaFome.setAccessible(true);
		for (int i = 0; i < 5; i++) {
			incrementaFome.invoke(lobo);
		}
		assertTrue(lobo.estaVivo());
	}

//--------------------------------------------------------------------------
	@Test
	public void testePodeProcriarTrue() {
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), new Localizacao(1, 1));

		for (int i = 0; i < 12; i++)
			lobo.incrementaIdade(16, new Localizacao(1, 1));

		boolean resultado = lobo.podeProcriar(10);
		assertTrue(resultado);
	}

//--------------------------------------------------------------------------	
	@Test
	public void testePodeProcriarFalse() {
		Localizacao loc = new Localizacao(1, 1);
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), loc);

		for (int i = 0; i < 5; i++)
			lobo.incrementaIdade(16, loc);

		boolean resultado = lobo.podeProcriar(10);
		assertFalse(resultado);
	}

//--------------------------------------------------------------------------
	@Test
	public void testeSetMorte() {
		Localizacao loc = new Localizacao(1, 1);
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), loc);

		lobo.setMorte();

		assertFalse(lobo.estaVivo());
		assertEquals(lobo.getCampo(), null);
		assertEquals(lobo.getLocalizacao(), null);
	}

//--------------------------------------------------------------------------
	@Test
	public void testeIncementaIdadeVivo() {
		Localizacao loc = new Localizacao(1, 1);
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), loc);
		Localizacao newLocalizacao = new Localizacao(1, 1);

		for (int i = 0; i < 5; i++)
			newLocalizacao = lobo.incrementaIdade(16, loc);

		assertEquals(lobo.getIdade(), 5);
		assertEquals(loc.getClass(), newLocalizacao.getClass());
	}

//--------------------------------------------------------------------------
	@Test
	public void testeIncementaIdadeMorte() {
		Localizacao loc = new Localizacao(1, 1);
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), loc);
		Localizacao newLocalizacao = new Localizacao(1, 1);

		for (int i = 0; i < 17; i++)
			newLocalizacao = lobo.incrementaIdade(16, loc);

		assertEquals(lobo.getIdade(), 17);
		assertFalse(lobo.estaVivo());
		assertNull(newLocalizacao);
	}

//--------------------------------------------------------------------------
	@Test
	public void testeProcria() {
		Localizacao loc = new Localizacao(1, 1);
		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), loc);
		int nacimentos = lobo.procria(10, 0.75, 5);
		boolean teste = false;
		if (nacimentos >= 0 && nacimentos <= 5)
			teste = true;

		assertTrue(teste);
	}

//--------------------------------------------------------------------------
	@Test
	public void testeGetLocalizacao() {

		LoboGuara lobo = new LoboGuara(false, new Campo(50, 50), new Localizacao(1, 1));

		assertTrue(lobo.getLocalizacao() instanceof Localizacao);

	}

//--------------------------------------------------------------------------
	@Test
	public void testeSetLocalizacao() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Localizacao localizacao = new Localizacao(1, 1);
		Localizacao newLocalizacao = new Localizacao(1, 2);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);
		Method setLoc = lobo.getClass().getDeclaredMethod("setLocalizacao", localizacao.getClass());
		setLoc.setAccessible(true);

		setLoc.invoke(lobo, newLocalizacao);

		assertEquals(lobo.getLocalizacao(), newLocalizacao);

	}

//--------------------------------------------------------------------------
	@Test
	public void testeProcuraComidaFalse() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);
		Method procuraComida = lobo.getClass().getDeclaredMethod("procuraComida", localizacao.getClass());
		procuraComida.setAccessible(true);

		localizacao = (Localizacao) procuraComida.invoke(lobo, localizacao);

		assertNull(localizacao);

	}

//--------------------------------------------------------------------------	
	@Test
	public void testeProcuraComidaTrue() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);
		List<Localizacao> localizacoesLivres = campo.localizacoesAdjacentes(lobo.getLocalizacao());
		for (Localizacao loc : localizacoesLivres) {
			campo.lugar(new Ovelha(false, campo, loc), loc);
		}
		Method procuraComida = lobo.getClass().getDeclaredMethod("procuraComida", localizacao.getClass());
		procuraComida.setAccessible(true);

		localizacao = (Localizacao) procuraComida.invoke(lobo, localizacao);

		assertNotNull(localizacao);
		assertEquals(lobo.getNivelFome(), 7);
	}

//--------------------------------------------------------------------------	
	@Test
	public void testeLoboGuaraNotRandom() {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);

		assertNotNull(campo.getObjectAt(localizacao));
		assertEquals(lobo.getClasse(), campo.getObjectAt(localizacao));
		assertEquals(localizacao.getColuna(), 1);
		assertEquals(localizacao.getLinha(), 1);
		assertEquals(lobo.getNivelFome(), 7);
		assertEquals(lobo.getLocalizacao(), localizacao);
		assertEquals(lobo.getIdade(), 0);
		assertEquals(lobo.getCampo(), campo);

	}

	// --------------------------------------------------------------------------
	@Test
	public void testeLoboGuaraRandom() {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(true, campo, localizacao);
		boolean teste = false;
		if (lobo.getIdade() <= 150 && lobo.getIdade() >= 0 && lobo.getNivelFome() <= 7 && lobo.getNivelFome() >= 0)
			teste = true;

		assertNotNull(campo.getObjectAt(localizacao));
		assertEquals(lobo.getClasse(), campo.getObjectAt(localizacao));
		assertEquals(localizacao.getColuna(), 1);
		assertEquals(localizacao.getLinha(), 1);
		assertEquals(lobo.getLocalizacao(), localizacao);
		assertEquals(lobo.getCampo(), campo);
		assertTrue(teste);

	}

//--------------------------------------------------------------------------
	@Test
	public void testeCacaComOvelha() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);

		for (int i = 0; i < 3; i++) {
			for (int i2 = 0; i < 3; i++) {
				if (i != 1 && i2 != 1) {
					campo.lugar(new Ovelha(false, campo, new Localizacao(i, i2)), new Localizacao(i, i2));
				}
			}
		}

		lobo.caca(new ArrayList<LoboGuara>());

		assertTrue(lobo.estaVivo());
		assertNotEquals(lobo.getLocalizacao(), localizacao);
		assertEquals(lobo.getIdade(), 1);
		assertEquals(lobo.getNivelFome(), 7);

	}

//--------------------------------------------------------------------------
	@Test
	public void testeCacaSemOvelha() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);
		lobo.caca(new ArrayList<LoboGuara>());
		assertTrue(lobo.estaVivo());
		assertNotEquals(lobo.getLocalizacao(), localizacao);
		assertEquals(lobo.getIdade(), 1);
		assertEquals(lobo.getNivelFome(), 6);
	}

//--------------------------------------------------------------------------	
	@Test
	public void testeCacaSemOvelhaAteAMorte() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Localizacao localizacao = new Localizacao(1, 1);
		Campo campo = new Campo(50, 50);
		LoboGuara lobo = new LoboGuara(false, campo, localizacao);
		for (int i = 0; i < 7; i++)
			lobo.caca(new ArrayList<LoboGuara>());

		assertFalse(lobo.estaVivo());
		assertEquals(lobo.getIdade(), 7);
		assertEquals(lobo.getNivelFome(), 0);
	}

//--------------------------------------------------------------------------
	/*
	 * public List<LoboGuara> returnList(){ return new ArrayList<LoboGuara>(); }
	 * 
	 * @Test public void testeDaALuz() throws IllegalAccessException,
	 * IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
	 * SecurityException{ Localizacao localizacao = new Localizacao(1,1); Campo
	 * campo = new Campo(50,50);
	 * 
	 * LoboGuara lobo = new LoboGuara(false, campo , localizacao); List<LoboGuara>
	 * lobos = new ArrayList<LoboGuara>();
	 * 
	 * lobos.add(new LoboGuara(false, campo , localizacao));
	 * 
	 * Method daALuz = lobo.getClass().getDeclaredMethod("daALuz",returnList());
	 * daALuz.setAccessible(true);
	 * 
	 * List<LoboGuara> novosLobos = (List<LoboGuara>) daALuz.invoke(lobo,lobos);
	 * 
	 * lobos.addAll(novosLobos);
	 * 
	 * assertNotNull(lobos);
	 * 
	 * }
	 */
	@Test
	public void testeAnimais() {

	}

}
