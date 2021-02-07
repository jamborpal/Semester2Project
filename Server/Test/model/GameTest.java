package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
  private Game game;
  private boolean available;

  @BeforeEach void setUp()
  {
    available = true;
    game = new Game("Doom", "PC", 2016, false, 10, 123456);
  }

  @Test void Game()
  {
    assertNotNull(game);
    assertTrue(available);
  }

  @Test void reRollID()  //this was used in previous tests before DBS and is still used for testing purposes
  {
    int value = game.getId();
    game.reRollID();
    assertNotEquals(value, game.getId());
  }

  @Test void getId()
  {
    int value = game.getId();
    assertEquals(value, game.getId());
  }

  @Test void getUserID()
  {
    assertEquals(123456, game.getUserId());
  }

  @Test void getTitle()
  {
    assertEquals("Doom", game.getTitle());
  }

  @Test void getType()
  {
    assertEquals("PC", game.getType());
  }

  @Test void getReleaseYear()
  {
    assertEquals(2016, game.getReleaseYear());
  }

  @Test void deposit()
  {
    assertFalse(game.deposit());
  }

  @Test void getAvailabilityPeriod()
  {
    assertEquals(10, game.getAvailabilityPeriod());
  }

  @Test void testToString()
  {
    int value = game.getId();
    assertEquals(
        "Title: Doom, id: " + value + ", type: PC, release year: 2016, "
            + "deposit: false, availability period: 10, Available:true",
        game.toString());
  }

  @Test void testGetAvailable()
  {
    assertTrue(available);
  }

  @Test void testSetAvailable()
  {
    game.setAvailable(false);
    assertFalse(game.getAvailable());
  }

  @Test void testEquals()
  {
    int value = game.getId();
    assertEquals("Doom", game.getTitle());
    assertEquals("PC", game.getType());
    assertEquals(value, game.getId());
    assertEquals(2016, game.getReleaseYear());
    assertFalse(game.deposit());
    assertEquals(10, game.getAvailabilityPeriod());

  }

}