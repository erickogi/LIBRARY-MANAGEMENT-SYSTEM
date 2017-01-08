/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.ResultSet;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class addstudent {

  private String id;
  private String fname;
  private String lastname;
  private String form;
  private String sformc;
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public addstudent(String Id, String Fname, String Lname, String Form, String Sformc)
  {
    this.id = Id;
    this.fname = Fname;
    this.lastname = Lname;
    this.form = Form;
     this.sformc = Sformc;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getFname()
  {
    return this.fname;
  }
  
  public String getLname()
  {
    return this.lastname;
  }
  
  public String getForm()
  {
    return this.form;
  }
   public String getSformc()
  {
    return this.sformc;
  }
}
