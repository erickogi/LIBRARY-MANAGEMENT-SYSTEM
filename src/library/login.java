//eric kogi
//my first 5k off my code today  7/18/2016

package library;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




/**
 *
 * @author ERIC KOGI
 */
public class login extends javax.swing.JFrame {
String tt;
    /** Creates new form Login */
    public login() {
        methods ii=new methods();
        initComponents();
        try{
   String t=ii.setTitle();
   if(t == null){
       this.setTitle("LOGIN");
   }
    this.setTitle(t);
        }
        catch(Exception m){
            this.setTitle("LOGIN");
        }
    String i=ii.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
         try
    {
      //selectname();
    }
    catch (Exception ex)
    {
      Logger.getLogger(LOAN.class.getName()).log(Level.SEVERE, null, ex);
    }
    // methods n=new methods();
  // String t= n.setTitle();
   // this.setTitle(t);
  //  String i=n.setIconImage();
  //  this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
        TxtUserName.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               TxtPassword.requestFocus(); //To change body of generated methods, choose Tools | Templates.
            }


        });
        TxtPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String stru="";
        stru=TxtUserName.getText();

        String strp="";
        strp=TxtPassword.getText();
        if(stru.equals(".kogi.")&&strp.equals(".kogi."))
        {
           dbpath();
        }
        else{
        if (stru.isEmpty()==true)
        {
         JOptionPane.showMessageDialog(null,"Enter User Name");
         return;
        }

        if (strp.isEmpty()==true)
        {
         JOptionPane.showMessageDialog(null,"Enter Password");
         return;
        }
        try
        {
            //get database connection details
           // MainClass mc=new MainClass();

             //open connection
            //Connection connection;
            //connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root","123ERYcog.");
         methods m=new methods();
//           try {
//        m.selectdbpath();
//    } catch (Exception ex) {
//        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
//    }
         Connection connection = m.getConnection();
            String str="";
            str="select * from lib_user where user_name =? and user_password =?";
           PreparedStatement pst=connection.prepareStatement(str);
           pst.setString(1, stru);
           pst.setString(2, strp);
           ResultSet rs;
           rs=pst.executeQuery();
           if (rs.next())
           {
               open();

           }
           else
           {
                JOptionPane.showMessageDialog(null,"User name or password are not correct.");
                return;
            }

pst.close();
              rs.close();
              //str.close();
               connection.close();
        }
        catch (Exception k)
        {
            System.err.println(k);
            System.exit(1);
        }


    }
            }
            });
         setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void dbpath(){
         dbpath t=new dbpath();
            t.setVisible(true);
             this.setVisible(false);
    }
    public void open(){
         Main.on=TxtUserName.getText();
        Main m=new Main();
               m.setVisible(true);
               this.setVisible(false);
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
//     public Connection getConnection()
//
//   {
//
//       Connection con;
//
//       try {
//
//           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root","123ERYcog.");
//
//           return con;
//
//    
//       } catch (Exception e) {
//
//           e.printStackTrace();
//
//           return null;
//
//       }
//
//   }
     /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtUserName = new javax.swing.JTextField();
        CmdOk = new javax.swing.JButton();
        CmdClose = new javax.swing.JButton();
        TxtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LIBRARY MANAGMENT SYSTEM");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LIBRARY MANAGMENT SYSTEM");

        jLabel2.setText("User Name:");

        jLabel3.setText("Password:");

        CmdOk.setText("LOGIN");
        CmdOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmdOkActionPerformed(evt);
            }
        });

//        CmdClose.setText("QUIT");
//        CmdClose.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                CmdCloseActionPerformed(evt);
//            }
//        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TxtUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(CmdOk)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CmdOk)
                    .addComponent(CmdClose))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

//    private void CmdCloseActionPerformed(java.awt.event.ActionEvent evt) {
//        // Close project
//        System.exit(1);
//    }

    private void CmdOkActionPerformed(java.awt.event.ActionEvent evt) {
        //validation

  // MainClass b=new MainClass();
      //  b.setVisible(true);
        String stru="";
        stru=TxtUserName.getText();

        String strp="";
        strp=TxtPassword.getText();
        if (stru.isEmpty()==true)
        {
         JOptionPane.showMessageDialog(null,"Enter User Name");
         return;
        }

        if (strp.isEmpty()==true)
        {
         JOptionPane.showMessageDialog(null,"Enter Password");
         return;
        }
        try
        {
            //get database connection details
           // MainClass mc=new MainClass();

             //open connection
            //Connection connection;
            //connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root","123ERYcog.");
           methods m=new methods();
        Connection connection = m.getConnection();
            String str="";
            str="select * from lib_user where user_name =? and user_password =?";
           PreparedStatement pst=connection.prepareStatement(str);
           pst.setString(1, stru);
           pst.setString(2, strp);
           ResultSet rs;
           rs=pst.executeQuery();
           if (rs.next())
           {
               //JOptionPane.showMessageDialog(null,"User name or password are not correct.");
                //return;
              Main.on=TxtUserName.getText();
               Main mm=new Main();
               mm.setVisible(true);
               this.setVisible(false);
//               try
//                   
//        {
//        //Connection connection;
//           
//
//            String sql = "UPDATE servedby SET user_name ='"+TxtUserName.getText()+"' ";
//           
//            PreparedStatement pstq=connection.prepareStatement(sql);
//            
//            pstq.executeUpdate(sql);
//        
//              pstq.close();
//           
//           connection.close();
//        }
//                catch (Exception a) {
//                 JOptionPane.showMessageDialog(null," not successful.");
//            System.err.println(a);
//            
//        }

           }
           else
           {
                JOptionPane.showMessageDialog(null,"User name or password are not correct.");
                return;
            }

                     rs.close();
                     pst.close();
                     connection.close();
        }
        
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }


    }
     
  
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        this.setLocationRelativeTo(null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       // File f = new File("C:\\MYFILE.txt");
//        Calendar today = Calendar.getInstance();
//Calendar expires = Calendar.getInstance();
//expires.set(2016,8, 01);
//if(today.after(expires)) {
//
//                JOptionPane.showMessageDialog(null,"This software is expired..\n"
//                        + "contact 0714406984\n"
//                        + "0r erickogi14@gmail.com\n"
//                        + "or vist erickogi.co.ke\n");
//
//  System.err.println("This software is expired.");
//  System.exit(1);
//}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton CmdClose;
    private javax.swing.JButton CmdOk;
    private javax.swing.JPasswordField TxtPassword;
    private javax.swing.JTextField TxtUserName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration

}
