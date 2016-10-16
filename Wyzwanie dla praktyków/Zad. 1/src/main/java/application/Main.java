package application;

import java.io.File;
import java.io.FileNotFoundException;

import model.MessageLevel;
import service.Logger;
import service.LogsToConsole;
import service.LogsToFile;

public class Main {

    static Logger log_1;

    static Logger log_2;

    static Logger log_3;


    public static void main(String[] args) throws FileNotFoundException {
        init();

        log_1.debug("Logger_1 message to show.", MessageLevel.ERROR);
        log_2.debug("Logger_2 message to show in console.", MessageLevel.INFO);
        log_1.debug("Another Logger_1 message.", MessageLevel.WARN);
        log_3.debug("Message to file version 2 impl.", MessageLevel.ERROR);
        log_3.debug("Working.", MessageLevel.ERROR);
        log_3.debug("Logger_3 test.", MessageLevel.ERROR);
    }


    static void init() throws FileNotFoundException {
        log_1 = new LogsToConsole("Logger_1");
        log_2 = new LogsToConsole("Logger_2");
        log_3 = new LogsToFile("Logger to file", new File("logger-file-2.txt"));
    }
}
