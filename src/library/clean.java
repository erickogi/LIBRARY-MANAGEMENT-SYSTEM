/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author kimani kogi
 */
public class clean extends javax.swing.JFrame {
     ButtonGroup editableGroup = new ButtonGroup();
    String tt;
int all1=0;

int notall1=0;

String nameTField;
int count;
//int all4=0;
    /**
     * Creates new form clean
     */
    public clean() {
        initComponents();
        nameTField="tField";
        count=0;
text();
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
      Logger.getLogger(clean.class.getName()).log(Level.SEVERE, null, ex);
    }
        methods n=new methods();
   String t= n.setTitle();
    this.setTitle(t);
    String i=n.setIconImage();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
        
      editableGroup.add(all);
    editableGroup.add(notall);
     all.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          all1=1;
         notall1=0;
         empty();
        }
    });

        //notall1=1;      

    //add disallow listener
    notall.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
   notall1=1;          
   all1=0;
zero();
        }
    });
    }
    public void zero(){
f11.setText("0");
f12.setText("0");
f13.setText("0");
f14.setText("0");
f15.setText("0");
f16.setText("0");
f17.setText("0");
f18.setText("0");
f19.setText("0");
f110.setText("0");
f31.setText("0");
   // String f35 = this.f35.getText();
ff2.setText("0");

f32.setText("0");
f33.setText("0");
f34.setText("0");
f35.setText("0");
f36.setText("0");
f37.setText("0");
f38.setText("0");
f310.setText("0");
    }
      public void empty(){
f11.setText("");
f12.setText("");
f13.setText("");
f14.setText("");
f15.setText("");
f16.setText("");
f17.setText("");
f18.setText("");
f19.setText("");
f110.setText("");

f31.setText("");
   // String f35 = this.f35.getText();
ff2.setText("");

f32.setText("");
f33.setText("");
f34.setText("");
f35.setText("");
f36.setText("");
f37.setText("");
f38.setText("");
f310.setText("");
    }
