package event;

import java.util.List;

public interface EventStatistic {
    void incEvent(String name);
    Double getEventStatisticByName(String name);
    List<Event> getAllEventStatistic();
    void printStatistic();
}
