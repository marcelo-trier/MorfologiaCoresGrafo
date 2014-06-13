package br.graph.testes;

import java.io.File;
import br.graph.Grafo;
import br.graphcolormorphology.util.GerenteArquivos;
import br.graphcolormorphology.util.GraphUtils;

public class TesteGrafoRead {

	public static void main(String[] args) throws Exception {
		GerenteArquivos ga = GerenteArquivos.getInstance();
		File f = ga.getOpenFile();
		Grafo<Integer> g = GraphUtils.readGraphAdjacency( f );
		GraphUtils.writeGMLFile( g );
	}

}
