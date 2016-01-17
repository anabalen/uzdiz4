package anabalen_zadaca_4.controller;

import anabalen_zadaca_4.helper.AutomobilHelper;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.view.PrikazPodataka;
import java.util.Scanner;

/**
 *
 * @author Ana-Marija
 */
public class KontrolerAplikacije {

    private PostavkeAplikacije postavke;
    private PrikazPodataka prikaz;

    public KontrolerAplikacije() {
    }

    public void pocetniZaslon(String[] argumenti) {
        ValidatorPostavki validator = new ValidatorPostavki();
        postavke = validator.ucitajPostavke(argumenti);
        
        prikaz = new PrikazPodataka();
        prikaz.ispisiIzbornik();
        
        /* inicijalizacija automobila */
        AutomobilHelper aHelper = new AutomobilHelper();
        aHelper.kreirajAutomobil(postavke);

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
                        prikaz.ispisi("" + postavke.getBrojAutomobila());
                        prikaz.ispisi("Izabrana je opcija 1. \n");
                        break;
                    case 2:
                        /* otvaranje parkirališta za nove ulaze automobila */
                        prikaz.ispisi("Izabrana je opcija 2. \n");
                        break;
                    case 3:
                        /* ispis zarada od parkiranja po zonama */
                        prikaz.ispisi("Izabrana je opcija 3. \n");
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
        
        
}
}
