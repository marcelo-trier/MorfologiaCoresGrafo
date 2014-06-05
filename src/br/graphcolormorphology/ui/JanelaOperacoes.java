package br.graphcolormorphology.ui;

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

	OpMat opEscolhida = OpMat.SUBTRACAO;
	
	public enum OpMat {
		SUBTRACAO, SOMA
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
	}
	
	public JanelaOperacoes() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				onShow();
			}
		});
		setBounds(100, 100, 450, 225);
		getContentPane().setLayout(null);
		
		cbxEscolha1 = new JComboBox<TelaInterna>();
		cbxEscolha1.setBounds(12, 12, 188, 33);
		getContentPane().add(cbxEscolha1);
		
		cbxEscolha2 = new JComboBox<TelaInterna>();
		cbxEscolha2.setBounds(238, 12, 188, 33);
		getContentPane().add(cbxEscolha2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = true;
				setVisible( false );
			}
		});
		btnOk.setBounds(12, 125, 188, 42);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = false;
				setVisible( false );
			}
		});
		btnCancel.setBounds(238, 125, 188, 42);
		getContentPane().add(btnCancel);
		
		JRadioButton rdbtnSubtrao = new JRadioButton("Subtração");
		rdbtnSubtrao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opEscolhida = OpMat.SUBTRACAO;
			}
		});
		rdbtnSubtrao.setSelected(true);
		buttonGroup.add(rdbtnSubtrao);
		rdbtnSubtrao.setBounds(12, 59, 104, 23);
		getContentPane().add(rdbtnSubtrao);
		
		JRadioButton rdbtnSoma = new JRadioButton("Soma");
		rdbtnSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opEscolhida = OpMat.SOMA;
			}
		});
		buttonGroup.add(rdbtnSoma);
		rdbtnSoma.setBounds(120, 59, 149, 23);
		getContentPane().add(rdbtnSoma);
		
	}
}
