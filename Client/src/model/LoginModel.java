package model;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Login model.
 */
public interface LoginModel
{
  /**
   * Register new user.
   *
   * @param username the username
   * @param password the password
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void registerNewUser(String username, String password) throws RemoteException, SQLException;

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
}
