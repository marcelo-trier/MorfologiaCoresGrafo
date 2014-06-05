package br.graph;

public class Aresta implements Comparable<Aresta> {
	public int vi[] = { 0, 0 }; // somente um indice para o elemento estruturante, q eh o vertice
	public float weight; // peso
	
	public Aresta( int i1, int i2, float w ) {
		setInfo( i1, i2, w );
	}

	public void setInfo( int i1, int i2, float w ) {
		vi[0] = i1;
		vi[1] = i2;
		weight = w;
	}
	
	public int compareTo(Aresta o) {
		if( this.weight < o.weight )
			return -1;

		if( this.weight == o.weight )
			return 0;

		return 1;
	}
}
