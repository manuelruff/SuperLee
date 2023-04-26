package DataAccess;
import Domain.ManagerController;
import Domain.Super;
import Domain.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
public class SuperMapper {
    private static SuperMapper instance = new SuperMapper();
    private static Map<String, Super> SuperMap;
    private static Connection conn;
    private SuperMapper(){
        SuperMap=new HashMap<>();
        conn = Connect.getConnection();
    }
    public static SuperMapper GetInstance(){return instance;}
    public static Map<String,Super>getSuperMap(){return SuperMap;}
    /**
     * @param name  of branch
     * @return the branch asked
     */
    public static Super getsuper(String name){
        //if i dont have this worker in the data ill go read it from DB
        if (SuperMap.get(name)==null){
            ReadSuper(name);
        }
        return SuperMap.get(name);
    }
    /**
     * this functiuon read the brnach from the db
     * @param branchName name of super
     *
     */
    private static void ReadSuper(String branchName){
        String name;
        try {
            java.sql.Statement stmt = conn.createStatement();
            //java.sql.ResultSet rs = stmt.executeQuery("select * from Super WHERE name=="+branchName+"" );
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Super WHERE name = '" + branchName + "'");

            if(rs.next()) {
                name = rs.getString("name");
                Super branch = new Super(name);
                //add the worker to the map
                SuperMap.put(name, branch);
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }


    public static void WriteAllSupers(){
        for(Super branch:SuperMap.values()){
            String name;
            try{
                java.sql.Statement stmt = conn.createStatement();
                name = branch.getName();
                stmt.executeUpdate("INSERT OR IGNORE INTO Super (name) VALUES ('" + name + "')");
            }
            catch (SQLException e) {
                System.out.println("i have a problem in writing the super sorry");
            }
        }
    }

    //im not sure about this one
    public static void WriteSuper(String branch){SuperMap.put(branch, ManagerController.getBranchByName(branch));}

}
