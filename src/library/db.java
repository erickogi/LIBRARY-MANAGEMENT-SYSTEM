package library;

import java.sql.ResultSet;
import javax.swing.table.TableModel;

public class db
{
  private String id;
  private String title;
  private String ediion;
  private String status;
  
  static TableModel resultSetToTableModel(ResultSet result)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public db(String Id, String Title, String Edition, String Status)
  {
    this.id = Id;
    this.title = Title;
    this.ediion = Edition;
    this.status = Status;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public String getEdition()
  {
    return this.ediion;
  }
  
  public String getStatus()
  {
    return this.status;
  }
}
