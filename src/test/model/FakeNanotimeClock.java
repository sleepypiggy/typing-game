package model;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class FakeNanotimeClock implements NanotimeClock {
    private long time;

    public void setTime(long time) {
        this.time = time;
    }

    public long nanotimeClock() {
        return time;
    }
}
