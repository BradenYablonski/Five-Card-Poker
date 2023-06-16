/********************
 * CLASS:   Deck
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMARKS: This class is used to create a deck of cards for a poker game.
 ********************/

import java.util.LinkedList; 

public class Deck implements Deckable
{
    //Instance Variables
    private LinkedList<Cardable> cardDeck;//The deck of cards as a LinkedList.

    //Class Constants
    private static final int NUM_SUITS = 4;//The number of suits in the card deck.

    //Constructor

    /**************
     * constructor
     * 
     * PURPOSE: This constructor creates a LinkedList of NUM_CARDS where we have exactly one of each card.
     * PARAMETERS:
     *      This constructor does not accept any parameters it just goes through a loop to create cards
     *      of the ranks 2-14 where we have one card of every suit with every rank.
     * RETURNS: Implicitly returns a pointer to the new deck of cards.
     **************/
    public Deck()
    {
        cardDeck = new LinkedList<Cardable>();//Create the new deck of cards.

        //Now create the deck of cards note that the ranks start at 2 and not 1 so we need to add 1 to NUM_CARDS/NUM_SUITS
        for(int i = Card.LOWEST_RANK; i <= NUM_CARDS/NUM_SUITS + 1; i++)
        {
            cardDeck.add(new Card(i, Cardable.Suit.HEART));
            cardDeck.add(new Card(i, Cardable.Suit.DIAMOND));
            cardDeck.add(new Card(i, Cardable.Suit.SPADE));
            cardDeck.add(new Card(i, Cardable.Suit.CLUB));
        }//end for
    }//end constructor

    //Instance Methods

    //Deckable interface methods

    /*****************
     * shuffle
     * 
     * PURPOSE: To shuffle the cards in the deck.
     * PARAMETERS:
     *      This method does not accepts any parameters. It just shuffles the 
     *      cards in the deck using the Math.random() to randomly choose index positions to swap.
     * RETURNS: This method does not return anything.
     ****************/
    public void shuffle()
    {
        Cardable temp;//Temporarily holds onto the card while being swaped.

        //Shuffling the cards.
        for(int i = 0; i < cardDeck.size(); i++)
        {
            int swapIndex = ((int) (Math.random()*(cardDeck.size() - i))) + i;//Choose index from i up to but not including NUM_CARDS.

            //Now perform the swap
            temp = cardDeck.get(swapIndex);
            cardDeck.set(swapIndex, cardDeck.get(i));
            cardDeck.set(i, temp);
        }//end for
    }//end shuffle

    /****************
     * returnToDeck
     * 
     * PURPOSE: This method is used to return a set of cards to the back of the deck.
     * PARAMETERS:
     *      This method accepts one parameter which is a LinkedList of Cardable containing cards
     *      to add to the back of the deck.
     * RETURNS: This method does not return anything.
     ***************/
    public void returnToDeck(LinkedList<Cardable> discarded)
    {
        Cardable theCard;//Points to the current cards being returned to the deck

        //Note that before returning the cards back to the deck we want to reset the selected state for the card.
        if(null != discarded)
        {
            while(!discarded.isEmpty())
            {
                theCard = discarded.remove();

                //Reset the selected state of the card after before adding it back to the deck.
                if(null != theCard)
                {
                    theCard.resetSelected();
                    cardDeck.add(theCard);
                }//end if
            }//end while
        }//end if
    }//end returnToDeck

    /***************
     * drawACard
     * 
     * PURPOSE: This method is used to draw a card from the top of the deck.
     * PARAMETERS:
     *      This method accpets one parameter which is a boolean on whether the card is 
     *      to be drawn face up or face down.
     * RETURNS: A Cardable from the top of the deck.
     **************/
    public Cardable drawACard(boolean faceUp)
    {
        Cardable drawnCard = cardDeck.removeFirst();

        //Set the face up or down.
        drawnCard.setFaceUp(faceUp);

        return drawnCard;
    }//end drawACard

    //End of Deckable Methods

    //This method is a toString methods just used for testing purposes so that we can see what is in the deck.
    //REMOVE THIS METHOD BEFORE HANDING IN!!!!!!!!!!!!!!!!!!!!!
    public String toString()
    {
        return cardDeck.toString();
    }
}//end Deck class
