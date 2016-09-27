/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author kimani kogi
 */
public class loanedcourse extends javax.swing.JFrame {
  DefaultTableModel model = new DefaultTableModel();
  String filePath;
  String tt;
  String fileurlp = null;
    /**
     * Creates new form loanedcourse
     */
    public loanedcourse() {
 this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = loanedcourse.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = loanedcourse.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void changedUpdate(DocumentEvent e)
      {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    });
    initComponents();
    findUsers();
    try
    {
      selectname();
    }
    catch (Exception ex)
    {
      Logger.getLogger(returnn.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
        this.setTitle(tt);
      this.sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          try
    {
      selectsid();
      showimg();
    }
    catch (Exception ex)
    {
      Logger.getLogger(loanedcourse.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
        catch (Exception ex)
        {
          Logger.getLogger(loanedcourse.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
            this.bid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          try
    {
      selectbid();
     
    }
    catch (Exception ex)
    {
      Logger.getLogger(loanedcourse.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
        catch (Exception ex)
        {
          Logger.getLogger(loanedcourse.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    }
  
  public void selectname()
    throws Exception
  {
    Connection con = getConnection();
    Statement st2 = con.createStatement();
    
    ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
    while (res7.next()) {
     tt=(res7.getString("name"));
    }
    st2.close();
    res7.close();
    con.close();
  }
  
  public Connection getConnection()
  {
    Connection con = null;
    try
    {
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return con;
  }
  
  public ArrayList<returndb> ListUsers(String ValToSearch)
  {
    ArrayList<returndb> usersList = new ArrayList();
    try
    {
      Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `loanedcourse` WHERE CONCAT(`sid`,`bid`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        returndb user = new returndb(rs.getString("bid"), rs.getString("edition"), rs.getString("fname"), rs.getString("lname"), rs.getInt("no"), rs.getInt("sid"), rs.getString("title"), rs.getString("updated_at"));
        
        usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
  
  public void findUsers()
  {
    ArrayList<returndb> users = ListUsers(this.jtFilter.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { " STUDENT ID", "BOOK ID", "TITLE", "EDITION", "Loaned ON" });
    Object[] row = new Object[5];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = Integer.valueOf(((returndb)users.get(i)).getSid());
      row[1] = ((returndb)users.get(i)).getBid();
      row[2] = ((returndb)users.get(i)).getTitle();
      row[3] = ((returndb)users.get(i)).getEdition();
      row[4] = ((returndb)users.get(i)).getUpdated_at();
      
      model.addRow(row);
    }
    this.table.setModel(model);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jtFilter = new javax.swing.JTextField();
        sid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        bid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        iconl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.setRowHeight(40);
        table.setRowMargin(4);
        jScrollPane1.setViewportView(table);

        jtFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtFilterActionPerformed(evt);
            }
        });

        jButton1.setText("CHECK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bidActionPerformed(evt);
            }
        });

        jButton2.setText("CHECK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        iconl.setText("image");

        jLabel2.setText("ADM NO TO CHECK");

        jLabel3.setText("BOOK NO TO CHECK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(sid)
                    .addComponent(jButton1)
                    .addComponent(bid)
                    .addComponent(jButton2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bidActionPerformed

    private void jtFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtFilterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
    {
      selectsid();
      showimg();
    }
    catch (Exception ex)
    {
      Logger.getLogger(loaned.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try
    {
      selectbid();
    }
    catch (Exception ex)
    {
      Logger.getLogger(loaned.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton2ActionPerformed
 public void selectbid()
    throws Exception
  {
    String si = this.bid.getText();
    if (si.equals(""))
    {
      JOptionPane.showMessageDialog(null, "enter book id");
    }
    else
    {
      Connection con = getConnection();
      Statement st2 = con.createStatement();
      Statement st0 = con.createStatement();
      Statement st3 = con.createStatement();
      ResultSet res7 = st2.executeQuery("SELECT fname FROM loanedcourse  WHERE bid='" + this.bid.getText() + "'");
      ResultSet res8 = st0.executeQuery("SELECT title FROM loanedcourse  WHERE bid='" + this.bid.getText() + "'");
      ResultSet res9 = st3.executeQuery("SELECT updated_at FROM loanedcourse  WHERE bid='" + this.bid.getText() + " '");
      if ((res7.next()) && (res8.next()) && (res9.next()))
      {
        JOptionPane.showMessageDialog(null, "" + res8.getString("title") + "'  given to  " + "\n" + "      " + res7.getString("fname") + "  on" + "\n" + "" + res9.getString("updated_at") + "");
        st2.close();
        st0.close();
        st3.close();
        res7.close();
        res8.close();
        res9.close();
        
        con.close();
      }
      else
      {
        JOptionPane.showMessageDialog(null, "NO DATA ");
      }
    }
  }
  
    public void selectsid()
    throws Exception
  {
    String si = this.sid.getText();
    if (si.equals(""))
    {
      JOptionPane.showMessageDialog(null, "enter students id");
    }
    else
    {
      Connection con = getConnection();
      Statement st2 = con.createStatement();
      Statement st0 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT fname FROM students  WHERE id=" + this.sid.getText() + "");
      ResultSet res8 = st0.executeQuery("SELECT SUM(no) FROM loanedcourse  WHERE sid=" + this.sid.getText() + "");
      if ((res7.next()) && (res8.next()))
      {
        int f = res8.getInt(1);
        
        JOptionPane.showMessageDialog(null, "" + res7.getString("fname") + "'s  student  " + "\n" + " has     " + f + "  books" + "\n" + "given");
        st2.close();
        st0.close();
        
        res7.close();
        res8.close();
        
        con.close();
      }
      else
      {
        JOptionPane.showMessageDialog(null, "NO DATA ");
      }
    }
  }
  
  public void showimg()
    throws Exception
  {
    try
    {
      Connection con = getConnection();
      Statement st2 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT imgurl FROM students  WHERE id=" + this.sid.getText() + "");
      if (res7.next()) {
        this.filePath = res7.getString("imgurl");
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
            java.util.logging.Logger.getLogger(loanedcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loanedcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loanedcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loanedcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loanedcourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bid;
    private javax.swing.JLabel iconl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtFilter;
    private javax.swing.JTextField sid;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
