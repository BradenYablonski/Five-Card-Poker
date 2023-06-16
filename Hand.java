/**********************
 * CLASS:   Hand
 * 
 * AUTHOR:  Braden Yablonski
 * 
 * REMAKRS: This class is used to create a hand of cards in a poker game.
 *********************/
import java.util.LinkedList;

public class Hand implements TestableHand
{
    //Instance Variables
    private Cardable[] hand;//Stores each card that is in the hand.

    //Class Constants

    //These constants are used to determine what kind of hand we have.
    private static final int STRAIGHT_FLUSH = 1;//Represents a straight flush in a five-card poker hand.
    private static final int FOUR_OF_KIND = 2;//Represents a four of a kind in a five-card poker hand.
    private static final int FULL_HOUSE = 3;//Represents a full house in a five-card poker hand.
    private static final int FLUSH = 4;//Represents a flush in a five-card poker hand.
    private static final int STRAIGHT = 5;//Represents a straight in a five-card poker hand.
    private static final int THREE_OF_KIND = 6;//Represents a three of a kind in a five-card poker hand.
    private static final int TWO_PAIRS = 7;//Represents a two pair in a five-card poker hand.
    private static final int PAIR = 8;//Represents a pair in a five-card poker hand.
    private static final int NOTHING = 9;//Represents when there is nothing in a five-card poker hand.


    //Constructors

    /***************
     * constructor
     * 
     * PURPOSE: Create an empty poker hand.
     * PARAMETERS:
     *      This constructor does not accept any parameters, it just assigns the hand
     *      to be null.
     * RETURNS: Implicitly returns a pointer to the Hand object.
     ***************/
    public Hand()
    {
        hand = null;
    }//end Hand

    /***************
     * constructor
     * 
     * PURPOSE: Create a new poker hand.
     * PARAMETERS:
     *      This constructor accepts two parameters one of which is the Deckable to draw
     *      the Cardables from and the other is a boolean on whether the cards should be drawn face
     *      up or down.
     * RETURNS: Implicitly returns a pointer to a Hand object.
     **************/
    public Hand(Deckable deck, boolean faceUp)
    {
        if(null != deck)
        {
            //Initialize array and aceHigh variables
            hand = new Cardable[HAND_SIZE];

            //Now draw the cards from the deck.
            for(int i = 0; i < hand.length; i++)
            {
                hand[i] = deck.drawACard(faceUp);
            }//end for
        }//end if
        else 
        {
            hand = null;
        }//end else
    
    }//end constructor

    //Handable interface methods.

    /***************
     * addCards
     * 
     * PURPOSE: This method is for testing only, it is used to add cards without drawing from the deck.
     * PARAMETERS:
     *      Accepts an array of Cardable to assign as the hand for the purposes of testing the Hand clas methods.
     *      Note that it is assumed that the array has size HAND_SIZE.
     * RETURNS: This method does not return anything.
     **************/
    public void addCards(Cardable[] cards)
    {
        hand = cards;
    }//end addCards

    /******************
     * getCard
     * 
     * PURPOSE: To get and return the ith card in the hand.
     * PARAMETERS:
     *      This method accepts one parameter which is an integer with the index
     *      of the card to get and return. If the passed interger is not a valid
     *      index number than null will be returned.
     * RETURNS: A Cardable object from the hand or null.
     ****************/
    public Cardable getCard(int i)
    {
        Cardable theCard = null;//The cardable object to return.

        //Check that i is a valid index and if it is get the card at that index.
        if(null != hand && 0 <= i && HAND_SIZE > i)
        {
            theCard = hand[i];
        }//end if

        return theCard;
    }//end getCard

