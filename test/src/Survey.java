import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Survey extends HttpServlet{
public void doGet(HttpServletRequest req, HttpServletResponse res)

throws ServletException, IOException
	{
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	Connection con;
	Statement stmt;
	Statement stmt2;
	ResultSet rs;
	ResultSet rs2;
	try{
		String connectionURL = "jdbc:mysql://localhost/projekt";
		  
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection
				  (connectionURL, "root", "root"); 
		stmt = con.createStatement();
		stmt2 = con.createStatement();
		rs = stmt.executeQuery("Select Answer from answers where idSurvey = 30");
		pw.println("<HTML>");
		rs2=stmt2.executeQuery("Select Question from survey where idSurvey=30");
	    
	

		pw.println("<BODY>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<td style=\"background-color:#0066FF; width:1000px; height:10px\">");
		pw.println("<h1 style=\"color:white\">");
		pw.println("Survey");
		pw.println("</h1>");				
		pw.println("</td>");
		pw.println("</tr>");
		pw.println("</table>");
		pw.println("<h3>Pitanje:</h3>");
		String value;
		while(rs2.next())
		{
			pw.println("<h4>");
			value = rs2.getString(1);
			pw.println(value);
			pw.println("</h4>");
		}
		String columnValue;
		ResultSetMetaData r = (ResultSetMetaData) rs.getMetaData();
		int numofcol = r.getColumnCount();
		
		
		while(rs.next())
			{
			
			pw.println("<table  width=\"300\">");
			pw.println("<tr>");
			pw.println("<td>");
			pw.println("</td>");
					for (int i = 1 ; i <= numofcol; i++)
					{
						
						columnValue = rs.getString(i);
						pw.println("<td>");				
						pw.println(columnValue);
						pw.println("<form action=\"submit\">");
						// u implementaciji ...
						
						pw.println("</td>");
						
					}
			pw.println("</tr>");
			pw.println("</table>");
			
			}
		
		pw.println("</BODY>");
		pw.println("</HTML>");
		}
	catch (Exception e){
						pw.println(e);
						}
	}
}