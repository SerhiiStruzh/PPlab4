package logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import logger.formatter.CustomFormatter;

public class LoggerHandler {
	private static Logger logger = null;
	private static final String LOG_FILE_PATH = "log.txt";
	private static CustomFormatter formatter = new CustomFormatter();
	
	public static void initLogger() {
		try {
			logger = Logger.getLogger(LoggerHandler.class.getName());
			String currentDirectory = System.getProperty("user.dir") + "\\" + LOG_FILE_PATH;
			FileHandler fileHandler = new FileHandler(currentDirectory, true);
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
		} catch (SecurityException | IOException e) {
			logger = null;
		}
	}
	
	public static void logInfo(String message) {
		if(logger != null) {
			logger.info(message);
		}
    }

    public static void logCritical(String message) {
    	if(logger != null) {
    		logger.severe(message);
		}
    }
}
