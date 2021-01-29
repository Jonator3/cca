package dbAdapters;

public class Configuration {
    private static final String SERVER = "snakeparty.net";
    private static final String TYPE = "mysql";
    private static final int PORT = 1433;
    private static final String USER = "cca";
    private static final String PASSWORD = "stistdoof";
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
