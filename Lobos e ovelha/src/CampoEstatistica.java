
//import java.awt.Color;
import java.util.HashMap;

@SuppressWarnings("rawtypes")
public class CampoEstatistica {
	private HashMap<Class, Contador> contadores;
	private boolean contadoresValidos;

	// Contadores começando como validos, assim mesmo se não houvessem contadores
	// eles estariam
	public CampoEstatistica() {
		contadores = new HashMap<Class, Contador>();
		contadoresValidos = false;
	}

//ok
	public String getPopulationDetails(Campo campo) {
		StringBuffer buffer = new StringBuffer();
		if (!contadoresValidos) {
			geraContadores(campo);
		}
		for (Class chave : contadores.keySet()) {
			Contador info = contadores.get(chave);
			buffer.append(info.getName());
			buffer.append(": ");
			buffer.append(info.getCount());
			buffer.append(' ');
		}
		return buffer.toString();
	}

//ok
	public void redefine() {
		contadoresValidos = false;
		for (Class chave : contadores.keySet()) {
			Contador contador = contadores.get(chave);
			contador.reset();
		}
	}

//ok 
	public void incrementaContador(Class animalClass) {
		Contador contador = contadores.get(animalClass);
		if (contador == null) {
			contador = new Contador(animalClass.getName());
			contadores.put(animalClass, contador);
		}
		contador.increment();
		contadores.put(animalClass, contador);
	}

//ok
//correcao: os contadores nao eram setados como invalidos quando o codigo terminava, troquei "true" para  "false"
	public void contadorFinalizado() {
		contadoresValidos = false;
	}

//ok
	public boolean ehViavel(Campo campo) {
		int nonZero = 0;
		if (!contadoresValidos) {
			geraContadores(campo);
		}
		for (Class key : contadores.keySet()) {
			Contador info = contadores.get(key);
			if (info.getCount() > 0) {
				nonZero++;
			}
		}
		return nonZero > 1;
	}

//ok
	private void geraContadores(Campo campo) {
		redefine();
		for (int linha = 0; linha < campo.getProfundidade(); linha++) {
			for (int coluna = 0; coluna < campo.getLargura(); coluna++) {
				Object animal = campo.getObjectAt(linha, coluna);
				if (animal != null) {
					incrementaContador(animal.getClass());
				}
			}
		}
		contadoresValidos = true;
	}
}
