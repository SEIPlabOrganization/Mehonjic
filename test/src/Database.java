import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Database extends HttpServlet
{
	  public void init(ServletConfig config) throws ServletException
	  {
		  super.init(config);
	  }
  /**Process the HTTP Get request*/
	  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  		{
		  String connectionURL = "jdbc:mysql://localhost/projekt";
		  Connection connection=null;
		  
		  res.setContentType("text/html");
		  PrintWriter out = res.getWriter();
		 
		
		  
		  //get the variables entered in the form
		  
		  String Question = req.getParameter("question");
		  String Deadline = req.getParameter("deadline");
		  String Answer = req.getParameter("answer1");
		  String Type = req.getParameter("type");
		  
		 
		  try 
		  {
			  // Load the database driver
			  Class.forName("com.mysql.jdbc.Driver");
			  // Get a Connection to the database
			  connection = DriverManager.getConnection
			  (connectionURL, "root", "root"); 
			  //Add the data into the database
			  String sql = 
			  "insert into survey (Question,Deadline,Answer,Type) values (?,?,?,?)";
			  PreparedStatement pst = 
			  connection.prepareStatement(sql);
			  pst.setString(1, Question);
			  pst.setString(2, Deadline);
			  pst.setString(3, Answer);
			  pst.setString(4, Type);
 
  
			  int numRowsChanged = pst.executeUpdate();
			  out.println("Survey successfully created!");
			  pst.close();
		  }
		  
  catch(ClassNotFoundException e)
  {
	  out.println("Couldn't load database driver: " 
	  + e.getMessage());
  }
  catch(SQLException e)
  {
	  out.println("SQLException caught: " 
	  + e.getMessage());
  }
  catch (Exception e)
  {
	  out.println(e);
  }
  finally {
  // Always close the database connection.
	  try 
	  {
		  	if (connection != null) connection.close();
	  }
	  catch (SQLException ignored)
	  {
	  out.println(ignored);
	  }
  	}
  }
}