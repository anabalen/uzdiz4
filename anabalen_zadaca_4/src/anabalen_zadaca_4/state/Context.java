package anabalen_zadaca_4.state;

import anabalen_zadaca_4.parkiraliste.Parkiraliste;

/**
 *
 * @author Ana-Marija
 */
public class Context implements State {

    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

   
    @Override
    public Parkiraliste doAction(Parkiraliste parkiraliste) {
        state.doAction(parkiraliste);
        return parkiraliste;
    }
    
}