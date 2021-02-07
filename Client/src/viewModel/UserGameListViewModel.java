//package viewModel;
//
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import model.Game;
//import model.GameList;
//import model.Model;
//
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.rmi.RemoteException;
//
//public class UserGameListViewModel
//{
//  private Model model;
//  private ObservableList<Game> list;
//
//  public UserGameListViewModel(Model model)
//  {
//    this.model = model;
//    this.list = FXCollections.observableArrayList();
//  }
//
//  public ObservableList<Game> getList() throws RemoteException
//  {
//    GameList games = model.GetGameList();
//    list.clear();
//    for (int i = 0; i < games.size(); i++)
//    {
//      list.add(games.getGame(i));
//    }
//    return list;
//  }
//
//  public void removeGame(Game game) throws RemoteException
//  {
//    model.RemoveGame(game.getId());
//  }
//
//}
