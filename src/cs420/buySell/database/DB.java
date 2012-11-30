package buySell.database;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class DB {

   public static void main(String[] args) {

     //create connection
      DB_Conn dbcon=new DB_Conn();
      
      // Declare the JDBC objects.
      Connection con = dbcon.getCon();
      Queries q = new Queries();
      Statement stmt = null;
      ResultSet rs = null;
      Statement inst = null;

      try {
        
         // Create and execute an SQL statement that returns some data.
          //String SQL = q.qGetItems("nil", "nil", "nil");
    	  String SQL =q.qGetUserPool();
    	 // String SQL = q.qGetUsers("nil", "nil", "nil");
    	 // String SQL = q.qGetVendors("nil", "nil", "1", "nil");
    	 // String SQL =q.qGetWTBFull("nil", "nil", "nil", "nil", "nil", "nil", "nil","nil", "WTB");
    	 //String SQL = q.qGetWTSFull("nil", "CISCO", "nil", "nil", "nil", "nil", "nil", "nil"); 
         stmt = con.createStatement();
         inst=con.createStatement();
         
         rs = stmt.executeQuery(SQL);
         //System.out.println(q.qInsertWTS("9", "1", "1", "5", "99.54", "01/27/2013"));
         //inst.executeUpdate((q.qInsertWTB("9", "1", "1", "WTB", "12/12/2012", "1", "99.99")));
         //inst.executeUpdate((q.qInsertWTS("9", "1", "1", "5", "99.54", "01/27/2013")));
       // inst.executeUpdate(q.qUpdateWTS("2", "2", "8", "99.65", "12/18/2014", "9"));
         //inst.executeUpdate(q.qDeleteWTS("9"));
         inst.executeUpdate(Queries.qUpdateUserPool("1"));
        //System.out.println(q.qUpdateVendors("Phils goat emporium", "The YMCA", "1","8","RAGE")); 
         // Iterate through the data in the result set and display it.
         while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
         }
      }

      // Handle any errors that may have occurred.
      catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if (rs != null) try { rs.close(); } catch(Exception e) {}
         if (stmt != null) try { stmt.close(); } catch(Exception e) {}
         if (con != null) try { con.close(); } catch(Exception e) {}
      }
   }
}