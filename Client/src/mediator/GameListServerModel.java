package mediator;


import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Game list server model.
 */
//RemoteSubject<GameList, RentalList>
public interface GameListServerModel extends RemoteSubject<Game, User>{

  /**
   * Gets all games.
   *
   * @return the all games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  GameList getAllGames() throws RemoteException, SQLException;

  /**
   * Gets user by game.
   *
   * @param game the game
   * @return the user by game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  User getUserByGame(Game game) throws RemoteException, SQLException;

  /**
   * Gets user by credentials.
   *
   * @param username the username
   * @param password the password
   * @return the user by credentials
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  User getUserByCredentials(String username, String password) throws RemoteException, SQLException;

  /**
   * Gets rental list.
   *
   * @return the rental list
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  RentalList getRentalList() throws RemoteException, SQLException;

  /**
   * Register client.
   *
   * @param username the username
   * @param password the password
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void registerClient(String username, String password) throws RemoteException, SQLException;

  /**
   * Remove game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void removeGame(Game game) throws RemoteException, SQLException;

  /**
   * Sets bio.
   *
   * @param user the user
   * @param bio  the bio
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void setBio(User user, String bio) throws RemoteException, SQLException;

  /**
   * Add game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void addGame(Game game) throws RemoteException, SQLException;

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
   * Accept incoming game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void acceptIncomingGame(Rental rental) throws RemoteException, SQLException;

  /**
   * Decline incoming game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void declineIncomingGame(Rental rental) throws RemoteException, SQLException;

  /**
   * Sets game available true.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void setGameAvailableTrue(Game game) throws RemoteException, SQLException;

  /**
   * Gets rented games.
   *
   * @param user the user
   * @return the rented games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  GameList getRentedGames(User user) throws RemoteException, SQLException;

  /**
   * Remove user.
   *
   * @param user the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void removeUser(User user) throws RemoteException, SQLException;

  /**
   * Gets user by id.
   *
   * @param userId the user id
   * @return the user by id
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public User getUserById(int userId) throws RemoteException, SQLException;
}
