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
public class prefrence {
    
  private String names;
  private int days;
  private int perday;
  private String pdfs;
  
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public prefrence(String Names, int Days, int Perday, String Pdfs)
  {
    this.names = Names;
    this.days = Days;
    this.perday = Perday;
    this.pdfs = Pdfs;
  }
  
  public String getNames()
  {
    return this.names;
  }
  
  public int getDays()
  {
    return this.days;
  }
  
  public int getPerday()
  {
    return this.perday;
  }
  
  public String getPdfs()
  {
    return this.pdfs;
  }
}
