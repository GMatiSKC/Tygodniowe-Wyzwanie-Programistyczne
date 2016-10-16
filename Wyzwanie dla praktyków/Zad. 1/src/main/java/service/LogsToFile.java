package service;

import java.io.PrintWriter;
import java.sql.Time;

import model.LoggerModel;
import model.MessageLevel;

public class LogsToFile implements Logger {

    private LoggerModel loggerModel;

    private PrintWriter writer;


    public LogsToFile(String loggerName, PrintWriter writer) {
        loggerModel = new LoggerModel(loggerName);
        this.writer = writer;
    }


    public void debug(String message, MessageLevel level) {
        Time time = new Time(System.currentTimeMillis());
        loggerModel.setTime(time);
        loggerModel.setLevel(level);
        loggerModel.setMessage(message);

        writer.println(loggerModel.toString());
        writer.close();
    }

}
