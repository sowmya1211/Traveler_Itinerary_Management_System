import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetSessionServlet extends HttpServlet{
      
    public void doGet(HttpServletRequest request, HttpServletResponse response){  
        try{
            HttpSession session = request.getSession(false);      
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print(session.getAttribute("uid") + "," + session.getAttribute("utype"));

            out.close();
        } 
        catch(Exception e){
            System.out.println(e); 
        }   
    }  
}
