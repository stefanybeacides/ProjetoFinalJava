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
import persistencia.DaoCorrentistaBasico;
import persistencia.DaoCorrentistaPremium;

public class ControllerTelaCadastroCorrentista implements ActionListener {
	
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
	String tipoCorrentista;
	JFrame frameTelaCadastroCorrentista;
	JButton botaoCadastrar;
	JButton botaoVoltar;
	
	
	
	public ControllerTelaCadastroCorrentista(JTextField caixaTextoRecebidoNome, JTextField caixaTextoRecebidoCpf,
			JTextField caixaTextoRecebidoCep, JTextField caixaTextoRecebidoLogradouro,
			JTextField caixaTextoRecebidoBairro, JTextField caixaTextoRecebidoCidade, JTextField caixaTextoRecebidoUf,
			JTextField caixaTextoRecebidoEmail, JTextField caixaTextoRecebidoQtd, JTextField caixaTextoRecebidoAnuidade, String correntista,
			JFrame frameTelaCadastroCorrentista, JButton botaoCadastrar, JButton botaoVoltar) {
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
		this.frameTelaCadastroCorrentista = frameTelaCadastroCorrentista;
		this.tipoCorrentista = correntista;
		this.botaoCadastrar = botaoCadastrar;
		this.botaoVoltar = botaoVoltar;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == botaoCadastrar) {
			 CorrentistaBasico correntista = new CorrentistaBasico();
			 Correntista correntistaSimples = new Correntista() {
			};
			 CorrentistaPremium correntistaPremium = new CorrentistaPremium();
			 DaoCorrentistaBasico dao = new DaoCorrentistaBasico();
			 DaoCorrentistaPremium daoPremium = new DaoCorrentistaPremium();
			 DaoCorrentista daoCorrentista = new DaoCorrentista();
			 boolean sucesso = false;
			 Correntista correntista2 = daoCorrentista.buscarCorrentista(caixaTextoRecebidoCpf.getText());
			 
			 if (correntista2.getCpf() == null) {
			 
				 correntistaSimples.setNome(caixaTextoRecebidoNome.getText()); 
				 correntistaSimples.setCpf(caixaTextoRecebidoCpf.getText()); 
				 correntistaSimples.setCep(caixaTextoRecebidoCep.getText());
				 correntistaSimples.setLogradouro(caixaTextoRecebidoLogradouro.getText());
				 correntistaSimples.setBairro(caixaTextoRecebidoBairro.getText());
				 correntistaSimples.setCidade(caixaTextoRecebidoCidade.getText());
				 correntistaSimples.setUf(caixaTextoRecebidoUf.getText());
				 correntistaSimples.setEmail(caixaTextoRecebidoEmail.getText());
				 correntistaSimples.setQtdTransacao(Integer.parseInt(caixaTextoRecebidoQtd.getText()));
				 correntistaSimples.setValorAnuidade(Double.parseDouble(caixaTextoRecebidoAnuidade.getText()));
				 correntistaSimples.setTabela(tipoCorrentista);
	            
	            if(tipoCorrentista.equals("correntista_premium")) {
	            	correntistaSimples.setCalcularLimiteCredito(correntistaPremium.calcularLimiteCredito(Integer.parseInt(caixaTextoRecebidoQtd.getText())));
		            sucesso = daoPremium.salvarCorrentistaNoBanco(correntistaSimples);

	            	
	            }if(tipoCorrentista.equals("correntista_basico")) {
	            	correntistaSimples.setCalcularLimiteSaque(correntista.calcularLimiteSaque(Integer.parseInt(caixaTextoRecebidoQtd.getText())));
		            sucesso = dao.salvarCorrentistaNoBanco(correntistaSimples);

	            }
	           

	            if (sucesso) {
	                JOptionPane.showMessageDialog(frameTelaCadastroCorrentista, "Cadastro realizado com sucesso!");
	                limparCampos();
	            } else {
	                JOptionPane.showMessageDialog(frameTelaCadastroCorrentista, "Erro ao realizar cadastro!");
	            }
			 }else {
				 JOptionPane.showMessageDialog(null, "CPF ja cadastrado na base de dados.");
			 }
	        } else if (e.getSource() == botaoVoltar) {
	            frameTelaCadastroCorrentista.dispose();
	        }
	    }

	    private void limparCampos() {
	        caixaTextoRecebidoNome.setText("");
	        caixaTextoRecebidoCpf.setText("");
	        caixaTextoRecebidoCep.setText("");
	        caixaTextoRecebidoLogradouro.setText("");
	        caixaTextoRecebidoBairro.setText("");
	        caixaTextoRecebidoCidade.setText("");
	        caixaTextoRecebidoUf.setText("");
	        caixaTextoRecebidoEmail.setText("");
	        caixaTextoRecebidoQtd.setText("");
	        caixaTextoRecebidoAnuidade.setText("");
	    }
		
	}
	


