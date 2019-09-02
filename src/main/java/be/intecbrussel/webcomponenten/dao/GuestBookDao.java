package be.intecbrussel.webcomponenten.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDao {

    private Connection conn;

    public GuestBookDao(String url,String user, String password) throws SQLException {
        conn = DriverManager.getConnection(url,user,password);
    }

    public List<GuestBookBean> getGuestBookItems(){
        List<GuestBookBean> guestBookBeans = new ArrayList<>();
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Guestbook");
            while (resultSet.next()) {
                guestBookBeans.add(new GuestBookBean(resultSet.getString("Name")
                        ,resultSet.getString("Message")
                        ,Timestamp.valueOf(resultSet.getString("Date")).toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guestBookBeans;
    }

    public void addGuestBookItem(GuestBookBean item){

    }

}
