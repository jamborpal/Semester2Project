package model;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Other profile model.
 */
public interface OtherProfileModel
{
  /**
   * Gets user.
   *
   * @param game the game
   * @return the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  User getUser(Game game) throws RemoteException, SQLException;
  public int getUserBuffer();
  GameList getAllGamesFromServer() throws RemoteException, SQLException;

  /**
   * Gets user by user id.
   *
   * @param userId the user id
   * @return the user by user id
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  User getUserByUserId(int userId) throws RemoteException, SQLException;
}
