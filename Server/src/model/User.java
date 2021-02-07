package model;

import java.io.Serializable;

/**
 * Class representing the User of the system.
 *
 * @author Group 1
 */
public class User implements Serializable
{
  private String username;
  private String password;
  private int userID;
  private String bio;

  /**
   * Three argument constructor initialising the variables.
   *
   * @param username The username of the user.
   * @param password The password for the profile.
   * @param userID   The ID of the user to be identified by.
   */
  public User(String username, String password, int userID)
  {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.bio = "";
  }

  /**
   * Getting the ID of the user.
   *
   * @return Integer representing the ID of the User.
   */
  public int getUserID()
  {
    return userID;
  }

  /**
   * Getting the username of the User.
   *
   * @return A String representing the username of the User.
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Getting the password of the User profile.
   *
   * @return A String representing the password of the User.
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Getting the bio of the User.
   *
   * @return A String representing the bio of the User.
   */
  public String getBio()
  {
    return bio;
  }

  /**
   * Setting the bio of the User.
   *
   * @param bio A string representing the bio of the User.
   */
  public void setBio(String bio)
  {
    if (bio != null)
      this.bio = bio;
  }
}
