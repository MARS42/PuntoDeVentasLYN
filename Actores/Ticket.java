/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.ServiceUI;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;

public class Ticket {

    Document doce = new Document();
    //Ticket attribute content
    public String contentTicket = "Papeleria {{nameLocal}}\n"
            + "EXPEDIDO EN: {{expedition}}\n"
            + "Av Insurgentes no.1229, Miguel Hidalgo, 62748 Cuautla, Mor.\n"
            + "Telefono: 7351544789 \n"
            + "=====================================================\n"
            + "Ticket # {{ticket}}\n"
            + "LE ATENDIO: {{cajero}}\n"
            + "Hora: {{dateTime}}\n"
            + "=============================\n"
            + "{{items}}\n"
            + "=============================\n"
            + "TOTAL: {{total}}\n\n"
            + "RECIBIDO: {{recibo}}\n"
            + "CAMBIO: {{change}}\n\n"
            + "=============================\n"
            + "GRACIAS POR SU COMPRA...\n"
            + "ESPERAMOS SU VISITA NUEVAMENTE {{nameLocal}}\n"
            + "\n"
            + "\n";
    
    //El constructor que setea los valores a la instancia
    public Ticket(String nameLocal, String expedition, String ticket, String caissier, String dateTime, String items, String total, String recibo, String change) {
        this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
        this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);

        this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
        this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
        this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
        this.contentTicket = this.contentTicket.replace("{{items}}", items);

        this.contentTicket = this.contentTicket.replace("{{total}}", total);
        this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
        this.contentTicket = this.contentTicket.replace("{{change}}", change);
    }

    public void generarTicket() {

        try {
            PdfWriter pdf = PdfWriter.getInstance(doce, new FileOutputStream("ticket.pdf"));
            doce.open();
            doce.add(new Paragraph(contentTicket));
            doce.close();
            //
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void imprimir(){
    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
 FileInputStream in = null;
        try {
            in = new FileInputStream(new File("ticket.pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
 PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
 pras.add(new Copies(1));        
        DocFlavor.INPUT_STREAM flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
 Doc doc = new SimpleDoc(in, flavor, null);
 DocPrintJob job = service.createPrintJob();

        try {
            job.print(doc, pras);
        } catch (PrintException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void print() {
        
        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes; Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        //Aca obtenemos el servicio de impresion por defatul
        //Si no quieres ver el dialogo de seleccionar impresora usa esto
        //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //Con esto mostramos el dialogo para seleccionar impresora
        //Si quieres ver el dialogo de seleccionar impresora usalo
        //Solo mostrara las impresoras que soporte arreglo de bits
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);

        //Creamos un arreglo de tipo byte
        byte[] bytes;

        //Aca convertimos el string(cuerpo del ticket) a bytes tal como
        //lo maneja la impresora(mas bien ticketera :p)
        bytes = this.contentTicket.getBytes();
        
        //Creamos un documento a imprimir, a el se le appendeara
        //el arreglo de bytes
        Doc doc = new SimpleDoc(doce, flavor, null);

        //Creamos un trabajo de impresión
        DocPrintJob job = service.createPrintJob();

        //Imprimimos dentro de un try de a huevo
        try {
            //El metodo print imprime
            job.print(doc, null);
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
        }
    }

}
