package DataAccess;

import Domain.Weekly;
import junit.framework.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

//this will be singleton
public class WeeklyMapper {
    private static WeeklyMapper instance = new WeeklyMapper();
    private static Map<String, Weekly> WeeklyMap;
    private static Connection conn;

    private WeeklyMapper() {
        WeeklyMap=new HashMap<>();
        conn = Connect.getConnection();
    }
    public static WeeklyMapper getInstance() {
        if (instance == null) {
            instance = new WeeklyMapper();
        }
        return instance;
    }



}
