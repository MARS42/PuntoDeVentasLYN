/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Actores.BloquarLetras;
import Principal.Conectar;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author omara
 */
public class Productos extends javax.swing.JFrame implements Conectar{

    /**
     * Creates new form Productos
     */
    public Productos() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
    }

 public Object obtenerValor(JTextField caja )
 {
     return caja.getText();
 }
 public void ObtenerProductos(){
     //Primero meto tadas las cajas en una lista para con un for obtener sus datos y meterlos 
     //a la lista de productos
     ArrayList<JTextField> cajas= new ArrayList<>();
     cajas.add(txtCodigoBarra);
     cajas.add(txtNombreProducto);
     cajas.add(txtPrecioUnitarii);
     cajas.add(TxtPrecioMayoreo);
     cajas.add(txtUnidades);
     ArrayList<Object> products= new ArrayList<>();
     for(int i=0; i<cajas.size(); i++){
         if(cajas.get(i)!=null){
           products.add(cajas.get(i).getText());  
         }
         else{
             products.add("");
         }
         
     }
     for(int i=0; i<products.size(); i++){
         cajas.get(i).setText("");
     }
     cajas.clear();
     Conec.insert("insert into productos values (?,?,?,?,?);", products, "No se pudieron agregar los productos ");
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        RegistroProductos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtPrecioUnitarii = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        TxtPrecioMayoreo = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(238, 112, 82));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N

        RegistroProductos.setBackground(new java.awt.Color(246, 246, 246));
        RegistroProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel1.setText("Codigo de Barras ");
        RegistroProductos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, 50));

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel2.setText("Registro de productos");
        RegistroProductos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, 50));

        txtCodigoBarra.setBackground(new java.awt.Color(246, 246, 246));
        txtCodigoBarra.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCodigoBarra.setBorder(null);
        RegistroProductos.add(txtCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 320, 50));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RegistroProductos.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 320, 30));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel3.setText("Nombre del producto ");
        RegistroProductos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 50));

        txtNombreProducto.setBackground(new java.awt.Color(246, 246, 246));
        txtNombreProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNombreProducto.setBorder(null);
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });
        RegistroProductos.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 320, 50));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RegistroProductos.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 320, 30));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel4.setText("Precio unitario");
        RegistroProductos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, 50));

        txtPrecioUnitarii.setBackground(new java.awt.Color(246, 246, 246));
        txtPrecioUnitarii.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPrecioUnitarii.setBorder(null);
        txtPrecioUnitarii.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioUnitariiKeyTyped(evt);
            }
        });
        RegistroProductos.add(txtPrecioUnitarii, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 320, 50));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RegistroProductos.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 320, 30));

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel5.setText("Precio Mayoreo");
        RegistroProductos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, 50));

        TxtPrecioMayoreo.setBackground(new java.awt.Color(246, 246, 246));
        TxtPrecioMayoreo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtPrecioMayoreo.setBorder(null);
        TxtPrecioMayoreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtPrecioMayoreoKeyTyped(evt);
            }
        });
        RegistroProductos.add(TxtPrecioMayoreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 320, 50));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RegistroProductos.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, 320, 30));

        jLabel6.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel6.setText("Unidades");
        RegistroProductos.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, 50));

        txtUnidades.setBackground(new java.awt.Color(246, 246, 246));
        txtUnidades.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUnidades.setBorder(null);
        txtUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadesKeyTyped(evt);
            }
        });
        RegistroProductos.add(txtUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 320, 50));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RegistroProductos.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 320, 30));

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Registrar");
        jButton2.setActionCommand("Registrar");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        RegistroProductos.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, 300, 60));

        jTabbedPane1.addTab("Registro del productos ", RegistroProductos);

        jPanel2.setBackground(new java.awt.Color(246, 246, 246));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1281, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Actualizar productos", jPanel2);

        jPanel3.setBackground(new java.awt.Color(246, 246, 246));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1281, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Eliminar productos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(246, 246, 246));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1281, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inventario", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
          ObtenerProductos();  
        }catch(Error e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
        // TODO add your handling code here:
    
        
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtPrecioUnitariiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitariiKeyTyped
        // TODO add your handling code here:
  char mander=evt.getKeyChar();
              String ex=mander+"";
              if(ex.equalsIgnoreCase(".")){
                 if(!txtPrecioUnitarii.getText().contains(".")){
                     txtPrecioUnitarii.setText(  txtPrecioUnitarii.getText()+"."); 
                 }
                
              }
               if(!Character.isDigit(mander) ){
             evt.consume();
          }
    }//GEN-LAST:event_txtPrecioUnitariiKeyTyped

    private void TxtPrecioMayoreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPrecioMayoreoKeyTyped
        // TODO add your handling code here:
          char mander=evt.getKeyChar();
              String ex=mander+"";
              if(ex.equalsIgnoreCase(".")){
                 if(!TxtPrecioMayoreo.getText().contains(".")){
                     TxtPrecioMayoreo.setText(  TxtPrecioMayoreo.getText()+"."); 
                 }
                
              }
               if(!Character.isDigit(mander) ){
             evt.consume();
          }
    }//GEN-LAST:event_TxtPrecioMayoreoKeyTyped

    private void txtUnidadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadesKeyTyped
        // TODO add your handling code here:
           char mander=evt.getKeyChar();
              String ex=mander+"";
              if(ex.equalsIgnoreCase(".")){
                 if(!txtUnidades.getText().contains(".")){
                     txtUnidades.setText(  txtUnidades.getText()+"."); 
                 }
                
              }
               if(!Character.isDigit(mander) ){
             evt.consume();
          }
    }//GEN-LAST:event_txtUnidadesKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RegistroProductos;
    private javax.swing.JTextField TxtPrecioMayoreo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioUnitarii;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
