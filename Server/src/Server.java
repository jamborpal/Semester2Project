import mediator.GameListServer;
import mediator.ThreadSafeServer;
import model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class Server
{
  public static void main(String[] args) throws RemoteException, SQLException {
    Model model = new ModelManager();
    ThreadSafeServer server = new ThreadSafeServer();
    GameListServer gameListServer = new GameListServer(model, server);
  }
}
