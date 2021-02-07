package DBSConnection;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User dao.
 */
public class UserDAOImpl extends Database implements UserDAO
{
  private static UserDAOImpl instance;

  private UserDAOImpl() throws SQLException
  {
    super();
  }

  public Connection getConnection() throws SQLException
  {
    return super.getConnection();
  }

  /**
   * Gets instance.
   *
   * @return the instance
   * @throws SQLException the sql exception
   */
  public static synchronized UserDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new UserDAOImpl();
    }
    return instance;
  }

  @Override public User getUserByUserID(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User_dbms WHERE userid = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        User user = new User(username, password, id);
        user.setBio(resultSet.getString("bio"));
        return user;
      }
      else
      {
        return null;
      }
    }
  }

  // I'm not sure about this method
  @Override public User getUser(User user) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM User_dbms WHERE userid = ?");
      statement.setInt(1, user.getUserID());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        return new User(username, password, user.getUserID());
      }
      else
      {
        return null;
      }
    }
  }

  @Override public User getUserByCredentials(String username, String password)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM User_dbms WHERE username LIKE ? and password LIKE ?");
      statement.setString(1, "%" + username + "%");
      statement.setString(2, "%" + password + "%");
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        if (resultSet.getString("username").equals(username) && resultSet
            .getString("password").equals(password))
        {
          int id = resultSet.getInt("userid");
          User user = new User(username, password, id);
          user.setBio(resultSet.getString("bio"));
          return user;
        }
      }
      else
      {
        return null;
      }
    }
    return null;
  }

  @Override public void registerUser(String username, String password)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO User_dbms (username,password,bio) VALUES (?,?,?) ",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, username);
      statement.setString(2, password);
      statement.setString(3, "Default bio, please change");
      statement.executeUpdate();
    }
  }

  @Override public int size() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT COUNT(userid) as size FROM User_dbms");
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getInt("size");
      }
      else
      {
        return 0;
      }
    }
  }

  @Override public void setBio(User user, String bio) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("UPDATE User_dbms set bio = ? where userid = ?");
      statement.setString(1, bio);
      statement.setInt(2, user.getUserID());
      statement.executeUpdate();
    }
  }

  @Override
  public void removeUser(User user) throws SQLException
  {
    try (Connection connection = getConnection()) {

      PreparedStatement statementGames = connection.prepareStatement("DELETE FROM Game where userid=?");
      statementGames.setInt(1, user.getUserID());
      statementGames.executeUpdate();

      PreparedStatement statement = connection
              .prepareStatement("DELETE FROM User_dbms WHERE userid=?");
      statement.setInt(1, user.getUserID());
      statement.executeUpdate();
    }
  }
}
