package br.grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grafo {
	protected List<Vertice> vertices = new ArrayList<Vertice>();
	protected List<Aresta> arestas = new ArrayList<Aresta>();

	public String toString() {
		String msg = "Printing G = { V, E }::\n";
		msg += "V["+ vertices.size() + "] = " + vertices + "\n";
		msg += "E["+ arestas.size() + "] = " + arestas + "\n";
		return msg;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void transpose() {
		Vertice tmp;
		for (Aresta a : arestas) {
			tmp = a.from;
			a.from = a.to;
			a.to = tmp;
		}
	}

	public Grafo() {
	}

	public Grafo(Aresta[] la, Vertice[] lv) {
		this(Arrays.asList(la), Arrays.asList(lv));
	}

	public Grafo(List la, List lv) {
		if (la == null || lv == null)
			return;

		arestas.clear();
		vertices.clear();
		arestas.addAll(la);
		vertices.addAll(lv);
	}

	public Aresta getAresta(Vertice v1, Vertice v2, float w) {
		for (Aresta a : arestas) {
			if ((v1 == a.to || v1 == a.from) && (v2 == a.to || v2 == a.from)
					&& a.weight == w ) {
				return a;
			}
		}
		return null;
	}

	public void addAresta(Vertice v1, Vertice v2, float w) {
		if (getAresta(v1, v2, w) == null) {
			arestas.add(new Aresta(v1, v2, w));
		}
	}

	public void criaVertices(int len) {
		// vertices = new VerticeV2[len];
		vertices.clear();
		char tmp = 'a';
		String label;
		for (int i = 0; i < len; i++) {
			label = "" + tmp++;
			vertices.add(new Vertice(i, label));
		}
	}

	public void loadFromFile(File f) throws Exception {
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String linha;
		int numeroLinha = -1;

		while ((linha = br.readLine()) != null) {
			linha = linha.trim();
			if (linha.equals("") || linha.charAt(0) == '#') {
				continue;
			}
			numeroLinha++;
			String[] tokens = linha.split("\\s+"); // pega qualquer coisa:
													// espaço, tab, quebra de
													// linha, etc..
			if (vertices.size() == 0)
				criaVertices(tokens.length);

			for (int numeroColuna = 0; numeroColuna < tokens.length; numeroColuna++) {
				int peso = Integer.parseInt(tokens[numeroColuna]);
				if (peso != -1) {
					Vertice v1, v2;
					v1 = vertices.get(numeroLinha);
					v2 = vertices.get(numeroColuna);
					addAresta( v1, v2, peso );
				}
			}
		}

		br.close();
	}
}
