package br.grafo;

public class Aresta implements Comparable<Aresta> {
	//public VerticeV2 vi[] = { null, null }; // somente um indice para o vetor de vertice
	public Vertice from;
	public Vertice to;
	public float weight; // peso
	
	public String toString() {
		String msg;
		msg = "[" + from + "--" + to + "]";
		return msg;
	}
	
	public Aresta( Vertice v1, Vertice v2, float w ) {
		setInfo( v1, v2, w );
	}

	public Aresta setInfo( Vertice src, Vertice dst, float w ) {
//		vi[0] = v1;
//		vi[1] = v2;
		from = src;
		to = dst;
		weight = w;
		return this;
	}
	
	public int compareTo(Aresta o) {
		if( this.weight < o.weight )
			return -1;

		if( this.weight == o.weight )
			return 0;

		return 1;
	}
}
