package br.pereira.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import br.morfcorgraf.Morfologia;
import br.morfcorgraf.Morfologia.TAREFA;
import br.pereira.ui.JanelaOperacoes.OpMat;
import br.pereira.util.ImageFile;
import br.pereira.util.ImgUtil;


public class JanelaPrincipal extends JFrame {

	private JDesktopPane contentPane;
	protected ImageFile fileManager = ImageFile.getInstance();
	private JanelaOperacoes operacoes = new JanelaOperacoes();
	
	public void clickOperacoes() throws Exception {
		JInternalFrame arrTelas[] = contentPane.getAllFrames();
		operacoes.setEscolhas( arrTelas );
		operacoes.setModal( true );
		operacoes.setVisible( true );
		if( !operacoes.isOk() ) {
			return;
		}
		
		TelaInterna t1, t2;
		t1 = ( TelaInterna )operacoes.cbxEscolha1.getSelectedItem();
		t2 = ( TelaInterna )operacoes.cbxEscolha2.getSelectedItem();
		if( operacoes.opEscolhida == OpMat.SUBTRACAO ) {
			ImgUtil.subtrai( t1.getImage(), t2.getImage() );
		}
		else {
			// TODO
		}
			
	}
	
	public void clickTipoImagem() {
		BufferedImage img = getImage();
		ImgUtil.IMAGES_TYPE tipo;
		tipo = ImgUtil.IMAGES_TYPE.getType(img.getType());
		JOptionPane.showMessageDialog(this, tipo);
	}

	public void clickGray1() {
		mostraImagem(ImgUtil.rgb2gray(getImage()));
	}

	public void clickTask( TAREFA t ) {
		try {
			Morfologia m = new Morfologia(getImage());
			m.setTask( t );
			m.execute();
			mostraImagem( m.getImage() );
		} catch (Exception ex) {
			JOptionPane.showMessageDialog( this, "erro: " + ex );
		}
	}

	public void mostraImagem(String titulo, BufferedImage imgOut) {
		TelaInterna interno = new TelaInterna(titulo, imgOut);
		contentPane.add(interno);
		interno.setVisible(true);
	}

	public void mostraImagem(BufferedImage imgOut) {
		mostraImagem("", imgOut);
	}

	public BufferedImage getImage() {
		// pega a janela ativa...
		TelaInterna ti = (TelaInterna) contentPane.getSelectedFrame();
		BufferedImage img;
		img = ti.getImage();
		return img;
	}
	
	public void clickCarregaArquivos() throws Exception {
		BufferedImage imgs[] = ImageFile.getInstance().carregaImagens();

		for (BufferedImage imagem : imgs)
			mostraImagem(imagem);
	}

	public void clickSave() throws Exception {
		ImageFile.getInstance().save(getImage());
	}

	public void clickLoad() throws Exception {
		mostraImagem( ImageFile.getInstance().carregaImagem());
	}

	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 399);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Carrega Imagem...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickLoad();
				} catch (Exception ex) {

				}
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Salva Imagem...");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickSave();
				} catch (Exception ex) {

				}
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmCarregaPastaCom = new JMenuItem(
				"Carrega Pasta com Imagens...");
		mntmCarregaPastaCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickCarregaArquivos();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmCarregaPastaCom);

		JMenu mnImagens = new JMenu("Imagens");
		menuBar.add(mnImagens);

		JMenuItem mntmGrayscale = new JMenuItem("GrayScale - 1");
		mntmGrayscale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGray1();
			}
		});
		mnImagens.add(mntmGrayscale);

		JMenuItem mntmTipoDaImagem = new JMenuItem("Tipo da Imagem?");
		mntmTipoDaImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickTipoImagem();
			}
		});
		mnImagens.add(mntmTipoDaImagem);
		
		JMenuItem mntmOperaesMatemticas = new JMenuItem("Operações Matemáticas");
		mntmOperaesMatemticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickOperacoes();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnImagens.add(mntmOperaesMatemticas);

		JMenu mnProcessamento = new JMenu("Morfologia");
		menuBar.add(mnProcessamento);

		JMenuItem mntmOtsu = new JMenuItem("Otsu");
		mnProcessamento.add(mntmOtsu);
		
		JMenuItem mntmEroso = new JMenuItem("Erosão");
		mntmEroso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickTask( TAREFA.EROSAO );
			}
		});
		mnProcessamento.add(mntmEroso);

		JMenuItem mntmDilatao = new JMenuItem("Dilatação");
		mntmDilatao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickTask( TAREFA.DILATACAO );
			}
		});
		mnProcessamento.add(mntmDilatao);
		
		JMenuItem mntmErosaoGrafo = new JMenuItem("Erosao Grafo");
		mntmErosaoGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickTask( TAREFA.EROSAO_GRAFO_MST );
			}
		});
		mnProcessamento.add(mntmErosaoGrafo);
		contentPane = new JDesktopPane();
		contentPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
}
