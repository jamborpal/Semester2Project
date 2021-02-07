    package DBSConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Database.
 */
public abstract class Database
{
    /**
     * Instantiates a new Database.
     *
     * @throws SQLException the sql exception
     */
    public Database() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://35.204.219.115:5432/sep2?currentSchema=mist-sep", "javaapp", "javaAppUser123");
    }
}
