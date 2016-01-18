package anabalen_zadaca_4.parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public class Parkiraliste {
    int brZona;
    boolean status;
    
    public Parkiraliste() {
    }
    
    public Parkiraliste(boolean status, int brZona){
        this.status = status;
        this.brZona = brZona;
    }

    public int getBrZona() {
        return brZona;
    }

    public boolean isStatus() {
        return status;
    }

    public void setBrZona(int brZona) {
        this.brZona = brZona;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
