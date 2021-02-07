package model;

import mediator.GameListClient;
import mediator.GameListClientClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ModelManager implements Model {
    private User user;
    private String username;
    private String password;
    private PropertyChangeSupport property;
    private GameListClientClient gameListClientModel;
    private int userIdBuffer;

  /**
   * Initialising the instance variables.
   *
   * @throws InterruptedException
   */
  public ModelManager() throws InterruptedException {
    this.property = new PropertyChangeSupport(this);
    this.username = "";
    this.password = "";
    this.gameListClientModel = new GameListClient(this);
  }

  /**
   * Registering a new user into the system.
   *
   *@param username The username of the User.
   *@param password The password of the User.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void registerNewUser(String username, String password)
          throws RemoteException, SQLException {
    gameListClientModel.registerNewUser(username, password);
  }
  /**
   * Getting the logged in User by the username and password.
   *
   * @param username The username of the User.
   * @param password The password of the User.
   *
   * @return The logged in User object with the given credentials.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public User login(String username, String password)
          throws RemoteException, SQLException {
    this.username = username;
    this.password = password;
    user = gameListClientModel.login(username, password);
    return gameListClientModel.login(username, password);
  }

  /**
   * Accepting a request for renting a game.
   *
   *@param rental The incoming trade.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void clientAcceptIncomingGame(Rental rental)
          throws RemoteException, SQLException {
    gameListClientModel.clientAcceptIncomingGame(rental);
  }

  /**
   * Declining a request for renting a game.
   *
   *@param rental The incoming trade.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void clientDeclineIncomingGame(Rental rental)
          throws RemoteException, SQLException {
    gameListClientModel.clientDeclineIncomingGame(rental);
  }

  /**
   * Removing a game from the list.
   *
   *@param game The game which is being removed.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void clientRemoveGame(Game game) throws RemoteException, SQLException {
    gameListClientModel.clientRemoveGame(game);
  }

  /**
   * Getting all the games that are in the system.
   *
   * @return The list of games.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public GameList getAllGamesFromServer() throws RemoteException, SQLException {
    return gameListClientModel.getGamesFromServer();
  }
    @Override
    public void setUserBuffer(int userId) {
      userIdBuffer = userId;
    }

  /**
   * Requesting a game.
   *
   *@param requester The user who requests a game.
   * @param game The specific game.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void clientRequestGame(User requester, Game game)
          throws RemoteException, SQLException {
    gameListClientModel.clientRequestGame(requester, game);
  }
    @Override
    public int getUserBuffer() {
        return userIdBuffer;
    }

  /**
   * Getting a User by a game he owns.
   *
   *@param game The specific game.
   *
   * @return The User with the specified game.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public User getUser(Game game) throws RemoteException, SQLException {
    return gameListClientModel.getUserFromServer(game);
  }
  /**
   * Returning the username of the user.
   *
   *@return The username as a String.
   */
  @Override public String getUsername()
  {
    return username;
  }

  /**
   * Returning the password of the user.
   *
   *@return The password as a String.
   */
  @Override public String getPassword()
  {
    return password;
  }

  /**
   * Setting the user's bio.
   *
   *@param user The specific user.
   * @param bioText The input in the bio.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void setBio(User user, String bioText) throws RemoteException, SQLException {
    gameListClientModel.clientSetBio(user, bioText);
  }
  /**
   * Adding a game.
   *
   *@param game The game which is added.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void clientAddGame(Game game) throws RemoteException, SQLException {
    gameListClientModel.clientAddGame(game);
  }

  /**
   * Checking if the information about the game is correct.
   *
   * @param name              The title of the Game.
   * @param type               The type of the Game.
   * @param releaseYear        The year the Game was released.
   * @param availablePeriod For how long the Game can be rented.
   * @param needsDeposit       Whether the Game needs deposit.
   *
   * @throws RemoteException
   * @throws SQLException
   */
    @Override
    public void validateGame(String name, String type,
                             String releaseYear, String availablePeriod, boolean needsDeposit)
            throws RemoteException, SQLException {

        //this is the check for everything not null. If the if is valid, which is not good,
        //the error message is sent

        String result = "";
        if (name == null || type == null || releaseYear == null
                || availablePeriod == null) {
            result += "All fields ought to be filled out!" + "\n";
            property.firePropertyChange("validateGame", null, result);
        }

        //In this else, the system checks for the empty fields and logic of the dates
        //If something fails, the error message is sent, if not, "success" is sent and the game is valid

        else {
            int releaseYearInt = 0;
            try {
                releaseYearInt = Integer.parseInt(releaseYear);
            } catch (Exception e) {
                result += "Release year has to a number" + "\n";
                property.firePropertyChange("validateGame", null, result);
            }
            int availablePeriodInt = 0;
            try {
                availablePeriodInt = Integer.parseInt(availablePeriod);
            } catch (Exception e) {
                result += "Availability period has to be a number" + "\n";
                property.firePropertyChange("validateGame", null, result);
            }

            //here, we created specific date object to help with the check and to also look good in the list

            Game game = new Game(name, type, releaseYearInt, needsDeposit,
                    availablePeriodInt, user.getUserID());
            if (game.getTitle().equals("") || game.getTitle().length() > 150) {
                result += "Title can't be empty or longer than 150 characters." + "\n";
                property.firePropertyChange("validateGame", null, result);
            } else if (game.getType().equals("") || game.getTitle().length() > 50) {
                result += "Type can't be empty or longer than 50 characters" + "\n";
                property.firePropertyChange("validateGame", null, result);
            } else if (!(game.getReleaseYear() > 0) || !(game.getReleaseYear() < 3000)) {
                result += "Release year has to be between 0 - 3000" + "\n";
                property.firePropertyChange("validateGame", null, result);
            } else if (!(game.getAvailabilityPeriod() > 0) || !(game.getAvailabilityPeriod() < 3652)) {
                result +=
                        "Availability period must be larger than a single day and less than 10 years." + "\n";
                property.firePropertyChange("validateGame", null, result);
            } else {
                result = "Success";
                clientAddGame(game);
                property.firePropertyChange("validateGame", null, result);
            }
        }
    }

  /**
   * Returning the list of all rentals.
   *
   * @return The list containing the rentals.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public RentalList getRentalList() throws RemoteException, SQLException {
    return gameListClientModel.clientGetRentalList();
  }

  /**
   * Setting the game's availability to true, so that it can be rented again.
   *
   *@param game The specific game.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override public void setGameAvailabilityTrue(Game game)
          throws RemoteException, SQLException {
    gameListClientModel.setGameAvailableTrue(game);
  }

  /**
   * Returning the list of all games that are rented.
   *
   * @param user the owner of the rented games
   *
   * @return The list containing the ranted games.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public GameList getAllRentedGames(User user) throws RemoteException, SQLException {
    return gameListClientModel.getRentedGames(user);
  }

  /**
   * Deleting a user from the system.
   *
   * @param user The user that will be deleted.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void clientRemoveUser(User user) throws RemoteException, SQLException
  {
    gameListClientModel.clientRemoveUser(user);
  }

  /**
   * Returning the user using the system.
   *
   * @return The user which is logged in.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public User getUser() throws RemoteException, SQLException {
    return user;
  }

  /**
   * Adding a listener when a game is added.
   *
   *@param game The game which has been added.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void gameAddedOnServer(Game game) throws RemoteException, SQLException {
    property.firePropertyChange("gameAdded", null, game);
  }

  /**
   * Adding a listener when a game is removed.
   *
   *@param game The game which has been removed.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void gameRemovedOnServer(Game game) throws RemoteException, SQLException {
    property.firePropertyChange("gameRemoved", null, game);
}

  /**
   * Adding a listener when a profile is being updated.
   *
   *@param user The user who wants his profile updated.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void profileUpdate(User user) throws RemoteException, SQLException {
    property.firePropertyChange("gameRentalUpdate", null, user);
  }

  /**
   * Adding a listener when a game's availability is changed.
   *
   *@param game The specific game.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void gameAvailabilityUpdate(Game game) throws RemoteException, SQLException {
    property.firePropertyChange("gameAvailabilityChange", null, game);
  }

  /**
   * Adding a listener when a user is deleted from the system.
   *
   *@param user The user which has been deleted.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public void userRemovedOnServer(User user) throws RemoteException, SQLException
  {
    property.firePropertyChange("userRemoved", null, user);
  }

  /**
   * Returning a User based on its ID.
   *
   *@param userId The ID of the user.
   *
   * @return The user with the specific ID.
   *
   * @throws RemoteException
   * @throws SQLException
   */
  @Override
  public User getUserByUserId(int userId) throws RemoteException, SQLException {
   return gameListClientModel.getUserById(userId);
  }

  /**
   * Add listener.
   *
   * @param listener The listener.
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  /**
   * Remove listener.
   *
   * @param listener The listener.
   */
  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
}
