package br.pereira.ui;

import javax.swing.JDialog;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class JanelaOperacoes extends JDialog {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JComboBox<TelaInterna> cbxEscolha1;
	JComboBox<TelaInterna> cbxEscolha2;
	boolean ok = false;

	OpMat opEscolhida = OpMat.SUB;
	
	public enum OpMat {
		SUB, SOMA
	}
	
	public boolean isOk() {
		return ok;
	}
	
	public void onShow() {
		ok = false;
	}
	
	public void setEscolhas( JInternalFrame opcoes[] ) {
		DefaultComboBoxModel<TelaInterna> cbm1 = ( DefaultComboBoxModel )cbxEscolha1.getModel();
		cbm1.removeAllElements();

		DefaultComboBoxModel<TelaInterna> cbm2 = ( DefaultComboBoxModel<TelaInterna> )cbxEscolha2.getModel();
		cbm2.removeAllElements();
		
		for( JInternalFrame op : opcoes ) {
			TelaInterna ti = ( TelaInterna ) op;
			cbm1.addElement( ti );
			cbm2.addElement( ti );
		}
		cbm1.setSelectedItem( opcoes[0] );
		cbm2.setSelectedItem( opcoes[1] );
	}
	
	public JanelaOperacoes() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				onShow();
			}
		});
		setBounds(100, 100, 450, 339);
		getContentPane().setLayout(null);
		
		cbxEscolha1 = new JComboBox<TelaInterna>();
		cbxEscolha1.setBounds(12, 12, 350, 33);
		getContentPane().add(cbxEscolha1);
		
		cbxEscolha2 = new JComboBox<TelaInterna>();
		cbxEscolha2.setBounds(12, 50, 350, 33);
		getContentPane().add(cbxEscolha2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = true;
				setVisible( false );
			}
		});
		btnOk.setBounds(12, 250, 188, 42);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = false;
				setVisible( false );
			}
		});
		btnCancel.setBounds(238, 250, 188, 42);
		getContentPane().add(btnCancel);
		
		JRadioButton rdbtnSubtrao = new JRadioButton("Subtração");
		rdbtnSubtrao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opEscolhida = OpMat.SUB;
			}
		});
		rdbtnSubtrao.setSelected(true);
		buttonGroup.add(rdbtnSubtrao);
		rdbtnSubtrao.setBounds(12, 200, 104, 23);
		getContentPane().add(rdbtnSubtrao);
		
		JRadioButton rdbtnSoma = new JRadioButton("Soma");
		rdbtnSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opEscolhida = OpMat.SOMA;
			}
		});
		buttonGroup.add(rdbtnSoma);
		rdbtnSoma.setBounds(120, 200, 149, 23);
		getContentPane().add(rdbtnSoma);
		
	}
}
