import java.util.List;
import java.util.Random;

public class Ovelha
{
    private static final int IDADE_PROCRIACAO = 5;
    private static final int IDADE_MAXIMA = 40;
    private static final double PROBABILIDADE_PROCRIACAO = 0.15;
    private static final int TAMANHO_MAXIMO_NINHADA = 4;
    private static final Random rand = Randomizador.getRandom();
    
    private int idade;
    private boolean vivo;
    private Localizacao localizacao;
    private Campo campo;
//ok
    public Ovelha(boolean randomAge, Campo campo, Localizacao localizacao)
    {
        idade = 0;
        vivo = true;
        this.campo = campo;
        setLocalizacao(localizacao);
        if(randomAge) {
            idade = rand.nextInt(IDADE_MAXIMA);
        }
    }
//Trocado de == para != na linha 34
    public void corre(List<Ovelha> novasOvelhas)
    {
        incrementaIdade();
        if(vivo) {
            daALuz(novasOvelhas);            
            Localizacao newLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);
            if(newLocalizacao != null) { 
                setLocalizacao(newLocalizacao);
            }
            else {
                setMorte();
            }
        }
    }
//ok    
    public boolean estaViva()
    {
        return vivo;
    }
//ok
    public void setMorte()
    {
        vivo = false;
        if(localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }
//ok
    public Localizacao getLocalizacao()
    {
        return localizacao;
    }
//ok  
    private void setLocalizacao(Localizacao newLocalizacao)
    {
        if(localizacao != null) {
            campo.limpa(localizacao);
        }
        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }
//set morte add
    private void incrementaIdade()
    {
        idade++;
        if(idade >= IDADE_MAXIMA) {
        	setMorte();
        }
    }
////add teste para analizar se existem campos adjacentes livres no "for".    
    private void daALuz(List<Ovelha> novasOvelhas)
    {
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        int nascimentos = procria();
        for(int b = 0; (b < nascimentos) && (livre.size()>0); b++) {
            Localizacao loc = livre.remove(0);
            Ovelha jovem = new Ovelha(false, campo, loc);
            novasOvelhas.add(jovem);
        }
    }
//Alteração de == para <= linha 94    
    private int procria()
    {
        int nascimentos = 0;
        if(podeProcriar() && rand.nextDouble() <= PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }
//ok
    private boolean podeProcriar()
    {
        return idade >= IDADE_PROCRIACAO;
    }
}
