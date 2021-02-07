package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RentalListTest
{
  private RentalList rentalList;
  private ArrayList<Rental> rentals;
  private Rental rental1;
  private Rental rental2;
  private User owner1;
  private User owner2;
  private User requester1;
  private User requester2;
  private Game game1;
  private Game game2;

  @BeforeEach void setUp()
  {
    owner1 = new User("bob", "1234", 12345);
    requester1 = new User("john", "4321", 54321);
    game1 = new Game("Doom", "PC", 2016, false, 10, 123456);

    owner2 = new User("bob1", "1234", 12345);
    requester2 = new User("john1", "4321", 54321);
    game2 = new Game("Doom", "PC", 2016, false, 10, 123456);

    rental1 = new Rental(owner1, requester1, game1, 12);
    rental2 = new Rental(owner2, requester2, game2, 16);
    rentals = new ArrayList<>();
    rentalList = new RentalList();
  }

  @Test void RentalList()
  {
    assertNotNull(owner1);
    assertNotNull(owner2);
    assertNotNull(requester1);
    assertNotNull(requester2);
    assertNotNull(game1);
    assertNotNull(game2);
    assertNotNull(rental1);
    assertNotNull(rental2);
    assertNotNull(rentals);
    assertNotNull(rentalList);
  }

  @Test void getRentals()
  {
    assertEquals(rentals, rentalList.getRentals());
  }

  @Test void addRentalOne()
  {
    rentalList.addRental(rental1);
    assertEquals(1, rentalList.size());
  }

  @Test void addRentalMany()
  {
    rentalList.addRental(rental1);
    rentalList.addRental(rental2);
    assertEquals(2, rentalList.size());
  }

  @Test void addRentalNull()
  {
    rentalList.addRental(null);
    assertEquals(0, rentalList.size());
  }


  @Test void removeRentalOne()
  {
    rentalList.addRental(rental1);
    assertEquals(1, rentalList.size());
    rentalList.removeRental(rental1);
    assertEquals(0, rentalList.size());
  }

  @Test void removeRentalMany()
  {
    rentalList.addRental(rental1);
    rentalList.addRental(rental2);
    assertEquals(2, rentalList.size());
    rentalList.removeRental(rental1);
    rentalList.removeRental(rental2);
    assertEquals(0, rentalList.size());
  }

  @Test void removeRentalNull()
  {
    rentalList.removeRental(null);
    assertEquals(0, rentalList.size());
  }

  @Test void size()
  {
    assertEquals(0, rentalList.size());
    rentalList.addRental(rental1);
    assertEquals(1, rentalList.size());
  }
}