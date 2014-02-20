package com.mw.sms.modems;

import java.io.PrintStream;

public class Logger{
	private static final boolean DO_PRINTING = true;
	private final String description;

	public Logger(Class<?> clazz){
		this.description = clazz.getSimpleName();
	}

	public void trace(String s){
		out("TRACE", s); 
	}
	
	public void debug(String s){
		out("DEBUG", s); 
	}
	
	public void info(String s){
		out("INFO", s); 
	}
	
	public void info(String message, Throwable t){
		out("INFO", message, t); 
	}

	public void warn(String message, Throwable t){
		out("WARN", message, t); 
	}
	
	private void out(String level, String message){
		out(level, message, null); 
	}
	
	@SuppressWarnings("resource")
	private void out(String level, String message, Throwable t){
		if(!DO_PRINTING){
			return;
		}
		
		PrintStream out = t == null ? System.out : System.err;
		out.println("[" + Thread.currentThread().getName() + " : " + description + "] " + level + ": " + message);
		if(t != null){
			t.printStackTrace();
		}
	}
}