/*******************
 * CLASS:   Card
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMARKS: This class is used to create a card used in a poker game.
 ******************/

public class Card implements Cardable
{
    
    //Instnace Variables
    private int rank;//The rank of the card as an integer from 2-14(Ace)
    private Suit cardSuit;//The suit of the card.
    private boolean selected;//Keeps track if the card is selected or not.
    private boolean faceUp;//Keeps track if the card is suppoed to be face up or down.

    //Class constants
    public static final int LOWEST_RANK = 2;//The rank of the lowest card. 

    private static final char HEART_SYMBOL = '\u2665';//Unicode cahracter for heart symbol.
    private static final char DIAMOND_SYMBOL = '\u2666';//Unicode character for diamond symbol.
    private static final char SPADE_SYMBOL = '\u2660';//Unicode character for spade symbol.
    private static final char CLUB_SYMBOL = '\u2663';//Unicode character for club symbol.

    public static final int JACK = 11;//Numerical value of the Jack.
    public static final int QUEEN = 12;//Numerical value of the Queen.
    public static final int KING = 13;//Numerical value of the King.
    public static final int ACE = 14;//Numerical value of the Ace. (Most of the time)

    //Constructor

    /*****************
     * constructor
     * 
     * PURPOSE: To create an instnace of the card class.
     * PARAMETERS:
     *      This constructor accepts two parameters, and integer with the rank
     *      of the card as a value from 2-14 and a Suit enum type with the suit of the card.
     *      If the rank is an integer from outside of 2-14 then we will just make the card
     *      have a rank of 2.
     * RETURNS: Implicitly returns a pointer to the new Card instance.
     ****************/
    public Card(int rank, Suit suit)
    {
        //Make sure the card rank is between 2(deuce) and 14(Ace)
        if(LOWEST_RANK <= rank && ACE >= rank)
        {
            this.rank = rank;
        }//end if
        else
        {
            this.rank = LOWEST_RANK;//If the rank is not valid we will just make the card 2 so that the card remains valid.
        }//end else

        //Set the suit of the card.
        cardSuit = suit;

        //Now we will just set the selected and faceUp fields to false by default.
        selected = false;
        faceUp = false;
    }//end constructor

    //Instance Methods

    //Cardable interface methods.

    //This method is a getter method that just returns the value of selected to determine if the card is selected or not.
    public boolean getSelected()
    {
        return selected;
    }//end getSelected

    //This method is a getter method that returns whether or not the card should be shown face up or down.
    public boolean getFaceUp()
    {
        return faceUp;
    }//end getFaceUp

    //This method just returns the suit of the card.
    public Suit getSuit()
    {
        return cardSuit;
    }//end getSuit

    //This method just switched the selected field from true to false or vice versa.
    public void switchSelectedState()
    {
        selected = !selected;
    }//end switchSelectedState

    //This method sets selected back to false which is the default value.
    public void resetSelected()
    {
        selected = false;
    }//end resetSelected

    //This method is a setter method for the faceUp field it sets faceUp to whatever is given as the parameter.
    public void setFaceUp(boolean faceUp)
    {
        this.faceUp = faceUp;
    }//end setFaceUp

    //End of Cardable interface methods

    //This is a getter method that gets and returns the rank of the card.
    public int getRank()
    {
        return rank;
    }//end getRank

    /********************
     * toString
     * 
     * PURPOSE: This method is used to return a string representation of a card.
     * PARAMETERS:
     *      This method does accept any parameters, it just creates and returns a String
     *      representation of a card.
     * RETURNS: A string representation of the card.
     ******************/
    public String toString()
    {
        String theCard = "";//Points to the String representation of the card.
        
        //We first add the rank to the string. Note that if the rank is less than JACK we just add the number,
        //Otherwise we need to add the character representing the card.
        if(JACK > rank)
        {
            theCard += rank;
        }//end if
        else if(JACK == rank)
        {
            theCard += "J";
        }//end else if
        else if(QUEEN == rank)
        {
            theCard += "Q";
        }//end else if
        else if(KING == rank)
        {
            theCard += "K";
        }//end else if
        else
        {
            theCard += "A";
        }//end else

        //Now we need to add the suit to the card.
        theCard += " ";

        //Check the suit of the card.
        if(Suit.HEART == cardSuit)
        {
            theCard += HEART_SYMBOL;
        }//end if
        else if(Suit.DIAMOND == cardSuit)
        {
            theCard += DIAMOND_SYMBOL;
        }//end else if
        else if(Suit.SPADE == cardSuit)
        {
            theCard += SPADE_SYMBOL;
        }//end else if
        else
        {
            theCard += CLUB_SYMBOL;
        }//end else

        return theCard;
    }//end toStirng

    /*****************
     * compareTo
     * 
     * PURPOSE: To compare the ranks of two cards.
     * PARAMETERS:
     *      This method accepts one parameter which is a Card. It then compares the
     *      rank of the card and returns a negative integer if this card is less than
     *      the card passed as a parameter, zero if both equal and positive otherwise.
     * RETURNS: An integer depending on which card has the higher value.
     ****************/
    public int compareTo(Card theCard)
    {
        return rank - theCard.rank;
    }//end compareTo
}//end Card class
