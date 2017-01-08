/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddUpdatenventory extends javax.swing.JFrame {
 DefaultTableModel model = new DefaultTableModel();
 String tt;
    /**
     * Creates new form AddUpdatenventory
     */
    public AddUpdatenventory() {
      this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = AddUpdatenventory.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = AddUpdatenventory.this.jtFilter.getText();
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
    
    jPanel1.setBackground(Color.yellow);
   // paintComponent(this);
    try
    {
      selectname();
     methods n=new methods();
    String col=n.selectcolor();
    Color c=new Color(Integer.parseInt(col));
    jPanel1.setBackground(c);
    }
    catch (Exception ex)
    {
      Logger.getLogger(AddUpdatenventory.class.getName()).log(Level.SEVERE, null, ex);
    }
    methods n=new methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
      //  this.setTitle(tt);
  
             bid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
             btitle.requestFocus(); //To change body of generated methods, choose Tools | Templates.
            }
        });
          btitle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               Bedition.requestFocus(); //To change body of generated methods, choose Tools | Templates.
            }
        });
           Bedition.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              insert();
            }
        });
    
    }
  
  public void selectname()
    throws Exception
  {
      methods m=new methods();
        Connection con = m.getConnection();
  //  Connection con = getConnection();
    Statement st2 = con.createStatement();
    
    ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
    while (res7.next()) {
       tt=(res7.getString("name"));
    }
    st2.close();
    res7.close();
    con.close();
   // return tt;
  }
  
//  public Connection getConnection()
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
  
  public ArrayList<db> ListUsers(String ValToSearch)
  {
    ArrayList<db> usersList = new ArrayList();
    try
    {
      methods m=new methods();
        Connection con = m.getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `books` WHERE CONCAT(`id`, `title`, `ediion`, `status`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        db user = new db(rs.getString("id"), rs.getString("title"), rs.getString("ediion"), rs.getString("status"));
        
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
    ArrayList<db> users = ListUsers(this.jtFilter.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "ID", "TITLE", "EDITION", "STATUS" });
    Object[] row = new Object[4];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((db)users.get(i)).getId();
      row[1] = ((db)users.get(i)).getTitle();
      row[2] = ((db)users.get(i)).getEdition();
      row[3] = ((db)users.get(i)).getStatus();
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
  
  public void executeSQlQuery(String query, String message)
  {
   methods m=new methods();
        Connection con = m.getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
        
        JOptionPane.showMessageDialog(null, "Book " + message + " Succefully");
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Book Not " + message);
        JOptionPane.showMessageDialog(null, "MAKE SURE THE BOOK ID IS NOT ALREADY BEING USED \nCHECK THE BOOK DETAILS ENTERED FOR ERRORS ie ID SHOULD BE NUMBERS ONLY");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jtFilter = new javax.swing.JTextField();
        bid = new javax.swing.JTextField();
        btitle = new javax.swing.JTextField();
        Bedition = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bstatus = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(Color.RED);

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
        table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableMouseDragged(evt);
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtFilterKeyReleased(evt);
            }
        });

        Bedition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BeditionActionPerformed(evt);
            }
        });

        jLabel1.setText("BOOK ID");

        jLabel2.setText(" TITLE");

        jLabel3.setText("EDITION");

        bstatus.setEditable(false);
        bstatus.setText("available");
        bstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bstatusActionPerformed(evt);
            }
        });

        jLabel5.setText("STATUS");

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jButton1))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addComponent(bid)
                        .addComponent(btitle)
                        .addComponent(Bedition)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(214, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Bedition, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(0, 81, Short.MAX_VALUE))))
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

    private void BeditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BeditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BeditionActionPerformed

    private void bstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bstatusActionPerformed

    private void jtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtFilterKeyReleased
        findUsers();
    }//GEN-LAST:event_jtFilterKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
       int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    
    this.bid.setText(model.getValueAt(i, 0).toString());
    
    this.btitle.setText(model.getValueAt(i, 1).toString());
    
    this.Bedition.setText(model.getValueAt(i, 2).toString());
    
    this.bstatus.setText(model.getValueAt(i, 3).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseDragged
public void delete(){
    {
    String query = ("DELETE FROM `books` WHERE id ='" + this.bid.getText()+"'");
    
    executeSQlQuery(query, "Deleted");
  }
}
    public void update(){
    String sidi = this.bid.getText();
    String sfnamei = this.btitle.getText();
    String slnamei = this.Bedition.getText();
    if ((sidi.equals("")) || (sfnamei.equals("")) || (slnamei.equals("")))
    {
      JOptionPane.showMessageDialog(null, "make sure all fields are filed");
    }
    else
    {
      String query = "UPDATE `books` SET `title`='" + this.btitle.getText() + "',`ediion`='" + this.Bedition.getText() + "',`status`= '" + this.bstatus.getText() + "' WHERE `id` = '" + this.bid.getText() + "'";
      
      executeSQlQuery(query, "Updated");
    }
}
    public void insert(){
    String sidi = this.bid.getText();
    String sfnamei = this.btitle.getText();
    String slnamei = this.Bedition.getText();
    if ((sidi.equals("")) || (sfnamei.equals("")) || (slnamei.equals(""))) {
      JOptionPane.showMessageDialog(null, "make sure all fields are filed");
    } else {
      try
      {
        String stru = this.bid.getText().toString();
        methods m=new methods();
        Connection con = m.getConnection();
        String str = "";
        
        str = "select * from books where  id =?";
        
        PreparedStatement pst = con.prepareStatement(str);
        
        pst.setString(1, stru);
        
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
          JOptionPane.showMessageDialog(null, "A BOOK WITH SUCH an ID  (" + this.bid.getText() + ")  ALREADY EXIST \n " + " USE ANOTHER ID OR UPDATE THE BOOKS IN QUESTION");
        }
        else
        {
          String query = "INSERT INTO `books`(`id`, `title`, `ediion`,`status`) VALUES ('" + this.bid.getText() + "','" + this.btitle.getText() + "','" + this.Bedition.getText() + "','" + this.bstatus.getText() + "')";
          
          executeSQlQuery(query, "Inserted");
        }
        con.close();
        rs.close();
        pst.close();
      }
      catch (Exception a)
      {
        System.err.println(a);
      }
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
            java.util.logging.Logger.getLogger(AddUpdatenventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUpdatenventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUpdatenventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUpdatenventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUpdatenventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bedition;
    private javax.swing.JTextField bid;
    private javax.swing.JTextField bstatus;
    private javax.swing.JTextField btitle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtFilter;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
