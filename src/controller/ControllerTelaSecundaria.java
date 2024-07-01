package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidade.Correntista;
import persistencia.DaoCorrentista;
import persistencia.DaoCorrentistaBasico;
import persistencia.DaoCorrentistaPremium;
import tela.TelaCadastroCorrentista;
import tela.TelaEditarCorrentista;
import tela.TelaListarCorrentistas;
import tela.TelaSecundaria;

public class ControllerTelaSecundaria implements ActionListener{

	JFrame frameTelaSecundaria;
	JTextField opcao;
	JButton botaoEnviar;
	JButton botaoVoltar;
	String tipoCorrentista;
	
	
	TelaSecundaria telaSecundaria = new TelaSecundaria();
	DaoCorrentista correntistaBuscar = new DaoCorrentista();


	public ControllerTelaSecundaria(JFrame frameTelaSecundaria,  JTextField opcao, String correntista, JButton botaoEnviar,
			JButton botaoVoltar) {
		this.frameTelaSecundaria = frameTelaSecundaria;
		this.opcao = opcao;
		this.botaoEnviar = botaoEnviar;
		this.botaoVoltar = botaoVoltar;
		this.tipoCorrentista = correntista;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(opcao.getText().equals("1") || opcao.getText().equals("2") || opcao.getText().equals("3") || opcao.getText().equals("4") || opcao.getText().equals("5")) {
			switch (opcao.getText()) {
				case "1": {
					try {
						TelaCadastroCorrentista.chamarTelaCadastroCorrentista(tipoCorrentista);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					frameTelaSecundaria.setVisible(false);
					break;
					
					}
				case "2": {
					if (tipoCorrentista.equals("correntista_basico")) {
						TelaListarCorrentistas.listarCorrentistas(DaoCorrentistaBasico.retornoListaCorrentista(tipoCorrentista));
					}if (tipoCorrentista.equals("correntista_premium")) {
						TelaListarCorrentistas.listarCorrentistas(DaoCorrentistaPremium.retornoListaCorrentista(tipoCorrentista));

					}
					break;
					
					}
				case "3": {
					String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do correntista que deseja editar: ");
					Correntista correntista = null;
					if (!cpf.isEmpty()) {
						correntista =  correntistaBuscar.buscarCorrentista(cpf);
						try {
							TelaEditarCorrentista.chamarTelaEditarCorrentista(correntista);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}else {
						JOptionPane.showMessageDialog(null, "Campo CPF Vazio");

					}
					break;
					
					}
				case "4": {
					String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do correntista que deseja deletar: ");
					Correntista correntista = null;
					if (!cpf.isEmpty()) {
						correntista =  correntistaBuscar.buscarCorrentista(cpf);
						int confirma = JOptionPane.showConfirmDialog(null, "Confirme se é este correntista que deseja deletar: \n" + correntista.getCpf() + "\n" + correntista.getNome());
						if(confirma == 0) {
							correntistaBuscar.deletarCorrentistaNoBanco(correntista);
							JOptionPane.showMessageDialog(null, "Correntista deletado.");
						}else {
							JOptionPane.showMessageDialog(null, "Você não confirmou a deleção. Retornando ao menu.");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Campo CPF Vazio");

					}
					break;
					
					}
				case "5": {
					System.exit(0);
					break;
					
				}
				
			}
		}else {
			
			JOptionPane.showMessageDialog(null, "A opção " + opcao.getText() + " não é válida!\nEscolha 1 para CADASTRAR, 2 para LISTAR, 3 para EDITAR, 4 para DELETAR ou 5 para ENCERRAR.");
	
	}
		
	}


	

	
}
