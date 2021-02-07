package DBSConnection;

import model.Game;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Game dao.
 */
public class GameDAOImpl extends Database implements GameDAO
{
  private static GameDAOImpl instance;

  private GameDAOImpl() throws SQLException
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
  public static GameDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new GameDAOImpl();
    }
    return instance;
  }

  @Override public void addGame(String title, String type, int releaseYear,
      boolean needsDeposit, int availabilityPeriod, int userId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Game(title, type,ReleaseYear ,NeedsDeposit , AvailabilityPeriod , Available , UserID ) VALUES("
              + "?,?,?,?,?,?,?" + "); ", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, title);
      statement.setString(2, type);
      statement.setInt(3, releaseYear);
      statement.setBoolean(4, needsDeposit);
      statement.setInt(5, availabilityPeriod);
      statement.setBoolean(6, true);
      statement.setInt(7, userId);
      statement.executeUpdate();
    }
  }

  @Override public void removeGame(Game game) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM GAME WHERE gameid=?");
      statement.setInt(1, game.getId());
      statement.executeUpdate();
    }
  }

  @Override public Game getGameById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Game WHERE gameid =?");
      statement.setInt(1, id);
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("ReleaseYear"),
            resultSet.getBoolean("NeedsDeposit"),
            resultSet.getInt("AvailabilityPeriod"),
            resultSet.getInt("UserID"));
      }
      else
      {
        return null;
      }
    }
  }

  @Override public int size() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT COUNT(gameid) as size FROM Game");
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

  @Override public ArrayList<Game> getAvailableGames() throws SQLException
  {
    ArrayList<Game> availableGames = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Game WHERE available=True");
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        Game game = new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("ReleaseYear"),
            resultSet.getBoolean("NeedsDeposit"),
            resultSet.getInt("AvailabilityPeriod"),
            resultSet.getInt("UserID"),
            resultSet.getInt("gameid"));
        availableGames.add(game);
      }
      if(availableGames.size() == 0){
        Game gameDummy = new Game("placeholder", "placeholder", -1, false, 1, -1, -1);
        gameDummy.setAvailable(false);
        availableGames.add(gameDummy);
        return availableGames;
      }
      else {
      return availableGames;
      }

    }
  }

  @Override public ArrayList<Game> getUnavailableGames() throws SQLException
  {
    ArrayList<Game> UnavailableGames = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Game WHERE available=False");
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        Game game = new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("ReleaseYear"),
            resultSet.getBoolean("NeedsDeposit"),
            resultSet.getInt("AvailabilityPeriod"),
            resultSet.getInt("UserID"), resultSet.getInt("gameid"));
        game.setAvailable(false);
        UnavailableGames.add(game);
      }
      return UnavailableGames;
    }
  }

  @Override
  public void setAvailable(Game game) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE Game SET available = true WHERE gameid = ?");
      statement.setInt(1, game.getId());
      statement.executeUpdate();
    }
  }
}
