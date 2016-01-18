package anabalen_zadaca_4.state;

import anabalen_zadaca_4.parkiraliste.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public class OtvoriParkiraliste implements State{
    
    @Override
    public Parkiraliste doAction(Parkiraliste parkiraliste) {
        parkiraliste.setStatus(true);
        return parkiraliste;
    }
    
}
