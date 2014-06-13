package br.pereira.util;

import java.io.File;

public class Log {

	File baseDir = new File(System.getProperty("user.dir"));
	File logFile = new File( baseDir.toString() + "/umLog.log" );

	public void write( String msg ) {
		
	}
	
}
