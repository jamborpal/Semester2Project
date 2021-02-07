package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest
{
  private User owner;
  private User requester;
  private Game game;
  private int id;
  private Rental rental;

  @BeforeEach void setUp()
  {
    owner = new User("bob", "1234", 12345);
    requester = new User("john", "4321", 54321);
    game = new Game("Doom", "PC", 2016, false, 10, 123456);
    id = 1010;
    rental = new Rental(owner, requester, game, id);
  }

  @Test void Rental()
  {
    assertNotNull(owner);
    assertNotNull(requester);
    assertNotNull(game);
    assertNotNull(rental);
    assertNotNull(id);
  }

  @Test void testGetOwner()
  {
    assertEquals(owner, rental.getOwner());
  }

  @Test void testGetRequester()
  {
    assertEquals(requester, rental.getRequester());
  }

  @Test void testGetGame()
  {
    assertEquals(game, rental.getGame());
  }


  @Test void testToString()
  {
    assertEquals(game.toString() + " ", rental.toString());   // the space is from the toString to be read as a String
  }

  @Test void testGetId()
  {
    assertEquals(1010, rental.getId());
  }
}