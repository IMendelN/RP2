import java.util.Random;

public abstract class Animais {
    private static final Random rand = Randomizador.getRandom();
    
    private int idade;
    private boolean vivo;
    private Campo campo;
    
    protected Animais(boolean idadeRandomica, Campo campo,int idadeMaxima) {
    	idade = 0;
        vivo = true;
        this.campo = campo;
        if(idadeRandomica) {
            idade = rand.nextInt(idadeMaxima);
        }
       
    }
    
    protected void incrementaIdade(int idadeMaxima, Localizacao localizacao){
        idade++;
        if(idade >= idadeMaxima) {
            setMorte(localizacao);
        }
    }
    
    protected void setMorte(Localizacao localizacao){
        vivo = false;
        if(localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }
    
    protected int procria(int idadeProcriacao, double probabilidadeProcriacao, int tamanhoNinhada){
        int nascimentos = 0;
        if(podeProcriar(idadeProcriacao) && rand.nextDouble() <= probabilidadeProcriacao) {
            nascimentos = rand.nextInt(tamanhoNinhada) + 1;
        }
        return nascimentos;
    }
    
    protected boolean podeProcriar(int idadeProcriacao){
        return idade > idadeProcriacao;
    }
   
    public boolean estaVivo()
    {
        return vivo;
    }
   
    public Campo getCampo() {
    	return campo;
    }

    public int getIdade() {
    	return idade;
    }
}


	
	

