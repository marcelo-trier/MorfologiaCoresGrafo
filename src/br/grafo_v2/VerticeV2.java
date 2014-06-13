package br.grafo_v2;

public class VerticeV2<T> {
	int id = 0;
	String label = "";
	T umDado;
	
	public VerticeV2( int i, String l ) {
		id = i;
		label = l;
	}
	
	public void setData( T dado ) {
		umDado = dado;
	}
	
	public String toString() {
		String msg;
		msg = label;
		return msg;
	}
}
