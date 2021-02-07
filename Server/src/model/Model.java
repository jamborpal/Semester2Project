package model;

import utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Model.
 */
public interface Model
{
  /**
   * Gets user by id.
   *
   * @param userId the user id
   * @return the user by id
   * @throws SQLException the sql exception
   */
  User getUserByID(int userId) throws SQLException;

  /**
   * Gets user by credentials.
   *
   * @param username the username
   * @param password the password
   * @return the user by credentials
   * @throws SQLException the sql exception
   */
  User getUserByCredentials(String username, String password) throws SQLException;

  /**
   * Sets user bio.
   *
   * @param user the user
   * @param bio  the bio
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void setUserBio(User user, String bio) throws RemoteException, SQLException;

  /**
   * Request game.
   *
   * @param requester the requester
   * @param game      the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void requestGame(User requester, Game game) throws RemoteException, SQLException;

  /**
   * Accept game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void acceptGame(Rental rental) throws RemoteException, SQLException;

  /**
   * Decline game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void declineGame(Rental rental) throws RemoteException, SQLException;

  /**
   * Gets full list of games.
   *
   * @return the full list of games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  GameList getFullListOfGames() throws RemoteException, SQLException;

  /**
   * Gets rented games.
   *
   * @param user the user
   * @return the rented games
   * @throws SQLException the sql exception
   */
  GameList getRentedGames(User user) throws SQLException;

  /**
   * Add game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void addGame(Game game) throws RemoteException, SQLException;

  /**
   * Remove game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void removeGame(Game game) throws RemoteException, SQLException;

  /**
   * Gets game by index.
   *
   * @param index the index
   * @return the game by index
   */
  Game getGameByIndex(int index);

  /**
   * Gets game by id.
   *
   * @param gameID the game id
   * @return the game by id
   * @throws SQLException the sql exception
   */
  Game getGameByID(int gameID) throws SQLException;

  /**
   * Gets user by game.
   *
   * @param game the game
   * @return the user by game
   * @throws SQLException the sql exception
   */
  User getUserByGame(Game game) throws SQLException;

  /**
   * Gets size of game list.
   *
   * @return the size of game list
   * @throws SQLException the sql exception
   */
  int getSizeOfGameList() throws SQLException;

  /**
   * Gets rental list.
   *
   * @return the rental list
   * @throws SQLException the sql exception
   */
  RentalList getRentalList() throws SQLException;

  /**
   * Register user.
   *
   * @param username the username
   * @param password the password
   * @throws SQLException the sql exception
   */
  void registerUser(String username, String password) throws SQLException;

  /**
   * Sets game available true.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void setGameAvailableTrue(Game game) throws RemoteException, SQLException;

  /**
   * Remove user.
   *
   * @param user the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void removeUser(User user) throws RemoteException, SQLException;

}
