package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.GameListClientClient;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The interface Model.
 */
public interface Model
        extends UnnamedPropertyChangeSubject, OtherProfileModel, MyProfileModel,
        LoginModel, GameListModel, EditProfileModel, AddGameModel{
    void registerNewUser(String username, String password) throws RemoteException, SQLException;

    User login(String username, String password) throws RemoteException, SQLException;

    GameList getAllGamesFromServer() throws RemoteException, SQLException;

    User getUser(Game game) throws RemoteException, SQLException;

    User getUser() throws RemoteException, SQLException;

    String getUsername();

    String getPassword();

    void setBio(User user, String bioText) throws RemoteException, SQLException;

    void validateGame(String name, String type, String releaseYear,
                      String availablePeriod, boolean needsDeposit) throws RemoteException, SQLException;

    RentalList getRentalList() throws RemoteException, SQLException;

    void setGameAvailabilityTrue(Game game) throws RemoteException, SQLException;

    GameList getAllRentedGames(User user) throws RemoteException, SQLException;

    public void gameAddedOnServer(Game game) throws RemoteException, SQLException;

    /**
     * Game removed on server.
     *
     * @param game the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void gameRemovedOnServer(Game game) throws RemoteException, SQLException;

    /**
     * Profile update.
     *
     * @param user the user
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void profileUpdate(User user)  throws RemoteException, SQLException;

    /**
     * Game availability update.
     *
     * @param game the game
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    public void gameAvailabilityUpdate(Game game) throws RemoteException, SQLException;

    /**
     * User removed on server.
     *
     * @param user the user
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void userRemovedOnServer(User user) throws RemoteException, SQLException;

    User getUserByUserId(int userId) throws RemoteException, SQLException;

    public void setUserBuffer(int userId);

    public int getUserBuffer();


}
