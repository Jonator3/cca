package dbAdapters;

public class Configuration {
    private static final String SERVER = "144.91.126.232";
    private static final String TYPE = "mysql";
    private static final int PORT = 3306;
    private static final String USER = "client";
    private static final String PASSWORD = "abc";
    private static final String DATABASE = "CCA";

    public static String getDATABASE() {
        return DATABASE;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getSERVER() {
        return SERVER;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getUSER() {
        return USER;
    }
}