//public Connection getConnection()
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        f11 = new javax.swing.JTextField();
        f12 = new javax.swing.JTextField();
        f13 = new javax.swing.JTextField();
        f14 = new javax.swing.JTextField();
        f15 = new javax.swing.JTextField();
        f16 = new javax.swing.JTextField();
        f17 = new javax.swing.JTextField();
        f18 = new javax.swing.JTextField();
        f19 = new javax.swing.JTextField();
        f110 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        text1 = new javax.swing.JTextArea();
        f31 = new javax.swing.JTextField();
        f32 = new javax.swing.JTextField();
        f33 = new javax.swing.JTextField();
        f34 = new javax.swing.JTextField();
        f35 = new javax.swing.JTextField();
        f36 = new javax.swing.JTextField();
        f37 = new javax.swing.JTextField();
        f38 = new javax.swing.JTextField();
        f310 = new javax.swing.JTextField();
        all = new javax.swing.JRadioButton();
        notall = new javax.swing.JRadioButton();
        form = new javax.swing.JTextField();
        ff2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("FORM TO MOVE");

        jButton1.setText("move");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        f17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f17ActionPerformed(evt);
            }
        });

        text1.setColumns(20);
        text1.setRows(5);
        jScrollPane2.setViewportView(text1);

        all.setText("ALL");

        notall.setText("NOT ALL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f13)
                                    .addComponent(f11))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(f12, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(f14)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(f19, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(f17)
                                    .addComponent(f15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(f18)
                                    .addComponent(f16)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(f110, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(all))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(f31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(f34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(f33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(f36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(f35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(f38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(f37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ff2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(notall)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(f310, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(f32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(all)
                    .addComponent(notall))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f310, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ff2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(38, Short.MAX_VALUE))
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

    private void f17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f17ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(form.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter the class you wish to move");
        }
        else if((all1==0&&notall1==0)||(all1==1&&notall1==1)){
            JOptionPane.showMessageDialog(null, "you must select one of the check boxes ");
            all1=0;
            notall1=0;
            editableGroup.clearSelection();

        }
        else if(all1==1){
            moveallform1();
        }
        else{
            movenotallform1();
        }

    }//GEN-LAST:event_jButton1ActionPerformed
public void moveallform1(){
    

     try
    {
      int s=Integer.valueOf(form.getText())  ;
      int d = s;
      int d1 = (s+1);
      
     methods m=new methods();
        Connection connection = m.getConnection();
      
      String sql4 = "UPDATE students SET form = '" + d1 + "'  WHERE form='" + d + "'";
      
      PreparedStatement pst4 = connection.prepareStatement(sql4);
      pst4.executeUpdate(sql4);
      pst4.close();
      connection.close();
      JOptionPane.showMessageDialog(null, "moved");
    }
    catch (Exception e)
    {
      System.err.println(e);
      JOptionPane.showMessageDialog(null, "error");
    }
}
public void movenotallform1(){
        String f1 = this.f11.getText();
    String f2 = this.f12.getText();
    String f3 = this.f13.getText();
    String f4 = this.f14.getText();
    String f5 = this.f15.getText();
    String f6 = this.f16.getText();
    String f7 = this.f17.getText();
    String f8 = this.f18.getText();
    String f9 = this.f19.getText();
    String f10 = this.f110.getText();
    String f101 = this.f310.getText();
   
    String f1q = this.f31.getText();
   // String f35 = this.f35.getText();
     String f351 = this.ff2.getText();

    String f122 = this.f32.getText();
    String f132 = this.f33.getText();
    String f142 = this.f34.getText();
    String f152 = this.f35.getText();
    String f162 = this.f36.getText();
    String f172 = this.f37.getText();
    String f182 = this.f38.getText();
    //String f19 = this.f39.getText();
   // String f110 = this.f310.getText();
     try
    {
   int s=Integer.valueOf(form.getText())  ;
      int d = s;
      int d1 = (s+1);
     methods m=new methods();
        Connection connection = m.getConnection();
      
      String sql4 = "UPDATE students SET form = '" + d1 + "'  WHERE form='" + d + "' AND id NOT IN('"+f1+"','"+f2+"','"+f3+"','"+f4+"','"+f5+"',"
              + "'"+f6+"','"+f7+"','"+f8+"','"+f9+"',"
              + "'"+f10+"','"+f122+"','"+f132+"','"+f142+"','"+f152+"','"+f162+"','"+f172+"','"+f182+"','"+f1q+"','"+f351+"','"+f101+"' )";
      
      PreparedStatement pst4 = connection.prepareStatement(sql4);
      pst4.executeUpdate(sql4);
      pst4.close();
      connection.close();
      editableGroup.clearSelection();
       }
    catch (Exception e)
    {
      System.err.println(e);
      JOptionPane.showMessageDialog(null, "error");
    }
}

    public void text(){
         text1.append("if not all student to be moved,"  + "\n");
         text1.append("select 'NOT ALLL' then " + "\n");
         text1.append("enter adm no of those students"  + "\n");
         text1.append("not to be promoted"  + "\n");
         text1.append("And finally click on 'move'  "+ "\n");
         text1.append("IF NO OF THOSE NOT TO BE PROMOTED IS"+ "\n");
         text1.append("MORE THAN 10,YOU WILL UPDATE  "+ "\n");
         text1.append("THE DETAILS OF THE PLUS 10  "+ "\n");
         text1.append("IN THE STUDENTS PAGE "+ "\n");
                
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
            java.util.logging.Logger.getLogger(clean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clean().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton all;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField f11;
    private javax.swing.JTextField f110;
    private javax.swing.JTextField f12;
    private javax.swing.JTextField f13;
    private javax.swing.JTextField f14;
    private javax.swing.JTextField f15;
    private javax.swing.JTextField f16;
    private javax.swing.JTextField f17;
    private javax.swing.JTextField f18;
    private javax.swing.JTextField f19;
    private javax.swing.JTextField f31;
    private javax.swing.JTextField f310;
    private javax.swing.JTextField f32;
    private javax.swing.JTextField f33;
    private javax.swing.JTextField f34;
    private javax.swing.JTextField f35;
    private javax.swing.JTextField f36;
    private javax.swing.JTextField f37;
    private javax.swing.JTextField f38;
    private javax.swing.JTextField ff2;
    private javax.swing.JTextField form;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton notall;
    private javax.swing.JTextArea text1;
    // End of variables declaration//GEN-END:variables
}
