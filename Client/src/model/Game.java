package model;

import java.io.Serializable;

/**
 * A class representing a Game
 *
 * @author Group 1
 */
public class Game implements Serializable
{
  private String title;
  private String type;
  private int releaseYear;
  private boolean needsDeposit;
  private int availabilityPeriod;
  private int id;
  private int userId;
  private boolean available;

  /**
   * Six argument constructor. The instance variables are initialised.
   *
   * @param title              The title of the Game.
   * @param type               The type of the Game.
   * @param releaseYear        The year the Game was released.
   * @param needsDeposit       Whether the Game needs deposit.
   * @param availabilityPeriod For how long the Game can be rented.
   * @param userId             The user who owns the Game.
   */
  public Game(String title, String type, int releaseYear, boolean needsDeposit,
              int availabilityPeriod, int userId)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.availabilityPeriod = availabilityPeriod;
    this.id = (int) (Math.random() * 9999) + 1;
    this.userId = userId;
    this.available = true;
  }

  /**
   * Seven argument constructor. The instance variables are initialised.
   *
   * @param title              The title of the Game.
   * @param type               The type of the Game.
   * @param releaseYear        The year the Game was released.
   * @param needsDeposit       Whether the Game needs deposit.
   * @param availabilityPeriod For how long the Game can be rented.
   * @param userId             The user who owns the Game.
   * @param gameID             The ID of the Game.
   */
  public Game(String title, String type, int releaseYear, boolean needsDeposit,
              int availabilityPeriod, int userId, int gameID)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.availabilityPeriod = availabilityPeriod;
    this.id = gameID;
    this.userId = userId;
    this.available = true;
  }

  /**
   * Randomizing an ID for the Game. (ID is now from the Database).
   */
  public void reRollID()
  {
    this.id = (int) (Math.random() * 9999) + 1;
  }

  /**
   * Returning the ID of the Game.
   *
   * @return The ID of the Game as an Integer.
   */
  public int getId()
  {
    return id;
  }

  /**
   * Returning the owner of the Game.
   *
   * @return The ID of the user who owns the game as an Integer.
   */
  public int getUserId()
  {
    return userId;
  }

  /**
   * Returning the title of the Game.
   *
   * @return The title of the Game as a String.
   */
  public String getTitle()
  {
    return title;
  }

  /**
   *Returning the type of the Game.
   *
   * @return The type of the Game as a String.
   */
  public String getType()
  {
    return type;
  }

  /**
   * Returning the year the Game was released.
   *
   * @return The year the Game was released as an Integer.
   */
  public int getReleaseYear()
  {
    return releaseYear;
  }

  /**
   * Returning whether the renting of the Game requires a deposit.
   *
   * @return A Boolean, if the game requires a deposit.
   */
  public boolean deposit()
  {
    return needsDeposit;
  }

  /**
   * Returning how long the Game can be rented for.
   *
   * @return The length of rentability as an Integer.
   */
  public int getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  /**Returning all the information about the Game.
   *
   * @return The information about the Game as a String.
   */
  public String toString()
  {
    if (deposit())
    {
      if (available)
      {
        return title + ", " + type + ", " + releaseYear + ", Deposit required"
                + ", Game Available for " + availabilityPeriod + " days, Available, " + "Owner Id: " + userId;
      }
      else
      {
        return title + ", " + type + ", " + releaseYear + ", Deposit required"
                + ", Game Available for " + availabilityPeriod
                + " days, Unavailable, " + "Owner Id: " + userId;
      }
    }
    else
    {
      if (available)
      {
        return title + ", " + type + ", " + releaseYear
                + ", Game Available for " + availabilityPeriod + " days, Available, " + "Owner Id: " + userId;
      }
      else
      {
        return title + ", " + type + ", " + releaseYear
                + ", Game Available for " + availabilityPeriod
                + " days, Unavailable, " + "Owner Id: " + userId;
      }
    }

  }

  /**
   * Returning whether the Game object is the same as the one compared to.
   *
   * @param obj A Game object that should be compared to.
   *
   * @return A boolean, whether the Game is the same as the one compared to.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Game))
      return false;
    Game other = (Game) obj;
    return title.equals(other.title) && type.equals(other.type)
            && id == other.id && releaseYear == other.releaseYear
            && needsDeposit == other.needsDeposit
            && availabilityPeriod == other.availabilityPeriod;
  }

  /**
   * Setting the Game's status.
   *
   * @param available A boolean representing the status we want the Game to set.
   */
  public void setAvailable(boolean available)
  {
    this.available = available;
  }

  /**
   * Returning whether the Game is available.
   *
   * @return A boolean whether the Game is available.
   */
  public boolean getAvailable()
  {
    return available;
  }
}
