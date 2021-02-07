package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  private String username;
  private String password;
  private String bio;
  private int userID;
  private GameList gameList;
  private GameList rentedList;
  private User user;

  @BeforeEach void setUp()
  {
    userID = 123456;
    username = "Bob";
    password = "1111";
    bio = "zzzz";
    gameList = new GameList();
    rentedList = new GameList();
    user = new User(username, password, userID);
  }

  @Test void User()
  {
    assertNotNull(gameList);
    assertNotNull(rentedList);
    assertNotNull(user);
    assertNotNull(userID);
    assertNotNull(username);
    assertNotNull(password);
    assertNotNull(bio);
  }

  @Test void testUserID()
  {
    assertEquals(123456, userID);
  }

  @Test void testUsername()
  {
    assertEquals("Bob", username);
  }

  @Test void testPassword()
  {
    assertEquals("1111", password);
  }

  @Test void testBio()
  {
    assertEquals("zzzz", bio);
  }

  @Test void testSetBio()
  {
    user.setBio("aaaa");
    assertEquals("aaaa", user.getBio());
  }

  @Test void testSetBioNull()
  {
    user.setBio(null);
    assertNotNull(user.getBio());
  }
}