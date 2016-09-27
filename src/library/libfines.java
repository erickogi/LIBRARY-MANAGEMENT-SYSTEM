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
public class libfines {
     private int id;
  private String amount;
   
  private String day;
  private String status;
 
  
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public libfines(int Id, String Amount,String Day, String Status )
  {
    this.id = Id;
    this.amount = Amount;
    this.day = Day;
    this.status = Status;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getAmount()
  {
    return this.amount;
  }
  
  public String getDay()
  {
    return this.day;
  }
   public String getStatus()
  {
    return this.status;
  }
}
