package anabalen_zadaca_4.state;

import anabalen_zadaca_4.parkiraliste.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public class ZatvoriParkiraliste implements State{
    
    @Override
    public Parkiraliste doAction(Parkiraliste parkiraliste) {
        parkiraliste.setStatus(false);
        return parkiraliste;
    }
    
}
