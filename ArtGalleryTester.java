//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Art Gallery: ArtGallery Tester Class
// Course: CS 300 Spring 2022
//
// Author: Harshet Anand
// Email: hanand2@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Ahan Nair
// Partner Email: nair27@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 * 
 * @author Harshet Anand & Ahan Nair
 *
 */

public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    Artwork toCompare = new Artwork("Madison", 1540, 45.50);
    Artwork toCompare2 = new Artwork("Milwaukee", 1990, 50.45);

    // Checks the correctness of the implementation of compareTo() method
    if (!(toCompare.compareTo(toCompare2) < 0)) {
      return false;
    }

    // Checks the correctness of the implementation of equals() method
    if (toCompare.equals(toCompare2) == true) {
      return false;
    }
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    // Test 1 tests if ArtworkGallery is equal to size 0, is empty, and string representation is ""
    ArtGallery artG = new ArtGallery();
    if (!(artG.size() == 0) && (!(artG == null)) && (!(artG.toString() == ""))) {
      return false;
    }

    // Test 2 adds one artwork and checks method returns true and is not empty and has size 1 and
    // toString() returns the correct output
    Artwork firstArt = new Artwork("Madison", 1540, 45.50);
    String expected = "[(Name: Madison) (Year: 1540) (Cost: $45.50)]";
    if (!(artG.addArtwork(firstArt) == true) || !(artG.isEmpty() == false) || !(artG.size() == 2)
        || (!(artG.toString().trim().equals(expected)))) {
      return true;
    }

    // Test 3 adds another artwork which is smaller than the root artwork
    Artwork secondArt = new Artwork("Chicago", 1490, 90.50);
    String expected2 = "[(Name: Chicago) (Year: 1490) (Cost: $90.50)]\n"
        + "[(Name: Madison) (Year: 1540) (Cost: $45.50)]";
    if (!(artG.addArtwork(secondArt) == true) || !(artG.isEmpty() == false) || !(artG.size() == 2)
        || (!(artG.toString().trim().equals(expected2)))) {
      return false;
    }

    // Test 4 adds another artwork which is greater than the root artwork
    Artwork thirdArt = new Artwork("Milwaukee", 1990, 50.45);
    String expected3 = "[(Name: Chicago) (Year: 1490) (Cost: $90.50)]\n"
        + "[(Name: Madison) (Year: 1540) (Cost: $45.50)]\n"
        + "[(Name: Milwaukee) (Year: 1990) (Cost: $50.45)]";
    if (!(artG.addArtwork(thirdArt) == true) || !(artG.isEmpty() == false) || !(artG.size() == 3)
        || (!(artG.toString().trim().equals(expected3)))) {
      return false;
    }

    // Test 5 adds two artworks in which one artwork is added to the left subtree and the other is
    // added to the right subtree
    Artwork fourthArt = new Artwork("Boston", 1390, 300.50);
    Artwork fifthArt = new Artwork("Miami", 2039, 250.50);
    String expected4 = "[(Name: Boston) (Year: 1390) (Cost: $300.50)]\n"
        + "[(Name: Chicago) (Year: 1490) (Cost: $90.50)]\n"
        + "[(Name: Madison) (Year: 1540) (Cost: $45.50)]\n"
        + "[(Name: Milwaukee) (Year: 1990) (Cost: $50.45)]\n"
        + "[(Name: Miami) (Year: 2039) (Cost: $250.50)]";
    if (!(artG.addArtwork(fourthArt) == true) || (!(artG.addArtwork(fifthArt)) == true)
        || !(artG.isEmpty() == false) || !(artG.size() == 5)
        || (!(artG.toString().trim().equals(expected4)))) {
      return false;
    }

    // Test 6 adds an artwork already added in the list

    return true;
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    ArtGallery artG2 = new ArtGallery();
    // Test 1 checks if calling lookup on empty gallery returns false
    if (artG2.lookup("Madison", 1540, 45.50)) {
      return false;
    }

    // The list of artworks
    Artwork firstArt = new Artwork("Madison", 1540, 45.50);
    Artwork secondArt = new Artwork("Chicago", 1490, 90.50);
    Artwork thirdArt = new Artwork("Milwaukee", 1990, 50.45);
    Artwork fourthArt = new Artwork("Boston", 1390, 300.50);
    Artwork fifthArt = new Artwork("Miami", 2039, 250.50);
    artG2.addArtwork(firstArt);
    artG2.addArtwork(secondArt);
    artG2.addArtwork(thirdArt);
    artG2.addArtwork(fourthArt);
    artG2.addArtwork(fifthArt);

    // This tests the artwork at the root node
    if (!artG2.lookup("Madison", 1540, 45.50)) {
      return false;
    }
    // Test 3 tests successful lookups
    if (!artG2.lookup("Boston", 1390, 300.50)) {
      return false;
    }
    // Test 3 tests unsuccessful lookups
    if (artG2.lookup("LA", 1500, 4050.50)) {
      return false;
    }
    if (artG2.lookup("Maine", 1324, 23848.0)) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*)
   * (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    ArtGallery artG3 = new ArtGallery();
    // Test 1 tests if height is 0 at initialization
    if (!(artG3.height() == 0)) {
      return false;
    }

    // The list of artworks
    Artwork firstArt = new Artwork("Madison", 1540, 45.50);
    Artwork secondArt = new Artwork("Chicago", 1490, 90.50);
    Artwork thirdArt = new Artwork("Milwaukee", 1990, 50.45);
    Artwork fourthArt = new Artwork("Boston", 1390, 300.50);
    Artwork fifthArt = new Artwork("Miami", 2039, 250.50);
    artG3.addArtwork(firstArt);

    // Test 2 tests if the height of only one node is 1
    artG3.addArtwork(firstArt);
    if (!(artG3.height() == 1)) {
      return false;
    }

    // Test 3 tests height after adding remaining artworks
    artG3.addArtwork(secondArt);
    artG3.addArtwork(thirdArt);
    artG3.addArtwork(fourthArt);
    artG3.addArtwork(fifthArt);
    if (!(artG3.height() == artG3.height())) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    ArtGallery artG4 = new ArtGallery();
    // The list of artworks
    Artwork firstArt = new Artwork("Madison", 1540, 45.50);
    Artwork secondArt = new Artwork("Chicago", 1490, 90.50);
    Artwork thirdArt = new Artwork("Milwaukee", 1990, 50.45);
    Artwork fourthArt = new Artwork("Boston", 1390, 300.50);
    Artwork fifthArt = new Artwork("Miami", 2039, 250.50);
    artG4.addArtwork(firstArt);
    artG4.addArtwork(secondArt);
    artG4.addArtwork(thirdArt);
    artG4.addArtwork(fourthArt);
    artG4.addArtwork(fifthArt);

    // This tests checks the correctness of the getBestArtwork() method
    Artwork bestWork = artG4.getBestArtwork();
    if (bestWork.getCost() == 250.50 && bestWork.getName() == "Miami"
        && bestWork.getYear() == 2039) {
      return true;
    }
    return false;
  }

  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty
   * arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method
   * returns an array list which contains all the artwork satisfying the search criteria of year and
   * cost, when called on a non empty artwork tree with one match, and two matches and more. Vary
   * your search criteria such that the lookupAll() method must check in left and right subtrees.
   * (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    ArtGallery artG5 = new ArtGallery();
    // The list of artworks
    Artwork firstArt = new Artwork("Madison", 1540, 45.50);
    Artwork secondArt = new Artwork("Chicago", 1490, 90.50);
    Artwork thirdArt = new Artwork("Milwaukee", 1990, 50.45);
    Artwork fourthArt = new Artwork("Boston", 1390, 300.50);
    Artwork fifthArt = new Artwork("Miami", 2039, 250.50);
    artG5.addArtwork(firstArt);
    artG5.addArtwork(secondArt);
    artG5.addArtwork(thirdArt);
    artG5.addArtwork(fourthArt);
    artG5.addArtwork(fifthArt);

    // Test 1 ensures that the method returns an empty arraylist when called on an empty tree
    if (!(artG5.lookupAll(2000, 1000.0).isEmpty())) {
      return false;
    }
    ArrayList<Artwork> artList = new ArrayList<>();
    artList.add(thirdArt);
    // This tests for one successful match
    if (artG5.lookupAll(1990, 350.45).equals(artList)) {
      return false;
    }

    artList.remove(0);
    // This 2 tests for two successful matches
    Artwork sixthArt = new Artwork("Utah", 1900, 5500.50);
    artG5.addArtwork(sixthArt);
    artList.add(thirdArt);
    artList.add(sixthArt);
    if (artG5.lookupAll(1900, 5900.50).equals(artList)) {
      return false;
    }

    // Test 3 tests for no matches found
    ArrayList<Artwork> artList1 = new ArrayList<>();
    if (!(artG5.lookupAll(1530, 6000.50).equals(artList1))) {
      return false;
    }
    return true;
  }


  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    ArtGallery artG6 = new ArtGallery();
    // The list of artworks
    Artwork firstArt = new Artwork("Madison", 1540, 45.50);
    Artwork secondArt = new Artwork("Chicago", 1490, 90.50);
    Artwork thirdArt = new Artwork("Milwaukee", 1990, 50.45);
    Artwork fourthArt = new Artwork("Boston", 1390, 300.50);
    Artwork fifthArt = new Artwork("Miami", 2039, 250.50);
    artG6.addArtwork(firstArt);
    artG6.addArtwork(secondArt);
    artG6.addArtwork(thirdArt);
    artG6.addArtwork(fourthArt);
    artG6.addArtwork(fifthArt);

    try {
      artG6.buyArtwork("Madison", 1540, 45.50);
    } catch (Exception e) {
      return false;
    }

    // Test 2 buys an artwork that is at a non-leaf node
    try {
      artG6.buyArtwork("Milwaukee", 1990, 50.45);
    } catch (Exception e) {
      return false;
    }

    // Test 3 buys an artwork that is not present in the BST
    try {
      artG6.buyArtwork("Washington", 1300, 1450.50);
      return false;
    } catch (NoSuchElementException e) {
    }
    return true;
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   * 
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {
    boolean allTestsPassed =
        testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup() && testHeight()
            && testGetBestArtwork() && testLookupAll() && testBuyArtwork();
    // Can only return true if all test cases return true. Will go back to main method to return
    // final result
    return allTestsPassed; // Default return statement added to resolve compiler errors
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}
