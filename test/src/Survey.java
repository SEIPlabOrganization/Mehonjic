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
	ResultSet rs;
	try{
		String connectionURL = "jdbc:mysql://localhost/projekt";
		  
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection
				  (connectionURL, "root", "root"); 
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select Question,Answer from survey where ID = 1");
		pw.println("<HTML>");
	
	    
	

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
		String[] strArray;
				
		
		while(rs.next())
			{
			String n = rs.getString("Question");
			strArray = new String[] {n};
			pw.println(strArray[0]);
			String nm = rs.getString("Answer");
			pw.println("<br/>");
			pw.println("<table>");
			//pw.println("<th><td><h4>" + strArray[0] + "</h4></td></th>");
            pw.println("<tr><td>" + nm + "</td></tr>"); 
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