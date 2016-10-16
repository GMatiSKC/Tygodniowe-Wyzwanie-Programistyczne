package model;

import java.sql.Time;

public class LoggerModel implements Comparable<LoggerModel> {

    private final String name;

    private final String message;

    private final MessageLevel level;

    private final Time time;


    public LoggerModel(String name, MessageLevel level, String message) {
        this.time = new Time(System.currentTimeMillis());
        this.name = name;
        this.level = level;
        this.message = message;
    }


    @Override
    public String toString() {
        return time + " >> " + name + "\t[" + level + "]\t" + message + "\n";
    }


    public int compareTo(LoggerModel o) {
        return this.time.compareTo(o.time);
    }
}
