package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 * The type Game list client.
 */
public class GameListClient implements GameListClientClient, RemoteListener<Game, User> {

  private int failedConnectionCount;
  private GameListServerModel server;
  private Model model;

  /**
   * Instantiates a new Game list client.
   *
   * @param model the model
   * @throws InterruptedException the interrupted exception
   */
  public GameListClient(Model model) throws InterruptedException {
    this.failedConnectionCount = 0;
    connect();
    this.model = model;
  }

  @Override
  public void connect() throws InterruptedException {
    try {
      this.server = (GameListServerModel) Naming
              .lookup("rmi://localhost:1099/games");
      UnicastRemoteObject.exportObject(this, 0);
      server.addListener(this, "gameAdded", "gameRemoved", "gameRentalUpdate", "gameAvailabilityChange");
    } catch (Exception e) {
      failedConnectionCount++;
      if (failedConnectionCount <= 5) {
        System.out.println(
                "Client failed to connect, attempting to connect in 5 seconds.");
        System.out.println(e);
        Thread.sleep(5000);
        connect();
      } else {
        System.out.println("Connection timed out, exiting.");
        System.exit(0);
      }
    }
  }

  @Override
  public void registerNewUser(String username, String password)
          throws RemoteException, SQLException {
    server.registerClient(username, password);
  }

  @Override
  public User login(String username, String password)
          throws RemoteException, SQLException {
    return server.getUserByCredentials(username, password);
  }

  @Override
  public GameList getGamesFromServer() throws RemoteException, SQLException {
    return server.getAllGames();
  }

  @Override
  public RentalList clientGetRentalList() throws RemoteException, SQLException {
    return server.getRentalList();
  }

  @Override
  public void clientRemoveGame(Game game) throws RemoteException, SQLException {
    server.removeGame(game);
  }

  @Override
  public void clientSetBio(User user, String bio)
          throws RemoteException, SQLException {
    server.setBio(user, bio);
  }

  @Override
  public void clientAddGame(Game game) throws RemoteException, SQLException {
    server.addGame(game);
  }

  @Override
  public void clientRequestGame(User requester, Game game)
          throws RemoteException, SQLException {
    server.requestGame(requester, game);
  }

  @Override
  public void clientAcceptIncomingGame(Rental rental)
          throws RemoteException, SQLException {
    server.acceptIncomingGame(rental);
  }

  @Override
  public void clientDeclineIncomingGame(Rental rental)
          throws RemoteException, SQLException {
    server.declineIncomingGame(rental);
  }

  @Override
  public User getUserFromServer(Game game) throws RemoteException, SQLException {
    return server.getUserByGame(game);
  }

  @Override
  public void setGameAvailableTrue(Game game) throws RemoteException, SQLException {
    server.setGameAvailableTrue(game);
  }

  @Override
  public GameList getRentedGames(User user) throws RemoteException, SQLException {
    return server.getRentedGames(user);
  }

  @Override
  public void clientRemoveUser(User user) throws RemoteException, SQLException
  {
    server.removeUser(user);
  }

  @Override
  public User getUserById(int userId) throws RemoteException, SQLException {
    return server.getUserById(userId);
  }

  @Override
  public void propertyChange(ObserverEvent<Game, User> event) throws RemoteException {

    switch (event.getPropertyName()) {
      case "gameAdded":
        try {
          model.gameAddedOnServer(event.getValue1());
        } catch (SQLException e) {
          e.printStackTrace();
        }
        break;
      case "gameRemoved":
        try {
          model.gameRemovedOnServer(event.getValue1());
        } catch (SQLException e) {
          e.printStackTrace();
        }
      case "gameRentalUpdate":
        try {
          if (event.getValue2().getUserID() == model.login(model.getUsername(), model.getPassword()).getUserID()) {
            try {
              model.profileUpdate(event.getValue2());
            } catch (SQLException e) {
              e.printStackTrace();
            }
          }

        } catch (Exception e) {

        }
        break;
      case "gameAvailabilityChange":
        try {
          model.gameAvailabilityUpdate(event.getValue1());
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
  }
}
