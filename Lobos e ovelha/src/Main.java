import java.util.Scanner;

public class Main {

	public static Simulador simu;
	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Bem vindo ao simulador Predadeor-presa");
		configuraTamanhoCampo();
		executaSimulacao();
		s.close();
	}

	public static void configuraTamanhoCampo() {

		System.out.println("Agora defina o tamanho do campo que voce deseja simular:");
		int largura = 0, profundidade = 0, r = 0;

		System.out.println("Voce deseja escolher o tamanho do campo ou usar o padrao 50 x 50?");
		System.out.println("1- Seguir padrao");
		System.out.println("2- Escolher Tamanho");
		try {
			r = s.nextInt();

		} catch (Exception e) {
			System.out.println("!Caracter invalido!");
			r = 1;
		}
		s.nextLine();

		if (r == 2) {
			System.out.println("Qual a largura do campo? (apenas numeros inteiros) ");
			try {
				largura = s.nextInt();
			} catch (Exception e) {
				System.out.println("!Caracter invalido!");
				System.out.println("!Seguindo os padroes de tamanho!");
				largura = 50;
			}
			s.nextLine();
			System.out.println("Qual a Profundidade do campo? (apenas numeros inteiros) ");
			try {
				profundidade = s.nextInt();
			} catch (Exception e) {
				System.out.println("!Caracter invalido!");
				System.out.println("!Seguindo os padroes de tamanho!");
				profundidade = 50;
			}
			s.nextLine();
			simu = new Simulador(largura, profundidade);
		} else {
			System.out.println("!Seguindo os padroes de tamanho!");
			simu = new Simulador();
		}
	}

	public static void executaSimulacao() {
		int r;

		System.out.println("Deseja realizar que tipo de simulacao?");
		System.out.println("1- Longa(ate 1000 etapas)");
		System.out.println("2- Personalizada");
		System.out.println("3- Simulacao etapa por etapa");
		try {
			r = s.nextInt();

		} catch (Exception e) {
			System.out.println("!Caracter invalido!");
			r = 1;
		}
		s.nextLine();

		if (r == 2) {
			System.out.println("Quantas etapas deseja simular?");
			try {
				r = s.nextInt();

			} catch (Exception e) {
				System.out.println("!Caracter invalido!");
				System.out.println("!Executando simulacao longa!");
				r = 1000;
			}
			s.nextLine();
			simu.simulacao(r);
			if (simu.getEtapa() < r) {
				System.out.println("!Execucao parada pois o resultado já está definido!");
			}
		} else if (r == 3) {
			do {
				simu.simulacaoUmaEtapa();
				System.out.println("Para pasar para a proxima etapa digite \"1\".");
				System.out.println("Digite outro valor inteiro para parar");
				try {
					r = s.nextInt();
				} catch (Exception e) {
					System.out.println("!Digite apenas números inteiros!");
					r = 1;
				}
				s.nextLine();
			} while (r == 1);
		} else {
			System.out.println("!Executando simulacao longa!");
			simu.executaLongaSimulacao();
		}

	}

}
