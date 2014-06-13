package br.grafo_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrafoV2 {
	List<VerticeV2> vertices = new ArrayList<VerticeV2>();
	List<ArestaV2> arestas = new ArrayList<ArestaV2>();

	public String toString() {
		String msg = "Pring: G = { V, E }::\n";
		msg += "V = " + vertices + "\n";
		msg += "E = " + arestas + "\n";
		return msg;
	}

	public List<VerticeV2> getVertices() {
		return vertices;
	}

	public void transpose() {
		VerticeV2 tmp;
		for (ArestaV2 a : arestas) {
			tmp = a.from;
			a.from = a.to;
			a.to = tmp;
		}
	}

	public List<ArestaV2> getArestas() {
		return arestas;
	}

	public GrafoV2() {
	}

	public GrafoV2(ArestaV2[] la, VerticeV2[] lv) {
		this(Arrays.asList(la), Arrays.asList(lv));
	}

	public GrafoV2(List la, List lv) {
		if (la == null || lv == null)
			return;

		arestas.clear();
		vertices.clear();
		arestas.addAll(la);
		vertices.addAll(lv);
	}

	public ArestaV2 getAresta(VerticeV2 v1, VerticeV2 v2, float w) {
		for (ArestaV2 a : arestas) {
			if (v1 == a.from && v2 == a.to && w == a.weight) {
				return a;
			}
		}
		return null;
	}

	public void addAresta(VerticeV2 v1, VerticeV2 v2, float w) {
		if (getAresta(v1, v2, w) == null) {
			arestas.add(new ArestaV2(v1, v2, w));
		}
	}

	public void addAresta__OLD(ArestaV2 a) {
		arestas.add(a);
	}

	public void criaVertices(int len) {
		// vertices = new VerticeV2[len];
		vertices.clear();
		char tmp = 'a';
		String label;
		for (int i = 0; i < len; i++) {
			label = "" + tmp++;
			vertices.add(new VerticeV2(i, label));
		}
	}

	public boolean existeAresta__OLD(VerticeV2 v1, VerticeV2 v2, float peso) {
		for (ArestaV2 a : arestas) {
			if ((v1 == a.to || v1 == a.from) && (v2 == a.to || v2 == a.from)
					&& a.weight == peso) {
				return true;
			}
		}
		return false;
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
					VerticeV2 v1, v2;
					v1 = vertices.get(numeroLinha);
					v2 = vertices.get(numeroColuna);
					addAresta( v1, v2, peso );
				}
			}
		}

		br.close();
	}
}
