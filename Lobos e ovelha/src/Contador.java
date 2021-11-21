//import java.awt.Color;

public class Contador
{
    private String name;
    private int count;
//ok
    public Contador(String name)
    {
        this.name = name;
        count = 0;
    }
//ok    
    public String getName()
    {
        return name;
    }
//ok
    public int getCount()
    {
        return count;
    }
//ok
    public void increment()
    {
        count++;
    }
//ok    
    public void reset()
    {
        count = 0;
    }
}
