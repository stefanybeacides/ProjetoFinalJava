package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tela.TelaInicial;
import tela.TelaSecundaria;

public class ControllerTelaInicial implements ActionListener{
	
		JFrame frameTelaInicial;
		JTextField opcao;
		JButton botaoEnviar;
		
		
		TelaInicial telaInicial = new TelaInicial();


		public ControllerTelaInicial(JFrame frameTelaInicial, JTextField opcao, JButton botaoEnviar) {
			this.frameTelaInicial = frameTelaInicial;
			this.opcao = opcao;
			this.botaoEnviar = botaoEnviar;
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(opcao.getText().equals("1") || opcao.getText().equals("2") || opcao.getText().equals("3")) {
				switch (opcao.getText()) {
					case "1": {
						try {
							TelaSecundaria.chamarTelaMenuSecundario("correntista_basico");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						frameTelaInicial.setVisible(false);
						break;
						
						}
					case "2": {
						try {
							TelaSecundaria.chamarTelaMenuSecundario("correntista_premium");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
						
						}
					case "3": {
						System.exit(0);
						break;
						
					}
					
				}
			}else {
				
				JOptionPane.showMessageDialog(null, "A opção " + opcao.getText() + " não é válida!\nEscolha 1 para CORRENTISTA BÁSICO, 2 para CORRENTISTA PREMIUM ou 3 para ENCERRAR.");
		
		}
			
		}


}
