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
		int largura = 0, profundidade = 0, r;
		System.out.println("Voce deseja escolher o tamanho do campo ou usar o padrao 50 x 50?");
		System.out.println("1- Seguir padrao");
		System.out.println("2- Escolher Tamanho");
		r = s.nextInt();
		
		if(r == 2){
		System.out.println("Qual a largura do campo? (apenas numeros inteiros) ");
		largura = s.nextInt();
		System.out.println("Qual a Profundidade do campo? (apenas numeros inteiros) ");
		profundidade = s.nextInt();
		simu = new Simulador(largura, profundidade);
		}else {
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
		r = s.nextInt();
		
		if(r == 2) {
			System.out.println("Quantas etapas deseja simular?");
			r = s.nextInt();			
			simu.simulacao(r);
			if(simu.getEtapa()< r) {
				System.out.println("!Execucao parada pois o resultado já está definido!");
			}
		}else if(r == 3) {
			do {
				simu.simulacaoUmaEtapa();
				System.out.println("Para pasar para a proxima etapa digite \"1\".");
				System.out.println("Digite outro valor inteiro para parar");
				r = s.nextInt();
			}while(r == 1);
		}else {

		simu.executaLongaSimulacao();
		}
		
	}

}
