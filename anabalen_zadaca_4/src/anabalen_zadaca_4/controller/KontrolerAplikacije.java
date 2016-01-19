package anabalen_zadaca_4.controller;

import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.helper.AutomobilHelper;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.parkiraliste.Parkiraliste;
import anabalen_zadaca_4.state.Context;
import anabalen_zadaca_4.state.OtvoriParkiraliste;
import anabalen_zadaca_4.state.State;
import anabalen_zadaca_4.state.ZatvoriParkiraliste;
import anabalen_zadaca_4.thread.DretvaDolaska;
import anabalen_zadaca_4.thread.DretvaOdlaska;
import anabalen_zadaca_4.view.PrikazPodataka;
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

    private boolean aktivnaDretva;
    private boolean aktivnaDrugaDretva;
    private DretvaDolaska dretvaDolaska;
    private DretvaOdlaska dretvaOdlaska;

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
        if (!aktivnaDretva) {
            dretvaDolaska = new DretvaDolaska(postavke.getIntervalDolaska(), parkiraliste, postavke, sviAuti);
            //postavljanje broja zona
            dretvaDolaska.postavljanjeZona();
            dretvaDolaska.start();
            aktivnaDretva = true;
        } else {
            System.out.println("Dretva dolaska je vec pokrenuta.");
        }
        
        /* pokreni dretvu odlaska */
       /*
               if (!aktivnaDrugaDretva) {
            dretvaOdlaska = new DretvaOdlaska();
            
           
            dretvaOdlaska.start();
            aktivnaDrugaDretva = true;
        } else {
            System.out.println("Dretva odlaska je vec pokrenuta.");
        }
        */
        

        Context context = new Context();
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
                            prikaz.ispisi("Parkiralište je već zatvoreno. \n");
                        } else {
                            State zatvori = new ZatvoriParkiraliste();
                            context.setState(zatvori);
                            noviStatus = context.doAction(parkiraliste);
                            System.out.println("Parkiraliste je sada zatvoreno. \n");
                        }

                        break;
                    case 2:
                        /* otvaranje parkirališta za nove ulaze automobila */
                        if (parkiraliste.isStatus()) {
                            prikaz.ispisi("Parkiralište je već otvoreno. \n");
                        } else {
                            State otvori = new OtvoriParkiraliste();
                            context.setState(otvori);
                            noviStatus = context.doAction(parkiraliste);
                            System.out.println("Parkiralište je sada otvoreno. \n");
                        }

                        break;
                    case 3:
                        /* ispis zarada od parkiranja po zonama */

                        System.out.println("Iznos u zoni 1: " + dretvaDolaska.getIznosZona1() + " "
                                + "Iznos u zoni 2: " + dretvaDolaska.getIznosZona2() + " "
                                + "Iznos u zoni 3: " + dretvaDolaska.getIznosZona3() + " "
                                + "Iznos u zoni 4: " + dretvaDolaska.getIznosZona4());

                        break;
                    case 4:
                        /* ispis zarada od kazni po zonama */
                        prikaz.ispisi("Izabrana je opcija 4. \n");
                        break;
                    case 5:
                        /* ispis broja automobila koji nisu mogli parkirati po zonama */
                        prikaz.ispisi("Izabrana je opcija 5. \n");
                        break;
                    case 6:
                        /* ispis broja automobila koje je pauk odveo na deponij po zonama */
                        prikaz.ispisi("Izabrana je opcija 6. \n");
                        break;
                    case 7:
                        /* ispis 5 automobila s najviše parkirana */
                        prikaz.ispisi("Izabrana je opcija 7. \n");
                        break;
                    case 8:
                        /* stanje parkirnih mjesta po zonama (% zauzetih) */
                        prikaz.ispisi("Izabrana je opcija 8. \n");
                        break;

                }
            }
        } while (!"Q".equals(unos));

        dretvaDolaska.interrupt();
    }
}
