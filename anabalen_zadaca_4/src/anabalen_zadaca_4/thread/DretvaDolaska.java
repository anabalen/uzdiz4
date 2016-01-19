package anabalen_zadaca_4.thread;

import anabalen_zadaca_4.MainClass;
import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.helper.Generator;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.parkiraliste.Parkiraliste;
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

    List<Automobil> zona4;
    List<Automobil> zona3;
    List<Automobil> zona2;
    List<Automobil> zona1;

    PostavkeAplikacije postavke;
    Parkiraliste parkiraliste;
    List<Automobil> sviAuti;
    Automobil automobil;
    Generator generator = new Generator();

    public DretvaDolaska(int interval, Parkiraliste parkiraliste, PostavkeAplikacije postavke, List<Automobil> sviAuti) {
        this.interval = interval;
        this.parkiraliste = parkiraliste;
        this.postavke = postavke;
        this.sviAuti = sviAuti;
    }

    @Override
    public void interrupt() {
        if (!radi) {
            super.interrupt();
            //System.out.println("Dretva je prekinuta.");

        } else {
            System.out.println("Dretva radi!");
        }
    }

    @Override
    public void run() {
        long pocetak, vrijeme;

        //System.out.println("Dretva aktivirana.");
        aktivna = true;

        while (aktivna) {

            if (!sviAuti.isEmpty()) {
                radi = true;
                //System.out.println("Dretva zapocinje s radom.");

                //funkcija dretve
                dolazakNaParking();

            } else {
                System.out.println("Svi auti su sparkirani.");
            }

            try {
                int pauza = (int) ((postavke.getVremenskaJedinica() / interval) * generator.generirajVrijednost1() * 10000);
                //System.out.println("Dretva spava " + pauza + " milisekundi.");
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
            System.out.println("Kreirana zona 4");
            brojZona--;
        }
        if (brojZona == 3) {
            zona3 = new ArrayList<>();
            System.out.println("Kreirana zona 3");
            brojZona--;
        }
        if (brojZona == 2) {
            zona2 = new ArrayList<>();
            System.out.println("Kreirana zona 2");
            brojZona--;
        }
        if (brojZona == 1) {
            zona1 = new ArrayList<>();
            System.out.println("Kreirana zona 1");
        }
    }

    private void dolazakNaParking() {

        for (int i = 0; i < sviAuti.size(); i++) {
            automobil = sviAuti.get(i);
            zonaAuta = sviAuti.get(i).getZona();
            if (parkiraliste.isStatus()) {

                if (zonaAuta == 4) {
                    int kapacitetZone4 = zonaAuta * postavke.getKapacitetZone();

                    if (kapacitetZone4 > zona4.size()) {
                        zona4.add(automobil);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        System.out.println("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran");

                        //računanje iznosa plaćanja
                        iznosZona4 = iznosZona4 + automobil.getIznos();
                    } else {
                        System.out.println("U zoni 4 nema mjesta.");
                    }
                } else if (zonaAuta == 3) {
                    int kapacitetZone3 = zonaAuta * postavke.getKapacitetZone();

                    if (kapacitetZone3 > zona3.size()) {
                        zona3.add(automobil);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        System.out.println("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran");

                        //računanje iznosa plaćanja
                        iznosZona3 = iznosZona3 + automobil.getIznos();
                    } else {
                        System.out.println("U zoni 3 nema mjesta.");
                    }
                } else if (zonaAuta == 2) {
                    int kapacitetZone2 = zonaAuta * postavke.getKapacitetZone();

                    if (kapacitetZone2 > zona2.size()) {
                        zona2.add(automobil);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        System.out.println("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran");

                        //računanje iznosa plaćanja
                        iznosZona2 = iznosZona2 + automobil.getIznos();
                    } else {
                        System.out.println("U zoni 3 nema mjesta.");
                    }
                } else if (zonaAuta == 1) {
                    int kapacitetZone1 = zonaAuta * postavke.getKapacitetZone();

                    if (kapacitetZone1 > zona1.size()) {
                        zona1.add(automobil);
                        sviAuti.remove(automobil);
                        automobil.setStatus(true);
                        System.out.println("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: parkiran");

                        //računanje iznosa plaćanja
                        iznosZona1 = iznosZona1 + automobil.getIznos();
                    } else {
                        System.out.println("U zoni 1 nema mjesta.");
                    }
                }
                //vraćanje "i" na nulu zbog brisanja elementa iz liste
                i--;
            } else {
                automobil.setStatus(false);
                System.out.println("Automobil " + automobil.getRedniBroj() + ", zona " + automobil.getZona() + " iznos " + automobil.getIznos() + " status: nije parkiran");
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

}
