/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kimani kogi
 */
public class methods {
    String tt;
   static String path;
   
   //  path = "localhost";
   // String h="\"jdbc:mysql://localhost:3306/library\", \"root\", \"123ERYcog.\"";
   
  String db=":3306/library";
  String jdbc="jdbc:mysql://";
  String user="root";
  String pass="123ERYcog.";
  String dbp=(jdbc+path+db);
  
   //  selectname();
   
//    public void nameimage(){
//         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
//        setTitle("ITHANGA SECONDARY SCHOOL");
//    }
//  public void setpdb(String url){
//      path=url;
//      dbpath n=new dbpath();
//     // r();
//  }
//   public void r(String h){
//    path=h;
//    dbpath n=new dbpath();
//    String query = "UPDATE `dbpath` SET `path`='" +h+ "'";
//    executeSQlQueryb(query, "Updated"); 
// }
//  public String getpdb(){
//      return path;
//  }
  public void create(){
     Connection con = null;
    try
    {
        String url="jdbc:derby://myDB;create=true;user=me;password=mine";
        // selectdbpath();
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
      con = DriverManager.getConnection(dbp,user,pass);
     // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
}
  
    public void selectdbpath()
    throws Exception
  {
   
        Connection con = getConnection();
        Statement st2 = con.createStatement();
    
    ResultSet res7 = st2.executeQuery("SELECT path FROM dbpath  ");
    while (res7.next()) {
       path=(res7.getString("path"));
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
        // selectdbpath();
        
      con = DriverManager.getConnection(dbp,user,pass);
     // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123ERYcog.");
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return con;
  }
           public void executeSQlQueryb(String query, String message)
  {
    Connection con = getConnection();
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
      public void executeSQlQuery(String query, String message)
  {
    Connection con = getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
        //DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        //model.setRowCount(0);
        LOAN N=new LOAN();
        N.findUsers();
        
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

    public String setIconImage() {
       String image="academic.png";
       return image;
    }

    public String setTitle() {
        String title;
        try {
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
            // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
           
           
            //  setTitle(title);
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
         title=tt;
         return title;
    }
 public String selectnamei()
         
    
  {
      String title;
       String  tti = null;
        try {
           
            
            methods m=new methods();
            Connection con = m.getConnection();
            Statement st2 = con.createStatement();
            
            ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
            while (res7.next()) {
                tti=(res7.getString("name"));
                //schoolname.setText(tt);
                // return tti;
                title=tti;
            }
            st2.close();
            res7.close();
            con.close();
            // title=tti;
           // return title;
        } catch (SQLException ex) {
            Logger.getLogger(methods.class.getName()).log(Level.SEVERE, null, ex);
        }
       // tti="jina";
         title=tti;
        return title;
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

}
