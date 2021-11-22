//ok
public class Localizacao
{
    private int linha;
    private int coluna;
//ok
    public Localizacao(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }
//ok
    public boolean equals(Object obj)
    {
        if(obj instanceof Localizacao) {
        	Localizacao outra = (Localizacao) obj;
            return linha == outra.getLinha() && coluna == outra.getColuna();
        }
        else {
            return false;
        }
    }
//ok    
    public String toString()
    {
        return linha + "," + coluna;
    }
//ok 
    public int hashCode()
    {
        return (linha << 16) + coluna;
    }
//ok
    public int getLinha()
    {
        return linha;
    }
//ok  
    public int getColuna()
    {
        return coluna;
    }
}
