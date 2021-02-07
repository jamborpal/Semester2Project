package viewModel;

import model.Model;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * The type View model factory.
 */
public class ViewModelFactory
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;
  private MyProfileViewModel myProfileViewModel;
  private EditProfileViewModel editProfileViewModel;
  private LoginViewModel loginViewModel;
  private OtherProfileViewModel otherProfileViewModel;

  /**
   * Instantiates a new View model factory.
   *
   * @param model the model
   * @throws RemoteException       the remote exception
   * @throws InterruptedException  the interrupted exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   */
  public ViewModelFactory(Model model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.gameListViewModel = new GameListViewModel(model);
    this.gameMenuViewModel = new GameMenuViewModel(model);
    this.myProfileViewModel = new MyProfileViewModel(model);
    this.editProfileViewModel = new EditProfileViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.otherProfileViewModel = new OtherProfileViewModel(model);
  }

  /**
   * Game menu view model game menu view model.
   *
   * @return the game menu view model
   */
  public GameMenuViewModel gameMenuViewModel()
  {
    return gameMenuViewModel;
  }

  /**
   * Game list view model game list view model.
   *
   * @return the game list view model
   */
  public GameListViewModel gameListViewModel()
  {
    return gameListViewModel;
  }

  /**
   * My profile view model my profile view model.
   *
   * @return the my profile view model
   */
  public MyProfileViewModel myProfileViewModel()
  {
    return myProfileViewModel;
  }

  /**
   * Edit profile view model edit profile view model.
   *
   * @return the edit profile view model
   */
  public EditProfileViewModel editProfileViewModel()
  {
    return editProfileViewModel;
  }

  /**
   * Loading screen view model login view model.
   *
   * @return the login view model
   */
  public LoginViewModel loadingScreenViewModel()
  {
    return loginViewModel;
  }

  /**
   * Other profile view model other profile view model.
   *
   * @return the other profile view model
   */
  public OtherProfileViewModel otherProfileViewModel()
  {
    return otherProfileViewModel;
  }

}
