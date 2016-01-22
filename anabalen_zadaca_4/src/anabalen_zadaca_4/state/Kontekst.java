package anabalen_zadaca_4.state;

import anabalen_zadaca_4.model.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public class Kontekst implements Stanje {

    private Stanje state;

    public void setState(Stanje state) {
        this.state = state;
    }

    public Stanje getState() {
        return state;
    }

    @Override
    public Parkiraliste doAction(Parkiraliste parkiraliste) {
        state.doAction(parkiraliste);
        return parkiraliste;
    }

}
