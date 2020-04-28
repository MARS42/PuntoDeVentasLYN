/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import Principal.Conectar;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omara
 * esta clasegenera un pdf con el reporte de la base de datos
 * el reporte contiene 
 * productos con bajas unidades 
 * productos con las unidades optimas 
 * productos mas vendidos del mes
 * productos menos vendidos del mes
 * 
 */
public class GenerarReportes implements Conectar{
    

    
    
  public void ProductosBajos(String ruta){
      ArrayList<String> p= Conec.Select("select codigoBarras,NombreP,Unidades"
              + " from productos where Unidades<=10;", 3);
      Document doc= new Document();
       try {
           PdfWriter pdf=PdfWriter.getInstance(doc, new FileOutputStream(ruta));
           doc.open();
         PdfPTable mitabla = new PdfPTable(3);
        for(int i=0; i<p.size(); i++){
            mitabla.addCell(p.get(i));
        }
    /* for (int i = 1; i < 11; i++) {
         doc.add(new Paragraph("Tabla del "+i));
         mitabla = new PdfPTable(2);
         for (int j = 0; j < 10; j++) {
             mitabla.addCell(i+"*"+j+"=");
             mitabla.addCell(i*j+" ");
         }   
         doc.add(new Paragraph(""));
         doc.add(mitabla);
        }*/
           doc.close();
       } catch (DocumentException ex) {
     
       } catch (FileNotFoundException ex) {
        
       }
           
  
  }  
}
