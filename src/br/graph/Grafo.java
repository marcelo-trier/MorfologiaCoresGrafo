package br.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Grafo<T> {
	T[] vertices;
	ArrayList<Aresta> arestas;
	int numeroLigacoes[];
	
	public T[] getVertices() {
		return vertices;
	}
	
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public Grafo( ArrayList<Aresta> la, T[] v ) {
		arestas = (ArrayList<Aresta>) la.clone();
		vertices = v.clone();
		numeroLigacoes = new int[ vertices.length ];
	}

	public Grafo() {
		arestas = new ArrayList<Aresta>();
	}
	
	public void addVertices( T[] v ) {
		vertices = v;
		numeroLigacoes = new int[ vertices.length ];
	}
	
	public void addAresta( Aresta a ) {
		arestas.add( a );
	}

	
	/*	
	CollectionUtils collUt;
	Vertice findVertice( char c ) {
		CollectionUtils
	}
	
	Customer findCustomerByid(final int id){
	    return (Customer) CollectionUtils.find(customers, new Predicate() {
	        public boolean evaluate(Object arg0) {
	            return ((Customer) arg0).getId()==id;
	        }
	    });
	}	*/
	
}
