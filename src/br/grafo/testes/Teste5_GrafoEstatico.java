package br.grafo.testes;

import java.io.File;

import br.grafo.Grafo;
import br.grafo.GrafoEstatico;
import br.grafo.mst.MST;
import br.morfcorgraf.ElementoEstruturante;
import br.morfcorgraf.Morfologia;
import br.pereira.util.GerenteArquivos;

public class Teste5_GrafoEstatico {
	
	static int arrImg[][][] = {
			{
				{ 20, 50, 30 },
				{ 30, 60, 10 },
				{ 40, 70, 100 },
			},
			{
				{ 120, 50, 230 },
				{ 220, 150, 30 },
				{ 20, 240, 130 },
			},
			{
				{ 10, 40, 50 },
				{ 55, 58, 52 },
				{ 70, 100, 230 },
			},
	};

	public static void main( String[] args ) throws Exception {
		
		int[][] EE = new int[ ElementoEstruturante.offset.length ][ 3 ]; // rgb
		GrafoEstatico.setData( EE ); // aqui faz a ligacao dos vertices com o ElemEstrut

		EE = Morfologia.getElemEstrut( arrImg, 1, 1, EE );
		
		Grafo g = GrafoEstatico.newGrafo();

		System.out.println( g );

		MST mst = new MST( g );

		do {
			mst.runKruskal();
			System.out.println( mst );
		} while( mst.removeTrunks() > 2 );

		System.out.println( mst );
		System.out.println( g );
	}
}
