package model;

import Utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Game list model.
 */
public interface GameListModel extends UnnamedPropertyChangeSubject{
    /**
     * Gets all games from server.
     *
     * @return the all games from server
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    GameList getAllGamesFromServer() throws RemoteException, SQLException;

    /**
     * Client request game.
     *
     * @param requester the requester
     * @param game      the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void clientRequestGame(User requester, Game game) throws RemoteException, SQLException;

    public void setUserBuffer(int userId);

    User getUser() throws RemoteException, SQLException;

    String getUsername();

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword();

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
     * Game added on server.
     *
     * @param game the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void gameAddedOnServer(Game game) throws RemoteException, SQLException;
}