    /***************
     * draw
     * 
     * PURPOSE: To draw cards to replace the ones that were discarded.
     * PARAMETERS:
     *      This method accepts two parameters, a Deckable from which to draw the cards from
     *      and a boolean on whether the cards should be drawn face up or down. Note that if 
     *      a spot in the Hand is null then that card has been discarded and we need to replace it.
     * RETURNS: This method does not return anything.
     ***************/
    public void draw(Deckable d, boolean faceUp)
    {
        if(null != d)
        {
            //If hand is null then we will create a new to which we add the cards to.
            if(null == hand)
            {
                hand = new Cardable[HAND_SIZE];
            }//end if

            //Now we just go through the hand and draw a card if a spot is null.
            for(int i = 0; i < hand.length; i++)
            {
                if(null == hand[i])
                {
                    hand[i] = d.drawACard(faceUp);
                }//end if
            }//end for
        }//end if
    }//end draw

    /*****************
     * showAllCards
     * 
     * PURPOSE: To flip all the cards face up so that they can be shown for the showdown.
     * PARAMETERS:
     *      This method does not accept any parameters. It just goes through the hand
     *      and ensures that all the cards are face up.
     * RETURNS: This method does not return anything.
     *****************/
    public void showAllCards()
    {
        if(null != hand)
        {
            //Go through the hand a flip all the cards face up.
            for(int i = 0; i < hand.length; i++)
            {
                hand[i].setFaceUp(true);
            }//end for
        }//end if
    }//end showAllCards

    /*******************
     * discard
     * 
     * PURPOSE: Removes all the cards from the hand that have been selected to be discarded.
     * PARAMETERS:
     *      This method does not accept any parameters. It just goes through the hand
     *      and checks if a card is selected, if it is then it is removed and returned in a linked list.
     * RETURNS: A LinkedList of the discarded cards.
     ******************/
    public LinkedList<Cardable> discard()
    {
        LinkedList<Cardable> discarded= new LinkedList<Cardable>();//The LinkedList to return with the discard cards.

        if(null != hand)
        {
            //Now check the hand for any selected cardables.
            //Note that any discarded cards will be set to null so that 
            //the GUI knows the card has been discarded.
            for(int i = 0; i < hand.length; i++)
            {
                if(hand[i].getSelected())
                {
                    discarded.add(hand[i]);
                    hand[i] = null;
                }//end if
            }//end for
        }//end if

        return discarded;
    }//end discard

    /******************
     * returnCards
     * 
     * PURPOSE: This method is used to return all the cards from the hand back to the deck.
     * PARAMETERS:
     *      This method does not accept any parameters. It just removes the cards from the hand
     *      and places them in a LinkedList which is returned from the method afterward.
     * RETURNS: A LinkedList with the cards to return to the deck.
     *****************/
    public LinkedList<Cardable> returnCards()
    {
        LinkedList<Cardable> cardsToReturn = new LinkedList<Cardable>();//The linkedList of the cards to return.

        if(null != hand)
        {
            //Now go through the hand and remove the cards from it.
            for(int i = 0; i < hand.length; i++)
            {
                //Make sure the cards being added to the list are not null.
                if(null != hand[i])
                {
                    cardsToReturn.add(hand[i]);
                    hand[i] = null;
                }//end if
            }//end for
        }//end if

        return cardsToReturn;
    }//end returnCards

