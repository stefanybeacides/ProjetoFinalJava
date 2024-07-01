package tela;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerTelaInicial;

public class TelaInicial {
	
public void chamarTelaMenuInicial() throws IOException {
		
		JFrame frameTelaInicial = new JFrame(); 
		BufferedImage icon = ImageIO.read(new File("src/resources/images.jpg")); 
		frameTelaInicial.setIconImage(icon);
		frameTelaInicial.setSize(500,500); 
		frameTelaInicial.setLocationRelativeTo(null);
		
		GridLayout grid = new GridLayout(0, 1);
		
		
		JPanel panelTelaInicial = new JPanel();
		
		JLabel labelCorrentistaBasico = new JLabel("1 - Correntista básico");
		panelTelaInicial.add(labelCorrentistaBasico);
		panelTelaInicial.setLayout(grid);
		
		JLabel labelCorrentistaPremium = new JLabel("2 - Correntista premium");
		panelTelaInicial.add(labelCorrentistaPremium);
		
		JLabel labelEncerrar = new JLabel("3 - Encerrar");
		panelTelaInicial.add(labelEncerrar);
		
		JTextField opcaoTexto = new JTextField(10);
		panelTelaInicial.add(opcaoTexto);
		
		JButton botaoEnviar = new JButton("Enviar");
		panelTelaInicial.add(botaoEnviar);
		
		ImageIcon icon2 = new ImageIcon("src/resources/impacta.png");

		
		JLabel imageLabel = new JLabel(icon2);

		
		frameTelaInicial.add(imageLabel, BorderLayout.NORTH);

		frameTelaInicial.add(panelTelaInicial);
		frameTelaInicial.setVisible(true);
		
		
		
		ControllerTelaInicial controllerTelaInicial = new ControllerTelaInicial(frameTelaInicial, opcaoTexto, botaoEnviar);
		botaoEnviar.addActionListener(controllerTelaInicial);
		
	}

}
