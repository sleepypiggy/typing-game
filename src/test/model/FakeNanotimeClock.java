package model;

public class FakeNanotimeClock implements NanotimeClock {
    private long time;

    public void setTime(long time) {
        this.time = time;
    }

    public long nanotimeClock() {
        return time;
    }
}
