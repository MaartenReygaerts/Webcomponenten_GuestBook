package be.intecbrussel.webcomponenten.dao;

import java.sql.*;
import java.time.LocalDateTime;
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GuestBook");
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

    public void addGuestBookItem(GuestBookBean item) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO GuestBook (Name,Message) values (?,?)");
        try {
            statement.setString(1,item.getName());
            statement.setString(2,item.getMessage());


            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
