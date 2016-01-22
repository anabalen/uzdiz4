package anabalen_zadaca_4.helper;

import java.util.Random;

/**
 *
 * @author Ana-Marija
 */
public class Generator {

    public Generator() {
    }

    public float generirajVrijednost1() {
        float generiranaVrijednost1 = 0;

        Random slucajniBroj = new Random();
        generiranaVrijednost1 = slucajniBroj.nextFloat();

        return generiranaVrijednost1;
    }

    public float generirajVrijednost2() {
        float generiranaVrijednost2 = 0;

        Random slucajniBroj = new Random();
        generiranaVrijednost2 = slucajniBroj.nextFloat();

        return generiranaVrijednost2;
    }

    public float generirajVrijednost3() {
        float generiranaVrijednost3 = 0;

        Random slucajniBroj = new Random();
        generiranaVrijednost3 = slucajniBroj.nextFloat();

        return generiranaVrijednost3;
    }

    public int generirajOdabir() {
        int generiraniOdabir = 0;
        int generiranaVrijednostZaOdabir;

        Random slucajniBroj = new Random();
        generiranaVrijednostZaOdabir = slucajniBroj.nextInt((100 - 0) + 1);

        if (generiranaVrijednostZaOdabir >= 0 && generiranaVrijednostZaOdabir <= 25) {
            generiraniOdabir = 0;
        } else if (generiranaVrijednostZaOdabir > 25 && generiranaVrijednostZaOdabir <= 75) {
            generiraniOdabir = 1;
        } else if (generiranaVrijednostZaOdabir > 75 && generiranaVrijednostZaOdabir <= 100) {
            generiraniOdabir = 2;
        }

        //System.out.println(generiranaVrijednostZaOdabir + " i generirani odabir je: " + generiraniOdabir);
        return generiraniOdabir;
    }

}
