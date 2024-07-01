package arquivo;

import java.awt.Desktop;
import java.util.List;

import entidade.Correntista;
import entidade.CorrentistaBasico;
import entidade.CorrentistaPremium;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ManipuladorArquivo {
	
	public static void gerarPdfCorrentista(List<Correntista> correntista) {
		String caminhoArquivo = "C:\\Users\\ritad\\Documents\\correntista.pdf";
		Document document = new Document();

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
			writer.setPageEvent(new RodapeTemplate());
			String tipoCorrentista = null;
			
			if (correntista.get(0).getTabela().equals("correntista_basico")) {
				tipoCorrentista = "Basico";
			}
			if (correntista.get(0).getTabela().equals("correntista_premium")) {
				tipoCorrentista = "Premium";
			}

			document.open();
			document.add(new Paragraph("Empresa: IMPACTA", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

			document.add(new Paragraph("Relatorio detalhado de correntistas " + tipoCorrentista, FontFactory.getFont(FontFactory.HELVETICA_BOLD,14)));

			document.add(new Paragraph(" "));

			if (correntista != null) {
				
				for (Correntista listCorrentista : correntista) {
					PdfPTable table = new PdfPTable(2);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.setWidthPercentage(100);
					
					table.addCell("Tipo de Correntista");
					table.addCell(tipoCorrentista);

					table.addCell("Nome");
					table.addCell(listCorrentista.getNome());

					table.addCell("CPF");
					table.addCell(listCorrentista.getCpf());
					
					table.addCell("CEP");
					table.addCell(listCorrentista.getCep());
					
					table.addCell("Logradouro");
					table.addCell(listCorrentista.getLogradouro());
					
					table.addCell("Bairro");
					table.addCell(listCorrentista.getBairro());
					
					table.addCell("Cidade");
					table.addCell(listCorrentista.getCidade());
					
					table.addCell("Uf");
					table.addCell(listCorrentista.getUf());
					
					table.addCell("Email");
					table.addCell(listCorrentista.getEmail());
					
					table.addCell("Qtd Transações");
					table.addCell(String.valueOf(listCorrentista.getQtdTransacao()));
					
					table.addCell("Valor de Anuidade");
					table.addCell("R$" +String.valueOf(listCorrentista.getValorAnuidade()));
					
					if (listCorrentista.getTabela().equals("correntista_basico")) {
						CorrentistaBasico correntistaBasico = new CorrentistaBasico();
						Double limiteSaque = correntistaBasico.calcularLimiteSaque(listCorrentista.getQtdTransacao());
						
						table.addCell("Limite de Saque");
						table.addCell("R$" +String.valueOf(limiteSaque));
					}

					if (listCorrentista.getTabela().equals("correntista_premium")) {
						CorrentistaPremium correntistaPremium = new CorrentistaPremium();
						Double limiteCredito = correntistaPremium.calcularLimiteCredito(listCorrentista.getQtdTransacao());
						
						table.addCell("Limite de Crédito");
						table.addCell("R$" +String.valueOf(limiteCredito));
					}
					
					document.add(new Paragraph(" "));


					document.add(table);
				}

	
			}

			document.close();

			File pdfArquivo = new File(caminhoArquivo);

			if(pdfArquivo.exists()) {
				if(Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfArquivo);
					JOptionPane.showMessageDialog(null, "PDF Gerado com sucesso!");
				}else {
					System.out.println("PDF gerado mas não foi possivel abrir!");
				}

			}else {
				System.out.println("PDF gerado mas não foi possivel encontrar!");
			}


		} catch (Exception e) {
			e.getMessage();
		}

	}

}