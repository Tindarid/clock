import clock.FakeClock;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertNotEquals;

public class ClockTest {
    @Test
    public void testFakeClock() {
        FakeClock c = new FakeClock(Instant.ofEpochSecond(0));
        Instant before = c.now();
        c.set(Instant.ofEpochSecond(1));
        Instant after = c.now();
        assertNotEquals(before, after);
    }
}
