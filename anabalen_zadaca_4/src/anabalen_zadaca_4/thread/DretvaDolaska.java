package anabalen_zadaca_4.thread;

import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.helper.Generator;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.parkiraliste.Parkiraliste;
import anabalen_zadaca_4.view.PrikazPodataka;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana-Marija
 */
public class DretvaDolaska extends Thread {

    int interval;
    boolean aktivna = false;
    boolean radi = false;

    int zonaAuta;

    int iznosZona1;
    int iznosZona2;
    int iznosZona3;
    int iznosZona4;

    int kapacitetZone1;
    int kapacitetZone2;
    int kapacitetZone3;
    int kapacitetZone4;
    
    int brojParkiranja;

    List<Automobil> zona4 = new ArrayList<>();
    List<Automobil> zona3 = new ArrayList<>();
    List<Automobil> zona2 = new ArrayList<>();
    List<Automobil> zona1 = new ArrayList<>();

    List<Automobil> neparkirani = new ArrayList<>();

    PostavkeAplikacije postavke;
    Parkiraliste parkiraliste;
    List<Automobil> sviAuti;
    Automobil automobil;
    Generator generator = new Generator();
    PrikazPodataka prikaz;

    public DretvaDolaska(int interval, Parkiraliste parkiraliste, PostavkeAplikacije postavke, List<Automobil> sviAuti, PrikazPodataka prikaz) {
        this.interval = interval;
        this.parkiraliste = parkiraliste;
        this.postavke = postavke;
        this.sviAuti = sviAuti;
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

            if (!sviAuti.isEmpty()) {
                radi = true;

                //funkcija dretve
                dolazakNaParking();

            } else {
                prikaz.ispisi("Svi auti su sparkirani. \n");
            }

            try {
                int pauza = (int) ((postavke.getVremenskaJedinica() / interval) * generator.generirajVrijednost1() * 1000);
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

    public void postavljanjeZona() {
        int brojZona = parkiraliste.getBrZona();

        if (brojZona == 4) {
            zona4 = new ArrayList<>();
            brojZona--;
        }
        if (brojZona == 3) {
            zona3 = new ArrayList<>();
            brojZona--;
        }
        if (brojZona == 2) {
            zona2 = new ArrayList<>();
            brojZona--;
        }
        if (brojZona == 1) {
            zona1 = new ArrayList<>();
        }
    }

    private void dolazakNaParking() {

        for (int i = 0; i < sviAuti.size(); i++) {
            automobil = sviAuti.get(i);
            zonaAuta = sviAuti.get(i).getZona();
            if (parkiraliste.isStatus()) {

                if (zonaAuta == 4) {
                    int kapacitetZone4 = zonaAuta * postavke.getKapacitetZone();
                    setKapacitetZone4(kapacitetZone4);

                    if (kapacitetZone4 > zona4.size()) {
                        zona4.add(automobil);
                       //broj parkiranja povecavati svaki puta kad se auto sparkira
                        brojParkiranja = automobil.getBrojParkiranja();
                        brojParkiranja++;
                        automobil.setBrojParkiranja(brojParkiranja);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        automobil.setVazecaKarta(true);
                        prikaz.ispisi("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran \n");

                        //računanje iznosa plaćanja
                        iznosZona4 = iznosZona4 + automobil.getIznos();
                    } else {
                        prikaz.ispisi("U zoni 4 nema mjesta za automobil broj." + automobil.getRedniBroj() + "\n");
                    }
                } else if (zonaAuta == 3) {
                    int kapacitetZone3 = zonaAuta * postavke.getKapacitetZone();
                    setKapacitetZone3(kapacitetZone3);

                    if (kapacitetZone3 > zona3.size()) {
                        zona3.add(automobil);
                        //broj parkiranja povecavati svaki puta kad se auto sparkira
                        brojParkiranja = automobil.getBrojParkiranja();
                        brojParkiranja++;
                        automobil.setBrojParkiranja(brojParkiranja);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        automobil.setVazecaKarta(true);
                        prikaz.ispisi("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran \n");

                        //računanje iznosa plaćanja
                        iznosZona3 = iznosZona3 + automobil.getIznos();
                    } else {
                        prikaz.ispisi("U zoni 3 nema mjesta za automobil broj." + automobil.getRedniBroj() + "\n");
                    }
                } else if (zonaAuta == 2) {
                    int kapacitetZone2 = zonaAuta * postavke.getKapacitetZone();
                    setKapacitetZone2(kapacitetZone2);

                    if (kapacitetZone2 > zona2.size()) {
                        zona2.add(automobil);
                        //broj parkiranja povecavati svaki puta kad se auto sparkira
                        brojParkiranja = automobil.getBrojParkiranja();
                        brojParkiranja++;
                        automobil.setBrojParkiranja(brojParkiranja);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        automobil.setVazecaKarta(true);
                        prikaz.ispisi("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran \n");

                        //računanje iznosa plaćanja
                        iznosZona2 = iznosZona2 + automobil.getIznos();
                    } else {
                        prikaz.ispisi("U zoni 2 nema mjesta za automobil broj." + automobil.getRedniBroj() + "\n");
                    }
                } else if (zonaAuta == 1) {
                    int kapacitetZone1 = zonaAuta * postavke.getKapacitetZone();
                    setKapacitetZone1(kapacitetZone1);
                    if (kapacitetZone1 > zona1.size()) {
                        zona1.add(automobil);
                        //broj parkiranja povecavati svaki puta kad se auto sparkira
                        brojParkiranja = automobil.getBrojParkiranja();
                        brojParkiranja++;
                        automobil.setBrojParkiranja(brojParkiranja);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        automobil.setVazecaKarta(true);
                        prikaz.ispisi("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran \n");

                        //računanje iznosa plaćanja
                        iznosZona1 = iznosZona1 + automobil.getIznos();
                    } else {
                        prikaz.ispisi("U zoni 1 nema mjesta za automobil broj." + automobil.getRedniBroj() + "\n");
                    }
                }
                //vraćanje "i" na nulu zbog brisanja elementa iz liste
                i--;
            } else {
                automobil.setStatus(false);
                prikaz.ispisi("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: nije parkiran \n");

                neparkirani.add(automobil);
            }
        }

    }

    public int getIznosZona1() {
        return iznosZona1;
    }

    public int getIznosZona2() {
        return iznosZona2;
    }

    public int getIznosZona3() {
        return iznosZona3;
    }

    public int getIznosZona4() {
        return iznosZona4;
    }

    public void setIznosZona1(int iznosZona1) {
        this.iznosZona1 = iznosZona1;
    }

    public void setIznosZona2(int iznosZona2) {
        this.iznosZona2 = iznosZona2;
    }

    public void setIznosZona3(int iznosZona3) {
        this.iznosZona3 = iznosZona3;
    }

    public void setIznosZona4(int iznosZona4) {
        this.iznosZona4 = iznosZona4;
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

    public List<Automobil> getSviAuti() {
        return sviAuti;
    }

    public void setZona4(List<Automobil> zona4) {
        this.zona4 = zona4;
    }

    public void setZona3(List<Automobil> zona3) {
        this.zona3 = zona3;
    }

    public void setZona2(List<Automobil> zona2) {
        this.zona2 = zona2;
    }

    public void setZona1(List<Automobil> zona1) {
        this.zona1 = zona1;
    }

    public void setSviAuti(List<Automobil> sviAuti) {
        this.sviAuti = sviAuti;
    }

    public List<Automobil> getNeparkirani() {
        return neparkirani;
    }

    public void setNeparkirani(List<Automobil> neparkirani) {
        this.neparkirani = neparkirani;
    }

    public int getKapacitetZone1() {
        return kapacitetZone1;
    }

    public int getKapacitetZone2() {
        return kapacitetZone2;
    }

    public int getKapacitetZone3() {
        return kapacitetZone3;
    }

    public int getKapacitetZone4() {
        return kapacitetZone4;
    }

    public void setKapacitetZone1(int kapacitetZone1) {
        this.kapacitetZone1 = kapacitetZone1;
    }

    public void setKapacitetZone2(int kapacitetZone2) {
        this.kapacitetZone2 = kapacitetZone2;
    }

    public void setKapacitetZone3(int kapacitetZone3) {
        this.kapacitetZone3 = kapacitetZone3;
    }

    public void setKapacitetZone4(int kapacitetZone4) {
        this.kapacitetZone4 = kapacitetZone4;
    }
    
    

}
