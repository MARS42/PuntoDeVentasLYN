/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omara
 * Esta clase genera el codigo de barras de los productos
 */
public class CodigoBarras {

   public void genenar(String codigo,String nombreArchivo) {
       Document doc= new Document();
       try {
           PdfWriter pdf=PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo+".pdf"));
           doc.open();
           doc.add(new Paragraph("Codigo de barras 1"));
           doc.add(new Paragraph(" "));
           Barcode39 code= new Barcode39();
           code.setCode(codigo);
           Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
           doc.add(img);
            Barcode128 code128= new Barcode128();
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Codigo de barras 2"));
           doc.add(new Paragraph(" "));
           code.setCode(codigo);
           Image img128 = code128.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
           doc.add(img128);
           doc.close();
       } catch (DocumentException ex) {
           Logger.getLogger(CodigoBarras.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(CodigoBarras.class.getName()).log(Level.SEVERE, null, ex);
       }
   } 
}
