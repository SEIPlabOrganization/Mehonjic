import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Survey extends HttpServlet{
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Connection con;
		Statement stmt;
		Statement stmt2;
		Statement stmt3;
		
		ResultSet rs;
		ResultSet rs2;
		ResultSet rs3;
	
		String type = null;
		int[] id = { 
			    39, 40
			};
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
		for (int i = 0 ; i <  id.length ; i++)
		{
					
			try
			{
				String connectionURL = "jdbc:mysql://localhost/projekt";
				  
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection (connectionURL, "root", "root"); 
				stmt = con.createStatement();
				stmt2 = con.createStatement();
				stmt3 = con.createStatement();
				
				rs = stmt.executeQuery("Select Answer from answers where idSurvey = "+ id[i] + "");
				rs2=stmt2.executeQuery("Select Question from survey where idSurvey = "+ id[i] + "");
			    rs3=stmt3.executeQuery("Select Type from survey where idSurvey = "+ id[i] + "");
			    
			    pw.println("<HTML>");
			    	while (rs3.next())
			    	{
			    		type = rs3.getString(1);
			    	}
		
				
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
				
				
				pw.println("<table  width=\"300\">");
				pw.println("<form name\"myform\" action=\"Submit\" method=\"get\">");
				while(rs.next())
					{			
							for (int j = 1 ; j <= numofcol; j++)
							{
								columnValue = rs.getString(j);
								pw.println("<tr>");
								pw.println("<td>");				
								pw.println(columnValue);
								if (type.equalsIgnoreCase("single"))
									{
										pw.println("<td>");
										pw.println("<input type=\"radio\" name=\"radio\" value=\""+ columnValue +"\">");
										pw.println("</td>");
									}
								else if (type.equalsIgnoreCase("multi")) 
									{
										pw.println("<td>");
										pw.println("<input type=\"checkbox\" name=\"checkbox\" value=\""+ columnValue +"\">");
										pw.println("</td>");	
									}	
							}
					
							pw.println("</td>");
							pw.println("</tr>");
					}				
					pw.println("<tr>");
					pw.println("<td>");
					pw.println("<input type=\"hidden\" name=\"id\" value=\"" + id[i] + "\"/>");
					pw.println("<input type=\"submit\" value=\"Submit\"/>");
					pw.println("</form>");
					pw.println("</tr>");
					pw.println("</td>");
					pw.println("</table>");
					pw.println("</br>");
				}
		catch (Exception e)
				{
				pw.println(e);
				}
		}
		pw.println("</BODY>");
		pw.println("</HTML>");
		}

}