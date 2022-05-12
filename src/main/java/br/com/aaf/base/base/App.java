package br.com.aaf.base.base;

import java.util.ArrayList;
import java.util.List;

import br.com.aaf.base.whats.model.Parametro;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        EnviadorWhats a = new EnviadorWhats();
        List<Parametro> pa = new ArrayList<Parametro>();
        Parametro p = new Parametro();
        p.setName("name");
        p.setValue("JoJOquinha pre pre ");
        pa.add(p);
        a.enviarWhats("new_chat_v1", "5548999484089", pa);
    }
}
