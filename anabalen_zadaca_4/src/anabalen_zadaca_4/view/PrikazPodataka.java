package anabalen_zadaca_4.view;

/**
 *
 * @author Ana-Marija
 */
public class PrikazPodataka {

    private String izbornik
            = "\t|----------------------------------------------------------------------------|\n"
            + "\t|                              UZDIZ - ZADACA 4                              |\n"
            + "\t|----------------------------------------------------------------------------|\n"
            + "\t| 1   - zatvaranje parkirališta za nove ulaze automobila                     |\n"
            + "\t| 2   - otvaranje parkirališta za nove ulaze automobila                      |\n"
            + "\t| 3   - ispis zarada od parkiranja po zonama                                 |\n"
            + "\t| 4   - ispis zarada od kazni po zonama                                      |\n"
            + "\t| 5   - ispis broja automobila koji nisu mogli parkirati po zonama           |\n"
            + "\t| 6   - ispis broja automobila koje je pauk odveo na deponij po zonama       |\n"
            + "\t| 7   - ispis 5 automobila s najviše parkiranja                              |\n"
            + "\t| 8   - stanje parkirnih mjesta po zonama (% zauzetih)                       |\n"
            + "\t| Q   - prekid rada                                                          |\n"
            + "\t|----------------------------------------------------------------------------|";

    public void ispisi(String sadrzaj) {
        System.out.print(sadrzaj);
    }

    public static void ispisGreske(String greska) {
        System.out.println("GRESKA: " + greska);
    }

    public void ispisiIzbornik() {
        System.out.println(izbornik);
    }
}
