package anabalen_zadaca_4.automobili;

/**
 *
 * @author Ana-Marija
 */
public class Automobil {
    int redniBroj;
    float ulaz;
    int zona;
    boolean status;
    int iznos;
    int vrijemeParkiranja;
    float generiranaVrijednost1;
    float generiranaVrijednost2;
    float generiranaVrijednost3;
    
    
    public Automobil() {
    }
    
    public Automobil(int redniBroj, float ulaz, int zona, boolean status, int iznos, int vrijemeParkiranja, float generiranaVrijednost1, float generiranaVrijednost2, float generiranaVrijednost3){
        this.redniBroj = redniBroj;
        this.ulaz = ulaz;
        this.zona = zona;
        this.status = status;
        this.iznos = iznos;
        this.vrijemeParkiranja = vrijemeParkiranja;
        this.generiranaVrijednost1 = generiranaVrijednost1;
        this.generiranaVrijednost2 = generiranaVrijednost2;
        this.generiranaVrijednost3 = generiranaVrijednost3;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public float getUlaz() {
        return ulaz;
    }

    public int getZona() {
        return zona;
    }

    public boolean isStatus() {
        return status;
    }

    public int getIznos() {
        return iznos;
    }

    public float getGeneriranaVrijednost1() {
        return generiranaVrijednost1;
    }

    public float getGeneriranaVrijednost2() {
        return generiranaVrijednost2;
    }

    public float getGeneriranaVrijednost3() {
        return generiranaVrijednost3;
    }
    
    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public void setUlaz(float ulaz) {
        this.ulaz = ulaz;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setIznos(int iznos) {
        this.iznos = iznos;
    }

    public void setGeneriranaVrijednost1(float generiranaVrijednost1) {
        this.generiranaVrijednost1 = generiranaVrijednost1;
    }

    public void setGeneriranaVrijednost2(float generiranaVrijednost2) {
        this.generiranaVrijednost2 = generiranaVrijednost2;
    }

    public void setGeneriranaVrijednost3(float generiranaVrijednost3) {
        this.generiranaVrijednost3 = generiranaVrijednost3;
    }

    public int getVrijemeParkiranja() {
        return vrijemeParkiranja;
    }

    public void setVrijemeParkiranja(int vrijemeParkiranja) {
        this.vrijemeParkiranja = vrijemeParkiranja;
    }
    
    
    
}
