package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Other profile view model.
 */
public class OtherProfileViewModel
{
  private StringProperty bio;
  private StringProperty username;
  private ObservableList<Game> ownedGames;
  private OtherProfileModel model;
  private User otherUser;

  /**
   * Instantiates a new Other profile view model.
   *
   * @param model the model
   * @throws RemoteException the remote exception
   */
  public OtherProfileViewModel(OtherProfileModel model) throws RemoteException
  {
    this.model = model;
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.ownedGames = FXCollections.observableArrayList();
  }
  public void setOtherUser() throws RemoteException, SQLException {
    this.otherUser = model.getUserByUserId(model.getUserBuffer());
  }

  /**
   * Gets bio.
   *
   * @return the bio
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public StringProperty getBio() throws RemoteException, SQLException {
    bio.setValue(otherUser.getBio());
    System.out.println(bio.getValue());
    return bio;
  }

  /**
   * Gets username.
   *
   * @return the username
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public StringProperty getUsername() throws RemoteException, SQLException {
    username.setValue(otherUser.getUsername());
    return username;
  }

  /**
   * Gets all other user owned games.
   *
   * @return the all other user owned games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Game> getAllOtherUserOwnedGames() throws RemoteException, SQLException {
    ownedGames.clear();
    GameList allGames = model.getAllGamesFromServer();
    ownedGames.clear();
    for(int i = 0; i < allGames.size(); i++){
      if(allGames.getGame(i).getUserId() == otherUser.getUserID()){
        ownedGames.add(allGames.getGame(i));
      }
    }
    return ownedGames;
  }
}
