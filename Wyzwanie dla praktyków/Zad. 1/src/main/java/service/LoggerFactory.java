package service;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Time;

import model.LoggerModel;
import model.MessageLevel;


public class LoggerFactory implements Logger {
    private LoggerModel loggerModel;
    private LoggerOutput loggerOutput;
    private PrintWriter writer;
    
    public LoggerFactory(String loggerName) {
        loggerModel = new LoggerModel(loggerName);
        loggerOutput = LoggerOutput.CONSOLE;
    }
    
    public LoggerFactory(String loggerName, PrintWriter  writer){
        loggerModel = new LoggerModel(loggerName);
        loggerOutput = LoggerOutput.FILE;
        this.writer = writer;
    }

    public void debug(String message, MessageLevel level) { 
        Time time = new Time(System.currentTimeMillis());
        loggerModel.setTime(time);
        loggerModel.setLevel(level);
        loggerModel.setMessage(message);
        
        switch (loggerOutput){
            case CONSOLE:
                System.out.println(loggerModel.toString());                
                break;
            case FILE:
                writer.println(loggerModel.toString());
                writer.close();
                break;
        }
    }
    
    private enum LoggerOutput{
        CONSOLE, FILE
    }

}
