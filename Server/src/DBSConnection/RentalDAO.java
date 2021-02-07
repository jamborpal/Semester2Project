package DBSConnection;

import model.Game;
import model.Rental;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Rental dao.
 */
public interface RentalDAO
{
    /**
     * Gets rentals.
     *
     * @return the rentals
     * @throws SQLException the sql exception
     */
    ArrayList<Rental> getRentals() throws SQLException;

    /**
     * Add rental.
     *
     * @param owner     the owner
     * @param requester the requester
     * @param game      the game
     * @throws SQLException the sql exception
     */
    void addRental(User owner, User requester, Game game) throws SQLException;

    /**
     * Gets rental by id.
     *
     * @param id the id
     * @return the rental by id
     * @throws SQLException the sql exception
     */
    Rental getRentalById(int id) throws SQLException;

    /**
     * Accept rental.
     *
     * @param rental the rental
     * @throws SQLException the sql exception
     */
    void acceptRental(Rental rental) throws SQLException;

    /**
     * Decline rental.
     *
     * @param rental the rental
     * @throws SQLException the sql exception
     */
    void declineRental(Rental rental) throws SQLException;

    /**
     * Size int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    int size() throws SQLException;
}
