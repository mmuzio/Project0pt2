package dealership.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewResultSets {
	
	public void viewResultSet(ResultSet rs) {
		
		try {
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			System.out.println("Here are your results: ");
			
		    int columnsNumber = rsmd.getColumnCount();
		    
		    while (rs.next()) {
		    	
		       for (int i = 1; i <= columnsNumber; i++) {
		    	   
		           if (i > 1) System.out.print(",  ");
		           
		           String columnValue = rs.getString(i);
		    
		           System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    
		       }
		       
		    System.out.println("");
		    
		    }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
