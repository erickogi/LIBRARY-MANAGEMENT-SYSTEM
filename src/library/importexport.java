/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author kimani kogi
 */
public class importexport extends javax.swing.JFrame {
    JFileChooser chooser;
   String choosertitle;

String filePath;
String tt;
    /**
     * Creates new form importexport
     */
    public importexport() {
        initComponents();
        try
    {
      selectname();
    }
    catch (Exception ex)
    {
      Logger.getLogger(returnn.class.getName()).log(Level.SEVERE, null, ex);
    }
     methods n=new methods();
     String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
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
//    public Connection getConnection()
//  {
//    Connection con = null;
//    try
//    {
//      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
//    }
//    catch (Exception ex)
//    {
//      System.out.println(ex.getMessage());
//    }
//    return con;
//  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jButton1.setText("EXPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("WILL EXPORT STUDENTS \n,BOOKS COURSE BOOKS \n  GIVEN,AND \nLIBRARY FINES RECORDS");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                 int result;
        
    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle(choosertitle);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
      chooser.getSelectedFile();
      }
    else {
      System.out.println("No Selection ");
      }

students();
books();
loaned();
loanedcourse();
libfines();



    }//GEN-LAST:event_jButton1ActionPerformed
public void students(){
            try{

methods m=new methods();
Connection con = m.getConnection();
Statement statement = con.createStatement();
FileOutputStream fileOut;
fileOut = new FileOutputStream(""+chooser.getSelectedFile()+"\\students.xls");
HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet worksheet = workbook.createSheet("Sheet 0");
Row row1 = worksheet.createRow((short)0);
row1.createCell(0).setCellValue("ADM NO");
row1.createCell(1).setCellValue(" FIRST NAME");
row1.createCell(2).setCellValue("LAST NAME");
row1.createCell(3).setCellValue("FORM");
row1.createCell(4).setCellValue("CLASS");
row1.createCell(5).setCellValue("IMG");
Row row2 ;
ResultSet rs = statement.executeQuery("SELECT id,fname,lastname,form,class,imgurl FROM students");
while(rs.next()){
int a = rs.getRow();
row2 = worksheet.createRow((short)a);
row2.createCell(0).setCellValue(rs.getString(1));
row2.createCell(1).setCellValue(rs.getString(2));
row2.createCell(2).setCellValue(rs.getString(3));
row2.createCell(3).setCellValue(rs.getString(4));
row2.createCell(4).setCellValue(rs.getString(5));
row2.createCell(5).setCellValue(rs.getString(6));

}
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
rs.close();statement.close();

con.close();
System.out.println("Export Success");
 jTextArea1.append("\n   Export Successfull    ");
}catch(SQLException ex){
System.out.println(ex);
}catch(IOException ioe){
System.out.println(ioe);
}
}
public void books(){
            try{
methods m=new methods();
        Connection con = m.getConnection();
Statement statement = con.createStatement();
FileOutputStream fileOut;
fileOut = new FileOutputStream(""+chooser.getSelectedFile()+"\\books.xls");
HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet worksheet = workbook.createSheet("Sheet 0");
Row row1 = worksheet.createRow((short)0);
row1.createCell(0).setCellValue("BOOK NO");
row1.createCell(1).setCellValue("TITLE");
row1.createCell(2).setCellValue("EDITION");
row1.createCell(3).setCellValue("STATUS");

Row row2 ;
ResultSet rs = statement.executeQuery("SELECT id,title,ediion,status FROM books");
while(rs.next()){
int a = rs.getRow();
row2 = worksheet.createRow((short)a);
row2.createCell(0).setCellValue(rs.getString(1));
row2.createCell(1).setCellValue(rs.getString(2));
row2.createCell(2).setCellValue(rs.getString(3));
row2.createCell(3).setCellValue(rs.getString(4));


}
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
rs.close();statement.close();

con.close();
jTextArea1.append("\n   Export Successfull    ");
System.out.println("Export Success");
}catch(SQLException ex){
System.out.println(ex);
}catch(IOException ioe){
System.out.println(ioe);
}
}
public void loaned(){
            try{
methods m=new methods();
        Connection con = m.getConnection();
Statement statement = con.createStatement();
FileOutputStream fileOut;
fileOut = new FileOutputStream(""+chooser.getSelectedFile()+"\\loaned.xls");
HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet worksheet = workbook.createSheet("Sheet 0");
Row row1 = worksheet.createRow((short)0);
row1.createCell(0).setCellValue("NO");
row1.createCell(1).setCellValue("ADM NO");
row1.createCell(2).setCellValue("BOOK NO");
row1.createCell(3).setCellValue("TITLE");

row1.createCell(4).setCellValue("FIRST NAME");
row1.createCell(5).setCellValue("SECOND NAME");
row1.createCell(6).setCellValue("FORM");
row1.createCell(7).setCellValue("CLASS");
row1.createCell(8).setCellValue("EDITION");
row1.createCell(9).setCellValue("DATE");

//row1.createCell(10).setCellValue("DATE");
Row row2 ;
ResultSet rs = statement.executeQuery("SELECT no,bid,sid,title,fname,lname,form,class,edition,updated_at FROM loaned");
while(rs.next()){
int a = rs.getRow();
row2 = worksheet.createRow((short)a);
row2.createCell(0).setCellValue(rs.getString(1));
row2.createCell(1).setCellValue(rs.getString(2));
row2.createCell(2).setCellValue(rs.getString(3));
row2.createCell(3).setCellValue(rs.getString(4));
row2.createCell(4).setCellValue(rs.getString(5));
row2.createCell(5).setCellValue(rs.getString(6));
row2.createCell(6).setCellValue(rs.getString(7));
row2.createCell(7).setCellValue(rs.getString(8));
row2.createCell(8).setCellValue(rs.getString(9));
row2.createCell(9).setCellValue(rs.getString(10));

}
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
rs.close();statement.close();

con.close();
System.out.println("Export Success");
jTextArea1.append("\n   Export Successfull    ");
}catch(SQLException ex){
System.out.println(ex);
}catch(IOException ioe){
System.out.println(ioe);
}
}
public void loanedcourse(){
            try{
methods m=new methods();
        Connection con = m.getConnection();
Statement statement = con.createStatement();
FileOutputStream fileOut;
fileOut = new FileOutputStream(""+chooser.getSelectedFile()+"\\loanedcourse.xls");
HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet worksheet = workbook.createSheet("Sheet 0");
Row row1 = worksheet.createRow((short)0);
row1.createCell(0).setCellValue("NO");
row1.createCell(1).setCellValue("ADM NO");
row1.createCell(2).setCellValue("BOOK NO");
row1.createCell(3).setCellValue("TITLE");

row1.createCell(4).setCellValue("FIRST NAME");
row1.createCell(5).setCellValue("SECOND NAME");
row1.createCell(6).setCellValue("FORM");
row1.createCell(7).setCellValue("CLASS");
row1.createCell(8).setCellValue("EDITION");
row1.createCell(9).setCellValue("DATE");


Row row2 ;
ResultSet rs = statement.executeQuery("SELECT  no,bid,sid,title,fname,lname,form,class,edition,updated_at FROM loanedcourse");
while(rs.next()){
int a = rs.getRow();
row2 = worksheet.createRow((short)a);
row2.createCell(0).setCellValue(rs.getString(1));
row2.createCell(1).setCellValue(rs.getString(2));
row2.createCell(2).setCellValue(rs.getString(3));
row2.createCell(3).setCellValue(rs.getString(4));
row2.createCell(4).setCellValue(rs.getString(5));
row2.createCell(5).setCellValue(rs.getString(6));
row2.createCell(6).setCellValue(rs.getString(7));
row2.createCell(7).setCellValue(rs.getString(8));
row2.createCell(8).setCellValue(rs.getString(9));
row2.createCell(9).setCellValue(rs.getString(10));

}
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
rs.close();statement.close();

con.close();
System.out.println("Export Success");
 jTextArea1.append("\n   Export Successfull    ");
}catch(SQLException ex){
System.out.println(ex);
}catch(IOException ioe){
System.out.println(ioe);
}
}
public void libfines(){
            try{
methods m=new methods();
Connection con = m.getConnection();
Statement statement = con.createStatement();
FileOutputStream fileOut;
fileOut = new FileOutputStream(""+chooser.getSelectedFile()+"\\fines.xls");
HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet worksheet = workbook.createSheet("Sheet 0");
Row row1 = worksheet.createRow((short)0);
row1.createCell(0).setCellValue("pid");
row1.createCell(1).setCellValue("ADM NO");
row1.createCell(2).setCellValue("AMOUNT");
row1.createCell(3).setCellValue("DAY");
row1.createCell(4).setCellValue("STATUS");

Row row2 ;
ResultSet rs = statement.executeQuery("SELECT pid,id,amount,day,status FROM libfines");
while(rs.next()){
int a = rs.getRow();
row2 = worksheet.createRow((short)a);
row2.createCell(0).setCellValue(rs.getString(1));
row2.createCell(1).setCellValue(rs.getString(2));
row2.createCell(2).setCellValue(rs.getString(3));
row2.createCell(3).setCellValue(rs.getString(4));
row2.createCell(4).setCellValue(rs.getString(5));


}
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
rs.close();statement.close();

con.close();
jTextArea1.append("\n   Export Successfull    ");
System.out.println("Export Successfull");
}catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Export not Successfull");
System.out.println(ex);
}catch(IOException ioe){
    
System.out.println(ioe);
}
}
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
            java.util.logging.Logger.getLogger(importexport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(importexport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(importexport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(importexport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new importexport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
