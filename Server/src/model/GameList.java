package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list containing Game objects.
 *
 * @author Group 1
 */
public class GameList implements Serializable
{
  private ArrayList<Game> games;

  /**
   * Constructor initialising the ArrayList of Games.
   */
  public GameList()
  {
    this.games = new ArrayList<>();
  }

  /**
   * Adding a Game to the list of games.
   *
   * @param game The game to be added.
   */
  public void addGame(Game game)
  {
    if (game == null)
    {
      return;
    }
    for (int i = 0; i < games.size(); i++)
    {
      if (games.get(i).equals(game))
      {
        return;
      }
    }
    games.add(game);
  }

  /**
   * Removing a Game by its ID from the list of games.
   *
   * @param id The ID of the Game to be removed.
   */
  public void removeGame(int id)
  {
    for (int i = 0; i < games.size(); i++)
    {
      if (games.get(i).getId() == id)
        games.remove(games.get(i));
    }
  }

  /**
   * Removing a Game from the list of games.
   *
   * @param game The game to be removed.
   */
  public void removeGame(Game game)
  {
    games.remove(game);
  }

  /**
   * Getting a Game object by its ID.
   *
   * @param id The ID of a Game.
   *
   * @return A Game object that has the corresponding ID.
   */
  public Game getGameById(int id)
  {
    return games.stream().filter(game -> id == game.getId()).findFirst().orElse(null);
  }

  /**
   * Returning a Game object that has a specific index in the ArrayList.
   *
   * @param index The index of an object in the list.
   *
   * @return A Game with the index in the list.
   */
  public Game getGame(int index)
  {
    if (index < 0 || index > games.size())
      return null;
    return games.get(index);
  }

  /**
   * Returning the size of the ArrayList.
   *
   * @return An integer representing the amount of objects present in the ArrayList.
   */
  public int size()
  {
    return games.size();
  }

  /**
   * Returning whether a GameList object is the same as the obj.
   *
   * @param obj An object to compare a GameLis object to.
   *
   * @return A boolean of whether the two objects are the same.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof GameList))
      return false;
    GameList other = (GameList) obj;
    return games.equals(other.games);
  }

  /**
   * Returning the games that are available for renting. In other words available equals true.
   *
   * @return An ArrayList with the Games that are available for renting.
   */
  public ArrayList<Game> getAvailableGames()
  {
    ArrayList<Game> dummy = new ArrayList<>();
    for(int i = 0;i<games.size();i++)
    {
      if(games.get(i).getAvailable())
        dummy.add(games.get(i));
    }
    return dummy;
  }

  /**
   * Returning the games that are not available for renting. In other words available equals false.
   *
   * @return An ArrayList with the Games that are not available for renting.
   */
  public ArrayList<Game> getUnavailableGames()
  {
    ArrayList<Game> dummy = new ArrayList<>();
    for(int i = 0;i<games.size();i++)
    {
      if(!(games.get(i).getAvailable()))
        dummy.add(games.get(i));
    }
    return dummy;
  }

}
