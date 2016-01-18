package anabalen_zadaca_4.state;

import anabalen_zadaca_4.parkiraliste.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public interface State {
     public Parkiraliste doAction(Parkiraliste parkiraliste);
}
