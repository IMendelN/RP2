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
		System.out.println("Agora defina o tamanho do campo que você deseja simular:");
		int largura = 0, profundidade = 0, r;
		System.out.println("Você deseja escolher o tamanho do campo ou usar o padrão 50 x 50?");
		System.out.println("1- Seguir padrão");
		System.out.println("2- Escolher Tamanho");
		r = s.nextInt();
		
		if(r == 2){
		System.out.println("Qual a largura do campo? (apenas números inteiros) ");
		largura = s.nextInt();
		System.out.println("Qual a Profundidade do campo? (apenas números inteiros) ");
		profundidade = s.nextInt();
		simu = new Simulador(largura, profundidade);
		}else {
			System.out.println("!Seguindo os padrões de tamanho!");
			simu = new Simulador();
			}
	}
	
	public static void executaSimulacao() {
		int r;
		
		System.out.println("Deseja realizar que tipo de simulação?");
		System.out.println("1- Longa(até 1000 etapas)");
		System.out.println("2- Personalizada");
		System.out.println("3- Simulacao etapa por etapa");
		r = s.nextInt();
		
		if(r == 2) {
			System.out.println("Quantas etapas deseja simular?");
			r = s.nextInt();			
			simu.simulacao(r);
			if(simu.getEtapa()< r) {
				System.out.println("!Execução parada pois o resultado já está definido!");
			}
		}else if(r == 3) {
			do {
				simu.simulacaoUmaEtapa();
				System.out.println("Para pasar para a próxima etapa digite \"1\".");
				System.out.println("Digite outro valor inteiro para parar");
				r = s.nextInt();
			}while(r == 1);
		}else {

		simu.executaLongaSimulacao();
		}
		
	}

}
