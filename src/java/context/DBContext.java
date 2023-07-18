package context;

import java.sql.Connection;
import java.sql.DriverManager;

//public interface DBContext {
//    public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static String DBURL="jdbc:sqlserver:MSI\\SQLEXPRESS;databaseName=Fastfood;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
//    public static String USERDB="sa";
//    public static String PASSDB="123";  
//}
public interface DBContext {

    public static String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=Fastfood;instanceName=SQLEXPRESS;encrypt=false;";
    public static String USERDB = "sa";
    public static String PASSDB = "passwordforsa123";


}
