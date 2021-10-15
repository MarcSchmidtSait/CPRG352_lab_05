package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
public class LoginServlet extends HttpServlet {

   


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       HttpSession session = request.getSession();
        
       if(request.getParameter("output") != null){
           request.getSession().invalidate();
           request.setAttribute("output", "Thank you, you have now been logged out");
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
           return;
       }
       else{
           if(session.getAttribute("user") != null){
               response.sendRedirect("home");
               return;
           }
           else{
               getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
               return;
           }
           
           
       }
       
       
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String userName = "";
        String passWord = "";
        
        userName = request.getParameter("username");
        passWord = request.getParameter("password");
        
        AccountService accountsev = new AccountService();
        
        User user = accountsev.login(userName, passWord);
        if (user == null){
            request.setAttribute("username", userName);
            request.setAttribute("password", passWord);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else{
            HttpSession session;
            session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home");
        }
        
        
    }

    
  

}
