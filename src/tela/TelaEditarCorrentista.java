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

import controller.ControllerTelaEditarCorrentista;
import entidade.Cep;
import entidade.Correntista;
import service.BuscarCep;

public class TelaEditarCorrentista {
	
public static void chamarTelaEditarCorrentista(Correntista correntista) throws IOException {
		
		String campoNome = "Digite o nome do correntista";
		String campoCpf = "Digite o CPF do correntista";
		String campoCep = "Digite o CEP do correntista"; 
		String campoLogradouro = "Logradouro";
		String campoBairro = "Bairro";
		String campoCidade = "Cidade";
		String campoUf = "UF";
		String campoEmail = "Digite o email do correntista";
		String campoQtdTransacao = "Digite a quantidade de transações";
		String campoAnuidade = "Digite o valor da anuidade";
		BuscarCep buscarCep = new BuscarCep();
		
		JFrame frameTelaEditarCorrentista = new JFrame();
		BufferedImage icon = ImageIO.read(new File("src/resources/images.jpg")); 
		frameTelaEditarCorrentista.setIconImage(icon);
		frameTelaEditarCorrentista.setSize (350, 600);
		frameTelaEditarCorrentista.setLocationRelativeTo(null);
		
		
		GridLayout grid = new GridLayout(0, 1);
		
		JPanel panelTelaEditarCorrentista = new JPanel();
		
		JLabel labelCampoNome = new JLabel(campoNome);
		panelTelaEditarCorrentista.add(labelCampoNome);
		panelTelaEditarCorrentista.setLayout(grid);
		
		JTextField caixaTextoNome = new JTextField(10);
		caixaTextoNome.setText(correntista.getNome());
		panelTelaEditarCorrentista.add(caixaTextoNome);
		
		
		JLabel labelCampoCpf = new JLabel(campoCpf);
		panelTelaEditarCorrentista.add(labelCampoCpf);
		
		JTextField caixaTextoCpf = new JTextField(10);
		caixaTextoCpf.setText(correntista.getCpf());
		panelTelaEditarCorrentista.add(caixaTextoCpf);
		
		JLabel labelCampoCep = new JLabel(campoCep);
		panelTelaEditarCorrentista.add(labelCampoCep);
		
		JTextField caixaTextoCep = new JTextField(10);
		caixaTextoCep.setText(correntista.getCep());
		panelTelaEditarCorrentista.add(caixaTextoCep);
		
		JLabel labelCampoLogradouro = new JLabel(campoLogradouro);
		panelTelaEditarCorrentista.add(labelCampoLogradouro);
		
		JTextField caixaTextoLogradouro = new JTextField(10);
		caixaTextoLogradouro.setText(correntista.getLogradouro());
		panelTelaEditarCorrentista.add(caixaTextoLogradouro);
		
		JLabel labelCampoBairro = new JLabel(campoBairro);
		panelTelaEditarCorrentista.add(labelCampoBairro);
		
		JTextField caixaTextoBairro = new JTextField(10);
		caixaTextoBairro.setText(correntista.getBairro());
		panelTelaEditarCorrentista.add(caixaTextoBairro);
		
		JLabel labelCampoCidade = new JLabel(campoCidade);
		panelTelaEditarCorrentista.add(labelCampoCidade);
		
		JTextField caixaTextoCidade = new JTextField(10);
		caixaTextoCidade.setText(correntista.getCidade());
		panelTelaEditarCorrentista.add(caixaTextoCidade);
		
		JLabel labelCampoUf = new JLabel(campoUf);
		panelTelaEditarCorrentista.add(labelCampoUf);
		
		JTextField caixaTextoUf = new JTextField(10);
		caixaTextoUf.setText(correntista.getUf());
		panelTelaEditarCorrentista.add(caixaTextoUf);
		
		JLabel labelCampoEmail = new JLabel(campoEmail);
		panelTelaEditarCorrentista.add(labelCampoEmail);
		
		JTextField caixaTextoEmail = new JTextField(10);
		caixaTextoEmail.setText(correntista.getEmail());
		panelTelaEditarCorrentista.add(caixaTextoEmail);
		
		JLabel labelCampoQtdTransacao = new JLabel(campoQtdTransacao);
		panelTelaEditarCorrentista.add(labelCampoQtdTransacao);
		
		JTextField caixaTextoQtdTransacao = new JTextField(10);
		caixaTextoQtdTransacao.setText(Integer.toString(correntista.getQtdTransacao()));
		panelTelaEditarCorrentista.add(caixaTextoQtdTransacao);
		
		JLabel labelCampoAnuidade = new JLabel(campoAnuidade);
		panelTelaEditarCorrentista.add(labelCampoAnuidade);
		
		JTextField caixaTextoAnuidade = new JTextField(10);
		caixaTextoAnuidade.setText(Double.toString(correntista.getValorAnuidade()));
		panelTelaEditarCorrentista.add(caixaTextoAnuidade);
		
		JButton botaoAtualizar = new JButton("Atualizar");
		panelTelaEditarCorrentista.add(botaoAtualizar);
		
		JButton botaoVoltar = new JButton("Voltar");
		panelTelaEditarCorrentista.add(botaoVoltar);
		
	
			caixaTextoCep.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
	                String valorCep = caixaTextoCep.getText();
	                try {
						Cep cep = buscarCep.ConsultaCep(valorCep);
						caixaTextoLogradouro.setText(cep.getLogradouro());
						caixaTextoBairro.setText(cep.getBairro());
						caixaTextoCidade.setText(cep.getLocalidade());
						caixaTextoUf.setText(cep.getUf());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
	        });
			
			
			botaoVoltar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
	                try {
	                	TelaSecundaria secundaria  = new TelaSecundaria();
	                	frameTelaEditarCorrentista.setVisible(false);
	                	TelaSecundaria.chamarTelaMenuSecundario(correntista.getTabela());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
	        });
		
		frameTelaEditarCorrentista.add(panelTelaEditarCorrentista);
		frameTelaEditarCorrentista.setVisible(true);
		
		ControllerTelaEditarCorrentista controllerEditarCorrentista = new ControllerTelaEditarCorrentista(caixaTextoNome,caixaTextoCpf,
				caixaTextoCep, caixaTextoLogradouro, caixaTextoBairro, caixaTextoCidade, caixaTextoUf, caixaTextoEmail, caixaTextoQtdTransacao, 
				caixaTextoAnuidade, correntista.getTabela(), frameTelaEditarCorrentista, botaoAtualizar, botaoVoltar);
		
		botaoAtualizar.addActionListener(controllerEditarCorrentista); 
		
		
	}

}
