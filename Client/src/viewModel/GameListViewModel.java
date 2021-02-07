package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.GameListModel;
import model.User;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Game list view model.
 */
public class GameListViewModel implements PropertyChangeListener
{
  private GameListModel model;
  private ObservableList<Game> availableGames;
  private StringProperty search;

  /**
   * Instantiates a new Game list view model.
   *
   * @param model the model
   */
  public GameListViewModel(GameListModel model)
  {
    this.model = model;
    this.availableGames = FXCollections.observableArrayList();
    this.search = new SimpleStringProperty();
    model.addListener(this);
  }

  /**
   * Gets search.
   *
   * @return the search
   */
  public StringProperty getSearch()
  {
    return search;
  }

  /**
   * Gets available games.
   *
   * @return the available games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Game> getAvailableGames() throws RemoteException, SQLException {
        GameList gameList = model.getAllGamesFromServer();
        availableGames.clear();
        for (int i = 0; i < gameList.size(); i++) {
            if(!(gameList.getGame(i).getId() == -1)){
                availableGames.add(gameList.getGame(i));
            }
        }
        return availableGames;
    }

  /**
   * Request trade.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void requestTrade(Game game) throws RemoteException, SQLException
  {
    model.clientRequestGame(
        model.login(model.getUsername(), model.getPassword()), game);
  }

  public void setUserBuffer(Game game) throws RemoteException
  {
    model.setUserBuffer(game.getUserId());
  }

  /**
   * Update game availability.
   *
   * @param game the game
   */
  public void updateGameAvailability(Game game)
  {
    for (int i = 0; i < availableGames.size(); i++)
    {
      if (availableGames.get(i).getId() == game.getId())
      {
        availableGames.remove(i);
        availableGames.add(game);
      }
    }
  }

  /**
   * Gets specific games.
   *
   * @return the specific games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Game> getSpecificGames()
      throws RemoteException, SQLException
  {
    GameList gameList = model.getAllGamesFromServer();
    availableGames.clear();
    for (int i = 0; i < gameList.size(); i++)
    {
      String s = gameList.getGame(i).getTitle();
      if (!(gameList.getGame(i).getId() == -1) &&
          (s.toLowerCase().contains(search.getValue().toLowerCase()) ||
              s.toUpperCase().contains(search.getValue().toUpperCase())))
      {
        availableGames.add(gameList.getGame(i));
      }
      else if(search.getValue().equals(""))
      {
       return getAvailableGames();
      }
    }
    return availableGames;
  }

  /**
   * Gets sorted games.
   *
   * @return the sorted games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Game> getSortedGames()
      throws RemoteException, SQLException
  {
    GameList gameList = model.getAllGamesFromServer();
    availableGames.clear();
    for (int i = 0; i < gameList.size(); i++)
    {
      if (!(gameList.getGame(i).getId() == -1))
      {
        availableGames.add(gameList.getGame(i));
      }
    }
    return availableGames.sorted();
  }

  /**
   * Add game to game list.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void addGameToGameList(Game game) throws RemoteException, SQLException
  {
    boolean hasCopy = false;
    for(int i =0; i < availableGames.size(); i++)
    {
      if(availableGames.get(i).getId() == game.getId())
      hasCopy = true;
    }
    if(!hasCopy){
      availableGames.add(game);
    }
  }

  /**
   * Remove game from game list.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void removeGameFromGameList(Game game)
          throws RemoteException, SQLException
  {
    for (int i = 0; i < availableGames.size(); i++)
    {
      if (availableGames.get(i).getTitle().equals(game.getTitle()) && game
              .getType().equals(availableGames.get(i).getType())
              && availableGames.get(i).getUserId() == game.getUserId())
      {
        availableGames.remove(i);
      }
    }
  }
  public User getLocalUser() throws RemoteException, SQLException {
    return model.getUser();
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "gameAdded":
          try
          {
            addGameToGameList((Game) evt.getNewValue());
          }
          catch (RemoteException | SQLException ex)
          {
            ex.printStackTrace();
          }
          break;
        case "gameRemoved":
          try
          {
            removeGameFromGameList((Game) evt.getNewValue());
          }
          catch (RemoteException | SQLException e)
          {
            e.printStackTrace();
          }
          break;
        case "gameAvailabilityChange":
          updateGameAvailability((Game) evt.getNewValue());
      }
    });

  }
}