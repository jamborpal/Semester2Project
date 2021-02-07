package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.EditProfileModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type Edit profile view model.
 */
public class EditProfileViewModel
{
  private StringProperty bio;
  private EditProfileModel model;

  /**
   * Instantiates a new Edit profile view model.
   *
   * @param model the model
   */
  public EditProfileViewModel(EditProfileModel model)
  {
    this.model = model;
    this.bio = new SimpleStringProperty();
  }

  /**
   * Gets bio.
   *
   * @return the bio
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public StringProperty getBio() throws RemoteException, SQLException {
    bio.setValue(model.login(model.getUsername(), model.getPassword()).getBio());
    return bio;
  }

  /**
   * Edit bio.
   *
   * @param bio the bio
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void editBio(String bio) throws RemoteException, SQLException {
    model.setBio(model.login(model.getUsername(), model.getPassword()), bio);
  }
}
