package model;

// This class is the actual clock that is passed into as an argument to the Round constructor.
// Basically, it's the one we use for the actual game.
// TEST COVERAGE DOESN'T COVER THIS CLASS BECAUSE IT'S MEANT TO ONLY RETURN THE CURRENT
// SYSTEM TIME, AND A FAKE CLOCK, FakeNanotimeClock IS USED FOR TEST WHICH DOES HAVE 100% COVERAGE.

public class RoundTimer implements NanotimeClock {
    public long nanotimeClock() {
        return System.nanoTime();
    }
}
