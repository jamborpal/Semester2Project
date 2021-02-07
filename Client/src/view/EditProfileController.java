package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import viewModel.EditProfileViewModel;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Edit profile controller.
 */
public class EditProfileController
{
  /**
   * The Text area.
   */
  @FXML TextArea textArea;

  private Region root;

  private ViewHandler viewHandler;
  private EditProfileViewModel editProfileViewModel;

  /**
   * Init.
   *
   * @param viewHandler          the view handler
   * @param editProfileViewModel the edit profile view model
   * @param root                 the root
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void init(ViewHandler viewHandler,
      EditProfileViewModel editProfileViewModel, Region root) throws RemoteException, SQLException {
    this.root = root;
    this.viewHandler = viewHandler;
    this.editProfileViewModel = editProfileViewModel;
    this.textArea.setText(editProfileViewModel.getBio().getValue());
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
   * On cancel.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onCancel()
          throws RemoteException, InterruptedException, NotBoundException,
          MalformedURLException, SQLException {
    viewHandler.openView("profile");
  }

  /**
   * On save.
   *
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   * @throws SQLException          the sql exception
   */
  @FXML public void onSave()
          throws RemoteException, InterruptedException, NotBoundException,
          MalformedURLException, SQLException {
    editProfileViewModel.editBio(textArea.getText());
    viewHandler.openView("profile");
  }
}
