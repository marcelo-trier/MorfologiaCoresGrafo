package br.morfcorgraf;

import java.awt.EventQueue;

import br.pereira.ui.JanelaPrincipal;

public class Aplicacao {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