    /*******************
     * evaluateHand
     * 
     * PURPOSE: Determines the best hand that can be made with the cards.
     * PARAMETERS:
     *      This method does not accept any parameters, it just evaluates the hand and
     *      constructs and returns a String of the description of that hand.
     * RETURNS: A String with the description of the best hand.
     ******************/
    public String evaluateHand()
    {
        String description = "";//The String with the description of the hand.
        int highestHandType;//Gets the type of the highest possible hand.
        Card[] sortedHand;//The sorted hand of cards.
        int[] pairs;//Stores the array from having a pair(s) as the highest type.

        if(null != hand)
        {
            //Get the highestHandType and sortedHand
            highestHandType = highestHand();
            sortedHand = sortHand();

            //Now go through each type of card and append the appropiate description to the String.
            if(STRAIGHT_FLUSH == highestHandType)
            {
                description += "Straight Flush, " + getStringRank(isAStraight(sortedHand)) + " high";
            }//end if
            else if(FOUR_OF_KIND == highestHandType)
            {
                description += "Four of a Kind, four " + getStringRank(isFourOfAKind(sortedHand)); 
            }//end else if
            else if(FULL_HOUSE == highestHandType)
            {
                description += "Full House, three " + getStringRank(isThreeOfAKind(sortedHand)) + " two ";
                pairs = hasPairs(sortedHand);
                description += getStringRank(pairs[0]);
            }//end else if
            else if(FLUSH == highestHandType)
            {
                description += "Flush, " + getStringRank(sortedHand[0].getRank()) + " high";
            }//end else if
            else if(STRAIGHT == highestHandType)
            {
                description += "Straight, " + getStringRank(isAStraight(sortedHand)) + " high";
            }//end else if
            else if(THREE_OF_KIND == highestHandType)
            {
                description += "Three of a Kind, three " + getStringRank(isThreeOfAKind(sortedHand));
            }
            else if(TWO_PAIRS == highestHandType)
            {
                description += "Two Pairs, ";
                pairs = hasPairs(sortedHand);
                description += "two " + getStringRank(pairs[0]) + " two " + getStringRank(pairs[1]);
            }//end else if
            else if(PAIR == highestHandType)
            {
                description += "Pair, two ";
                pairs = hasPairs(sortedHand);
                description += getStringRank(pairs[0]);
            }//end else if
            else
            {
                //If we have nothing then it is just the high card.
                description += "High Card, " + getStringRank(sortedHand[0].getRank());
            }//end else
        }//end if

        return description;
    }//end evaluateHand

    /*******************
     * getStringRank
     * 
     * PURPOSE: This is a helper method that is used to return the rank as a String.
     * PARAMETERS:
     *      This method accepts one parameter which is an integer value, if the value represents
     *      one of jack, queen, king, or ace than that corresiponding string is returned otherwise the
     *      String just has the integer value.
     * RETURNS: A String represention of the rank.
     ******************/
    private String getStringRank(int rank)
    {
        String stringRank;//The rank as a String.

        //Check to see if the rank is a royal card.
        if(Card.JACK == rank)
        {
            stringRank = "J";
        }//end if
        else if(Card.QUEEN == rank)
        {
            stringRank = "Q";
        }//end else if
        else if(Card.KING == rank)
        {
            stringRank = "K";
        }//end else if
        else if(Card.ACE == rank)
        {
            stringRank = "A";
        }//end else if
        else
        {
            stringRank = rank + "";
        }//end else

        return stringRank;
    }//end getStringRank

    /*******************
     * compareTo
     * 
     * PURPOSE: This method is used to determine which hand is better.
     * PARAMETERS:
     *      This method accepts one parameter which is a Handable. It then compares the 
     *      given Handable with this Handable and determines which one has the better poker hand.
     * RETURNS: An integer on whether this hand is weaker, equal to, or stronger than the given hand.
     ********************/
    public int compareTo(Handable otherHand)
    {
        int compareValue = 0;//Keeps track on which hand is better.
        Hand otherPlayer;//The other hand casted down to a Hand object.
        Card[] thisPlayer;//Array of sorted cards for this player.
        Card[] otherPlayerCards;//Array of sorted cards for other player.
        int[] thisPairs;//Array for the case there is a tie between pairs.
        int[] otherPairs;//Array for the case there is a tie between pairs.
        int thisPlayerType;//Stores the highest type of hand this player has.
        int otherPlayerType;//Stores the highest type of hand the other player has.

        //Check that the otherHand is a Hand object then complete the comparison.
        if(null != hand && null != otherHand && otherHand instanceof Hand)
        {
            otherPlayer = (Hand) otherHand;

            if(null != otherPlayer.hand)
            {
                //Get sorted hands for comparison.
                thisPlayer = sortHand();
                otherPlayerCards = otherPlayer.sortHand();

                //Now get the highest type of hand for each player.
                thisPlayerType = highestHand();
                otherPlayerType = otherPlayer.highestHand();

                compareValue = otherPlayerType - thisPlayerType;//Compare the two values.

                //If we get zero than we have a tie to break.
                if(0 == compareValue)
                {
                    //Look at the tie breaker cases now.
                    if(STRAIGHT_FLUSH == thisPlayerType || STRAIGHT == thisPlayerType)
                    {
                        compareValue = isAStraight(thisPlayer) - isAStraight(otherPlayerCards);
                    }//end if
                    else if(FOUR_OF_KIND == thisPlayerType)
                    {
                        compareValue = isFourOfAKind(thisPlayer) - isFourOfAKind(otherPlayerCards);
                    }//end else if
                    else if(FULL_HOUSE == thisPlayerType || THREE_OF_KIND == thisPlayerType)
                    {
                        //Note that in this game of poker since there cannot be two of the same three of kinds we only need to look
                        //at the three of a kind for a full house.
                        compareValue = isThreeOfAKind(thisPlayer) - isThreeOfAKind(otherPlayerCards);
                    }//end else if
                    else if(TWO_PAIRS == thisPlayerType || PAIR == thisPlayerType)
                    {
                        //We need to get the pairs seperately and then call the break pair tie method.
                        thisPairs = hasPairs(thisPlayer);
                        otherPairs = hasPairs(otherPlayerCards);
                        compareValue = breakPairTie(thisPlayer, thisPairs, otherPlayerCards, otherPairs);
                    }//end else if
                    else
                    {
                        //For the cases where there is nothing or both flushes we just compare the high cards.
                        //Note that since the cards are in descending order we compare card to card.
                        for(int i = 0; i < thisPlayer.length && (0 == compareValue); i++)
                        {
                            compareValue = thisPlayer[i].getRank() - otherPlayerCards[i].getRank();
                        }//end for
                    }//end else
                }//end if
            }//end if
        }//end if

        return compareValue;
    }//end compareTo

