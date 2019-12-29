package utils;

public enum DBCredentials{
    DB_URL("jdbc:mysql://remotemysql.com:3306/CiDmZ2TSKL"),
    DB_USERNAME("CiDmZ2TSKL"),
    DB_PASSWORD("qBmzVPUDd8");

    private String value;

    private DBCredentials(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
