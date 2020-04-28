/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author omara
 * generar las barras para los deportes 
 */
public class GenerarGrafica {
    public double conNumero(String cadena){
        return Double.parseDouble(cadena);
    }
    public void  generarBarras(ArrayList<Producto> pro){
        try {
            DefaultCategoryDataset ds= new DefaultCategoryDataset();
            for(int i=0; i<pro.size(); i++){
            ds.addValue(pro.get(i).unidades, pro.get(i).NombreP, "");
            
            }
           
            JFreeChart jf=ChartFactory.createBarChart3D("Prductos", "Nombre del producto", "Unidades", ds, PlotOrientation.VERTICAL, true, true, true);
            ChartFrame f= new ChartFrame("Productos", jf);
            f.setSize(1000, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    JFreeChart jf;
    public  JFreeChart  generarBarrasImagen(ArrayList<Producto> pro){
        try {
            DefaultCategoryDataset ds= new DefaultCategoryDataset();
            for(int i=0; i<pro.size(); i++){
            ds.addValue(pro.get(i).unidades, pro.get(i).NombreP, "");
            
            }
           
            jf=ChartFactory.createBarChart3D("Prductos", "Nombre del producto", "Unidades", ds, PlotOrientation.VERTICAL, true, true, true);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return jf;
    }
}
