package DBSConnection;

import model.Game;
import model.Rental;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Rental dao.
 */
public class RentalDAOImpl extends Database implements RentalDAO
{
  private static RentalDAOImpl instance;

  private RentalDAOImpl() throws SQLException
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
  public static synchronized RentalDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RentalDAOImpl();
    }
    return instance;
  }

  @Override public ArrayList<Rental> getRentals() throws SQLException
  {
    Game game = null;
    User owner = null;
    User requester = null;
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Rental");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Rental> rentals = new ArrayList<>();
      while (resultSet.next())
      {
        int rentalId = resultSet.getInt("RentalID");
        int ownerId = resultSet.getInt("gameownerid");
        int requesterId = resultSet.getInt("gamerequesterid");
        int gameId = resultSet.getInt("rentedgameid");

        PreparedStatement statement1 = connection
            .prepareStatement("SELECT * FROM user_dbms where userid = ?");
        statement1.setInt(1, ownerId);
        ResultSet result = statement1.executeQuery();
        if (result.next())
        {
          owner = new User(result.getString("username"),
              result.getString("password"), result.getInt("userid"));

        }
        PreparedStatement statement2 = connection
            .prepareStatement("SELECT * FROM user_dbms where userid = ?");
        statement2.setInt(1, requesterId);
        ResultSet result1 = statement2.executeQuery();
        if (result1.next())
        {
          requester = new User(result1.getString("username"),
              result1.getString("password"), result1.getInt("userid"));

        }
        PreparedStatement statement3 = connection
            .prepareStatement("SELECT * FROM Game WHERE gameid =?");
        statement3.setInt(1, gameId);
        ResultSet result2 = statement3.executeQuery();
        if (result2.next())
        {
          game = new Game(result2.getString("Title"),
                  result2.getString("type"), result2.getInt("ReleaseYear"),
                  result2.getBoolean("NeedsDeposit"),
                  result2.getInt("AvailabilityPeriod"),
                  result2.getInt("UserID"),
                  result2.getInt("gameid"));
        }
        Rental rental = new Rental(owner, requester, game, rentalId);
        rentals.add(rental);
      }
      if(rentals.size() == 0){
        ArrayList<Rental> rentalArrayDummy = new ArrayList<>();
        Rental rentalDummy = new Rental(null, null, null, -1);
        rentalArrayDummy.add(rentalDummy);
        return rentalArrayDummy;
      }
      return rentals;
    }

  }

  @Override public void addRental(User owner, User requester, Game game)
      throws SQLException
  {
    int id = game.getId();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Rental (GameOwnerID, GameRequesterID, RentedGameID) VALUES (?,?,?) ",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, owner.getUserID());
      statement.setInt(2, requester.getUserID());
      statement.setInt(3, game.getId());
      statement.executeUpdate();
      PreparedStatement statement1 = connection.prepareStatement(
          "UPDATE Game SET available = false WHERE gameid = ?");
      statement1.setInt(1, id);
      statement1.executeUpdate();
    }
  }

  @Override public Rental getRentalById(int id) throws SQLException
  {
    Game game = null;
    User owner = null;
    User requester = null;

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Rental WHERE rentalid = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      int rentalId = resultSet.getInt("rentalId");
      int ownerId = resultSet.getInt("gameownerid");
      int requesterId = resultSet.getInt("gamerequesterid");
      int gameId = resultSet.getInt("rentedgameid");
      if (resultSet.next())
      {
        PreparedStatement statement1 = connection
            .prepareStatement("SELECT * FROM user_dbms where userid = ?");
        statement1.setInt(1, ownerId);
        ResultSet result = statement1.executeQuery();
        if (result.next())
        {
          owner = new User(result.getString("username"),
              result.getString("password"), result.getInt("userid"));

        }
        PreparedStatement statement2 = connection
            .prepareStatement("SELECT * FROM user_dbms where userid = ?");
        statement2.setInt(1, requesterId);
        ResultSet result1 = statement1.executeQuery();
        if (result1.next())
        {
          requester = new User(result.getString("username"),
              result.getString("password"), result.getInt("userid"));

        }
        PreparedStatement statement3 = connection
            .prepareStatement("SELECT * FROM Game WHERE game_id =?");
        statement3.setInt(1, gameId);
        ResultSet result2 = statement.executeQuery();
        if (result2.next())
        {
          game = new Game(resultSet.getString("title"),
              resultSet.getString("type"), resultSet.getInt("ReleaseYear"),
              resultSet.getBoolean("NeedsDeposit"),
              resultSet.getInt("AvailabilityPeriod"),
              resultSet.getInt("UserID"));
        }
        return new Rental(owner, requester, game,rentalId);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public void acceptRental(Rental rental) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      int ownerId = rental.getOwner().getUserID();
      int gameId = rental.getGame().getId();
      int userId = rental.getRequester().getUserID();
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM Rental WHERE rentalid = ?");
      statement.setInt(1, rental.getId());
      statement.executeUpdate();
      PreparedStatement statement1 = connection
          .prepareStatement("INSERT INTO Rented_game VALUES (?,?,?)");
      statement1.setInt(1, gameId);
      statement1.setInt(2, ownerId);
      statement1.setInt(3, userId);
      statement1.executeUpdate();
    }
  }


  @Override public void declineRental(Rental rental) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      int Gameid = rental.getGame().getId();
      System.out.println(Gameid);
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM Rental WHERE rentalid = ?");
      statement.setInt(1, rental.getId());
      statement.executeUpdate();
      PreparedStatement statement1 = connection.prepareStatement(
          "UPDATE Game SET available = true WHERE gameid = ?");
      statement1.setInt(1, Gameid);
      statement1.executeUpdate();
    }
  }

  @Override public int size() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT COUNT(rentalid) as size FROM Rental");
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
}
