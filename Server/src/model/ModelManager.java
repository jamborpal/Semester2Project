package model;

import DBSConnection.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class representing all the necessary methods the views will use.
 * @author Group 1
 */
public class ModelManager implements Model
{

  private GameDAO gameList;
  private RentalDAO rentalList;
  private UserDAO userList;
  private RentedGameDAO rentedList;

  /**
   * Initialising the lists from the Database.
   * @throws SQLException
   */
  public ModelManager() throws SQLException
  {
    this.gameList = GameDAOImpl.getInstance();
    this.userList = UserDAOImpl.getInstance();
    this.rentalList = RentalDAOImpl.getInstance();
    this.rentedList = RentedGameDAOImpl.getInstance();

  }

  /**
   * Getting a specific User by their userID.
   * @param userId  ID of a User.
   * @return A User with a specific ID.
   * @throws SQLException
   */
  @Override public User getUserByID(int userId) throws SQLException
  {
    if (userList.getUserByUserID(userId) != null)
    {
      return userList.getUserByUserID(userId);
    }
    else
    {
      return null;
    }
  }

  /**
   * Getting a User by the username and password.
   * @param username The username of the User.
   * @param password The password of the User.
   * @return The User object with the given credentials.
   * @throws SQLException
   */
  @Override public User getUserByCredentials(String username, String password)
      throws SQLException
  {
    return userList.getUserByCredentials(username, password);
  }

  /**
   * Setting the bio of a given User.
   * @param user The User that should have a new bio set.
   * @param bio The bio to be set for the given User.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void setUserBio(User user, String bio)
      throws RemoteException, SQLException
  {
    userList.setBio(user, bio);
  }

  /**
   *Requesting a Game to be traded
   * @param requester The User who requests the Game.
   * @param game The Game requested.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void requestGame(User requester, Game game)
      throws RemoteException, SQLException
  {
    rentalList
        .addRental(userList.getUserByUserID(game.getUserId()), requester, game);
  }

  /**
   * Accepting a Game that has been up for trade.
   * @param rental The Rental to be accepted.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void acceptGame(Rental rental)
      throws RemoteException, SQLException
  {
    rentalList.acceptRental(rental);
  }
  /**
   * Declining a Game that has been up for trade.
   * @param rental The Rental to be declined.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void declineGame(Rental rental)
      throws RemoteException, SQLException
  {
    rentalList.declineRental(rental);
  }

  /**
   * Getting the GameList with all of the Games present.
   * @return A GameList with all the Games.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public GameList getFullListOfGames()
      throws RemoteException, SQLException
  {
    GameList gameListObject = new GameList();
    ArrayList<Game> gamesDBS = gameList.getAvailableGames();
    ArrayList<Game> unavailableGames = gameList.getUnavailableGames();
    for (int i = 0; i < gamesDBS.size(); i++)
    {
      gameListObject.addGame(gamesDBS.get(i));
    }
    for (int i = 0; i < unavailableGames.size(); i++)
    {
      gameListObject.addGame(unavailableGames.get(i));
    }
    return gameListObject;
  }

  /**
   * Adding a Game to the list of games.
   * @param game The Game to be added.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void addGame(Game game) throws RemoteException, SQLException {
gameList.addGame(game.getTitle(), game.getType(), game.getReleaseYear(), game.deposit(), game.getAvailabilityPeriod(), game.getUserId());
  }
  /**
   * Removing a Game from the list of games.
   * @param game The Game to be removed.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void removeGame(Game game)
      throws RemoteException, SQLException
  {
    gameList.removeGame(game);
  }

  /**
   * Getting a Game by the index in the ArrayList.
   * @param index The index the Game should be gotten from.
   * @return A Game at the given index.
   */
  @Override public Game getGameByIndex(int index)
  {
    return null;
  }

  /**
   * Getting a Game by its ID.
   * @param gameID The ID of the Game.
   * @return The Game with the given ID.
   * @throws SQLException
   */
  @Override public Game getGameByID(int gameID) throws SQLException
  {
    return gameList.getGameById(gameID);
  }

  /**Getting a User that has the given Game.
   * @param game The game which owner should be gotten.
   * @return The User that has the given Game.
   * @throws SQLException
   */
  @Override public User getUserByGame(Game game) throws SQLException
  {
    return userList.getUserByUserID(game.getUserId());
  }

  /**
   * Returning the amount of Games present in the system.
   * @return An integer representing the number of Games present.
   * @throws SQLException
   */
  @Override public int getSizeOfGameList() throws SQLException
  {
    return gameList.size();
  }

  /**
   * Getting the list of the Games that are up for rent.
   * @return A RentalList of the games that are up for Rent.
   * @throws SQLException
   */
  @Override public RentalList getRentalList() throws SQLException
  {
    RentalList rentalListObject = new RentalList();
    ArrayList<Rental> rentalDBS = rentalList.getRentals();
    for (int i = 0; i < rentalDBS.size(); i++)
    {
      rentalListObject.addRental(rentalDBS.get(i));
    }
    return rentalListObject;
  }

  /**
   * Registering a new User in the system.
   * @param username The username of the User.
   * @param password The password of the User.
   * @throws SQLException
   */
  @Override public void registerUser(String username, String password)
      throws SQLException
  {
    userList.registerUser(username, password);
  }

  /**
   * Setting a Game available.
   * @param game The Game to be set to available.
   * @throws SQLException
   */
  @Override public void setGameAvailableTrue(Game game) throws SQLException
  {
    gameList.setAvailable(game);
  }

  /**
   * Removing a User from the UserList.
   * @param user The User to be removed.
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void removeUser(User user) throws RemoteException, SQLException
  {
    userList.removeUser(user);
  }

  /**
   * Getting the Games that are currently rented for a given User.
   * @param user The User, which rented games should be displayed.
   * @return
   * @throws SQLException
   */
  @Override public GameList getRentedGames(User user) throws SQLException
  {
    GameList rentedGamesObj = new GameList();
    ArrayList<Game> rentedArray = rentedList.getRentedGames(user);
    for (int i = 0; i < rentedArray.size(); i++)
    {
      rentedGamesObj.addGame(rentedArray.get(i));
    }
    return rentedGamesObj;
  }
}

