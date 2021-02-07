package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModel;
import model.User;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Login view model.
 */
public class LoginViewModel
{
  private LoginModel model;
  private StringProperty loginUsername;
  private StringProperty loginPassword;

  /**
   * Instantiates a new Login view model.
   *
   * @param model the model
   */
  public LoginViewModel(LoginModel model)
  {
    this.model = model;
    this.loginUsername = new SimpleStringProperty();
    this.loginPassword = new SimpleStringProperty();
  }

  /**
   * Gets login username.
   *
   * @return the login username
   */
  public StringProperty getLoginUsername()
  {
    return loginUsername;
  }

  /**
   * Gets login password.
   *
   * @return the login password
   */
  public StringProperty getLoginPassword()
  {
    return loginPassword;
  }

  /**
   * Login user.
   *
   * @param username the username
   * @param password the password
   * @return the user
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public User login(String username, String password) throws RemoteException, SQLException {
    return model.login(username, password);
  }

  /**
   * Register user.
   *
   * @param username the username
   * @param password the password
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void registerUser(String username, String password) throws RemoteException, SQLException {
    model.registerNewUser(username, password);
  }
}
