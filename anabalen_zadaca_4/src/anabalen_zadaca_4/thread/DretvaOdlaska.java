package anabalen_zadaca_4.thread;

import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.helper.Generator;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import java.util.List;

/**
 *
 * @author Ana-Marija
 */
public class DretvaOdlaska extends Thread {

    boolean aktivna = false;
    boolean radi = false;
    int iznosZona1;

    List<Automobil> sviAuti;
    PostavkeAplikacije postavke;
    Generator generator = new Generator();
    DretvaDolaska dretvaDolaska;

    List<Automobil> zona4;
    List<Automobil> zona3;
    List<Automobil> zona2;
    List<Automobil> zona1;

    public DretvaOdlaska(PostavkeAplikacije postavke, List<Automobil> sviAuti, List<Automobil> zona1, List<Automobil> zona2, List<Automobil> zona3, List<Automobil> zona4, DretvaDolaska dretvaDolaska) {
        this.postavke = postavke;
        this.sviAuti = sviAuti;
        this.zona1 = zona1;
        this.zona2 = zona2;
        this.zona3 = zona3;
        this.zona4 = zona4;
        this.dretvaDolaska = dretvaDolaska;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public void run() {
        System.out.println("Dretva aktivirana.");
        aktivna = true;

        while (aktivna) {

            if (sviAuti.isEmpty()) {
                radi = true;
                //System.out.println("Dretva odlaska zapocinje s radom.");

                //funkcija dretve
                odabirVlasnika();

            } else {
                System.out.println("Svi auti su isparkirani.");
            }

            try {
                int pauza = (int) ((postavke.getVremenskaJedinica() / postavke.getIntervalOdlaska()) * generator.generirajVrijednost3() * 10000);
                System.out.println("Dretva spava " + pauza + " milisekundi.");
                radi = false;
                sleep(pauza);
            } catch (InterruptedException ex) {
                aktivna = false;
            }
        }
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    private void odabirVlasnika() {

        for (int i = 0; i < zona1.size(); i++) {
            int izbor = generator.generirajOdabir();

        // TODO: proći for petljom kroz svaku listu zone i izgenerirati za svaki auto izbor i izvrsiti ga 
            switch (izbor) {
                case 0:
                    System.out.println("Redni broj auta " + zona1.get(i).getRedniBroj());
                    System.out.println(" ništa");
                    
                    break;
                case 1:
                    System.out.println("Redni broj auta " + zona1.get(i).getRedniBroj());
                    sviAuti.add(zona1.get(i));
                    zona1.remove(zona1.get(i));
                    System.out.println("izaći");
                    i--;
                    break;
                case 2:
                    System.out.println("Redni broj auta " + zona1.get(i).getRedniBroj());
                    System.out.println("produljiti");
                    iznosZona1 = dretvaDolaska.getIznosZona1();
                    iznosZona1 = iznosZona1 + zona1.get(i).getIznos();
                    dretvaDolaska.setIznosZona1(iznosZona1);
                    break;
            }
        }
    }
}
