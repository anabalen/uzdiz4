package anabalen_zadaca_4.controller;

import anabalen_zadaca_4.model.Automobil;
import anabalen_zadaca_4.helper.AutomobilHelper;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.model.Parkiraliste;
import anabalen_zadaca_4.state.Kontekst;
import anabalen_zadaca_4.state.OtvoriParkiraliste;
import anabalen_zadaca_4.state.Stanje;
import anabalen_zadaca_4.state.ZatvoriParkiraliste;
import anabalen_zadaca_4.thread.DretvaDolaska;
import anabalen_zadaca_4.thread.DretvaKontrole;
import anabalen_zadaca_4.thread.DretvaOdlaska;
import anabalen_zadaca_4.view.PrikazPodataka;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ana-Marija
 */
public class KontrolerAplikacije {

    private PostavkeAplikacije postavke;
    private PrikazPodataka prikaz;
    boolean statusParkinga = true;

    private boolean aktivnaDretvaDolaska;
    private boolean aktivnaDretvaOdlaska;
    private boolean aktivnaDretvaKontrole;
    private DretvaDolaska dretvaDolaska;
    private DretvaOdlaska dretvaOdlaska;
    private DretvaKontrole dretvaKontrole;

    List<Automobil> zona4;
    List<Automobil> zona3;
    List<Automobil> zona2;
    List<Automobil> zona1;
    List<Automobil> deponij;

    public KontrolerAplikacije() {
    }

