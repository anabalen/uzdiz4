package anabalen_zadaca_4;

import anabalen_zadaca_4.controller.KontrolerAplikacije;

/**
 *
 * @author Ana-Marija
 */
public class MainClass {

    public static KontrolerAplikacije kontroler;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        kontroler = new KontrolerAplikacije();
        kontroler.pocetniZaslon(args);
    }
}
