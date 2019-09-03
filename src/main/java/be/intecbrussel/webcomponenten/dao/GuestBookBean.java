package be.intecbrussel.webcomponenten.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GuestBookBean {

   private String name;
   private String message;
   private LocalDateTime date;

    public GuestBookBean() {
    }

    public GuestBookBean(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public GuestBookBean(String name, String message, LocalDateTime date) {
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public GuestBookBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public GuestBookBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public GuestBookBean setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "GuestBookBean{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
