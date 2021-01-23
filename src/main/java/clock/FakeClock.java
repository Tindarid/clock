package clock;

import java.time.Instant;

public class FakeClock implements Clock {
    private Instant now;

    public FakeClock(Instant time) {
        set(time);
    }

    public void set(Instant time) {
        now = time;
    }

    public Instant now() {
        return now;
    }
}
