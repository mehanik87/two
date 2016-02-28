package dao;

import model.Contact;


import javax.management.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 26.02.2016.
 */
public class ContactsDao {

    public List<Contact> getContacts(String regexp) {
        List<Contact> list= new ArrayList<Contact>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from contacts");

            Pattern p = Pattern.compile(regexp);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Matcher m = p.matcher(name);



                if (!m.matches())  list.add(new Contact(id,name));
                /*System.out.println();
                System.out.printf("id: %d, name: %s", id, name);
                System.out.println("         " + m.matches());*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!= null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
