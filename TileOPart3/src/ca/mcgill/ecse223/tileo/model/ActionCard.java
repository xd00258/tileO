/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 65 "../../../../../TileOPersistence.ump"
// line 64 "../../../../../TileO (updated Feb10).ump"
public abstract class ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionCard Attributes
  private String instructions;

  //ActionCard Associations
  private Deck deck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionCard(String aInstructions, Deck aDeck)
  {
    instructions = aInstructions;
    boolean didAddDeck = setDeck(aDeck);
    if (!didAddDeck)
    {
      throw new RuntimeException("Unable to create card due to deck");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getInstructions()
  {
    return instructions;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public boolean setDeck(Deck aDeck)
  {
    boolean wasSet = false;
    //Must provide deck to card
    if (aDeck == null)
    {
      return wasSet;
    }

    //deck already at maximum (32)
    if (aDeck.numberOfCards() >= Deck.maximumNumberOfCards())
    {
      return wasSet;
    }
    
    Deck existingDeck = deck;
    deck = aDeck;
    if (existingDeck != null && !existingDeck.equals(aDeck))
    {
      boolean didRemove = existingDeck.removeCard(this);
      if (!didRemove)
      {
        deck = existingDeck;
        return wasSet;
      }
    }
    deck.addCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Deck placeholderDeck = deck;
    this.deck = null;
    placeholderDeck.removeCard(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "instructions" + ":" + getInstructions()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 68 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 1010101010101010101L ;

  public abstract Game.Mode getActionCardGameMode();
 
  
}