package com.emily.app.entity;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

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

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfExport {

    // list of products to feed into - automatic constructor with Lombok
    private List<Product> products;


    // creating a table header
    private void writeTableHeader(PdfPTable table) {
        // creating a cell instance
        PdfPCell cell = new PdfPCell();
        // setting background colour of cell and padding
        cell.setBackgroundColor(Color.pink);
        cell.setPadding(5);

        //setting font of header
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        // assigning names of fields and adding them to the header
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

    // writing data to the table
    private void writeTableData(PdfPTable table) {
        // iterating through each product individually and adding values to fields
        for (Product product : products) {
            table.addCell(product.getProductName());
            table.addCell(product.getProductCategory());
            table.addCell(String.valueOf(product.getQuantityAvailable()));
            table.addCell("£" + String.valueOf(product.getPricePerItem()));
            table.addCell(String.valueOf(product.getQuantitySold()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        // creating document and generating an output
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        // opening document and setting font
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        // creating title of table and adding to document
        Paragraph p = new Paragraph("List of Products", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        // creating instance of a table
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f, 2.5f, 2.5f, 2.5f, 2.5f});
        table.setSpacingBefore(10);

        // calling previous functions to fill out the table
        writeTableHeader(table);
        writeTableData(table);

        // adding table to the document
        document.add(table);

        // closing document - will allow completed pdf to be output
        document.close();
    }

}
