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
import jxl.write.biff.File;

/**
 *
 * @author kimani kogi
 */
public class LOAN extends javax.swing.JFrame {
  DefaultTableModel model = new DefaultTableModel();
  String filePath;
  String fileurlp = null;
  String y;
  String tt;
    /**
     * Creates new form LOAN
     */
    public LOAN() {
        
       
     this.table = new JTable(this.model);
    this.jtFilter = new JTextField();
    final TableRowSorter<TableModel> rowSorter = new TableRowSorter(this.table.getModel());
    this.table.setRowSorter(rowSorter);
    this.jtFilter.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e)
      {
        String text = LOAN.this.jtFilter.getText();
        if (text.trim().length() == 0) {
          rowSorter.setRowFilter(null);
        } else {
          rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, new int[0]));
        }
      }
      
      public void removeUpdate(DocumentEvent e)
      {
        String text = LOAN.this.jtFilter.getText();
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
    methods n=new methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
    this.bid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          LOAN.this.select11();
        }
        catch (Exception ex)
        {
          Logger.getLogger(LOAN.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    this.sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
          LOAN.this.select();
          LOAN.this.showimg();
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
        methods m=new methods();
       // Connection con = m.getConnection();
      Connection con = m.getConnection();
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
           img = ImageIO.read(new java.io.File(this.filePath));
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
   methods m=new methods();
        Connection con = m.getConnection();
      //Connection con = getConnection();
    Statement st2 = con.createStatement();
    
    ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
    while (res7.next()) {
      tt=(res7.getString("name"));
    }
    st2.close();
    res7.close();
    con.close();
  }
  
  public void select11()
    throws Exception
  {
      methods m=new methods();
        Connection con = m.getConnection();
    //Connection con = getConnection();
    Statement st2 = con.createStatement();
    Statement st1 = con.createStatement();
    Statement st0 = con.createStatement();
    ResultSet res7 = st2.executeQuery("SELECT title FROM books  WHERE id='" + this.bid.getText() + "'");
    ResultSet res8 = st0.executeQuery("SELECT ediion FROM books  WHERE id='" + this.bid.getText() + "' ");
    ResultSet res9 = st1.executeQuery("SELECT status FROM books  WHERE id='" + this.bid.getText() + "' ");
    while ((res7.next()) && (res8.next()) && (res9.next()))
    {
      this.btitle.setText(res7.getString("title"));
      this.Bedition.setText(res8.getString("ediion"));
      this.bstatus.setText(res9.getString("status"));
    }
    st2.close();
    st0.close();
    st1.close();
    res7.close();
    res8.close();
    res9.close();
    con.close();
  }
  
  public void select()
    throws Exception
  {
      methods m=new methods();
        Connection con = m.getConnection();
    //Connection con = getConnection();
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
      
      this.sid.setText("");
      this.sfname.setText("");
      this.slname.setText("");
      this.sform.setText("");
      this.sformc.setText("");
    }
    st2.close();
     st3.close();
    st0.close();
    st1.close();
    res7.close();
    res8.close();
    res9.close();
    res3.close();
    con.close();
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
     // Connection con = getConnection();
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
   methods m=new methods();
        Connection con = m.getConnection();
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
        iconl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        sfname = new javax.swing.JTextField();
        slname = new javax.swing.JTextField();
        eligible = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bid = new javax.swing.JTextField();
        btitle = new javax.swing.JTextField();
        Bedition = new javax.swing.JTextField();
        bstatus = new javax.swing.JTextField();
        loanbtn = new javax.swing.JButton();
        clearbtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtFilter = new javax.swing.JTextField();
        sform = new javax.swing.JTextField();
        sformc = new javax.swing.JTextField();

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

        iconl.setText("image");

        jLabel2.setText("ADM NO");

        jLabel3.setText("FIRST NAME");

        sfname.setEditable(false);

        slname.setEditable(false);

        eligible.setText("Eligibility");
        eligible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eligibleActionPerformed(evt);
            }
        });

        jLabel4.setText("MIDDLE NAME");

        btitle.setEditable(false);
        btitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btitleActionPerformed(evt);
            }
        });

        Bedition.setEditable(false);

        bstatus.setEditable(false);

        loanbtn.setText("loan");
        loanbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanbtnActionPerformed(evt);
            }
        });

        clearbtn.setText("clear");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        jLabel5.setText("BOOK ID");

        jLabel6.setText("TITLE");

        jLabel7.setText("EDITION");

        jLabel8.setText("STATUS");

        jtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtFilterKeyReleased(evt);
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
                        .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bid)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(eligible)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(sformc))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(slname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Bedition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(clearbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loanbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 411, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(slname, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sformc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(eligible)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bedition, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearbtn)
                    .addComponent(loanbtn))
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eligibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eligibleActionPerformed
        String si = this.sid.getText();
        String hi = this.sfname.getText();
        String bi = this.slname.getText();
        if ((bi.equals("")) || (hi.equals("")) || (si.equals(""))) {
            JOptionPane.showMessageDialog(null, "enter students details");
        } else {
            try
            {
                String t = "";

               methods m=new methods();
        Connection con = m.getConnection();
                Statement st2 = con.createStatement();
                Statement st0 = con.createStatement();
                ResultSet res7 = st2.executeQuery("SELECT fname FROM loaned  WHERE sid=" + this.sid.getText() + "");
                ResultSet res8 = st0.executeQuery("SELECT SUM(no) FROM loaned  WHERE sid=" + this.sid.getText() + "");
                if ((res7.next()) && (res8.next()))
                {
                    String b = res7.getString("fname");

                    int c = res8.getInt(1);

                    double f = c;
                    if (f >= 1.0D) {
                        JOptionPane.showMessageDialog(null, " " + b + "  has " + c + " books not returned");
                    } else if (f == 0.0D) {
                        JOptionPane.showMessageDialog(null, "Student is Eligible");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Student is Eligible");
                }
                st2.close();
                st0.close();
                res7.close();
                res8.close();
                con.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(LOAN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_eligibleActionPerformed

    private void btitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btitleActionPerformed

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
        //this.btitle.setText("");
        
    }//GEN-LAST:event_clearbtnActionPerformed

    private void loanbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanbtnActionPerformed
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
                   methods m=new methods();
        Connection connection = m.getConnection();
                    int b = 1;
                    String query = "INSERT INTO `loaned`(`no`, `bid`, `sid`,`fname`,`lname`,`title`,`updated_at`,`form`,`class`,`edition`) VALUES (" + b + ",'" + this.bid.getText() + "','" + this.sid.getText() + "','" + this.sfname.getText() + "','" + this.slname.getText() + "','" + this.btitle.getText() + "',now(),'" + this.y + "','" + this.sformc.getText() + "', '" + this.Bedition.getText() + "')";

                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, " " + a + "  book id  " + c + "  \n loaned to " + f + " " + d + " \n on " + timeStamp + "");
                    pst.close();
                    connection.close();
                    updatebooks();
                }
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(null, "error/n"
                + "BOOK NOT loaned");
                System.err.println(e);
            }
           
        }
        findUsers();
    }//GEN-LAST:event_loanbtnActionPerformed
public void updatebooks(){
                try
            {

                String x = "not availabe";
                methods m=new methods();
        Connection connection = m.getConnection();
                String sql = "UPDATE books SET status = '" + x + "'  WHERE id='" + this.bid.getText() + "'";

                PreparedStatement pst = connection.prepareStatement(sql);
                pst.executeUpdate(sql);
                pst.close();
                connection.close();
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
            java.util.logging.Logger.getLogger(LOAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LOAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LOAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LOAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOAN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bedition;
    private javax.swing.JTextField bid;
    private javax.swing.JTextField bstatus;
    private javax.swing.JTextField btitle;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton eligible;
    private javax.swing.JLabel iconl;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
