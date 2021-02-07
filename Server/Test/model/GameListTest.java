package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest
{
  private GameList games;
  private Game game1;
  private Game game2;
  private Game game3;
  private ArrayList<Game> games1;

  @BeforeEach void setUp()
  {
    games = new GameList();
    games1 = new ArrayList<>();
    game1 = new Game("Doom", "PC", 2016, false, 10, 123456);
    game2 = new Game("Warcraft 3", "PC", 2004, true, 30, 987654);
    game3 = new Game("Dark Souls", "PS3", 2009, false, 15, 555555);
  }

  @Test void GameList()
  {
    assertNotNull(games);
    assertNotNull(games1);
    assertNotNull(game1);
    assertNotNull(game2);
    assertNotNull(game3);
  }

  @Test void addGameNull()
  {
    games.addGame(null);
    assertEquals(0, games.size());
  }

  @Test void addGameOne()
  {
    games.addGame(game1);
    assertEquals(1, games.size());
  }

  @Test void addGameTwice()
  {
    games.addGame(game1);
    games.addGame(game1);
    assertEquals(1, games.size());
  }

  @Test void addGameMany()
  {
    games.addGame(game1);
    games.addGame(game2);
    games.addGame(game3);
    assertEquals(3, games.size());
  }

  @Test void removeGameEmptyList()
  {
    games.removeGame(game1);
    assertEquals(0, games.size());
  }

  @Test void removeGameOne()
  {

    games.addGame(game1);
    games.removeGame(game1);
    assertEquals(0, games.size());
  }

  @Test void removeGameMany()
  {
    games.addGame(game1);
    games.addGame(game2);
    games.removeGame(game1);
    games.removeGame(game2);
    assertEquals(0, games.size());
  }

  @Test void removeGameTwice()
  {
    games.addGame(game1);
    games.removeGame(game1);
    games.removeGame(game1);
    assertEquals(0, games.size());
  }

  @Test void removeGameFromMultipleOnes()
  {
    games.addGame(game1);
    games.addGame(game2);
    games.removeGame(game1);
    assertEquals(1, games.size());
  }

  @Test void removeGameEmptyListID()
  {
    int value = game1.getId();
    games.removeGame(value);
    assertEquals(0, games.size());
  }

  @Test void removeGameOneID()
  {
    int value = game1.getId();
    games.addGame(game1);
    games.removeGame(value);
    assertEquals(0, games.size());
  }

  @Test void removeGameManyID()
  {
    int value = game1.getId();
    int value1 = game2.getId();
    games.addGame(game1);
    games.addGame(game2);
    games.removeGame(value);
    games.removeGame(value1);
    assertEquals(0, games.size());
  }

  @Test void removeGameTwiceID()
  {
    int value = game1.getId();
    games.addGame(game1);
    games.removeGame(value);
    games.removeGame(value);
    assertEquals(0, games.size());
  }

  @Test void removeGameFromMultipleOnesID()
  {
    int value = game1.getId();
    games.addGame(game1);
    games.addGame(game2);
    games.removeGame(value);
    assertEquals(1, games.size());
  }

  @Test void getGameNegativeIndex()
  {
    games.addGame(game1);
    assertNull(games.getGame(-5));
  }

  @Test void getGameNull()
  {
    games.addGame(game1);
    assertNull(games.getGame(4));
  }

  @Test void getGameIndex()
  {
    games.addGame(game1);
    assertNotNull(games.getGame(0));
  }

  @Test void getGameID()
  {
    int value = game1.getId();
    games.addGame(game1);
    assertNotNull(games.getGameById(value));
  }

  @Test void sizeEmpty()
  {
    assertEquals(0, games.size());
  }

  @Test void sizeNotEmpty()
  {
    games.addGame(game1);
    assertEquals(1, games.size());
  }

  @Test void sizeMinus()
  {
    assertNotEquals(-4, games.size());
  }

  @Test void testEquals()
  {
    ArrayList<Game> games2 = new ArrayList<>();
    assertEquals(games1, games2);
  }

  @Test void testGetAvailableGames()
  {
    game1.setAvailable(false);
    game2.setAvailable(false);
    games.addGame(game1);
    games.addGame(game2);
    games.addGame(game3);
    assertEquals(1, games.getAvailableGames().size());
  }

  @Test void testGetUnavailableGames()
  {
    games.addGame(game1);
    games.addGame(game2);
    games.addGame(game3);
    assertEquals(0, games.getUnavailableGames().size());
  }

}