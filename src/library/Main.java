/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kimani kogi
 */
public class Main extends javax.swing.JFrame {
 static String on;
 static String ondb;
 int yes;
String tt;
 
String admin;
 String filePath;
   
  String fileurlp = null;
    /**
     * Creates new form loan
     */
    public Main() {
        
        //myTopLevelContainer.setTitle("hj");
        initComponents();
       // String image="academic.png";
       // ImageIcon icon=new ImageIcon(image);
          //String image="academic.png";
       // ic.setIcon(icon);
    try {
        showimg();
    } catch (Exception ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
//        try
//    {
//      selectname();
//    }
//    catch (Exception ex)
//    {
//      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//    }
               try
    {
        selecton();
        methods n=new methods();
    String col=n.selectcolor();
    Color c=new Color(Integer.parseInt(col));
    jPanel1.setBackground(c);
    }
    catch (Exception ex)
    {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
// this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("academic.png")));
//        this.setTitle("ITHANGA SECONDARY SCHOOL");
    methods n=new methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
 // String m=  n.selectnamei();
 String mo= n.selectnamei();
 //for changeable name uncomment next line
 // schoolname.setText(mo);
 //for changable name uncomment next line
 schoolname.setText("ITHANGA SECONDARY SCHOOL ");
       // admin();
    }
//  public void selectname()
//    throws Exception
//  {
//   methods m=new methods();
//        Connection con = m.getConnection();
//    Statement st2 = con.createStatement();
//    
//    ResultSet res7 = st2.executeQuery("SELECT name FROM libprefrence  ");
//    while (res7.next()) {
//      tt=(res7.getString("name"));
//      schoolname.setText(tt);
//    }
//    st2.close();
//    res7.close();
//    con.close();
//  }
   public void selecton()
    throws Exception
  {
   methods m=new methods();
        Connection con = m.getConnection();
    Statement st2 = con.createStatement();
    
    ResultSet res7 = st2.executeQuery("SELECT user_name FROM lib_user  WHERE id=1 ");
    while (res7.next()) {
      ondb=(res7.getString("user_name"));
      
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
//   public void select()
//    throws Exception
//  {
//    Connection con = getConnection();
//
//    Statement stb = con.createStatement();
//
//    ResultSet resb = stb.executeQuery("SELECT pdfs FROM libprefrence   ");
//    while (resb.next())
//    {
//      
//      String sid=(resb.getString("pdfs"));
//      
//      
//      showimg();
//    }
//
//    stb.close();
//    
//
//    resb.close();
//    
//    con.close();
//  }
   public void showimg()
    throws Exception
  {
    try
    {
      methods m=new methods();
        Connection con = m.getConnection();
      Statement st2 = con.createStatement();
      
      ResultSet res7 = st2.executeQuery("SELECT pdfs FROM libprefrence  WHERE id=1");
      if (res7.next()) {
        this.filePath = res7.getString("pdfs");
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        schoolname = new javax.swing.JLabel();
        iconl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem10.setText("jMenuItem10");

        jMenuItem13.setText("jMenuItem13");

        jMenuItem14.setText("jMenuItem14");

        jMenu11.setText("jMenu11");

        jMenu5.setText("File");
        jMenuBar2.add(jMenu5);

        jMenu10.setText("Edit");
        jMenuBar2.add(jMenu10);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ITHANGA SECONDARY SCHOOL");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("WELCOME");

        schoolname.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        schoolname.setText("ITHANGA SECONDARY SCHOOL ");

        iconl.setText("                        SCHOOL    LOGO");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("LIBRARY MANAGMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolname)
                .addGap(240, 240, 240))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(323, 323, 323)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(443, 443, 443)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(348, 348, 348))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(schoolname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(iconl, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBar1.setMargin(new java.awt.Insets(3, 3, 3, 3));

        jMenu1.setText("LOAN");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(3, 3, 3, 3));
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/bookmark.png"))); // NOI18N
        jMenuItem4.setText("LOAN LIBRARY BOOKS");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/books.png"))); // NOI18N
        jMenuItem5.setText("GIVE COURSE BOOKS");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/book.png"))); // NOI18N
        jMenuItem11.setText("VIEW LOANED LIBRARY BOOKS");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/book-1.png"))); // NOI18N
        jMenuItem12.setText("VIEW COURSE BOOKS GIVEN");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("RETURN");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu2.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/erase.png"))); // NOI18N
        jMenuItem2.setText("RETURN LIBRARY BOOKS");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/notepad.png"))); // NOI18N
        jMenuItem3.setText("RETURN COURSE BOOKS");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("STUDENTS");
        jMenu3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu3.setIconTextGap(10);
        jMenu3.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/edit.png"))); // NOI18N
        jMenuItem6.setText("ADD ,UPDATE ");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/ok-icon.png"))); // NOI18N
        jMenuItem7.setText("CLEAR STUDENTS");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/Science-Classroom-icon.png"))); // NOI18N
        jMenuItem8.setText("PROMOTE TO NEXT CLASS");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("BOOKS");
        jMenu4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu4.setIconTextGap(10);
        jMenu4.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/formula.png"))); // NOI18N
        jMenuItem9.setText("ADD ,UPDATE BOOKS RECORD");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/document-scroll-icon.png"))); // NOI18N
        jMenuItem24.setText("PRINT BOOKS LIST");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem24);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("PREFRENCES");
        jMenu6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu6.setIconTextGap(10);
        jMenu6.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/password.png"))); // NOI18N
        jMenuItem16.setText("ADMIN PASSWORD");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem16);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/stock-vector-a-vector-icon-with-lock-inside-113615734.jpg"))); // NOI18N
        jMenuItem17.setText("USER PASSWORD");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/settings_1.png"))); // NOI18N
        jMenuItem18.setText("SYSTEM PROPERTIES");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem18);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("FINES");
        jMenu7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu7.setIconTextGap(10);
        jMenu7.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/Icon-CourtFinesFees.png"))); // NOI18N
        jMenuItem23.setText("fined");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem23);

        jMenuBar1.add(jMenu7);

        jMenu9.setText("PRINT REPORTS");
        jMenu9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu9.setIconTextGap(10);
        jMenu9.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/gnome_dev_printer.png"))); // NOI18N
        jMenuItem15.setText("PER YEAR");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);

        jMenuBar1.add(jMenu9);

        jMenu8.setText("HELP");
        jMenu8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu8.setIconTextGap(10);
        jMenu8.setMargin(new java.awt.Insets(3, 3, 3, 9));

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/Help.png"))); // NOI18N
        jMenuItem21.setText("Manual");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/1474675499_about.png"))); // NOI18N
        jMenuItem22.setText("about");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem22);

        jMenuBar1.add(jMenu8);

        jMenu13.setText("BACKUP &RESTORE");
        jMenu13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/Backup 2.png"))); // NOI18N
        jMenuItem19.setText("BACK UP");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem19);

