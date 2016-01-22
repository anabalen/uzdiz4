package anabalen_zadaca_4.state;

import anabalen_zadaca_4.model.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public class ZatvoriParkiraliste implements Stanje {

    @Override
    public Parkiraliste doAction(Parkiraliste parkiraliste) {
        parkiraliste.setStatus(false);
        return parkiraliste;
    }

}
