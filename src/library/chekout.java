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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kimani kogi
 */
public class chekout extends javax.swing.JFrame {
DefaultTableModel model = new DefaultTableModel();
  String filePath;
  String fileurlp = null;
  int loanedbooks;
  int loanedcourse;
  String studentname;
  double notpaid;
  String tt;
    /**
     * Creates new form chekout
     */
    public chekout() {
 initComponents();
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
      Logger.getLogger(returnn.class.getName()).log(Level.SEVERE, null, ex);
    }
     methods n=new methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
        sid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent b)
      {
        try
        {
         checkdone();
        }
        catch (Exception ex)
        {
          Logger.getLogger(chekout.class.getName()).log(Level.SEVERE, null, ex);
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
  
  public ArrayList<returndb> ListUsers(String ValToSearch)
  {
    ArrayList<returndb> usersList = new ArrayList();
    try
    {
        methods m=new methods();
        Connection con = m.getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `loaned` WHERE CONCAT(`sid`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        returndb user = new returndb(rs.getString("bid"), rs.getString("edition"), rs.getString("fname"), rs.getString("lname"), rs.getInt("no"), rs.getString("sid"), rs.getString("title"), rs.getString("updated_at"));
        
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
    ArrayList<returndb> users = ListUsers(this.sid.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { " STUDENT ID", "BOOK ID", "TITLE", "EDITION", "Loaned ON" });
    Object[] row = new Object[5];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] =((returndb)users.get(i)).getSid();
      row[1] = ((returndb)users.get(i)).getBid();
      row[2] = ((returndb)users.get(i)).getTitle();
      row[3] = ((returndb)users.get(i)).getEdition();
      row[4] = ((returndb)users.get(i)).getUpdated_at();
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
  
  public ArrayList<returndb> ListUsers2(String ValToSearch)
  {
    ArrayList<returndb> usersList = new ArrayList();
    try
    {
      methods m=new methods();
        Connection con = m.getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `loanedcourse` WHERE CONCAT(`sid`) LIKE '%" + ValToSearch + "%'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        returndb user = new returndb(rs.getString("bid"), rs.getString("edition"), rs.getString("fname"), rs.getString("lname"), rs.getInt("no"), rs.getString("sid"), rs.getString("title"), rs.getString("updated_at"));
        
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
  
  public void findUsers2()
  {
    ArrayList<returndb> users = ListUsers2(this.sid.getText());
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "BOOK ID", "TITLE", "EDITION", "Loaned ON" });
    Object[] row = new Object[4];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((returndb)users.get(i)).getBid();
      row[1] = ((returndb)users.get(i)).getTitle();
      row[2] = ((returndb)users.get(i)).getEdition();
      row[3] = ((returndb)users.get(i)).getUpdated_at();
      
      model.addRow(row);
    }
    this.table2.setModel(model);
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
        table2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iconl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table2.setRowHeight(40);
        table2.setRowMargin(4);
        jScrollPane1.setViewportView(table2);

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
        jScrollPane2.setViewportView(table);

        jLabel1.setText("COURSE BOOKS");

        jLabel2.setText("LOANED");

        iconl.setText("image");

        jLabel4.setText("ADM NO");

        jButton1.setText("CHECK OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addComponent(iconl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(352, 352, 352)
                        .addComponent(jLabel1)
                        .addGap(195, 195, 195))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       checkdone();
    }//GEN-LAST:event_jButton1ActionPerformed
public void checkdone(){
    try
    {
      String si = this.sid.getText();
      if (si.equals(""))
      {
        JOptionPane.showMessageDialog(null, "enter students id");
      }
      else
      {
        methods m=new methods();
        Connection con = m.getConnection();
        Statement st2 = con.createStatement();
        
        ResultSet res7 = st2.executeQuery("SELECT fname FROM students  WHERE id='" + this.sid.getText() + "'");
        if (res7.next())
        {
          showimg();
          
          findUsers();
          findUsers2();
          
          try
          {
            selectsid();
            selectsid2();
            selectsid3();
            if (this.studentname == "null")
            {
              JOptionPane.showMessageDialog(null, " NO SUCH STUDENT");
            }
            else if ((this.loanedbooks == 0) && (this.loanedcourse == 0)&& (this.notpaid == 0))
            {
              delete();
              JOptionPane.showMessageDialog(null, "STUDENT CLEARED");
              this.iconl.setText("");
              this.fileurlp = null;
              
              this.iconl.setIcon(null);
            }
            else if ((this.loanedbooks != 0) && (this.loanedcourse != 0))
            {
                if(notpaid!=0){
                 JOptionPane.showMessageDialog(null, "" + this.studentname + "'   has     " + this.loanedbooks + "  book(s) borrowed \n" + "and  " + this.loanedcourse + " course book(s) Given \n"
                         + "And"+notpaid+" fine not paid " + "CANNOT BE CLEARED WITH UNRETURNED BOOK(S)");
                
                }
                else{
              JOptionPane.showMessageDialog(null, "" + this.studentname + "'   has     " + this.loanedbooks + "  book(s) borrowed \n" + "and  " + this.loanedcourse + " course book(s) Given \n" + "CANNOT BE CLEARED WITH UNRETURNED BOOK(S)");
            
                }
            }
            else if ((this.loanedbooks != 0) && (this.loanedcourse == 0))
            {
                               if(notpaid!=0){
JOptionPane.showMessageDialog(null, "" + this.studentname + "'   has     " + this.loanedbooks + "  book(s) borrowed \n"
        + "And"+notpaid+" ksh fine not paid " + "CANNOT BE CLEARED WITH UNRETURNED BOOK(S)");
            
                
                } 
                               else{
              JOptionPane.showMessageDialog(null, "" + this.studentname + "'   has     " + this.loanedbooks + "  book(s) borrowed \n" + "CANNOT BE CLEARED WITH UNRETURNED BOOK(S)");
                               }
                               }
            else if ((this.loanedbooks == 0) && (this.loanedcourse != 0))
            {
                                              if(notpaid!=0){
 JOptionPane.showMessageDialog(null, "" + this.studentname + "'   has     " + this.loanedcourse + " course book(s) given \n"
         + " And"+notpaid+" ksh fine not paid ../n"
         + "CANNOT BE CLEARED WITH UNRETURNED BOOK(S)");
            
                
                }
                                              else{
                
                
              JOptionPane.showMessageDialog(null, "" + this.studentname + "'   has     " + this.loanedcourse + " course book(s) given \n" + "CANNOT BE CLEARED WITH UNRETURNED BOOK(S)");
                                              }
                                              }
          }
          catch (Exception ex)
          {
            Logger.getLogger(chekout.class.getName()).log(Level.SEVERE, null, ex);
          }
          st2.close();
          
          res7.close();
          
          con.close();
        }
        else
        {
          JOptionPane.showMessageDialog(null, "NO Such student ");
        }
      }
    }
    catch (Exception ex)
    {
      Logger.getLogger(chekout.class.getName()).log(Level.SEVERE, null, ex);
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
      methods m=new methods();
        Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      Statement st0 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT fname FROM students  WHERE id='" + this.sid.getText() + "'");
      ResultSet res8 = st0.executeQuery("SELECT SUM(no) FROM loaned  WHERE sid='" + this.sid.getText() + "'");
      if ((res7.next()) && (res8.next()))
      {
        this.loanedbooks = res8.getInt(1);
        this.studentname = res7.getString("fname");
        
        st2.close();
        st0.close();
        
        res7.close();
        res8.close();
        
        con.close();
      }
      else
      {
        this.studentname = "null";
        this.loanedbooks = 0;
      }
    }
  }
  
  public void selectsid2()
    throws Exception
  {
    String si = this.sid.getText();
    if (si.equals(""))
    {
      JOptionPane.showMessageDialog(null, "enter students id");
    }
    else
    {
     methods m=new methods();
        Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      Statement st0 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT fname FROM students  WHERE id='" + this.sid.getText() + "'");
      ResultSet res8 = st0.executeQuery("SELECT SUM(no) FROM loanedcourse  WHERE sid='" + this.sid.getText() + "'");
      if ((res7.next()) && (res8.next()))
      {
        this.loanedcourse = res8.getInt(1);
        
        st2.close();
        st0.close();
        
        res7.close();
        res8.close();
        
        con.close();
      }
      else
      {
        this.loanedcourse = 0;
      }
    }
  }
  public void selectsid3()
    throws Exception
  {
    String si = this.sid.getText();
    if (si.equals(""))
    {
      JOptionPane.showMessageDialog(null, "enter students id");
    }
    else
    {
        String np="not paid";
      methods m=new methods();
        Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      Statement st0 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT fname FROM students  WHERE id='" + this.sid.getText() + "'");
      ResultSet res8 = st0.executeQuery("SELECT SUM(amount) FROM libfines  WHERE id='" + this.sid.getText() +"'AND status='"+np+"'");
      if ((res7.next()) && (res8.next()))
      {
        this.notpaid = res8.getDouble(1);
        this.studentname = res7.getString("fname");
        
        st2.close();
        st0.close();
        
        res7.close();
        res8.close();
        
        con.close();
      }
      else
      {
        this.studentname = "null";
        this.notpaid = 0;
      }
    }
  }
  
  public void delete()
  {
    try
    {
      int a = 4;
      int a1 = 5;
      
      methods m=new methods();
        Connection connection = m.getConnection();
      
      String sql = "DELETE FROM `students` WHERE id = '" + this.sid.getText() + "'";
      
      PreparedStatement pst = connection.prepareStatement(sql);
      pst.executeUpdate(sql);
      
      pst.close();
      connection.close();
    }
    catch (Exception e)
    {
      System.err.println(e);
      JOptionPane.showMessageDialog(null, "error");
    }
  }
  
  public void showimg()
    throws Exception
  {
    this.iconl.setIcon(null);
    this.iconl.setText(" no image");
    try
    {
      methods m=new methods();
        Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT imgurl FROM students  WHERE id='" + this.sid.getText() + "'");
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
            java.util.logging.Logger.getLogger(chekout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chekout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chekout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chekout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chekout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField sid;
    private javax.swing.JTable table;
    private javax.swing.JTable table2;
    // End of variables declaration//GEN-END:variables
}
