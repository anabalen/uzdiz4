package anabalen_zadaca_4.helper;

import anabalen_zadaca_4.MainClass;
import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.controller.KontrolerAplikacije;
import anabalen_zadaca_4.controller.ValidatorPostavki;
import anabalen_zadaca_4.model.PostavkeAplikacije;

/**
 *
 * @author Ana-Marija
 */
public class AutomobilHelper {

    private PostavkeAplikacije postavke;

    int brojac;
    int zona;
    int iznos;
    float ulaz;

    Generator generator = new Generator();

    public AutomobilHelper() {
    }

    public Automobil kreirajAutomobil(PostavkeAplikacije postavke) {
        this.postavke = postavke;
        int brojAutomobila = postavke.getBrojAutomobila();
        Automobil automobil = null;

        /* kreira se broj automobila */
        
        for (brojac = 1; brojac <= brojAutomobila; brojac++) {
             
            float generiranaVrijednost1 = generator.generirajVrijednost1();
            float generiranaVrijednost2 = generator.generirajVrijednost2();
            float generiranaVrijednost3 = generator.generirajVrijednost3();
            
            ulaz = (float)(postavke.getVremenskaJedinica()/postavke.getIntervalDolaska())*generiranaVrijednost1;
            zona = (int) (postavke.getBrojZona() * generiranaVrijednost2) + 1;
            iznos = (postavke.getBrojZona() + 1 - zona) * postavke.getCijenaJedinice();

            automobil = new Automobil(brojac, ulaz, zona, true, iznos, generiranaVrijednost1, generiranaVrijednost2, generiranaVrijednost3);
            System.out.println(automobil.getRedniBroj() + " " + automobil.getUlaz() + " Zona: "  + automobil.getZona() + " "  + automobil.getIznos());
        }
        
        return automobil;
    }

}
