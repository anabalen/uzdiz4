package anabalen_zadaca_4.thread;

import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana-Marija
 */
public class DretvaKontrole extends Thread {

    boolean aktivna = false;
    boolean radi = false;

    int kazneZona1;
    int kazneZona2;
    int kazneZona3;
    int kazneZona4;

    PostavkeAplikacije postavke;
    List<Automobil> sviAuti;
    List<Automobil> zona4;
    List<Automobil> zona3;
    List<Automobil> zona2;
    List<Automobil> zona1;

    List<Automobil> deponij = new ArrayList<>();

    public DretvaKontrole(PostavkeAplikacije postavke, List<Automobil> sviAuti, List<Automobil> zona1, List<Automobil> zona2, List<Automobil> zona3, List<Automobil> zona4) {
        this.postavke = postavke;
        this.sviAuti = sviAuti;
        this.zona1 = zona1;
        this.zona2 = zona2;
        this.zona3 = zona3;
        this.zona4 = zona4;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public void run() {
        //System.out.println("Dretva aktivirana.");
        aktivna = true;

        while (aktivna) {

            if (!sviAuti.isEmpty()) {
                radi = true;
                //System.out.println("Dretva zapocinje s radom.");

                //funkcija dretve
                kontrolaParkinga();

            } else {
                System.out.println("Svi auti su sparkirani.");
            }

            try {
                int pauza = (int) ((postavke.getVremenskaJedinica() / postavke.getIntervalKontrole()) * 10000);
                //System.out.println("Dretva spava " + pauza + " milisekundi.");
                radi = false;
                sleep(pauza);
            } catch (InterruptedException ex) {
                aktivna = false;
            }
        }
    }

    private void kontrolaParkinga() {
        /* za prvu zonu */
        for (int i = 0; i < zona1.size(); i++) {
            /* ako automobil u zoni 1 nema vazecu kartu, naplati kaznu */
            if (!zona1.get(i).isVazecaKarta()) {
                kazneZona1 = kazneZona1 + ((postavke.getBrojZona() + 1 - 1) * postavke.getCijenaJedinice() * postavke.getKaznaParkiranja());
                deponij.add(zona1.get(i));
                zona1.remove(zona1.get(i));

            }
        }

        /* za drugu zonu */
        for (int i = 0; i < zona2.size(); i++) {
            /* ako automobil u zoni 1 nema vazecu kartu, naplati kaznu */
            if (!zona2.get(i).isVazecaKarta()) {
                kazneZona2 = kazneZona2 + ((postavke.getBrojZona() + 1 - 2) * postavke.getCijenaJedinice() * postavke.getKaznaParkiranja());
                deponij.add(zona2.get(i));
                zona2.remove(zona2.get(i));

            }
        }

        /* za trecu zonu */
        for (int i = 0; i < zona3.size(); i++) {
            /* ako automobil u zoni 1 nema vazecu kartu, naplati kaznu */
            if (!zona3.get(i).isVazecaKarta()) {
                kazneZona3 = kazneZona3 + ((postavke.getBrojZona() + 1 - 3) * postavke.getCijenaJedinice() * postavke.getKaznaParkiranja());
                deponij.add(zona3.get(i));
                zona3.remove(zona3.get(i));

            }
        }

        /* za cetvrtu zonu */
        for (int i = 0; i < zona4.size(); i++) {
            /* ako automobil u zoni 1 nema vazecu kartu, naplati kaznu */
            if (!zona4.get(i).isVazecaKarta()) {
                kazneZona4 = kazneZona4 + ((postavke.getBrojZona() + 1 - 4) * postavke.getCijenaJedinice() * postavke.getKaznaParkiranja());
                deponij.add(zona4.get(i));
                zona4.remove(zona4.get(i));

            }
        }

    }

    public int getKazneZona1() {
        return kazneZona1;
    }

    public int getKazneZona2() {
        return kazneZona2;
    }

    public int getKazneZona3() {
        return kazneZona3;
    }

    public int getKazneZona4() {
        return kazneZona4;
    }

    public List<Automobil> getZona4() {
        return zona4;
    }

    public List<Automobil> getZona3() {
        return zona3;
    }

    public List<Automobil> getZona2() {
        return zona2;
    }

    public List<Automobil> getZona1() {
        return zona1;
    }

    public List<Automobil> getDeponij() {
        return deponij;
    }

    public void setDeponij(List<Automobil> deponij) {
        this.deponij = deponij;
    }



}
