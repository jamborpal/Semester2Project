package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Game list controller.
 */
public class GameListController
{

  /**
   * The Available games.
   */
  @FXML ListView<Game> availableGames;
  /**
   * The Search.
   */
  @FXML TextField search;
  private Region root;
  private ViewHandler viewHandler;
  private GameListViewModel gameListViewModel;

  /**
   * Init.
   *
   * @param viewHandler       the view handler
   * @param gameListViewModel the game list view model
   * @param root              the root
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void init(ViewHandler viewHandler, GameListViewModel gameListViewModel,
      Region root) throws RemoteException, SQLException
  {
    this.search.textProperty().bindBidirectional(gameListViewModel.getSearch());
    this.root = root;
    this.viewHandler = viewHandler;
    this.gameListViewModel = gameListViewModel;
    this.availableGames.setItems(gameListViewModel.getAvailableGames());
  }

  /**
   * Gets root.
   *
   * @return the root
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * Reset.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void reset() throws RemoteException, SQLException
  {
    this.availableGames.setItems(gameListViewModel.getAvailableGames());
  }

  /**
   * On my profile.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onMyProfile()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException, SQLException
  {
    viewHandler.openView("profile");
  }

  /**
   * On add game.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onAddGame()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException, SQLException
  {
    viewHandler.openView("menu");
  }

  /**
   * Request game.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  @FXML public void requestGame() throws RemoteException, SQLException
  {
    if (availableGames.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else if (!availableGames.getSelectionModel().getSelectedItem()
        .getAvailable())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a available game", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else if(availableGames.getSelectionModel().getSelectedItem().getUserId() == gameListViewModel.getLocalUser().getUserID()){
      Alert alert = new Alert(Alert.AlertType.ERROR,
              "You can't request your own game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      gameListViewModel
          .setUserBuffer(availableGames.getSelectionModel().getSelectedItem());
      gameListViewModel
          .requestTrade(availableGames.getSelectionModel().getSelectedItem());
      reset();
    }
  }

  /**
   * On other profile.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onOtherProfile()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException, SQLException
  {
    if (availableGames.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      gameListViewModel
          .setUserBuffer(availableGames.getSelectionModel().getSelectedItem());
      viewHandler.openView("other");
    }
  }

  /**
   * On search.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  @FXML public void onSearch() throws RemoteException, SQLException
  {
    availableGames.setItems(gameListViewModel.getSpecificGames());
  }

  /**
   * On sort.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  @FXML public void onSort() throws RemoteException, SQLException
  {
    availableGames.setItems(gameListViewModel.getSortedGames());
  }

  @FXML public void onEnterSearch() throws RemoteException, SQLException {
    onSearch();
  }
}
