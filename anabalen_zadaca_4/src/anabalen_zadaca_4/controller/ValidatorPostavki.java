package anabalen_zadaca_4.controller;

import anabalen_zadaca_4.model.PostavkeAplikacije;
import anabalen_zadaca_4.view.PrikazPodataka;
import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ana-Marija
 */
public class ValidatorPostavki {

    PostavkeAplikacije postavke;

    /**
     * Validacija ulaznih parametara aplikacije
     *
     */
    public ValidatorPostavki() {
    }

    public PostavkeAplikacije ucitajPostavke(String[] args) {
        if (!provjeriPostavke(args)) {
            System.exit(0);
        }
        return postavke;
    }

    private boolean provjeriPostavke(String[] args) {
        String sintaksa = "(([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+))";
        StringBuilder sb = new StringBuilder();

        for (String arg : args) {
            sb.append(arg).append(" ");
        }

        String p = sb.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(p);
        boolean status = m.matches();

        postavke = new PostavkeAplikacije();
        if (status) {
            if (m.group(2) != null && (Integer.parseInt(m.group(2)) >= 10) && (Integer.parseInt(m.group(2)) <= 100)) {
                //brojAutomobila
                postavke.setBrojAutomobila(Integer.parseInt(m.group(2)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan broj automobila.");
                return false;
            }
            if (m.group(3) != null && Integer.parseInt(m.group(3)) >= 1 && Integer.parseInt(m.group(3)) <= 4) {
                //brojZona
                postavke.setBrojZona(Integer.parseInt(m.group(3)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan broj zona.");
                return false;
            }

            if (m.group(4) != null && (Integer.parseInt(m.group(4)) >= 1) && (Integer.parseInt(m.group(4)) <= 100)) {
                //kapacitetZone
                postavke.setKapacitetZone(Integer.parseInt(m.group(4)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan kapacitet zone.");
                return false;
            }

            if (m.group(5) != null && (Integer.parseInt(m.group(5)) >= 1) && (Integer.parseInt(m.group(5)) <= 10)) {
                //maksParkiranje
                postavke.setMaksParkiranje(Integer.parseInt(m.group(5)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan broj maksimalnog parkiranja.");
                return false;
            }

            if (m.group(6) != null && (Integer.parseInt(m.group(6)) >= 1) && (Integer.parseInt(m.group(6)) <= 10)) {
                //vremenskaJedinica
                postavke.setVremenskaJedinica(Integer.parseInt(m.group(6)));
            } else {
                PrikazPodataka.ispisGreske("Neispravna vremenska jedinica.");
                return false;
            }

            if (m.group(7) != null && (Integer.parseInt(m.group(7)) >= 1) && (Integer.parseInt(m.group(7)) <= 10)) {
                //intervalDolaska
                postavke.setIntervalDolaska(Integer.parseInt(m.group(7)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan interval dolaska.");
                return false;
            }

            if (m.group(8) != null && (Integer.parseInt(m.group(8)) >= 1) && (Integer.parseInt(m.group(8)) <= 10)) {
                //intervalOdlaska
                postavke.setIntervalOdlaska(Integer.parseInt(m.group(8)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan interval odlaska.");
                return false;
            }

            if (m.group(9) != null && (Integer.parseInt(m.group(9)) >= 1) && (Integer.parseInt(m.group(9)) <= 10)) {
                //cijenaJedinice
                postavke.setCijenaJedinice(Integer.parseInt(m.group(9)));
            } else {
                PrikazPodataka.ispisGreske("Neispravna cijena jedinice.");
                return false;
            }

            if (m.group(10) != null && (Integer.parseInt(m.group(10)) >= 1) && (Integer.parseInt(m.group(10)) <= 10)) {
                //intervalKontrole
                postavke.setIntervalKontrole(Integer.parseInt(m.group(10)));
            } else {
                PrikazPodataka.ispisGreske("Neispravan interval kontrole.");
                return false;
            }

            if (m.group(11) != null && (Integer.parseInt(m.group(11)) >= 10) && (Integer.parseInt(m.group(11)) <= 100)) {
                //kaznaParkiranja
                postavke.setKaznaParkiranja(Integer.parseInt(m.group(11)));
            } else {
                PrikazPodataka.ispisGreske("Neispravna kazna parkiranja.");
                return false;
            }

        } else {
            PrikazPodataka.ispisGreske("Neispravan unos argumenata, unesite npr. u obliku : \n "
                    + "12 3 45 3 3 4 4 5 6 7 85");
            return false;
        }
        return true;
    }
}
