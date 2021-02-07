package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.OtherProfileViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Other profile controller.
 */
public class OtherProfileController
{
  /**
   * The Owned games.
   */
  @FXML ListView<Game> ownedGames;
  /**
   * The Bio.
   */
  @FXML Label bio;
  /**
   * The Username.
   */
  @FXML Label username;
  private OtherProfileViewModel otherProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  /**
   * Init.
   *
   * @param viewHandler           the view handler
   * @param otherProfileViewModel the other profile view model
   * @param root                  the root
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void init(ViewHandler viewHandler,
      OtherProfileViewModel otherProfileViewModel, Region root)
          throws RemoteException, SQLException {
    this.otherProfileViewModel = otherProfileViewModel;
    otherProfileViewModel.setOtherUser();
    this.viewHandler = viewHandler;
    this.root = root;
    this.username.textProperty().bind(otherProfileViewModel.getUsername());
    this.bio.textProperty().bind(otherProfileViewModel.getBio());
    this.bio.setWrapText(true);
    this.ownedGames.setItems(otherProfileViewModel
        .getAllOtherUserOwnedGames());
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
  public void reset() throws RemoteException, SQLException {
    otherProfileViewModel.setOtherUser();
    this.username.textProperty().bind(otherProfileViewModel.getUsername());
    this.bio.textProperty().bind(otherProfileViewModel.getBio());
    this.ownedGames.setItems(otherProfileViewModel
            .getAllOtherUserOwnedGames());
  }
  /**
   * On browse games.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onBrowseGames()
          throws RemoteException, InterruptedException, NotBoundException,
          MalformedURLException, SQLException {
    viewHandler.openView("list");
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
          MalformedURLException, SQLException {
    viewHandler.openView("menu");
  }

  /**
   * On my profile.
   *
   * @throws RemoteException       the remote exception
   * @throws MalformedURLException the malformed url exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onMyProfile()
          throws RemoteException, MalformedURLException, InterruptedException,
          NotBoundException, SQLException {
    viewHandler.openView("profile");
  }
}
