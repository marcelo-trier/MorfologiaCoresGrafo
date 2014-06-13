package br.grafo_v2;

public class ArestaV2 implements Comparable<ArestaV2> {
	//public VerticeV2 vi[] = { null, null }; // somente um indice para o vetor de vertice
	public VerticeV2 from;
	public VerticeV2 to;
	public float weight; // peso
	
	public String toString() {
		String msg;
		msg = "[" + from + "--" + to + "]";
		return msg;
	}
	
	public ArestaV2( VerticeV2 v1, VerticeV2 v2, float w ) {
		setInfo( v1, v2, w );
	}

	public void setInfo( VerticeV2 src, VerticeV2 dst, float w ) {
//		vi[0] = v1;
//		vi[1] = v2;
		from = src;
		to = dst;
		weight = w;
	}
	
	public int compareTo(ArestaV2 o) {
		if( this.weight < o.weight )
			return -1;

		if( this.weight == o.weight )
			return 0;

		return 1;
	}
}
