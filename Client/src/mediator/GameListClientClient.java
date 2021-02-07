package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;


/**
 * The interface Game list client client.
 */
public interface GameListClientClient
{
    /**
     * Connect.
     *
     * @throws InterruptedException the interrupted exception
     */
    void connect() throws InterruptedException;

    /**
     * Register new user.
     *
     * @param username the username
     * @param password the password
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void registerNewUser(String username, String password)
            throws RemoteException, SQLException;

    /**
     * Login user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    User login(String username, String password) throws
            RemoteException, SQLException;

    /**
     * Gets games from server.
     *
     * @return the games from server
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    GameList getGamesFromServer() throws RemoteException, SQLException;

    /**
     * Client get rental list rental list.
     *
     * @return the rental list
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    RentalList clientGetRentalList() throws RemoteException, SQLException;

    /**
     * Client remove game.
     *
     * @param game the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void clientRemoveGame(Game game) throws RemoteException, SQLException;

    /**
     * Client set bio.
     *
     * @param user the user
     * @param bio  the bio
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void clientSetBio(User user, String bio) throws RemoteException, SQLException;

    /**
     * Client add game.
     *
     * @param game the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void clientAddGame(Game game) throws RemoteException, SQLException;

    /**
     * Client request game.
     *
     * @param requester the requester
     * @param game      the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void clientRequestGame(User requester, Game game) throws RemoteException, SQLException;

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
     * Gets user from server.
     *
     * @param game the game
     * @return the user from server
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    User getUserFromServer(Game game) throws RemoteException, SQLException;

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
     * Client remove user.
     *
     * @param user the user
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void clientRemoveUser(User user) throws RemoteException, SQLException;

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
