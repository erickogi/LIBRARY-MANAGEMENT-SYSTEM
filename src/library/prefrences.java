/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author kimani kogi
 */
public class prefrences extends javax.swing.JFrame {
  String filePath;
   String tt;
  String fileurlp = null;
    /**
     * Creates new form prefrences
     */
    public prefrences() {
  initComponents();
    try
    {
      select();
      methods n=new methods();
    String col=n.selectcolor();
    Color c=new Color(Integer.parseInt(col));
    jPanel1.setBackground(c);
    }
    catch (Exception ex)
    {
      Logger.getLogger(prefrences.class.getName()).log(Level.SEVERE, null, ex);
    }
    try
    {
      selectname();
    }
    catch (Exception ex)
    {
      Logger.getLogger(prefrences.class.getName()).log(Level.SEVERE, null, ex);
    }
//    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
//        this.setTitle(tt);
     methods n=new methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
    this.name.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          prefrences.this.select();
        }
        catch (Exception ex)
        {
          Logger.getLogger(prefrences.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
  }
  
  public void selectname()
    throws Exception
  {
    methods m=new methods();
                Connection con = m.getConnection();
    Statement st2 = con.createStatement();
    
    ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
    while (res7.next()) {
       tt=(res7.getString("name"));
    }
    st2.close();
    res7.close();
    con.close();
  }
// public Connection getConnection()
//  {
//    try
//    {
//      return DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
//    }
//    catch (Exception e)
//    {
//      e.printStackTrace();
//    }
//    return null;
//  }
  public void showimg()
    throws Exception
  {
    try
    {
       methods m=new methods();
                Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT pdfs FROM libprefrence  WHERE id=1");
      if (res7.next()) {
        this.filePath = res7.getString("pdfs");
      } else {
        JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
      }
      st2.close();
      res7.close();
      con.close();
      String op = "image";
      if (this.filePath.equals(op))
      {
        this.iconl.setIcon(null);
        this.iconl.setIcon(null);
        this.iconl.setText(" no image");
      }
      else
      {
        BufferedImage img = null;
        try
        {
          img = ImageIO.read(new File(this.filePath));
          this.fileurlp = this.filePath.replace("\\", "\\\\");
        }
        catch (IOException e)
        {
          JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
          
          this.iconl.setIcon(null);
          this.iconl.setText(" no image");
        }
        Image dimg = img.getScaledInstance(this.iconl.getWidth(), this.iconl.getHeight(), 4);
        
        ImageIcon icon = new ImageIcon(dimg);
        this.iconl.setText("");
        this.iconl.setIcon(icon);
      }
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
  }
  public void select()
    throws Exception
  {
    methods m=new methods();
    Connection con = m.getConnection();
    Statement st2 = con.createStatement();
    Statement st0 = con.createStatement();
    Statement sta = con.createStatement();
    Statement stb = con.createStatement();
     Statement stC= con.createStatement();
    ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
    ResultSet res8 = st0.executeQuery("SELECT days FROM libprefrence   ");
    ResultSet resa = sta.executeQuery("SELECT perday FROM libprefrence   ");
    ResultSet resb = stb.executeQuery("SELECT pdfs FROM libprefrence   ");
    ResultSet resj = stC.executeQuery("SELECT daysc FROM libprefrence   ");
    while ((res7.next()) && (res8.next()) && (resa.next()) && (resb.next())&&(resj.next()))
    {
      this.name.setText(res7.getString("name"));
      String sid=(resb.getString("pdfs"));
      
      int y = res8.getInt("days");
      this.fine.setText(Integer.toString(y));
      int y1 = resa.getInt("perday");
      int j=resj.getInt("daysc");
      mfcb.setText(Integer.toString(j));
      this.maximumdays.setText(Integer.toString(y1));
      showimg();
    }
    st2.close();
    st0.close();
    sta.close();
    stb.close();
    
    res7.close();
    res8.close();
    resa.close();
    resb.close();
    
    con.close();
  }
  
  public void executeSQlQuery(String query, String message)
  {
     methods m=new methods();
     Connection con = m.getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1) {
        JOptionPane.showMessageDialog(null, "Data " + message + " Succefully  !!SYSTEM WILL CLOSE TO EFFECT CHANGES");
        System.exit(1);
      } else {
        JOptionPane.showMessageDialog(null, "Data Not " + message);
      }
      st.close();
      con.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JTextField();
        maximumdays = new javax.swing.JTextField();
        fine = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        iconl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        mfcb = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jColorChooser1 = new javax.swing.JColorChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        fine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fineActionPerformed(evt);
            }
        });

        jButton1.setText("CHOOSE LOGO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        iconl.setText("image");

        jLabel1.setText("NAME");

        jLabel2.setText("MAXIMUM DAYS");

        jLabel3.setText("FINE");

        jButton2.setText("EFFECT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("MAXIMUM DAYS FOR COURSE BOOKS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maximumdays)
                    .addComponent(name)
                    .addComponent(fine)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(mfcb)
                    .addComponent(jLabel4))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maximumdays, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(10, 10, 10)
                        .addComponent(mfcb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fine, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(358, 358, 358))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try
    {
      JFileChooser chooser = new JFileChooser();
      FileFilter ft = new FileNameExtensionFilter("Image Files", new String[] { "jpg", "png", "jpeg" });
      
      chooser.addChoosableFileFilter(ft);
      
      chooser.showOpenDialog(null);
      File f = chooser.getSelectedFile();
      
      this.filePath = f.getAbsolutePath().toString();
      BufferedImage img = null;
      try
      {
        img = ImageIO.read(new File(this.filePath));
        this.fileurlp = this.filePath.replace("\\", "\\\\");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      Image dimg = img.getScaledInstance(this.iconl.getWidth(), this.iconl.getHeight(), 4);
      
      ImageIcon icon = new ImageIcon(dimg);
      this.iconl.setText("");
      this.iconl.setIcon(icon);
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
      JOptionPane.showMessageDialog(null, "error loading image ");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fineActionPerformed
public String readTxt(){
     String col="";
      try{
         File x=new File("C:\\Users\\kimani kogi\\Documents\\file.txt"); 
        // File x=new File("file.txt");
           Scanner sc=new Scanner(x);
           String fs="";
           while(sc.hasNext()){
              col=col+sc.next(); 
              }
           
             try{
                 
            
     
       Color c=new Color(Integer.parseInt(col));
       }
           
            
       catch(Exception cc){
          // c.printStackTrace();
           col="-1";
       }
      }
        catch(Exception cc){
          cc.printStackTrace();
           col="-1";
       }
      return col;
}
public void writeCol(String newcol){
    FileWriter writer = null;
      try {
          writer = new FileWriter("file.txt");
          writer.write(newcol);
          writer.close();
      } catch (IOException ex) {
          Logger.getLogger(prefrences.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
          try {
              writer.close();
          } catch (IOException ex) {
              Logger.getLogger(prefrences.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 
        Color c=jColorChooser1.getColor();
 String newcol=Integer.toString(c.getRGB());
 String oldcol=readTxt();
 
 writeCol(newcol);
 
 //JOptionPane.showMessageDialog(null, color);//
        
        
        String query = "UPDATE `libprefrence` SET `name`='" + this.name.getText() + "',`days`='" + this.maximumdays.getText() + "',`daysc`='" + this.mfcb.getText() + "',`perday`='" + this.fine.getText() + "',`pdfs`='" + this.fileurlp + "'";
    
    executeSQlQuery(query, "Updated");        
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(prefrences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prefrences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prefrences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prefrences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prefrences().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fine;
    private javax.swing.JLabel iconl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField maximumdays;
    private javax.swing.JTextField mfcb;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}
