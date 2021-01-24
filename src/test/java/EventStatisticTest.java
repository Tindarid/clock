import clock.FakeClock;
import event.EventStatistic;
import event.EventStatisticLastHour;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventStatisticTest {
    private FakeClock clock;
    private EventStatistic stats;
    private static final Double epsilon = 0.000001;

    @Before
    public void setUp() {
        clock = new FakeClock(Instant.now());
        stats = new EventStatisticLastHour(clock);
    }

    @Test
    public void testEmpty() {
        assertTrue(stats.getAllEventStatistic().isEmpty());
    }

    @Test
    public void testSimple() {
        stats.incEvent("lol");
        assertEquals(1.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);
        stats.incEvent("lol");
        assertEquals(2.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);
        stats.incEvent("lol");
        assertEquals(3.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);
    }

    @Test
    public void testSet() {
        clock.set(Instant.ofEpochSecond(1));
        stats.incEvent("lol");
        assertEquals(1.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);

        clock.set(Instant.ofEpochSecond(0));
        assertEquals(0.0, stats.getEventStatisticByName("lol"), epsilon);

        clock.set(Instant.ofEpochSecond(1));
        assertEquals(1.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);

        clock.set(Instant.ofEpochSecond(3600));
        assertEquals(1.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);

        clock.set(Instant.ofEpochSecond(3601));
        assertEquals(1.0 / 60.0, stats.getEventStatisticByName("lol"), epsilon);

        clock.set(Instant.ofEpochSecond(3602));
        assertEquals(0.0, stats.getEventStatisticByName("lol"), epsilon);
    }

    @Test
    public void testPrint() {
        stats.incEvent("lol");
        stats.incEvent("kek");
        stats.printStatistic();
    }
}
