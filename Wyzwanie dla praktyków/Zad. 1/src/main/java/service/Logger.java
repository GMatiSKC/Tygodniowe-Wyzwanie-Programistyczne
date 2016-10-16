package service;

import model.MessageLevel;

public interface Logger {

    void debug(String message, MessageLevel level);
}
