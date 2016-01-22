package anabalen_zadaca_4.thread;

import anabalen_zadaca_4.model.Automobil;
import anabalen_zadaca_4.helper.Generator;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.view.PrikazPodataka;
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
    private PrikazPodataka prikaz;

    int brojParkiranja;

    List<Automobil> zona4;
    List<Automobil> zona3;
    List<Automobil> zona2;
    List<Automobil> zona1;

    public DretvaOdlaska(PostavkeAplikacije postavke, List<Automobil> sviAuti, List<Automobil> zona1, List<Automobil> zona2, List<Automobil> zona3, List<Automobil> zona4, DretvaDolaska dretvaDolaska, PrikazPodataka prikaz) {
        this.postavke = postavke;
        this.sviAuti = sviAuti;
        this.zona1 = zona1;
        this.zona2 = zona2;
        this.zona3 = zona3;
        this.zona4 = zona4;
        this.dretvaDolaska = dretvaDolaska;
        this.prikaz = prikaz;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public void run() {
        aktivna = true;

        while (aktivna) {

            if (sviAuti.isEmpty()) {
                radi = true;
                //funkcija dretve
                odabirVlasnika();

            } else {
                //System.out.println("Svi auti su isparkirani.");
            }

            try {
                int pauza = (int) ((postavke.getVremenskaJedinica() / postavke.getIntervalOdlaska()) * generator.generirajVrijednost3() * 1000);
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

            switch (izbor) {
                case 0:
                    prikaz.ispisi("Redni broj auta " + zona1.get(i).getRedniBroj() + ", odabir vlasnika - ništa \n");
                    zona1.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    prikaz.ispisi("Redni broj auta " + zona1.get(i).getRedniBroj() + ", odabir vlasnika - izaći \n");
                    zona1.get(i).setVazecaKarta(false);
                    sviAuti.add(zona1.get(i));
                    zona1.remove(zona1.get(i));
                    i--;
                    break;
                case 2:
                    prikaz.ispisi("Redni broj auta " + zona1.get(i).getRedniBroj() + "odabir vlasnika - produljiti \n");
                    produljenje = zona1.get(i).getProduljenje();
                    if (produljenje < 0) {
                        produljenje++;
                        brojParkiranja = zona1.get(i).getBrojParkiranja();
                        brojParkiranja++;
                        zona1.get(i).setBrojParkiranja(brojParkiranja);
                        zona1.get(i).setProduljenje(produljenje);
                        zona1.get(i).setVazecaKarta(true);
                        iznosZona1 = dretvaDolaska.getIznosZona1();
                        iznosZona1 = iznosZona1 + zona1.get(i).getIznos();
                        dretvaDolaska.setIznosZona1(iznosZona1);
                    } else {
                        prikaz.ispisi("Redni broj auta " + zona1.get(i).getRedniBroj() + " - ne mogu produljiti parking zbog max puta.\n ");
                        zona1.get(i).setVazecaKarta(false);
                    }
                    break;
            }
        }

        for (int i = 0; i < zona2.size(); i++) {
            int izbor = generator.generirajOdabir();

            switch (izbor) {
                case 0:
                    prikaz.ispisi("Redni broj auta " + zona2.get(i).getRedniBroj() + ", odabir vlasnika - ništa \n");
                    zona2.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    prikaz.ispisi("Redni broj auta " + zona2.get(i).getRedniBroj() + ", odabir vlasnika - izaći \n");
                    zona2.get(i).setVazecaKarta(false);
                    sviAuti.add(zona2.get(i));
                    zona2.remove(zona2.get(i));

                    i--;
                    break;
                case 2:
                    prikaz.ispisi("Redni broj auta " + zona2.get(i).getRedniBroj() + "odabir vlasnika - produljiti \n");
                    produljenje = zona2.get(i).getProduljenje();
                    if (produljenje < 1) {
                        produljenje++;
                        brojParkiranja = zona2.get(i).getBrojParkiranja();
                        brojParkiranja++;
                        zona2.get(i).setBrojParkiranja(brojParkiranja);
                        zona2.get(i).setProduljenje(produljenje);
                        zona2.get(i).setVazecaKarta(true);
                        iznosZona2 = dretvaDolaska.getIznosZona2();
                        iznosZona2 = iznosZona2 + zona2.get(i).getIznos();
                        dretvaDolaska.setIznosZona2(iznosZona2);
                    } else {
                        prikaz.ispisi("Redni broj auta " + zona2.get(i).getRedniBroj() + " - ne mogu produljiti parking zbog max puta. \n");
                        zona2.get(i).setVazecaKarta(false);
                    }
                    break;
            }
        }

        for (int i = 0; i < zona3.size(); i++) {
            int izbor = generator.generirajOdabir();

            switch (izbor) {
                case 0:
                    prikaz.ispisi("Redni broj auta " + zona3.get(i).getRedniBroj() + ", odabir vlasnika - ništa \n");
                    zona3.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    prikaz.ispisi("Redni broj auta " + zona3.get(i).getRedniBroj() + ", odabir vlasnika - izaći \n");
                    zona3.get(i).setVazecaKarta(false);
                    sviAuti.add(zona3.get(i));
                    zona3.remove(zona3.get(i));

                    i--;
                    break;
                case 2:
                    prikaz.ispisi("Redni broj auta " + zona3.get(i).getRedniBroj() + "odabir vlasnika - produljiti \n");
                    produljenje = zona3.get(i).getProduljenje();
                    if (produljenje < 2) {
                        produljenje++;
                        brojParkiranja = zona3.get(i).getBrojParkiranja();
                        brojParkiranja++;
                        zona3.get(i).setBrojParkiranja(brojParkiranja);
                        zona3.get(i).setProduljenje(produljenje);
                        zona3.get(i).setVazecaKarta(true);
                        iznosZona3 = dretvaDolaska.getIznosZona3();
                        iznosZona3 = iznosZona3 + zona3.get(i).getIznos();
                        dretvaDolaska.setIznosZona3(iznosZona3);
                    } else {
                        prikaz.ispisi("Redni broj auta " + zona3.get(i).getRedniBroj() + " - ne mogu produljiti parking zbog max puta. \n");
                        zona3.get(i).setVazecaKarta(false);

                    }
                    break;
            }
        }

        for (int i = 0; i < zona4.size(); i++) {
            int izbor = generator.generirajOdabir();

            switch (izbor) {
                case 0:
                    prikaz.ispisi("Redni broj auta " + zona4.get(i).getRedniBroj() + ", odabir vlasnika - ništa \n");
                    zona4.get(i).setVazecaKarta(false);

                    break;
                case 1:
                    prikaz.ispisi("Redni broj auta " + zona4.get(i).getRedniBroj() + ", odabir vlasnika - izaći \n");
                    zona4.get(i).setVazecaKarta(false);
                    sviAuti.add(zona4.get(i));
                    zona4.remove(zona4.get(i));

                    i--;
                    break;
                case 2:
                    prikaz.ispisi("Redni broj auta " + zona4.get(i).getRedniBroj() + "odabir vlasnika - produljiti \n");
                    produljenje = zona4.get(i).getProduljenje();
                    if (produljenje < 3) {
                        produljenje++;
                        brojParkiranja = zona4.get(i).getBrojParkiranja();
                        brojParkiranja++;
                        zona4.get(i).setBrojParkiranja(brojParkiranja);
                        zona4.get(i).setProduljenje(produljenje);
                        zona4.get(i).setVazecaKarta(true);
                        iznosZona4 = dretvaDolaska.getIznosZona4();
                        iznosZona4 = iznosZona4 + zona4.get(i).getIznos();
                        dretvaDolaska.setIznosZona4(iznosZona4);
                    } else {
                        prikaz.ispisi("Redni broj auta " + zona4.get(i).getRedniBroj() + " - ne mogu produljiti parking zbog max puta. \n");
                        zona4.get(i).setVazecaKarta(false);
                    }
                    break;
            }
        }

    }
}
