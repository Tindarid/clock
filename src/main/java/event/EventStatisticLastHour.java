package event;

import clock.Clock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStatisticLastHour implements EventStatistic {
    private final Map<String, List<Instant>> events = new HashMap<>();
    private final Clock clock;
    private final static long MINUTES_IN_HOUR = 60;
    private final static long SECONDS_IN_HOUR = 60 * MINUTES_IN_HOUR;

    public EventStatisticLastHour(Clock clock) {
        this.clock = clock;
    }

    public void incEvent(String name) {
        events.computeIfAbsent(name, k -> new ArrayList<>()).add(clock.now());
    }

    private double getRPM(Instant now, List<Instant> times) {
        long r = now.getEpochSecond();
        long l = r - SECONDS_IN_HOUR;
        long total = 0;
        for (Instant time : times) {
            long t = time.getEpochSecond();
            if (l <= t && t <= r) {
                total++;
            }
        }
        return (double) total / MINUTES_IN_HOUR;
    }

    public Double getEventStatisticByName(String name) {
        List<Instant> recorded = events.get(name);
        return recorded == null ? 0.0 : getRPM(clock.now(), recorded);
    }

    public List<Event> getAllEventStatistic() {
        Instant now = clock.now();
        List<Event> result = new ArrayList<>();
        for (Map.Entry<String, List<Instant>> record : events.entrySet()) {
            result.add(new Event(record.getKey(), getRPM(now, record.getValue())));
        }
        return result;
    }

    public void printStatistic() {
        for (Event s : getAllEventStatistic()) {
            System.out.println(s);
        }
    }
}
