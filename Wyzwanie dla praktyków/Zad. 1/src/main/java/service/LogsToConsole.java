package service;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Time;

import model.LoggerModel;
import model.MessageLevel;

public class LogsToConsole implements Logger {

    private LoggerModel loggerModel;


    public LogsToConsole(String loggerName) {
        loggerModel = new LoggerModel(loggerName);
    }


    public void debug(String message, MessageLevel level) {
        Time time = new Time(System.currentTimeMillis());
        loggerModel.setTime(time);
        loggerModel.setLevel(level);
        loggerModel.setMessage(message);

        System.out.println(loggerModel.toString());
    }
}
