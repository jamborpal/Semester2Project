package mediator;

import model.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


/**
 * The type Game list server.
 */
public class GameListServer implements GameListServerModel
{
    private Model model;
private ThreadSafeServer threadSafeServer;
    private PropertyChangeAction<Game, User> property;

    /**
     * Instantiates a new Game list server.
     *
     * @param model            the model
     * @param threadSafeServer the thread safe server
     */
    public GameListServer(Model model, ThreadSafeServer threadSafeServer) {
        this.model = model;
        this.threadSafeServer = threadSafeServer;
        this.property= new PropertyChangeProxy<>(this, true);
        startServer();
    }

    private void startServer() {
        try {
            startRegistry();
            UnicastRemoteObject.exportObject(this, 1099);
            Naming.rebind("games", this);
            System.out.println("Starting server...");
            Registry registry = LocateRegistry.getRegistry();
            System.out.println(registry.lookup("games"));
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void startRegistry() {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);

            System.out.println("Registry started...");
        } catch (java.rmi.server.ExportException ex) {
            System.out.println("Registry already started? " + ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public GameList getAllGames() throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireRead();
            return model.getFullListOfGames();
        } finally {
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public User getUserByGame(Game game) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireRead();
            return model.getUserByGame(game);
        }
        finally {
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public User getUserByCredentials(String username, String password) throws RemoteException, SQLException {
       try{
           threadSafeServer.acquireRead();
           return model.getUserByCredentials(username, password);
       }
       finally {
           threadSafeServer.releaseRead();
       }
    }

    @Override
    public RentalList getRentalList() throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireRead();
            return model.getRentalList();
        }
        finally {
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public void registerClient(String username, String password) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.registerUser(username, password);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void removeGame(Game game) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.removeGame(game);
            property.firePropertyChange("gameRemoved", game, null);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void setBio(User user, String bio) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.setUserBio(user, bio);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void addGame(Game game) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.addGame(game);
            Thread t = new Thread(() -> {
                try {
                    GameList gameList =  model.getFullListOfGames();
                    Game returnGame = null;
                    for(int i = 0; i < model.getFullListOfGames().size(); i++){
                        if(gameList.getGame(i).getTitle().equals(game.getTitle()) && gameList.getGame(i).getType().equals(game.getType()) && game.getUserId() == gameList.getGame(i).getUserId()){
                            returnGame = gameList.getGame(i);
                        }
                    }
                    property.firePropertyChange("gameAdded", returnGame, null);
                }
      catch (Exception e){

      }
            });
            t.start();

        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void requestGame(User requester, Game game) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.requestGame(requester, game);
            property.firePropertyChange("gameRentalUpdate", null, model.getUserByID(game.getUserId()));
            game.setAvailable(false);
            property.firePropertyChange("gameAvailabilityChange", game, null);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void acceptIncomingGame(Rental rental) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.acceptGame(rental);
            property.firePropertyChange("gameRentalUpdate", null, rental.getRequester());
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void declineIncomingGame(Rental rental) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireWrite();
            model.declineGame(rental);
            property.firePropertyChange("gameRentalUpdate", null, rental.getRequester());
            Game game = rental.getGame();
            game.setAvailable(true);
            property.firePropertyChange("gameAvailabilityChange", game, null);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void setGameAvailableTrue(Game game) throws RemoteException, SQLException {

            threadSafeServer.acquireWrite();
            model.setGameAvailableTrue(game);
            game.setAvailable(true);
            property.firePropertyChange("gameAvailabilityChange", game, null);
            threadSafeServer.releaseWrite();
    }

    @Override
    public GameList getRentedGames(User user) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireRead();
            return model.getRentedGames(user);
        }
        finally{
            threadSafeServer.releaseRead();
        }
    }
    @Override public User getUserById(int userId) throws RemoteException, SQLException {
        try{
            threadSafeServer.acquireRead();
            return model.getUserByID(userId);
        }
        finally{
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public void removeUser(User user) throws RemoteException, SQLException
    {
        try{
            threadSafeServer.acquireWrite();
            model.removeUser(user);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public boolean addListener(GeneralListener<Game, User> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Game, User> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }

}
