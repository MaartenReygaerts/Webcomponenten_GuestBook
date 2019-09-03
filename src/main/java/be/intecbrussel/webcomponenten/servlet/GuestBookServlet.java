package be.intecbrussel.webcomponenten.servlet;

import be.intecbrussel.webcomponenten.dao.GuestBookBean;
import be.intecbrussel.webcomponenten.dao.GuestBookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;


@WebServlet(value = "/guestbook", initParams = {@WebInitParam(name="url", value="jdbc:mariadb://noelvaes.eu/StudentDB"),@WebInitParam(name="user", value="student"),@WebInitParam(name="password", value="student123")})
public class GuestBookServlet extends HttpServlet {
        GuestBookDao guestBookDao;
    public void init(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            guestBookDao = new GuestBookDao(getInitParameter("url"),getInitParameter("user"),getInitParameter("password"));
        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

      try(PrintWriter out = resp.getWriter()){
          out.println("<!DOCTYPE html>");
          out.println("<html>");
          out.println("<head>");
          out.println("<title>GuestBook</title>");
          out.println("</head>");
          out.println("<body>");
          out.println("<h1>GuestBook</h1>");
          out.println("<ul>");

          for (GuestBookBean boontje: guestBookDao.getGuestBookItems()){
              out.println("<li>");
              out.println(boontje);
              out.println("</li>");
          }

          out.println("</ul>");

            out.println("<form method='post' action='guestbook'");
            out.println("<p>Please enter name :</p>");
            out.println("<input type='text' name='name'>");
            out.println("<p>Plze enter a message:</p>");
            out.println("<input type='text' name='message'>");
            out.println("<input type='submit' value='send nudes'>");
            out.println("</form>");


          out.println("</body>");
          out.println("</html>");
      }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        try {
            guestBookDao.addGuestBookItem(new GuestBookBean(req.getParameter("name"),req.getParameter("message")));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>GuestBook</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<h1>GuestBook</h1>");

            for (GuestBookBean boontje: guestBookDao.getGuestBookItems()){
                out.println("<li>");
                out.println(boontje);
                out.println("</li>");
            }
            out.println("</body>");
            out.println("</html>");


        }catch (Exception ex){
            ex.getCause();
        }
    }
}
