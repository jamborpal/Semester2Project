package model;

import java.io.Serializable;

/**
 * Class representing the information that is needed for a renting.
 *
 * @author Group 1
 */
public class Rental implements Serializable
{

  private User owner;
  private User requester;
  private Game game;
  private int id;

  /**
   * Four parameter constructor initialising the instance variables.
   *
   * @param owner The owner of the Game being that is being rented out.
   * @param requester The user who would like to rent the game out.
   * @param game The game that is up for rent.
   * @param Id The ID of the renting.
   */
  public Rental(User owner, User requester, Game game,int Id)
  {
    this.owner = owner;
    this.requester = requester;
    this.game = game;
    this.id = Id;
  }

  /**
   * Returning the owner of the Game.
   *
   * @return A User object representing the owner of the Game.
   */
  public User getOwner()
  {
    return owner;
  }

  /**
   * Returning the requester of the Game.
   *
   * @return A User object representing the requester of the Game.
   */
  public User getRequester()
  {
    return requester;
  }

  /**
   * Returning the Game.
   *
   * @return A Game object that is being rented out.
   */
  public Game getGame()
  {
    return game;
  }

  /**
   * Returning the ID corresponding to the renting process.
   *
   * @return An integer that is the ID of the renting.
   */
  public int getId()
  {
    return id;
  }

  /**
   * Returning the information of the Rental object as a String.
   *
   * @return A String with the information of the object.
   */
  public String toString(){
    return game + " ";
  }
}