    public void pocetniZaslon(String[] argumenti) {
        ValidatorPostavki validator = new ValidatorPostavki();
        postavke = validator.ucitajPostavke(argumenti);

        prikaz = new PrikazPodataka();
        prikaz.ispisiIzbornik();

        /* kreiranje parkirališta */
        Parkiraliste parkiraliste = new Parkiraliste(statusParkinga, postavke.getBrojZona());


        /* inicijalizacija automobila */
        AutomobilHelper aHelper = new AutomobilHelper();

        List<Automobil> sviAuti = aHelper.kreirajAutomobil(postavke, parkiraliste);

        /* pokreni dretvu parkiranja */
        if (!aktivnaDretvaDolaska) {
            dretvaDolaska = new DretvaDolaska(postavke.getIntervalDolaska(), parkiraliste, postavke, sviAuti, prikaz);
            //postavljanje broja zona
            dretvaDolaska.postavljanjeZona();
            dretvaDolaska.start();
            aktivnaDretvaDolaska = true;
            zona1 = dretvaDolaska.getZona1();
            zona2 = dretvaDolaska.getZona2();
            zona3 = dretvaDolaska.getZona3();
            zona4 = dretvaDolaska.getZona4();
            sviAuti = dretvaDolaska.getSviAuti();

        } else {
            prikaz.ispisi("Dretva dolaska je vec pokrenuta. \n");
        }

        /* pokreni dretvu odlaska */
        if (!aktivnaDretvaOdlaska) {
            dretvaOdlaska = new DretvaOdlaska(postavke, sviAuti, zona1, zona2, zona3, zona4, dretvaDolaska, prikaz);

            dretvaOdlaska.start();
            aktivnaDretvaOdlaska = true;
        } else {
            prikaz.ispisi("Dretva odlaska je vec pokrenuta. \n");
        }

        /* pokreni dretvu kontrole */
        if (!aktivnaDretvaKontrole) {
            dretvaKontrole = new DretvaKontrole(postavke, sviAuti, zona1, zona2, zona3, zona4, prikaz);

            dretvaKontrole.start();
            aktivnaDretvaKontrole = true;
        } else {
            prikaz.ispisi("Dretva kontrole je vec pokrenuta. \n");
        }

        Kontekst context = new Kontekst();
        Parkiraliste noviStatus;

        String unos = "";

        do {
            try {
                Scanner scanner = new Scanner(System.in);
                unos = scanner.nextLine();
                String[] words = unos.split("\\s+");
            } catch (Exception e) {
                //odbaci
            }
            if (unos.matches("[0-9]")) {
                switch (Integer.parseInt(unos)) {
                    case 1:
                        /* zatvaranje parkirališta za nove ulaze automobila */

                        if (!parkiraliste.isStatus()) {
                            prikaz.ispisi("|**************************************| \n");
                            prikaz.ispisi("   Parkiralište je već zatvoreno. \n");
                            prikaz.ispisi("|**************************************| \n");
                        } else {
                            Stanje zatvori = new ZatvoriParkiraliste();
                            context.setState(zatvori);
                            noviStatus = context.doAction(parkiraliste);
                            prikaz.ispisi("|**************************************| \n");
                            prikaz.ispisi("   Parkiraliste je sada zatvoreno. \n");
                            prikaz.ispisi("|**************************************| \n");
                        }

                        break;
                    case 2:
                        /* otvaranje parkirališta za nove ulaze automobila */

                        if (parkiraliste.isStatus()) {
                            prikaz.ispisi("|**************************************| \n");
                            prikaz.ispisi("   Parkiralište je već otvoreno. \n");
                            prikaz.ispisi("|**************************************| \n");
                        } else {
                            Stanje otvori = new OtvoriParkiraliste();
                            context.setState(otvori);
                            noviStatus = context.doAction(parkiraliste);
                            prikaz.ispisi("|**************************************| \n");
                            prikaz.ispisi("   Parkiralište je sada otvoreno. \n");
                            prikaz.ispisi("|**************************************| \n");
                        }

                        break;
                    case 3:
                        /* ispis zarada od parkiranja po zonama */
                        prikaz.ispisi("|*********************************| \n");
                        if (postavke.getBrojZona() == 1) {
                            prikaz.ispisi("   Iznos u zoni 1: " + dretvaDolaska.getIznosZona1() + " \n");
                        } else if (postavke.getBrojZona() == 2) {
                            prikaz.ispisi("   Iznos u zoni 1: " + dretvaDolaska.getIznosZona1() + " \n"
                                    + "   Iznos u zoni 2: " + dretvaDolaska.getIznosZona2() + " \n");
                        } else if (postavke.getBrojZona() == 3) {
                            prikaz.ispisi("   Iznos u zoni 1: " + dretvaDolaska.getIznosZona1() + " \n"
                                    + "   Iznos u zoni 2: " + dretvaDolaska.getIznosZona2() + " \n"
                                    + "   Iznos u zoni 3: " + dretvaDolaska.getIznosZona3() + " \n");
                        } else if (postavke.getBrojZona() == 4) {
                            prikaz.ispisi("   Iznos u zoni 1: " + dretvaDolaska.getIznosZona1() + " \n"
                                    + "   Iznos u zoni 2: " + dretvaDolaska.getIznosZona2() + " \n"
                                    + "   Iznos u zoni 3: " + dretvaDolaska.getIznosZona3() + " \n"
                                    + "   Iznos u zoni 4: " + dretvaDolaska.getIznosZona4() + " \n");
                        }
                        prikaz.ispisi("|*********************************| \n");

                        break;
                    case 4:
                        /* ispis zarada od kazni po zonama */
                        prikaz.ispisi("|*********************************| \n");
                        if (postavke.getBrojZona() == 1) {
                            prikaz.ispisi("   Kazne u zoni 1: " + dretvaKontrole.getKazneZona1() + " \n");
                        } else if (postavke.getBrojZona() == 2) {
                            prikaz.ispisi("   Kazne u zoni 1: " + dretvaKontrole.getKazneZona1() + " \n"
                                    + "   Kazne u zoni 2: " + dretvaKontrole.getKazneZona2() + " \n");
                        } else if (postavke.getBrojZona() == 3) {
                            prikaz.ispisi("   Kazne u zoni 1: " + dretvaKontrole.getKazneZona1() + " \n"
                                    + "   Kazne u zoni 2: " + dretvaKontrole.getKazneZona2() + " \n"
                                    + "   Kazne u zoni 3: " + dretvaKontrole.getKazneZona3() + " \n");
                        } else if (postavke.getBrojZona() == 4) {
                            prikaz.ispisi("   Kazne u zoni 1: " + dretvaKontrole.getKazneZona1() + " \n"
                                    + "   Kazne u zoni 2: " + dretvaKontrole.getKazneZona2() + " \n"
                                    + "   Kazne u zoni 3: " + dretvaKontrole.getKazneZona3() + " \n"
                                    + "   Kazne u zoni 4: " + dretvaKontrole.getKazneZona4() + " \n");
                        }
                        prikaz.ispisi("|*********************************| \n");
                        break;

                    case 5:
                        int neparkirani1 = 0;
                        int neparkirani2 = 0;
                        int neparkirani3 = 0;
                        int neparkirani4 = 0;
                        for (int i = 0; i < dretvaDolaska.getNeparkirani().size(); i++) {
                            if (dretvaDolaska.getNeparkirani().get(i).getZona() == 1) {
                                neparkirani1++;
                            } else if (dretvaDolaska.getNeparkirani().get(i).getZona() == 2) {
                                neparkirani2++;
                            } else if (dretvaDolaska.getNeparkirani().get(i).getZona() == 3) {
                                neparkirani3++;
                            } else if (dretvaDolaska.getNeparkirani().get(i).getZona() == 4) {
                                neparkirani4++;
                            }
                        }

                        prikaz.ispisi("|******************************************| \n");
                        if (postavke.getBrojZona() == 1) {
                            prikaz.ispisi("   Broj neparkiranih u prvoj zoni je: " + neparkirani1 + " \n");
                        } else if (postavke.getBrojZona() == 2) {
                            prikaz.ispisi("   Broj neparkiranih u prvoj zoni je: " + neparkirani1 + " \n"
                                    + "   Broj neparkiranih u drugoj zoni je: " + neparkirani2 + " \n");
                        } else if (postavke.getBrojZona() == 3) {
                            prikaz.ispisi("   Broj neparkiranih u prvoj zoni je: " + neparkirani1 + " \n"
                                    + "   Broj neparkiranih u drugoj zoni je: " + neparkirani2 + " \n"
                                    + "   Broj neparkiranih u trecoj zoni je: " + neparkirani3 + " \n");
                        } else if (postavke.getBrojZona() == 4) {
                            prikaz.ispisi("   Broj neparkiranih u prvoj zoni je: " + neparkirani1 + " \n"
                                    + "   Broj neparkiranih u drugoj zoni je: " + neparkirani2 + " \n"
                                    + "   Broj neparkiranih u trecoj zoni je: " + neparkirani3 + " \n"
                                    + "   Broj neparkiranih u cetvrtoj zoni je: " + neparkirani4 + " \n");
                        }
                        prikaz.ispisi("|******************************************| \n");
                        break;
                    case 6:
                        /* ispis broja automobila koje je pauk odveo na deponij po zonama */
                        int deponij1 = 0;
                        int deponij2 = 0;
                        int deponij3 = 0;
                        int deponij4 = 0;
                        for (int i = 0; i < dretvaKontrole.getDeponij().size(); i++) {
                            if (dretvaKontrole.getDeponij().get(i).getZona() == 1) {
                                deponij1++;
                            } else if (dretvaKontrole.getDeponij().get(i).getZona() == 2) {
                                deponij2++;
                            } else if (dretvaKontrole.getDeponij().get(i).getZona() == 3) {
                                deponij3++;
                            } else if (dretvaKontrole.getDeponij().get(i).getZona() == 4) {
                                deponij4++;
                            }
                        }

                        prikaz.ispisi("|****************************************************| \n");
                        if (postavke.getBrojZona() == 1) {
                            prikaz.ispisi("   Broj automobila na deponiju iz prve zone je: " + deponij1 + " \n");
                        } else if (postavke.getBrojZona() == 2) {
                            prikaz.ispisi("   Broj automobila na deponiju iz prve zone je: " + deponij1 + " \n"
                                    + "   Broj automobila na deponiju iz druge zone je: " + deponij2 + " \n");
                        } else if (postavke.getBrojZona() == 3) {
                            prikaz.ispisi("   Broj automobila na deponiju iz prve zone je: " + deponij1 + " \n"
                                    + "   Broj automobila na deponiju iz druge zone je: " + deponij2 + " \n"
                                    + "   Broj automobila na deponiju iz trece zone je: " + deponij3 + " \n");
                        } else if (postavke.getBrojZona() == 4) {
                            prikaz.ispisi("   Broj automobila na deponiju iz prve zone je: " + deponij1 + " \n"
                                    + "   Broj automobila na deponiju iz druge zone je: " + deponij2 + " \n"
                                    + "   Broj automobila na deponiju iz trece zone je: " + deponij3 + " \n"
                                    + "   Broj automobila na deponiju iz cetvrte zone je: " + deponij4 + " \n");
                        }
                        prikaz.ispisi("|****************************************************| \n");

                        break;
                    case 7:
                        /* ispis 5 automobila s najviše parkirana */
                        List<Automobil> listaSvihAuta = new ArrayList<>();
                        listaSvihAuta.addAll(sviAuti);
                        listaSvihAuta.addAll(zona1);
                        listaSvihAuta.addAll(zona2);
                        listaSvihAuta.addAll(zona3);
                        listaSvihAuta.addAll(zona4);
                        listaSvihAuta.addAll(dretvaKontrole.getDeponij());

                        /* sortiranje liste prema broju parkiranja DESC */
                        Collections.sort(listaSvihAuta, new Comparator<Automobil>() {

                            public int compare(Automobil a1, Automobil a2) {
                                if (a1.getBrojParkiranja() > a2.getBrojParkiranja()) {
                                    return -1;
                                } else if (a1.getBrojParkiranja() < a2.getBrojParkiranja()) {
                                    return 1;
                                }

                                return 0;
                            }

                        });

                        prikaz.ispisi("|*****************************************| \n");
                        for (int i = 0; i < 5; i++) {
                            prikaz.ispisi("    " + listaSvihAuta.get(i) + "\n");
                        }
                        prikaz.ispisi("|*****************************************| \n");
                        break;
                    case 8:
                        /* stanje parkirnih mjesta po zonama (% zauzetih) */
                        float postotak1 = 0;
                        float postotak2 = 0;
                        float postotak3 = 0;
                        float postotak4 = 0;

                        if (postavke.getBrojZona() == 1) {
                            for (int i = 0; i < dretvaDolaska.getZona1().size(); i++) {
                                postotak1 = (((float) dretvaDolaska.getZona1().size() / (float) dretvaDolaska.getKapacitetZone1()) * 100);
                            }
                        }

                        if (postavke.getBrojZona() == 2) {
                            for (int i = 0; i < dretvaDolaska.getZona2().size(); i++) {
                                postotak1 = (((float) dretvaDolaska.getZona1().size() / (float) dretvaDolaska.getKapacitetZone1()) * 100);
                                postotak2 = (((float) dretvaDolaska.getZona2().size() / (float) dretvaDolaska.getKapacitetZone2()) * 100);
                            }
                        }
                        if (postavke.getBrojZona() == 3) {
                            for (int i = 0; i < dretvaDolaska.getZona3().size(); i++) {
                                postotak1 = (((float) dretvaDolaska.getZona1().size() / (float) dretvaDolaska.getKapacitetZone1()) * 100);
                                postotak2 = (((float) dretvaDolaska.getZona2().size() / (float) dretvaDolaska.getKapacitetZone2()) * 100);
                                postotak3 = (((float) dretvaDolaska.getZona3().size() / (float) dretvaDolaska.getKapacitetZone3()) * 100);
                            }
                        }
                        if (postavke.getBrojZona() == 4) {
                            for (int i = 0; i < dretvaDolaska.getZona4().size(); i++) {
                                postotak1 = (((float) dretvaDolaska.getZona1().size() / (float) dretvaDolaska.getKapacitetZone1()) * 100);
                                postotak2 = (((float) dretvaDolaska.getZona2().size() / (float) dretvaDolaska.getKapacitetZone2()) * 100);
                                postotak3 = (((float) dretvaDolaska.getZona3().size() / (float) dretvaDolaska.getKapacitetZone3()) * 100);
                                postotak4 = (((float) dretvaDolaska.getZona4().size() / (float) dretvaDolaska.getKapacitetZone4()) * 100);
                            }
                        }

                        prikaz.ispisi("|*********************************| \n");
                        if (postavke.getBrojZona() == 1) {
                            prikaz.ispisi("   Zona 1 - zauzeto: " + postotak1 + "% \n");
                        } else if (postavke.getBrojZona() == 2) {
                            prikaz.ispisi("   Zona 1 - zauzeto: " + postotak1 + "% \n"
                                    + "   Zona 2 - zauzeto: " + postotak2 + "% \n");
                        } else if (postavke.getBrojZona() == 3) {
                            prikaz.ispisi("   Zona 1 - zauzeto: " + postotak1 + "% \n"
                                    + "   Zona 2 - zauzeto: " + postotak2 + "% \n"
                                    + "   Zona 3 - zauzeto: " + postotak3 + "% \n");
                        } else if (postavke.getBrojZona() == 4) {
                            prikaz.ispisi("   Zona 1 - zauzeto: " + postotak1 + "% \n"
                                    + "   Zona 2 - zauzeto: " + postotak2 + "% \n"
                                    + "   Zona 3 - zauzeto: " + postotak3 + "% \n"
                                    + "   Zona 4 - zauzeto: " + postotak4 + "% \n");
                        }
                        prikaz.ispisi("|*********************************| \n");

                        break;

                }
            }
        } while (!"Q".equals(unos));

        dretvaDolaska.interrupt();
        dretvaOdlaska.interrupt();
        dretvaKontrole.interrupt();
    }
}
