package anabalen_zadaca_4.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

/**
 *
 * @author Ana-Marija
 */
public class Generator {
    
    public Generator(){}
    
    public float generirajVrijednost1(){
        float generiranaVrijednost1 = 0;

        Random slucajniBroj = new Random();
        generiranaVrijednost1 = slucajniBroj.nextFloat();
   
        return generiranaVrijednost1;
    }
    
    public float generirajVrijednost2(){
        float generiranaVrijednost2 = 0;

        Random slucajniBroj = new Random();
        generiranaVrijednost2 = slucajniBroj.nextFloat();
   
        return generiranaVrijednost2;
    }
    
    public float generirajVrijednost3(){
        float generiranaVrijednost3 = 0;

        Random slucajniBroj = new Random();
        generiranaVrijednost3 = slucajniBroj.nextFloat();
   
        return generiranaVrijednost3;
    }
    
    
}
