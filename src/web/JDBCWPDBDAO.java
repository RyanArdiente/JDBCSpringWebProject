package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class JDBCWPDBDAO implements JDBCSWPDAO
{
	private static final String URL = "jdbc:mysql://localhost:3306/companydb";
	private Connection conn = null;
	private final String driverDB = "com.mysql.jdbc.Driver";
	private ResultSet rs = null;
	private Statement stmt = null;
	private int uc;
	@Override
	public List<List<String>> searchDB(String username, String password, String sqlstatement)
	{
		List<List<String>> searchResults = new ArrayList<>();
		try
		{
			Class.forName(driverDB);
			conn = DriverManager.getConnection(URL, username, password);
			

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlstatement);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			boolean firstrun = true;
			List<String> titleline = null;
			while(rs.next())
			{
				if(firstrun == true)
				{
					titleline = new ArrayList<>();
					for (int i = 1; i <= columnCount ; i++)
					{
						titleline.add(rs.getMetaData().getColumnName(i));
						//System.out.println(rs.getString(i));
					}
					searchResults.add(titleline);
					firstrun = false;
				}
				List<String> currentline = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++)
				{
					currentline.add(rs.getString(i));
					//System.out.println(rs.getString(i));
				}
				searchResults.add(currentline);
			}
//			System.out.println(searchResults.get(0).toString());
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("problem");
			System.err.println(cnfe);
			return null;
		}
 		catch(SQLException sqle)
		{
			System.err.println(sqle);
			return null;
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return searchResults;
	}
	@Override
	public int updateDB(String username, String password, String sqlstatement)
	{
		try
		{
			Class.forName(driverDB);
			conn = DriverManager.getConnection(URL, username, password);
		
			stmt = conn.createStatement();
			uc = stmt.executeUpdate(sqlstatement);
	
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			System.err.println(cnfe);
			return 0;
		}
 		catch(SQLException sqle)
		{
			System.err.println(sqle);
			return 0;
		}
		finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return uc;
	}

}
