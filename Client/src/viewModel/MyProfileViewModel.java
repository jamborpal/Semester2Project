package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type My profile view model.
 */
public class MyProfileViewModel implements PropertyChangeListener
{
  private MyProfileModel model;
  private ObservableList<Game> ownedGames;
  private ObservableList<Game> rentedGames;
  private ObservableList<Rental> rentals;
  private ObservableList<Rental> pendingRentals;
  private StringProperty bio;
  private StringProperty username;

  /**
   * Instantiates a new My profile view model.
   *
   * @param model the model
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   */
  public MyProfileViewModel(MyProfileModel model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.model = model;
    this.ownedGames = FXCollections.observableArrayList();
    this.rentedGames = FXCollections.observableArrayList();
    this.rentals = FXCollections.observableArrayList();
    this.pendingRentals = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    model.addListener(this);
  }

  /**
   * Gets owned games.
   *
   * @return the owned games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Game> getOwnedGames()
      throws RemoteException, SQLException
  {
    GameList allGames = model.getAllGamesFromServer();
    ownedGames.clear();
    for (int i = 0; i < allGames.size(); i++)
    {
      if (allGames.getGame(i).getUserId() == model.getUser().getUserID())
      {
        ownedGames.add(allGames.getGame(i));
      }
    }
    return ownedGames;
  }

  /**
   * Gets user.
   *
   * @return the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public User getUser() throws RemoteException, SQLException
  {
   return model.getUser();
  }

  /**
   * Gets rented games.
   *
   * @return the rented games
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Game> getRentedGames()
      throws RemoteException, SQLException
  {
    GameList rentedGamesList = model.getAllRentedGames(model.getUser());
    rentedGames.clear();
    for (int i = 0; i < rentedGamesList.size(); i++)
    {
      if(!(rentedGamesList.getGame(i).getId() == -1)){
          rentedGames.add(rentedGamesList.getGame(i));
      }
    }
    return rentedGames;
  }
  public void setUserBuffer(Rental rental){
    model.setUserBuffer(rental.getRequester().getUserID());
  }

  /**
   * Gets rentals.
   *
   * @return the rentals
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Rental> getRentals()
      throws RemoteException, SQLException
  {

    RentalList rentalList = model.getRentalList();
    rentals.clear();
    for (int i = 0; i < rentalList.size(); i++)
    {
      if(!(rentalList.getRentals().get(i).getId() == -1)){
        if (rentalList.getRentals().get(i).getOwner().getUserID() == model.getUser().getUserID())
        {
          rentals.add(rentalList.getRentals().get(i));
        }
      }
    }
    return rentals;

  }

  /**
   * Gets pending rentals.
   *
   * @return the pending rentals
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public ObservableList<Rental> getPendingRentals()
      throws RemoteException, SQLException
  {
    RentalList rentalList = model.getRentalList();
    pendingRentals.clear();
    for (int i = 0; i < rentalList.getRentals().size(); i++)
    {
      if(!(rentalList.getRentals().get(i).getId() == -1)){
        if (rentalList.getRentals().get(i).getRequester().getUserID()
                == model.getUser().getUserID())
        {
          pendingRentals.add(rentalList.getRentals().get(i));
        }
      }
    }
    return pendingRentals;
  }

  /**
   * Gets bio.
   *
   * @return the bio
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public StringProperty getBio() throws RemoteException, SQLException
  {
    bio.setValue(
        model.login(model.getUsername(), model.getPassword()).getBio());
    return bio;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public StringProperty getUsername()
  {
    username.setValue(model.getUsername());
    return username;
  }

  /**
   * Remove user.
   *
   * @param user the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void removeUser(User user) throws RemoteException, SQLException
  {
    model.clientRemoveUser(user);
  }

  /**
   * Remove game.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void removeGame(Game game) throws RemoteException, SQLException
  {
    model.clientRemoveGame(game);
  }

  /**
   * Accept game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void acceptGame(Rental rental) throws RemoteException, SQLException
  {
    model.clientAcceptIncomingGame(rental);
  }

  /**
   * Decline game.
   *
   * @param rental the rental
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void declineGame(Rental rental) throws RemoteException, SQLException
  {
    model.clientDeclineIncomingGame(rental);
  }

  /**
   * Sets game available.
   *
   * @param game the game
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void setGameAvailable(Game game) throws RemoteException, SQLException
  {
    model.setGameAvailabilityTrue(game);
  }

  /**
   * Reload.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void reload() throws RemoteException, SQLException{
    getRentals();
    getPendingRentals();
    getRentedGames();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() -> {
      if(evt.getPropertyName().equals("gameRentalUpdate")){
      try {
        reload();
      } catch (RemoteException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    });
  }
}
