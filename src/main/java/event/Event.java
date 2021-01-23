package event;

public class Event {
    private final String name;
    private final Double rpm;

    Event(String name, Double rpm) {
        this.name = name;
        this.rpm = rpm;
    }

    public String name() {
        return name;
    }

    public Double rpm() {
        return rpm;
    }

    public String toString() {
        return "NAME: " + name + ", RPM: " + rpm;
    }
}
