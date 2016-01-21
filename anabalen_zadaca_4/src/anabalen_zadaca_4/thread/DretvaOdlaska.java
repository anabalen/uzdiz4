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
    int iznosZona2;
    int iznosZona3;
    int iznosZona4;

    List<Automobil> sviAuti;
    PostavkeAplikacije postavke;
    Generator generator = new Generator();
    DretvaDolaska dretvaDolaska;
    int produljenje;

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
                    zona1.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    System.out.println("Redni broj auta " + zona1.get(i).getRedniBroj());
                    zona1.get(i).setVazecaKarta(false);
                    sviAuti.add(zona1.get(i));
                    zona1.remove(zona1.get(i));
                    System.out.println("izaći");
                    i--;
                    break;
                case 2:
                    System.out.println("Redni broj auta " + zona1.get(i).getRedniBroj());
                    System.out.println("produljiti");
                    produljenje = zona1.get(i).getProduljenje();
                    if (produljenje < 0) {
                        produljenje++;
                        zona1.get(i).setProduljenje(produljenje);
                        zona1.get(i).setVazecaKarta(true);
                        iznosZona1 = dretvaDolaska.getIznosZona1();
                        iznosZona1 = iznosZona1 + zona1.get(i).getIznos();
                        dretvaDolaska.setIznosZona1(iznosZona1);
                    } else {
                        System.out.println("Ne mogu produljiti parking zbog max puta.");
                        zona1.get(i).setVazecaKarta(false);
                    }
                    break;
            }
        }
        
        for (int i = 0; i < zona2.size(); i++) {
            int izbor = generator.generirajOdabir();

            switch (izbor) {
                case 0:
                    System.out.println("Redni broj auta " + zona2.get(i).getRedniBroj());
                    System.out.println(" ništa");
                    zona2.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    System.out.println("Redni broj auta " + zona2.get(i).getRedniBroj());
                    zona2.get(i).setVazecaKarta(false);
                    sviAuti.add(zona2.get(i));
                    zona2.remove(zona2.get(i));
                    
                    System.out.println("izaći");
                    i--;
                    break;
                case 2:
                    System.out.println("Redni broj auta " + zona2.get(i).getRedniBroj());
                    System.out.println("produljiti");
                    produljenje = zona2.get(i).getProduljenje();
                    if (produljenje < 1) {
                        produljenje++;
                        zona2.get(i).setProduljenje(produljenje);
                        zona2.get(i).setVazecaKarta(true);
                        iznosZona2 = dretvaDolaska.getIznosZona2();
                        iznosZona2 = iznosZona2 + zona2.get(i).getIznos();
                        dretvaDolaska.setIznosZona2(iznosZona2);
                    } else {
                        System.out.println("Ne mogu produljiti parking zbog max puta.");
                        zona2.get(i).setVazecaKarta(false);
                    }
                    break;
            }
        }
        
        for (int i = 0; i < zona3.size(); i++) {
            int izbor = generator.generirajOdabir();

            switch (izbor) {
                case 0:
                    System.out.println("Redni broj auta " + zona3.get(i).getRedniBroj());
                    System.out.println(" ništa");
                    zona3.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    System.out.println("Redni broj auta " + zona3.get(i).getRedniBroj());
                    zona3.get(i).setVazecaKarta(false);
                    sviAuti.add(zona3.get(i));
                    zona3.remove(zona3.get(i));
                    
                    System.out.println("izaći");
                    i--;
                    break;
                case 2:
                    System.out.println("Redni broj auta " + zona3.get(i).getRedniBroj());
                    System.out.println("produljiti");
                    produljenje = zona3.get(i).getProduljenje();
                    if (produljenje < 2) {
                        produljenje++;
                        zona3.get(i).setProduljenje(produljenje);
                        zona3.get(i).setVazecaKarta(true);
                        iznosZona3 = dretvaDolaska.getIznosZona2();
                        iznosZona3 = iznosZona3 + zona3.get(i).getIznos();
                        dretvaDolaska.setIznosZona2(iznosZona3);
                    } else {
                        System.out.println("Ne mogu produljiti parking zbog max puta.");
                        zona3.get(i).setVazecaKarta(false);
                        
                    }
                    break;
            }
        }
        
        for (int i = 0; i < zona4.size(); i++) {
            int izbor = generator.generirajOdabir();

            switch (izbor) {
                case 0:
                    System.out.println("Redni broj auta " + zona4.get(i).getRedniBroj());
                    System.out.println(" ništa");
                    zona4.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    System.out.println("Redni broj auta " + zona4.get(i).getRedniBroj());
                    zona4.get(i).setVazecaKarta(false);
                    sviAuti.add(zona4.get(i));
                    zona4.remove(zona4.get(i));
                    
                    System.out.println("izaći");
                    i--;
                    break;
                case 2:
                    System.out.println("Redni broj auta " + zona4.get(i).getRedniBroj());
                    System.out.println("produljiti");
                    produljenje = zona4.get(i).getProduljenje();
                    if (produljenje < 3) {
                        produljenje++;
                        zona4.get(i).setProduljenje(produljenje);
                        zona4.get(i).setVazecaKarta(true);
                        iznosZona4 = dretvaDolaska.getIznosZona2();
                        iznosZona4 = iznosZona4 + zona4.get(i).getIznos();
                        dretvaDolaska.setIznosZona2(iznosZona4);
                    } else {
                        System.out.println("Ne mogu produljiti parking zbog max puta.");
                        zona4.get(i).setVazecaKarta(false);
                    }
                    break;
            }
        }
        
        
    }
}
