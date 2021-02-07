import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mediator.GameListClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.*;

public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        Model model = new ModelManager();
        GameMenuViewModel gameMenuViewModel = new GameMenuViewModel(model);
        GameListViewModel gameListViewModel = new GameListViewModel(model);
        LoginViewModel loginViewModel = new LoginViewModel(model);

        EditProfileViewModel editProfileViewModel = new EditProfileViewModel(model);
        MyProfileViewModel myProfileViewModel = new MyProfileViewModel(model);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);

        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(stage);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
