package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import model.LoggerModel;
import model.MessageLevel;

public class LogsToFile implements Logger {

    private String loggerName;

    private static File file;

    private static PriorityBlockingQueue<LoggerModel> logsQueue = new PriorityBlockingQueue<LoggerModel>();

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();


    public LogsToFile(String loggerName, File file) {
        this.loggerName = loggerName;
        LogsToFile.file = file;
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
                        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);

                        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

                        synchronized (this) {
                            bufferWriter.write(nextItem.toString());
                        }

                        bufferWriter.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
