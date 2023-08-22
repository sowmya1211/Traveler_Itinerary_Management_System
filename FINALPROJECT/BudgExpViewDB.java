import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BudgExpViewDB extends HttpServlet{ 
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost:3306/TIMS";

    //  Database credentials
    static final String USER = "root"; 
    static final String PASS = "sqldb";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException{
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String trip_id = request.getParameter("tid");

		   
         try{
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Finding No of Expenses in budget
			Statement st1 = conn.createStatement();
			String sql1 = "SELECT * FROM budget WHERE trip_id='" + trip_id + "'";
			ResultSet rs1 = st1.executeQuery(sql1);

			while(rs1.next())
			{	
				//out.print("hey2");
				String div ="<div id='"+rs1.getString("budget_id")+"' class='budget' style='margin:50px;height:fit-content; width:55vw;'>";
                div+="<h3 style='text-align:left;''>"+rs1.getString("group_name")+":\t"+rs1.getString("amt")+"</h3><table style='margin:auto;'><tr><th>Expense Name</th><th>Expense Amt</th></tr>";
                out.println(div);

				String bid = rs1.getString("budget_id");
				
				Statement st2 = conn.createStatement();
				String sql2 = "SELECT * FROM expenses WHERE budget_id='" + bid + "' and trip_id='"+trip_id+"'" ;
				ResultSet rs2 = st2.executeQuery(sql2);

				int tot = 0;
				while(rs2.next()){
					out.println("<tr id='"+rs2.getString("exp_no")+"'><td>"+ rs2.getString("exp_dets")+"</td><td>"+rs2.getString("amt_spent")+"</td></tr>");
					tot += rs2.getFloat("amt_spent");
				}
				out.println("<tr><td colspan='2' class ='tot'>Total="+tot+"</td></tr>");
				out.println("<tr><td><input name='ename' class='ename' placeholder='Expense name'></td>");
				out.println("<td><input name='eamt' class='eamt' planceholder='Amount spent'></td><input type='hidden' name = 'trip_id' value = '");
				out.println(trip_id+"'> </tr></table><div class='btn' id= 'add-exp-btn' onclick='add_exp("+bid+")'>Add new expense</div></div>");
			}
			rs1.close();	
            conn.close(); 
		} 
        catch(Exception e){ 
            out.println("<p>Error</p>");
        }
      }
    
}

