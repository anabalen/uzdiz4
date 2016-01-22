package anabalen_zadaca_4.model;

/**
 *
 * @author Ana-Marija
 */
public class PostavkeAplikacije {

    private int brojAutomobila;
    private int brojZona;
    private int kapacitetZone;
    private int maksParkiranje;
    private int vremenskaJedinica;
    private int intervalDolaska;
    private int intervalOdlaska;
    private int cijenaJedinice;
    private int intervalKontrole;
    private int kaznaParkiranja;

    /**
     * Spremanje ulaznih parametara aplikacije
     *
     */
    public PostavkeAplikacije() {
    }

    public PostavkeAplikacije(int brojAutomobila, int brojZona, int kapacitetZone, int maksParkiranje, int vremenskaJedinica, int intervalDolaska, int intervalOdlaska, int cijenaJedinice, int intervalKontrole, int kaznaParkiranja) {
        this.brojAutomobila = brojAutomobila;
        this.brojZona = brojZona;
        this.kapacitetZone = kapacitetZone;
        this.maksParkiranje = maksParkiranje;
        this.vremenskaJedinica = vremenskaJedinica;
        this.intervalDolaska = intervalDolaska;
        this.intervalOdlaska = intervalOdlaska;
        this.cijenaJedinice = cijenaJedinice;
        this.intervalKontrole = intervalKontrole;
        this.kaznaParkiranja = kaznaParkiranja;
    }

    public int getBrojAutomobila() {
        return brojAutomobila;
    }

    public int getBrojZona() {
        return brojZona;
    }

    public int getKapacitetZone() {
        return kapacitetZone;
    }

    public int getMaksParkiranje() {
        return maksParkiranje;
    }

    public int getVremenskaJedinica() {
        return vremenskaJedinica;
    }

    public int getIntervalDolaska() {
        return intervalDolaska;
    }

    public int getIntervalOdlaska() {
        return intervalOdlaska;
    }

    public int getCijenaJedinice() {
        return cijenaJedinice;
    }

    public int getIntervalKontrole() {
        return intervalKontrole;
    }

    public int getKaznaParkiranja() {
        return kaznaParkiranja;
    }

    public void setBrojAutomobila(int brojAutomobila) {
        this.brojAutomobila = brojAutomobila;
    }

    public void setBrojZona(int brojZona) {
        this.brojZona = brojZona;
    }

    public void setKapacitetZone(int kapacitetZone) {
        this.kapacitetZone = kapacitetZone;
    }

    public void setMaksParkiranje(int maksParkiranje) {
        this.maksParkiranje = maksParkiranje;
    }

    public void setVremenskaJedinica(int vremenskaJedinica) {
        this.vremenskaJedinica = vremenskaJedinica;
    }

    public void setIntervalDolaska(int intervalDolaska) {
        this.intervalDolaska = intervalDolaska;
    }

    public void setIntervalOdlaska(int intervalOdlaska) {
        this.intervalOdlaska = intervalOdlaska;
    }

    public void setCijenaJedinice(int cijenaJedinice) {
        this.cijenaJedinice = cijenaJedinice;
    }

    public void setIntervalKontrole(int intervalKontrole) {
        this.intervalKontrole = intervalKontrole;
    }

    public void setKaznaParkiranja(int kaznaParkiranja) {
        this.kaznaParkiranja = kaznaParkiranja;
    }

}