    //End interface methods

    /*****************
     * bestDispose
     * 
     * PURPOSE: This method is mainly used by the Smart CPU to see if there is a particular value we the card should keep.
     * PARAMETERS:
     *      This method does not accept any parameters, It determines what the highest hand
     *      that can be made and sees if there is a value we should keep to try and make a
     *      higher hand. Any cards that we should be disposed of are selected.]
     * RETURNS: Does not return anything but it may select some cards from the hand to dispose.
     *****************/
    public void bestDispose()
    {
        int highestHandPossible;//Stores the type of hand it is.
        int cardValue = 0;//Stores the card value to keep.
        int[] pairs;//Stores the result from getting pairs.
        Card[] sortedHand;

        if(null != hand)
        {
            highestHandPossible = highestHand();
            sortedHand = sortHand();

            //Note if hte hand is already a Straight flush, four of kind, full house, flush, or straight then we will not 
            //select any cards to dispose of since these hand types involve all the cards in the deck except for four of kind which does not
            //use one card.
            if(THREE_OF_KIND == highestHandPossible)
            {
                //In this case we will determine the three of a kind card and select the two cards not in the three of a kind.
                cardValue = isThreeOfAKind(sortedHand);
            }//end if
            else if(TWO_PAIRS == highestHandPossible || PAIR == highestHandPossible)
            {
                //In the case of two pairs we keep the pair of higher value.
                //Get the pairs array.
                pairs = hasPairs(sortedHand);

                //Then get the value of the first pair since that should be the one of higher value.
                cardValue = pairs[0];
            }//end else if
            else if(NOTHING == highestHandPossible)
            {
                //In this case we just select all the cards in the hand.
                //and hope for the best.
                for(int i = 0; i < hand.length; i++)
                {
                    hand[i].switchSelectedState();
                }//end for
            }//end else if

            //If cardValue is not zero then we select the cards that are not equal to the card value.
            if(0 != cardValue)
            {
                for(int i = 0; i < hand.length; i++)
                {
                    if(hand[i] instanceof Card)
                    {
                        if(((Card) hand[i]).getRank() != cardValue)
                        {
                            hand[i].switchSelectedState();
                        }//end if
                    }//end if
                }//end for
            }//end if
        }//end if
    }//end bestDispose

    //Helper methods

