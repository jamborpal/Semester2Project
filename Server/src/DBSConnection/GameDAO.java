package DBSConnection;

import model.Game;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Game dao.
 */
public interface GameDAO
{
    /**
     * Add game.
     *
     * @param title              the title
     * @param type               the type
     * @param releaseYear        the release year
     * @param needsDeposit       the needs deposit
     * @param availabilityPeriod the availability period
     * @param userId             the user id
     * @throws SQLException the sql exception
     */
    void addGame(String title, String type, int releaseYear, boolean needsDeposit,
                 int availabilityPeriod, int userId) throws SQLException;

    /**
     * Remove game.
     *
     * @param game the game
     * @throws SQLException the sql exception
     */
    void removeGame(Game game) throws SQLException;

    /**
     * Gets game by id.
     *
     * @param id the id
     * @return the game by id
     * @throws SQLException the sql exception
     */
    Game getGameById(int id) throws SQLException;

    /**
     * Gets .
     *
     * @return the
     * @throws SQLException the sql exception
     */
   int size() throws SQLException;

    /**
     * Gets available games.
     *
     * @return the available games
     * @throws SQLException the sql exception
     */
    ArrayList<Game> getAvailableGames() throws SQLException;

    /**
     * Gets unavailable games.
     *
     * @return the unavailable games
     * @throws SQLException the sql exception
     */
    ArrayList<Game> getUnavailableGames() throws SQLException;

    /**
     * Sets available.
     *
     * @param game the game
     * @throws SQLException the sql exception
     */
    void setAvailable(Game game) throws SQLException;
}
