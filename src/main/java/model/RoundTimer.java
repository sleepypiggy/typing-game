package model;

// This class is the actual clock that is passed into as an argument to the Round constructor.
// Basically, it's the one we use for the actual game.

public class RoundTimer implements NanotimeClock {

    // EFFECTS: returns the current time of the system.
    public long nanotimeClock() {
        return System.nanoTime();
    }
}