    /****************
     * highestHand
     * 
     * PURPOSE: This method is used to determine the best type of hand that a player has.
     * PARAMETERS:
     *      This method does not accept any parameters. It just check through all the types of poker hands
     *      and determines the best one.
     * RETURNS: An integer corresponding to a hand type.
     ****************/
    private int highestHand()
    {
        int highestType = NOTHING;//Keeps track of the highest hand.
        Card[] sortedHand = sortHand();//Get the sorted hand.
        boolean hasFlush = sameSuit();//Determine if the hand is a flush.

        if(null != hand)
        {
            //Now we just check each type of hand in order from highest to lowest.
            if(hasFlush && 0 < isAStraight(sortedHand))
            {
                highestType = STRAIGHT_FLUSH;
            }//end if
            else if(0 < isFourOfAKind(sortedHand))
            {
                highestType = FOUR_OF_KIND;
            }//end else if
            else if(0 < isThreeOfAKind(sortedHand) && 0 < hasPairs(sortedHand)[0])
            {
                highestType = FULL_HOUSE;
            }//end else if
            else if(hasFlush)
            {
                highestType = FLUSH;
            }//end else if
            else if(0 < isAStraight(sortedHand))
            {
                highestType = STRAIGHT;
            }//end else if
            else if(0 < isThreeOfAKind(sortedHand))
            {
                highestType = THREE_OF_KIND;
            }//end else if
            else if(0 < hasPairs(sortedHand)[0] && 0 < hasPairs(sortedHand)[1])
            {
                highestType = TWO_PAIRS;
            }//end else if
            else if(0 < hasPairs(sortedHand)[0])
            {
                highestType = PAIR;
            }//end else if
        }//end if

        return highestType;
    }//end highestType

    /***************
     * sortHand
     * 
     * PURPOSE: This method is used to sort the cards in the hand.
     * PARAMETERS:
     *      This method does not accept any parameters. It takes each Cardable in the hand
     *      checks if it is an instance of a Card and then does a shallow copy of the card
     *      into a sorted array.
     * RETURNS: An sorted array of the Cards in the hand.
     ***************/
    private Card[] sortHand()
    {
        Card[] sortedHand = new Card[HAND_SIZE];//The array with the sorted hand.
        boolean spotFound = false;//Used to make determine when the spot for the card is found.
        int currentSize = 0;//Stores the number of cards currently in the sortedHand.
        int index;//The current index of the sortedHand array.
        Card temp;//Used to temporarily store the sorted card.
        
        if(null != hand)
        {
            //Now we just perform an ordered insert of the cards.
            for(int i = 0; i < HAND_SIZE; i++)
            {
                //Make sure to check that the cardable in the hand is a card, also make sure it is not null.
                if(null != hand[i] && hand[i] instanceof Card)
                {
                    //Now we can cast hand[i] to a card.
                    temp = (Card) hand[i];
                    
                    //Now set up to so an ordered insertion.
                    index = currentSize;
                    spotFound = false;

                    //Do the ordered insertion.
                    while(0 < index && !spotFound)
                    {
                        //Now we check whether or not we found the spot to place the card.
                        if(0 >= temp.compareTo(sortedHand[index - 1]))
                        {
                            spotFound = true;
                        }//end if
                        else
                        {
                            sortedHand[index] = sortedHand[index - 1];
                            index--;
                        }//end else 

                    }//end while

                    sortedHand[index] = temp;//Then add the card to the sortedHand.
                    currentSize++;
                }//end if
            }//end for
        }//end if

        return sortedHand;
    }//end sortHand.

    /***************
     * sameSuit
     * 
     * PURPOSE: This method is used to determine if all the cards in the hand have the same suit.
     * PARAMETERS:
     *      This method does not accept any parameters. It just checks each card for the suit and determines
     *      if all the cards have the same suit.
     * RETURNS: A boolean on whether or not all the cards have the same suit.
     ***************/
    private boolean sameSuit()
    {
        boolean allSame = true;//Stores whether or not the cards have the same suit.
        Cardable.Suit suitType = Cardable.Suit.HEART;//Stores the suit that all the cards have.

        if(null != hand)
        {
            //Now we go through the hand and make sure all the cards have the same suit.
            for(int i = 0; i < hand.length && allSame; i++)
            {
                //Note for the first card we need to get the actual suit.
                if(0 == i)
                {
                    suitType = hand[i].getSuit();
                }//end if
                else if(suitType != hand[i].getSuit())
                {
                    allSame = false;
                }//end else if
            }//end for
        }//end if

        return allSame;
    }//end sameSuit

