package anabalen_zadaca_4.state;

import anabalen_zadaca_4.model.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public interface Stanje {

    public Parkiraliste doAction(Parkiraliste parkiraliste);
}
