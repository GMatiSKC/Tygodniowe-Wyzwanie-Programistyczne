package model;

import java.sql.Time;

public class LoggerModel {

    private String name;

    private String message;

    private MessageLevel level;

    private Time time;


    public LoggerModel(String name) {
        this.name = name;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public MessageLevel getLevel() {
        return level;
    }


    public void setLevel(MessageLevel level) {
        this.level = level;
    }


    public Time getTime() {
        return time;
    }


    public void setTime(Time time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return time + " >> " + name + "\t[" + level + "]\t" + message;
    }
}
