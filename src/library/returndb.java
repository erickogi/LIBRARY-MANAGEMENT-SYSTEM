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
public class returndb {
    private String bid;
  private String edition;
  private String fname;
  private String lname;
  private int no;
  private int sid;
  private String title;
  private String updated_at;
  
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public returndb(String Bid, String Edition, String Fname, String Lname, int No, int Sid, String Title, String Upadated_at)
  {
    this.bid = Bid;
    this.edition = Edition;
    this.fname = Fname;
    this.lname = Lname;
    this.no = No;
    this.sid = Sid;
    this.title = Title;
    
    this.updated_at = Upadated_at;
  }
  
  public String getBid()
  {
    return this.bid;
  }
  
  public String getEdition()
  {
    return this.edition;
  }
  
  public String getFname()
  {
    return this.fname;
  }
  
  public String getLname()
  {
    return this.lname;
  }
  
  public int getNo()
  {
    return this.no;
  }
  
  public int getSid()
  {
    return this.sid;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
}
