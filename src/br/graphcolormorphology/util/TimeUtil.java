package br.graphcolormorphology.util;

import javax.swing.JOptionPane;

public class TimeUtil {
	static long initTime = 0;
	
	public static void mark() {
		initTime = System.currentTimeMillis();
	}

	public static float getTime( boolean mostrar ) {
		long fim = System.currentTimeMillis();
		float tempo = fim - initTime;
		tempo = ( float )tempo / 1000f;
		if( mostrar ) {
			String msg = String.format("Tempo demora: %.02f seg", tempo );
			JOptionPane.showMessageDialog( null, msg );
		}
		return tempo;
	}
	
}