    /***************
     * isAStraight
     * 
     * PURPOSE: This method is used to determine if a hand is a straight.
     * PARAMETERS:
     *      This method accpets one parameter which is an array of cards
     *      sorted in decending order. The method then return the highest card if it is
     *      a straight or 0 if there is not straight.
     * RETURNS: An integer with highest card if it is a straight or zero. Note the case where five is returned in this case ACE becomes the low card.
     * The highAce field will be set outside this method.
     ***************/
    private int isAStraight(Card[] sortedHand)
    {
        int highCard = 0;//The highest card in the hand if it is a straight.
        boolean inOrder = true;//Determines if the cards are in sequence.

        if(null != sortedHand)
        {

            //Note that there are two cases to check here one is just a normal case.
            //where the ace is the high card and the other is where the ace is the low card
            //in which case 5 is the high card.
            
            //First we check the normal case.
            for(int i = 0; i < sortedHand.length - 1 && inOrder; i++)
            {
                inOrder = (sortedHand[i].getRank() == (sortedHand[i+1].getRank() + 1));
            }//end if

            //Now if we don't have a straight we then check the second case.
            if(!inOrder && (Card.LOWEST_RANK == sortedHand[sortedHand.length - 1].getRank()) && (Card.ACE == sortedHand[0].getRank()))
            {
                inOrder = true;//Reset in order.

                //Note here that we do not want to compare the ACE with the second card as it is still entered as the highest value.
                for(int i = sortedHand.length - 1; i > 1 && inOrder; i--)
                {
                    inOrder = (sortedHand[i].getRank() == sortedHand[i - 1].getRank() - 1);
                }//end for

                //Now if the cards form a low straight then ACE becomes the low card.
                if(inOrder)
                {
                    highCard = sortedHand[1].getRank();//Then the high card becomes the five.
                }//end if
            }//end if
            else if(inOrder)
            {
                highCard = sortedHand[0].getRank();
            }//end else if
        }//end if

        return highCard;
    }//end isAStraight

    /*****************
     * isFourOfAKind
     * 
     * PURPOSE: This method is used to determine if the hand is a four of a kind.
     * PARAMETERS:
     *      This method accepts one parameter which is an array of sorted cards.
     *      The method then checks four a four of a kind and returns the value of the card
     *      that has four of its kind or zero if there is no four of a kind.
     * RETURNS: An integer with the value of the card with four of a kind or zero.
     *****************/
    private int isFourOfAKind(Card[] sortedHand)
    {
        //Note that in a 52 card deck there will not be a case where both players have
        //four of the same kind so we do not need to consider the case where we look at the
        //kicker(fifth card)
        int fourCard = 0;//Stores the value of the card with four of its kind.
        int compareCard1, compareCard2;//Stores the value of the card to check for.
        int found1 = 0, found2 = 0;//Stores the number of cards found of the compareCards.
        final int NUM_KIND = 4;//Number of cards of the same kind that we need.

        if(null != hand && HAND_SIZE == sortedHand.length)
        {
        
            compareCard1 = sortedHand[0].getRank();
            compareCard2 = sortedHand[1].getRank();
            for(int i = 0; i < sortedHand.length; i++)
            {
                //Now increment the found values.
                if(sortedHand[i].getRank() == compareCard1)
                {
                    found1++;
                }//end if
                else if(sortedHand[i].getRank() == compareCard2)
                {
                    found2++;
                }//end else if
            }//end for

            //Now check to see if found1 or found2 are equal to 4 signaling that we have a four of a kind.
            if(NUM_KIND == found1)
            {
                fourCard = compareCard1;
            }//end if
            else if(NUM_KIND == found2)
            {
                fourCard = compareCard2;
            }//end else if
        }//end if

        return fourCard;
    }//end isFourOfAKind

