package main;

import database.DataBaseOperations;
import logger.LoggerHandler;
import menu.MenuManager;

public class Main {
	public static void main(String[] args){	   
    	if(DataBaseOperations.openDBConection()) {
    		LoggerHandler.initLogger();
        	MenuManager.run();
        	DataBaseOperations.closeDBConection();
    	}else {
    		System.out.println("Невдалося підключитися до Баз Даних");
    	}
    }
}
