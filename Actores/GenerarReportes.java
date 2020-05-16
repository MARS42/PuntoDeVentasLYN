/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import Principal.Conectar;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author omara esta clasegenera un pdf con el reporte de la base de datos el
 * reporte contiene productos con bajas unidades productos con las unidades
 * optimas productos mas vendidos del mes productos menos vendidos del mes
 *
 */
public class GenerarReportes implements Conectar {
TablaProductos tb= new TablaProductos();
    private com.itextpdf.text.Font fuenteBold = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER,
            30, Font.BOLD);
    private com.itextpdf.text.Font fuenteBold1 = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER,
            12, Font.BOLD);
    private com.itextpdf.text.Font Normal = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER,
            12, Font.PLAIN);
    private com.itextpdf.text.Font fuenteItalica = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER,
            8, Font.ITALIC);

    public void ProductosBajos(String ruta) throws IOException {
        ArrayList<Producto> p = Conec.SelectProductos("select  codigoBarras,NombreP,Unidades"
                + " from productos where Unidades<=10;");
        Document doc = new Document();
        try {
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();

            doc.add(new Paragraph(getHeader("Papeleria LYN")));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(getInfo1("Reporte generado automáticamente por el sistema de inventarios, fecha de creación ")));
            doc.add(new Paragraph(getDateFormat(getDate())));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(getInfo("Productos con un número de unidades menor o igual a 10")));
            doc.add(new Paragraph(" "));
            PdfPTable mitabla = new PdfPTable(3);
            Chunk c = new Chunk();
            c.setBackground(BaseColor.ORANGE);
            c.setFont(Normal);
            c.append("Código B.");
            Chunk c1 = new Chunk();
            c1.setBackground(BaseColor.ORANGE);
            c1.setFont(Normal);
            c1.append("Nombre P.");
            Chunk c2 = new Chunk();
            c2.setBackground(BaseColor.ORANGE);
            c2.setFont(Normal);
            c2.append("Unidades");
            mitabla.addCell(new Paragraph(c));

            mitabla.addCell(new Paragraph(c1));

            mitabla.addCell(new Paragraph(c2));

            for (int i = 0; i < p.size(); i++) {
                mitabla.addCell(p.get(i).codigoBarras);
                mitabla.addCell(p.get(i).NombreP);
                mitabla.addCell(p.get(i).unidades + "");

            }

            doc.add(mitabla);
            doc.add(new Paragraph());
           
            p.clear();
            
            
            doc.add(new Paragraph(getInfo("Productos mas vendidos del mes ")));
            doc.add(new Paragraph()); 
            doc.add(new Paragraph()); 
            doc.add(new Paragraph()); 
            //añadiendo la tabla con los productos mas vendidos 
             tb.productos();
           PdfPTable  mitabla1 = new PdfPTable(3);
         Chunk    c3 = new Chunk();
            c3.setBackground(BaseColor.ORANGE);
            c3.setFont(Normal);
            c3.append("Código Barras.");
           Chunk  c4 = new Chunk();
            c4.setBackground(BaseColor.ORANGE);
            c4.setFont(Normal);
            c4.append("Nombre P.");
             Chunk c5 = new Chunk();
            c5.setBackground(BaseColor.ORANGE);
            c5.setFont(Normal);
            c5.append("Unidades");
            mitabla1.addCell(new Paragraph(c3));

            mitabla1.addCell(new Paragraph(c4));

            mitabla1.addCell(new Paragraph(c5));
              for (int i = 0; i < tb.p.length; i++) {
                mitabla1.addCell(tb.p[i].codigoBarras);
                mitabla1.addCell(""+Conec.Select("select NombreP from productos where codigoBarras='"+tb.p[i].codigoBarras+"';", 1).get(0));
                mitabla1.addCell(tb.p[i].ventas+ "");

            }
              doc.add(mitabla1);
            doc.add(new Paragraph());
           doc.add(new Paragraph(getFoter("Papeleria LYN ")));
           tb.p=null;
            doc.close();
        } catch (DocumentException ex) {

        } catch (FileNotFoundException ex) {

        }

    }

    public Paragraph getHeader(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteBold);

        p.add(c);
        return p;
    }

    public Paragraph getInfo(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
        c.append(texto);
        c.setFont(Normal);
        p.add(c);
        return p;
    }

    public Paragraph getInfo1(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        // p.setAlignment(Element.ALIGN_JUSTIFIED_AL);
        c.append(texto);
        c.setFont(Normal);
        p.add(c);
        return p;
    }

    public Paragraph getFoter(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_RIGHT);
        c.append(texto);
        c.setFont(fuenteItalica);
        p.add(c);
        return p;
    }
     public Paragraph getDateFormat(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        
        c.append(texto);
        c.setFont(fuenteBold1);
        p.add(c);
        return p;
    }
    public String getDate() {
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return dia + "/" + mes + "/" + año;
    }
}
