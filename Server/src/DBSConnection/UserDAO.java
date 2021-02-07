package DBSConnection;

import model.User;

import java.sql.SQLException;

/**
 * The interface User dao.
 */
public interface UserDAO
{
    /**
     * Gets user by user id.
     *
     * @param id the id
     * @return the user by user id
     * @throws SQLException the sql exception
     */
    User getUserByUserID(int id) throws SQLException;

    /**
     * Gets user.
     *
     * @param user the user
     * @return the user
     * @throws SQLException the sql exception
     */
    User getUser(User user) throws SQLException;

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
     * Register user.
     *
     * @param username the username
     * @param password the password
     * @throws SQLException the sql exception
     */
    void registerUser(String username, String password) throws SQLException;

    /**
     * Gets .
     *
     * @return the
     * @throws SQLException the sql exception
     */
    int size() throws SQLException;

    /**
     * Sets bio.
     *
     * @param user the user
     * @param bio  the bio
     * @throws SQLException the sql exception
     */
    void setBio(User user, String bio) throws SQLException;

    /**
     * Gets user.
     *
     * @param user the user
     * @throws SQLException the sql exception
     */
    void removeUser(User user) throws SQLException;

}
