/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author tlmarco
 */
public class PrintPDF1
{
    private Document documento=null;
    
    public PrintPDF1()
    {
    }
    
    public String printdocumentoPdf(String UserName, String Prezzo, String Pass) throws FileNotFoundException, DocumentException, IOException
    {
        
        try
        {
            String Path = "/Users/tlmarco/NetBeansProjects/iMyTripService/";
            UUID uuid = UUID.randomUUID();
            documento = new Document();
            String pathpaz = Path+"PDF/"+UserName+uuid+".pdf";
            Date adesso = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(pathpaz));
            
            documento.open();
            
            FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            if(documento == null)
            {
                return "";
            }
            documento.add(new Chunk("L'utente: "));
            documento.add(new Chunk(UserName,FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            
            documento.add(new Paragraph(""));
            documento.add(new Chunk("ha effetuate il bonifico per un biglietto di valore : "));
            documento.add(new Chunk(Prezzo,FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

            documento.add(new Paragraph("giorno validit√† biglietto : "+ formatter.format(adesso)));
            
            if(documento!=null)
            {   
                documento.close();
                documento=null;
            }
            return pathpaz;
        }
        catch (DocumentException de)
        {
            de.printStackTrace();
            throw de;
        }
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            throw ioe;
        }
    }
    
}
