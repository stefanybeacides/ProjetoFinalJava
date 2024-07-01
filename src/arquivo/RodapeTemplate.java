package arquivo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class RodapeTemplate extends PdfPageEventHelper {

	Font fonte = new Font(Font.FontFamily.UNDEFINED, 10, Font.ITALIC);

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		PdfPTable footer = new PdfPTable(2);

		try {

			footer.setWidths(new int[] { 24, 2 });
			footer.setTotalWidth(527);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(20);
			footer.getDefaultCell().setBorder(Rectangle.OUT_RIGHT);

			footer.addCell(new Phrase("São Paulo", fonte));
			footer.addCell(new Phrase(new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date())));
			footer.writeSelectedRows(0, -1, 34, 50, writer.getDirectContent());

		} catch (Exception e) {

		}

	}
}
