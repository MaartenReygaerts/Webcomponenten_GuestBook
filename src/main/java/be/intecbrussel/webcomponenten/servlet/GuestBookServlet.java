package be.intecbrussel.webcomponenten.servlet;

import be.intecbrussel.webcomponenten.dao.GuestBookBean;
import be.intecbrussel.webcomponenten.dao.GuestBookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
        GuestBookDao guestBookDao;
    public void init(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            guestBookDao = new GuestBookDao("jdbc:mariadb://noelvaes.eu/StudentDB","student","student123");
        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        for (GuestBookBean boontje: guestBookDao.getGuestBookItems()) {
            pw.println(boontje);
        }
    }
}
