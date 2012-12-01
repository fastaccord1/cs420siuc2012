package cs420.buySell.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Conn {
  private	  Connection con = null;
  public Connection getCon(){
	  return con;
  }
	public DB_Conn(){

    // Create a variable for the connection string.
    String c = "jdbc:sqlserver://a8w1c5vhe9.database.windows.net;" +
    "databaseName=CS420proj;user=apps@a8w1c5vhe9;password=Alastor1";
    
    try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(c);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
    
   
	}

}
