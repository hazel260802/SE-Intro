
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.UserMoldel;
import services.MysqlConnection;

/**
 *
 * @author Hai
 */
public class LoginController {
    
    public static UserMoldel currentUser = new UserMoldel();
    
    public boolean login(String userName, String password) throws SQLException, ClassNotFoundException{
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE userName = '" + userName +"'");
        if (rs == null) {
            return false;
        }
        while (rs.next()) {                
            if (rs.getString("passwd") == null ? password == null : rs.getString("passwd").equals(password)) {
                LoginController.currentUser.setID(rs.getInt("ID"));
                LoginController.currentUser.setUserName(rs.getString("userName"));
                return true;
            }
        }
        connection.close();
        return false;
    }
}
