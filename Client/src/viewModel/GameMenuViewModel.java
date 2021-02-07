package viewModel;


import javafx.beans.property.*;
import model.AddGameModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;


/**
 * The type Game menu view model.
 */
public class GameMenuViewModel implements PropertyChangeListener
{
  private StringProperty title;
  private StringProperty type;
  private StringProperty releaseYear;
  private StringProperty availabilityPeriod;
  private BooleanProperty checkBox;
  private StringProperty responseMessage;

  private AddGameModel model;

  /**
   * Instantiates a new Game menu view model.
   *
   * @param model the model
   */
  public GameMenuViewModel(AddGameModel model)
  {
    this.model = model;
    this.title = new SimpleStringProperty();
    this.type = new SimpleStringProperty();
    this.releaseYear = new SimpleStringProperty();
    this.availabilityPeriod = new SimpleStringProperty();
    this.checkBox = new SimpleBooleanProperty();
    this.responseMessage = new SimpleStringProperty();
    model.addListener(this);
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public StringProperty getName()
  {
    return title;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public StringProperty getType()
  {
    return type;
  }

  /**
   * Gets release year.
   *
   * @return the release year
   */
  public StringProperty getReleaseYear()
  {
    return releaseYear;
  }

  /**
   * Gets from date.
   *
   * @return the from date
   */
  public StringProperty getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  /**
   * Gets check box.
   *
   * @return the check box
   */
  public BooleanProperty getCheckBox()
  {
    return checkBox;
  }

  /**
   * Gets response message.
   *
   * @return the response message
   */
  public StringProperty getResponseMessage()
  {
    return responseMessage;
  }

  /**
   * Add current game.
   *
   * @throws RemoteException the remote exception
   * @throws SQLException    the sql exception
   */
  public void addCurrentGame() throws RemoteException, SQLException {
    model
        .validateGame(title.getValue(), type.getValue(), releaseYear.getValue(),
            availabilityPeriod.getValue(), checkBox.getValue());
  }

  /**
   * Reset.
   */
  public void reset()
  {
    title.setValue("");
    type.setValue("");
    releaseYear.setValue("");
    availabilityPeriod.setValue("");
    checkBox.setValue(false);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("validateGame")){
      responseMessage.setValue((String) evt.getNewValue());
    }
  }
}
