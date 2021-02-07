package view;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewModel.GameMenuViewModel;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Game menu controller.
 */
public class GameMenuController
{
  /**
   * The Availability period.
   */
  @FXML TextField availabilityPeriod;
  /**
   * The Name.
   */
  @FXML TextField name;
  /**
   * The Type.
   */
  @FXML TextField type;
  /**
   * The Release year.
   */
  @FXML TextField releaseYear;
  /**
   * The Deposit.
   */
  @FXML CheckBox deposit;

  private Region root;
  private ViewHandler viewHandler;
  private GameMenuViewModel gameMenuViewModel;
  private StringProperty responseMessage;


  /**
   * Init.
   *
   * @param viewHandler       the view handler
   * @param gameMenuViewModel the game menu view model
   * @param root              the root
   */
  public void init(ViewHandler viewHandler, GameMenuViewModel gameMenuViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.gameMenuViewModel = gameMenuViewModel;
    this.name.textProperty().bindBidirectional(gameMenuViewModel.getName());
    this.type.textProperty().bindBidirectional(gameMenuViewModel.getType());
    this.releaseYear.textProperty()
        .bindBidirectional(gameMenuViewModel.getReleaseYear());
    this.availabilityPeriod.textProperty()
        .bindBidirectional(gameMenuViewModel.getAvailabilityPeriod());
    this.deposit.allowIndeterminateProperty()
        .bindBidirectional(gameMenuViewModel.getCheckBox());
    this.responseMessage = gameMenuViewModel.getResponseMessage();
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
   */
  public void reset()
  {
    name.clear();
    type.clear();
    releaseYear.clear();
    deposit.setSelected(false);
    availabilityPeriod.clear();
  }

  /**
   * On reset.
   */
  @FXML public void onReset()
  {
    reset();
  }

  /**
   * On submit.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  @FXML public void onSubmit() throws RemoteException, SQLException {
    gameMenuViewModel.addCurrentGame();
    Platform.runLater(() -> {
      try
      {
        checkGame();
      }
      catch (RemoteException | InterruptedException | NotBoundException | MalformedURLException | SQLException e)
      {
        e.printStackTrace();
      }
    });

  }

  /**
   * Check game.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  public void checkGame()
          throws RemoteException, InterruptedException, NotBoundException,
          MalformedURLException, SQLException {
    System.out.println(responseMessage);
    if (responseMessage.getValue().equals("Success"))
    {
      viewHandler.openView("list");
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, responseMessage.getValue(),
          ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
  }

  /**
   * On back.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onBack()
          throws RemoteException, InterruptedException, NotBoundException,
          MalformedURLException, SQLException {
    viewHandler.openView("list");
  }
}
