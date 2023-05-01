package HR.DataAccess;

import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;

//this will be singleton
public class ManagerPasswordMapper {
    private static ManagerPasswordMapper instance;
    private static String managerPassword;

    /**
     * we will take the managet password from the database
     */
    private ManagerPasswordMapper() {

        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from ManagerPassword");
            while (rs.next()) {
                managerPassword = rs.getString("ManagerPass");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }
    public static ManagerPasswordMapper getInstance() {
        if (instance==null){
            instance=new ManagerPasswordMapper();
        }
        return instance;
    }

    /**
     * @return the current password
     */
    public static String getManagerPassword() {
        return managerPassword;
    }

    /**
     * @param managerPassword the new password
     *                        we will update the database
     */
    public static void setManagerPassword(String managerPassword) {
        ManagerPasswordMapper.managerPassword = managerPassword;
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("update ManagerPassword set ManagerPass = '"+managerPassword+"'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

}
