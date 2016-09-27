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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class LOANcourse extends javax.swing.JFrame {
DefaultTableModel model = new DefaultTableModel();
 String y;
  String filePath;
   String tt;
  String fileurlp = null;
    /**
     * Creates new form LOANcourse
     */
    public LOANcourse(){
    this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = LOANcourse.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = LOANcourse.this.jtFilter.getText();
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
    this.sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          LOANcourse.this.select();
          LOANcourse.this.showimg();
        }
        catch (Exception ex)
        {
          Logger.getLogger(LOAN.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
  }
  
  public void showimg()
    throws Exception
  {
    this.iconl.setIcon(null);
    this.iconl.setText(" no image");
    try
    {
      Connection con = getConnection();
      Statement st2 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT imgurl FROM students  WHERE id=" + this.sid.getText() + "");
      if (res7.next())
      {
        this.filePath = res7.getString("imgurl");
        
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
      else
      {
        JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
      }
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
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
  
  public void select()
    throws Exception
  {
    Connection con = getConnection();
    Statement st2 = con.createStatement();
    Statement st1 = con.createStatement();
    Statement st0 = con.createStatement();
    Statement st3 = con.createStatement();
    ResultSet res7 = st2.executeQuery("SELECT fname FROM students  WHERE id=" + this.sid.getText() + "");
    ResultSet res8 = st0.executeQuery("SELECT lastname FROM students  WHERE id=" + this.sid.getText() + " ");
    ResultSet res9 = st1.executeQuery("SELECT form FROM students  WHERE id=" + this.sid.getText() + " ");
     ResultSet res3 = st3.executeQuery("SELECT class FROM students  WHERE id=" + this.sid.getText() + " ");
    if ((res7.next()) && (res8.next()) && (res9.next())&& (res3.next()))
    {
      this.sfname.setText(res7.getString("fname"));
      this.slname.setText(res8.getString("lastname"));
      this.sform.setText(res9.getString("form"));
       this.sformc.setText(res3.getString("class"));
      this.y = res9.getString("form");
    }
    else
    {
      JOptionPane.showMessageDialog(null, "error student \n  make sure student is in the database ");
      this.sform.setText("");
      this.sformc.setText("");
      this.sid.setText("");
      this.sfname.setText("");
      this.slname.setText("");
    }
    st2.close();
    st0.close();
     st3.close();
    st1.close();
    res7.close();
    res8.close();
    res9.close();
    res3.close();
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
  
  public ArrayList<db> ListUsers(String ValToSearch)
  {
    ArrayList<db> usersList = new ArrayList();
    try
    {
      Connection con = getConnection();
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
  
  public void loan(String query, String message)
  {
    Connection con = getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1) {
        JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jtFilter = new javax.swing.JTextField();
        iconl = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        sfname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        slname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btitle = new javax.swing.JTextField();
        Bedition = new javax.swing.JTextField();
        bstatus = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        clearbtn = new javax.swing.JButton();
        loanbtn = new javax.swing.JButton();
        sform = new javax.swing.JTextField();
        sformc = new javax.swing.JTextField();

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

        iconl.setText("image");

        jLabel2.setText("ADM NO");

        sfname.setEditable(false);

        jLabel3.setText("FIRST NAME");

        slname.setEditable(false);

        jLabel4.setText("LAST NAME");

        jLabel5.setText("BOOK");

        btitle.setEditable(false);

        Bedition.setEditable(false);

        bstatus.setEditable(false);

        jLabel6.setText("BOOK ID");

        jLabel7.setText("TITLE");

        jLabel8.setText("EDITION");

        jLabel9.setText("STATUS");

        clearbtn.setText("CLEAR");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        loanbtn.setText("GIVE");
        loanbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanbtnActionPerformed(evt);
            }
        });

        sform.setEditable(false);

        sformc.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bstatus)
                                    .addComponent(Bedition)
                                    .addComponent(btitle)
                                    .addComponent(slname)
                                    .addComponent(sfname)
                                    .addComponent(bid)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sformc, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(85, 85, 85))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clearbtn)
                                .addGap(62, 62, 62)
                                .addComponent(loanbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(slname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sformc)
                            .addComponent(sform, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bedition, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loanbtn)
                            .addComponent(clearbtn))
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
       int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    
    this.bid.setText(model.getValueAt(i, 0).toString());
    
    this.btitle.setText(model.getValueAt(i, 1).toString());
    
    this.Bedition.setText(model.getValueAt(i, 2).toString());
    
    this.bstatus.setText(model.getValueAt(i, 3).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void jtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtFilterKeyReleased
        
    findUsers();
    }//GEN-LAST:event_jtFilterKeyReleased

    private void loanbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanbtnActionPerformed
           eligible();
    findUsers();
    }//GEN-LAST:event_loanbtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed
      this.sid.setText("");
    this.sfname.setText("");
    this.slname.setText("");
    this.sform.setText("");
    this.sformc.setText("");
        this.bid.setText("");
    this.Bedition.setText("");
    this.bstatus.setText("");
    this.btitle.setText("");
     this.iconl.setText("");
    this.iconl.setIcon(null);
    this.iconl.setText("image");
    }//GEN-LAST:event_clearbtnActionPerformed
  public void eligible()
  {
    String si = this.sid.getText();
    String hi = this.sfname.getText();
    String bi = this.slname.getText();
    String w = this.bid.getText();
    String x = this.btitle.getText();
    if ((bi.equals("")) || (hi.equals("")) || (si.equals("")) || (w.equals("")) || (x.equals(""))) {
      JOptionPane.showMessageDialog(null, "enter students details");
    } else {
      try
      {
        String t = "";
        
        Connection con = getConnection();
        
        Statement st3 = con.createStatement();
        
        ResultSet res9 = st3.executeQuery("SELECT title FROM loanedcourse  WHERE title='" + this.btitle.getText() + "' AND sid='" + this.sid.getText() + "'");
        if (res9.next())
        {
          String c = res9.getString("title");
          
          String b = this.sfname.getText();
          
          JOptionPane.showMessageDialog(null, " " + b + "  has a " + c + " book not returned");
        }
        else
        {
          loan();
        }
        st3.close();
        
        res9.close();
        
        con.close();
      }
      catch (SQLException ex)
      {
        Logger.getLogger(LOAN.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
    public void loan()
  {
    String t = this.sid.getText();
    String lo = this.sfname.getText();
    String ho = this.slname.getText();
    if (((lo.equals("")) && (t.equals(""))) || (ho.equals("")) || (lo.equals("")) || (t.equals("")))
    {
      JOptionPane.showMessageDialog(null, "no reciepient");
    }
    else
    {
      String time = "now(),";
      String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
      
      String f = this.sfname.getText();
      String d = this.slname.getText();
      String c = this.bid.getText();
      String a = this.btitle.getText();
      String h = this.bstatus.getText().toString();
      String n = "not availabe";
      try
      {
        if (h.equals(n))
        {
          JOptionPane.showMessageDialog(null, "BOOK NOT AVAILABLE");
          this.bid.setText("");
          this.Bedition.setText("");
          this.bstatus.setText("");
          this.btitle.setText("");
        }
        else
        {
          Connection connection = getConnection();
          int b = 1;
          String query = "INSERT INTO `loanedcourse`(`no`, `bid`, `sid`,`fname`,`lname`,`title`,`updated_at`,`form`,`class`,`edition`) VALUES (" + b + ",'" + this.bid.getText() + "','" + this.sid.getText() + "','" + this.sfname.getText() + "','" + this.slname.getText() + "','" + this.btitle.getText() + "',now(),'" + this.y + "','" + this.sformc.getText() + "', '" + this.Bedition.getText() + "')";
          
          PreparedStatement pst = connection.prepareStatement(query);
          pst.executeUpdate(query);
          JOptionPane.showMessageDialog(null, " " + a + "  book id  " + c + "  \n loaned to " + f + " " + d + " \n on " + timeStamp + "");
          pst.close();
          connection.close();
          updatebook();
        }
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, "error/n"
                + "BOOK NOT loaned");
        System.err.println(e);
      }

    }
  }
    public void updatebook(){
          try
      {
        String x = "not availabe";
        Connection connection = getConnection();
        
        String sql = "UPDATE books SET status = '" + x + "'  WHERE id='" + this.bid.getText() + "'";
        
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.executeUpdate(sql);
        pst.close();
        connection.close();
        findUsers();
      }
      catch (Exception e)
      {
        System.err.println(e);
        JOptionPane.showMessageDialog(null, " error!!!/n"
                + "BOOK NOT UPDATED TO AVAILABLE ");
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
            java.util.logging.Logger.getLogger(LOANcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LOANcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LOANcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LOANcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOANcourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bedition;
    private javax.swing.JTextField bid;
    private javax.swing.JTextField bstatus;
    private javax.swing.JTextField btitle;
    private javax.swing.JButton clearbtn;
    private javax.swing.JLabel iconl;
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
    private javax.swing.JButton loanbtn;
    private javax.swing.JTextField sfname;
    private javax.swing.JTextField sform;
    private javax.swing.JTextField sformc;
    private javax.swing.JTextField sid;
    private javax.swing.JTextField slname;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
