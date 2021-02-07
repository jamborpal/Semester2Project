package DBSConnection;

import model.Game;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Rented game dao.
 */
public interface RentedGameDAO
{
    /**
     * Gets rented games.
     *
     * @param user the user
     * @return the rented games
     * @throws SQLException the sql exception
     */
    ArrayList<Game> getRentedGames(User user) throws SQLException;

    /**
     * Gets rented game by id.
     *
     * @param id the id
     * @return the rented game by id
     * @throws SQLException the sql exception
     */
    Game getRentedGameById(int id) throws SQLException;

    /**
     * Remove rented game.
     *
     * @param id the id
     * @throws SQLException the sql exception
     */
    void removeRentedGame(int id) throws SQLException;
}
