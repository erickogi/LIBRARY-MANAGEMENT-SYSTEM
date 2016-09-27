/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
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
public class fines extends javax.swing.JFrame {
  DefaultTableModel model = new DefaultTableModel();
  
   String tt;
  
    /**
     * Creates new form fines
     */
    public fines() {
    this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = fines.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = fines.this.jtFilter.getText();
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
      Logger.getLogger(LOAN.class.getName()).log(Level.SEVERE, null, ex);
    }
     this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
        this.setTitle(tt);
    try
    {
      sum();
    }
    catch (Exception ex)
    {
      Logger.getLogger(fines.class.getName()).log(Level.SEVERE, null, ex);
    }
    try
    {
      sump();
    }
    catch (Exception ex)
    {
      Logger.getLogger(fines.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   
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
  public void sum()
    throws Exception
  {
    double sum = 0.0D;
    double sum1 = 0.0D;
    double sum2 = 0.0D;
    double sum3 = 0.0D;
    String np="not paid";
    Connection connection = getConnection();
    Statement st = connection.createStatement();
    Statement st3 = connection.createStatement();
    Statement st2 = connection.createStatement();
    Statement st1 = connection.createStatement();
    ResultSet res = st.executeQuery("SELECT SUM(amount) FROM libfines WHERE status='"+np+"'");
    ResultSet res1 = st1.executeQuery("SELECT SUM(amount) FROM libfines WHERE DATE(day)= DATE(NOW())AND status='"+np+"'");
    ResultSet res2 = st2.executeQuery("SELECT SUM(amount) FROM libfines WHERE  YEAR(day)=YEAR(CURDATE())AND status='"+np+"'");
    ResultSet res3 = st3.executeQuery("SELECT SUM(amount) FROM libfines WHERE MONTH(day)=MONTH(CURDATE()) AND YEAR(day)=YEAR(CURDATE())AND status='"+np+"'");
    while ((res.next()) && (res1.next()) && (res2.next()) && (res3.next()))
    {
      double c = res.getDouble(1);
      double c1 = res1.getDouble(1);
      double c2 = res2.getDouble(1);
      sum1 = c1;
      double c3 = res3.getDouble(1);
      sum3 = c3;
      sum = c;
      sum2 = c2;
    }
    this.ttoday.setText(Double.toString(sum1));
    this.tall.setText(Double.toString(sum));
    this.tyear.setText(Double.toString(sum2));
    this.tmonth.setText(Double.toString(sum3));
    st.close();
    st1.close();
    
    res.close();
    st2.close();
    res2.close();
    st3.close();
    res3.close();
    res1.close();
    connection.close();
  }
   public void sump()
    throws Exception
  {
    double sum = 0.0D;
    double sum1 = 0.0D;
    double sum2 = 0.0D;
    double sum3 = 0.0D;
    String np="paid";
    Connection connection = getConnection();
    Statement st = connection.createStatement();
    Statement st3 = connection.createStatement();
    Statement st2 = connection.createStatement();
    Statement st1 = connection.createStatement();
    ResultSet res = st.executeQuery("SELECT SUM(amount) FROM libfines WHERE status='"+np+"'");
    ResultSet res1 = st1.executeQuery("SELECT SUM(amount) FROM libfines WHERE DATE(day)= DATE(NOW())AND status='"+np+"'");
    ResultSet res2 = st2.executeQuery("SELECT SUM(amount) FROM libfines WHERE  YEAR(day)=YEAR(CURDATE())AND status='"+np+"'");
    ResultSet res3 = st3.executeQuery("SELECT SUM(amount) FROM libfines WHERE MONTH(day)=MONTH(CURDATE()) AND YEAR(day)=YEAR(CURDATE())AND status='"+np+"'");
    while ((res.next()) && (res1.next()) && (res2.next()) && (res3.next()))
    {
      double c = res.getDouble(1);
      double c1 = res1.getDouble(1);
      double c2 = res2.getDouble(1);
      sum1 = c1;
      double c3 = res3.getDouble(1);
      sum3 = c3;
      sum = c;
      sum2 = c2;
    }
    this.ptoday.setText(Double.toString(sum1));
    this.pall.setText(Double.toString(sum));
    this.pyear.setText(Double.toString(sum2));
    this.pmonth.setText(Double.toString(sum3));
    st.close();
    st1.close();
    
    res.close();
    st2.close();
    res2.close();
    st3.close();
    res3.close();
    res1.close();
    connection.close();
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
  
  public ArrayList<libfines> ListUsers(String ValToSearch)
  {
    ArrayList<libfines> usersList = new ArrayList();
    try
    {
      Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `libfines` WHERE CONCAT(`id`, `amount`, `day`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        libfines user = new libfines(rs.getInt("id"), rs.getString("amount"), rs.getString("day"), rs.getString("status"));
        
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
    ArrayList<libfines> users = ListUsers(this.jtFilter.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "STUDENT ID", "AMOUNT", "DATE", "STATUS" });
    Object[] row = new Object[4];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = Integer.valueOf(((libfines)users.get(i)).getId());
      row[1] = ((libfines)users.get(i)).getAmount();
      row[2] = ((libfines)users.get(i)).getDay();
      row[3] = ((libfines)users.get(i)).getStatus();
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
  
  public void executeSQlQuery(String query, String message)
  {
    Connection con = getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
        sum();
        sump();
        
        JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
      }
      else
      {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jtFilter = new javax.swing.JTextField();
        pyear = new javax.swing.JTextField();
        pmonth = new javax.swing.JTextField();
        ptoday = new javax.swing.JTextField();
        pall = new javax.swing.JTextField();
        sid = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        tall = new javax.swing.JTextField();
        tyear = new javax.swing.JTextField();
        tmonth = new javax.swing.JTextField();
        ttoday = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        statust = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jtFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtFilterActionPerformed(evt);
            }
        });
        jtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtFilterKeyReleased(evt);
            }
        });

