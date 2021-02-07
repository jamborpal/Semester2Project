package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import model.Rental;
import model.User;
import viewModel.MyProfileViewModel;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * The type My profile controller.
 */
public class MyProfileController {
    /**
     * The Owned games.
     */
    @FXML
    ListView<Game> ownedGames;
    /**
     * The Rentals.
     */
    @FXML
    ListView<Rental> rentals;
    /**
     * The Rented games.
     */
    @FXML
    ListView<Game> rentedGames;
    /**
     * The Pending rentals.
     */
    @FXML
    ListView<Rental> pendingRentals;
    /**
     * The Bio.
     */
    @FXML
    Label bio;
    /**
     * The Username.
     */
    @FXML
    Label username;
    private MyProfileViewModel myProfileViewModel;
    private Region root;
    private ViewHandler viewHandler;

    /**
     * Init.
     *
     * @param viewHandler        the view handler
     * @param myProfileViewModel the my profile view model
     * @param root               the root
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    public void init(ViewHandler viewHandler,
                     MyProfileViewModel myProfileViewModel, Region root)
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        this.viewHandler = viewHandler;
        this.root = root;
        this.myProfileViewModel = myProfileViewModel;
        this.username.textProperty().bind(myProfileViewModel.getUsername());
        this.rentedGames.setItems(myProfileViewModel.getRentedGames());
        this.pendingRentals.setItems(myProfileViewModel.getPendingRentals());
        this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
        this.rentals.setItems(myProfileViewModel.getRentals());
        this.bio.textProperty().bind(myProfileViewModel.getBio());
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public Region getRoot() {
        return root;
    }

    /**
     * Reset.
     *
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    public void reset()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
        this.rentals.setItems(myProfileViewModel.getRentals());
        this.rentedGames.setItems(myProfileViewModel.getRentedGames());
        this.bio.textProperty().bind(myProfileViewModel.getBio());
        this.bio.setWrapText(true);
        this.pendingRentals.setItems(myProfileViewModel.getPendingRentals());
    }

    /**
     * On add game.
     *
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    @FXML
    public void onAddGame()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("menu");
    }

    /**
     * On browse games.
     *
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    @FXML
    public void onBrowseGames()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("list");
    }

    /**
     * On delete.
     *
     * @throws RemoteException the remote exception
     * @throws SQLException    the sql exception
     */
    @FXML
    public void onDelete() throws RemoteException, SQLException {
        if (ownedGames.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            Game selectedGame = ownedGames.getSelectionModel().getSelectedItem();
            myProfileViewModel.removeGame(selectedGame);
            int index = ownedGames.getSelectionModel().getSelectedIndex();
            if (ownedGames.getSelectionModel().getSelectedItem() == null) {
                this.myProfileViewModel.getOwnedGames()
                        .remove(index);
            }
            this.myProfileViewModel.getOwnedGames().clear();
            this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
        }
    }

    /**
     * On accept.
     *
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    @FXML
    public void onAccept()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        if (rentals.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a incoming trade.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
            myProfileViewModel.acceptGame(selectedRental);
            int index = rentals.getSelectionModel().getSelectedIndex();
            rentals.setItems(myProfileViewModel.getPendingRentals());
        }
    }

    /**
     * On decline.
     *
     * @throws RemoteException       the remote exception
     * @throws SQLException          the sql exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     */
    @FXML
    public void onDecline() throws RemoteException, SQLException, InterruptedException, NotBoundException, MalformedURLException {
        if (rentals.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
            int index = rentals.getSelectionModel().getSelectedIndex();
            if (index == 0) {
                myProfileViewModel.declineGame(selectedRental);
            }
            this.myProfileViewModel.getRentals().clear();
            this.rentals.setItems(myProfileViewModel.getRentals());
        }
    }

    /**
     * On edit.
     *
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    @FXML
    public void onEdit()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("editBio");
    }

    /**
     * On set available.
     *
     * @throws RemoteException       the remote exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     * @throws SQLException          the sql exception
     */
    @FXML
    public void onSetAvailable() throws RemoteException, InterruptedException, NotBoundException, MalformedURLException, SQLException {
        if (ownedGames.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else if (ownedGames.getSelectionModel().getSelectedItem().getAvailable()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game that is not available", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            myProfileViewModel.setGameAvailable(ownedGames.getSelectionModel().getSelectedItem());
            reset();
        }
    }


    /**
     * On delete user.
     *
     * @throws RemoteException       the remote exception
     * @throws SQLException          the sql exception
     * @throws InterruptedException  the interrupted exception
     * @throws NotBoundException     the not bound exception
     * @throws MalformedURLException the malformed url exception
     */
    @FXML
    public void onDeleteUser() throws RemoteException, SQLException, InterruptedException, NotBoundException, MalformedURLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to proceed?", ButtonType.YES);
        alert.showAndWait();
        alert.close();
        if (alert.getResult() == ButtonType.YES) {
            User selectedUser = myProfileViewModel.getUser();
            myProfileViewModel.removeUser(selectedUser);
            viewHandler.openView("loading");
        }
    }

    public void onViewOtherProfile() throws RemoteException, MalformedURLException, SQLException, InterruptedException, NotBoundException {
        if (rentals.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select an incoming trade from the incoming trades list.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
            myProfileViewModel.setUserBuffer(rentals.getSelectionModel().getSelectedItem());
            viewHandler.openView("other");
        }
    }
}
