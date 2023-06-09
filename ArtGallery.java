//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Art Gallery: ArtGallery Class
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 * 
 * @author TODO Harshet Anand & Ahan Nair
 *
 */
public class ArtGallery {
  // Complete the TODO tags in this source file

  private BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    }
    return false; // Default return statement added to resolve compiler errors
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   * 
   * @return the size of this ArtworkGallery
   */
  public int size() {
    return this.size; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks whether this ArtworkGallery contains a Artwork given its name, year, and cost.
   * 
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {
    Artwork current = new Artwork(name, year, cost);
    if (isEmpty()) {
      return false; // Returns false if there is no match with this artwork
    }
    return lookupHelper(current, this.root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current
   * 
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {
    if (current.getData().equals(target)) {
      return true;
    }
    if (current.getData().compareTo(target) < 0) {
      if (current.getRight() == null) {
        return false; // Returns false is getRight is null
      }
      return lookupHelper(target, current.getRight());
    } else if (current.getData().compareTo(target) > 0) {
      if (current.getLeft() == null) {
        return false; // Returns false is getLeft is null
      }
      return lookupHelper(target, current.getLeft());
    }
    return false;
  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   * 
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   *         there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) throws NullPointerException {
    if (newArtwork == null) {
      throw new NullPointerException();
    }
    if (isEmpty()) {
      root = new BSTNode<Artwork>(newArtwork);
      size++;
      return true;
    }
    if (addArtworkHelper(newArtwork, this.root) == true) {
      size++;
      return true;
    }
    return false; // Returns false if none of the statements are true
  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   *         with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {
    if (current == null) {
      current = new BSTNode<Artwork>(newArtwork);
      return true;
    }
    if (newArtwork.compareTo(current.getData()) > 0) {
      if (current.getRight() == null) {
        current.setRight(new BSTNode<Artwork>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getRight());
      }
    } else if (newArtwork.compareTo(current.getData()) < 0) {
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<Artwork>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getLeft());
      }
    } else {
      return false; // Returns false is none of statements are true
    }
  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery)
   * 
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   *         ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {
    if (this.isEmpty()) {
      return null;
    }
    BSTNode<Artwork> getRight = this.root;
    while (!(getRight.getRight() == null)) {
      BSTNode<Artwork> getNew = getRight.getRight();
      getRight = getNew;
    }
    return getRight.getData(); // returns the right side as that is the best artwork
  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   * 
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: $300.0)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: $550.0)]" + "\n"
   * 
   * @return a String representation of all the artwork stored within this BST sorted in an
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {
    String string = "";
    if (current == null) {
      return string; // Returns string of null artworks
    } else {
      string = string + toStringHelper(current.getLeft());
      string = string + (current.getData() + "\n");
      string = string + toStringHelper(current.getRight());
    }
    return string; // Returns string of artworks
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root); // Default return statement added to resolve compiler errors
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {
    if (current == null) {
      return 0;
    } else {
      int getRight = heightHelper(current.getRight());
      int getLeft = heightHelper(current.getLeft());
      if (getLeft < getRight) {
        return (getRight + 1); // Returns right side plus one
      } else {
        return (getLeft + 1); // Returns left side plus one
      }
    }
  }

  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.
   * 
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {
    return lookupAllHelper(year, cost, this.root); // Default return statement added to resolve
                                                   // compiler errors
  }

  /**
   * Recursive helper method to lookup the list of artworks given their year of creation and a
   * maximum value of cost
   * 
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   *         this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
      BSTNode<Artwork> current) {
    ArrayList<Artwork> list = new ArrayList<Artwork>();
    if (current == null) {
      return list;
    }
    if (current.getData().getCost() < cost && current.getData().getYear() == year) {
      list.add(current.getData());
    }
    lookupAllHelper(year, cost, current.getLeft());
    lookupAllHelper(year, cost, current.getLeft());
    // Returns the list of the artworks looked up
    return list;
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   * 
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria
   */

  public void buyArtwork(String name, int year, double cost) {
    Artwork artwork = new Artwork(name, year, cost);
    root = buyArtworkHelper(artwork, root);
    size--;
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree
   * 
   * @param target  a reference to a Artwork we are searching to remove in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current)
      throws NoSuchElementException {
    // if current == null (empty subtree rooted at current), no match found, throw an exception
    if (current == null) {
      throw new NoSuchElementException("Error! There was no match found!");
    }
    if (current.getData().compareTo(target) < 0) {
      current.setRight(buyArtworkHelper(target, current.getRight()));
    } else if (current.getData().compareTo(target) > 0) {
      current.setLeft(buyArtworkHelper(target, current.getLeft()));
    } else if (!(current.getRight() == null)) {
      return current.getRight();
    } else if (!(current.getLeft() == null)) {
      return current.getLeft();
    } else {
      current = new BSTNode<Artwork>(getSuccessor(current));
      current.setRight(buyArtworkHelper(current.getData(), current.getRight()));
    }
    return current; // Returns current list artwork bought
  }

  /**
   * Helper method to find the successor of a node in a BST (to be used by the delete operation).
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not null. If node does not have a right child, return node.getData().
   * 
   * @param node node whose successor is to be found in the tree
   * @return returns without removing the key of the successor node
   */

  protected static Artwork getSuccessor(BSTNode<Artwork> node) {
    BSTNode<Artwork> getNext = node.getRight();
    while (!(getNext.getLeft() == null)) {
      BSTNode<Artwork> behind = getNext.getLeft();
      getNext = behind;
    }
    return getNext.getData(); // Returns without removing the key of the successor node
  }
}
