package model;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The interface Edit profile model.
 */
public interface EditProfileModel {
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

    /**
     * Sets bio.
     *
     * @param user    the user
     * @param bioText the bio text
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    void setBio(User user, String bioText) throws RemoteException, SQLException;
}