    /********************
     * isThreeOfAKind
     * 
     * PURPOSE: This method is used to determine whether we have a three of a kind.
     * PARAMETERS:
     *      This method accepts one parameter which is a sorted array of cards.
     *      It then checks the array of cards for a three of a kind in a five-card
     *      poker hand.
     * RETURNS: This method returns 0 if there is no three of a kind or the value of the card with three.
     *******************/
    private int isThreeOfAKind(Card[] sortedHand)
    {
        //Since the array of cards is sorted the three of a kind will
        //start in either the first second or third position. Also note
        //that in this game of poker it is not possible for both players
        //to have the same three of a kind.
        int threeCard = 0;//Stores the value of the card with three of a kind.
        int compareCard1, compareCard2, compareCard3;//Stores the ranks of the cards to search for.
        int found1 = 0, found2 = 0, found3 = 0;//Stores the number of each card found above.
        final int NUM_KIND = 3;//Stores  the number of cards we are looking for.

        if(null != sortedHand && HAND_SIZE == sortedHand.length)
        {
            //Set up the compare cards these are the values found positions 0, 1, 2
            compareCard1 = sortedHand[0].getRank();
            compareCard2 = sortedHand[1].getRank();
            compareCard3 = sortedHand[2].getRank();
            for(int i = 0; i < sortedHand.length; i++)
            {
                if(sortedHand[i].getRank() == compareCard1)
                {
                    found1++;
                }//end if
                else if(sortedHand[i].getRank() == compareCard2)
                {
                    found2++;
                }//end else if
                else if(sortedHand[i].getRank() == compareCard3)
                {
                    found3++;
                }//end else if
            }//end for

            //Now check to see if any of them are three.
            //Note we also have to check if any of the compareCards are equal since we do not want to count
            //a four of a kind.
            if(NUM_KIND == found1)
            {
                threeCard = compareCard1;
            }//end if
            else if(NUM_KIND == found2 && compareCard1 != compareCard2)
            {
                threeCard = compareCard2;
            }//end else if
            else if(NUM_KIND == found3 && compareCard1 != compareCard3 && compareCard2 != compareCard3)
            {
                threeCard = compareCard3;
            }//end else if
        }//end if

        return threeCard;
    }//end isThreeOfAKind

    /*****************
     * hasPairs
     * 
     * PURPOSE: This method is used to determine whether a hand has any pairs.
     * PARAMETERS:
     *      This method accpets an array of sorted Cards. It then searches the array
     *      for any pairs of cards in a five-card poker hand and returns an array
     *      of size 2 where the first value is the value of the highest pair and
     *      the second value is the value of the lowest pair. If there is no
     *      pairs or only one pair then wither one value will be put in the array
     *      or the array will be filled with zeros.
     * RETURNS: An array of integers with teh values of the pairs in the array or zeros if no pairs.
     ***************/
    private int[] hasPairs(Card[] sortedHand)
    {
        //Since the hand is sorted the highest pair will be the first pair and 
        //the lower pair will be the second.
        int[] pairs = {0, 0};//Start off with an array of zeros.First zero is first pair and second zero is where the second pair value goes.
        int compareCard1, compareCard2, compareCard3, compareCard4;//Used to compare the start positions of where a pair can start.
        int found1 = 0, found2 = 0, found3 = 0, found4 = 0;//Counts the number of compareCards we found.
        final int NUM_KIND = 2;//Number of cards we are looking for.

        if(null != sortedHand && HAND_SIZE == sortedHand.length)
        {
            //Now the compareCard are in positions 0, 1, 2, 3 of the sorted hand.
            compareCard1 = sortedHand[0].getRank();
            compareCard2 = sortedHand[1].getRank();
            compareCard3 = sortedHand[2].getRank();
            compareCard4 = sortedHand[3].getRank();
            for(int i = 0; i < sortedHand.length; i++)
            {
                if(sortedHand[i].getRank() == compareCard1)
                {
                    found1++;
                }//end if
                else if(sortedHand[i].getRank() == compareCard2)
                {
                    found2++;
                }//end if
                else if(sortedHand[i].getRank() == compareCard3)
                {
                    found3++;
                }//end if
                else if(sortedHand[i].getRank() == compareCard4)
                {
                    found4++;
                }//end if
            }//end for

            //Now check the counts for any pairs. Note that the first pair is the highest so it will
            //go into the first position.
            if(NUM_KIND == found1)
            {
                pairs[0] = compareCard1;
            }//end if
            else if(NUM_KIND == found2 && compareCard1 != compareCard2)
            {
                //In this case a pair found at the second card will be a first pair.
                pairs[0] = compareCard2;
            }//end else if

            //Now check the thrid and fourth cards.
            if(NUM_KIND == found3 && compareCard1 != compareCard3 && compareCard2 != compareCard3)
            {
                //Determine if this is the first or second pair.
                if(0 == pairs[0])
                {
                    pairs[0] = compareCard3;
                }//end if
                else
                {
                    pairs[1] = compareCard3;
                }//end else
            }//end if
            else if(NUM_KIND == found4 && compareCard1 != compareCard4 && compareCard2 != compareCard4 && compareCard3 != compareCard4)
            {
                //Determine if this is the first or second pair.
                if(0 == pairs[0])
                {
                    pairs[0] = compareCard4;
                }//end if
                else
                {
                    pairs[1] = compareCard4;
                }//end else
            }//end else if
        }//end if

        return pairs;
    }//end hasPairs

