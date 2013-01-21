import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

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
 
	  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  		{
		  String connectionURL = "jdbc:mysql://localhost/projekt";
		  Connection connection=null;	  
		  res.setContentType("text/html");
		  PrintWriter out = res.getWriter();  
		  String Question = req.getParameter("question");
		  
		  String Deadline = req.getParameter("deadline");
		  
		  String[] answer = req.getParameterValues("answer");
		  
		  String Type = req.getParameter("type");
		  
		 
		  try 
		  {
			  // Load the database driver
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  connection = DriverManager.getConnection
			  (connectionURL, "root", "root"); 
			  String sql = 
					  "insert into survey (Type,Deadline,Question) values (?,?,?)";
					  PreparedStatement pst = 
					  connection.prepareStatement(sql);
		
			  	  
				  pst.setString(1, Type);
				  pst.setString(2, Deadline);
				  pst.setString(3, Question);		 	 
				  pst.executeUpdate();
		 
				  pst.close();
				  
				  String sql2 = 
						  "insert into answers (Answer,idSurvey) values (? ,(SELECT max(idSurvey) FROM survey));";
						  PreparedStatement pst2 = 
						  connection.prepareStatement(sql2);
			  for(int i=0; i<answer.length; i++)
			  	{
			  
				  pst2.setString(1, answer[i]);
				  pst2.executeUpdate();
			  	}
			  pst2.close();
					
			  out.println("Survey successfully created!");
			  out.println("<a href=\"http://localhost:8080/test/survey.html\">Click here to get back</a>");		  
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