        pyear.setEditable(false);

        pmonth.setEditable(false);

        ptoday.setEditable(false);

        pall.setEditable(false);
        pall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pallActionPerformed(evt);
            }
        });

        amount.setEditable(false);

        date.setEditable(false);

        jButton2.setText("PAID");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tall.setEditable(false);

        tyear.setEditable(false);

        tmonth.setEditable(false);

        ttoday.setEditable(false);

        jLabel1.setText("PAID");

        jLabel2.setText("TODAY");

        jLabel3.setText("MONTH");

        jLabel4.setText("YEAR");

        jLabel5.setText("ALL");

        jLabel6.setText("TODAY");

        jLabel7.setText("MONTH");

        jLabel8.setText("YEAR");

        jLabel9.setText("ALL");

        jLabel10.setText("NOT PAID");

        statust.setEditable(false);

        jLabel11.setText("ADM NO");

        jLabel12.setText("AMOUNT");

        jLabel13.setText("DATE");

        jLabel14.setText("STATUS");

        jButton3.setText("CLEAR");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(0, 0, 0))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ptoday, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                        .addComponent(ttoday))
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel7)
                                        .addComponent(pmonth, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                        .addComponent(tmonth)))
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tyear)
                                .addGap(18, 18, 18)
                                .addComponent(tall, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pyear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(59, 59, 59))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(45, 45, 45))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(pall))))
                            .addComponent(amount)
                            .addComponent(sid)
                            .addComponent(date)
                            .addComponent(statust)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(13, 13, 13)
                            .addComponent(jLabel2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addComponent(jtFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ptoday, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pyear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pall, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tall, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tyear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ttoday, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statust, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addContainerGap())))
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

    private void jtFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtFilterActionPerformed
        
    }//GEN-LAST:event_jtFilterActionPerformed

    private void pallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pallActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pallActionPerformed

    private void jtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtFilterKeyReleased
      findUsers();
    }//GEN-LAST:event_jtFilterKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
         int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    
    this.sid.setText(model.getValueAt(i, 0).toString());
    
    this.amount.setText(model.getValueAt(i, 1).toString());
    
    this.date.setText(model.getValueAt(i, 2).toString());
     this.statust.setText(model.getValueAt(i, 3).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String v = "paid";
      String query = "UPDATE libfines SET status = '"+v+"'  WHERE id='" +sid.getText() + "'AND day='"+date.getText()+"'";
      executeSQlQuery(query, "updated");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String query = "DELETE FROM `libfines` WHERE id ='" + this.sid.getText()+"' AND day='"+date.getText()+"'";
    
    executeSQlQuery(query, "Deleted");
    sid.setText("");
    date.setText("");
    amount.setText("");
    statust.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      sid.setText("");
    date.setText("");
    amount.setText("");
    statust.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(fines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fines().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JTextField date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtFilter;
    private javax.swing.JTextField pall;
    private javax.swing.JTextField pmonth;
    private javax.swing.JTextField ptoday;
    private javax.swing.JTextField pyear;
    private javax.swing.JTextField sid;
    private javax.swing.JTextField statust;
    private javax.swing.JTable table;
    private javax.swing.JTextField tall;
    private javax.swing.JTextField tmonth;
    private javax.swing.JTextField ttoday;
    private javax.swing.JTextField tyear;
    // End of variables declaration//GEN-END:variables
}
