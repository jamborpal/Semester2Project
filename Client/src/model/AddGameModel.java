package model;

import Utility.UnnamedPropertyChangeSubject;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Add game model.
 */
public interface AddGameModel extends UnnamedPropertyChangeSubject {
  /**
   * Client add game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void clientAddGame(Game game) throws RemoteException, SQLException;

  /**
   * Validate game.
   *
   * @param name            the name
   * @param type            the type
   * @param releaseYear     the release year
   * @param availablePeriod the available period
   * @param needsDeposit    the needs deposit
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  void validateGame(String name, String type, String releaseYear,
      String availablePeriod, boolean needsDeposit) throws RemoteException, SQLException;
}
