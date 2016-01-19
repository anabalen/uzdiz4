package anabalen_zadaca_4.thread;

import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.parkiraliste.Parkiraliste;
import java.util.List;

/**
 *
 * @author Ana-Marija
 */
public class DretvaOdlaska extends Thread {

    boolean aktivna = false;
    boolean radi = false;
    
    List<Automobil> sviAuti;

    public DretvaOdlaska() {
        //sta ce joj se prosljedjivati
    }

    @Override
    public void interrupt() {
        if (!radi) {
            super.interrupt();
            System.out.println("Dretva je prekinuta.");

        } else {
            System.out.println("Dretva radi!");
        }
    }
    
    @Override
    public void run() {
        long pocetak, vrijeme;

        System.out.println("Dretva aktivirana.");
        aktivna = true;

        while (aktivna) {

            if (!sviAuti.isEmpty()) {
                radi = true;
                System.out.println("Dretva zapocinje s radom.");

                //funkcija dretve
                

            } else {
                System.out.println("Svi auti su sparkirani.");
            }
/*
            try {
                int pauza = (int) ((postavke.getVremenskaJedinica() / interval) * generator.generirajVrijednost1() * 10000);
                System.out.println("Dretva spava " + pauza + " milisekundi.");
                radi = false;
                sleep(pauza);
            } catch (InterruptedException ex) {
                aktivna = false;
            }*/
        }
    }
    
    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }
}
