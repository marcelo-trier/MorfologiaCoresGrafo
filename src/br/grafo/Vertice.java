package br.grafo;

public class Vertice<T> {
	int id = 0;
	String label = "";
	T umDado;
	public int numLigacoes = 0;
	
	public Vertice( int i, String l ) {
		id = i;
		label = l;
	}
	
	public void setData( T dado ) {
		umDado = dado;
	}
	
	public T getData() {
		return umDado;
	}
	
	public String toString() {
		String msg;
		msg = label;
		return msg;
	}
}
