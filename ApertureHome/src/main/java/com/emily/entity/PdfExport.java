package com.emily.entity;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfExport {
	
	private List<Product> products;
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.pink);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("Product Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Product Category", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Quantity Available", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Price Per Item", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Quantity Sold", font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		for (Product product : products) {
			table.addCell(product.getProductName());
			table.addCell(product.getProductCategory());
			table.addCell(String.valueOf(product.getQuantityAvailable()));
			table.addCell("£" + String.valueOf(product.getPricePerItem()));
			table.addCell(String.valueOf(product.getQuantitySold()));
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);
		
		Paragraph p = new Paragraph("List of Products", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f, 2.5f, 2.5f, 2.5f, 2.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
		
	}

}
