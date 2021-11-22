//import java.awt.Color;

//Nada muito grnade aqui não, só organização mesmo.

public class Contador{
    private String name;
    private int count;
    
    public Contador(String name){
        this.name = name;
        count = 0;
    }
    
    public String getName(){return name;}

    public int getCount(){return count;}

    public void increment(){count++;}
   
    public void reset(){count = 0;}
}
