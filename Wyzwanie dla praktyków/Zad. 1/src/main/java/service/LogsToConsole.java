package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import model.LoggerModel;
import model.MessageLevel;

public class LogsToConsole implements Logger {

    private String loggerName;

    private static PriorityBlockingQueue<LoggerModel> logsQueue = new PriorityBlockingQueue<LoggerModel>();

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();


    public LogsToConsole(String loggerName) {
        this.loggerName = loggerName;
    }


    public void debug(String message, MessageLevel level) {
        LoggerModel loggerModel = new LoggerModel(this.loggerName, level, message);
        logsQueue.add(loggerModel);
    }

    static {
        executorService.submit(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        LoggerModel nextItem = logsQueue.take();
                        System.out.print(nextItem.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
}
