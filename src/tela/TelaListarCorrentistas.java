package tela;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import arquivo.ManipuladorArquivo;
import entidade.Correntista;

public class TelaListarCorrentistas {
	
public static void listarCorrentistas(List<Correntista> list) {
		
		int qtdLinhas = list.size();
		
		String[][] tabelaString = new String [qtdLinhas] [11];
		
		int posicaoColuna = 0;
		int posicaoLinha = 0;
		
		for (Correntista correntista : list) {
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getNome();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getCpf();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getCep();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getLogradouro();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getBairro();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getCidade();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getUf();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = correntista.getEmail();
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = String.valueOf(correntista.getQtdTransacao());
			posicaoColuna ++;
			
			tabelaString[posicaoLinha][posicaoColuna] = String.valueOf(correntista.getValorAnuidade());
			posicaoColuna ++;
			
			if (correntista.getTabela().equals("correntista_basico")) {
				tabelaString[posicaoLinha][posicaoColuna] = String.valueOf(correntista.getCalcularLimiteSaque());
				posicaoColuna ++;
			}if (correntista.getTabela().equals("correntista_premium")) {
				tabelaString[posicaoLinha][posicaoColuna] = String.valueOf(correntista.getCalcularLimiteCredito());
				posicaoColuna ++;

			}
			
			posicaoColuna = 0;
			
		}
		
		JFrame frameListarCorrentista = new JFrame();
		
		frameListarCorrentista.setSize(1300,800);

	    String[] nomeColunas;

        if (list.get(0).getTabela().equals("correntista_basico")) {
            nomeColunas = new String[]{"NOME", "CPF", "CEP", "LOGRADOURO", "BAIRRO", "CIDADE", "UF", "EMAIL", "QTD TRANSAÇÕES", "VALOR ANUIDADE", "LIMITE DE SAQUE"};
        } else if (list.get(0).getTabela().equals("correntista_premium")) {
            nomeColunas = new String[]{"NOME", "CPF", "CEP", "LOGRADOURO", "BAIRRO", "CIDADE", "UF", "EMAIL", "QTD TRANSAÇÕES", "VALOR ANUIDADE", "LIMITE DE CRÉDITO"};
        } else {
            nomeColunas = new String[]{"NOME", "CPF", "CEP", "LOGRADOURO", "BAIRRO", "CIDADE", "UF", "EMAIL", "QTD TRANSAÇÕES", "VALOR ANUIDADE"};
        }
        JTable tabelaCorrentista = new JTable(tabelaString, nomeColunas);

        
        int[] columnWidths = {200, 200, 200, 250, 250, 200, 100, 250, 200, 200, 200};
        for (int i = 0; i < columnWidths.length; i++) {
            if (i < tabelaCorrentista.getColumnModel().getColumnCount()) {
                TableColumn column = tabelaCorrentista.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }
        }
        
        tabelaCorrentista.setPreferredScrollableViewportSize(new Dimension(800, 400));
        tabelaCorrentista.setFillsViewportHeight(true);
	
		tabelaCorrentista.setBounds(30, 40, 300, 300);
		
		JScrollPane scrollPaneListarCorrentista = new JScrollPane(tabelaCorrentista);
		
		JPanel panelListarCorrentista = new JPanel();
		panelListarCorrentista.setLayout(new BorderLayout()); 
        panelListarCorrentista.add(scrollPaneListarCorrentista, BorderLayout.CENTER);
		
		panelListarCorrentista.add(scrollPaneListarCorrentista);
		
		frameListarCorrentista.add(panelListarCorrentista);

		JButton gerarPDF = new JButton("Gerar PDF Detalhado");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		buttonPanel.add(gerarPDF);
		panelListarCorrentista.add(buttonPanel, BorderLayout.SOUTH);

		gerarPDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                	ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();
                	ManipuladorArquivo.gerarPdfCorrentista(list);
                	TelaSecundaria secundaria  = new TelaSecundaria();
                	frameListarCorrentista.setVisible(false);
                	TelaSecundaria.chamarTelaMenuSecundario(list.get(0).getTabela());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
        });
		
		frameListarCorrentista.setVisible(true);
		
	}
		
	}

