package tela;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerTelaSecundaria;

public class TelaSecundaria {
	
public static void chamarTelaMenuSecundario(String correntista) throws IOException {
		
		JFrame frameTelaSecundaria = new JFrame(); 
		BufferedImage icon = ImageIO.read(new File("src/resources/images.jpg")); 
		frameTelaSecundaria.setIconImage(icon);
		frameTelaSecundaria.setSize(500,500); 
		frameTelaSecundaria.setLocationRelativeTo(null);
		
		GridLayout grid = new GridLayout(0, 1);
		
		frameTelaSecundaria.setSize(300,300); 
		JPanel panelTelaSecundaria = new JPanel();
		
		JLabel labelCadastrar = new JLabel("1 - Cadastrar correntista");
		panelTelaSecundaria.add(labelCadastrar);
		panelTelaSecundaria.setLayout(grid);
		
		JLabel labelListar = new JLabel("2 - Listar correntista");
		panelTelaSecundaria.add(labelListar);
		
		JLabel labelEditar = new JLabel("3 - Editar correntista");
		panelTelaSecundaria.add(labelEditar);
		
		JLabel labelDeletar = new JLabel("4 - Deletar correntista");
		panelTelaSecundaria.add(labelDeletar);
		
		JLabel labelEncerrar = new JLabel("5 - Encerrar");
		panelTelaSecundaria.add(labelEncerrar);
		
		JTextField opcaoTexto = new JTextField(10);
		panelTelaSecundaria.add(opcaoTexto);
		
		JButton botaoEnviar = new JButton("Enviar");
		panelTelaSecundaria.add(botaoEnviar);
		
		JButton botaoVoltar = new JButton("Voltar");
		panelTelaSecundaria.add(botaoVoltar);
		
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                	TelaInicial inicial  = new TelaInicial();
                	frameTelaSecundaria.setVisible(false);
                	inicial.chamarTelaMenuInicial();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
        });
		
		
		frameTelaSecundaria.add(panelTelaSecundaria);
		frameTelaSecundaria.setVisible(true);
		
		ControllerTelaSecundaria controllerTelaSecundaria = new ControllerTelaSecundaria(frameTelaSecundaria, opcaoTexto, correntista, botaoEnviar, botaoVoltar);
		botaoEnviar.addActionListener(controllerTelaSecundaria);
		
	}

}
