package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.User;
import viewModel.LoginViewModel;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Login screen controller.
 */
public class LoginScreenController
{
  /**
   * The Login username.
   */
  @FXML TextField loginUsername;
  /**
   * The Login password.
   */
  @FXML
  PasswordField loginPassword;
  /**
   * The Error label.
   */
  @FXML Label errorLabel;

  private Region root;
  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;

  /**
   * Init.
   *
   * @param viewHandler    the view handler
   * @param loginViewModel the login view model
   * @param root           the root
   */
  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.loginViewModel = loginViewModel;
    this.errorLabel.setText("");
    this.loginUsername.setText("");
    this.loginPassword.setText("");
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
    loginUsername.setText("");
    loginPassword.setText("");
    errorLabel.setText("");
  }

  /**
   * On login.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   */
  @FXML public void onLogin() throws RemoteException, InterruptedException, NotBoundException, MalformedURLException {

    Platform.runLater(() -> {
      User userBuffer = null;
      try {
        userBuffer = loginViewModel.login(loginUsername.getText(), loginPassword.getText());
      } catch (RemoteException | SQLException e) {
        e.printStackTrace();
      }
      if(userBuffer != null){

        try {
          viewHandler.openView("list");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      else{
        errorLabel.setText("Invalid username or password.");
      }

    });

  }

  /**
   * On register.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  @FXML public void onRegister() throws RemoteException, SQLException {
    loginViewModel.registerUser(loginUsername.getText(), loginPassword.getText());
    errorLabel.setText("User created if username was not taken. You can now try to log in.");
  }

  @FXML public void onEnter() throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
    onLogin();
  }
}

