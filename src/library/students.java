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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author kimani kogi
 */
public class students extends javax.swing.JFrame {
 DefaultTableModel model = new DefaultTableModel();
  String filePath;
  String fileurlp = null;
  String tt;
    /**
     * Creates new form students
     */
    public students() {
    this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = students.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = students.this.jtFilter.getText();
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
      Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
    }
     this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
        this.setTitle(tt);
    this.sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
sfname.requestFocus();
      }
    });
    this.sfname.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
     slname.requestFocus();
      }
    });
    this.slname.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
     sform.requestFocus();
     
      }
    });
     this.sform.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
     sclass.requestFocus();
     
      }
    });
      this.sclass.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
    insert();
     
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
  
  public ArrayList<addstudent> ListUsers(String ValToSearch)
  {
    ArrayList<addstudent> usersList = new ArrayList();
    try
    {
      Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `students` WHERE CONCAT(`id`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        addstudent user = new addstudent(rs.getInt("id"), rs.getString("fname"), rs.getString("lastname"), rs.getString("form"),rs.getString("class"));
        
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
    ArrayList<addstudent> users = ListUsers(this.jtFilter.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { " STUDENT ID", "F NAME", "L NAME", "FORM", "CLASS" });
    Object[] row = new Object[5];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = Integer.valueOf(((addstudent)users.get(i)).getId());
      row[1] = ((addstudent)users.get(i)).getFname();
      row[2] = ((addstudent)users.get(i)).getLname();
      
      row[3] = ((addstudent)users.get(i)).getForm();
      row[4] = ((addstudent)users.get(i)).getSformc();
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
  
  public void executeSQlQuery(String query, String message)
  {
    Connection con = getConnection();
    try
    {
      try
      {
        Statement st = con.createStatement();
        if (st.executeUpdate(query) == 1)
        {
          DefaultTableModel model = (DefaultTableModel)this.table.getModel();
          
          model.setRowCount(0);
          
          findUsers();
          
          JOptionPane.showMessageDialog(null, "STUDENT " + message + " Succefully");
        }
        else
        {
          JOptionPane.showMessageDialog(null, "STUDENT " + message);
        }
        st.close();
        con.close();
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    catch (Exception c)
    {
      System.err.println();
      System.exit(1);
    }
  }
  
  public void executeSQlQuery1(String query, String message)
  {
    Connection con = getConnection();
    try
    {
      try
      {
        Statement st = con.createStatement();
        if (st.executeUpdate(query) == 1)
        {
          DefaultTableModel model = (DefaultTableModel)this.table.getModel();
          
          model.setRowCount(0);
          
          findUsers();
        }
        st.close();
        con.close();
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    catch (Exception c)
    {
      System.err.println();
      System.exit(1);
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
        sfname = new javax.swing.JTextField();
        slname = new javax.swing.JTextField();
        sform = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        iconl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sclass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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

        slname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slnameActionPerformed(evt);
            }
        });

        sform.setToolTipText("");
        sform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sformActionPerformed(evt);
            }
        });

        jLabel1.setText("FIRST NAME");

        iconl.setText("image");

        jButton1.setText("CHOOSE PHOTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("ADM NO");

        jLabel4.setText("LAST NAME");

        jLabel5.setText("FORM");

        sclass.setToolTipText("integer only|||||");

        jLabel6.setText("CLASS");

        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ADD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("CLEAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sclass, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slname, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel3))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel1))))))
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                    .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(105, 105, 105)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(slname, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sclass, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void slnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slnameActionPerformed

    private void sformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sformActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sformActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
          int i = this.table.getSelectedRow();
    
    TableModel model = this.table.getModel();
    
    this.sid.setText(model.getValueAt(i, 0).toString());
    
    this.sfname.setText(model.getValueAt(i, 1).toString());
    
    this.slname.setText(model.getValueAt(i, 2).toString());
    
    this.sform.setText(model.getValueAt(i, 3).toString());
    try
    {
      showimg();
    }
    catch (Exception ex)
    {
      Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sidi = this.sid.getText();
    String sfnamei = this.sfname.getText();
    String slnamei = this.slname.getText();
    String sformi = this.sform.getText();
    if ((sidi.equals("")) || (sfnamei.equals("")) || (slnamei.equals("")) || (sformi.equals("")))
    {
      JOptionPane.showMessageDialog(null, "make sure all fields are filed");
    }
    else
    {
      String query = "UPDATE `students` SET `fname`='" + this.sfname.getText() + "',`lastname`='" + this.slname.getText() + "',`form`='" + this.sform.getText() + "',`class`='" + this.sclass.getText() + "',`imgurl`='" + this.fileurlp + "' WHERE `id` = " + this.sid.getText();
      
      executeSQlQuery(query, "Updated");
      updateloanstudentdetails();
      updateloancoursestudentdetails();
      this.fileurlp = null;
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
insert();
    }//GEN-LAST:event_jButton3ActionPerformed
public  void insert(){
            String sidi = this.sid.getText();
    String sfnamei = this.sfname.getText();
    String slnamei = this.slname.getText();
    String sformi = this.sform.getText();
    String sformc = this.sclass.getText();
    if ((sidi.equals("")) || (sfnamei.equals("")) || (slnamei.equals("")) || (sformi.equals(""))|| (sformc.equals(""))) {
      JOptionPane.showMessageDialog(null, "make sure all fields are filed");
    } else {
      try
      {
        int stru = Integer.valueOf(this.sid.getText()).intValue();
        
        Connection con = getConnection();
        String str = "";
        
        str = "select * from students where  id =?";
        
        PreparedStatement pst = con.prepareStatement(str);
        
        pst.setInt(1, stru);
        
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
          JOptionPane.showMessageDialog(null, "A STUDENT WITH SUCH an ID  (" + this.sid.getText() + ")  ALREADY EXIST \n " + " USE ANOTHER ADM NO OR UPDATE THE STUDENT IN QUESTION");
        }
        else if (this.fileurlp == null)
        {
          String k = "image";
          String query = "INSERT INTO `students`(`id`,`fname`, `lastname`, `form`,`class`,`imgurl`) VALUES ('" + this.sid.getText() + "','" + this.sfname.getText() + "','" + this.slname.getText() + "','" + this.sform.getText() + "','" + this.sclass.getText() + "','" + k + "')";
          
          executeSQlQuery(query, "Inserted");
        }
        else
        {
          try
          {
            String query = "INSERT INTO `students`(`id`,`fname`, `lastname`, `form`,`class`,`imgurl`) VALUES ('" + this.sid.getText() + "','" + this.sfname.getText() + "','" + this.slname.getText() + "','" + this.sform.getText() + "','" + this.sclass.getText() + "','" + this.fileurlp + "')";
            
            executeSQlQuery(query, "Inserted");
            this.fileurlp = null;
          }
          catch (Exception e)
          {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");
          }
        }
        rs.close();
        pst.close();
        con.close();
      }
      catch (Exception a)
      {
        System.err.println(a);
      }
    }
}
    private void jtFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtFilterActionPerformed

    private void jtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtFilterKeyReleased
        findUsers();
    }//GEN-LAST:event_jtFilterKeyReleased

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         this.sid.setText("");
    this.sfname.setText("");
    this.slname.setText("");
    this.sform.setText("");
    this.sclass.setText("");
    this.iconl.setText("");
    this.iconl.setIcon(null);
    this.iconl.setText("image");
    }//GEN-LAST:event_jButton4ActionPerformed
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
  public void updateloanstudentdetails()
  {
    try
    {
      String stru = this.sid.getText();
      
      Connection connection = getConnection();
      String str = "";
      
      str = "select * from loaned where  sid =?";
      
      PreparedStatement pst = connection.prepareStatement(str);
      
      pst.setString(1, stru);
      
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        String query = "UPDATE `loaned` SET `fname`='" + this.sfname.getText() + "',`lname`='" + this.slname.getText() + "',`form`='" + this.sform.getText() + "',`class`='" + this.sclass.getText() + "' WHERE `sid` = " + this.sid.getText();
        executeSQlQuery1(query, "Updated");
      }
      connection.close();
      rs.close();      pst.close();

    }
    catch (SQLException ex)
    {
      Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void updateloancoursestudentdetails()
  {
    try
    {
      String stru = this.sid.getText();
      
      Connection connection = getConnection();
      String str = "";
      
      str = "select * from loanedcourse where  sid =?";
      
      PreparedStatement pst = connection.prepareStatement(str);
      
      pst.setString(1, stru);
      
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        String query = "UPDATE `loanedcourse` SET `fname`='" + this.sfname.getText() + "',`lname`='" + this.slname.getText() + "',`form`='" + this.sform.getText() + "',`class`='" + this.sclass.getText() + "' WHERE `sid` = " + this.sid.getText();
        executeSQlQuery1(query, "Updated");
      }
      connection.close();
      rs.close();
      pst.close();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(students.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new students().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtFilter;
    private javax.swing.JTextField sclass;
    private javax.swing.JTextField sfname;
    private javax.swing.JTextField sform;
    private javax.swing.JTextField sid;
    private javax.swing.JTextField slname;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
