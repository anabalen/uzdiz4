package anabalen_zadaca_4.helper;

import anabalen_zadaca_4.automobili.Automobil;
import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.parkiraliste.Parkiraliste;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana-Marija
 */
public class AutomobilHelper {

    private PostavkeAplikacije postavke;
    private Parkiraliste parkiraliste;

    int brojac;
    int zona;
    int iznos;
    float ulaz;
    int vrijemeParkiranja;
    int produljenje = 0;
    boolean vazecaKarta = false;

    Generator generator = new Generator();
    List<Automobil> sviAuti = new ArrayList<>();

    
    

    public AutomobilHelper() {
    }

    public List<Automobil> kreirajAutomobil(PostavkeAplikacije postavke, Parkiraliste parkiraliste) {
        this.postavke = postavke;
        this.parkiraliste = parkiraliste;
        int brojAutomobila = postavke.getBrojAutomobila();
        Automobil automobil = null;

        /* kreira se broj automobila */
        for (brojac = 1; brojac <= brojAutomobila; brojac++) {

            float generiranaVrijednost1 = generator.generirajVrijednost1();
            float generiranaVrijednost2 = generator.generirajVrijednost2();
            float generiranaVrijednost3 = generator.generirajVrijednost3();
            //float generiranaVrijednostZaOdabir = generator.generirajOdabir();

            ulaz = (float) ((postavke.getVremenskaJedinica() / postavke.getIntervalDolaska()) * generiranaVrijednost1) * 1000;
            zona = (int) (postavke.getBrojZona() * generiranaVrijednost2) + 1;
            iznos = (postavke.getBrojZona() + 1 - zona) * postavke.getCijenaJedinice();
            vrijemeParkiranja = zona * postavke.getMaksParkiranje() * postavke.getVremenskaJedinica();

            /* kreiranje automobila */
            automobil = new Automobil(brojac, ulaz, zona, true, iznos, vrijemeParkiranja, generiranaVrijednost1, generiranaVrijednost2, generiranaVrijednost3, produljenje, vazecaKarta);
            System.out.println(automobil.getRedniBroj() + " " + automobil.getUlaz() + " Zona: " + automobil.getZona() + " " + automobil.getIznos());

            sviAuti.add(automobil);
        }
        

        return sviAuti;
    }

}
