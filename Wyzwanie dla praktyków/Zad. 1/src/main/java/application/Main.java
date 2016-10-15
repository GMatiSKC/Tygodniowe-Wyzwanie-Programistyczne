package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.MessageLevel;
import service.Logger;
import service.LoggerFactory;

public class Main {
    
    static Logger log_1;
    static Logger log_2;
    static Logger log_3;
    
    public static void main(String[] args) throws FileNotFoundException {
        init();
        
        log_1.debug("Logger_1 message to show.", MessageLevel.ERROR);
        log_2.debug("Logger_2 message to show in console.", MessageLevel.INFO);
        log_1.debug("Another Logger_1 message.", MessageLevel.WARN);
        log_3.debug("Message to file", MessageLevel.ERROR);
    }
    
    static void init() throws FileNotFoundException{
        log_1 = new LoggerFactory("Logger_1");
        log_2 = new LoggerFactory("Logger_2");
        log_3 = new LoggerFactory("Logger to file", new PrintWriter("logger-file.txt"));
    }
}