    /****************
     * breakPairTie
     * 
     * PURPOSE: This method is used to break a tie between the two players if they both have the same number of pairs.
     * PARAMETERS:
     *      This method acepts four parameters, two of them are arrays of sorted cards representing both hands of the players
     *      and the other two are the array of integer returned from the hasPairs method. This method then
     *      determines which player has the better hand of pairs. If both players have the exact same hands then zero will
     *      be returned.
     * RETURNS: An integer that is negative if the first player has the better hand, positive if it is the second player or zero if both equal.
     ***************/
    private int breakPairTie(Card[] player1Hand, int[] player1Pairs, Card[] player2Hand, int[] player2Pairs)
    {
        final int PLAYER_ONE = 1;//Number to return if player 1 has the better hand.
        final int PLAYER_TWO = -1;//Number to return if player 2 has the better hand.
        int better = 0;//The value to return from this method on which player has the better hand. Initialized to zero as both hands are equal until found otherwise.

        if(null != player1Hand && null != player1Pairs && null != player2Hand && null != player2Pairs && HAND_SIZE == player1Hand.length && HAND_SIZE == player2Hand.length)
        {
            //First check the values from the pairs of cards.
            if(2 >= player1Pairs.length && 2 >= player2Pairs.length)
            {
                //Check the first pair.
                if(player1Pairs[0] > player2Pairs[0])
                {
                    better = PLAYER_ONE;
                }//end if
                else if(player1Pairs[0] < player2Pairs[0])
                {
                    better = PLAYER_TWO;
                }//end else if
                else
                {
                    //Other wise we need to check the second pairs.
                    if(player1Pairs[1] > player2Pairs[1])
                    {
                        better = PLAYER_ONE;
                    }//end if
                    else if(player1Pairs[1] < player2Pairs[1])
                    {
                        better = PLAYER_TWO;
                    }//end else if
                    else
                    {
                        //Since both pairs are equal we need to find the odd card in the hand.
                        //Note that if any of the players does not have a second pair than they just have a zero value.
                        for(int i = 0; i < player1Hand.length; i++)
                        {
                            //Since the cards of the hands are in order we can just compare the cards hand to hand
                            //to which hand has a higher card.
                            if(player1Hand[i].getRank() > player2Hand[i].getRank())
                            {
                                better = PLAYER_ONE;
                            }//end if
                            else if(player2Hand[i].getRank() > player1Hand[i].getRank())
                            {
                                better = PLAYER_TWO;
                            }//end else if
                        }//end for
                    }//end else
                }//end else
            }//end if
        }//end if

        return better;
    }//end breakPairTie

}//end Hand class
