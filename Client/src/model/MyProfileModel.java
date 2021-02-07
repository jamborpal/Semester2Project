package model;

import Utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface My profile model.
 */
public interface MyProfileModel extends UnnamedPropertyChangeSubject
{
  /**
   * Login user.
   *
   * @param username the username
   * @param password the password
   * @return the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  User login(String username, String password) throws RemoteException, SQLException;

  /**
   * Client accept incoming game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void clientAcceptIncomingGame(Rental rental) throws RemoteException, SQLException;

  /**
   * Client decline incoming game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void clientDeclineIncomingGame(Rental rental) throws RemoteException, SQLException;

  /**
   * Client remove game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void clientRemoveGame(Game game) throws RemoteException, SQLException;

  /**
   * Gets username.
   *
   * @return the username
   */
  String getUsername();

  /**
   * Gets password.
   *
   * @return the password
   */
  String getPassword();

  public void setUserBuffer(int userId);

  /**
   * Gets rental list.
   *
   * @return the rental list
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  RentalList getRentalList() throws RemoteException, SQLException;

  /**
   * Sets game availability true.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void setGameAvailabilityTrue(Game game) throws RemoteException, SQLException;

  /**
   * Gets all games from server.
   *
   * @return the all games from server
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  GameList getAllGamesFromServer() throws RemoteException, SQLException;

  /**
   * Gets all rented games.
   *
   * @param user the user
   * @return the all rented games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  GameList getAllRentedGames(User user) throws RemoteException, SQLException;

  /**
   * Client remove user.
   *
   * @param user the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void clientRemoveUser(User user) throws RemoteException, SQLException;

  /**
   * Gets user.
   *
   * @return the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  User getUser() throws RemoteException, SQLException;
}
