package menuOrders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class createInvoice {

	public void createWord(String [] invoiceText) throws IOException {
	       
	            try (
				XWPFDocument document = new XWPFDocument()) {
					//Erstelle Dokument im Ordner
					FileOutputStream out = new FileOutputStream(
					new File(invoiceText [0]+"_Invoice" + ".docx"));
 
					//erstelle Zeilenumbruch
					XWPFParagraph paragraph = document.createParagraph();
					XWPFRun run = paragraph.createRun();
					
					run.setText("-------------------------------------------SadCarRent--------------------------------------------------");
					run.addCarriageReturn();
				
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					
					//Adress zeile Unternehmen
					run.setText("SadCarRent| "+invoiceText[9]+"."+invoiceText[10]+"| "+invoiceText[11]+"| "+invoiceText[12]);
					run.addCarriageReturn();
					
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					//Adress zeile Kunde
					run.setText(invoiceText[1]+" "+ invoiceText[2]);
					run.addCarriageReturn();
					run.setText(invoiceText[3]+". "+ invoiceText[4]);
					run.addCarriageReturn();
					run.setText(invoiceText[6]+" "+ invoiceText[5]);
					run.addCarriageReturn();
					run.setText(invoiceText[7]);
					run.addCarriageReturn();
					
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					// Customer ID und Rechnungsdatum ausgabe
					run.setText("Customer ID:"+ invoiceText[0]+"               Date: " + LocalDate.now() );
					run.addCarriageReturn();
					
					paragraph = document.createParagraph();
					run = paragraph.createRun();
					//Ausgabe der Rechnungspositionen
					run.setText("Description                                                                  "+"Total");
					run.addCarriageReturn();
					run.setText(invoiceText[14]+" "+ invoiceText[13]+ " "+ invoiceText[15]+" "+ invoiceText[16]+ "                                "+ invoiceText[17]+"€"  );
					
					document.write(out);
						
					out.close();
				}
	            System.out.println("createdWord" + "_" + invoiceText [0] + ".docx" + " written successfully");
	        }
}
