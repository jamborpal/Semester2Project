package DBSConnection;

import model.Game;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Rented game dao.
 */
public class RentedGameDAOImpl extends Database implements RentedGameDAO
{
  private static RentedGameDAOImpl instance;

  private RentedGameDAOImpl() throws SQLException
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
  public static RentedGameDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RentedGameDAOImpl();
    }
    return instance;
  }

  @Override public ArrayList<Game> getRentedGames(User user) throws SQLException
  {
    ArrayList<Game> rentedGames = new ArrayList<>();
    int receiverId = user.getUserID();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement1 = connection.prepareStatement(
          "SELECT * FROM Rented_game;");
      ResultSet resultSet1 = statement1.executeQuery();
      while (resultSet1.next())
      {
        if(resultSet1.getInt("userid") == receiverId){
          int idbuffer = resultSet1.getInt("gameid");
          PreparedStatement statement2 = connection.prepareStatement("Select * from Game where gameid = ?");
          statement2.setInt(1, idbuffer);
          ResultSet resultSet2 = statement2.executeQuery();
          while(resultSet2.next()){
            Game game = new  Game(resultSet2.getString("title"),
                    resultSet2.getString("type"), resultSet2.getInt("releaseyear"),
                    resultSet2.getBoolean("needsdeposit"),
                    resultSet2.getInt("availabilityperiod"),
                    resultSet2.getInt("userid"), resultSet2.getInt("gameid"));
            rentedGames.add(game);
        }
        }
      }
      if(rentedGames.size() == 0){
        Game gameDummy = new Game("placeholder", "placeholder", -1, false, 1, -1, -1);
        gameDummy.setAvailable(false);
        rentedGames.add(gameDummy);
        return rentedGames;
      }
      else{
        return rentedGames;
      }
    }
  }

  @Override public Game getRentedGameById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Rented_game WHERE gameid =?");
      statement.setInt(1, id);
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("releaseyear"),
            resultSet.getBoolean("needsdeposit"),
            resultSet.getInt("availabilityperiod"),
            resultSet.getInt("userod"));
      }
      else
      {
        return null;
      }
    }

  }

  @Override public void removeRentedGame(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM GAME WHERE gameid=?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }
}
