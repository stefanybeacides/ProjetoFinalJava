package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidade.Correntista;
import entidade.CorrentistaBasico;
import entidade.CorrentistaPremium;
import persistencia.DaoCorrentista;

public class ControllerTelaEditarCorrentista implements ActionListener {
	
	JTextField caixaTextoRecebidoNome;
	JTextField caixaTextoRecebidoCpf;
	JTextField caixaTextoRecebidoCep;
	JTextField caixaTextoRecebidoLogradouro;
	JTextField caixaTextoRecebidoBairro;
	JTextField caixaTextoRecebidoCidade;
	JTextField caixaTextoRecebidoUf;
	JTextField caixaTextoRecebidoEmail;
	JTextField caixaTextoRecebidoQtd;
	JTextField caixaTextoRecebidoAnuidade;
	String table;
	JFrame frameTelaCadastroCorrentista;
	JButton botaoAtualizar;
	JButton botaoVoltar;
	
	
	
	public ControllerTelaEditarCorrentista(JTextField caixaTextoRecebidoNome, JTextField caixaTextoRecebidoCpf,
			JTextField caixaTextoRecebidoCep, JTextField caixaTextoRecebidoLogradouro,
			JTextField caixaTextoRecebidoBairro, JTextField caixaTextoRecebidoCidade, JTextField caixaTextoRecebidoUf,
			JTextField caixaTextoRecebidoEmail, JTextField caixaTextoRecebidoQtd, JTextField caixaTextoRecebidoAnuidade,
			String tabela,
			JFrame frameTelaCadastroCorrentista, JButton botaoAtualizar, JButton botaoVoltar) {
		this.caixaTextoRecebidoNome = caixaTextoRecebidoNome;
		this.caixaTextoRecebidoCpf = caixaTextoRecebidoCpf;
		this.caixaTextoRecebidoCep = caixaTextoRecebidoCep;
		this.caixaTextoRecebidoLogradouro = caixaTextoRecebidoLogradouro;
		this.caixaTextoRecebidoBairro = caixaTextoRecebidoBairro;
		this.caixaTextoRecebidoCidade = caixaTextoRecebidoCidade;
		this.caixaTextoRecebidoUf = caixaTextoRecebidoUf;
		this.caixaTextoRecebidoEmail = caixaTextoRecebidoEmail;
		this.caixaTextoRecebidoQtd = caixaTextoRecebidoQtd;
		this.caixaTextoRecebidoAnuidade = caixaTextoRecebidoAnuidade;
		this.table = tabela;
		this.frameTelaCadastroCorrentista = frameTelaCadastroCorrentista;
		this.botaoAtualizar = botaoAtualizar;
		this.botaoVoltar = botaoVoltar;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == botaoAtualizar) {
			 CorrentistaBasico correntistaBasico = new CorrentistaBasico();
			 CorrentistaPremium correntistaPremium = new CorrentistaPremium();
			 boolean sucesso = false;
			 Correntista correntista = new Correntista() {
			};
			 DaoCorrentista dao = new DaoCorrentista();
			 
	            correntista.setNome(caixaTextoRecebidoNome.getText()); 
	            correntista.setCpf(caixaTextoRecebidoCpf.getText()); 
	            correntista.setCep(caixaTextoRecebidoCep.getText());
	            correntista.setLogradouro(caixaTextoRecebidoLogradouro.getText());
	            correntista.setBairro(caixaTextoRecebidoBairro.getText());
	            correntista.setCidade(caixaTextoRecebidoCidade.getText());
	            correntista.setUf(caixaTextoRecebidoUf.getText());
	            correntista.setEmail(caixaTextoRecebidoEmail.getText());
	            correntista.setQtdTransacao(Integer.parseInt(caixaTextoRecebidoQtd.getText()));
	            correntista.setValorAnuidade(Double.parseDouble(caixaTextoRecebidoAnuidade.getText()));
	            correntista.setTabela(table);
	            
	            if(table.equals("correntista_premium")) {
	            	correntista.setCalcularLimiteCredito(correntistaPremium.calcularLimiteCredito(Integer.parseInt(caixaTextoRecebidoQtd.getText())));
		            sucesso = dao.atualizarCorrentistaNoBanco(correntista);

	            	
	            }if(table.equals("correntista_basico")) {
	            	correntista.setCalcularLimiteSaque(correntistaBasico.calcularLimiteSaque(Integer.parseInt(caixaTextoRecebidoQtd.getText())));
		            sucesso = dao.atualizarCorrentistaNoBanco(correntista);

	            }


	            sucesso = dao.atualizarCorrentistaNoBanco(correntista);
	            if (sucesso) {
	                JOptionPane.showMessageDialog(frameTelaCadastroCorrentista, "Cadastro atualizado com sucesso!");
	                frameTelaCadastroCorrentista.setVisible(false);
	                
	            } else {
	                JOptionPane.showMessageDialog(frameTelaCadastroCorrentista, "Erro ao realizar cadastro!");
	            }
	        } else if (e.getSource() == botaoVoltar) {
	            frameTelaCadastroCorrentista.dispose();
	        }
	    }
		
	}
	


