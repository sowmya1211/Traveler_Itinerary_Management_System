import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetItinServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response){  
        try{
            HttpSession session = request.getSession(false);
            
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter(); 
            
            String uid = (String)session.getAttribute("uid");
            String tid = (String)request.getParameter("tid");
            RequestDispatcher rd = request.getRequestDispatcher("itin.html?tid="+tid);
            rd.forward(request, response);
        }
        catch(Exception e){  
            System.out.println(e);
        }   
  
    }  
}  

