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
import java.text.DateFormat;
import java.text.ParseException;
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
public class returnncourse extends javax.swing.JFrame {
  DefaultTableModel model = new DefaultTableModel();
  String v;
   String tt;
  String names;
  int days;
  int perday;
  String filePath;
  String fileurlp = null;
  String nwr;
    /**
     * Creates new form returnncourse
     */
    public returnncourse() {
this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = returnncourse.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = returnncourse.this.jtFilter.getText();
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
      Logger.getLogger(returnncourse.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
        this.setTitle(tt);
    try
    {
      Show_Users_In_JTable4();
    }
    catch (ParseException ex)
    {
      Logger.getLogger(returnncourse.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          returnncourse.this.select();
          returnncourse.this.showimg();
        }
        catch (Exception ex)
        {
          Logger.getLogger(returnncourse.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    this.bid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          returnncourse.this.select1();
          returnncourse.this.calcdate();
        }
        catch (Exception ex)
        {
          Logger.getLogger(returnncourse.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(null, "error loading image  for ADM NO  " + this.sid.getText() + " \n  " + "make sure image for ADM NO  " + this.sid.getText() + "  is in images folder ");
            
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
        JOptionPane.showMessageDialog(null, "error loading image for ADM NO  " + this.sid.getText() + "\n  " + "make sure image for ADM NO  " + this.sid.getText() + "  is in images folder ");
        
        this.iconl.setIcon(null);
        this.iconl.setText(" no image");
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
    Statement st0 = con.createStatement();
    Statement st3 = con.createStatement();
    ResultSet res7 = st2.executeQuery("SELECT fname FROM loanedcourse  WHERE sid=" + this.sid.getText() + "");
    ResultSet res8 = st0.executeQuery("SELECT lname FROM loanedcourse  WHERE sid=" + this.sid.getText() + " ");
     ResultSet res9 = st3.executeQuery("SELECT form FROM loanedcourse  WHERE sid=" + this.sid.getText() + " ");
    if ((res7.next()) && (res8.next())&& (res9.next()))
    {
      this.sfname.setText(res7.getString("fname"));
      this.slname.setText(res8.getString("lname"));
      this.sform.setText(res9.getString("form"));
    }
    else
    {
      JOptionPane.showMessageDialog(null, "error ADM NO  " + this.sid.getText() + " this student  is not in  loaned RECORDS \n  ");
       this.sform.setText("");
      this.sfname.setText("");
      this.slname.setText("");
    }
    st2.close();
    st0.close();
    res7.close();
    res8.close();
    con.close();
  }
  
  public void select1()
    throws Exception
  {
    Connection con = getConnection();
    Statement st2 = con.createStatement();
    Statement st0 = con.createStatement();
    ResultSet res7 = st2.executeQuery("SELECT title FROM loaned  WHERE bid=" + this.bid.getText() + "");
    ResultSet res8 = st0.executeQuery("SELECT edition FROM loaned  WHERE bid=" + this.bid.getText() + " ");
    while ((res7.next()) && (res8.next()))
    {
      this.btitle.setText(res7.getString("title"));
      this.Bedition.setText(res8.getString("edition"));
    }
    st2.close();
    st0.close();
    res7.close();
    res8.close();
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
    public void executeSQlQuery1(String query, String message)
  {
    Connection con = getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
      //  findUsers();
        
        
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
  
  public ArrayList<prefrence> getUsersList4()
    throws ParseException
  {
    ArrayList<prefrence> usersList4 = new ArrayList();
    
    Connection con = getConnection();
    
    String query4 = "SELECT * FROM `libprefrence` ";
    try
    {
      Statement st4 = con.createStatement();
      
      ResultSet rs4 = st4.executeQuery(query4);
      while (rs4.next())
      {
        prefrence user = new prefrence(rs4.getString("name"), rs4.getInt("days"), rs4.getInt("perday"), rs4.getString("pdfs"));
        
        usersList4.add(user);
      }
      st4.close();
      rs4.close();
      
      con.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return usersList4;
  }
  
  public void Show_Users_In_JTable4()
    throws ParseException
  {
    ArrayList<prefrence> list4 = getUsersList4();
    for (int i = 0; i < list4.size(); i++)
    {
      this.names = ((prefrence)list4.get(i)).getNames();
      
      this.days = ((prefrence)list4.get(i)).getDays();
      
      this.perday = ((prefrence)list4.get(i)).getPerday();
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
        sid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        iconl = new javax.swing.JLabel();
        sfname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        slname = new javax.swing.JTextField();
        bid = new javax.swing.JTextField();
        btitle = new javax.swing.JTextField();
        Bedition = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        sform = new javax.swing.JTextField();

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

        jtFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFilterMouseClicked(evt);
            }
        });
        jtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtFilterKeyReleased(evt);
            }
        });

        jLabel1.setText("ADM NO");

        iconl.setText("image");

        sfname.setEditable(false);

        jLabel3.setText("FIRST NAME");

        slname.setEditable(false);

        btitle.setEditable(false);

        Bedition.setEditable(false);

        jLabel4.setText("LAST NAME");

        jLabel5.setText("BOOK ID");

        jLabel6.setText("TITLE");

        jLabel7.setText("EDITION");

        jButton1.setText("RETURN NEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("RETURN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        sform.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(6, 6, 6))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Bedition, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(btitle)
                                .addComponent(bid)
                                .addComponent(slname)
                                .addComponent(sfname)
                                .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(36, 36, 36)
                        .addComponent(jButton3)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(slname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btitle, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bedition, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(0, 59, Short.MAX_VALUE))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
deltloaned();
           
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    deltloaned1();

    }//GEN-LAST:event_jButton1ActionPerformed
public void newbook(){
        String q = "available";
    try
    {
      String stru = this.bid.getText();
      
      
      Connection con = getConnection();
      String str = "";
      
      str = "select * from books where  id =?";
      
      PreparedStatement pst = con.prepareStatement(str);
      
      pst.setString(1, stru);
      
      ResultSet rs = pst.executeQuery();
      if (rs.next())
      {
        JOptionPane.showMessageDialog(null, "A BOOK WITH SUCH an ID  (" + this.bid.getText() + ")  ALREADY EXIST \n " + " USE ANOTHER ID OR UPDATE THE BOOKS IN QUESTION\n"
                + " Bokk Not Inserted try again");
      }
      else
      {
        String query = "INSERT INTO `books`(`id`, `title`, `ediion`,`status`) VALUES ('" + this.bid.getText() + "','" + this.btitle.getText() + "','" + this.Bedition.getText() + "','" + q + "')";
        
        executeSQlQuery(query, "Inserted");
      }
      rs.close();
      pst.close();
      con.close();
      
    }
    catch (Exception a)
    {
      JOptionPane.showMessageDialog(null, "error");
      System.err.println(a);
    }
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFilterMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtFilterMouseClicked

    private void jtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtFilterKeyReleased
       findUsers();
    }//GEN-LAST:event_jtFilterKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    
    this.bid.setText(model.getValueAt(i, 1).toString());
    this.Bedition.setText(model.getValueAt(i, 3).toString());
    this.btitle.setText(model.getValueAt(i, 2).toString());
     nwr = this.bid.getText();
    this.sid.setText(model.getValueAt(i, 0).toString());
      try {
          showimg();
      } catch (Exception ex) {
          Logger.getLogger(returnncourse.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_tableMouseClicked
public void deltloaned()
  {
    try
    {
      String query = "DELETE FROM `loanedcourse` WHERE bid = '" + this.bid.getText() + "' AND sid=" + this.sid.getText();
      executeSQlQuery(query, "Deleted");
      update();
    }
    catch (Exception e)
    {
      System.err.println(e);
      JOptionPane.showMessageDialog(null, "error WAS THIS BOOK GIVEN AS CLASS BOOK OR LOANED ");
    }
  }
  public void deltloaned1()
  {
    try
    {
      String query = "DELETE FROM `loanedcourse` WHERE bid = '" +nwr + "' AND sid=" + this.sid.getText();
      executeSQlQuery1(query, "Deleted");
      newbook();
    }
    catch (Exception e)
    {
      System.err.println(e);
      JOptionPane.showMessageDialog(null, "error WAS THIS BOOK GIVEN AS CLASS BOOK OR LOANED ");
    }
  }
  
  public void fine()
  {
    try
    {
      Connection con = getConnection();
      
      con.close();
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
  
  public void clear()
  {
    this.sid.setText("");
    this.bid.setText("");
    this.sfname.setText("");
    this.slname.setText("");
    this.btitle.setText("");
    this.Bedition.setText("");
     this.iconl.setText("");
    this.iconl.setIcon(null);
    this.iconl.setText("image");
  }
  
  public void calcdate()
  {
    Connection con = getConnection();
    try
    {
      Statement st3 = con.createStatement();
      ResultSet res9 = st3.executeQuery("SELECT updated_at FROM loaned  WHERE bid='" + this.bid.getText() + " '");
      if (res9.next())
      {
        String r = res9.getString("updated_at");
        DateFormat dueFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String f = dueFormat.format(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try
        {
          d1 = format.parse(f);
          
          d2 = format.parse(r);
          long diff = d1.getTime() - d2.getTime();
          long diffSeconds = diff / 1000L % 60L;
          long diffMinutes = diff / 60000L % 60L;
          long diffHours = diff / 3600000L % 24L;
          long diffDays = diff / 86400000L;
          if (diffDays >= 10L)
          {
            int sd = (int)(diffDays - this.days);
            int u = sd * this.perday;
            v = Integer.toString(u);
          }
        }
        catch (Exception ex)
        {
          String v;
          ex.printStackTrace();
        }
      }
      res9.close();
      st3.close();
      con.close();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(returnn.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void update()
  {
    try
    {
      String v = "available";
      String query = "UPDATE books SET status = '" + v + "'  WHERE id='" + this.bid.getText() + "'";
      executeSQlQuery(query, "updated");
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
    clear();
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
            java.util.logging.Logger.getLogger(returnncourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(returnncourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(returnncourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(returnncourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new returnncourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bedition;
    private javax.swing.JTextField bid;
    private javax.swing.JTextField btitle;
    private javax.swing.JLabel iconl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtFilter;
    private javax.swing.JTextField sfname;
    private javax.swing.JTextField sform;
    private javax.swing.JTextField sid;
    private javax.swing.JTextField slname;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