        jMenuItem25.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/download.jpg"))); // NOI18N
        jMenuItem25.setText("RESTORE");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem25);

        jMenuBar1.add(jMenu13);

        jMenu12.setText("EXIT");
        jMenu12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenu12.setIconTextGap(10);
        jMenu12.setMargin(new java.awt.Insets(3, 3, 3, 9));
        jMenu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu12ActionPerformed(evt);
            }
        });

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library/images/Actions-application-exit-icon.png"))); // NOI18N
        jMenuItem20.setText("EXIT");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem20);

        jMenuBar1.add(jMenu12);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
       admin();
        if(yes==1){
       clean  b=new clean();
        b.setVisible(true);
        }
        
       
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        loanedcourse b=new loanedcourse();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        admin();
        if(yes==1){
       changepassuser b= new changepassuser();
        b.setVisible(true);
        }
        
        
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       LOAN b=new LOAN();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
          admin();
        if(yes==1){
       returnn b=new returnn();
        b.setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        LOANcourse b=new LOANcourse();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       admin();
        if(yes==1){
       AddUpdatenventory b=new AddUpdatenventory();
        b.setVisible(true);
        }
       
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
       loaned b=new loaned();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
         admin();
        if(yes==1){
       prefrences b= new prefrences ();
        b.setVisible(true);
        }
       
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        admin();
        if(yes==1){
        returnncourse b=new returnncourse();
        b.setVisible(true);
        }
       
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
          admin();
        if(yes==1){
       chekout b=new chekout();
        b.setVisible(true);
        }
       
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
      printbooklist b= new printbooklist();
      b.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        admin();
        if(yes==1){
        changepass b=new changepass();
        b.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        students b=new students();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed

        admin();
        if(yes==1){
        fines b=new fines() ;
        b.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        printper b=new printper();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenu12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu12ActionPerformed

    }//GEN-LAST:event_jMenu12ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        importexport b= new importexport();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        restore b= new restore();
        b.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
    try
    {
      String url = "help.html";
      Desktop.getDesktop().browse(URI.create(url));
    }
    catch (IOException e1)
    {
       JOptionPane.showMessageDialog(null, "NOT AVAILABLE");
    }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
       about b=new about();
       b.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed
public void admin(){
    try{
    if(on.equals(ondb)){
        yes=1;
         }
    else{
        JOptionPane.showMessageDialog(null, "ACCESS DENEID ");
    }
    }
     catch (Exception ex)
    {
      JOptionPane.showMessageDialog(null, "ACCESS DENEID ");
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //String on;
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel schoolname;
    // End of variables declaration//GEN-END:variables

   
}
