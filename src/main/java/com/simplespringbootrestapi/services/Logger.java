package com.simplespringbootrestapi.services;

public class Logger {
	
	private static Logger logger;
	
	private Logger() {
		if (logger != null) {
			throw new RuntimeException("Logger instance already initiated.");
		}
	}
	
	public static Logger getInstance() {
		if (logger == null) {
			synchronized (Logger.class) {
				if (logger == null) {
					logger = new Logger();
				}
			}
		}
		return logger;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	protected Object readResolve() {
		return getInstance();
	}
	
	// Should push logs to a log server for better performance, operation, and monitoring
	public void log(String message) {
		System.out.println(message);
	}
}
